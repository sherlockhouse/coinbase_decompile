package com.coinbase.android.modalAlerts;

import rx.functions.Action1;

final /* synthetic */ class ModalRouterAggregator$$Lambda$2 implements Action1 {
    private final ModalRouterAggregator arg$1;

    private ModalRouterAggregator$$Lambda$2(ModalRouterAggregator modalRouterAggregator) {
        this.arg$1 = modalRouterAggregator;
    }

    public static Action1 lambdaFactory$(ModalRouterAggregator modalRouterAggregator) {
        return new ModalRouterAggregator$$Lambda$2(modalRouterAggregator);
    }

    public void call(Object obj) {
        this.arg$1.mLogger.error("Couldn't subscribe for modal routing action", (Throwable) obj);
    }
}
