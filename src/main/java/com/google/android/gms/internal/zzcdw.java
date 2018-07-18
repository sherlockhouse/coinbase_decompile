package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.common.util.zzd;
import com.google.android.gms.measurement.AppMeasurement.ConditionalUserProperty;
import com.google.android.gms.measurement.AppMeasurement.EventInterceptor;
import com.google.android.gms.measurement.AppMeasurement.OnEventListener;
import com.google.android.gms.measurement.AppMeasurement.zzb;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicReference;

public final class zzcdw extends zzcdu {
    protected zzcej zziut;
    private EventInterceptor zziuu;
    private final Set<OnEventListener> zziuv = new CopyOnWriteArraySet();
    private boolean zziuw;
    private final AtomicReference<String> zziux = new AtomicReference();

    protected zzcdw(zzccw com_google_android_gms_internal_zzccw) {
        super(com_google_android_gms_internal_zzccw);
    }

    public static int getMaxUserProperties(String str) {
        zzbp.zzgg(str);
        return zzcax.zzawa();
    }

    private final void zza(ConditionalUserProperty conditionalUserProperty) {
        long currentTimeMillis = zzvx().currentTimeMillis();
        zzbp.zzu(conditionalUserProperty);
        zzbp.zzgg(conditionalUserProperty.mName);
        zzbp.zzgg(conditionalUserProperty.mOrigin);
        zzbp.zzu(conditionalUserProperty.mValue);
        conditionalUserProperty.mCreationTimestamp = currentTimeMillis;
        String str = conditionalUserProperty.mName;
        Object obj = conditionalUserProperty.mValue;
        if (zzauh().zzjy(str) != 0) {
            zzaul().zzayd().zzj("Invalid conditional user property name", zzaug().zzje(str));
        } else if (zzauh().zzl(str, obj) != 0) {
            zzaul().zzayd().zze("Invalid conditional user property value", zzaug().zzje(str), obj);
        } else {
            Object zzm = zzauh().zzm(str, obj);
            if (zzm == null) {
                zzaul().zzayd().zze("Unable to normalize conditional user property value", zzaug().zzje(str), obj);
                return;
            }
            conditionalUserProperty.mValue = zzm;
            long j = conditionalUserProperty.mTriggerTimeout;
            if (TextUtils.isEmpty(conditionalUserProperty.mTriggerEventName) || (j <= zzcax.zzawc() && j >= 1)) {
                j = conditionalUserProperty.mTimeToLive;
                if (j > zzcax.zzawd() || j < 1) {
                    zzaul().zzayd().zze("Invalid conditional user property time to live", zzaug().zzje(str), Long.valueOf(j));
                    return;
                } else {
                    zzauk().zzg(new zzcdy(this, conditionalUserProperty));
                    return;
                }
            }
            zzaul().zzayd().zze("Invalid conditional user property timeout", zzaug().zzje(str), Long.valueOf(j));
        }
    }

    private final void zza(String str, String str2, long j, Bundle bundle, boolean z, boolean z2, boolean z3, String str3) {
        Bundle bundle2;
        if (bundle == null) {
            bundle2 = new Bundle();
        } else {
            bundle2 = new Bundle(bundle);
            for (String str4 : bundle2.keySet()) {
                Object obj = bundle2.get(str4);
                if (obj instanceof Bundle) {
                    bundle2.putBundle(str4, new Bundle((Bundle) obj));
                } else if (obj instanceof Parcelable[]) {
                    Parcelable[] parcelableArr = (Parcelable[]) obj;
                    for (r4 = 0; r4 < parcelableArr.length; r4++) {
                        if (parcelableArr[r4] instanceof Bundle) {
                            parcelableArr[r4] = new Bundle((Bundle) parcelableArr[r4]);
                        }
                    }
                } else if (obj instanceof ArrayList) {
                    ArrayList arrayList = (ArrayList) obj;
                    for (r4 = 0; r4 < arrayList.size(); r4++) {
                        Object obj2 = arrayList.get(r4);
                        if (obj2 instanceof Bundle) {
                            arrayList.set(r4, new Bundle((Bundle) obj2));
                        }
                    }
                }
            }
        }
        zzauk().zzg(new zzcee(this, str, str2, j, bundle2, z, z2, z3, str3));
    }

