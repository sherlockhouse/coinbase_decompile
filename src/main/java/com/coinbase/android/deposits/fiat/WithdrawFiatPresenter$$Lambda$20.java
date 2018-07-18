package com.coinbase.android.deposits.fiat;

import com.coinbase.android.event.ClassConsumableEvent;
import rx.functions.Func1;

final /* synthetic */ class WithdrawFiatPresenter$$Lambda$20 implements Func1 {
    private static final WithdrawFiatPresenter$$Lambda$20 instance = new WithdrawFiatPresenter$$Lambda$20();

    private WithdrawFiatPresenter$$Lambda$20() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return WithdrawFiatPresenter.lambda$hookUpGoToSettingsDialog$19((ClassConsumableEvent) obj);
    }
}
