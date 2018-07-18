package com.coinbase.android.paymentmethods;

import com.coinbase.android.event.ClassConsumableEvent;
import rx.functions.Func1;

final /* synthetic */ class AddBankPickerPresenter$$Lambda$1 implements Func1 {
    private static final AddBankPickerPresenter$$Lambda$1 instance = new AddBankPickerPresenter$$Lambda$1();

    private AddBankPickerPresenter$$Lambda$1() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return AddBankPickerPresenter.lambda$onShow$0((ClassConsumableEvent) obj);
    }
}
