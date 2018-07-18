package com.coinbase.android.identityverification;

import org.apache.commons.lang3.mutable.MutableBoolean;
import rx.functions.Func4;

final /* synthetic */ class JumioDocumentScanPresenter$$Lambda$1 implements Func4 {
    private final JumioDocumentScanPresenter arg$1;

    private JumioDocumentScanPresenter$$Lambda$1(JumioDocumentScanPresenter jumioDocumentScanPresenter) {
        this.arg$1 = jumioDocumentScanPresenter;
    }

    public static Func4 lambdaFactory$(JumioDocumentScanPresenter jumioDocumentScanPresenter) {
        return new JumioDocumentScanPresenter$$Lambda$1(jumioDocumentScanPresenter);
    }

    public Object call(Object obj, Object obj2, Object obj3, Object obj4) {
        return JumioDocumentScanPresenter.lambda$onShow$4(this.arg$1, (MutableBoolean) obj, (MutableBoolean) obj2, (MutableBoolean) obj3, (IdentityVerificationBitmapContainer) obj4);
    }
}
