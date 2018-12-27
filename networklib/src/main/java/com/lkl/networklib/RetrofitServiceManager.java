package com.lkl.networklib;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
* 生成Retrofit实例，配置OkHttpClient和公共配置
* */
public class RetrofitServiceManager {
    private static final int DEFAULT_TIME_OUT = 5;//超时时间5s
    public static final int DEFAULT_READ_OUT = 10;

    private Retrofit mRetrofit;

    public RetrofitServiceManager() {
        //创建OkHttpClient
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);//连接超时时间
        builder.writeTimeout(DEFAULT_READ_OUT, TimeUnit.SECONDS);//写操作超时时间
        builder.readTimeout(DEFAULT_READ_OUT, TimeUnit.SECONDS);//读操作超时时间

        //添加公共参数拦截器
        HttpCommonInterceptor interceptor = new HttpCommonInterceptor.Builder()
                .addHeaderParams("paltform", "android")
                .addHeaderParams("userToken", "123434343343433df3434")
                .addHeaderParams("userId", "123455")
                .build();

        builder.addInterceptor(interceptor);

        //创建Retrofit
        mRetrofit = new Retrofit.Builder()
                .client(builder.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("baseUrl")
                .build();
    }

    private static class SingletonHolder{
        private static final RetrofitServiceManager INSTANCE = new RetrofitServiceManager();
    }

    public static  RetrofitServiceManager getInstance(){
        return SingletonHolder.INSTANCE;
    }

    public <T> T create(Class<T> service){
        return mRetrofit.create(service);
    }
}
