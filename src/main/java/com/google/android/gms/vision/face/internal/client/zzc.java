package com.google.android.gms.vision.face.internal.client;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;

public final class zzc extends zzbck {
    public static final Creator<zzc> CREATOR = new zzd();
    public int mode;
    public int zzkjj;
    public int zzkjk;
    public boolean zzkjl;
    public boolean zzkjm;
    public float zzkjn;

    public zzc(int i, int i2, int i3, boolean z, boolean z2, float f) {
        this.mode = i;
        this.zzkjj = i2;
        this.zzkjk = i3;
        this.zzkjl = z;
        this.zzkjm = z2;
        this.zzkjn = f;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbcn.zze(parcel);
        zzbcn.zzc(parcel, 2, this.mode);
        zzbcn.zzc(parcel, 3, this.zzkjj);
        zzbcn.zzc(parcel, 4, this.zzkjk);
        zzbcn.zza(parcel, 5, this.zzkjl);
        zzbcn.zza(parcel, 6, this.zzkjm);
        zzbcn.zza(parcel, 7, this.zzkjn);
        zzbcn.zzai(parcel, zze);
    }
}
