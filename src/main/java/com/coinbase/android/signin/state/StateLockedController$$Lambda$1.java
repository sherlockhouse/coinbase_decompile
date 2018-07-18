package com.coinbase.android.signin.state;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class StateLockedController$$Lambda$1 implements OnClickListener {
    private final StateLockedController arg$1;

    private StateLockedController$$Lambda$1(StateLockedController stateLockedController) {
        this.arg$1 = stateLockedController;
    }

    public static OnClickListener lambdaFactory$(StateLockedController stateLockedController) {
        return new StateLockedController$$Lambda$1(stateLockedController);
    }

    public void onClick(View view) {
        this.arg$1.mPresenter.onBackButtonClicked();
    }
}
