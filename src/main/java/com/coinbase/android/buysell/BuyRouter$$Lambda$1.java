package com.coinbase.android.buysell;

import android.content.Intent;
import android.util.Pair;
import com.coinbase.android.event.ClassConsumableEvent;
import rx.Observable;
import rx_activity_result.OnPreResult;

final /* synthetic */ class BuyRouter$$Lambda$1 implements OnPreResult {
    private final BuyRouter arg$1;

    private BuyRouter$$Lambda$1(BuyRouter buyRouter) {
        this.arg$1 = buyRouter;
    }

    public static OnPreResult lambdaFactory$(BuyRouter buyRouter) {
        return new BuyRouter$$Lambda$1(buyRouter);
    }

    public Observable response(int i, Intent intent) {
        return this.arg$1.mBuy3dsVerificationConnector.get().onNext(new Pair(new ClassConsumableEvent(), new Pair(Integer.valueOf(i), intent)));
    }
}
