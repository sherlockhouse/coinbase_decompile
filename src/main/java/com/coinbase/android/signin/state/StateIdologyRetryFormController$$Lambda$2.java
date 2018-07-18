package com.coinbase.android.signin.state;

import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

final /* synthetic */ class StateIdologyRetryFormController$$Lambda$2 implements OnEditorActionListener {
    private final StateIdologyRetryFormController arg$1;

    private StateIdologyRetryFormController$$Lambda$2(StateIdologyRetryFormController stateIdologyRetryFormController) {
        this.arg$1 = stateIdologyRetryFormController;
    }

    public static OnEditorActionListener lambdaFactory$(StateIdologyRetryFormController stateIdologyRetryFormController) {
        return new StateIdologyRetryFormController$$Lambda$2(stateIdologyRetryFormController);
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        return StateIdologyRetryFormController.lambda$onCreateView$1(this.arg$1, textView, i, keyEvent);
    }
}
