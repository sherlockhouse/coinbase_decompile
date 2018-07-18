package com.google.firebase.iid;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcelable;
import android.os.Process;
import android.os.RemoteException;
import android.os.SystemClock;
import android.support.v4.util.SimpleArrayMap;
import android.text.TextUtils;
import android.util.Log;
import com.coinbase.android.utils.CryptoUri;
import com.google.android.gms.common.util.zzq;
import com.google.android.gms.iid.MessengerCompat;
import java.io.IOException;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.util.Random;

public final class zzl {
    private static PendingIntent zzhrm;
    private static String zzhtw = null;
    private static boolean zzhtx = false;
    private static int zzhty = 0;
    private static int zzhtz = 0;
    private static int zzhua = 0;
    private static BroadcastReceiver zzhub = null;
    private Context zzahz;
    private Messenger zzhrq;
    private Messenger zzhud;
    private MessengerCompat zzhue;
    private long zzhuf;
    private long zzhug;
    private int zzhuh;
    private int zzhui;
    private long zzhuj;
    private final SimpleArrayMap<String, zzp> zznfw = new SimpleArrayMap();

    public zzl(Context context) {
        this.zzahz = context;
    }

    private static String zza(KeyPair keyPair, String... strArr) {
        String str = null;
        try {
            byte[] bytes = TextUtils.join("\n", strArr).getBytes("UTF-8");
            try {
                PrivateKey privateKey = keyPair.getPrivate();
                Signature instance = Signature.getInstance(privateKey instanceof RSAPrivateKey ? "SHA256withRSA" : "SHA256withECDSA");
                instance.initSign(privateKey);
                instance.update(bytes);
                str = FirebaseInstanceId.zzn(instance.sign());
            } catch (Throwable e) {
                Log.e("InstanceID/Rpc", "Unable to sign registration request", e);
            }
        } catch (Throwable e2) {
            Log.e("InstanceID/Rpc", "Unable to encode string", e2);
        }
        return str;
    }

    private static boolean zza(PackageManager packageManager) {
        for (ResolveInfo resolveInfo : packageManager.queryBroadcastReceivers(new Intent("com.google.iid.TOKEN_REQUEST"), 0)) {
            if (zza(packageManager, resolveInfo.activityInfo.packageName, "com.google.iid.TOKEN_REQUEST")) {
                zzhtx = true;
                return true;
            }
        }
        return false;
    }

    private static boolean zza(PackageManager packageManager, String str, String str2) {
        if (packageManager.checkPermission("com.google.android.c2dm.permission.SEND", str) == 0) {
            return zzb(packageManager, str);
        }
        Log.w("InstanceID/Rpc", new StringBuilder((String.valueOf(str).length() + 56) + String.valueOf(str2).length()).append("Possible malicious package ").append(str).append(" declares ").append(str2).append(" without permission").toString());
        return false;
    }

    private final void zzast() {
        if (this.zzhrq == null) {
            zzdf(this.zzahz);
            this.zzhrq = new Messenger(new zzm(this, Looper.getMainLooper()));
        }
    }

    public static synchronized String zzasu() {
        String num;
        synchronized (zzl.class) {
            int i = zzhua;
            zzhua = i + 1;
            num = Integer.toString(i);
        }
        return num;
    }

