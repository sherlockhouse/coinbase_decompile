package com.google.android.gms.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ServiceInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.common.util.zzd;
import com.google.android.gms.measurement.AppMeasurement.Event;
import com.google.android.gms.measurement.AppMeasurement.UserProperty;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import javax.security.auth.x500.X500Principal;

public final class zzcfw extends zzcdu {
    private static String[] zzixe = new String[]{"firebase_"};
    private SecureRandom zzixf;
    private final AtomicLong zzixg = new AtomicLong(0);
    private int zzixh;

    zzcfw(zzccw com_google_android_gms_internal_zzccw) {
        super(com_google_android_gms_internal_zzccw);
    }

    private final int zza(String str, Object obj, boolean z) {
        if (z) {
            int length;
            Object obj2;
            String str2 = "param";
            zzcax.zzavs();
            if (obj instanceof Parcelable[]) {
                length = ((Parcelable[]) obj).length;
            } else if (obj instanceof ArrayList) {
                length = ((ArrayList) obj).size();
            } else {
                length = 1;
                if (obj2 == null) {
                    return 17;
                }
            }
            if (length > 1000) {
                zzaul().zzayf().zzd("Parameter array is too long; discarded. Value kind, name, array length", str2, str, Integer.valueOf(length));
                obj2 = null;
            } else {
                length = 1;
            }
            if (obj2 == null) {
                return 17;
            }
        }
        return zzkd(str) ? zza("param", str, zzcax.zzavr(), obj, z) : zza("param", str, zzcax.zzavq(), obj, z) ? 0 : 4;
    }

    private static Object zza(int i, Object obj, boolean z) {
        if (obj == null) {
            return null;
        }
        if ((obj instanceof Long) || (obj instanceof Double)) {
            return obj;
        }
        if (obj instanceof Integer) {
            return Long.valueOf((long) ((Integer) obj).intValue());
        }
        if (obj instanceof Byte) {
            return Long.valueOf((long) ((Byte) obj).byteValue());
        }
        if (obj instanceof Short) {
            return Long.valueOf((long) ((Short) obj).shortValue());
        }
        if (!(obj instanceof Boolean)) {
            return obj instanceof Float ? Double.valueOf(((Float) obj).doubleValue()) : ((obj instanceof String) || (obj instanceof Character) || (obj instanceof CharSequence)) ? zza(String.valueOf(obj), i, z) : null;
        } else {
            return Long.valueOf(((Boolean) obj).booleanValue() ? 1 : 0);
        }
    }

    public static String zza(String str, int i, boolean z) {
        return str.codePointCount(0, str.length()) > i ? z ? String.valueOf(str.substring(0, str.offsetByCodePoints(0, i))).concat("...") : null : str;
    }

    public static String zza(String str, String[] strArr, String[] strArr2) {
        zzbp.zzu(strArr);
        zzbp.zzu(strArr2);
        int min = Math.min(strArr.length, strArr2.length);
        for (int i = 0; i < min; i++) {
            if (zzas(str, strArr[i])) {
                return strArr2[i];
            }
        }
        return null;
    }

