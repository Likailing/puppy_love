package com.example.demo1.rxjavaandretrofit.util;


import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 将一些重复的操作提出来，放到父类以免Loader里每个接口都有重复代码
 * */
public class CommonLoader {
     protected <T> Observable<T> observe(Observable<T> observable){
         return observable
                 .subscribeOn(Schedulers.io())
                 .unsubscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread());
     }
}
