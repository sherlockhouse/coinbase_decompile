package com.google.android.gms.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.vision.barcode.Barcode;

public interface zzdcf extends IInterface {
    Barcode[] zza(IObjectWrapper iObjectWrapper, zzdck com_google_android_gms_internal_zzdck) throws RemoteException;

    Barcode[] zzb(IObjectWrapper iObjectWrapper, zzdck com_google_android_gms_internal_zzdck) throws RemoteException;

    void zzbin() throws RemoteException;
}
