package com.google.android.gms.auth.api.credentials;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbf;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Credential extends zzbck implements ReflectedParcelable {
    public static final Creator<Credential> CREATOR = new zza();
    private final String mName;
    private final String zzbsx;
    private final String zzdzr;
    private final Uri zzeaa;
    private final List<IdToken> zzeab;
    private final String zzeac;
    private final String zzead;
    private final String zzeae;
    private final String zzeaf;
    private final String zzeag;

    public static class Builder {
        private String mName;
        private final String zzbsx;
        private String zzdzr;
        private Uri zzeaa;
        private List<IdToken> zzeab;
        private String zzeac;
        private String zzead;
        private String zzeae;
        private String zzeaf;
        private String zzeag;

        public Builder(String str) {
            this.zzbsx = str;
        }

        public Credential build() {
            return new Credential(this.zzbsx, this.mName, this.zzeaa, this.zzeab, this.zzeac, this.zzdzr, this.zzead, this.zzeae, this.zzeaf, this.zzeag);
        }

        public Builder setName(String str) {
            this.mName = str;
            return this;
        }

        public Builder setPassword(String str) {
            this.zzeac = str;
            return this;
        }
    }

    Credential(String str, String str2, Uri uri, List<IdToken> list, String str3, String str4, String str5, String str6, String str7, String str8) {
        String trim = ((String) zzbp.zzb((Object) str, (Object) "credential identifier cannot be null")).trim();
        zzbp.zzh(trim, "credential identifier cannot be empty");
        if (str3 == null || !TextUtils.isEmpty(str3)) {
            if (str4 != null) {
                boolean z;
                if (!TextUtils.isEmpty(str4)) {
                    Uri parse = Uri.parse(str4);
                    if (!parse.isAbsolute() || !parse.isHierarchical() || TextUtils.isEmpty(parse.getScheme()) || TextUtils.isEmpty(parse.getAuthority())) {
                        z = false;
                        if (!Boolean.valueOf(z).booleanValue()) {
                            throw new IllegalArgumentException("Account type must be a valid Http/Https URI");
                        }
                    } else if ("http".equalsIgnoreCase(parse.getScheme()) || "https".equalsIgnoreCase(parse.getScheme())) {
                        z = true;
                        if (Boolean.valueOf(z).booleanValue()) {
                            throw new IllegalArgumentException("Account type must be a valid Http/Https URI");
                        }
                    }
                }
                z = false;
                if (Boolean.valueOf(z).booleanValue()) {
                    throw new IllegalArgumentException("Account type must be a valid Http/Https URI");
                }
            }
            if (TextUtils.isEmpty(str4) || TextUtils.isEmpty(str3)) {
                if (str2 != null && TextUtils.isEmpty(str2.trim())) {
                    str2 = null;
                }
                this.mName = str2;
                this.zzeaa = uri;
                this.zzeab = list == null ? Collections.emptyList() : Collections.unmodifiableList(list);
                this.zzbsx = trim;
                this.zzeac = str3;
                this.zzdzr = str4;
                this.zzead = str5;
                this.zzeae = str6;
                this.zzeaf = str7;
                this.zzeag = str8;
                return;
            }
            throw new IllegalArgumentException("Password and AccountType are mutually exclusive");
        }
        throw new IllegalArgumentException("Password must not be empty if set");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Credential)) {
            return false;
        }
        Credential credential = (Credential) obj;
        return TextUtils.equals(this.zzbsx, credential.zzbsx) && TextUtils.equals(this.mName, credential.mName) && zzbf.equal(this.zzeaa, credential.zzeaa) && TextUtils.equals(this.zzeac, credential.zzeac) && TextUtils.equals(this.zzdzr, credential.zzdzr) && TextUtils.equals(this.zzead, credential.zzead);
    }

    public String getAccountType() {
        return this.zzdzr;
    }

    public String getFamilyName() {
        return this.zzeag;
    }

    public String getGeneratedPassword() {
        return this.zzead;
    }

    public String getGivenName() {
        return this.zzeaf;
    }

    public String getId() {
        return this.zzbsx;
    }

    public List<IdToken> getIdTokens() {
        return this.zzeab;
    }

    public String getName() {
        return this.mName;
    }

    public String getPassword() {
        return this.zzeac;
    }

    public Uri getProfilePictureUri() {
        return this.zzeaa;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzbsx, this.mName, this.zzeaa, this.zzeac, this.zzdzr, this.zzead});
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbcn.zze(parcel);
        zzbcn.zza(parcel, 1, getId(), false);
        zzbcn.zza(parcel, 2, getName(), false);
        zzbcn.zza(parcel, 3, getProfilePictureUri(), i, false);
        zzbcn.zzc(parcel, 4, getIdTokens(), false);
        zzbcn.zza(parcel, 5, getPassword(), false);
        zzbcn.zza(parcel, 6, getAccountType(), false);
        zzbcn.zza(parcel, 7, getGeneratedPassword(), false);
        zzbcn.zza(parcel, 8, this.zzeae, false);
        zzbcn.zza(parcel, 9, getGivenName(), false);
        zzbcn.zza(parcel, 10, getFamilyName(), false);
        zzbcn.zzai(parcel, zze);
    }
}
