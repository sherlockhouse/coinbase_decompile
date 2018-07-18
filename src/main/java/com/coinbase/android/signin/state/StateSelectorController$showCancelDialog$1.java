package com.coinbase.android.signin.state;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

/* compiled from: StateSelectorController.kt */
final class StateSelectorController$showCancelDialog$1 implements OnClickListener {
    final /* synthetic */ StateSelectorController this$0;

    StateSelectorController$showCancelDialog$1(StateSelectorController stateSelectorController) {
        this.this$0 = stateSelectorController;
    }

    public final void onClick(DialogInterface $noName_0, int $noName_1) {
        this.this$0.getMPresenter().onConfirmCancelButtonClicked$coinbase_android_productionRelease();
    }
}
