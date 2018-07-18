package com.coinbase.android.identityverification;

final /* synthetic */ class JumioDocumentScanPresenter$$Lambda$5 implements Runnable {
    private final JumioDocumentScanPresenter arg$1;

    private JumioDocumentScanPresenter$$Lambda$5(JumioDocumentScanPresenter jumioDocumentScanPresenter) {
        this.arg$1 = jumioDocumentScanPresenter;
    }

    public static Runnable lambdaFactory$(JumioDocumentScanPresenter jumioDocumentScanPresenter) {
        return new JumioDocumentScanPresenter$$Lambda$5(jumioDocumentScanPresenter);
    }

    public void run() {
        this.arg$1.retakePhoto();
    }
}
