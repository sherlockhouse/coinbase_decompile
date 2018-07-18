package com.google.android.gms.measurement;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Keep;
import android.support.v4.util.ArrayMap;
import com.coinbase.android.Constants;
import com.google.android.gms.common.api.internal.zzca;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.internal.zzcax;
import com.google.android.gms.internal.zzccw;
import com.google.android.gms.internal.zzcdw;
import com.google.android.gms.internal.zzcft;
import com.google.android.gms.internal.zzcfw;
import java.util.List;
import java.util.Map;

@Keep
@Deprecated
public class AppMeasurement {
    public static final String CRASH_ORIGIN = "crash";
    public static final String FCM_ORIGIN = "fcm";
    private final zzccw zziki;

    public static class zzb {
        public String zzikn;
        public String zziko;
        public long zzikp;

        public zzb(zzb com_google_android_gms_measurement_AppMeasurement_zzb) {
            this.zzikn = com_google_android_gms_measurement_AppMeasurement_zzb.zzikn;
            this.zziko = com_google_android_gms_measurement_AppMeasurement_zzb.zziko;
            this.zzikp = com_google_android_gms_measurement_AppMeasurement_zzb.zzikp;
        }
    }

    public static class ConditionalUserProperty {
        @Keep
        public boolean mActive;
        @Keep
        public String mAppId;
        @Keep
        public long mCreationTimestamp;
        @Keep
        public String mExpiredEventName;
        @Keep
        public Bundle mExpiredEventParams;
        @Keep
        public String mName;
        @Keep
        public String mOrigin;
        @Keep
        public long mTimeToLive;
        @Keep
        public String mTimedOutEventName;
        @Keep
        public Bundle mTimedOutEventParams;
        @Keep
        public String mTriggerEventName;
        @Keep
        public long mTriggerTimeout;
        @Keep
        public String mTriggeredEventName;
        @Keep
        public Bundle mTriggeredEventParams;
        @Keep
        public long mTriggeredTimestamp;
        @Keep
        public Object mValue;

        public ConditionalUserProperty(ConditionalUserProperty conditionalUserProperty) {
            zzbp.zzu(conditionalUserProperty);
            this.mAppId = conditionalUserProperty.mAppId;
            this.mOrigin = conditionalUserProperty.mOrigin;
            this.mCreationTimestamp = conditionalUserProperty.mCreationTimestamp;
            this.mName = conditionalUserProperty.mName;
            if (conditionalUserProperty.mValue != null) {
                this.mValue = zzcfw.zzad(conditionalUserProperty.mValue);
                if (this.mValue == null) {
                    this.mValue = conditionalUserProperty.mValue;
                }
            }
            this.mValue = conditionalUserProperty.mValue;
            this.mActive = conditionalUserProperty.mActive;
            this.mTriggerEventName = conditionalUserProperty.mTriggerEventName;
            this.mTriggerTimeout = conditionalUserProperty.mTriggerTimeout;
            this.mTimedOutEventName = conditionalUserProperty.mTimedOutEventName;
            if (conditionalUserProperty.mTimedOutEventParams != null) {
                this.mTimedOutEventParams = new Bundle(conditionalUserProperty.mTimedOutEventParams);
            }
            this.mTriggeredEventName = conditionalUserProperty.mTriggeredEventName;
            if (conditionalUserProperty.mTriggeredEventParams != null) {
                this.mTriggeredEventParams = new Bundle(conditionalUserProperty.mTriggeredEventParams);
            }
            this.mTriggeredTimestamp = conditionalUserProperty.mTriggeredTimestamp;
            this.mTimeToLive = conditionalUserProperty.mTimeToLive;
            this.mExpiredEventName = conditionalUserProperty.mExpiredEventName;
            if (conditionalUserProperty.mExpiredEventParams != null) {
                this.mExpiredEventParams = new Bundle(conditionalUserProperty.mExpiredEventParams);
            }
        }
    }

    public static final class Event extends com.google.firebase.analytics.FirebaseAnalytics.Event {
        public static final String[] zzikj = new String[]{"app_clear_data", "app_exception", "app_remove", "app_upgrade", "app_install", "app_update", "firebase_campaign", "error", "first_open", "first_visit", "in_app_purchase", "notification_dismiss", "notification_foreground", "notification_open", "notification_receive", "os_update", "session_start", "user_engagement", "ad_exposure", "adunit_exposure", "ad_query", "ad_activeview", "ad_impression", "ad_click", "screen_view", "firebase_extra_parameter"};
        public static final String[] zzikk = new String[]{"_cd", "_ae", "_ui", "_ug", "_in", "_au", "_cmp", "_err", "_f", "_v", "_iap", "_nd", "_nf", "_no", "_nr", "_ou", "_s", "_e", "_xa", "_xu", "_aq", "_aa", "_ai", "_ac", "_vs", "_ep"};

