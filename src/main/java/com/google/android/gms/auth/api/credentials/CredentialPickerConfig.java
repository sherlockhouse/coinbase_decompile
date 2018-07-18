package com.google.android.gms.auth.api.credentials;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;

public final class CredentialPickerConfig extends zzbck implements ReflectedParcelable {
    public static final Creator<CredentialPickerConfig> CREATOR = new zzc();
    private final boolean mShowCancelButton;
    private int zzdxs;
    private final boolean zzeah;
    @Deprecated
    private final boolean zzeai;
    private final int zzeaj;

    public static class Builder {
        private boolean mShowCancelButton = true;
        private boolean zzeah = false;
        private int zzeak = 1;

        public CredentialPickerConfig build() {
            return new CredentialPickerConfig();
        }
    }

    CredentialPickerConfig(int i, boolean z, boolean z2, boolean z3, int i2) {
        int i3 = 3;
        boolean z4 = true;
        this.zzdxs = i;
        this.zzeah = z;
        this.mShowCancelButton = z2;
        if (i < 2) {
            this.zzeai = z3;
            if (!z3) {
                i3 = 1;
            }
            this.zzeaj = i3;
            return;
        }
        if (i2 != 3) {
            z4 = false;
        }
        this.zzeai = z4;
        this.zzeaj = i2;
    }

    private CredentialPickerConfig(Builder builder) {
        this(2, builder.zzeah, builder.mShowCancelButton, false, builder.zzeak);
    }

    @Deprecated
    public final boolean isForNewAccount() {
        return this.zzeaj == 3;
    }

    public final boolean shouldShowAddAccountButton() {
        return this.zzeah;
    }

    public final boolean shouldShowCancelButton() {
        return this.mShowCancelButton;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbcn.zze(parcel);
        zzbcn.zza(parcel, 1, shouldShowAddAccountButton());
        zzbcn.zza(parcel, 2, shouldShowCancelButton());
        zzbcn.zza(parcel, 3, isForNewAccount());
        zzbcn.zzc(parcel, 4, this.zzeaj);
        zzbcn.zzc(parcel, 1000, this.zzdxs);
        zzbcn.zzai(parcel, zze);
    }
}
