package com.coinbase.android.ui;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class DialogController$$Lambda$1 implements OnClickListener {
    private static final DialogController$$Lambda$1 instance = new DialogController$$Lambda$1();

    private DialogController$$Lambda$1() {
    }

    public static OnClickListener lambdaFactory$() {
        return instance;
    }

    public void onClick(View view) {
        DialogController.lambda$onCreateView$0(view);
    }
}
