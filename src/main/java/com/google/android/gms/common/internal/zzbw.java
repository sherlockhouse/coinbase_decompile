package com.google.android.gms.common.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.view.View;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.dynamic.zzp;
import com.google.android.gms.dynamic.zzq;

public final class zzbw extends zzp<zzbc> {
    private static final zzbw zzfwe = new zzbw();

    private zzbw() {
        super("com.google.android.gms.common.ui.SignInButtonCreatorImpl");
    }

    public static View zzc(Context context, int i, int i2) throws zzq {
        return zzfwe.zzd(context, i, i2);
    }

    private final View zzd(Context context, int i, int i2) throws zzq {
        try {
            zzbu com_google_android_gms_common_internal_zzbu = new zzbu(i, i2, null);
            return (View) zzn.zzx(((zzbc) zzcu(context)).zza(zzn.zzw(context), com_google_android_gms_common_internal_zzbu));
        } catch (Throwable e) {
            throw new zzq("Could not get button with size " + i + " and color " + i2, e);
        }
    }

    public final /* synthetic */ Object zze(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.ISignInButtonCreator");
        return queryLocalInterface instanceof zzbc ? (zzbc) queryLocalInterface : new zzbd(iBinder);
    }
}
