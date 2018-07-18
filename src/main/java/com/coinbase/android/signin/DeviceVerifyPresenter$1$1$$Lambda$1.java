package com.coinbase.android.signin;

import com.coinbase.android.signin.DeviceVerifyPresenter.1.AnonymousClass1;
import rx.functions.Action1;

final /* synthetic */ class DeviceVerifyPresenter$1$1$$Lambda$1 implements Action1 {
    private final AnonymousClass1 arg$1;

    private DeviceVerifyPresenter$1$1$$Lambda$1(AnonymousClass1 anonymousClass1) {
        this.arg$1 = anonymousClass1;
    }

    public static Action1 lambdaFactory$(AnonymousClass1 anonymousClass1) {
        return new DeviceVerifyPresenter$1$1$$Lambda$1(anonymousClass1);
    }

    public void call(Object obj) {
        AnonymousClass1.lambda$run$0(this.arg$1, (Boolean) obj);
    }
}
