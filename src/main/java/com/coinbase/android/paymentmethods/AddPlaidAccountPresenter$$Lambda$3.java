package com.coinbase.android.paymentmethods;

import com.coinbase.api.internal.models.institutions.Data;
import java.text.Collator;
import java.util.Comparator;

final /* synthetic */ class AddPlaidAccountPresenter$$Lambda$3 implements Comparator {
    private static final AddPlaidAccountPresenter$$Lambda$3 instance = new AddPlaidAccountPresenter$$Lambda$3();

    private AddPlaidAccountPresenter$$Lambda$3() {
    }

    public static Comparator lambdaFactory$() {
        return instance;
    }

    public int compare(Object obj, Object obj2) {
        return Collator.getInstance().compare(((Data) obj).getName(), ((Data) obj2).getName());
    }
}
