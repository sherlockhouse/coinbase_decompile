package com.coinbase.android.signin.state;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class StateIdologySourceOfFundsFormController$$Lambda$1 implements OnClickListener {
    private final StateIdologySourceOfFundsFormController arg$1;

    private StateIdologySourceOfFundsFormController$$Lambda$1(StateIdologySourceOfFundsFormController stateIdologySourceOfFundsFormController) {
        this.arg$1 = stateIdologySourceOfFundsFormController;
    }

    public static OnClickListener lambdaFactory$(StateIdologySourceOfFundsFormController stateIdologySourceOfFundsFormController) {
        return new StateIdologySourceOfFundsFormController$$Lambda$1(stateIdologySourceOfFundsFormController);
    }

    public void onClick(View view) {
        this.arg$1.mPresenter.onContinueButtonClicked();
    }
}
