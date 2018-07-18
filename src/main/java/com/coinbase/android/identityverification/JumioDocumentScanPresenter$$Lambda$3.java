package com.coinbase.android.identityverification;

import rx.functions.Action1;

final /* synthetic */ class JumioDocumentScanPresenter$$Lambda$3 implements Action1 {
    private final JumioDocumentScanPresenter arg$1;

    private JumioDocumentScanPresenter$$Lambda$3(JumioDocumentScanPresenter jumioDocumentScanPresenter) {
        this.arg$1 = jumioDocumentScanPresenter;
    }

    public static Action1 lambdaFactory$(JumioDocumentScanPresenter jumioDocumentScanPresenter) {
        return new JumioDocumentScanPresenter$$Lambda$3(jumioDocumentScanPresenter);
    }

    public void call(Object obj) {
        JumioDocumentScanPresenter.lambda$detectFace$6(this.arg$1, (Boolean) obj);
    }
}
