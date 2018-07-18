package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;

public final class zzbq extends zzbck {
    public static final Creator<zzbq> CREATOR = new zzbr();
    private final Account zzduz;
    private int zzdxs;
    private final int zzfvx;
    private final GoogleSignInAccount zzfvy;

    zzbq(int i, Account account, int i2, GoogleSignInAccount googleSignInAccount) {
        this.zzdxs = i;
        this.zzduz = account;
        this.zzfvx = i2;
        this.zzfvy = googleSignInAccount;
    }

    public zzbq(Account account, int i, GoogleSignInAccount googleSignInAccount) {
        this(2, account, i, googleSignInAccount);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbcn.zze(parcel);
        zzbcn.zzc(parcel, 1, this.zzdxs);
        zzbcn.zza(parcel, 2, this.zzduz, i, false);
        zzbcn.zzc(parcel, 3, this.zzfvx);
        zzbcn.zza(parcel, 4, this.zzfvy, i, false);
        zzbcn.zzai(parcel, zze);
    }
}
