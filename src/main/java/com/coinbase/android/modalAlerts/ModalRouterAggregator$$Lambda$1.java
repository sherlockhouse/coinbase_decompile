package com.coinbase.android.modalAlerts;

import rx.functions.Action0;
import rx.functions.Action1;

final /* synthetic */ class ModalRouterAggregator$$Lambda$1 implements Action1 {
    private final ModalRouterAggregator arg$1;

    private ModalRouterAggregator$$Lambda$1(ModalRouterAggregator modalRouterAggregator) {
        this.arg$1 = modalRouterAggregator;
    }

    public static Action1 lambdaFactory$(ModalRouterAggregator modalRouterAggregator) {
        return new ModalRouterAggregator$$Lambda$1(modalRouterAggregator);
    }

    public void call(Object obj) {
        ModalRouterAggregator.lambda$route$0(this.arg$1, (Action0) obj);
    }
}
