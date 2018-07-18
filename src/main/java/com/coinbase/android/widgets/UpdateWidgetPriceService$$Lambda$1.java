package com.coinbase.android.widgets;

final /* synthetic */ class UpdateWidgetPriceService$$Lambda$1 implements Runnable {
    private final UpdateWidgetPriceService arg$1;
    private final int arg$2;

    private UpdateWidgetPriceService$$Lambda$1(UpdateWidgetPriceService updateWidgetPriceService, int i) {
        this.arg$1 = updateWidgetPriceService;
        this.arg$2 = i;
    }

    public static Runnable lambdaFactory$(UpdateWidgetPriceService updateWidgetPriceService, int i) {
        return new UpdateWidgetPriceService$$Lambda$1(updateWidgetPriceService, i);
    }

    public void run() {
        this.arg$1.stopSelf(this.arg$2);
    }
}
