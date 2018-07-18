package com.coinbase.android.ui;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ActionBarController$$Lambda$5 implements OnClickListener {
    private static final ActionBarController$$Lambda$5 instance = new ActionBarController$$Lambda$5();

    private ActionBarController$$Lambda$5() {
    }

    public static OnClickListener lambdaFactory$() {
        return instance;
    }

    public void onClick(View view) {
        ActionBarController.lambda$clearToolbarNavigationOnClickListener$4(view);
    }
}
