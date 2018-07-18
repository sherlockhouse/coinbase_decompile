package com.tenjin.android;

import com.android.installreferrer.api.InstallReferrerStateListener;

class TenjinSDK$3 implements InstallReferrerStateListener {
    final /* synthetic */ TenjinSDK this$0;

    TenjinSDK$3(TenjinSDK this$0) {
        this.this$0 = this$0;
    }

    public void onInstallReferrerSetupFinished(int responseCode) {
        if (responseCode == 0) {
            TenjinSDK.access$6200(this.this$0, TenjinSDK.access$6100(this.this$0));
            TenjinSDK.access$6100(this.this$0).endConnection();
        } else if (responseCode == -1) {
            TenjinSDK.access$6308(this.this$0);
            if (TenjinSDK.access$6300(this.this$0) < 3) {
                TenjinSDK.access$6100(this.this$0).startConnection(TenjinSDK.access$6400(this.this$0));
            } else {
                TenjinSDK.access$6500(this.this$0, responseCode);
            }
        } else {
            TenjinSDK.access$6500(this.this$0, responseCode);
        }
    }

    public void onInstallReferrerServiceDisconnected() {
        TenjinSDK.access$6500(this.this$0, -1);
    }
}
