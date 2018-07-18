package com.coinbase.android.paymentmethods;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class AddBankErrorController$$Lambda$1 implements OnClickListener {
    private final AddBankErrorController arg$1;

    private AddBankErrorController$$Lambda$1(AddBankErrorController addBankErrorController) {
        this.arg$1 = addBankErrorController;
    }

    public static OnClickListener lambdaFactory$(AddBankErrorController addBankErrorController) {
        return new AddBankErrorController$$Lambda$1(addBankErrorController);
    }

    public void onClick(View view) {
        AddBankErrorController.lambda$onCreateView$0(this.arg$1, view);
    }
}
