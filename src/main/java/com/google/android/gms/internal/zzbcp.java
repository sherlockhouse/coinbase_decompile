package com.google.android.gms.internal;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbp;

public final class zzbcp {
    public static <T extends zzbco> T zza(Intent intent, String str, Creator<T> creator) {
        byte[] byteArrayExtra = intent.getByteArrayExtra(str);
        return byteArrayExtra == null ? null : zza(byteArrayExtra, creator);
    }

    public static <T extends zzbco> T zza(byte[] bArr, Creator<T> creator) {
        zzbp.zzu(creator);
        Parcel obtain = Parcel.obtain();
        obtain.unmarshall(bArr, 0, bArr.length);
        obtain.setDataPosition(0);
        zzbco com_google_android_gms_internal_zzbco = (zzbco) creator.createFromParcel(obtain);
        obtain.recycle();
        return com_google_android_gms_internal_zzbco;
    }
}
