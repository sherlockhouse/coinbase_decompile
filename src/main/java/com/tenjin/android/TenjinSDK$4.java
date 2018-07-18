package com.tenjin.android;

class TenjinSDK$4 implements Runnable {
    final /* synthetic */ TenjinSDK this$0;

    TenjinSDK$4(TenjinSDK this$0) {
        this.this$0 = this$0;
    }

    public void run() {
        synchronized (TenjinSDK.access$700()) {
            try {
                TenjinSDK.access$6600(this.this$0, TenjinSDK.access$1000(this.this$0));
            } catch (Exception e) {
                TenjinSDK.access$700().set(false);
            }
        }
    }
}
