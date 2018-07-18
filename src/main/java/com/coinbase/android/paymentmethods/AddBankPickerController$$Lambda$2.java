package com.coinbase.android.paymentmethods;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class AddBankPickerController$$Lambda$2 implements OnClickListener {
    private final AddBankPickerController arg$1;

    private AddBankPickerController$$Lambda$2(AddBankPickerController addBankPickerController) {
        this.arg$1 = addBankPickerController;
    }

    public static OnClickListener lambdaFactory$(AddBankPickerController addBankPickerController) {
        return new AddBankPickerController$$Lambda$2(addBankPickerController);
    }

    public void onClick(View view) {
        this.arg$1.mPresenter.gotoManualBankEntry();
    }
}
