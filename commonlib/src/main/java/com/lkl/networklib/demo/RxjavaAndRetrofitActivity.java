package com.lkl.networklib.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lkl.networklib.R;
import com.lkl.networklib.util.DefaultDisposableObserver;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
* Retrofit网络请求使用
 *和  Retrofit网络请求+配合Rxjava使用
* */
public class RxjavaAndRetrofitActivity extends AppCompatActivity {
    public static final String BASE_URL = "https://api.douban.com/v2/movie/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava_and_retrofit);

        getData1();//Retrofit网络请求
        getData2();//Retrofit网络请求+配合Rxjava使用
        getData3();//封装使用
    }

    private void getData3(){
        MovieLoader loader = new MovieLoader();
        loader.getMovie(0,20)
                .subscribe(new DefaultDisposableObserver<List<Movie>>() {
                    @Override
                    public void onNext(List<Movie> movies) {
                        //刷新数据
                    }

                    @Override
                    public void onError(Throwable e) {
                        //处理异常情况
                    }
                });
    }


    private void getData2(){
        //创建Retrofit实例
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(200, TimeUnit.SECONDS);//连接操作（超时时间）
        builder.writeTimeout(200, TimeUnit.SECONDS);//写操作（超时时间）
        builder.readTimeout(200, TimeUnit.SECONDS);//读操作（超时时间）
        builder.retryOnConnectionFailure(true);//错误重连

        Interceptor headerInterceptor = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                Request.Builder requestBuilder = originalRequest.newBuilder()
                        .addHeader("Accept-Encoding","gzip")
                        .addHeader("Accept","application/json")
                        .addHeader("Content-Type", "application/json; charset=utf-8")
                        //jdhahidahfdj是token
                        .addHeader("Authorization", "Bearer"+"jdhahidahfdj")//添加请求头信息，服务器进行token有效性验证
                        .method(originalRequest.method(),originalRequest.body());
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        };
        builder.addInterceptor(headerInterceptor);

        Retrofit retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.douban.com/v2/movie/")
                .build();

        //获取接口实例
        ApiService apiService = retrofit.create(ApiService.class);
        apiService.getTop250Observable(0, 20)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<Object, String>() {
                    @Override
                    public String apply(Object oubject) throws Exception {
                        return null;
                    }
                })
                .subscribe(new DefaultDisposableObserver<String>() {
                    @Override
                    public void onNext(String o) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                });
    }

    private void getData1(){
        //创建Retrofit实例
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.douban.com/v2/movie/")
                .addConverterFactory(GsonConverterFactory.create())//GsonConverterFactory是默认提供的Gson转换器
                .build();


        //获取接口实例
        ApiService apiService = retrofit.create(ApiService.class);
        //调用方法得到一个Call
        Call<Object> top250Get = apiService.getTop250Get(0, 20);
        Call<Object> top250Post = apiService.getTop250Post(0, 20);
        //进行网络请求
        //异步方式
        top250Get.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                //请求返回得结果
                Object body = response.body();
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                t.printStackTrace();
            }
        });
        top250Post.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                //请求返回得结果
                Object body = response.body();
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                t.printStackTrace();
            }
        });

        //同步方式
        try {
            //请求返回得结果response
            Response<Object> response = top250Get.execute();
            Response<Object> response1 = top250Post.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
