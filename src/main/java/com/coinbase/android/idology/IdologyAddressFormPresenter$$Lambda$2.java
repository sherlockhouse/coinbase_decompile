package com.coinbase.android.idology;

import java.util.List;
import rx.functions.Func1;

final /* synthetic */ class IdologyAddressFormPresenter$$Lambda$2 implements Func1 {
    private static final IdologyAddressFormPresenter$$Lambda$2 instance = new IdologyAddressFormPresenter$$Lambda$2();

    private IdologyAddressFormPresenter$$Lambda$2() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return IdologyAddressFormPresenter.lambda$onShow$1((List) obj);
    }
}
