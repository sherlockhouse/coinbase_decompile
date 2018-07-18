package com.coinbase.android;

import android.app.Application;
import com.coinbase.android.utils.GoogleApiClientWrapper;
import rx.functions.Func0;

final /* synthetic */ class GoogleClientApiModule$$Lambda$1 implements Func0 {
    private final Application arg$1;

    private GoogleClientApiModule$$Lambda$1(Application application) {
        this.arg$1 = application;
    }

    public static Func0 lambdaFactory$(Application application) {
        return new GoogleClientApiModule$$Lambda$1(application);
    }

    public Object call() {
        return new GoogleApiClientWrapper(this.arg$1);
    }
}
