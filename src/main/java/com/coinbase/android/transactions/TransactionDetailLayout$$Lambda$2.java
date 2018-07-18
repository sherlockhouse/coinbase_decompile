package com.coinbase.android.transactions;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class TransactionDetailLayout$$Lambda$2 implements OnClickListener {
    private final TransactionDetailLayout arg$1;
    private final ActionType arg$2;

    private TransactionDetailLayout$$Lambda$2(TransactionDetailLayout transactionDetailLayout, ActionType actionType) {
        this.arg$1 = transactionDetailLayout;
        this.arg$2 = actionType;
    }

    public static OnClickListener lambdaFactory$(TransactionDetailLayout transactionDetailLayout, ActionType actionType) {
        return new TransactionDetailLayout$$Lambda$2(transactionDetailLayout, actionType);
    }

    public void onClick(View view) {
        this.arg$1.mPresenter.onButtonClicked(this.arg$2);
    }
}
