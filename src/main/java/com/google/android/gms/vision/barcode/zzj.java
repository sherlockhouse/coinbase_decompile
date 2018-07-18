package com.google.android.gms.vision.barcode;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.zzbcl;
import com.google.android.gms.vision.barcode.Barcode.PersonName;

public final class zzj implements Creator<PersonName> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        String str = null;
        int zzd = zzbcl.zzd(parcel);
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        String str7 = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    str7 = zzbcl.zzq(parcel, readInt);
                    break;
                case 3:
                    str6 = zzbcl.zzq(parcel, readInt);
                    break;
                case 4:
                    str5 = zzbcl.zzq(parcel, readInt);
                    break;
                case 5:
                    str4 = zzbcl.zzq(parcel, readInt);
                    break;
                case 6:
                    str3 = zzbcl.zzq(parcel, readInt);
                    break;
                case 7:
                    str2 = zzbcl.zzq(parcel, readInt);
                    break;
                case 8:
                    str = zzbcl.zzq(parcel, readInt);
                    break;
                default:
                    zzbcl.zzb(parcel, readInt);
                    break;
            }
        }
        zzbcl.zzaf(parcel, zzd);
        return new PersonName(str7, str6, str5, str4, str3, str2, str);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new PersonName[i];
    }
}
