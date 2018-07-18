package com.coinbase.android.signin.state;

import com.coinbase.api.internal.models.idology.Data;
import rx.functions.Action1;

final /* synthetic */ class StateIdologyRetryFormPresenter$$Lambda$7 implements Action1 {
    private final StateIdologyRetryFormPresenter arg$1;

    private StateIdologyRetryFormPresenter$$Lambda$7(StateIdologyRetryFormPresenter stateIdologyRetryFormPresenter) {
        this.arg$1 = stateIdologyRetryFormPresenter;
    }

    public static Action1 lambdaFactory$(StateIdologyRetryFormPresenter stateIdologyRetryFormPresenter) {
        return new StateIdologyRetryFormPresenter$$Lambda$7(stateIdologyRetryFormPresenter);
    }

    public void call(Object obj) {
        this.arg$1.processVerificationResult((Data) obj);
    }
}