    private final Intent zzb(Bundle bundle, KeyPair keyPair) throws IOException {
        String zzasu = zzasu();
        zzo com_google_firebase_iid_zzo = new zzo();
        synchronized (this.zznfw) {
            this.zznfw.put(zzasu, com_google_firebase_iid_zzo);
        }
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (this.zzhuj == 0 || elapsedRealtime > this.zzhuj) {
            zzast();
            if (zzhtw == null) {
                throw new IOException("MISSING_INSTANCEID_SERVICE");
            }
            this.zzhuf = SystemClock.elapsedRealtime();
            Intent intent = new Intent(zzhtx ? "com.google.iid.TOKEN_REQUEST" : "com.google.android.c2dm.intent.REGISTER");
            intent.setPackage(zzhtw);
            bundle.putString("gmsv", Integer.toString(FirebaseInstanceId.zzao(this.zzahz, zzdf(this.zzahz))));
            bundle.putString("osv", Integer.toString(VERSION.SDK_INT));
            bundle.putString("app_ver", Integer.toString(FirebaseInstanceId.zzej(this.zzahz)));
            bundle.putString("app_ver_name", FirebaseInstanceId.zzdd(this.zzahz));
            bundle.putString("cliv", "fiid-11400000");
            bundle.putString("appid", FirebaseInstanceId.zza(keyPair));
            bundle.putString("pub2", FirebaseInstanceId.zzn(keyPair.getPublic().getEncoded()));
            bundle.putString("sig", zza(keyPair, this.zzahz.getPackageName(), r0));
            intent.putExtras(bundle);
            zzd(this.zzahz, intent);
            this.zzhuf = SystemClock.elapsedRealtime();
            intent.putExtra("kid", new StringBuilder(String.valueOf(zzasu).length() + 5).append("|ID|").append(zzasu).append("|").toString());
            intent.putExtra("X-kid", new StringBuilder(String.valueOf(zzasu).length() + 5).append("|ID|").append(zzasu).append("|").toString());
            boolean equals = "com.google.android.gsf".equals(zzhtw);
            if (Log.isLoggable("InstanceID/Rpc", 3)) {
                String valueOf = String.valueOf(intent.getExtras());
                Log.d("InstanceID/Rpc", new StringBuilder(String.valueOf(valueOf).length() + 8).append("Sending ").append(valueOf).toString());
            }
            if (equals) {
                synchronized (this) {
                    if (zzhub == null) {
                        zzhub = new zzn(this);
                        if (Log.isLoggable("InstanceID/Rpc", 3)) {
                            Log.d("InstanceID/Rpc", "Registered GSF callback receiver");
                        }
                        IntentFilter intentFilter = new IntentFilter("com.google.android.c2dm.intent.REGISTRATION");
                        intentFilter.addCategory(this.zzahz.getPackageName());
                        this.zzahz.registerReceiver(zzhub, intentFilter, "com.google.android.c2dm.permission.SEND", null);
                    }
                }
                this.zzahz.startService(intent);
            } else {
                intent.putExtra("google.messenger", this.zzhrq);
                if (!(this.zzhud == null && this.zzhue == null)) {
                    Message obtain = Message.obtain();
                    obtain.obj = intent;
                    try {
                        if (this.zzhud != null) {
                            this.zzhud.send(obtain);
                        } else {
                            this.zzhue.send(obtain);
                        }
                    } catch (RemoteException e) {
                        if (Log.isLoggable("InstanceID/Rpc", 3)) {
                            Log.d("InstanceID/Rpc", "Messenger failed, fallback to startService");
                        }
                    }
                }
                if (zzhtx) {
                    this.zzahz.sendBroadcast(intent);
                } else {
                    this.zzahz.startService(intent);
                }
            }
            try {
                Intent zzcgd = com_google_firebase_iid_zzo.zzcgd();
                synchronized (this.zznfw) {
                    this.zznfw.remove(zzasu);
                }
                return zzcgd;
            } catch (Throwable th) {
                synchronized (this.zznfw) {
                    this.zznfw.remove(zzasu);
                }
            }
        } else {
            Log.w("InstanceID/Rpc", "Backoff mode, next request attempt: " + (this.zzhuj - elapsedRealtime) + " interval: " + this.zzhui);
            throw new IOException("RETRY_LATER");
        }
    }

