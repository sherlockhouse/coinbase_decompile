package com.coinbase.android.transfers;

import android.view.View;
import android.view.View.OnFocusChangeListener;

final /* synthetic */ class TransferSendLayout$$Lambda$2 implements OnFocusChangeListener {
    private final TransferSendLayout arg$1;

    private TransferSendLayout$$Lambda$2(TransferSendLayout transferSendLayout) {
        this.arg$1 = transferSendLayout;
    }

    public static OnFocusChangeListener lambdaFactory$(TransferSendLayout transferSendLayout) {
        return new TransferSendLayout$$Lambda$2(transferSendLayout);
    }

    public void onFocusChange(View view, boolean z) {
        this.arg$1.mPresenter.onRecipientTapped();
    }
}
