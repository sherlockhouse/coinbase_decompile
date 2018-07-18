package com.coinbase.android.signin.state;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class StateIdologyAddressFormController$$Lambda$2 implements OnClickListener {
    private final StateIdologyAddressFormController arg$1;

    private StateIdologyAddressFormController$$Lambda$2(StateIdologyAddressFormController stateIdologyAddressFormController) {
        this.arg$1 = stateIdologyAddressFormController;
    }

    public static OnClickListener lambdaFactory$(StateIdologyAddressFormController stateIdologyAddressFormController) {
        return new StateIdologyAddressFormController$$Lambda$2(stateIdologyAddressFormController);
    }

    public void onClick(View view) {
        this.arg$1.mPresenter.onContinueButtonClicked();
    }
}