    private final void zza(String str, String str2, long j, Object obj) {
        zzauk().zzg(new zzcef(this, str, str2, obj, j));
    }

    private final void zza(String str, String str2, Bundle bundle, boolean z, boolean z2, boolean z3, String str3) {
        zza(str, str2, zzvx().currentTimeMillis(), bundle, true, z2, z3, null);
    }

    private final void zza(String str, String str2, Object obj, long j) {
        zzbp.zzgg(str);
        zzbp.zzgg(str2);
        zzuj();
        zzatv();
        zzwk();
        if (!this.zziki.isEnabled()) {
            zzaul().zzayi().log("User property not set since app measurement is disabled");
        } else if (this.zziki.zzayv()) {
            zzaul().zzayi().zze("Setting user property (FE)", zzaug().zzjc(str2), obj);
            zzauc().zzb(new zzcft(str2, j, obj, str));
        }
    }

    private final void zza(String str, String str2, String str3, Bundle bundle) {
        long currentTimeMillis = zzvx().currentTimeMillis();
        zzbp.zzgg(str2);
        ConditionalUserProperty conditionalUserProperty = new ConditionalUserProperty();
        conditionalUserProperty.mAppId = str;
        conditionalUserProperty.mName = str2;
        conditionalUserProperty.mCreationTimestamp = currentTimeMillis;
        if (str3 != null) {
            conditionalUserProperty.mExpiredEventName = str3;
            conditionalUserProperty.mExpiredEventParams = bundle;
        }
        zzauk().zzg(new zzcdz(this, conditionalUserProperty));
    }

    private final Map<String, Object> zzb(String str, String str2, String str3, boolean z) {
        if (zzauk().zzays()) {
            zzaul().zzayd().log("Cannot get user properties from analytics worker thread");
            return Collections.emptyMap();
        }
        zzauk();
        if (zzccr.zzaq()) {
            zzaul().zzayd().log("Cannot get user properties from main thread");
            return Collections.emptyMap();
        }
        AtomicReference atomicReference = new AtomicReference();
        synchronized (atomicReference) {
            this.zziki.zzauk().zzg(new zzceb(this, atomicReference, str, str2, str3, z));
            try {
                atomicReference.wait(5000);
            } catch (InterruptedException e) {
                zzaul().zzayf().zzj("Interrupted waiting for get user properties", e);
            }
        }
        List<zzcft> list = (List) atomicReference.get();
        if (list == null) {
            zzaul().zzayf().log("Timed out waiting for get user properties");
            return Collections.emptyMap();
        }
        Map<String, Object> arrayMap = new ArrayMap(list.size());
        for (zzcft com_google_android_gms_internal_zzcft : list) {
            arrayMap.put(com_google_android_gms_internal_zzcft.name, com_google_android_gms_internal_zzcft.getValue());
        }
        return arrayMap;
    }

