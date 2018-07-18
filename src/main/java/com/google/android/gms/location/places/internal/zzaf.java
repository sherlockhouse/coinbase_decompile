package com.google.android.gms.location.places.internal;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;
import java.util.Collections;
import java.util.List;

public final class zzaf extends zzbck {
    public static final Creator<zzaf> CREATOR = new zzag();
    private final List<Integer> zziaj;
    private final String zziak;
    private final Uri zzial;
    private final float zzidc;
    private final int zzidd;

    zzaf(List<Integer> list, String str, Uri uri, float f, int i) {
        this.zziaj = Collections.unmodifiableList(list);
        this.zziak = str;
        this.zzial = uri;
        this.zzidc = f;
        this.zzidd = i;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbcn.zze(parcel);
        zzbcn.zza(parcel, 1, this.zziaj, false);
        zzbcn.zza(parcel, 2, this.zziak, false);
        zzbcn.zza(parcel, 3, this.zzial, i, false);
        zzbcn.zza(parcel, 4, this.zzidc);
        zzbcn.zzc(parcel, 5, this.zzidd);
        zzbcn.zzai(parcel, zze);
    }
}
