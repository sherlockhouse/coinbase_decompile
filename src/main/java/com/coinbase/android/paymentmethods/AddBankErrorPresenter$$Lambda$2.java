package com.coinbase.android.paymentmethods;

import rx.functions.Action1;

final /* synthetic */ class AddBankErrorPresenter$$Lambda$2 implements Action1 {
    private final AddBankErrorPresenter arg$1;

    private AddBankErrorPresenter$$Lambda$2(AddBankErrorPresenter addBankErrorPresenter) {
        this.arg$1 = addBankErrorPresenter;
    }

    public static Action1 lambdaFactory$(AddBankErrorPresenter addBankErrorPresenter) {
        return new AddBankErrorPresenter$$Lambda$2(addBankErrorPresenter);
    }

    public void call(Object obj) {
        this.arg$1.gotoSettings();
    }
}
