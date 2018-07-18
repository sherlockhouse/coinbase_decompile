package com.coinbase.android.deposits.fiat;

import com.coinbase.android.wbl.AvailableBalance;
import rx.functions.Action1;

final /* synthetic */ class WithdrawFiatPresenter$$Lambda$23 implements Action1 {
    private final WithdrawFiatPresenter arg$1;

    private WithdrawFiatPresenter$$Lambda$23(WithdrawFiatPresenter withdrawFiatPresenter) {
        this.arg$1 = withdrawFiatPresenter;
    }

    public static Action1 lambdaFactory$(WithdrawFiatPresenter withdrawFiatPresenter) {
        return new WithdrawFiatPresenter$$Lambda$23(withdrawFiatPresenter);
    }

    public void call(Object obj) {
        WithdrawFiatPresenter.lambda$hookUpAvailableBalanceAppBar$22(this.arg$1, (AvailableBalance) obj);
    }
}
