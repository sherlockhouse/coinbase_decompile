package com.coinbase.android.transactions;

import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;

final /* synthetic */ class TransactionListLayout$$Lambda$1 implements OnRefreshListener {
    private final TransactionListLayout arg$1;

    private TransactionListLayout$$Lambda$1(TransactionListLayout transactionListLayout) {
        this.arg$1 = transactionListLayout;
    }

    public static OnRefreshListener lambdaFactory$(TransactionListLayout transactionListLayout) {
        return new TransactionListLayout$$Lambda$1(transactionListLayout);
    }

    public void onRefresh() {
        this.arg$1.mPresenter.onRefreshClicked();
    }
}
