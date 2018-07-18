package com.coinbase.android.signin.state;

import com.coinbase.android.utils.analytics.MixpanelTracking;
import rx.functions.Action0;

final /* synthetic */ class StateIdologyNameFormPresenter$$Lambda$1 implements Action0 {
    private final StateIdologyNameFormPresenter arg$1;

    private StateIdologyNameFormPresenter$$Lambda$1(StateIdologyNameFormPresenter stateIdologyNameFormPresenter) {
        this.arg$1 = stateIdologyNameFormPresenter;
    }

    public static Action0 lambdaFactory$(StateIdologyNameFormPresenter stateIdologyNameFormPresenter) {
        return new StateIdologyNameFormPresenter$$Lambda$1(stateIdologyNameFormPresenter);
    }

    public void call() {
        this.arg$1.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_REJECT_IDENTITY_VERIFICATION, new String[0]);
    }
}
