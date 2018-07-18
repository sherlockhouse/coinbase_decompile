package com.coinbase.android.widgets;

final /* synthetic */ class UpdateWidgetBalanceService$$Lambda$1 implements Runnable {
    private final UpdateWidgetBalanceService arg$1;
    private final int arg$2;

    private UpdateWidgetBalanceService$$Lambda$1(UpdateWidgetBalanceService updateWidgetBalanceService, int i) {
        this.arg$1 = updateWidgetBalanceService;
        this.arg$2 = i;
    }

    public static Runnable lambdaFactory$(UpdateWidgetBalanceService updateWidgetBalanceService, int i) {
        return new UpdateWidgetBalanceService$$Lambda$1(updateWidgetBalanceService, i);
    }

    public void run() {
        this.arg$1.stopSelf(this.arg$2);
    }
}
