package com.coinbase.android.transfers;

import android.view.View;
import android.view.View.OnFocusChangeListener;

final /* synthetic */ class TransferSendLayout$$Lambda$1 implements OnFocusChangeListener {
    private final TransferSendLayout arg$1;

    private TransferSendLayout$$Lambda$1(TransferSendLayout transferSendLayout) {
        this.arg$1 = transferSendLayout;
    }

    public static OnFocusChangeListener lambdaFactory$(TransferSendLayout transferSendLayout) {
        return new TransferSendLayout$$Lambda$1(transferSendLayout);
    }

    public void onFocusChange(View view, boolean z) {
        this.arg$1.mPresenter.onNotesTapped();
    }
}
