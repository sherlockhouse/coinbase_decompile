package com.coinbase.android.gdpr;

import android.util.Pair;
import rx.functions.Action1;

final /* synthetic */ class GdprMarketingEmailPresenter$$Lambda$1 implements Action1 {
    private final GdprMarketingEmailPresenter arg$1;

    private GdprMarketingEmailPresenter$$Lambda$1(GdprMarketingEmailPresenter gdprMarketingEmailPresenter) {
        this.arg$1 = gdprMarketingEmailPresenter;
    }

    public static Action1 lambdaFactory$(GdprMarketingEmailPresenter gdprMarketingEmailPresenter) {
        return new GdprMarketingEmailPresenter$$Lambda$1(gdprMarketingEmailPresenter);
    }

    public void call(Object obj) {
        GdprMarketingEmailPresenter.lambda$updateEmailPreferences$0(this.arg$1, (Pair) obj);
    }
}
