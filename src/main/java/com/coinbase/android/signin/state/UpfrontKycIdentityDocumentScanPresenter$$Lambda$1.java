package com.coinbase.android.signin.state;

import com.coinbase.android.identityverification.IdentityVerificationBitmapContainer;
import org.apache.commons.lang3.mutable.MutableBoolean;
import rx.functions.Func4;

final /* synthetic */ class UpfrontKycIdentityDocumentScanPresenter$$Lambda$1 implements Func4 {
    private final UpfrontKycIdentityDocumentScanPresenter arg$1;

    private UpfrontKycIdentityDocumentScanPresenter$$Lambda$1(UpfrontKycIdentityDocumentScanPresenter upfrontKycIdentityDocumentScanPresenter) {
        this.arg$1 = upfrontKycIdentityDocumentScanPresenter;
    }

    public static Func4 lambdaFactory$(UpfrontKycIdentityDocumentScanPresenter upfrontKycIdentityDocumentScanPresenter) {
        return new UpfrontKycIdentityDocumentScanPresenter$$Lambda$1(upfrontKycIdentityDocumentScanPresenter);
    }

    public Object call(Object obj, Object obj2, Object obj3, Object obj4) {
        return UpfrontKycIdentityDocumentScanPresenter.lambda$onShow$4(this.arg$1, (MutableBoolean) obj, (MutableBoolean) obj2, (MutableBoolean) obj3, (IdentityVerificationBitmapContainer) obj4);
    }
}