    private final void zzb(ConditionalUserProperty conditionalUserProperty) {
        zzuj();
        zzwk();
        zzbp.zzu(conditionalUserProperty);
        zzbp.zzgg(conditionalUserProperty.mName);
        zzbp.zzgg(conditionalUserProperty.mOrigin);
        zzbp.zzu(conditionalUserProperty.mValue);
        if (this.zziki.isEnabled()) {
            zzcft com_google_android_gms_internal_zzcft = new zzcft(conditionalUserProperty.mName, conditionalUserProperty.mTriggeredTimestamp, conditionalUserProperty.mValue, conditionalUserProperty.mOrigin);
            try {
                zzcbk zza = zzauh().zza(conditionalUserProperty.mTriggeredEventName, conditionalUserProperty.mTriggeredEventParams, conditionalUserProperty.mOrigin, 0, true, false);
                zzauc().zzf(new zzcav(conditionalUserProperty.mAppId, conditionalUserProperty.mOrigin, com_google_android_gms_internal_zzcft, conditionalUserProperty.mCreationTimestamp, false, conditionalUserProperty.mTriggerEventName, zzauh().zza(conditionalUserProperty.mTimedOutEventName, conditionalUserProperty.mTimedOutEventParams, conditionalUserProperty.mOrigin, 0, true, false), conditionalUserProperty.mTriggerTimeout, zza, conditionalUserProperty.mTimeToLive, zzauh().zza(conditionalUserProperty.mExpiredEventName, conditionalUserProperty.mExpiredEventParams, conditionalUserProperty.mOrigin, 0, true, false)));
                return;
            } catch (IllegalArgumentException e) {
                return;
            }
        }
        zzaul().zzayi().log("Conditional property not sent since Firebase Analytics is disabled");
    }

    private final void zzb(String str, String str2, long j, Bundle bundle, boolean z, boolean z2, boolean z3, String str3) {
        zzbp.zzgg(str);
        zzbp.zzgg(str2);
        zzbp.zzu(bundle);
        zzuj();
        zzwk();
        if (this.zziki.isEnabled()) {
            if (!this.zziuw) {
                this.zziuw = true;
                try {
                    try {
                        Class.forName("com.google.android.gms.tagmanager.TagManagerService").getDeclaredMethod("initialize", new Class[]{Context.class}).invoke(null, new Object[]{getContext()});
                    } catch (Exception e) {
                        zzaul().zzayf().zzj("Failed to invoke Tag Manager's initialize() method", e);
                    }
                } catch (ClassNotFoundException e2) {
                    zzaul().zzayh().log("Tag Manager is not found and thus will not be used");
                }
            }
            boolean equals = "am".equals(str);
            boolean zzkd = zzcfw.zzkd(str2);
            if (z && this.zziuu != null && !zzkd && !equals) {
                zzaul().zzayi().zze("Passing event to registered event handler (FE)", zzaug().zzjc(str2), zzaug().zzx(bundle));
                this.zziuu.interceptEvent(str, str2, bundle, j);
                return;
            } else if (this.zziki.zzayv()) {
                int zzjw = zzauh().zzjw(str2);
                if (zzjw != 0) {
                    zzauh();
                    this.zziki.zzauh().zza(str3, zzjw, "_ev", zzcfw.zza(str2, zzcax.zzavn(), true), str2 != null ? str2.length() : 0);
                    return;
                }
                int i;
                Bundle zza;
                List singletonList = Collections.singletonList("_o");
                Bundle zza2 = zzauh().zza(str2, bundle, singletonList, z3, true);
                List arrayList = new ArrayList();
                arrayList.add(zza2);
                long nextLong = zzauh().zzazy().nextLong();
                int i2 = 0;
                String[] strArr = (String[]) zza2.keySet().toArray(new String[bundle.size()]);
                Arrays.sort(strArr);
                int length = strArr.length;
                int i3 = 0;
                while (i3 < length) {
                    int length2;
                    String str4 = strArr[i3];
                    Object obj = zza2.get(str4);
                    zzauh();
                    Bundle[] zzac = zzcfw.zzac(obj);
                    if (zzac != null) {
                        zza2.putInt(str4, zzac.length);
                        for (i = 0; i < zzac.length; i++) {
                            zza = zzauh().zza("_ep", zzac[i], singletonList, z3, false);
                            zza.putString("_en", str2);
                            zza.putLong("_eid", nextLong);
                            zza.putString("_gn", str4);
                            zza.putInt("_ll", zzac.length);
                            zza.putInt("_i", i);
                            arrayList.add(zza);
                        }
                        length2 = zzac.length + i2;
                    } else {
                        length2 = i2;
                    }
                    i3++;
                    i2 = length2;
                }
                if (i2 != 0) {
                    zza2.putLong("_eid", nextLong);
                    zza2.putInt("_epc", i2);
                }
                zzcax.zzawk();
                zzb zzazn = zzaud().zzazn();
                if (!(zzazn == null || zza2.containsKey("_sc"))) {
                    zzazn.zzivx = true;
                }
                i = 0;
                while (i < arrayList.size()) {
                    zza = (Bundle) arrayList.get(i);
                    String str5 = (i != 0 ? 1 : null) != null ? "_ep" : str2;
                    zza.putString("_o", str);
                    if (!zza.containsKey("_sc")) {
                        zzcek.zza(zzazn, zza);
                    }
                    Bundle zzy = z2 ? zzauh().zzy(zza) : zza;
                    zzaul().zzayi().zze("Logging event (FE)", zzaug().zzjc(str2), zzaug().zzx(zzy));
                    zzauc().zzc(new zzcbk(str5, new zzcbh(zzy), str, j), str3);
                    if (!equals) {
                        for (OnEventListener onEvent : this.zziuv) {
                            onEvent.onEvent(str, str2, new Bundle(zzy), j);
                        }
                    }
                    i++;
                }
                zzcax.zzawk();
                if (zzaud().zzazn() != null && "_ae".equals(str2)) {
                    zzauj().zzbs(true);
                    return;
                }
                return;
            } else {
                return;
            }
        }
        zzaul().zzayi().log("Event not sent since app measurement is disabled");
    }

