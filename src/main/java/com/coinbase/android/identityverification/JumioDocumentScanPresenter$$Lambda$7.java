package com.coinbase.android.identityverification;

final /* synthetic */ class JumioDocumentScanPresenter$$Lambda$7 implements Runnable {
    private final JumioDocumentScanPresenter arg$1;
    private final boolean arg$2;

    private JumioDocumentScanPresenter$$Lambda$7(JumioDocumentScanPresenter jumioDocumentScanPresenter, boolean z) {
        this.arg$1 = jumioDocumentScanPresenter;
        this.arg$2 = z;
    }

    public static Runnable lambdaFactory$(JumioDocumentScanPresenter jumioDocumentScanPresenter, boolean z) {
        return new JumioDocumentScanPresenter$$Lambda$7(jumioDocumentScanPresenter, z);
    }

    public void run() {
        JumioDocumentScanPresenter.lambda$null$3(this.arg$1, this.arg$2);
    }
}
