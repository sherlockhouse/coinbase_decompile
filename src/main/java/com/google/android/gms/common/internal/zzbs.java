package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;

public final class zzbs extends zzbck {
    public static final Creator<zzbs> CREATOR = new zzbt();
    private int zzdxs;
    private ConnectionResult zzfiz;
    private boolean zzflu;
    private IBinder zzfvz;
    private boolean zzfwa;

    zzbs(int i, IBinder iBinder, ConnectionResult connectionResult, boolean z, boolean z2) {
        this.zzdxs = i;
        this.zzfvz = iBinder;
        this.zzfiz = connectionResult;
        this.zzflu = z;
        this.zzfwa = z2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzbs)) {
            return false;
        }
        zzbs com_google_android_gms_common_internal_zzbs = (zzbs) obj;
        return this.zzfiz.equals(com_google_android_gms_common_internal_zzbs.zzfiz) && zzakl().equals(com_google_android_gms_common_internal_zzbs.zzakl());
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbcn.zze(parcel);
        zzbcn.zzc(parcel, 1, this.zzdxs);
        zzbcn.zza(parcel, 2, this.zzfvz, false);
        zzbcn.zza(parcel, 3, this.zzfiz, i, false);
        zzbcn.zza(parcel, 4, this.zzflu);
        zzbcn.zza(parcel, 5, this.zzfwa);
        zzbcn.zzai(parcel, zze);
    }

    public final ConnectionResult zzagd() {
        return this.zzfiz;
    }

    public final zzam zzakl() {
        IBinder iBinder = this.zzfvz;
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.IAccountAccessor");
        return queryLocalInterface instanceof zzam ? (zzam) queryLocalInterface : new zzao(iBinder);
    }

    public final boolean zzakm() {
        return this.zzflu;
    }

    public final boolean zzakn() {
        return this.zzfwa;
    }
}
