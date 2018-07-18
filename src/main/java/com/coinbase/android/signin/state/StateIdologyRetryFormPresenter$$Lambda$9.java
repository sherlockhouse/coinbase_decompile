package com.coinbase.android.signin.state;

import com.coinbase.android.utils.analytics.MixpanelTracking;
import rx.functions.Action0;

final /* synthetic */ class StateIdologyRetryFormPresenter$$Lambda$9 implements Action0 {
    private final StateIdologyRetryFormPresenter arg$1;

    private StateIdologyRetryFormPresenter$$Lambda$9(StateIdologyRetryFormPresenter stateIdologyRetryFormPresenter) {
        this.arg$1 = stateIdologyRetryFormPresenter;
    }

    public static Action0 lambdaFactory$(StateIdologyRetryFormPresenter stateIdologyRetryFormPresenter) {
        return new StateIdologyRetryFormPresenter$$Lambda$9(stateIdologyRetryFormPresenter);
    }

    public void call() {
        this.arg$1.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_REJECT_IDENTITY_VERIFICATION, new String[0]);
    }
}