    private final void zzbp(boolean z) {
        zzuj();
        zzatv();
        zzwk();
        zzaul().zzayi().zzj("Setting app measurement enabled (FE)", Boolean.valueOf(z));
        zzaum().setMeasurementEnabled(z);
        zzauc().zzazp();
    }

    private final void zzc(ConditionalUserProperty conditionalUserProperty) {
        zzuj();
        zzwk();
        zzbp.zzu(conditionalUserProperty);
        zzbp.zzgg(conditionalUserProperty.mName);
        if (this.zziki.isEnabled()) {
            zzcft com_google_android_gms_internal_zzcft = new zzcft(conditionalUserProperty.mName, 0, null, null);
            try {
                zzauc().zzf(new zzcav(conditionalUserProperty.mAppId, conditionalUserProperty.mOrigin, com_google_android_gms_internal_zzcft, conditionalUserProperty.mCreationTimestamp, conditionalUserProperty.mActive, conditionalUserProperty.mTriggerEventName, null, conditionalUserProperty.mTriggerTimeout, null, conditionalUserProperty.mTimeToLive, zzauh().zza(conditionalUserProperty.mExpiredEventName, conditionalUserProperty.mExpiredEventParams, conditionalUserProperty.mOrigin, conditionalUserProperty.mCreationTimestamp, true, false)));
                return;
            } catch (IllegalArgumentException e) {
                return;
            }
        }
        zzaul().zzayi().log("Conditional property not cleared since Firebase Analytics is disabled");
    }

