package com.coinbase.android.settings.idology;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class IdologyFailureController$$Lambda$1 implements OnClickListener {
    private final IdologyFailureController arg$1;

    private IdologyFailureController$$Lambda$1(IdologyFailureController idologyFailureController) {
        this.arg$1 = idologyFailureController;
    }

    public static OnClickListener lambdaFactory$(IdologyFailureController idologyFailureController) {
        return new IdologyFailureController$$Lambda$1(idologyFailureController);
    }

    public void onClick(View view) {
        this.arg$1.mPresenter.retryIdology();
    }
}
