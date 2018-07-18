package com.tenjin.android;

class TenjinSDK$2 implements Runnable {
    final /* synthetic */ TenjinSDK this$0;
    final /* synthetic */ Callback val$callback;
    final /* synthetic */ boolean val$firstLaunch;

    TenjinSDK$2(TenjinSDK this$0, Callback callback, boolean z) {
        this.this$0 = this$0;
        this.val$callback = callback;
        this.val$firstLaunch = z;
    }

    public void run() {
        this.val$callback.onSuccess(TenjinSDK.access$4900(this.this$0), this.val$firstLaunch, TenjinSDK.access$5000(this.this$0));
    }
}
