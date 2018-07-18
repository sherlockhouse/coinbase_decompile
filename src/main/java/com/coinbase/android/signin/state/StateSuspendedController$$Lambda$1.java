package com.coinbase.android.signin.state;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class StateSuspendedController$$Lambda$1 implements OnClickListener {
    private final StateSuspendedController arg$1;

    private StateSuspendedController$$Lambda$1(StateSuspendedController stateSuspendedController) {
        this.arg$1 = stateSuspendedController;
    }

    public static OnClickListener lambdaFactory$(StateSuspendedController stateSuspendedController) {
        return new StateSuspendedController$$Lambda$1(stateSuspendedController);
    }

    public void onClick(View view) {
        this.arg$1.mPresenter.onBackButtonClicked();
    }
}
