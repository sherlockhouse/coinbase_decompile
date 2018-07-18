package com.coinbase.android.paymentmethods;

import rx.Observable.OnSubscribe;
import rx.Subscriber;

final /* synthetic */ class PlaidPresenter$WebviewJSInterface$$Lambda$1 implements OnSubscribe {
    private final WebviewJSInterface arg$1;
    private final String arg$2;

    private PlaidPresenter$WebviewJSInterface$$Lambda$1(WebviewJSInterface webviewJSInterface, String str) {
        this.arg$1 = webviewJSInterface;
        this.arg$2 = str;
    }

    public static OnSubscribe lambdaFactory$(WebviewJSInterface webviewJSInterface, String str) {
        return new PlaidPresenter$WebviewJSInterface$$Lambda$1(webviewJSInterface, str);
    }

    public void call(Object obj) {
        WebviewJSInterface.lambda$processHTML$0(this.arg$1, this.arg$2, (Subscriber) obj);
    }
}
