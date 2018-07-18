package com.coinbase.android.settings.gdpr;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class GdprPrivacyRequestController$$Lambda$1 implements OnClickListener {
    private final GdprPrivacyRequestController arg$1;

    private GdprPrivacyRequestController$$Lambda$1(GdprPrivacyRequestController gdprPrivacyRequestController) {
        this.arg$1 = gdprPrivacyRequestController;
    }

    public static OnClickListener lambdaFactory$(GdprPrivacyRequestController gdprPrivacyRequestController) {
        return new GdprPrivacyRequestController$$Lambda$1(gdprPrivacyRequestController);
    }

    public void onClick(View view) {
        this.arg$1.mPresenter.onSendRequestClicked(this.arg$1.mBinding.etAddlMessage.getText().toString());
    }
}
