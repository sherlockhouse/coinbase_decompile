package com.coinbase.android.signin.state;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class StateIdologyFailureController$$Lambda$1 implements OnClickListener {
    private final StateIdologyFailureController arg$1;

    private StateIdologyFailureController$$Lambda$1(StateIdologyFailureController stateIdologyFailureController) {
        this.arg$1 = stateIdologyFailureController;
    }

    public static OnClickListener lambdaFactory$(StateIdologyFailureController stateIdologyFailureController) {
        return new StateIdologyFailureController$$Lambda$1(stateIdologyFailureController);
    }

    public void onClick(View view) {
        this.arg$1.mPresenter.retryIdology();
    }
}
