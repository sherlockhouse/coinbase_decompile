package com.coinbase.android.signin.state;

import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

final /* synthetic */ class StateIdologySourceOfFundsFormController$$Lambda$2 implements OnEditorActionListener {
    private final StateIdologySourceOfFundsFormController arg$1;

    private StateIdologySourceOfFundsFormController$$Lambda$2(StateIdologySourceOfFundsFormController stateIdologySourceOfFundsFormController) {
        this.arg$1 = stateIdologySourceOfFundsFormController;
    }

    public static OnEditorActionListener lambdaFactory$(StateIdologySourceOfFundsFormController stateIdologySourceOfFundsFormController) {
        return new StateIdologySourceOfFundsFormController$$Lambda$2(stateIdologySourceOfFundsFormController);
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        return StateIdologySourceOfFundsFormController.lambda$onCreateView$1(this.arg$1, textView, i, keyEvent);
    }
}
