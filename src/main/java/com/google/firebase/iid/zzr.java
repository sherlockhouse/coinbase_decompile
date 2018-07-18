package com.google.firebase.iid;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Base64;
import android.util.Log;
import com.google.android.gms.common.util.zzu;
import java.io.File;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

final class zzr {
    private Context zzahz;
    SharedPreferences zzhul;

    public zzr(Context context) {
        this(context, "com.google.android.gms.appid");
    }

    private zzr(Context context, String str) {
        this.zzahz = context;
        this.zzhul = context.getSharedPreferences(str, 0);
        String valueOf = String.valueOf(str);
        String valueOf2 = String.valueOf("-no-backup");
        File file = new File(zzu.getNoBackupFilesDir(this.zzahz), valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
        if (!file.exists()) {
            try {
                if (file.createNewFile() && !isEmpty()) {
                    Log.i("InstanceID/Store", "App restored, clearing state");
                    FirebaseInstanceId.zza(this.zzahz, this);
                }
            } catch (IOException e) {
                if (Log.isLoggable("InstanceID/Store", 3)) {
                    valueOf = "InstanceID/Store";
                    String str2 = "Error creating file in no backup dir: ";
                    valueOf2 = String.valueOf(e.getMessage());
                    Log.d(valueOf, valueOf2.length() != 0 ? str2.concat(valueOf2) : new String(str2));
                }
            }
        }
    }

    private static String zzbl(String str, String str2) {
        String str3 = "|S|";
        return new StringBuilder((String.valueOf(str).length() + String.valueOf(str3).length()) + String.valueOf(str2).length()).append(str).append(str3).append(str2).toString();
    }

    private final void zzht(String str) {
        Editor edit = this.zzhul.edit();
        for (String str2 : this.zzhul.getAll().keySet()) {
            if (str2.startsWith(str)) {
                edit.remove(str2);
            }
        }
        edit.commit();
    }

    private static String zzn(String str, String str2, String str3) {
        String str4 = "|T|";
        return new StringBuilder((((String.valueOf(str).length() + 1) + String.valueOf(str4).length()) + String.valueOf(str2).length()) + String.valueOf(str3).length()).append(str).append(str4).append(str2).append("|").append(str3).toString();
    }

    public final synchronized boolean isEmpty() {
        return this.zzhul.getAll().isEmpty();
    }

    public final synchronized void zza(String str, String str2, String str3, String str4, String str5) {
        String zzc = zzs.zzc(str4, str5, System.currentTimeMillis());
        if (zzc != null) {
            Editor edit = this.zzhul.edit();
            edit.putString(zzn(str, str2, str3), zzc);
            edit.commit();
        }
    }

    public final synchronized void zzasv() {
        this.zzhul.edit().clear().commit();
    }

    public final synchronized void zzf(String str, String str2, String str3) {
        String zzn = zzn(str, str2, str3);
        Editor edit = this.zzhul.edit();
        edit.remove(zzn);
        edit.commit();
    }

    public final synchronized void zzhu(String str) {
        zzht(String.valueOf(str).concat("|T|"));
    }

    public final synchronized zzs zzo(String str, String str2, String str3) {
        return zzs.zzrf(this.zzhul.getString(zzn(str, str2, str3), null));
    }

    final synchronized KeyPair zzrc(String str) {
        KeyPair zzasp;
        zzasp = zza.zzasp();
        long currentTimeMillis = System.currentTimeMillis();
        Editor edit = this.zzhul.edit();
        edit.putString(zzbl(str, "|P|"), FirebaseInstanceId.zzn(zzasp.getPublic().getEncoded()));
        edit.putString(zzbl(str, "|K|"), FirebaseInstanceId.zzn(zzasp.getPrivate().getEncoded()));
        edit.putString(zzbl(str, "cre"), Long.toString(currentTimeMillis));
        edit.commit();
        return zzasp;
    }

    final synchronized void zzrd(String str) {
        zzht(String.valueOf(str).concat("|"));
    }

    public final synchronized KeyPair zzre(String str) {
        KeyPair keyPair;
        Object e;
        String string = this.zzhul.getString(zzbl(str, "|P|"), null);
        String string2 = this.zzhul.getString(zzbl(str, "|K|"), null);
        if (string == null || string2 == null) {
            keyPair = null;
        } else {
            try {
                byte[] decode = Base64.decode(string, 8);
                byte[] decode2 = Base64.decode(string2, 8);
                KeyFactory instance = KeyFactory.getInstance("RSA");
                keyPair = new KeyPair(instance.generatePublic(new X509EncodedKeySpec(decode)), instance.generatePrivate(new PKCS8EncodedKeySpec(decode2)));
            } catch (InvalidKeySpecException e2) {
                e = e2;
                string = String.valueOf(e);
                Log.w("InstanceID/Store", new StringBuilder(String.valueOf(string).length() + 19).append("Invalid key stored ").append(string).toString());
                FirebaseInstanceId.zza(this.zzahz, this);
                keyPair = null;
                return keyPair;
            } catch (NoSuchAlgorithmException e3) {
                e = e3;
                string = String.valueOf(e);
                Log.w("InstanceID/Store", new StringBuilder(String.valueOf(string).length() + 19).append("Invalid key stored ").append(string).toString());
                FirebaseInstanceId.zza(this.zzahz, this);
                keyPair = null;
                return keyPair;
            }
        }
        return keyPair;
    }
}
