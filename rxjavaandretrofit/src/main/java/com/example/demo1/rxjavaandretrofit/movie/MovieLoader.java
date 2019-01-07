package com.example.demo1.rxjavaandretrofit.movie;

import com.example.demo1.rxjavaandretrofit.util.ApiService;
import com.example.demo1.rxjavaandretrofit.util.CommonLoader;
import com.example.demo1.rxjavaandretrofit.util.RetrofitServiceManager;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;


/**
 * 业务Loader:获取Observable并处理相关业务
 * 把 请求逻辑 封装在 一个业务Loader 里面，一个 Loader 里面可以处理多个Api 接口。
 * */
public class MovieLoader extends CommonLoader {
    private ApiService apiService;

    public MovieLoader() {
        apiService = RetrofitServiceManager.getInstance().create(ApiService.class);
    }

    /**
    * 获取电影列表
    * */
    public Observable<List<Movie>> getMovie(int start, int count){
        return observe(apiService.getTop250Observable(start,count))
                .map(new Function<MovieSubject, List<Movie>>() {
            @Override
            public List<Movie> apply(MovieSubject movieSubject) throws Exception {
                return movieSubject.getSubjects();
            }
        });
    }

    /**
    * 获取天气列表
    * */
    public Observable<String> getWeatherList(String cityId, String key){
        return observe(apiService.getWeather(cityId,key)).map(new Function<String, String>() {
            @Override
            public String apply(String s) throws Exception {
                return s;
            }
        });
    }
}
