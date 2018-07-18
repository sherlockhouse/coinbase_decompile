package com.coinbase.android.signin.state;

final /* synthetic */ class UpfrontKycIdentityDocumentScanPresenter$$Lambda$9 implements Runnable {
    private final UpfrontKycIdentityDocumentScanPresenter arg$1;

    private UpfrontKycIdentityDocumentScanPresenter$$Lambda$9(UpfrontKycIdentityDocumentScanPresenter upfrontKycIdentityDocumentScanPresenter) {
        this.arg$1 = upfrontKycIdentityDocumentScanPresenter;
    }

    public static Runnable lambdaFactory$(UpfrontKycIdentityDocumentScanPresenter upfrontKycIdentityDocumentScanPresenter) {
        return new UpfrontKycIdentityDocumentScanPresenter$$Lambda$9(upfrontKycIdentityDocumentScanPresenter);
    }

    public void run() {
        this.arg$1.retakePhoto();
    }
}
