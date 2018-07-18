package com.coinbase.android.paymentmethods;

import rx.functions.Action1;

final /* synthetic */ class AddBankPickerPresenter$$Lambda$4 implements Action1 {
    private final AddBankPickerPresenter arg$1;

    private AddBankPickerPresenter$$Lambda$4(AddBankPickerPresenter addBankPickerPresenter) {
        this.arg$1 = addBankPickerPresenter;
    }

    public static Action1 lambdaFactory$(AddBankPickerPresenter addBankPickerPresenter) {
        return new AddBankPickerPresenter$$Lambda$4(addBankPickerPresenter);
    }

    public void call(Object obj) {
        AddBankPickerPresenter.lambda$onShow$3(this.arg$1, (Void) obj);
    }
}
