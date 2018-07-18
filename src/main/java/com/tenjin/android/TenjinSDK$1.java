package com.tenjin.android;

import java.util.Date;

class TenjinSDK$1 implements OnReferrerListener {
    final /* synthetic */ TenjinSDK this$0;

    TenjinSDK$1(TenjinSDK this$0) {
        this.this$0 = this$0;
    }

    public void onReferrerUpdated() {
        TenjinSDK.access$002(this.this$0, new Date().getTime());
        TenjinSDK.access$100().set(true);
        TenjinSDK.access$200(this.this$0, "broadcast_referrer");
    }
}
