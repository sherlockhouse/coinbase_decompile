package com.coinbase.android.idology;

import java.util.List;
import rx.functions.Action1;

final /* synthetic */ class IdologyAddressFormPresenter$$Lambda$3 implements Action1 {
    private final IdologyAddressFormPresenter arg$1;

    private IdologyAddressFormPresenter$$Lambda$3(IdologyAddressFormPresenter idologyAddressFormPresenter) {
        this.arg$1 = idologyAddressFormPresenter;
    }

    public static Action1 lambdaFactory$(IdologyAddressFormPresenter idologyAddressFormPresenter) {
        return new IdologyAddressFormPresenter$$Lambda$3(idologyAddressFormPresenter);
    }

    public void call(Object obj) {
        this.arg$1.handleErrors((List) obj);
    }
}
