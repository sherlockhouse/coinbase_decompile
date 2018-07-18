package com.google.android.gms.auth.api.signin;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.auth.api.signin.internal.zzn;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.internal.zzbcl;
import java.util.ArrayList;

public final class zzd implements Creator<GoogleSignInOptions> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        boolean z = false;
        ArrayList arrayList = null;
        int zzd = zzbcl.zzd(parcel);
        String str = null;
        String str2 = null;
        boolean z2 = false;
        boolean z3 = false;
        Account account = null;
        ArrayList arrayList2 = null;
        int i = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    i = zzbcl.zzg(parcel, readInt);
                    break;
                case 2:
                    arrayList2 = zzbcl.zzc(parcel, readInt, Scope.CREATOR);
                    break;
                case 3:
                    account = (Account) zzbcl.zza(parcel, readInt, Account.CREATOR);
                    break;
                case 4:
                    z3 = zzbcl.zzc(parcel, readInt);
                    break;
                case 5:
                    z2 = zzbcl.zzc(parcel, readInt);
                    break;
                case 6:
                    z = zzbcl.zzc(parcel, readInt);
                    break;
                case 7:
                    str2 = zzbcl.zzq(parcel, readInt);
                    break;
                case 8:
                    str = zzbcl.zzq(parcel, readInt);
                    break;
                case 9:
                    arrayList = zzbcl.zzc(parcel, readInt, zzn.CREATOR);
                    break;
                default:
                    zzbcl.zzb(parcel, readInt);
                    break;
            }
        }
        zzbcl.zzaf(parcel, zzd);
        return new GoogleSignInOptions(i, arrayList2, account, z3, z2, z, str2, str, arrayList);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new GoogleSignInOptions[i];
    }
}
