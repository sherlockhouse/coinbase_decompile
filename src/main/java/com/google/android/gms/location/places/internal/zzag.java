package com.google.android.gms.location.places.internal;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.zzbcl;
import java.util.List;

public final class zzag implements Creator<zzaf> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        Uri uri = null;
        int zzd = zzbcl.zzd(parcel);
        float f = 0.0f;
        int i = 0;
        String str = null;
        List list = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    list = zzbcl.zzab(parcel, readInt);
                    break;
                case 2:
                    str = zzbcl.zzq(parcel, readInt);
                    break;
                case 3:
                    uri = (Uri) zzbcl.zza(parcel, readInt, Uri.CREATOR);
                    break;
                case 4:
                    f = zzbcl.zzl(parcel, readInt);
                    break;
                case 5:
                    i = zzbcl.zzg(parcel, readInt);
                    break;
                default:
                    zzbcl.zzb(parcel, readInt);
                    break;
            }
        }
        zzbcl.zzaf(parcel, zzd);
        return new zzaf(list, str, uri, f, i);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzaf[i];
    }
}
