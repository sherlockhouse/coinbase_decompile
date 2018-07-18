package com.coinbase.android.gdpr;

import android.view.ViewTreeObserver.OnScrollChangedListener;

final /* synthetic */ class GdprPrivacyPolicyController$$Lambda$2 implements OnScrollChangedListener {
    private final GdprPrivacyPolicyController arg$1;

    private GdprPrivacyPolicyController$$Lambda$2(GdprPrivacyPolicyController gdprPrivacyPolicyController) {
        this.arg$1 = gdprPrivacyPolicyController;
    }

    public static OnScrollChangedListener lambdaFactory$(GdprPrivacyPolicyController gdprPrivacyPolicyController) {
        return new GdprPrivacyPolicyController$$Lambda$2(gdprPrivacyPolicyController);
    }

    public void onScrollChanged() {
        this.arg$1.checkScrollPosition();
    }
}
