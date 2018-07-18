package com.coinbase.android.ui;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ActionBarController$$Lambda$4 implements OnClickListener {
    private static final ActionBarController$$Lambda$4 instance = new ActionBarController$$Lambda$4();

    private ActionBarController$$Lambda$4() {
    }

    public static OnClickListener lambdaFactory$() {
        return instance;
    }

    public void onClick(View view) {
        ActionBarController.lambda$pushRootModalController$3(view);
    }
}
