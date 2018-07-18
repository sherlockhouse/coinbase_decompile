package com.coinbase.android.signin.state;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class StateIdologyNameFormController$$Lambda$1 implements OnClickListener {
    private final StateIdologyNameFormController arg$1;

    private StateIdologyNameFormController$$Lambda$1(StateIdologyNameFormController stateIdologyNameFormController) {
        this.arg$1 = stateIdologyNameFormController;
    }

    public static OnClickListener lambdaFactory$(StateIdologyNameFormController stateIdologyNameFormController) {
        return new StateIdologyNameFormController$$Lambda$1(stateIdologyNameFormController);
    }

    public void onClick(View view) {
        this.arg$1.mPresenter.onContinueButtonClicked();
    }
}
