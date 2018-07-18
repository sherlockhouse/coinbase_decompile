package com.coinbase.android.quickstart;

import android.app.Activity;
import android.app.ProgressDialog;
import android.util.Pair;
import rx.functions.Action1;

final /* synthetic */ class QuickstartManager$$Lambda$2 implements Action1 {
    private final QuickstartManager arg$1;
    private final ProgressDialog arg$2;
    private final Activity arg$3;

    private QuickstartManager$$Lambda$2(QuickstartManager quickstartManager, ProgressDialog progressDialog, Activity activity) {
        this.arg$1 = quickstartManager;
        this.arg$2 = progressDialog;
        this.arg$3 = activity;
    }

    public static Action1 lambdaFactory$(QuickstartManager quickstartManager, ProgressDialog progressDialog, Activity activity) {
        return new QuickstartManager$$Lambda$2(quickstartManager, progressDialog, activity);
    }

    public void call(Object obj) {
        QuickstartManager.lambda$showCdvVerification$1(this.arg$1, this.arg$2, this.arg$3, (Pair) obj);
    }
}
