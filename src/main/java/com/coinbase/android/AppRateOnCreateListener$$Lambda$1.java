package com.coinbase.android;

import hotchemi.android.rate.OnClickButtonListener;

final /* synthetic */ class AppRateOnCreateListener$$Lambda$1 implements OnClickButtonListener {
    private final AppRateOnCreateListener arg$1;

    private AppRateOnCreateListener$$Lambda$1(AppRateOnCreateListener appRateOnCreateListener) {
        this.arg$1 = appRateOnCreateListener;
    }

    public static OnClickButtonListener lambdaFactory$(AppRateOnCreateListener appRateOnCreateListener) {
        return new AppRateOnCreateListener$$Lambda$1(appRateOnCreateListener);
    }

    public void onClickButton(int i) {
        AppRateOnCreateListener.lambda$new$0(this.arg$1, i);
    }
}
