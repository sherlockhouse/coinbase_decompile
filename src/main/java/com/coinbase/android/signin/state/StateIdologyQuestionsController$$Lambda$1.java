package com.coinbase.android.signin.state;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class StateIdologyQuestionsController$$Lambda$1 implements OnClickListener {
    private final StateIdologyQuestionsController arg$1;

    private StateIdologyQuestionsController$$Lambda$1(StateIdologyQuestionsController stateIdologyQuestionsController) {
        this.arg$1 = stateIdologyQuestionsController;
    }

    public static OnClickListener lambdaFactory$(StateIdologyQuestionsController stateIdologyQuestionsController) {
        return new StateIdologyQuestionsController$$Lambda$1(stateIdologyQuestionsController);
    }

    public void onClick(View view) {
        this.arg$1.mPresenter.onContinueClicked(this.arg$1.getArgs());
    }
}
