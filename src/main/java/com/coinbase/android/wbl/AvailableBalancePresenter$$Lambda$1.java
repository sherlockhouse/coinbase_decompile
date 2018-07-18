package com.coinbase.android.wbl;

import android.os.Bundle;
import rx.functions.Action1;

final /* synthetic */ class AvailableBalancePresenter$$Lambda$1 implements Action1 {
    private final AvailableBalancePresenter arg$1;
    private final Bundle arg$2;

    private AvailableBalancePresenter$$Lambda$1(AvailableBalancePresenter availableBalancePresenter, Bundle bundle) {
        this.arg$1 = availableBalancePresenter;
        this.arg$2 = bundle;
    }

    public static Action1 lambdaFactory$(AvailableBalancePresenter availableBalancePresenter, Bundle bundle) {
        return new AvailableBalancePresenter$$Lambda$1(availableBalancePresenter, bundle);
    }

    public void call(Object obj) {
        AvailableBalancePresenter.lambda$onShow$0(this.arg$1, this.arg$2, (AvailableBalance) obj);
    }
}
