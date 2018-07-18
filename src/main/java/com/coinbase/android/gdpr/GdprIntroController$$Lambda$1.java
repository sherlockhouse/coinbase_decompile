package com.coinbase.android.gdpr;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class GdprIntroController$$Lambda$1 implements OnClickListener {
    private final GdprIntroController arg$1;

    private GdprIntroController$$Lambda$1(GdprIntroController gdprIntroController) {
        this.arg$1 = gdprIntroController;
    }

    public static OnClickListener lambdaFactory$(GdprIntroController gdprIntroController) {
        return new GdprIntroController$$Lambda$1(gdprIntroController);
    }

    public void onClick(View view) {
        this.arg$1.mPresenter.onNextClicked(this.arg$1.getArgs());
    }
}
