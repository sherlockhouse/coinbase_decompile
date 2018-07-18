package com.coinbase.android.gdpr;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class GdprPrivacyPolicyController$$Lambda$1 implements OnClickListener {
    private final GdprPrivacyPolicyController arg$1;

    private GdprPrivacyPolicyController$$Lambda$1(GdprPrivacyPolicyController gdprPrivacyPolicyController) {
        this.arg$1 = gdprPrivacyPolicyController;
    }

    public static OnClickListener lambdaFactory$(GdprPrivacyPolicyController gdprPrivacyPolicyController) {
        return new GdprPrivacyPolicyController$$Lambda$1(gdprPrivacyPolicyController);
    }

    public void onClick(View view) {
        this.arg$1.mPresenter.onAcknowledgeClicked();
    }
}
