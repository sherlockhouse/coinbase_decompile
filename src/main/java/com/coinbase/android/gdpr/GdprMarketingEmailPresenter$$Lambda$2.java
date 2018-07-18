package com.coinbase.android.gdpr;

import rx.functions.Action1;

final /* synthetic */ class GdprMarketingEmailPresenter$$Lambda$2 implements Action1 {
    private final GdprMarketingEmailPresenter arg$1;

    private GdprMarketingEmailPresenter$$Lambda$2(GdprMarketingEmailPresenter gdprMarketingEmailPresenter) {
        this.arg$1 = gdprMarketingEmailPresenter;
    }

    public static Action1 lambdaFactory$(GdprMarketingEmailPresenter gdprMarketingEmailPresenter) {
        return new GdprMarketingEmailPresenter$$Lambda$2(gdprMarketingEmailPresenter);
    }

    public void call(Object obj) {
        GdprMarketingEmailPresenter.lambda$updateEmailPreferences$1(this.arg$1, (Throwable) obj);
    }
}
