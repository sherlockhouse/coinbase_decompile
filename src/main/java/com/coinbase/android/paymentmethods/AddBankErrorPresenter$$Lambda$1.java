package com.coinbase.android.paymentmethods;

import com.coinbase.android.event.ClassConsumableEvent;
import rx.functions.Func1;

final /* synthetic */ class AddBankErrorPresenter$$Lambda$1 implements Func1 {
    private static final AddBankErrorPresenter$$Lambda$1 instance = new AddBankErrorPresenter$$Lambda$1();

    private AddBankErrorPresenter$$Lambda$1() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return AddBankErrorPresenter.lambda$onShow$0((ClassConsumableEvent) obj);
    }
}
