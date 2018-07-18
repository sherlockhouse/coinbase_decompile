package com.coinbase.android.paymentmethods;

import com.coinbase.android.ui.SnackBarWrapper;
import rx.functions.Action1;

final /* synthetic */ class AddPlaidAccountPresenter$$Lambda$5 implements Action1 {
    private final SnackBarWrapper arg$1;

    private AddPlaidAccountPresenter$$Lambda$5(SnackBarWrapper snackBarWrapper) {
        this.arg$1 = snackBarWrapper;
    }

    public static Action1 lambdaFactory$(SnackBarWrapper snackBarWrapper) {
        return new AddPlaidAccountPresenter$$Lambda$5(snackBarWrapper);
    }

    public void call(Object obj) {
        this.arg$1.showFailure((Throwable) obj);
    }
}
