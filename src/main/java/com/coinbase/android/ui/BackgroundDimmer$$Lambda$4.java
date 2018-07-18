package com.coinbase.android.ui;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class BackgroundDimmer$$Lambda$4 implements OnClickListener {
    private final BackgroundDimmer arg$1;

    private BackgroundDimmer$$Lambda$4(BackgroundDimmer backgroundDimmer) {
        this.arg$1 = backgroundDimmer;
    }

    public static OnClickListener lambdaFactory$(BackgroundDimmer backgroundDimmer) {
        return new BackgroundDimmer$$Lambda$4(backgroundDimmer);
    }

    public void onClick(View view) {
        this.arg$1.undim(null);
    }
}