    public static boolean zza(Context context, String str, boolean z) {
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null) {
                return false;
            }
            ActivityInfo receiverInfo = packageManager.getReceiverInfo(new ComponentName(context, str), 2);
            return receiverInfo != null && receiverInfo.enabled;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    private final boolean zza(String str, String str2, int i, Object obj, boolean z) {
        if (obj == null || (obj instanceof Long) || (obj instanceof Float) || (obj instanceof Integer) || (obj instanceof Byte) || (obj instanceof Short) || (obj instanceof Boolean) || (obj instanceof Double)) {
            return true;
        }
        if ((obj instanceof String) || (obj instanceof Character) || (obj instanceof CharSequence)) {
            String valueOf = String.valueOf(obj);
            if (valueOf.codePointCount(0, valueOf.length()) <= i) {
                return true;
            }
            zzaul().zzayf().zzd("Value is too long; discarded. Value kind, name, value length", str, str2, Integer.valueOf(valueOf.length()));
            return false;
        } else if ((obj instanceof Bundle) && z) {
            return true;
        } else {
            int length;
            int i2;
            Object obj2;
            if ((obj instanceof Parcelable[]) && z) {
                Parcelable[] parcelableArr = (Parcelable[]) obj;
                length = parcelableArr.length;
                i2 = 0;
                while (i2 < length) {
                    obj2 = parcelableArr[i2];
                    if (obj2 instanceof Bundle) {
                        i2++;
                    } else {
                        zzaul().zzayf().zze("All Parcelable[] elements must be of type Bundle. Value type, name", obj2.getClass(), str2);
                        return false;
                    }
                }
                return true;
            } else if (!(obj instanceof ArrayList) || !z) {
                return false;
            } else {
                ArrayList arrayList = (ArrayList) obj;
                length = arrayList.size();
                i2 = 0;
                while (i2 < length) {
                    obj2 = arrayList.get(i2);
                    i2++;
                    if (!(obj2 instanceof Bundle)) {
                        zzaul().zzayf().zze("All ArrayList elements must be of type Bundle. Value type, name", obj2.getClass(), str2);
                        return false;
                    }
                }
                return true;
            }
        }
    }

    private final boolean zza(String str, String[] strArr, String str2) {
        if (str2 == null) {
            zzaul().zzayd().zzj("Name is required and can't be null. Type", str);
            return false;
        }
        boolean z;
        zzbp.zzu(str2);
        for (String startsWith : zzixe) {
            if (str2.startsWith(startsWith)) {
                z = true;
                break;
            }
        }
        z = false;
        if (z) {
            zzaul().zzayd().zze("Name starts with reserved prefix. Type, name", str, str2);
            return false;
        }
        if (strArr != null) {
            zzbp.zzu(strArr);
            for (String startsWith2 : strArr) {
                if (zzas(str2, startsWith2)) {
                    z = true;
                    break;
                }
            }
            z = false;
            if (z) {
                zzaul().zzayd().zze("Name is reserved. Type, name", str, str2);
                return false;
            }
        }
        return true;
    }

    public static boolean zza(long[] jArr, int i) {
        return i < (jArr.length << 6) && (jArr[i / 64] & (1 << (i % 64))) != 0;
    }

    static byte[] zza(Parcelable parcelable) {
        if (parcelable == null) {
            return null;
        }
        Parcel obtain = Parcel.obtain();
        try {
            parcelable.writeToParcel(obtain, 0);
            byte[] marshall = obtain.marshall();
            return marshall;
        } finally {
            obtain.recycle();
        }
    }

    public static long[] zza(BitSet bitSet) {
        int length = (bitSet.length() + 63) / 64;
        long[] jArr = new long[length];
        int i = 0;
        while (i < length) {
            jArr[i] = 0;
            int i2 = 0;
            while (i2 < 64 && (i << 6) + i2 < bitSet.length()) {
                if (bitSet.get((i << 6) + i2)) {
                    jArr[i] = jArr[i] | (1 << i2);
                }
                i2++;
            }
            i++;
        }
        return jArr;
    }

    public static Bundle[] zzac(Object obj) {
        if (obj instanceof Bundle) {
            return new Bundle[]{(Bundle) obj};
        } else if (obj instanceof Parcelable[]) {
            return (Bundle[]) Arrays.copyOf((Parcelable[]) obj, ((Parcelable[]) obj).length, Bundle[].class);
        } else {
            if (!(obj instanceof ArrayList)) {
                return null;
            }
            ArrayList arrayList = (ArrayList) obj;
            return (Bundle[]) arrayList.toArray(new Bundle[arrayList.size()]);
        }
    }

