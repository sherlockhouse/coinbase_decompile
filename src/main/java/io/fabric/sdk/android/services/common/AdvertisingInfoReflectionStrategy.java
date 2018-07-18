package io.fabric.sdk.android.services.common;

import android.content.Context;
import io.fabric.sdk.android.Fabric;

class AdvertisingInfoReflectionStrategy implements AdvertisingInfoStrategy {
    private final Context context;

    public AdvertisingInfoReflectionStrategy(Context context) {
        this.context = context.getApplicationContext();
    }

    boolean isGooglePlayServiceAvailable(Context context) {
        try {
            if (((Integer) Class.forName("com.google.android.gms.common.GooglePlayServicesUtil").getMethod("isGooglePlayServicesAvailable", new Class[]{Context.class}).invoke(null, new Object[]{context})).intValue() == 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public AdvertisingInfo getAdvertisingInfo() {
        if (isGooglePlayServiceAvailable(this.context)) {
            return new AdvertisingInfo(getAdvertisingId(), isLimitAdTrackingEnabled());
        }
        return null;
    }

    private String getAdvertisingId() {
        try {
            return (String) Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient$Info").getMethod("getId", new Class[0]).invoke(getInfo(), new Object[0]);
        } catch (Exception e) {
            Fabric.getLogger().w("Fabric", "Could not call getId on com.google.android.gms.ads.identifier.AdvertisingIdClient$Info");
            return null;
        }
    }

    private boolean isLimitAdTrackingEnabled() {
        try {
            return ((Boolean) Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient$Info").getMethod("isLimitAdTrackingEnabled", new Class[0]).invoke(getInfo(), new Object[0])).booleanValue();
        } catch (Exception e) {
            Fabric.getLogger().w("Fabric", "Could not call isLimitAdTrackingEnabled on com.google.android.gms.ads.identifier.AdvertisingIdClient$Info");
            return false;
        }
    }

    private Object getInfo() {
        Object obj = null;
        try {
            obj = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient").getMethod("getAdvertisingIdInfo", new Class[]{Context.class}).invoke(null, new Object[]{this.context});
        } catch (Exception e) {
            Fabric.getLogger().w("Fabric", "Could not call getAdvertisingIdInfo on com.google.android.gms.ads.identifier.AdvertisingIdClient");
        }
        return obj;
    }
}
