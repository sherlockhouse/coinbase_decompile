package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;
import java.util.Collections;
import java.util.List;

public final class zzam extends zzbck {
    public static final Creator<zzam> CREATOR = new zzap();
    private List<zzan> zzidt;
    private List<zzao> zzidu;

    zzam(List<zzan> list, List<zzao> list2) {
        this.zzidt = Collections.unmodifiableList(list);
        this.zzidu = Collections.unmodifiableList(list2);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbcn.zze(parcel);
        zzbcn.zzc(parcel, 1, this.zzidt, false);
        zzbcn.zzc(parcel, 2, this.zzidu, false);
        zzbcn.zzai(parcel, zze);
    }
}
