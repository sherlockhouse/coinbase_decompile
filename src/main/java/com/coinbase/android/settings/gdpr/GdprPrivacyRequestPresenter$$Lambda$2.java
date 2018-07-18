package com.coinbase.android.settings.gdpr;

import android.util.Pair;
import rx.functions.Action1;

final /* synthetic */ class GdprPrivacyRequestPresenter$$Lambda$2 implements Action1 {
    private final GdprPrivacyRequestPresenter arg$1;

    private GdprPrivacyRequestPresenter$$Lambda$2(GdprPrivacyRequestPresenter gdprPrivacyRequestPresenter) {
        this.arg$1 = gdprPrivacyRequestPresenter;
    }

    public static Action1 lambdaFactory$(GdprPrivacyRequestPresenter gdprPrivacyRequestPresenter) {
        return new GdprPrivacyRequestPresenter$$Lambda$2(gdprPrivacyRequestPresenter);
    }

    public void call(Object obj) {
        GdprPrivacyRequestPresenter.lambda$onSendRequestClicked$1(this.arg$1, (Pair) obj);
    }
}
