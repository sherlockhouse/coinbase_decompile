package com.coinbase.android.confirmation;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ConfirmationDetailListAdapter$$Lambda$1 implements OnClickListener {
    private final ConfirmationDetailListAdapter arg$1;

    private ConfirmationDetailListAdapter$$Lambda$1(ConfirmationDetailListAdapter confirmationDetailListAdapter) {
        this.arg$1 = confirmationDetailListAdapter;
    }

    public static OnClickListener lambdaFactory$(ConfirmationDetailListAdapter confirmationDetailListAdapter) {
        return new ConfirmationDetailListAdapter$$Lambda$1(confirmationDetailListAdapter);
    }

    public void onClick(View view) {
        this.arg$1.mPresenter.onFeeHelpClicked();
    }
}
