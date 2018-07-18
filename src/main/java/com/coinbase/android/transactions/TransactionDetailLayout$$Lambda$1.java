package com.coinbase.android.transactions;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class TransactionDetailLayout$$Lambda$1 implements OnClickListener {
    private final TransactionDetailLayout arg$1;

    private TransactionDetailLayout$$Lambda$1(TransactionDetailLayout transactionDetailLayout) {
        this.arg$1 = transactionDetailLayout;
    }

    public static OnClickListener lambdaFactory$(TransactionDetailLayout transactionDetailLayout) {
        return new TransactionDetailLayout$$Lambda$1(transactionDetailLayout);
    }

    public void onClick(View view) {
        this.arg$1.mPresenter.onButtonClicked(ActionType.CLOSE);
    }
}
