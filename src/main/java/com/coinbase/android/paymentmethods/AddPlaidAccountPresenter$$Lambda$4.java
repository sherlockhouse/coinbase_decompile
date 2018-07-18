package com.coinbase.android.paymentmethods;

import android.util.Pair;
import rx.functions.Action1;

final /* synthetic */ class AddPlaidAccountPresenter$$Lambda$4 implements Action1 {
    private final AddPlaidAccountPresenter arg$1;

    private AddPlaidAccountPresenter$$Lambda$4(AddPlaidAccountPresenter addPlaidAccountPresenter) {
        this.arg$1 = addPlaidAccountPresenter;
    }

    public static Action1 lambdaFactory$(AddPlaidAccountPresenter addPlaidAccountPresenter) {
        return new AddPlaidAccountPresenter$$Lambda$4(addPlaidAccountPresenter);
    }

    public void call(Object obj) {
        AddPlaidAccountPresenter.lambda$getAchInstitutions$4(this.arg$1, (Pair) obj);
    }
}
