package com.google.android.gms.auth.api.credentials;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.zzbcl;

public final class zze implements Creator<CredentialRequest> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        boolean z = false;
        String str = null;
        int zzd = zzbcl.zzd(parcel);
        String str2 = null;
        boolean z2 = false;
        CredentialPickerConfig credentialPickerConfig = null;
        CredentialPickerConfig credentialPickerConfig2 = null;
        String[] strArr = null;
        boolean z3 = false;
        int i = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    z3 = zzbcl.zzc(parcel, readInt);
                    break;
                case 2:
                    strArr = zzbcl.zzaa(parcel, readInt);
                    break;
                case 3:
                    credentialPickerConfig2 = (CredentialPickerConfig) zzbcl.zza(parcel, readInt, CredentialPickerConfig.CREATOR);
                    break;
                case 4:
                    credentialPickerConfig = (CredentialPickerConfig) zzbcl.zza(parcel, readInt, CredentialPickerConfig.CREATOR);
                    break;
                case 5:
                    z2 = zzbcl.zzc(parcel, readInt);
                    break;
                case 6:
                    str2 = zzbcl.zzq(parcel, readInt);
                    break;
                case 7:
                    str = zzbcl.zzq(parcel, readInt);
                    break;
                case 8:
                    z = zzbcl.zzc(parcel, readInt);
                    break;
                case 1000:
                    i = zzbcl.zzg(parcel, readInt);
                    break;
                default:
                    zzbcl.zzb(parcel, readInt);
                    break;
            }
        }
        zzbcl.zzaf(parcel, zzd);
        return new CredentialRequest(i, z3, strArr, credentialPickerConfig2, credentialPickerConfig, z2, str2, str, z);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new CredentialRequest[i];
    }
}
