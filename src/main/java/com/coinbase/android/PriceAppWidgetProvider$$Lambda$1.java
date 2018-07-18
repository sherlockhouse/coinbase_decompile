package com.coinbase.android;

final /* synthetic */ class PriceAppWidgetProvider$$Lambda$1 implements Runnable {
    private static final PriceAppWidgetProvider$$Lambda$1 instance = new PriceAppWidgetProvider$$Lambda$1();

    private PriceAppWidgetProvider$$Lambda$1() {
    }

    public static Runnable lambdaFactory$() {
        return instance;
    }

    public void run() {
        PriceAppWidgetProvider.lambda$onUpdate$0();
    }
}
