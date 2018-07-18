package com.coinbase.android.billing;

import java.util.HashMap;
import rx.functions.Func1;

final /* synthetic */ class AddBillingAddressPresenter$$Lambda$1 implements Func1 {
    private static final AddBillingAddressPresenter$$Lambda$1 instance = new AddBillingAddressPresenter$$Lambda$1();

    private AddBillingAddressPresenter$$Lambda$1() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return AddBillingAddressPresenter.lambda$onResume$0((HashMap) obj);
    }
}
