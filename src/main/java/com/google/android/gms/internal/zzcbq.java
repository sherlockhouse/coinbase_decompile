package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import java.util.List;

public final class zzcbq extends zzeb implements zzcbo {
    zzcbq(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.measurement.internal.IMeasurementService");
    }

    public final List<zzcft> zza(zzcas com_google_android_gms_internal_zzcas, boolean z) throws RemoteException {
        Parcel zzax = zzax();
        zzed.zza(zzax, (Parcelable) com_google_android_gms_internal_zzcas);
        zzed.zza(zzax, z);
        zzax = zza(7, zzax);
        List createTypedArrayList = zzax.createTypedArrayList(zzcft.CREATOR);
        zzax.recycle();
        return createTypedArrayList;
    }

    public final List<zzcav> zza(String str, String str2, zzcas com_google_android_gms_internal_zzcas) throws RemoteException {
        Parcel zzax = zzax();
        zzax.writeString(str);
        zzax.writeString(str2);
        zzed.zza(zzax, (Parcelable) com_google_android_gms_internal_zzcas);
        zzax = zza(16, zzax);
        List createTypedArrayList = zzax.createTypedArrayList(zzcav.CREATOR);
        zzax.recycle();
        return createTypedArrayList;
    }

    public final List<zzcft> zza(String str, String str2, String str3, boolean z) throws RemoteException {
        Parcel zzax = zzax();
        zzax.writeString(str);
        zzax.writeString(str2);
        zzax.writeString(str3);
        zzed.zza(zzax, z);
        zzax = zza(15, zzax);
        List createTypedArrayList = zzax.createTypedArrayList(zzcft.CREATOR);
        zzax.recycle();
        return createTypedArrayList;
    }

    public final List<zzcft> zza(String str, String str2, boolean z, zzcas com_google_android_gms_internal_zzcas) throws RemoteException {
        Parcel zzax = zzax();
        zzax.writeString(str);
        zzax.writeString(str2);
        zzed.zza(zzax, z);
        zzed.zza(zzax, (Parcelable) com_google_android_gms_internal_zzcas);
        zzax = zza(14, zzax);
        List createTypedArrayList = zzax.createTypedArrayList(zzcft.CREATOR);
        zzax.recycle();
        return createTypedArrayList;
    }

    public final void zza(long j, String str, String str2, String str3) throws RemoteException {
        Parcel zzax = zzax();
        zzax.writeLong(j);
        zzax.writeString(str);
        zzax.writeString(str2);
        zzax.writeString(str3);
        zzb(10, zzax);
    }

    public final void zza(zzcas com_google_android_gms_internal_zzcas) throws RemoteException {
        Parcel zzax = zzax();
        zzed.zza(zzax, (Parcelable) com_google_android_gms_internal_zzcas);
        zzb(4, zzax);
    }

    public final void zza(zzcav com_google_android_gms_internal_zzcav, zzcas com_google_android_gms_internal_zzcas) throws RemoteException {
        Parcel zzax = zzax();
        zzed.zza(zzax, (Parcelable) com_google_android_gms_internal_zzcav);
        zzed.zza(zzax, (Parcelable) com_google_android_gms_internal_zzcas);
        zzb(12, zzax);
    }

    public final void zza(zzcbk com_google_android_gms_internal_zzcbk, zzcas com_google_android_gms_internal_zzcas) throws RemoteException {
        Parcel zzax = zzax();
        zzed.zza(zzax, (Parcelable) com_google_android_gms_internal_zzcbk);
        zzed.zza(zzax, (Parcelable) com_google_android_gms_internal_zzcas);
        zzb(1, zzax);
    }

    public final void zza(zzcbk com_google_android_gms_internal_zzcbk, String str, String str2) throws RemoteException {
        Parcel zzax = zzax();
        zzed.zza(zzax, (Parcelable) com_google_android_gms_internal_zzcbk);
        zzax.writeString(str);
        zzax.writeString(str2);
        zzb(5, zzax);
    }

    public final void zza(zzcft com_google_android_gms_internal_zzcft, zzcas com_google_android_gms_internal_zzcas) throws RemoteException {
        Parcel zzax = zzax();
        zzed.zza(zzax, (Parcelable) com_google_android_gms_internal_zzcft);
        zzed.zza(zzax, (Parcelable) com_google_android_gms_internal_zzcas);
        zzb(2, zzax);
    }

    public final byte[] zza(zzcbk com_google_android_gms_internal_zzcbk, String str) throws RemoteException {
        Parcel zzax = zzax();
        zzed.zza(zzax, (Parcelable) com_google_android_gms_internal_zzcbk);
        zzax.writeString(str);
        zzax = zza(9, zzax);
        byte[] createByteArray = zzax.createByteArray();
        zzax.recycle();
        return createByteArray;
    }

    public final void zzb(zzcas com_google_android_gms_internal_zzcas) throws RemoteException {
        Parcel zzax = zzax();
        zzed.zza(zzax, (Parcelable) com_google_android_gms_internal_zzcas);
        zzb(6, zzax);
    }

    public final void zzb(zzcav com_google_android_gms_internal_zzcav) throws RemoteException {
        Parcel zzax = zzax();
        zzed.zza(zzax, (Parcelable) com_google_android_gms_internal_zzcav);
        zzb(13, zzax);
    }

    public final String zzc(zzcas com_google_android_gms_internal_zzcas) throws RemoteException {
        Parcel zzax = zzax();
        zzed.zza(zzax, (Parcelable) com_google_android_gms_internal_zzcas);
        zzax = zza(11, zzax);
        String readString = zzax.readString();
        zzax.recycle();
        return readString;
    }

    public final List<zzcav> zzj(String str, String str2, String str3) throws RemoteException {
        Parcel zzax = zzax();
        zzax.writeString(str);
        zzax.writeString(str2);
        zzax.writeString(str3);
        zzax = zza(17, zzax);
        List createTypedArrayList = zzax.createTypedArrayList(zzcav.CREATOR);
        zzax.recycle();
        return createTypedArrayList;
    }
}
