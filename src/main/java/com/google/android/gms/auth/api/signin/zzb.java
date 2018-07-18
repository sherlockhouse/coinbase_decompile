package com.google.android.gms.auth.api.signin;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.internal.zzbcl;
import java.util.List;

public final class zzb implements Creator<GoogleSignInAccount> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int zzd = zzbcl.zzd(parcel);
        int i = 0;
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        Uri uri = null;
        String str5 = null;
        long j = 0;
        String str6 = null;
        List list = null;
        String str7 = null;
        String str8 = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    i = zzbcl.zzg(parcel, readInt);
                    break;
                case 2:
                    str = zzbcl.zzq(parcel, readInt);
                    break;
                case 3:
                    str2 = zzbcl.zzq(parcel, readInt);
                    break;
                case 4:
                    str3 = zzbcl.zzq(parcel, readInt);
                    break;
                case 5:
                    str4 = zzbcl.zzq(parcel, readInt);
                    break;
                case 6:
                    uri = (Uri) zzbcl.zza(parcel, readInt, Uri.CREATOR);
                    break;
                case 7:
                    str5 = zzbcl.zzq(parcel, readInt);
                    break;
                case 8:
                    j = zzbcl.zzi(parcel, readInt);
                    break;
                case 9:
                    str6 = zzbcl.zzq(parcel, readInt);
                    break;
                case 10:
                    list = zzbcl.zzc(parcel, readInt, Scope.CREATOR);
                    break;
                case 11:
                    str7 = zzbcl.zzq(parcel, readInt);
                    break;
                case 12:
                    str8 = zzbcl.zzq(parcel, readInt);
                    break;
                default:
                    zzbcl.zzb(parcel, readInt);
                    break;
            }
        }
        zzbcl.zzaf(parcel, zzd);
        return new GoogleSignInAccount(i, str, str2, str3, str4, uri, str5, j, str6, list, str7, str8);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new GoogleSignInAccount[i];
    }
}