        public static String zzil(String str) {
            return zzcfw.zza(str, zzikj, zzikk);
        }
    }

    public interface EventInterceptor {
        void interceptEvent(String str, String str2, Bundle bundle, long j);
    }

    public interface OnEventListener {
        void onEvent(String str, String str2, Bundle bundle, long j);
    }

    public static final class Param extends com.google.firebase.analytics.FirebaseAnalytics.Param {
        public static final String[] zzikl = new String[]{"firebase_conversion", "engagement_time_msec", "exposure_time", "ad_event_id", "ad_unit_id", "firebase_error", "firebase_error_value", "firebase_error_length", "firebase_event_origin", "firebase_screen", "firebase_screen_class", "firebase_screen_id", "firebase_previous_screen", "firebase_previous_class", "firebase_previous_id", "message_device_time", "message_id", "message_name", "message_time", "previous_app_version", "previous_os_version", "topic", "update_with_analytics", "previous_first_open_count", "system_app", "system_app_update", "previous_install_count", "firebase_event_id", "firebase_extra_params_ct", "firebase_group_name", "firebase_list_length", "firebase_index", "firebase_event_name"};
        public static final String[] zzikm = new String[]{"_c", "_et", "_xt", "_aeid", "_ai", "_err", "_ev", "_el", "_o", "_sn", "_sc", "_si", "_pn", "_pc", "_pi", "_ndt", "_nmid", "_nmn", "_nmt", "_pv", "_po", "_nt", "_uwa", "_pfo", "_sys", "_sysu", "_pin", "_eid", "_epc", "_gn", "_ll", "_i", "_en"};

        public static String zzil(String str) {
            return zzcfw.zza(str, zzikl, zzikm);
        }
    }

    public static final class UserProperty extends com.google.firebase.analytics.FirebaseAnalytics.UserProperty {
        public static final String[] zzikq = new String[]{"firebase_last_notification", "first_open_time", "first_visit_time", "last_deep_link_referrer", Constants.KEY_USER_ID, "first_open_after_install"};
        public static final String[] zzikr = new String[]{"_ln", "_fot", "_fvt", "_ldl", "_id", "_fi"};

        public static String zzil(String str) {
            return zzcfw.zza(str, zzikq, zzikr);
        }
    }

    public interface zza {
        boolean zza(zzb com_google_android_gms_measurement_AppMeasurement_zzb, zzb com_google_android_gms_measurement_AppMeasurement_zzb2);
    }

    public AppMeasurement(zzccw com_google_android_gms_internal_zzccw) {
        zzbp.zzu(com_google_android_gms_internal_zzccw);
        this.zziki = com_google_android_gms_internal_zzccw;
    }

    @Keep
    @Deprecated
    public static AppMeasurement getInstance(Context context) {
        return zzccw.zzdn(context).zzayy();
    }

    @Keep
    public void beginAdUnitExposure(String str) {
        this.zziki.zzatx().beginAdUnitExposure(str);
    }

    @Keep
    protected void clearConditionalUserProperty(String str, String str2, Bundle bundle) {
        this.zziki.zzatz().clearConditionalUserProperty(str, str2, bundle);
    }

    @Keep
    protected void clearConditionalUserPropertyAs(String str, String str2, String str3, Bundle bundle) {
        this.zziki.zzatz().clearConditionalUserPropertyAs(str, str2, str3, bundle);
    }

    @Keep
    public void endAdUnitExposure(String str) {
        this.zziki.zzatx().endAdUnitExposure(str);
    }

    @Keep
    public long generateEventId() {
        return this.zziki.zzauh().zzazx();
    }

    @Keep
    public String getAppInstanceId() {
        return this.zziki.zzatz().zzayn();
    }

    @Keep
    protected List<ConditionalUserProperty> getConditionalUserProperties(String str, String str2) {
        return this.zziki.zzatz().getConditionalUserProperties(str, str2);
    }

    @Keep
    protected List<ConditionalUserProperty> getConditionalUserPropertiesAs(String str, String str2, String str3) {
        return this.zziki.zzatz().getConditionalUserPropertiesAs(str, str2, str3);
    }

    @Keep
    public String getCurrentScreenClass() {
        zzb zzazo = this.zziki.zzaud().zzazo();
        return zzazo != null ? zzazo.zziko : null;
    }

