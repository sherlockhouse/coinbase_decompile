package com.coinbase.android.paymentmethods;

import com.coinbase.api.internal.models.institutions.Data;
import java.text.Collator;
import java.util.Comparator;

final /* synthetic */ class AddPlaidAccountPresenter$$Lambda$6 implements Comparator {
    private static final AddPlaidAccountPresenter$$Lambda$6 instance = new AddPlaidAccountPresenter$$Lambda$6();

    private AddPlaidAccountPresenter$$Lambda$6() {
    }

    public static Comparator lambdaFactory$() {
        return instance;
    }

    public int compare(Object obj, Object obj2) {
        return Collator.getInstance().compare(((Data) obj).getName(), ((Data) obj2).getName());
    }
}
