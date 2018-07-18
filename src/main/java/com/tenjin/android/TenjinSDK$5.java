package com.tenjin.android;

class TenjinSDK$5 implements Runnable {
    final /* synthetic */ TenjinSDK this$0;

    TenjinSDK$5(TenjinSDK this$0) {
        this.this$0 = this$0;
    }

    public void run() {
        if (!TenjinSDK.access$700().get()) {
            TenjinSDK.access$6700().set(true);
            TenjinSDK.access$6800(this.this$0, false);
            TenjinSDK.access$200(this.this$0, "install_referrer_timeout");
        }
    }
}
