package com.coinbase.android.deposits.fiat;

import com.coinbase.android.event.ClassConsumableEvent;
import rx.functions.Func1;

final /* synthetic */ class DepositFiatPresenter$$Lambda$20 implements Func1 {
    private static final DepositFiatPresenter$$Lambda$20 instance = new DepositFiatPresenter$$Lambda$20();

    private DepositFiatPresenter$$Lambda$20() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return DepositFiatPresenter.lambda$hookUpGoToSettingsDialog$19((ClassConsumableEvent) obj);
    }
}
