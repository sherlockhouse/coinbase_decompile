package com.coinbase.android.idology;

import java.util.List;
import rx.functions.Func1;

final /* synthetic */ class IdologySourceOfFundsFormPresenter$$Lambda$7 implements Func1 {
    private static final IdologySourceOfFundsFormPresenter$$Lambda$7 instance = new IdologySourceOfFundsFormPresenter$$Lambda$7();

    private IdologySourceOfFundsFormPresenter$$Lambda$7() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return IdologySourceOfFundsFormPresenter.lambda$fetchIdologyOptions$6((List) obj);
    }
}
