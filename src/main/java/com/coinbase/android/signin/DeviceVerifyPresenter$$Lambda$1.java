package com.coinbase.android.signin;

import rx.functions.Action0;

final /* synthetic */ class DeviceVerifyPresenter$$Lambda$1 implements Action0 {
    private final DeviceVerifyPresenter arg$1;

    private DeviceVerifyPresenter$$Lambda$1(DeviceVerifyPresenter deviceVerifyPresenter) {
        this.arg$1 = deviceVerifyPresenter;
    }

    public static Action0 lambdaFactory$(DeviceVerifyPresenter deviceVerifyPresenter) {
        return new DeviceVerifyPresenter$$Lambda$1(deviceVerifyPresenter);
    }

    public void call() {
        DeviceVerifyPresenter.lambda$onBackPressed$0(this.arg$1);
    }
}
