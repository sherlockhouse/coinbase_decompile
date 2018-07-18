package com.coinbase.android.settings.gdpr;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class GdprRequestSentController$$Lambda$1 implements OnClickListener {
    private final GdprRequestSentController arg$1;

    private GdprRequestSentController$$Lambda$1(GdprRequestSentController gdprRequestSentController) {
        this.arg$1 = gdprRequestSentController;
    }

    public static OnClickListener lambdaFactory$(GdprRequestSentController gdprRequestSentController) {
        return new GdprRequestSentController$$Lambda$1(gdprRequestSentController);
    }

    public void onClick(View view) {
        this.arg$1.mPresenter.gotoSettings();
    }
}
