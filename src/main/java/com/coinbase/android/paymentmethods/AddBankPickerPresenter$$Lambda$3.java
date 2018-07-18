package com.coinbase.android.paymentmethods;

import rx.functions.Action1;

final /* synthetic */ class AddBankPickerPresenter$$Lambda$3 implements Action1 {
    private final AddBankPickerPresenter arg$1;

    private AddBankPickerPresenter$$Lambda$3(AddBankPickerPresenter addBankPickerPresenter) {
        this.arg$1 = addBankPickerPresenter;
    }

    public static Action1 lambdaFactory$(AddBankPickerPresenter addBankPickerPresenter) {
        return new AddBankPickerPresenter$$Lambda$3(addBankPickerPresenter);
    }

    public void call(Object obj) {
        this.arg$1.mLogger.error("Error in closeModal", (Throwable) obj);
    }
}
