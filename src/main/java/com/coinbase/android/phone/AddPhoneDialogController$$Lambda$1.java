package com.coinbase.android.phone;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class AddPhoneDialogController$$Lambda$1 implements OnClickListener {
    private final AddPhoneDialogController arg$1;

    private AddPhoneDialogController$$Lambda$1(AddPhoneDialogController addPhoneDialogController) {
        this.arg$1 = addPhoneDialogController;
    }

    public static OnClickListener lambdaFactory$(AddPhoneDialogController addPhoneDialogController) {
        return new AddPhoneDialogController$$Lambda$1(addPhoneDialogController);
    }

    public void onClick(View view) {
        this.arg$1.dismiss();
    }
}
