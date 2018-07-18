package com.coinbase.android.ui;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class BackgroundDimmer$$Lambda$8 implements OnClickListener {
    private final BackgroundDimmer arg$1;
    private final Runnable arg$2;

    private BackgroundDimmer$$Lambda$8(BackgroundDimmer backgroundDimmer, Runnable runnable) {
        this.arg$1 = backgroundDimmer;
        this.arg$2 = runnable;
    }

    public static OnClickListener lambdaFactory$(BackgroundDimmer backgroundDimmer, Runnable runnable) {
        return new BackgroundDimmer$$Lambda$8(backgroundDimmer, runnable);
    }

    public void onClick(View view) {
        this.arg$1.undim(this.arg$2);
    }
}
