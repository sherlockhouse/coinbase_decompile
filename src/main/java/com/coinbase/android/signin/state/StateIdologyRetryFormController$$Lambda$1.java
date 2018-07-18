package com.coinbase.android.signin.state;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class StateIdologyRetryFormController$$Lambda$1 implements OnClickListener {
    private final StateIdologyRetryFormController arg$1;

    private StateIdologyRetryFormController$$Lambda$1(StateIdologyRetryFormController stateIdologyRetryFormController) {
        this.arg$1 = stateIdologyRetryFormController;
    }

    public static OnClickListener lambdaFactory$(StateIdologyRetryFormController stateIdologyRetryFormController) {
        return new StateIdologyRetryFormController$$Lambda$1(stateIdologyRetryFormController);
    }

    public void onClick(View view) {
        this.arg$1.mPresenter.onContinueButtonClicked();
    }
}
