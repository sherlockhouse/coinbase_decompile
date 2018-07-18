package com.tenjin.android;

import android.content.ContentResolver;
import android.os.AsyncTask;
import android.provider.Settings.Secure;
import android.text.TextUtils;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.IOException;

class TenjinSDK$AsyncGetAdvertisingInfo extends AsyncTask<Void, Void, TenjinSDK$AdvertiserInfo> {
    String advertisingId;
    Boolean limitAdTracking;
    final /* synthetic */ TenjinSDK this$0;

    private TenjinSDK$AsyncGetAdvertisingInfo(TenjinSDK tenjinSDK) {
        this.this$0 = tenjinSDK;
    }

    protected TenjinSDK$AdvertiserInfo doInBackground(Void... params) {
        boolean z = true;
        try {
            if (TenjinSDK.access$1400(this.this$0).equals("amazon")) {
                ContentResolver cr = TenjinSDK.access$1000(this.this$0).getContentResolver();
                int lat = Secure.getInt(cr, "limit_ad_tracking", 2);
                if (lat == 0) {
                    this.advertisingId = Secure.getString(cr, "advertising_id");
                    this.limitAdTracking = Boolean.valueOf(false);
                } else if (lat == 2) {
                    this.limitAdTracking = Boolean.valueOf(true);
                } else {
                    this.limitAdTracking = Boolean.valueOf(true);
                }
                TenjinSDK.access$1502(this.this$0, this.advertisingId);
                TenjinSDK.access$1602(this.this$0, String.valueOf(this.limitAdTracking));
                TenjinSDK.access$1702(this.this$0, new TenjinSDK$AdvertiserInfo(this.this$0, this.advertisingId, this.limitAdTracking));
                return TenjinSDK.access$1700(this.this$0);
            }
            try {
                Info adInfo = AdvertisingIdClient.getAdvertisingIdInfo(TenjinSDK.access$1000(this.this$0));
                if (adInfo != null) {
                    this.advertisingId = adInfo.getId();
                    if (!adInfo.isLimitAdTrackingEnabled()) {
                        z = false;
                    }
                    this.limitAdTracking = Boolean.valueOf(z);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (GooglePlayServicesNotAvailableException e2) {
                e2.printStackTrace();
            } catch (GooglePlayServicesRepairableException e3) {
                e3.printStackTrace();
            } catch (Exception e4) {
                e4.printStackTrace();
            }
            TenjinSDK.access$1502(this.this$0, this.advertisingId);
            TenjinSDK.access$1602(this.this$0, String.valueOf(this.limitAdTracking));
            TenjinSDK.access$1702(this.this$0, new TenjinSDK$AdvertiserInfo(this.this$0, this.advertisingId, this.limitAdTracking));
            return TenjinSDK.access$1700(this.this$0);
        } catch (Exception e42) {
            e42.printStackTrace();
        }
    }

    protected void onPostExecute(TenjinSDK$AdvertiserInfo advertiserInfo) {
        if (advertiserInfo != null && advertiserInfo.getAdvertisingId() != null && !TextUtils.isEmpty(advertiserInfo.getAdvertisingId())) {
            TenjinSDK.access$1900().set(true);
            TenjinSDK.access$200(this.this$0, "advertising_info");
        }
    }
}
