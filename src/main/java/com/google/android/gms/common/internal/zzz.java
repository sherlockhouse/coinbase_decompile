package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.zzc;
import com.google.android.gms.internal.zzbcl;

public final class zzz implements Creator<zzy> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int i = 0;
        zzc[] com_google_android_gms_common_zzcArr = null;
        int zzd = zzbcl.zzd(parcel);
        Account account = null;
        Bundle bundle = null;
        Scope[] scopeArr = null;
        IBinder iBinder = null;
        String str = null;
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    i3 = zzbcl.zzg(parcel, readInt);
                    break;
                case 2:
                    i2 = zzbcl.zzg(parcel, readInt);
                    break;
                case 3:
                    i = zzbcl.zzg(parcel, readInt);
                    break;
                case 4:
                    str = zzbcl.zzq(parcel, readInt);
                    break;
                case 5:
                    iBinder = zzbcl.zzr(parcel, readInt);
                    break;
                case 6:
                    scopeArr = (Scope[]) zzbcl.zzb(parcel, readInt, Scope.CREATOR);
                    break;
                case 7:
                    bundle = zzbcl.zzs(parcel, readInt);
                    break;
                case 8:
                    account = (Account) zzbcl.zza(parcel, readInt, Account.CREATOR);
                    break;
                case 10:
                    com_google_android_gms_common_zzcArr = (zzc[]) zzbcl.zzb(parcel, readInt, zzc.CREATOR);
                    break;
                default:
                    zzbcl.zzb(parcel, readInt);
                    break;
            }
        }
        zzbcl.zzaf(parcel, zzd);
        return new zzy(i3, i2, i, str, iBinder, scopeArr, bundle, account, com_google_android_gms_common_zzcArr);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzy[i];
    }
}