    public static Object zzad(Object obj) {
        ObjectOutputStream objectOutputStream;
        ObjectInputStream objectInputStream;
        Throwable th;
        if (obj == null) {
            return null;
        }
        try {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            try {
                objectOutputStream.writeObject(obj);
                objectOutputStream.flush();
                objectInputStream = new ObjectInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
            } catch (Throwable th2) {
                th = th2;
                objectInputStream = null;
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
                throw th;
            }
            try {
                Object readObject = objectInputStream.readObject();
                try {
                    objectOutputStream.close();
                    objectInputStream.close();
                    return readObject;
                } catch (IOException e) {
                    return null;
                } catch (ClassNotFoundException e2) {
                    return null;
                }
            } catch (Throwable th3) {
                th = th3;
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            objectInputStream = null;
            objectOutputStream = null;
            if (objectOutputStream != null) {
                objectOutputStream.close();
            }
            if (objectInputStream != null) {
                objectInputStream.close();
            }
            throw th;
        }
    }

    private final boolean zzai(Context context, String str) {
        X500Principal x500Principal = new X500Principal("CN=Android Debug,O=Android,C=US");
        try {
            PackageInfo packageInfo = zzbed.zzcr(context).getPackageInfo(str, 64);
            if (!(packageInfo == null || packageInfo.signatures == null || packageInfo.signatures.length <= 0)) {
                return ((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(packageInfo.signatures[0].toByteArray()))).getSubjectX500Principal().equals(x500Principal);
            }
        } catch (CertificateException e) {
            zzaul().zzayd().zzj("Error obtaining certificate", e);
        } catch (NameNotFoundException e2) {
            zzaul().zzayd().zzj("Package name not found", e2);
        }
        return true;
    }

    private final boolean zzaq(String str, String str2) {
        if (str2 == null) {
            zzaul().zzayd().zzj("Name is required and can't be null. Type", str);
            return false;
        } else if (str2.length() == 0) {
            zzaul().zzayd().zzj("Name is required and can't be empty. Type", str);
            return false;
        } else {
            int codePointAt = str2.codePointAt(0);
            if (Character.isLetter(codePointAt)) {
                int length = str2.length();
                codePointAt = Character.charCount(codePointAt);
                while (codePointAt < length) {
                    int codePointAt2 = str2.codePointAt(codePointAt);
                    if (codePointAt2 == 95 || Character.isLetterOrDigit(codePointAt2)) {
                        codePointAt += Character.charCount(codePointAt2);
                    } else {
                        zzaul().zzayd().zze("Name must consist of letters, digits or _ (underscores). Type, name", str, str2);
                        return false;
                    }
                }
                return true;
            }
            zzaul().zzayd().zze("Name must start with a letter. Type, name", str, str2);
            return false;
        }
    }

    private final boolean zzar(String str, String str2) {
        if (str2 == null) {
            zzaul().zzayd().zzj("Name is required and can't be null. Type", str);
            return false;
        } else if (str2.length() == 0) {
            zzaul().zzayd().zzj("Name is required and can't be empty. Type", str);
            return false;
        } else {
            int codePointAt = str2.codePointAt(0);
            if (Character.isLetter(codePointAt) || codePointAt == 95) {
                int length = str2.length();
                codePointAt = Character.charCount(codePointAt);
                while (codePointAt < length) {
                    int codePointAt2 = str2.codePointAt(codePointAt);
                    if (codePointAt2 == 95 || Character.isLetterOrDigit(codePointAt2)) {
                        codePointAt += Character.charCount(codePointAt2);
                    } else {
                        zzaul().zzayd().zze("Name must consist of letters, digits or _ (underscores). Type, name", str, str2);
                        return false;
                    }
                }
                return true;
            }
            zzaul().zzayd().zze("Name must start with a letter or _ (underscore). Type, name", str, str2);
            return false;
        }
    }

    public static boolean zzas(String str, String str2) {
        return (str == null && str2 == null) ? true : str == null ? false : str.equals(str2);
    }

    private static void zzb(Bundle bundle, Object obj) {
        zzbp.zzu(bundle);
        if (obj == null) {
            return;
        }
        if ((obj instanceof String) || (obj instanceof CharSequence)) {
            bundle.putLong("_el", (long) String.valueOf(obj).length());
        }
    }

    private final boolean zzb(String str, int i, String str2) {
        if (str2 == null) {
            zzaul().zzayd().zzj("Name is required and can't be null. Type", str);
            return false;
        } else if (str2.codePointCount(0, str2.length()) <= i) {
            return true;
        } else {
            zzaul().zzayd().zzd("Name is too long. Type, maximum supported length, name", str, Integer.valueOf(i), str2);
            return false;
        }
    }

    private static boolean zzd(Bundle bundle, int i) {
        if (bundle.getLong("_err") != 0) {
            return false;
        }
        bundle.putLong("_err", (long) i);
        return true;
    }

    static boolean zzd(zzcbk com_google_android_gms_internal_zzcbk, zzcas com_google_android_gms_internal_zzcas) {
        zzbp.zzu(com_google_android_gms_internal_zzcbk);
        zzbp.zzu(com_google_android_gms_internal_zzcas);
        if (!TextUtils.isEmpty(com_google_android_gms_internal_zzcas.zzilu)) {
            return true;
        }
        zzcax.zzawk();
        return false;
    }

    static MessageDigest zzec(String str) {
        int i = 0;
        while (i < 2) {
            try {
                MessageDigest instance = MessageDigest.getInstance(str);
                if (instance != null) {
                    return instance;
                }
                i++;
            } catch (NoSuchAlgorithmException e) {
            }
        }
        return null;
    }

    static boolean zzju(String str) {
        zzbp.zzgg(str);
        return str.charAt(0) != '_' || str.equals("_ep");
    }

    private final int zzjz(String str) {
        return !zzaq("event param", str) ? 3 : !zza("event param", null, str) ? 14 : zzb("event param", zzcax.zzavp(), str) ? 0 : 3;
    }

    private final int zzka(String str) {
        return !zzar("event param", str) ? 3 : !zza("event param", null, str) ? 14 : zzb("event param", zzcax.zzavp(), str) ? 0 : 3;
    }

    private static int zzkc(String str) {
        return "_ldl".equals(str) ? zzcax.zzavu() : zzcax.zzavt();
    }

    public static boolean zzkd(String str) {
        return !TextUtils.isEmpty(str) && str.startsWith("_");
    }

    static boolean zzkf(String str) {
        return str != null && str.matches("(\\+|-)?([0-9]+\\.?[0-9]*|[0-9]*\\.?[0-9]+)") && str.length() <= 310;
    }

    static boolean zzki(String str) {
        zzbp.zzgg(str);
        boolean z = true;
        switch (str.hashCode()) {
            case 94660:
                if (str.equals("_in")) {
                    z = false;
                    break;
                }
                break;
            case 95025:
                if (str.equals("_ug")) {
                    z = true;
                    break;
                }
                break;
            case 95027:
                if (str.equals("_ui")) {
                    z = true;
                    break;
                }
                break;
        }
        switch (z) {
            case false:
            case true:
            case true:
                return true;
            default:
                return false;
        }
    }

    public static boolean zzl(Intent intent) {
        String stringExtra = intent.getStringExtra("android.intent.extra.REFERRER_NAME");
        return "android-app://com.google.android.googlequicksearchbox/https/www.google.com".equals(stringExtra) || "https://www.google.com".equals(stringExtra) || "android-app://com.google.appcrawler".equals(stringExtra);
    }

    static long zzr(byte[] bArr) {
        long j = null;
        zzbp.zzu(bArr);
        zzbp.zzbg(bArr.length > 0);
        long j2 = 0;
        int length = bArr.length - 1;
        while (length >= 0 && length >= bArr.length - 8) {
            j2 += (((long) bArr[length]) & 255) << j;
            j += 8;
            length--;
        }
        return j2;
    }

    public static boolean zzv(Context context, String str) {
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null) {
                return false;
            }
            ServiceInfo serviceInfo = packageManager.getServiceInfo(new ComponentName(context, str), 4);
            return serviceInfo != null && serviceInfo.enabled;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    public final Bundle zza(String str, Bundle bundle, List<String> list, boolean z, boolean z2) {
        if (bundle == null) {
            return null;
        }
        Bundle bundle2 = new Bundle(bundle);
        zzcax.zzavm();
        int i = 0;
        for (String str2 : bundle.keySet()) {
            int zzjz;
            if (list == null || !list.contains(str2)) {
                zzjz = z ? zzjz(str2) : 0;
                if (zzjz == 0) {
                    zzjz = zzka(str2);
                }
            } else {
                zzjz = 0;
            }
            if (zzjz != 0) {
                if (zzd(bundle2, zzjz)) {
                    bundle2.putString("_ev", zza(str2, zzcax.zzavp(), true));
                    if (zzjz == 3) {
                        zzb(bundle2, (Object) str2);
                    }
                }
                bundle2.remove(str2);
            } else {
                zzjz = zza(str2, bundle.get(str2), z2);
                if (zzjz == 0 || "_ev".equals(str2)) {
                    if (zzju(str2)) {
                        i++;
                        if (i > 25) {
                            zzaul().zzayd().zze("Event can't contain more then 25 params", zzaug().zzjc(str), zzaug().zzx(bundle));
                            zzd(bundle2, 5);
                            bundle2.remove(str2);
                        }
                    }
                    i = i;
                } else {
                    if (zzd(bundle2, zzjz)) {
                        bundle2.putString("_ev", zza(str2, zzcax.zzavp(), true));
                        zzb(bundle2, bundle.get(str2));
                    }
                    bundle2.remove(str2);
                }
            }
        }
        return bundle2;
    }

    final zzcbk zza(String str, Bundle bundle, String str2, long j, boolean z, boolean z2) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (zzjw(str) != 0) {
            zzaul().zzayd().zzj("Invalid conditional property event name", zzaug().zzje(str));
            throw new IllegalArgumentException();
        }
        Bundle bundle2 = bundle != null ? new Bundle(bundle) : new Bundle();
        bundle2.putString("_o", str2);
        return new zzcbk(str, new zzcbh(zzy(zza(str, bundle2, Collections.singletonList("_o"), false, false))), str2, j);
    }

