package com.coinbase.android.paymentmethods;

import rx.functions.Action1;

final /* synthetic */ class PlaidPresenter$WebviewJSInterface$$Lambda$2 implements Action1 {
    private final WebviewJSInterface arg$1;

    private PlaidPresenter$WebviewJSInterface$$Lambda$2(WebviewJSInterface webviewJSInterface) {
        this.arg$1 = webviewJSInterface;
    }

    public static Action1 lambdaFactory$(WebviewJSInterface webviewJSInterface) {
        return new PlaidPresenter$WebviewJSInterface$$Lambda$2(webviewJSInterface);
    }

    public void call(Object obj) {
        WebviewJSInterface.lambda$processHTML$1(this.arg$1, obj);
    }
}
