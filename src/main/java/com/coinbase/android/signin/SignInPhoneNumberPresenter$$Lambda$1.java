package com.coinbase.android.signin;

import com.coinbase.android.utils.analytics.MixpanelTracking;
import rx.functions.Action0;

final /* synthetic */ class SignInPhoneNumberPresenter$$Lambda$1 implements Action0 {
    private final SignInPhoneNumberPresenter arg$1;

    private SignInPhoneNumberPresenter$$Lambda$1(SignInPhoneNumberPresenter signInPhoneNumberPresenter) {
        this.arg$1 = signInPhoneNumberPresenter;
    }

    public static Action0 lambdaFactory$(SignInPhoneNumberPresenter signInPhoneNumberPresenter) {
        return new SignInPhoneNumberPresenter$$Lambda$1(signInPhoneNumberPresenter);
    }

    public void call() {
        this.arg$1.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_REJECT_ADDING_PHONE, new String[0]);
    }
}
