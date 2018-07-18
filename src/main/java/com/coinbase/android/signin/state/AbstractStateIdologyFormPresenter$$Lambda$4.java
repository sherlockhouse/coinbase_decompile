package com.coinbase.android.signin.state;

import com.coinbase.api.internal.models.idology.Data;
import rx.functions.Action1;

final /* synthetic */ class AbstractStateIdologyFormPresenter$$Lambda$4 implements Action1 {
    private final AbstractStateIdologyFormPresenter arg$1;

    private AbstractStateIdologyFormPresenter$$Lambda$4(AbstractStateIdologyFormPresenter abstractStateIdologyFormPresenter) {
        this.arg$1 = abstractStateIdologyFormPresenter;
    }

    public static Action1 lambdaFactory$(AbstractStateIdologyFormPresenter abstractStateIdologyFormPresenter) {
        return new AbstractStateIdologyFormPresenter$$Lambda$4(abstractStateIdologyFormPresenter);
    }

    public void call(Object obj) {
        this.arg$1.processVerificationResult((Data) obj);
    }
}