    public final void zza(int i, String str, String str2, int i2) {
        zza(null, i, str, str2, i2);
    }

    public final void zza(Bundle bundle, String str, Object obj) {
        if (bundle != null) {
            if (obj instanceof Long) {
                bundle.putLong(str, ((Long) obj).longValue());
            } else if (obj instanceof String) {
                bundle.putString(str, String.valueOf(obj));
            } else if (obj instanceof Double) {
                bundle.putDouble(str, ((Double) obj).doubleValue());
            } else if (str != null) {
                zzaul().zzayg().zze("Not putting event parameter. Invalid value type. name, type", zzaug().zzjd(str), obj != null ? obj.getClass().getSimpleName() : null);
            }
        }
    }

    public final void zza(zzcgi com_google_android_gms_internal_zzcgi, Object obj) {
        zzbp.zzu(obj);
        com_google_android_gms_internal_zzcgi.zzfwo = null;
        com_google_android_gms_internal_zzcgi.zzizb = null;
        com_google_android_gms_internal_zzcgi.zzixc = null;
        if (obj instanceof String) {
            com_google_android_gms_internal_zzcgi.zzfwo = (String) obj;
        } else if (obj instanceof Long) {
            com_google_android_gms_internal_zzcgi.zzizb = (Long) obj;
        } else if (obj instanceof Double) {
            com_google_android_gms_internal_zzcgi.zzixc = (Double) obj;
        } else {
            zzaul().zzayd().zzj("Ignoring invalid (type) event param value", obj);
        }
    }

