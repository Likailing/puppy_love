package com.example.demo1.rxjavaandretrofit.util;

import io.reactivex.observers.DisposableObserver;

public abstract class DefaultDisposableObserver<T> extends DisposableObserver<T> {

    @Override
    public void onComplete() {

    }
}
