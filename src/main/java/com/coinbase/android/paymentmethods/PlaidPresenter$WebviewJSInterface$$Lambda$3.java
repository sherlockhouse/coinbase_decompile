package com.coinbase.android.paymentmethods;

import rx.functions.Action1;

final /* synthetic */ class PlaidPresenter$WebviewJSInterface$$Lambda$3 implements Action1 {
    private final WebviewJSInterface arg$1;

    private PlaidPresenter$WebviewJSInterface$$Lambda$3(WebviewJSInterface webviewJSInterface) {
        this.arg$1 = webviewJSInterface;
    }

    public static Action1 lambdaFactory$(WebviewJSInterface webviewJSInterface) {
        return new PlaidPresenter$WebviewJSInterface$$Lambda$3(webviewJSInterface);
    }

    public void call(Object obj) {
        this.arg$1.this$0.mLogger.error("Couldn't change status bar color", (Throwable) obj);
    }
}
