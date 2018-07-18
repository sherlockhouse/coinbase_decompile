package com.coinbase.android.transactions;

import android.view.View;
import android.view.View.OnClickListener;
import com.coinbase.v2.models.transactions.Data;

final /* synthetic */ class TransactionListAdapter$$Lambda$1 implements OnClickListener {
    private final TransactionListAdapter arg$1;
    private final Data arg$2;

    private TransactionListAdapter$$Lambda$1(TransactionListAdapter transactionListAdapter, Data data) {
        this.arg$1 = transactionListAdapter;
        this.arg$2 = data;
    }

    public static OnClickListener lambdaFactory$(TransactionListAdapter transactionListAdapter, Data data) {
        return new TransactionListAdapter$$Lambda$1(transactionListAdapter, data);
    }

    public void onClick(View view) {
        this.arg$1.mPresenter.onTransactionItemClicked(this.arg$2);
    }
}
