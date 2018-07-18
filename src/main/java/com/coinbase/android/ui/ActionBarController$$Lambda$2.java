package com.coinbase.android.ui;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ActionBarController$$Lambda$2 implements OnClickListener {
    private final ActionBarController arg$1;

    private ActionBarController$$Lambda$2(ActionBarController actionBarController) {
        this.arg$1 = actionBarController;
    }

    public static OnClickListener lambdaFactory$(ActionBarController actionBarController) {
        return new ActionBarController$$Lambda$2(actionBarController);
    }

    public void onClick(View view) {
        this.arg$1.mBackNavigationConnector.get().onNext(null);
    }
}
