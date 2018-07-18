package com.google.android.gms.auth.api.signin.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;

public final class SignInConfiguration extends zzbck implements ReflectedParcelable {
    public static final Creator<SignInConfiguration> CREATOR = new zzw();
    private final String zzedc;
    private GoogleSignInOptions zzedd;

    public SignInConfiguration(String str, GoogleSignInOptions googleSignInOptions) {
        this.zzedc = zzbp.zzgg(str);
        this.zzedd = googleSignInOptions;
    }

    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        try {
            SignInConfiguration signInConfiguration = (SignInConfiguration) obj;
            if (!this.zzedc.equals(signInConfiguration.zzedc)) {
                return false;
            }
            if (this.zzedd == null) {
                if (signInConfiguration.zzedd != null) {
                    return false;
                }
            } else if (!this.zzedd.equals(signInConfiguration.zzedd)) {
                return false;
            }
            return true;
        } catch (ClassCastException e) {
            return false;
        }
    }

    public final int hashCode() {
        return new zzo().zzo(this.zzedc).zzo(this.zzedd).zzaao();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbcn.zze(parcel);
        zzbcn.zza(parcel, 2, this.zzedc, false);
        zzbcn.zza(parcel, 5, this.zzedd, i, false);
        zzbcn.zzai(parcel, zze);
    }

    public final GoogleSignInOptions zzaaq() {
        return this.zzedd;
    }
}
