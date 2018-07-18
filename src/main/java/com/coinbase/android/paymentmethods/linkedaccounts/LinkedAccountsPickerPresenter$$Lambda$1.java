package com.coinbase.android.paymentmethods.linkedaccounts;

import com.coinbase.v2.models.paymentMethods.Data;
import java.util.Comparator;

final /* synthetic */ class LinkedAccountsPickerPresenter$$Lambda$1 implements Comparator {
    private static final LinkedAccountsPickerPresenter$$Lambda$1 instance = new LinkedAccountsPickerPresenter$$Lambda$1();

    private LinkedAccountsPickerPresenter$$Lambda$1() {
    }

    public static Comparator lambdaFactory$() {
        return instance;
    }

    public int compare(Object obj, Object obj2) {
        return LinkedAccountsPickerPresenter.lambda$filterPaymentMethods$0((Data) obj, (Data) obj2);
    }
}
