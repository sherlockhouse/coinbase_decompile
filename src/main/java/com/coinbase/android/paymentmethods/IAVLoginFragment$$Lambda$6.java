package com.coinbase.android.paymentmethods;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class IAVLoginFragment$$Lambda$6 implements OnClickListener {
    private final IAVLoginFragment arg$1;

    private IAVLoginFragment$$Lambda$6(IAVLoginFragment iAVLoginFragment) {
        this.arg$1 = iAVLoginFragment;
    }

    public static OnClickListener lambdaFactory$(IAVLoginFragment iAVLoginFragment) {
        return new IAVLoginFragment$$Lambda$6(iAVLoginFragment);
    }

    public void onClick(View view) {
        this.arg$1.mPresenter.onCdvVerificationAmountSubmit();
    }
}
