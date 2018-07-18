package com.coinbase.android.signin;

import com.coinbase.android.utils.analytics.MixpanelTracking;
import rx.functions.Action1;

final /* synthetic */ class EmailVerifyPresenter$$Lambda$1 implements Action1 {
    private final EmailVerifyPresenter arg$1;

    private EmailVerifyPresenter$$Lambda$1(EmailVerifyPresenter emailVerifyPresenter) {
        this.arg$1 = emailVerifyPresenter;
    }

    public static Action1 lambdaFactory$(EmailVerifyPresenter emailVerifyPresenter) {
        return new EmailVerifyPresenter$$Lambda$1(emailVerifyPresenter);
    }

    public void call(Object obj) {
        this.arg$1.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_VERIFY_EMAIL_VIEWED, new String[0]);
    }
}