    private final void zzb(String str, Intent intent) {
        synchronized (this.zznfw) {
            zzp com_google_firebase_iid_zzp = (zzp) this.zznfw.remove(str);
            if (com_google_firebase_iid_zzp == null) {
                String str2 = "InstanceID/Rpc";
                String str3 = "Missing callback for ";
                String valueOf = String.valueOf(str);
                Log.w(str2, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
                return;
            }
            com_google_firebase_iid_zzp.zzq(intent);
        }
    }

    private static boolean zzb(PackageManager packageManager, String str) {
        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(str, 0);
            zzhtw = applicationInfo.packageName;
            zzhtz = applicationInfo.uid;
            return true;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzbk(String str, String str2) {
        synchronized (this.zznfw) {
            if (str == null) {
                for (int i = 0; i < this.zznfw.size(); i++) {
                    ((zzp) this.zznfw.valueAt(i)).onError(str2);
                }
                this.zznfw.clear();
            } else {
                zzp com_google_firebase_iid_zzp = (zzp) this.zznfw.remove(str);
                if (com_google_firebase_iid_zzp == null) {
                    String str3 = "InstanceID/Rpc";
                    String str4 = "Missing callback for ";
                    String valueOf = String.valueOf(str);
                    Log.w(str3, valueOf.length() != 0 ? str4.concat(valueOf) : new String(str4));
                    return;
                }
                com_google_firebase_iid_zzp.onError(str2);
            }
        }
    }

    public static synchronized void zzd(Context context, Intent intent) {
        synchronized (zzl.class) {
            if (zzhrm == null) {
                Intent intent2 = new Intent();
                intent2.setPackage("com.google.example.invalidpackage");
                zzhrm = PendingIntent.getBroadcast(context, 0, intent2, 0);
            }
            intent.putExtra("app", zzhrm);
        }
    }

    public static String zzdf(Context context) {
        if (zzhtw != null) {
            return zzhtw;
        }
        zzhty = Process.myUid();
        PackageManager packageManager = context.getPackageManager();
        if (!zzq.isAtLeastO()) {
            boolean z;
            for (ResolveInfo resolveInfo : packageManager.queryIntentServices(new Intent("com.google.android.c2dm.intent.REGISTER"), 0)) {
                if (zza(packageManager, resolveInfo.serviceInfo.packageName, "com.google.android.c2dm.intent.REGISTER")) {
                    zzhtx = false;
                    z = true;
                    break;
                }
            }
            z = false;
            if (z) {
                return zzhtw;
            }
        }
        if (zza(packageManager)) {
            return zzhtw;
        }
        Log.w("InstanceID/Rpc", "Failed to resolve IID implementation package, falling back");
        if (zzb(packageManager, "com.google.android.gms")) {
            zzhtx = zzq.isAtLeastO();
            return zzhtw;
        } else if (zzq.zzalj() || !zzb(packageManager, "com.google.android.gsf")) {
            Log.w("InstanceID/Rpc", "Google Play services is missing, unable to get tokens");
            return null;
        } else {
            zzhtx = false;
            return zzhtw;
        }
    }

    final Intent zza(Bundle bundle, KeyPair keyPair) throws IOException {
        Intent zzb = zzb(bundle, keyPair);
        if (zzb == null || !zzb.hasExtra("google.messenger")) {
            return zzb;
        }
        zzb = zzb(bundle, keyPair);
        return (zzb == null || !zzb.hasExtra("google.messenger")) ? zzb : null;
    }

    final void zzc(Message message) {
        if (message != null) {
            if (message.obj instanceof Intent) {
                Intent intent = (Intent) message.obj;
                intent.setExtrasClassLoader(MessengerCompat.class.getClassLoader());
                if (intent.hasExtra("google.messenger")) {
                    Parcelable parcelableExtra = intent.getParcelableExtra("google.messenger");
                    if (parcelableExtra instanceof MessengerCompat) {
                        this.zzhue = (MessengerCompat) parcelableExtra;
                    }
                    if (parcelableExtra instanceof Messenger) {
                        this.zzhud = (Messenger) parcelableExtra;
                    }
                }
                zzi((Intent) message.obj);
                return;
            }
            Log.w("InstanceID/Rpc", "Dropping invalid message");
        }
    }

    final void zzi(Intent intent) {
        String str = null;
        if (intent != null) {
            String stringExtra;
            String stringExtra2;
            if ("com.google.android.c2dm.intent.REGISTRATION".equals(intent.getAction())) {
                stringExtra = intent.getStringExtra("registration_id");
                if (stringExtra == null) {
                    stringExtra = intent.getStringExtra("unregistered");
                }
                String str2;
                String[] split;
                if (stringExtra == null) {
                    stringExtra2 = intent.getStringExtra("error");
                    if (stringExtra2 == null) {
                        str = String.valueOf(intent.getExtras());
                        Log.w("InstanceID/Rpc", new StringBuilder(String.valueOf(str).length() + 49).append("Unexpected response, no error or registration id ").append(str).toString());
                        return;
                    }
                    if (Log.isLoggable("InstanceID/Rpc", 3)) {
                        String str3 = "InstanceID/Rpc";
                        str2 = "Received InstanceID error ";
                        stringExtra = String.valueOf(stringExtra2);
                        Log.d(str3, stringExtra.length() != 0 ? str2.concat(stringExtra) : new String(str2));
                    }
                    if (stringExtra2.startsWith("|")) {
                        split = stringExtra2.split("\\|");
                        if (!"ID".equals(split[1])) {
                            str2 = "InstanceID/Rpc";
                            String str4 = "Unexpected structured response ";
                            stringExtra = String.valueOf(stringExtra2);
                            Log.w(str2, stringExtra.length() != 0 ? str4.concat(stringExtra) : new String(str4));
                        }
                        if (split.length > 2) {
                            stringExtra = split[2];
                            str = split[3];
                            if (str.startsWith(CryptoUri.URI_SCHEME_DELIMETER)) {
                                str = str.substring(1);
                            }
                        } else {
                            str = "UNKNOWN";
                            stringExtra = null;
                        }
                        intent.putExtra("error", str);
                    } else {
                        stringExtra = null;
                        str = stringExtra2;
                    }
                    zzbk(stringExtra, str);
                    long longExtra = intent.getLongExtra("Retry-After", 0);
                    if (longExtra > 0) {
                        this.zzhug = SystemClock.elapsedRealtime();
                        this.zzhui = ((int) longExtra) * 1000;
                        this.zzhuj = SystemClock.elapsedRealtime() + ((long) this.zzhui);
                        Log.w("InstanceID/Rpc", "Explicit request from server to backoff: " + this.zzhui);
                        return;
                    } else if (("SERVICE_NOT_AVAILABLE".equals(str) || "AUTHENTICATION_FAILED".equals(str)) && "com.google.android.gsf".equals(zzhtw)) {
                        this.zzhuh++;
                        if (this.zzhuh >= 3) {
                            if (this.zzhuh == 3) {
                                this.zzhui = new Random().nextInt(1000) + 1000;
                            }
                            this.zzhui <<= 1;
                            this.zzhuj = SystemClock.elapsedRealtime() + ((long) this.zzhui);
                            Log.w("InstanceID/Rpc", new StringBuilder(String.valueOf(str).length() + 31).append("Backoff due to ").append(str).append(" for ").append(this.zzhui).toString());
                            return;
                        }
                        return;
                    } else {
                        return;
                    }
                }
                this.zzhuf = SystemClock.elapsedRealtime();
                this.zzhuj = 0;
                this.zzhuh = 0;
                this.zzhui = 0;
                if (stringExtra.startsWith("|")) {
                    split = stringExtra.split("\\|");
                    if (!"ID".equals(split[1])) {
                        stringExtra2 = "InstanceID/Rpc";
                        str2 = "Unexpected structured response ";
                        stringExtra = String.valueOf(stringExtra);
                        Log.w(stringExtra2, stringExtra.length() != 0 ? str2.concat(stringExtra) : new String(str2));
                    }
                    stringExtra2 = split[2];
                    if (split.length > 4) {
                        if ("SYNC".equals(split[3])) {
                            FirebaseInstanceId.zzek(this.zzahz);
                        } else if ("RST".equals(split[3])) {
                            Context context = this.zzahz;
                            zzj.zza(this.zzahz, null);
                            FirebaseInstanceId.zza(context, zzj.zzcga());
                            intent.removeExtra("registration_id");
                            zzb(stringExtra2, intent);
                            return;
                        }
                    }
                    stringExtra = split[split.length - 1];
                    if (stringExtra.startsWith(CryptoUri.URI_SCHEME_DELIMETER)) {
                        stringExtra = stringExtra.substring(1);
                    }
                    intent.putExtra("registration_id", stringExtra);
                    str = stringExtra2;
                }
                if (str != null) {
                    zzb(str, intent);
                } else if (Log.isLoggable("InstanceID/Rpc", 3)) {
                    Log.d("InstanceID/Rpc", "Ignoring response without a request ID");
                }
            } else if (Log.isLoggable("InstanceID/Rpc", 3)) {
                str = "InstanceID/Rpc";
                stringExtra2 = "Unexpected response ";
                stringExtra = String.valueOf(intent.getAction());
                Log.d(str, stringExtra.length() != 0 ? stringExtra2.concat(stringExtra) : new String(stringExtra2));
            }
        } else if (Log.isLoggable("InstanceID/Rpc", 3)) {
            Log.d("InstanceID/Rpc", "Unexpected response: null");
        }
    }
}
