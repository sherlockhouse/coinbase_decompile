package com.coinbase.android.utils;

import android.app.Activity;
import android.view.View;

final /* synthetic */ class Utils$$Lambda$3 implements Runnable {
    private final Activity arg$1;
    private final View arg$2;

    private Utils$$Lambda$3(Activity activity, View view) {
        this.arg$1 = activity;
        this.arg$2 = view;
    }

    public static Runnable lambdaFactory$(Activity activity, View view) {
        return new Utils$$Lambda$3(activity, view);
    }

    public void run() {
        Utils.lambda$postShowKeyboardFrom$2(this.arg$1, this.arg$2);
    }
}
