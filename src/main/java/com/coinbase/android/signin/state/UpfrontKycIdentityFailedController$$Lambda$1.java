package com.coinbase.android.signin.state;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class UpfrontKycIdentityFailedController$$Lambda$1 implements OnClickListener {
    private final UpfrontKycIdentityFailedController arg$1;

    private UpfrontKycIdentityFailedController$$Lambda$1(UpfrontKycIdentityFailedController upfrontKycIdentityFailedController) {
        this.arg$1 = upfrontKycIdentityFailedController;
    }

    public static OnClickListener lambdaFactory$(UpfrontKycIdentityFailedController upfrontKycIdentityFailedController) {
        return new UpfrontKycIdentityFailedController$$Lambda$1(upfrontKycIdentityFailedController);
    }

    public void onClick(View view) {
        this.arg$1.mPresenter.onTryAgainClicked();
    }
}
