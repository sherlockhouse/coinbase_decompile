package com.coinbase.android.signin.state;

import rx.functions.Func1;

final /* synthetic */ class StateIdologyAddressFormController$$Lambda$1 implements Func1 {
    private final StateIdologyAddressFormController arg$1;

    private StateIdologyAddressFormController$$Lambda$1(StateIdologyAddressFormController stateIdologyAddressFormController) {
        this.arg$1 = stateIdologyAddressFormController;
    }

    public static Func1 lambdaFactory$(StateIdologyAddressFormController stateIdologyAddressFormController) {
        return new StateIdologyAddressFormController$$Lambda$1(stateIdologyAddressFormController);
    }

    public Object call(Object obj) {
        return this.arg$1.mBinding.btnIdologyContinue.setText((String) obj);
    }
}
