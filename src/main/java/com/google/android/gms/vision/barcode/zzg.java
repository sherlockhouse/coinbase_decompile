package com.google.android.gms.vision.barcode;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.zzbcl;
import com.google.android.gms.vision.barcode.Barcode.DriverLicense;

public final class zzg implements Creator<DriverLicense> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int zzd = zzbcl.zzd(parcel);
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        String str7 = null;
        String str8 = null;
        String str9 = null;
        String str10 = null;
        String str11 = null;
        String str12 = null;
        String str13 = null;
        String str14 = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
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
                    str5 = zzbcl.zzq(parcel, readInt);
                    break;
                case 7:
                    str6 = zzbcl.zzq(parcel, readInt);
                    break;
                case 8:
                    str7 = zzbcl.zzq(parcel, readInt);
                    break;
                case 9:
                    str8 = zzbcl.zzq(parcel, readInt);
                    break;
                case 10:
                    str9 = zzbcl.zzq(parcel, readInt);
                    break;
                case 11:
                    str10 = zzbcl.zzq(parcel, readInt);
                    break;
                case 12:
                    str11 = zzbcl.zzq(parcel, readInt);
                    break;
                case 13:
                    str12 = zzbcl.zzq(parcel, readInt);
                    break;
                case 14:
                    str13 = zzbcl.zzq(parcel, readInt);
                    break;
                case 15:
                    str14 = zzbcl.zzq(parcel, readInt);
                    break;
                default:
                    zzbcl.zzb(parcel, readInt);
                    break;
            }
        }
        zzbcl.zzaf(parcel, zzd);
        return new DriverLicense(str, str2, str3, str4, str5, str6, str7, str8, str9, str10, str11, str12, str13, str14);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new DriverLicense[i];
    }
}
