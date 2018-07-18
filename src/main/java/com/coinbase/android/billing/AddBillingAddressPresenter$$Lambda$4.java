package com.coinbase.android.billing;

import rx.functions.Action1;

final /* synthetic */ class AddBillingAddressPresenter$$Lambda$4 implements Action1 {
    private final AddBillingAddressPresenter arg$1;

    private AddBillingAddressPresenter$$Lambda$4(AddBillingAddressPresenter addBillingAddressPresenter) {
        this.arg$1 = addBillingAddressPresenter;
    }

    public static Action1 lambdaFactory$(AddBillingAddressPresenter addBillingAddressPresenter) {
        return new AddBillingAddressPresenter$$Lambda$4(addBillingAddressPresenter);
    }

    public void call(Object obj) {
        AddBillingAddressPresenter.lambda$createBillingAddress$3(this.arg$1, (Throwable) obj);
    }
}
