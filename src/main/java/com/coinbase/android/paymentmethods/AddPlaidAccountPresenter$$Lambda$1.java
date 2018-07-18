package com.coinbase.android.paymentmethods;

import com.coinbase.api.internal.models.institutions.Data;
import java.util.Comparator;

final /* synthetic */ class AddPlaidAccountPresenter$$Lambda$1 implements Comparator {
    private final AddPlaidAccountPresenter arg$1;

    private AddPlaidAccountPresenter$$Lambda$1(AddPlaidAccountPresenter addPlaidAccountPresenter) {
        this.arg$1 = addPlaidAccountPresenter;
    }

    public static Comparator lambdaFactory$(AddPlaidAccountPresenter addPlaidAccountPresenter) {
        return new AddPlaidAccountPresenter$$Lambda$1(addPlaidAccountPresenter);
    }

    public int compare(Object obj, Object obj2) {
        return AddPlaidAccountPresenter.lambda$onSwitchAlphabeticalOrderClicked$0(this.arg$1, (Data) obj, (Data) obj2);
    }
}
