package com.coinbase.android.signin.state;

final /* synthetic */ class UpfrontKycIdentityDocumentScanPresenter$$Lambda$11 implements Runnable {
    private final UpfrontKycIdentityDocumentScanPresenter arg$1;
    private final boolean arg$2;

    private UpfrontKycIdentityDocumentScanPresenter$$Lambda$11(UpfrontKycIdentityDocumentScanPresenter upfrontKycIdentityDocumentScanPresenter, boolean z) {
        this.arg$1 = upfrontKycIdentityDocumentScanPresenter;
        this.arg$2 = z;
    }

    public static Runnable lambdaFactory$(UpfrontKycIdentityDocumentScanPresenter upfrontKycIdentityDocumentScanPresenter, boolean z) {
        return new UpfrontKycIdentityDocumentScanPresenter$$Lambda$11(upfrontKycIdentityDocumentScanPresenter, z);
    }

    public void run() {
        UpfrontKycIdentityDocumentScanPresenter.lambda$null$3(this.arg$1, this.arg$2);
    }
}
