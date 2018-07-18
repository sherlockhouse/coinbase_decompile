package com.coinbase.android.signin.state;

import java.util.List;
import rx.functions.Func1;

final /* synthetic */ class UpfrontKycIdentityDocumentScanPresenter$$Lambda$5 implements Func1 {
    private final UpfrontKycIdentityDocumentScanPresenter arg$1;
    private final String arg$2;
    private final String arg$3;

    private UpfrontKycIdentityDocumentScanPresenter$$Lambda$5(UpfrontKycIdentityDocumentScanPresenter upfrontKycIdentityDocumentScanPresenter, String str, String str2) {
        this.arg$1 = upfrontKycIdentityDocumentScanPresenter;
        this.arg$2 = str;
        this.arg$3 = str2;
    }

    public static Func1 lambdaFactory$(UpfrontKycIdentityDocumentScanPresenter upfrontKycIdentityDocumentScanPresenter, String str, String str2) {
        return new UpfrontKycIdentityDocumentScanPresenter$$Lambda$5(upfrontKycIdentityDocumentScanPresenter, str, str2);
    }

    public Object call(Object obj) {
        return this.arg$1.mLoginManager.getClient().createJumioProfileRx(this.arg$2, this.arg$3, (byte[]) ((List) obj).get(0), (byte[]) ((List) obj).get(1), (byte[]) ((List) obj).get(2));
    }
}
