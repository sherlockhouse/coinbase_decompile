package com.coinbase.android.paymentmethods;

import android.view.View;
import android.view.View.OnClickListener;
import com.coinbase.v2.models.paymentMethods.Data;

final /* synthetic */ class ConnectedAccountsListAdapter$$Lambda$1 implements OnClickListener {
    private final ConnectedAccountsListAdapter arg$1;
    private final Data arg$2;

    private ConnectedAccountsListAdapter$$Lambda$1(ConnectedAccountsListAdapter connectedAccountsListAdapter, Data data) {
        this.arg$1 = connectedAccountsListAdapter;
        this.arg$2 = data;
    }

    public static OnClickListener lambdaFactory$(ConnectedAccountsListAdapter connectedAccountsListAdapter, Data data) {
        return new ConnectedAccountsListAdapter$$Lambda$1(connectedAccountsListAdapter, data);
    }

    public void onClick(View view) {
        this.arg$1.mPresenter.onItemClicked(this.arg$2);
    }
}
