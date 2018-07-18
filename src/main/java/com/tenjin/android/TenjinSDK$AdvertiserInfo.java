package com.tenjin.android;

class TenjinSDK$AdvertiserInfo {
    private String advertisingId;
    private Boolean limitAdTracking;
    final /* synthetic */ TenjinSDK this$0;

    TenjinSDK$AdvertiserInfo(TenjinSDK this$0, String advertisingId, Boolean limitAdTracking) {
        this.this$0 = this$0;
        this.advertisingId = advertisingId;
        this.limitAdTracking = limitAdTracking;
    }

    private String getAdvertisingId() {
        return this.advertisingId;
    }

    private Boolean getLimitAdTracking() {
        return this.limitAdTracking;
    }
}
