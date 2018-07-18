package com.coinbase.android.ui;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ActionBarController$$Lambda$1 implements OnClickListener {
    private final ActionBarController arg$1;

    private ActionBarController$$Lambda$1(ActionBarController actionBarController) {
        this.arg$1 = actionBarController;
    }

    public static OnClickListener lambdaFactory$(ActionBarController actionBarController) {
        return new ActionBarController$$Lambda$1(actionBarController);
    }

    public void onClick(View view) {
        ActionBarController.lambda$updateActionBar$0(this.arg$1, view);
    }
}
