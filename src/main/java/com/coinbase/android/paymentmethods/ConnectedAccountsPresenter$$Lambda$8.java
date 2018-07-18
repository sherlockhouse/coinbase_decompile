package com.coinbase.android.paymentmethods;

import com.coinbase.android.paymentmethods.DeletePaymentMethodTask.DeletePaymentMethodListener;

final /* synthetic */ class ConnectedAccountsPresenter$$Lambda$8 implements DeletePaymentMethodListener {
    private final ConnectedAccountsPresenter arg$1;

    private ConnectedAccountsPresenter$$Lambda$8(ConnectedAccountsPresenter connectedAccountsPresenter) {
        this.arg$1 = connectedAccountsPresenter;
    }

    public static DeletePaymentMethodListener lambdaFactory$(ConnectedAccountsPresenter connectedAccountsPresenter) {
        return new ConnectedAccountsPresenter$$Lambda$8(connectedAccountsPresenter);
    }

    public void onFinally() {
        this.arg$1.mSnackBarWrapper.show(this.arg$1.mPaymentMethodDeletedResId);
    }
}
