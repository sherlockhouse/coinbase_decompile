package com.google.android.gms.vision.face.internal.client;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.apps.common.proguard.UsedByNative;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;

@UsedByNative("wrapper.cc")
public class FaceParcel extends zzbck {
    public static final Creator<FaceParcel> CREATOR = new zzb();
    public final float centerX;
    public final float centerY;
    public final float height;
    public final int id;
    private int versionCode;
    public final float width;
    public final float zzkjd;
    public final float zzkje;
    public final LandmarkParcel[] zzkjf;
    public final float zzkjg;
    public final float zzkjh;
    public final float zzkji;

    public FaceParcel(int i, int i2, float f, float f2, float f3, float f4, float f5, float f6, LandmarkParcel[] landmarkParcelArr, float f7, float f8, float f9) {
        this.versionCode = i;
        this.id = i2;
        this.centerX = f;
        this.centerY = f2;
        this.width = f3;
        this.height = f4;
        this.zzkjd = f5;
        this.zzkje = f6;
        this.zzkjf = landmarkParcelArr;
        this.zzkjg = f7;
        this.zzkjh = f8;
        this.zzkji = f9;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbcn.zze(parcel);
        zzbcn.zzc(parcel, 1, this.versionCode);
        zzbcn.zzc(parcel, 2, this.id);
        zzbcn.zza(parcel, 3, this.centerX);
        zzbcn.zza(parcel, 4, this.centerY);
        zzbcn.zza(parcel, 5, this.width);
        zzbcn.zza(parcel, 6, this.height);
        zzbcn.zza(parcel, 7, this.zzkjd);
        zzbcn.zza(parcel, 8, this.zzkje);
        zzbcn.zza(parcel, 9, this.zzkjf, i, false);
        zzbcn.zza(parcel, 10, this.zzkjg);
        zzbcn.zza(parcel, 11, this.zzkjh);
        zzbcn.zza(parcel, 12, this.zzkji);
        zzbcn.zzai(parcel, zze);
    }
}
