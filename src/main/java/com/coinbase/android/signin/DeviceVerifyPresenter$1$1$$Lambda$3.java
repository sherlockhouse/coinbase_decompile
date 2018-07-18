package com.coinbase.android.signin;

import com.coinbase.android.signin.DeviceVerifyPresenter.1.AnonymousClass1;
import rx.Observable.OnSubscribe;
import rx.Subscriber;

final /* synthetic */ class DeviceVerifyPresenter$1$1$$Lambda$3 implements OnSubscribe {
    private final AnonymousClass1 arg$1;
    private final Throwable arg$2;

    private DeviceVerifyPresenter$1$1$$Lambda$3(AnonymousClass1 anonymousClass1, Throwable th) {
        this.arg$1 = anonymousClass1;
        this.arg$2 = th;
    }

    public static OnSubscribe lambdaFactory$(AnonymousClass1 anonymousClass1, Throwable th) {
        return new DeviceVerifyPresenter$1$1$$Lambda$3(anonymousClass1, th);
    }

    public void call(Object obj) {
        AnonymousClass1.lambda$null$1(this.arg$1, this.arg$2, (Subscriber) obj);
    }
}
