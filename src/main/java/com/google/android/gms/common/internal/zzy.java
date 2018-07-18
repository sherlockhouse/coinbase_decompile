package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.zzc;
import com.google.android.gms.common.zze;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;

public final class zzy extends zzbck {
    public static final Creator<zzy> CREATOR = new zzz();
    private int version;
    private int zzfuc;
    private int zzfud;
    String zzfue;
    IBinder zzfuf;
    Scope[] zzfug;
    Bundle zzfuh;
    Account zzfui;
    zzc[] zzfuj;

    public zzy(int i) {
        this.version = 3;
        this.zzfud = zze.GOOGLE_PLAY_SERVICES_VERSION_CODE;
        this.zzfuc = i;
    }

    zzy(int i, int i2, int i3, String str, IBinder iBinder, Scope[] scopeArr, Bundle bundle, Account account, zzc[] com_google_android_gms_common_zzcArr) {
        Account account2 = null;
        this.version = i;
        this.zzfuc = i2;
        this.zzfud = i3;
        if ("com.google.android.gms".equals(str)) {
            this.zzfue = "com.google.android.gms";
        } else {
            this.zzfue = str;
        }
        if (i < 2) {
            if (iBinder != null) {
                zzam com_google_android_gms_common_internal_zzao;
                if (iBinder != null) {
                    IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.IAccountAccessor");
                    com_google_android_gms_common_internal_zzao = queryLocalInterface instanceof zzam ? (zzam) queryLocalInterface : new zzao(iBinder);
                }
                account2 = zza.zza(com_google_android_gms_common_internal_zzao);
            }
            this.zzfui = account2;
        } else {
            this.zzfuf = iBinder;
            this.zzfui = account;
        }
        this.zzfug = scopeArr;
        this.zzfuh = bundle;
        this.zzfuj = com_google_android_gms_common_zzcArr;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbcn.zze(parcel);
        zzbcn.zzc(parcel, 1, this.version);
        zzbcn.zzc(parcel, 2, this.zzfuc);
        zzbcn.zzc(parcel, 3, this.zzfud);
        zzbcn.zza(parcel, 4, this.zzfue, false);
        zzbcn.zza(parcel, 5, this.zzfuf, false);
        zzbcn.zza(parcel, 6, this.zzfug, i, false);
        zzbcn.zza(parcel, 7, this.zzfuh, false);
        zzbcn.zza(parcel, 8, this.zzfui, i, false);
        zzbcn.zza(parcel, 10, this.zzfuj, i, false);
        zzbcn.zzai(parcel, zze);
    }
}
