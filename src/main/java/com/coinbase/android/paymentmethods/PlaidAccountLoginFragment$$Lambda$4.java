package com.coinbase.android.paymentmethods;

import android.app.ProgressDialog;
import rx.functions.Action1;

final /* synthetic */ class PlaidAccountLoginFragment$$Lambda$4 implements Action1 {
    private final PlaidAccountLoginFragment arg$1;
    private final ProgressDialog arg$2;

    private PlaidAccountLoginFragment$$Lambda$4(PlaidAccountLoginFragment plaidAccountLoginFragment, ProgressDialog progressDialog) {
        this.arg$1 = plaidAccountLoginFragment;
        this.arg$2 = progressDialog;
    }

    public static Action1 lambdaFactory$(PlaidAccountLoginFragment plaidAccountLoginFragment, ProgressDialog progressDialog) {
        return new PlaidAccountLoginFragment$$Lambda$4(plaidAccountLoginFragment, progressDialog);
    }

    public void call(Object obj) {
        PlaidAccountLoginFragment.lambda$createBankAccount$3(this.arg$1, this.arg$2, (Throwable) obj);
    }
}
