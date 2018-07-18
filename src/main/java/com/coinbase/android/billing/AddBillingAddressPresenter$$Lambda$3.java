package com.coinbase.android.billing;

import android.util.Pair;
import rx.functions.Action1;

final /* synthetic */ class AddBillingAddressPresenter$$Lambda$3 implements Action1 {
    private final AddBillingAddressPresenter arg$1;

    private AddBillingAddressPresenter$$Lambda$3(AddBillingAddressPresenter addBillingAddressPresenter) {
        this.arg$1 = addBillingAddressPresenter;
    }

    public static Action1 lambdaFactory$(AddBillingAddressPresenter addBillingAddressPresenter) {
        return new AddBillingAddressPresenter$$Lambda$3(addBillingAddressPresenter);
    }

    public void call(Object obj) {
        AddBillingAddressPresenter.lambda$createBillingAddress$2(this.arg$1, (Pair) obj);
    }
}
