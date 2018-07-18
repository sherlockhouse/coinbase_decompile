package com.coinbase.android.signin;

import com.coinbase.android.utils.analytics.MixpanelTracking;
import rx.functions.Action0;

final /* synthetic */ class AcceptTermsPresenter$$Lambda$7 implements Action0 {
    private final AcceptTermsPresenter arg$1;

    private AcceptTermsPresenter$$Lambda$7(AcceptTermsPresenter acceptTermsPresenter) {
        this.arg$1 = acceptTermsPresenter;
    }

    public static Action0 lambdaFactory$(AcceptTermsPresenter acceptTermsPresenter) {
        return new AcceptTermsPresenter$$Lambda$7(acceptTermsPresenter);
    }

    public void call() {
        this.arg$1.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_REJECT_TOS, new String[0]);
    }
}