    private final List<ConditionalUserProperty> zzk(String str, String str2, String str3) {
        if (zzauk().zzays()) {
            zzaul().zzayd().log("Cannot get conditional user properties from analytics worker thread");
            return Collections.emptyList();
        }
        zzauk();
        if (zzccr.zzaq()) {
            zzaul().zzayd().log("Cannot get conditional user properties from main thread");
            return Collections.emptyList();
        }
        AtomicReference atomicReference = new AtomicReference();
        synchronized (atomicReference) {
            this.zziki.zzauk().zzg(new zzcea(this, atomicReference, str, str2, str3));
            try {
                atomicReference.wait(5000);
            } catch (InterruptedException e) {
                zzaul().zzayf().zze("Interrupted waiting for get conditional user properties", str, e);
            }
        }
        List<zzcav> list = (List) atomicReference.get();
        if (list == null) {
            zzaul().zzayf().zzj("Timed out waiting for get conditional user properties", str);
            return Collections.emptyList();
        }
        List<ConditionalUserProperty> arrayList = new ArrayList(list.size());
        for (zzcav com_google_android_gms_internal_zzcav : list) {
            ConditionalUserProperty conditionalUserProperty = new ConditionalUserProperty();
            conditionalUserProperty.mAppId = str;
            conditionalUserProperty.mOrigin = str2;
            conditionalUserProperty.mCreationTimestamp = com_google_android_gms_internal_zzcav.zzimi;
            conditionalUserProperty.mName = com_google_android_gms_internal_zzcav.zzimh.name;
            conditionalUserProperty.mValue = com_google_android_gms_internal_zzcav.zzimh.getValue();
            conditionalUserProperty.mActive = com_google_android_gms_internal_zzcav.zzimj;
            conditionalUserProperty.mTriggerEventName = com_google_android_gms_internal_zzcav.zzimk;
            if (com_google_android_gms_internal_zzcav.zziml != null) {
                conditionalUserProperty.mTimedOutEventName = com_google_android_gms_internal_zzcav.zziml.name;
                if (com_google_android_gms_internal_zzcav.zziml.zzinr != null) {
                    conditionalUserProperty.mTimedOutEventParams = com_google_android_gms_internal_zzcav.zziml.zzinr.zzaxz();
                }
            }
            conditionalUserProperty.mTriggerTimeout = com_google_android_gms_internal_zzcav.zzimm;
            if (com_google_android_gms_internal_zzcav.zzimn != null) {
                conditionalUserProperty.mTriggeredEventName = com_google_android_gms_internal_zzcav.zzimn.name;
                if (com_google_android_gms_internal_zzcav.zzimn.zzinr != null) {
                    conditionalUserProperty.mTriggeredEventParams = com_google_android_gms_internal_zzcav.zzimn.zzinr.zzaxz();
                }
            }
            conditionalUserProperty.mTriggeredTimestamp = com_google_android_gms_internal_zzcav.zzimh.zziwz;
            conditionalUserProperty.mTimeToLive = com_google_android_gms_internal_zzcav.zzimo;
            if (com_google_android_gms_internal_zzcav.zzimp != null) {
                conditionalUserProperty.mExpiredEventName = com_google_android_gms_internal_zzcav.zzimp.name;
                if (com_google_android_gms_internal_zzcav.zzimp.zzinr != null) {
                    conditionalUserProperty.mExpiredEventParams = com_google_android_gms_internal_zzcav.zzimp.zzinr.zzaxz();
                }
            }
            arrayList.add(conditionalUserProperty);
        }
        return arrayList;
    }

    public final void clearConditionalUserProperty(String str, String str2, Bundle bundle) {
        zzatv();
        zza(null, str, str2, bundle);
    }

    public final void clearConditionalUserPropertyAs(String str, String str2, String str3, Bundle bundle) {
        zzbp.zzgg(str);
        zzatu();
        zza(str, str2, str3, bundle);
    }

    public final Task<String> getAppInstanceId() {
        try {
            String zzayn = zzaum().zzayn();
            return zzayn != null ? Tasks.forResult(zzayn) : Tasks.call(zzauk().zzayt(), new zzceh(this));
        } catch (Exception e) {
            zzaul().zzayf().log("Failed to schedule task for getAppInstanceId");
            return Tasks.forException(e);
        }
    }

    public final List<ConditionalUserProperty> getConditionalUserProperties(String str, String str2) {
        zzatv();
        return zzk(null, str, str2);
    }

