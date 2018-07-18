package com.coinbase.android.settings.idology;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class IdologySuccessController$$Lambda$1 implements OnClickListener {
    private final IdologySuccessController arg$1;

    private IdologySuccessController$$Lambda$1(IdologySuccessController idologySuccessController) {
        this.arg$1 = idologySuccessController;
    }

    public static OnClickListener lambdaFactory$(IdologySuccessController idologySuccessController) {
        return new IdologySuccessController$$Lambda$1(idologySuccessController);
    }

    public void onClick(View view) {
        this.arg$1.mPresenter.gotoSettings();
    }
}
