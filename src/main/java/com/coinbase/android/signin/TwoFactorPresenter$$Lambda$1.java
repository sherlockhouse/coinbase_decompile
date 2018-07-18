package com.coinbase.android.signin;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class TwoFactorPresenter$$Lambda$1 implements OnClickListener {
    private final TwoFactorPresenter arg$1;

    private TwoFactorPresenter$$Lambda$1(TwoFactorPresenter twoFactorPresenter) {
        this.arg$1 = twoFactorPresenter;
    }

    public static OnClickListener lambdaFactory$(TwoFactorPresenter twoFactorPresenter) {
        return new TwoFactorPresenter$$Lambda$1(twoFactorPresenter);
    }

    public void onClick(View view) {
        this.arg$1.runSmsTask(this.arg$1.mUsername, this.arg$1.mPassword, true);
    }
}
