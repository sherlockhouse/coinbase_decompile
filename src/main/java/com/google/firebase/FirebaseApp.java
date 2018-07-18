package com.google.firebase;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import com.google.android.gms.common.api.internal.zzk;
import com.google.android.gms.common.internal.zzbf;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.common.util.zzr;
import com.google.android.gms.internal.zzeks;
import com.google.android.gms.internal.zzekt;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class FirebaseApp {
    private static final Object zzaqd = new Object();
    static final Map<String, FirebaseApp> zzhtn = new ArrayMap();
    private static final List<String> zzlid = Arrays.asList(new String[]{"com.google.firebase.auth.FirebaseAuth", "com.google.firebase.iid.FirebaseInstanceId"});
    private static final List<String> zzlie = Collections.singletonList("com.google.firebase.crash.FirebaseCrash");
    private static final List<String> zzlif = Arrays.asList(new String[]{"com.google.android.gms.measurement.AppMeasurement"});
    private static final List<String> zzlig = Arrays.asList(new String[0]);
    private static final Set<String> zzlih = Collections.emptySet();
    private final Context mApplicationContext;
    private final String mName;
    private final FirebaseOptions zzlii;
    private final AtomicBoolean zzlij = new AtomicBoolean(false);
    private final AtomicBoolean zzlik = new AtomicBoolean();
    private final List<Object> zzlil = new CopyOnWriteArrayList();
    private final List<zza> zzlim = new CopyOnWriteArrayList();
    private final List<Object> zzlin = new CopyOnWriteArrayList();
    private zzc zzlip;

    public interface zzc {
    }

    public interface zza {
        void zzbe(boolean z);
    }

    @TargetApi(24)
    static class zzd extends BroadcastReceiver {
        private static AtomicReference<zzd> zzliq = new AtomicReference();
        private final Context mApplicationContext;

        private zzd(Context context) {
            this.mApplicationContext = context;
        }

        private static void zzef(Context context) {
            if (zzliq.get() == null) {
                BroadcastReceiver com_google_firebase_FirebaseApp_zzd = new zzd(context);
                if (zzliq.compareAndSet(null, com_google_firebase_FirebaseApp_zzd)) {
                    context.registerReceiver(com_google_firebase_FirebaseApp_zzd, new IntentFilter("android.intent.action.USER_UNLOCKED"));
                }
            }
        }

        public final void onReceive(Context context, Intent intent) {
            synchronized (FirebaseApp.zzaqd) {
                for (FirebaseApp zza : FirebaseApp.zzhtn.values()) {
                    zza.zzbnz();
                }
            }
            this.mApplicationContext.unregisterReceiver(this);
        }
    }

    private FirebaseApp(Context context, String str, FirebaseOptions firebaseOptions) {
        this.mApplicationContext = (Context) zzbp.zzu(context);
        this.mName = zzbp.zzgg(str);
        this.zzlii = (FirebaseOptions) zzbp.zzu(firebaseOptions);
        this.zzlip = new zzeks();
    }

    public static FirebaseApp getInstance() {
        FirebaseApp firebaseApp;
        synchronized (zzaqd) {
            firebaseApp = (FirebaseApp) zzhtn.get("[DEFAULT]");
            if (firebaseApp == null) {
                String zzalk = zzr.zzalk();
                throw new IllegalStateException(new StringBuilder(String.valueOf(zzalk).length() + 116).append("Default FirebaseApp is not initialized in this process ").append(zzalk).append(". Make sure to call FirebaseApp.initializeApp(Context) first.").toString());
            }
        }
        return firebaseApp;
    }

    public static FirebaseApp initializeApp(Context context) {
        FirebaseApp instance;
        synchronized (zzaqd) {
            if (zzhtn.containsKey("[DEFAULT]")) {
                instance = getInstance();
            } else {
                FirebaseOptions fromResource = FirebaseOptions.fromResource(context);
                if (fromResource == null) {
                    instance = null;
                } else {
                    instance = initializeApp(context, fromResource);
                }
            }
        }
        return instance;
    }

    public static FirebaseApp initializeApp(Context context, FirebaseOptions firebaseOptions) {
        return initializeApp(context, firebaseOptions, "[DEFAULT]");
    }

    public static FirebaseApp initializeApp(Context context, FirebaseOptions firebaseOptions, String str) {
        FirebaseApp firebaseApp;
        zzekt.zzep(context);
        if (context.getApplicationContext() instanceof Application) {
            zzk.zza((Application) context.getApplicationContext());
            zzk.zzafz().zza(new zza());
        }
        String trim = str.trim();
        if (context.getApplicationContext() != null) {
            Object applicationContext = context.getApplicationContext();
        }
        synchronized (zzaqd) {
            zzbp.zza(!zzhtn.containsKey(trim), new StringBuilder(String.valueOf(trim).length() + 33).append("FirebaseApp name ").append(trim).append(" already exists!").toString());
            zzbp.zzb(applicationContext, (Object) "Application context cannot be null.");
            firebaseApp = new FirebaseApp(applicationContext, trim, firebaseOptions);
            zzhtn.put(trim, firebaseApp);
        }
        zzekt.zzf(firebaseApp);
        firebaseApp.zza(FirebaseApp.class, firebaseApp, zzlid);
        if (firebaseApp.zzbnw()) {
            firebaseApp.zza(FirebaseApp.class, firebaseApp, zzlie);
            firebaseApp.zza(Context.class, firebaseApp.getApplicationContext(), zzlif);
        }
        return firebaseApp;
    }

    private final <T> void zza(Class<T> cls, T t, Iterable<String> iterable) {
        String valueOf;
        boolean isDeviceProtectedStorage = ContextCompat.isDeviceProtectedStorage(this.mApplicationContext);
        if (isDeviceProtectedStorage) {
            zzd.zzef(this.mApplicationContext);
        }
        for (String valueOf2 : iterable) {
            if (isDeviceProtectedStorage) {
                try {
                    if (!zzlig.contains(valueOf2)) {
                    }
                } catch (ClassNotFoundException e) {
                    if (zzlih.contains(valueOf2)) {
                        throw new IllegalStateException(String.valueOf(valueOf2).concat(" is missing, but is required. Check if it has been removed by Proguard."));
                    }
                    Log.d("FirebaseApp", String.valueOf(valueOf2).concat(" is not linked. Skipping initialization."));
                } catch (NoSuchMethodException e2) {
                    throw new IllegalStateException(String.valueOf(valueOf2).concat("#getInstance has been removed by Proguard. Add keep rule to prevent it."));
                } catch (Throwable e3) {
                    Log.wtf("FirebaseApp", "Firebase API initialization failure.", e3);
                } catch (Throwable e4) {
                    String str = "FirebaseApp";
                    String str2 = "Failed to initialize ";
                    valueOf2 = String.valueOf(valueOf2);
                    Log.wtf(str, valueOf2.length() != 0 ? str2.concat(valueOf2) : new String(str2), e4);
                }
            }
            Method method = Class.forName(valueOf2).getMethod("getInstance", new Class[]{cls});
            int modifiers = method.getModifiers();
            if (Modifier.isPublic(modifiers) && Modifier.isStatic(modifiers)) {
                method.invoke(null, new Object[]{t});
            }
        }
    }

    public static void zzbe(boolean z) {
        synchronized (zzaqd) {
            ArrayList arrayList = new ArrayList(zzhtn.values());
            int size = arrayList.size();
            int i = 0;
            while (i < size) {
                Object obj = arrayList.get(i);
                i++;
                FirebaseApp firebaseApp = (FirebaseApp) obj;
                if (firebaseApp.zzlij.get()) {
                    firebaseApp.zzcb(z);
                }
            }
        }
    }

    private final void zzbnv() {
        zzbp.zza(!this.zzlik.get(), "FirebaseApp was deleted");
    }

    private final void zzbnz() {
        zza(FirebaseApp.class, this, zzlid);
        if (zzbnw()) {
            zza(FirebaseApp.class, this, zzlie);
            zza(Context.class, this.mApplicationContext, zzlif);
        }
    }

    private final void zzcb(boolean z) {
        Log.d("FirebaseApp", "Notifying background state change listeners.");
        for (zza zzbe : this.zzlim) {
            zzbe.zzbe(z);
        }
    }

    public boolean equals(Object obj) {
        return !(obj instanceof FirebaseApp) ? false : this.mName.equals(((FirebaseApp) obj).getName());
    }

    public Context getApplicationContext() {
        zzbnv();
        return this.mApplicationContext;
    }

    public String getName() {
        zzbnv();
        return this.mName;
    }

    public FirebaseOptions getOptions() {
        zzbnv();
        return this.zzlii;
    }

    public int hashCode() {
        return this.mName.hashCode();
    }

    public String toString() {
        return zzbf.zzt(this).zzg("name", this.mName).zzg("options", this.zzlii).toString();
    }

    public final boolean zzbnw() {
        return "[DEFAULT]".equals(getName());
    }
}
