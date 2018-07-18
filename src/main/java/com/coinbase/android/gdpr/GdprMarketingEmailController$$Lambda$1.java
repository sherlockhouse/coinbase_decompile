package com.coinbase.android.gdpr;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class GdprMarketingEmailController$$Lambda$1 implements OnClickListener {
    private final GdprMarketingEmailController arg$1;

    private GdprMarketingEmailController$$Lambda$1(GdprMarketingEmailController gdprMarketingEmailController) {
        this.arg$1 = gdprMarketingEmailController;
    }

    public static OnClickListener lambdaFactory$(GdprMarketingEmailController gdprMarketingEmailController) {
        return new GdprMarketingEmailController$$Lambda$1(gdprMarketingEmailController);
    }

    public void onClick(View view) {
        this.arg$1.mPresenter.onYesClicked();
    }
}
