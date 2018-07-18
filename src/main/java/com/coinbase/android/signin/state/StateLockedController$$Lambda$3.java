package com.coinbase.android.signin.state;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final /* synthetic */ class StateLockedController$$Lambda$3 implements OnClickListener {
    private final StateLockedController arg$1;

    private StateLockedController$$Lambda$3(StateLockedController stateLockedController) {
        this.arg$1 = stateLockedController;
    }

    public static OnClickListener lambdaFactory$(StateLockedController stateLockedController) {
        return new StateLockedController$$Lambda$3(stateLockedController);
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.arg$1.mPresenter.onConfirmCancelButtonClicked();
    }
}
