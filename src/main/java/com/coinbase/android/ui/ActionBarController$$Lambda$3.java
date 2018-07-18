package com.coinbase.android.ui;

import rx.functions.Action1;

final /* synthetic */ class ActionBarController$$Lambda$3 implements Action1 {
    private final ActionBarController arg$1;

    private ActionBarController$$Lambda$3(ActionBarController actionBarController) {
        this.arg$1 = actionBarController;
    }

    public static Action1 lambdaFactory$(ActionBarController actionBarController) {
        return new ActionBarController$$Lambda$3(actionBarController);
    }

    public void call(Object obj) {
        ActionBarController.lambda$enableBackSubscription$2(this.arg$1, (Void) obj);
    }
}