    public final void zza(zzcgm com_google_android_gms_internal_zzcgm, Object obj) {
        zzbp.zzu(obj);
        com_google_android_gms_internal_zzcgm.zzfwo = null;
        com_google_android_gms_internal_zzcgm.zzizb = null;
        com_google_android_gms_internal_zzcgm.zzixc = null;
        if (obj instanceof String) {
            com_google_android_gms_internal_zzcgm.zzfwo = (String) obj;
        } else if (obj instanceof Long) {
            com_google_android_gms_internal_zzcgm.zzizb = (Long) obj;
        } else if (obj instanceof Double) {
            com_google_android_gms_internal_zzcgm.zzixc = (Double) obj;
        } else {
            zzaul().zzayd().zzj("Ignoring invalid (type) user attribute value", obj);
        }
    }

    public final void zza(String str, int i, String str2, String str3, int i2) {
        Bundle bundle = new Bundle();
        zzd(bundle, i);
        if (!TextUtils.isEmpty(str2)) {
            bundle.putString(str2, str3);
        }
        if (i == 6 || i == 7 || i == 2) {
            bundle.putLong("_el", (long) i2);
        }
        zzcax.zzawk();
        this.zziki.zzatz().zzc("auto", "_err", bundle);
    }

    final long zzah(Context context, String str) {
        zzuj();
        zzbp.zzu(context);
        zzbp.zzgg(str);
        PackageManager packageManager = context.getPackageManager();
        MessageDigest zzec = zzec("MD5");
        if (zzec == null) {
            zzaul().zzayd().log("Could not get MD5 instance");
            return -1;
        }
        if (packageManager != null) {
            try {
                if (!zzai(context, str)) {
                    PackageInfo packageInfo = zzbed.zzcr(context).getPackageInfo(getContext().getPackageName(), 64);
                    if (packageInfo.signatures != null && packageInfo.signatures.length > 0) {
                        return zzr(zzec.digest(packageInfo.signatures[0].toByteArray()));
                    }
                    zzaul().zzayf().log("Could not get signatures");
                    return -1;
                }
            } catch (NameNotFoundException e) {
                zzaul().zzayd().zzj("Package name not found", e);
            }
        }
        return 0;
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

    public final long zzazx() {
        long nextLong;
        if (this.zzixg.get() == 0) {
            synchronized (this.zzixg) {
                nextLong = new Random(System.nanoTime() ^ zzvx().currentTimeMillis()).nextLong();
                int i = this.zzixh + 1;
                this.zzixh = i;
                nextLong += (long) i;
            }
        } else {
            synchronized (this.zzixg) {
                this.zzixg.compareAndSet(-1, 1);
                nextLong = this.zzixg.getAndIncrement();
            }
        }
        return nextLong;
    }

    final SecureRandom zzazy() {
        zzuj();
        if (this.zzixf == null) {
            this.zzixf = new SecureRandom();
        }
        return this.zzixf;
    }

    final <T extends Parcelable> T zzb(byte[] bArr, Creator<T> creator) {
        T t;
        if (bArr == null) {
            return null;
        }
        Parcel obtain = Parcel.obtain();
        try {
            obtain.unmarshall(bArr, 0, bArr.length);
            obtain.setDataPosition(0);
            t = (Parcelable) creator.createFromParcel(obtain);
            return t;
        } catch (zzbcm e) {
            t = zzaul().zzayd();
            t.log("Failed to load parcelable from buffer");
            return null;
        } finally {
            obtain.recycle();
        }
    }

    public final byte[] zzb(zzcgj com_google_android_gms_internal_zzcgj) {
        try {
            byte[] bArr = new byte[com_google_android_gms_internal_zzcgj.zzhi()];
            zzeyf zzn = zzeyf.zzn(bArr, 0, bArr.length);
            com_google_android_gms_internal_zzcgj.zza(zzn);
            zzn.zzctn();
            return bArr;
        } catch (IOException e) {
            zzaul().zzayd().zzj("Data loss. Failed to serialize batch", e);
            return null;
        }
    }

    public final boolean zzdt(String str) {
        zzuj();
        if (zzbed.zzcr(getContext()).checkCallingOrSelfPermission(str) == 0) {
            return true;
        }
        zzaul().zzayi().zzj("Permission not granted", str);
        return false;
    }

    public final boolean zzf(long j, long j2) {
        return j == 0 || j2 <= 0 || Math.abs(zzvx().currentTimeMillis() - j) > j2;
    }

    public final int zzjv(String str) {
        return !zzaq("event", str) ? 2 : !zza("event", Event.zzikj, str) ? 13 : zzb("event", zzcax.zzavn(), str) ? 0 : 2;
    }

    public final int zzjw(String str) {
        return !zzar("event", str) ? 2 : !zza("event", Event.zzikj, str) ? 13 : zzb("event", zzcax.zzavn(), str) ? 0 : 2;
    }

    public final int zzjx(String str) {
        return !zzaq("user property", str) ? 6 : !zza("user property", UserProperty.zzikq, str) ? 15 : zzb("user property", zzcax.zzavo(), str) ? 0 : 6;
    }

    public final int zzjy(String str) {
        return !zzar("user property", str) ? 6 : !zza("user property", UserProperty.zzikq, str) ? 15 : zzb("user property", zzcax.zzavo(), str) ? 0 : 6;
    }

    public final Object zzk(String str, Object obj) {
        if ("_ev".equals(str)) {
            return zza(zzcax.zzavr(), obj, true);
        }
        return zza(zzkd(str) ? zzcax.zzavr() : zzcax.zzavq(), obj, false);
    }

    public final boolean zzkb(String str) {
        if (TextUtils.isEmpty(str)) {
            zzaul().zzayd().log("Missing google_app_id. Firebase Analytics disabled. See https://goo.gl/NAOOOI");
            return false;
        }
        zzbp.zzu(str);
        if (str.matches("^1:\\d+:android:[a-f0-9]+$")) {
            return true;
        }
        zzaul().zzayd().zzj("Invalid google_app_id. Firebase Analytics disabled. See https://goo.gl/NAOOOI. provided id", str);
        return false;
    }

    public final boolean zzke(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String zzaxg = zzaun().zzaxg();
        zzcax.zzawk();
        return zzaxg.equals(str);
    }

    final boolean zzkg(String str) {
        return "1".equals(zzaui().zzan(str, "measurement.upload.blacklist_internal"));
    }

    final boolean zzkh(String str) {
        return "1".equals(zzaui().zzan(str, "measurement.upload.blacklist_public"));
    }

    public final int zzl(String str, Object obj) {
        return "_ldl".equals(str) ? zza("user property referrer", str, zzkc(str), obj, false) : zza("user property", str, zzkc(str), obj, false) ? 0 : 7;
    }

    public final Object zzm(String str, Object obj) {
        return "_ldl".equals(str) ? zza(zzkc(str), obj, true) : zza(zzkc(str), obj, false);
    }

    public final Bundle zzp(Uri uri) {
        Bundle bundle = null;
        if (uri != null) {
            try {
                Object queryParameter;
                Object queryParameter2;
                Object queryParameter3;
                Object queryParameter4;
                if (uri.isHierarchical()) {
                    queryParameter = uri.getQueryParameter("utm_campaign");
                    queryParameter2 = uri.getQueryParameter("utm_source");
                    queryParameter3 = uri.getQueryParameter("utm_medium");
                    queryParameter4 = uri.getQueryParameter("gclid");
                } else {
                    queryParameter4 = null;
                    queryParameter3 = null;
                    queryParameter2 = null;
                    queryParameter = null;
                }
                if (!(TextUtils.isEmpty(queryParameter) && TextUtils.isEmpty(queryParameter2) && TextUtils.isEmpty(queryParameter3) && TextUtils.isEmpty(queryParameter4))) {
                    bundle = new Bundle();
                    if (!TextUtils.isEmpty(queryParameter)) {
                        bundle.putString("campaign", queryParameter);
                    }
                    if (!TextUtils.isEmpty(queryParameter2)) {
                        bundle.putString("source", queryParameter2);
                    }
                    if (!TextUtils.isEmpty(queryParameter3)) {
                        bundle.putString("medium", queryParameter3);
                    }
                    if (!TextUtils.isEmpty(queryParameter4)) {
                        bundle.putString("gclid", queryParameter4);
                    }
                    queryParameter4 = uri.getQueryParameter("utm_term");
                    if (!TextUtils.isEmpty(queryParameter4)) {
                        bundle.putString("term", queryParameter4);
                    }
                    queryParameter4 = uri.getQueryParameter("utm_content");
                    if (!TextUtils.isEmpty(queryParameter4)) {
                        bundle.putString("content", queryParameter4);
                    }
                    queryParameter4 = uri.getQueryParameter("aclid");
                    if (!TextUtils.isEmpty(queryParameter4)) {
                        bundle.putString("aclid", queryParameter4);
                    }
                    queryParameter4 = uri.getQueryParameter("cp1");
                    if (!TextUtils.isEmpty(queryParameter4)) {
                        bundle.putString("cp1", queryParameter4);
                    }
                    queryParameter4 = uri.getQueryParameter("anid");
                    if (!TextUtils.isEmpty(queryParameter4)) {
                        bundle.putString("anid", queryParameter4);
                    }
                }
            } catch (UnsupportedOperationException e) {
                zzaul().zzayf().zzj("Install referrer url isn't a hierarchical URI", e);
            }
        }
        return bundle;
    }

    public final byte[] zzp(byte[] bArr) throws IOException {
        try {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(bArr);
            gZIPOutputStream.close();
            byteArrayOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            zzaul().zzayd().zzj("Failed to gzip content", e);
            throw e;
        }
    }

    public final byte[] zzq(byte[] bArr) throws IOException {
        try {
            InputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            GZIPInputStream gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr2 = new byte[1024];
            while (true) {
                int read = gZIPInputStream.read(bArr2);
                if (read > 0) {
                    byteArrayOutputStream.write(bArr2, 0, read);
                } else {
                    gZIPInputStream.close();
                    byteArrayInputStream.close();
                    return byteArrayOutputStream.toByteArray();
                }
            }
        } catch (IOException e) {
            zzaul().zzayd().zzj("Failed to ungzip content", e);
            throw e;
        }
    }

    public final /* bridge */ /* synthetic */ void zzuj() {
        super.zzuj();
    }

    protected final void zzuk() {
        SecureRandom secureRandom = new SecureRandom();
        long nextLong = secureRandom.nextLong();
        if (nextLong == 0) {
            nextLong = secureRandom.nextLong();
            if (nextLong == 0) {
                zzaul().zzayf().log("Utils falling back to Random for random id");
            }
        }
        this.zzixg.set(nextLong);
    }

    public final /* bridge */ /* synthetic */ zzd zzvx() {
        return super.zzvx();
    }

    final Bundle zzy(Bundle bundle) {
        Bundle bundle2 = new Bundle();
        if (bundle != null) {
            for (String str : bundle.keySet()) {
                Object zzk = zzk(str, bundle.get(str));
                if (zzk == null) {
                    zzaul().zzayf().zzj("Param value can't be null", zzaug().zzjd(str));
                } else {
                    zza(bundle2, str, zzk);
                }
            }
        }
        return bundle2;
    }
}
