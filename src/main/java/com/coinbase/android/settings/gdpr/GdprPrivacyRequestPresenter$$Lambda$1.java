package com.coinbase.android.settings.gdpr;

import rx.functions.Action1;

final /* synthetic */ class GdprPrivacyRequestPresenter$$Lambda$1 implements Action1 {
    private final GdprPrivacyRequestPresenter arg$1;

    private GdprPrivacyRequestPresenter$$Lambda$1(GdprPrivacyRequestPresenter gdprPrivacyRequestPresenter) {
        this.arg$1 = gdprPrivacyRequestPresenter;
    }

    public static Action1 lambdaFactory$(GdprPrivacyRequestPresenter gdprPrivacyRequestPresenter) {
        return new GdprPrivacyRequestPresenter$$Lambda$1(gdprPrivacyRequestPresenter);
    }

    public void call(Object obj) {
        this.arg$1.mScreen.updateListItems();
    }
}
