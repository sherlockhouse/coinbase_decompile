package com.coinbase.android.settings.gdpr;

import com.coinbase.android.settings.SettingsPreferenceItem;
import rx.functions.Action1;

final /* synthetic */ class PrivacyRightsPresenter$$Lambda$1 implements Action1 {
    private final PrivacyRightsPresenter arg$1;

    private PrivacyRightsPresenter$$Lambda$1(PrivacyRightsPresenter privacyRightsPresenter) {
        this.arg$1 = privacyRightsPresenter;
    }

    public static Action1 lambdaFactory$(PrivacyRightsPresenter privacyRightsPresenter) {
        return new PrivacyRightsPresenter$$Lambda$1(privacyRightsPresenter);
    }

    public void call(Object obj) {
        this.arg$1.mRouter.routeToRequestController((SettingsPreferenceItem) obj);
    }
}
