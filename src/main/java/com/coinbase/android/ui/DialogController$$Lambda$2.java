package com.coinbase.android.ui;

final /* synthetic */ class DialogController$$Lambda$2 implements Runnable {
    private final DialogController arg$1;

    private DialogController$$Lambda$2(DialogController dialogController) {
        this.arg$1 = dialogController;
    }

    public static Runnable lambdaFactory$(DialogController dialogController) {
        return new DialogController$$Lambda$2(dialogController);
    }

    public void run() {
        this.arg$1.getRouter().popController(this.arg$1);
    }
}