    public final List<ConditionalUserProperty> getConditionalUserPropertiesAs(String str, String str2, String str3) {
        zzbp.zzgg(str);
        zzatu();
        return zzk(str, str2, str3);
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    public final Map<String, Object> getUserProperties(String str, String str2, boolean z) {
        zzatv();
        return zzb(null, str, str2, z);
    }

    public final Map<String, Object> getUserPropertiesAs(String str, String str2, String str3, boolean z) {
        zzbp.zzgg(str);
        zzatu();
        return zzb(str, str2, str3, z);
    }

    public final void registerOnMeasurementEventListener(OnEventListener onEventListener) {
        zzatv();
        zzwk();
        zzbp.zzu(onEventListener);
        if (!this.zziuv.add(onEventListener)) {
            zzaul().zzayf().log("OnEventListener already registered");
        }
    }

    public final void setConditionalUserProperty(ConditionalUserProperty conditionalUserProperty) {
        zzbp.zzu(conditionalUserProperty);
        zzatv();
        ConditionalUserProperty conditionalUserProperty2 = new ConditionalUserProperty(conditionalUserProperty);
        if (!TextUtils.isEmpty(conditionalUserProperty2.mAppId)) {
            zzaul().zzayf().log("Package name should be null when calling setConditionalUserProperty");
        }
        conditionalUserProperty2.mAppId = null;
        zza(conditionalUserProperty2);
    }

    public final void setConditionalUserPropertyAs(ConditionalUserProperty conditionalUserProperty) {
        zzbp.zzu(conditionalUserProperty);
        zzbp.zzgg(conditionalUserProperty.mAppId);
        zzatu();
        zza(new ConditionalUserProperty(conditionalUserProperty));
    }

    public final void setEventInterceptor(EventInterceptor eventInterceptor) {
        zzuj();
        zzatv();
        zzwk();
        if (!(eventInterceptor == null || eventInterceptor == this.zziuu)) {
            zzbp.zza(this.zziuu == null, "EventInterceptor already set.");
        }
        this.zziuu = eventInterceptor;
    }

    public final void setMeasurementEnabled(boolean z) {
        zzwk();
        zzatv();
        zzauk().zzg(new zzcdx(this, z));
    }

    public final void setMinimumSessionDuration(long j) {
        zzatv();
        zzauk().zzg(new zzcec(this, j));
    }

    public final void setSessionTimeoutDuration(long j) {
        zzatv();
        zzauk().zzg(new zzced(this, j));
    }

    public final void unregisterOnMeasurementEventListener(OnEventListener onEventListener) {
        zzatv();
        zzwk();
        zzbp.zzu(onEventListener);
        if (!this.zziuv.remove(onEventListener)) {
            zzaul().zzayf().log("OnEventListener had not been registered");
        }
    }

    public final void zza(String str, String str2, Bundle bundle, long j) {
        zzatv();
        zza(str, str2, j, bundle, false, true, true, null);
    }

    public final void zza(String str, String str2, Bundle bundle, boolean z) {
        zzatv();
        boolean z2 = this.zziuu == null || zzcfw.zzkd(str2);
        zza(str, str2, bundle, true, z2, true, null);
    }

    public final /* bridge */ /* synthetic */ void zzatu() {
        super.zzatu();
    }

    public final /* bridge */ /* synthetic */ void zzatv() {
        super.zzatv();
    }

    public final /* bridge */ /* synthetic */ void zzatw() {
        super.zzatw();
    }

    public final /* bridge */ /* synthetic */ zzcan zzatx() {
        return super.zzatx();
    }

    public final /* bridge */ /* synthetic */ zzcau zzaty() {
        return super.zzaty();
    }

    public final /* bridge */ /* synthetic */ zzcdw zzatz() {
        return super.zzatz();
    }

    public final /* bridge */ /* synthetic */ zzcbr zzaua() {
        return super.zzaua();
    }

    public final /* bridge */ /* synthetic */ zzcbe zzaub() {
        return super.zzaub();
    }

    public final /* bridge */ /* synthetic */ zzceo zzauc() {
        return super.zzauc();
    }

    public final /* bridge */ /* synthetic */ zzcek zzaud() {
        return super.zzaud();
    }

    public final /* bridge */ /* synthetic */ zzcbs zzaue() {
        return super.zzaue();
    }

    public final /* bridge */ /* synthetic */ zzcay zzauf() {
        return super.zzauf();
    }

    public final /* bridge */ /* synthetic */ zzcbu zzaug() {
        return super.zzaug();
    }

    public final /* bridge */ /* synthetic */ zzcfw zzauh() {
        return super.zzauh();
    }

    public final /* bridge */ /* synthetic */ zzccq zzaui() {
        return super.zzaui();
    }

    public final /* bridge */ /* synthetic */ zzcfl zzauj() {
        return super.zzauj();
    }

    public final /* bridge */ /* synthetic */ zzccr zzauk() {
        return super.zzauk();
    }

    public final /* bridge */ /* synthetic */ zzcbw zzaul() {
        return super.zzaul();
    }

    public final /* bridge */ /* synthetic */ zzcch zzaum() {
        return super.zzaum();
    }

    public final /* bridge */ /* synthetic */ zzcax zzaun() {
        return super.zzaun();
    }

    public final String zzayn() {
        zzatv();
        return (String) this.zziux.get();
    }

    public final void zzb(String str, String str2, Object obj) {
        int i = 0;
        zzbp.zzgg(str);
        long currentTimeMillis = zzvx().currentTimeMillis();
        int zzjy = zzauh().zzjy(str2);
        String zza;
        if (zzjy != 0) {
            zzauh();
            zza = zzcfw.zza(str2, zzcax.zzavo(), true);
            if (str2 != null) {
                i = str2.length();
            }
            this.zziki.zzauh().zza(zzjy, "_ev", zza, i);
        } else if (obj != null) {
            zzjy = zzauh().zzl(str2, obj);
            if (zzjy != 0) {
                zzauh();
                zza = zzcfw.zza(str2, zzcax.zzavo(), true);
                if ((obj instanceof String) || (obj instanceof CharSequence)) {
                    i = String.valueOf(obj).length();
                }
                this.zziki.zzauh().zza(zzjy, "_ev", zza, i);
                return;
            }
            Object zzm = zzauh().zzm(str2, obj);
            if (zzm != null) {
                zza(str, str2, currentTimeMillis, zzm);
            }
        } else {
            zza(str, str2, currentTimeMillis, null);
        }
    }

    final String zzbc(long j) {
        AtomicReference atomicReference = new AtomicReference();
        synchronized (atomicReference) {
            zzauk().zzg(new zzcei(this, atomicReference));
            try {
                atomicReference.wait(j);
            } catch (InterruptedException e) {
                zzaul().zzayf().log("Interrupted waiting for app instance id");
                return null;
            }
        }
        return (String) atomicReference.get();
    }

    public final List<zzcft> zzbq(boolean z) {
        zzatv();
        zzwk();
        zzaul().zzayi().log("Fetching user attributes (FE)");
        if (zzauk().zzays()) {
            zzaul().zzayd().log("Cannot get all user properties from analytics worker thread");
            return Collections.emptyList();
        }
        zzauk();
        if (zzccr.zzaq()) {
            zzaul().zzayd().log("Cannot get all user properties from main thread");
            return Collections.emptyList();
        }
        AtomicReference atomicReference = new AtomicReference();
        synchronized (atomicReference) {
            this.zziki.zzauk().zzg(new zzceg(this, atomicReference, z));
            try {
                atomicReference.wait(5000);
            } catch (InterruptedException e) {
                zzaul().zzayf().zzj("Interrupted waiting for get user properties", e);
            }
        }
        List<zzcft> list = (List) atomicReference.get();
        if (list != null) {
            return list;
        }
        zzaul().zzayf().log("Timed out waiting for get user properties");
        return Collections.emptyList();
    }

    public final void zzc(String str, String str2, Bundle bundle) {
        zzatv();
        boolean z = this.zziuu == null || zzcfw.zzkd(str2);
        zza(str, str2, bundle, true, z, false, null);
    }

    final void zzjk(String str) {
        this.zziux.set(str);
    }

    public final /* bridge */ /* synthetic */ void zzuj() {
        super.zzuj();
    }

    protected final void zzuk() {
    }

    public final /* bridge */ /* synthetic */ zzd zzvx() {
        return super.zzvx();
    }
}
