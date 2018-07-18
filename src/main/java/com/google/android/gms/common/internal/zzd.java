package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.zzc;
import com.google.android.gms.common.zze;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class zzd<T extends IInterface> {
    private static String[] zzftj = new String[]{"service_esmobile", "service_googleme"};
    private final Context mContext;
    final Handler mHandler;
    private final Object mLock;
    private final Looper zzakg;
    private final zze zzfko;
    private int zzfso;
    private long zzfsp;
    private long zzfsq;
    private int zzfsr;
    private long zzfss;
    private zzal zzfst;
    private final zzaf zzfsu;
    private final Object zzfsv;
    private zzax zzfsw;
    protected zzj zzfsx;
    private T zzfsy;
    private final ArrayList<zzi<?>> zzfsz;
    private zzl zzfta;
    private int zzftb;
    private final zzf zzftc;
    private final zzg zzftd;
    private final int zzfte;
    private final String zzftf;
    private ConnectionResult zzftg;
    private boolean zzfth;
    protected AtomicInteger zzfti;

    protected zzd(Context context, Looper looper, int i, zzf com_google_android_gms_common_internal_zzf, zzg com_google_android_gms_common_internal_zzg, String str) {
        this(context, looper, zzaf.zzce(context), zze.zzaex(), i, (zzf) zzbp.zzu(com_google_android_gms_common_internal_zzf), (zzg) zzbp.zzu(com_google_android_gms_common_internal_zzg), null);
    }

    protected zzd(Context context, Looper looper, zzaf com_google_android_gms_common_internal_zzaf, zze com_google_android_gms_common_zze, int i, zzf com_google_android_gms_common_internal_zzf, zzg com_google_android_gms_common_internal_zzg, String str) {
        this.mLock = new Object();
        this.zzfsv = new Object();
        this.zzfsz = new ArrayList();
        this.zzftb = 1;
        this.zzftg = null;
        this.zzfth = false;
        this.zzfti = new AtomicInteger(0);
        this.mContext = (Context) zzbp.zzb((Object) context, (Object) "Context must not be null");
        this.zzakg = (Looper) zzbp.zzb((Object) looper, (Object) "Looper must not be null");
        this.zzfsu = (zzaf) zzbp.zzb((Object) com_google_android_gms_common_internal_zzaf, (Object) "Supervisor must not be null");
        this.zzfko = (zze) zzbp.zzb((Object) com_google_android_gms_common_zze, (Object) "API availability must not be null");
        this.mHandler = new zzh(this, looper);
        this.zzfte = i;
        this.zzftc = com_google_android_gms_common_internal_zzf;
        this.zzftd = com_google_android_gms_common_internal_zzg;
        this.zzftf = str;
    }

    private final void zza(int i, T t) {
        boolean z = true;
        if ((i == 4) != (t != null)) {
            z = false;
        }
        zzbp.zzbh(z);
        synchronized (this.mLock) {
            this.zzftb = i;
            this.zzfsy = t;
            switch (i) {
                case 1:
                    if (this.zzfta != null) {
                        this.zzfsu.zza(zzhc(), zzajd(), 129, this.zzfta, zzaje());
                        this.zzfta = null;
                        break;
                    }
                    break;
                case 2:
                case 3:
                    String zzakk;
                    String packageName;
                    if (!(this.zzfta == null || this.zzfst == null)) {
                        zzakk = this.zzfst.zzakk();
                        packageName = this.zzfst.getPackageName();
                        Log.e("GmsClient", new StringBuilder((String.valueOf(zzakk).length() + 70) + String.valueOf(packageName).length()).append("Calling connect() while still connected, missing disconnect() for ").append(zzakk).append(" on ").append(packageName).toString());
                        this.zzfsu.zza(this.zzfst.zzakk(), this.zzfst.getPackageName(), this.zzfst.zzakg(), this.zzfta, zzaje());
                        this.zzfti.incrementAndGet();
                    }
                    this.zzfta = new zzl(this, this.zzfti.get());
                    this.zzfst = new zzal(zzajd(), zzhc(), false, 129);
                    if (!this.zzfsu.zza(new zzag(this.zzfst.zzakk(), this.zzfst.getPackageName(), this.zzfst.zzakg()), this.zzfta, zzaje())) {
                        zzakk = this.zzfst.zzakk();
                        packageName = this.zzfst.getPackageName();
                        Log.e("GmsClient", new StringBuilder((String.valueOf(zzakk).length() + 34) + String.valueOf(packageName).length()).append("unable to connect to service: ").append(zzakk).append(" on ").append(packageName).toString());
                        zza(16, null, this.zzfti.get());
                        break;
                    }
                    break;
                case 4:
                    zza((IInterface) t);
                    break;
            }
        }
    }

    private final boolean zza(int i, int i2, T t) {
        boolean z;
        synchronized (this.mLock) {
            if (this.zzftb != i) {
                z = false;
            } else {
                zza(i2, (IInterface) t);
                z = true;
            }
        }
        return z;
    }

    private final String zzaje() {
        return this.zzftf == null ? this.mContext.getClass().getName() : this.zzftf;
    }

    private final boolean zzajg() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzftb == 3;
        }
        return z;
    }

    private final boolean zzajm() {
        if (this.zzfth || TextUtils.isEmpty(zzhd()) || TextUtils.isEmpty(null)) {
            return false;
        }
        try {
            Class.forName(zzhd());
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    private final void zzcd(int i) {
        int i2;
        if (zzajg()) {
            i2 = 5;
            this.zzfth = true;
        } else {
            i2 = 4;
        }
        this.mHandler.sendMessage(this.mHandler.obtainMessage(i2, this.zzfti.get(), 16));
    }

    public void disconnect() {
        this.zzfti.incrementAndGet();
        synchronized (this.zzfsz) {
            int size = this.zzfsz.size();
            for (int i = 0; i < size; i++) {
                ((zzi) this.zzfsz.get(i)).removeListener();
            }
            this.zzfsz.clear();
        }
        synchronized (this.zzfsv) {
            this.zzfsw = null;
        }
        zza(1, null);
    }

    public final void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        synchronized (this.mLock) {
            int i = this.zzftb;
            IInterface iInterface = this.zzfsy;
        }
        synchronized (this.zzfsv) {
            zzax com_google_android_gms_common_internal_zzax = this.zzfsw;
        }
        printWriter.append(str).append("mConnectState=");
        switch (i) {
            case 1:
                printWriter.print("DISCONNECTED");
                break;
            case 2:
                printWriter.print("REMOTE_CONNECTING");
                break;
            case 3:
                printWriter.print("LOCAL_CONNECTING");
                break;
            case 4:
                printWriter.print("CONNECTED");
                break;
            case 5:
                printWriter.print("DISCONNECTING");
                break;
            default:
                printWriter.print("UNKNOWN");
                break;
        }
        printWriter.append(" mService=");
        if (iInterface == null) {
            printWriter.append("null");
        } else {
            printWriter.append(zzhd()).append("@").append(Integer.toHexString(System.identityHashCode(iInterface.asBinder())));
        }
        printWriter.append(" mServiceBroker=");
        if (com_google_android_gms_common_internal_zzax == null) {
            printWriter.println("null");
        } else {
            printWriter.append("IGmsServiceBroker@").println(Integer.toHexString(System.identityHashCode(com_google_android_gms_common_internal_zzax.asBinder())));
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.US);
        if (this.zzfsq > 0) {
            PrintWriter append = printWriter.append(str).append("lastConnectedTime=");
            long j = this.zzfsq;
            String format = simpleDateFormat.format(new Date(this.zzfsq));
            append.println(new StringBuilder(String.valueOf(format).length() + 21).append(j).append(" ").append(format).toString());
        }
        if (this.zzfsp > 0) {
            printWriter.append(str).append("lastSuspendedCause=");
            switch (this.zzfso) {
                case 1:
                    printWriter.append("CAUSE_SERVICE_DISCONNECTED");
                    break;
                case 2:
                    printWriter.append("CAUSE_NETWORK_LOST");
                    break;
                default:
                    printWriter.append(String.valueOf(this.zzfso));
                    break;
            }
            append = printWriter.append(" lastSuspendedTime=");
            j = this.zzfsp;
            format = simpleDateFormat.format(new Date(this.zzfsp));
            append.println(new StringBuilder(String.valueOf(format).length() + 21).append(j).append(" ").append(format).toString());
        }
        if (this.zzfss > 0) {
            printWriter.append(str).append("lastFailedStatus=").append(CommonStatusCodes.getStatusCodeString(this.zzfsr));
            append = printWriter.append(" lastFailedTime=");
            j = this.zzfss;
            String format2 = simpleDateFormat.format(new Date(this.zzfss));
            append.println(new StringBuilder(String.valueOf(format2).length() + 21).append(j).append(" ").append(format2).toString());
        }
    }

    public Account getAccount() {
        return null;
    }

    public final Context getContext() {
        return this.mContext;
    }

    public final boolean isConnected() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzftb == 4;
        }
        return z;
    }

    public final boolean isConnecting() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzftb == 2 || this.zzftb == 3;
        }
        return z;
    }

    protected void onConnectionFailed(ConnectionResult connectionResult) {
        this.zzfsr = connectionResult.getErrorCode();
        this.zzfss = System.currentTimeMillis();
    }

    protected final void onConnectionSuspended(int i) {
        this.zzfso = i;
        this.zzfsp = System.currentTimeMillis();
    }

    protected final void zza(int i, Bundle bundle, int i2) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(7, i2, -1, new zzo(this, i, null)));
    }

    protected void zza(int i, IBinder iBinder, Bundle bundle, int i2) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(1, i2, -1, new zzn(this, i, iBinder, bundle)));
    }

    protected void zza(T t) {
        this.zzfsq = System.currentTimeMillis();
    }

    public final void zza(zzam com_google_android_gms_common_internal_zzam, Set<Scope> set) {
        Throwable e;
        Bundle zzzu = zzzu();
        zzy com_google_android_gms_common_internal_zzy = new zzy(this.zzfte);
        com_google_android_gms_common_internal_zzy.zzfue = this.mContext.getPackageName();
        com_google_android_gms_common_internal_zzy.zzfuh = zzzu;
        if (set != null) {
            com_google_android_gms_common_internal_zzy.zzfug = (Scope[]) set.toArray(new Scope[set.size()]);
        }
        if (zzaac()) {
            com_google_android_gms_common_internal_zzy.zzfui = getAccount() != null ? getAccount() : new Account("<<default account>>", "com.google");
            if (com_google_android_gms_common_internal_zzam != null) {
                com_google_android_gms_common_internal_zzy.zzfuf = com_google_android_gms_common_internal_zzam.asBinder();
            }
        } else if (zzajk()) {
            com_google_android_gms_common_internal_zzy.zzfui = getAccount();
        }
        com_google_android_gms_common_internal_zzy.zzfuj = zzajh();
        try {
            synchronized (this.zzfsv) {
                if (this.zzfsw != null) {
                    this.zzfsw.zza(new zzk(this, this.zzfti.get()), com_google_android_gms_common_internal_zzy);
                } else {
                    Log.w("GmsClient", "mServiceBroker is null, client disconnected");
                }
            }
        } catch (Throwable e2) {
            Log.w("GmsClient", "IGmsServiceBroker.getService failed", e2);
            zzcc(1);
        } catch (SecurityException e3) {
            throw e3;
        } catch (RemoteException e4) {
            e2 = e4;
            Log.w("GmsClient", "IGmsServiceBroker.getService failed", e2);
            zza(8, null, null, this.zzfti.get());
        } catch (RuntimeException e5) {
            e2 = e5;
            Log.w("GmsClient", "IGmsServiceBroker.getService failed", e2);
            zza(8, null, null, this.zzfti.get());
        }
    }

    public void zza(zzj com_google_android_gms_common_internal_zzj) {
        this.zzfsx = (zzj) zzbp.zzb((Object) com_google_android_gms_common_internal_zzj, (Object) "Connection progress callbacks cannot be null.");
        zza(2, null);
    }

    protected final void zza(zzj com_google_android_gms_common_internal_zzj, int i, PendingIntent pendingIntent) {
        this.zzfsx = (zzj) zzbp.zzb((Object) com_google_android_gms_common_internal_zzj, (Object) "Connection progress callbacks cannot be null.");
        this.mHandler.sendMessage(this.mHandler.obtainMessage(3, this.zzfti.get(), i, pendingIntent));
    }

    public boolean zzaac() {
        return false;
    }

    public boolean zzaal() {
        return false;
    }

    public Intent zzaam() {
        throw new UnsupportedOperationException("Not a sign in API");
    }

    public Bundle zzaeh() {
        return null;
    }

    public boolean zzaff() {
        return true;
    }

    public final IBinder zzafg() {
        IBinder iBinder;
        synchronized (this.zzfsv) {
            if (this.zzfsw == null) {
                iBinder = null;
            } else {
                iBinder = this.zzfsw.asBinder();
            }
        }
        return iBinder;
    }

    protected String zzajd() {
        return "com.google.android.gms";
    }

    public final void zzajf() {
        int isGooglePlayServicesAvailable = this.zzfko.isGooglePlayServicesAvailable(this.mContext);
        if (isGooglePlayServicesAvailable != 0) {
            zza(1, null);
            zza(new zzm(this), isGooglePlayServicesAvailable, null);
            return;
        }
        zza(new zzm(this));
    }

    public zzc[] zzajh() {
        return new zzc[0];
    }

    protected final void zzaji() {
        if (!isConnected()) {
            throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
        }
    }

    public final T zzajj() throws DeadObjectException {
        T t;
        synchronized (this.mLock) {
            if (this.zzftb == 5) {
                throw new DeadObjectException();
            }
            zzaji();
            zzbp.zza(this.zzfsy != null, "Client is connected but service is null");
            t = this.zzfsy;
        }
        return t;
    }

    public boolean zzajk() {
        return false;
    }

    protected Set<Scope> zzajl() {
        return Collections.EMPTY_SET;
    }

    public final void zzcc(int i) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(6, this.zzfti.get(), i));
    }

    protected abstract T zzd(IBinder iBinder);

    protected abstract String zzhc();

    protected abstract String zzhd();

    protected Bundle zzzu() {
        return new Bundle();
    }
}
