package com.coinbase.android.paymentmethods;

final /* synthetic */ class IAVLoginFragment$$Lambda$16 implements Runnable {
    private final IAVLoginFragment arg$1;

    private IAVLoginFragment$$Lambda$16(IAVLoginFragment iAVLoginFragment) {
        this.arg$1 = iAVLoginFragment;
    }

    public static Runnable lambdaFactory$(IAVLoginFragment iAVLoginFragment) {
        return new IAVLoginFragment$$Lambda$16(iAVLoginFragment);
    }

    public void run() {
        IAVLoginFragment.lambda$showRoutingDetails$15(this.arg$1);
    }
}
