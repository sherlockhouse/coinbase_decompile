package com.coinbase.android.wbl;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ExistingUserDialogController$$Lambda$1 implements OnClickListener {
    private final ExistingUserDialogController arg$1;

    private ExistingUserDialogController$$Lambda$1(ExistingUserDialogController existingUserDialogController) {
        this.arg$1 = existingUserDialogController;
    }

    public static OnClickListener lambdaFactory$(ExistingUserDialogController existingUserDialogController) {
        return new ExistingUserDialogController$$Lambda$1(existingUserDialogController);
    }

    public void onClick(View view) {
        this.arg$1.mPresenter.onClickAccountLevels();
    }
}
