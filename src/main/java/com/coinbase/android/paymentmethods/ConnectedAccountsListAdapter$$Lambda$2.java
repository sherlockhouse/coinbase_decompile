package com.coinbase.android.paymentmethods;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ConnectedAccountsListAdapter$$Lambda$2 implements OnClickListener {
    private final ConnectedAccountsListAdapter arg$1;

    private ConnectedAccountsListAdapter$$Lambda$2(ConnectedAccountsListAdapter connectedAccountsListAdapter) {
        this.arg$1 = connectedAccountsListAdapter;
    }

    public static OnClickListener lambdaFactory$(ConnectedAccountsListAdapter connectedAccountsListAdapter) {
        return new ConnectedAccountsListAdapter$$Lambda$2(connectedAccountsListAdapter);
    }

    public void onClick(View view) {
        this.arg$1.mPresenter.onAddAccountClicked();
    }
}
