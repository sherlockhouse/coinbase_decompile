package com.google.android.gms.auth.api.credentials;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;

public final class CredentialRequest extends zzbck {
    public static final Creator<CredentialRequest> CREATOR = new zze();
    private int zzdxs;
    private final boolean zzeal;
    private final String[] zzeam;
    private final CredentialPickerConfig zzean;
    private final CredentialPickerConfig zzeao;
    private final boolean zzeap;
    private final String zzeaq;
    private final String zzear;
    private final boolean zzeas;

    public static final class Builder {
        private boolean zzeal;
        private String[] zzeam;
        private CredentialPickerConfig zzean;
        private CredentialPickerConfig zzeao;
        private boolean zzeap = false;
        private String zzeaq = null;
        private String zzear;
        private boolean zzeas = false;

        public final CredentialRequest build() {
            if (this.zzeam == null) {
                this.zzeam = new String[0];
            }
            if (this.zzeal || this.zzeam.length != 0) {
                return new CredentialRequest();
            }
            throw new IllegalStateException("At least one authentication method must be specified");
        }

        public final Builder setAccountTypes(String... strArr) {
            if (strArr == null) {
                strArr = new String[0];
            }
            this.zzeam = strArr;
            return this;
        }

        public final Builder setPasswordLoginSupported(boolean z) {
            this.zzeal = z;
            return this;
        }
    }

    CredentialRequest(int i, boolean z, String[] strArr, CredentialPickerConfig credentialPickerConfig, CredentialPickerConfig credentialPickerConfig2, boolean z2, String str, String str2, boolean z3) {
        this.zzdxs = i;
        this.zzeal = z;
        this.zzeam = (String[]) zzbp.zzu(strArr);
        if (credentialPickerConfig == null) {
            credentialPickerConfig = new com.google.android.gms.auth.api.credentials.CredentialPickerConfig.Builder().build();
        }
        this.zzean = credentialPickerConfig;
        if (credentialPickerConfig2 == null) {
            credentialPickerConfig2 = new com.google.android.gms.auth.api.credentials.CredentialPickerConfig.Builder().build();
        }
        this.zzeao = credentialPickerConfig2;
        if (i < 3) {
            this.zzeap = true;
            this.zzeaq = null;
            this.zzear = null;
        } else {
            this.zzeap = z2;
            this.zzeaq = str;
            this.zzear = str2;
        }
        this.zzeas = z3;
    }

    private CredentialRequest(Builder builder) {
        this(4, builder.zzeal, builder.zzeam, builder.zzean, builder.zzeao, builder.zzeap, builder.zzeaq, builder.zzear, false);
    }

    public final String[] getAccountTypes() {
        return this.zzeam;
    }

    public final CredentialPickerConfig getCredentialHintPickerConfig() {
        return this.zzeao;
    }

    public final CredentialPickerConfig getCredentialPickerConfig() {
        return this.zzean;
    }

    public final String getIdTokenNonce() {
        return this.zzear;
    }

    public final String getServerClientId() {
        return this.zzeaq;
    }

    public final boolean isIdTokenRequested() {
        return this.zzeap;
    }

    public final boolean isPasswordLoginSupported() {
        return this.zzeal;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbcn.zze(parcel);
        zzbcn.zza(parcel, 1, isPasswordLoginSupported());
        zzbcn.zza(parcel, 2, getAccountTypes(), false);
        zzbcn.zza(parcel, 3, getCredentialPickerConfig(), i, false);
        zzbcn.zza(parcel, 4, getCredentialHintPickerConfig(), i, false);
        zzbcn.zza(parcel, 5, isIdTokenRequested());
        zzbcn.zza(parcel, 6, getServerClientId(), false);
        zzbcn.zza(parcel, 7, getIdTokenNonce(), false);
        zzbcn.zzc(parcel, 1000, this.zzdxs);
        zzbcn.zza(parcel, 8, this.zzeas);
        zzbcn.zzai(parcel, zze);
    }
}
