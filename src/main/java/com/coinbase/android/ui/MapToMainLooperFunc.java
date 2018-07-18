package com.coinbase.android.ui;

import android.os.Looper;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;

public class MapToMainLooperFunc<T> implements Func1<T, Observable<T>> {
    public Observable<T> call(T o) {
        Looper myLooper = Looper.myLooper();
        Observable<T> observable = Observable.just(o);
        if (myLooper == null || !myLooper.equals(Looper.getMainLooper())) {
            return observable.observeOn(AndroidSchedulers.mainThread());
        }
        return observable;
    }
}