    @Keep
    public String getCurrentScreenName() {
        zzb zzazo = this.zziki.zzaud().zzazo();
        return zzazo != null ? zzazo.zzikn : null;
    }

    @Keep
    public String getGmpAppId() {
        try {
            return zzca.zzaie();
        } catch (IllegalStateException e) {
            this.zziki.zzaul().zzayd().zzj("getGoogleAppId failed with exception", e);
            return null;
        }
    }

    @Keep
    protected int getMaxUserProperties(String str) {
        this.zziki.zzatz();
        return zzcdw.getMaxUserProperties(str);
    }

    @Keep
    protected Map<String, Object> getUserProperties(String str, String str2, boolean z) {
        return this.zziki.zzatz().getUserProperties(str, str2, z);
    }

    public Map<String, Object> getUserProperties(boolean z) {
        List<zzcft> zzbq = this.zziki.zzatz().zzbq(z);
        Map<String, Object> arrayMap = new ArrayMap(zzbq.size());
        for (zzcft com_google_android_gms_internal_zzcft : zzbq) {
            arrayMap.put(com_google_android_gms_internal_zzcft.name, com_google_android_gms_internal_zzcft.getValue());
        }
        return arrayMap;
    }

    @Keep
    protected Map<String, Object> getUserPropertiesAs(String str, String str2, String str3, boolean z) {
        return this.zziki.zzatz().getUserPropertiesAs(str, str2, str3, z);
    }

    public final void logEvent(String str, Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        zzcax.zzawk();
        if (!"_iap".equals(str)) {
            int zzjv = this.zziki.zzauh().zzjv(str);
            if (zzjv != 0) {
                this.zziki.zzauh();
                this.zziki.zzauh().zza(zzjv, "_ev", zzcfw.zza(str, zzcax.zzavn(), true), str != null ? str.length() : 0);
                return;
            }
        }
        this.zziki.zzatz().zza("app", str, bundle, true);
    }

    @Keep
    public void logEventInternal(String str, String str2, Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        this.zziki.zzatz().zzc(str, str2, bundle);
    }

    public void logEventInternalNoInterceptor(String str, String str2, Bundle bundle, long j) {
        this.zziki.zzatz().zza(str, str2, bundle == null ? new Bundle() : bundle, j);
    }

    public void registerOnMeasurementEventListener(OnEventListener onEventListener) {
        this.zziki.zzatz().registerOnMeasurementEventListener(onEventListener);
    }

    @Keep
    public void registerOnScreenChangeCallback(zza com_google_android_gms_measurement_AppMeasurement_zza) {
        this.zziki.zzaud().registerOnScreenChangeCallback(com_google_android_gms_measurement_AppMeasurement_zza);
    }

    @Keep
    protected void setConditionalUserProperty(ConditionalUserProperty conditionalUserProperty) {
        this.zziki.zzatz().setConditionalUserProperty(conditionalUserProperty);
    }

    @Keep
    protected void setConditionalUserPropertyAs(ConditionalUserProperty conditionalUserProperty) {
        this.zziki.zzatz().setConditionalUserPropertyAs(conditionalUserProperty);
    }

    public void setEventInterceptor(EventInterceptor eventInterceptor) {
        this.zziki.zzatz().setEventInterceptor(eventInterceptor);
    }

    @Deprecated
    public void setMeasurementEnabled(boolean z) {
        this.zziki.zzatz().setMeasurementEnabled(z);
    }

    public final void setMinimumSessionDuration(long j) {
        this.zziki.zzatz().setMinimumSessionDuration(j);
    }

    public final void setSessionTimeoutDuration(long j) {
        this.zziki.zzatz().setSessionTimeoutDuration(j);
    }

    public final void setUserProperty(String str, String str2) {
        int zzjx = this.zziki.zzauh().zzjx(str);
        if (zzjx != 0) {
            this.zziki.zzauh();
            this.zziki.zzauh().zza(zzjx, "_ev", zzcfw.zza(str, zzcax.zzavo(), true), str != null ? str.length() : 0);
            return;
        }
        setUserPropertyInternal("app", str, str2);
    }

    public void setUserPropertyInternal(String str, String str2, Object obj) {
        this.zziki.zzatz().zzb(str, str2, obj);
    }

    public void unregisterOnMeasurementEventListener(OnEventListener onEventListener) {
        this.zziki.zzatz().unregisterOnMeasurementEventListener(onEventListener);
    }

    @Keep
    public void unregisterOnScreenChangeCallback(zza com_google_android_gms_measurement_AppMeasurement_zza) {
        this.zziki.zzaud().unregisterOnScreenChangeCallback(com_google_android_gms_measurement_AppMeasurement_zza);
    }
}
