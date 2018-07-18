package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbf;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;
import java.util.Arrays;
import java.util.List;

@Deprecated
public final class zzak extends zzbck {
    public static final Creator<zzak> CREATOR = new zzal();
    private String address;
    private String name;
    private String zzidq;
    private String zzidr;
    private List<String> zzids;

    public zzak(String str, String str2, String str3, String str4, List<String> list) {
        this.name = str;
        this.address = str2;
        this.zzidq = str3;
        this.zzidr = str4;
        this.zzids = list;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzak)) {
            return false;
        }
        zzak com_google_android_gms_location_places_internal_zzak = (zzak) obj;
        return zzbf.equal(this.name, com_google_android_gms_location_places_internal_zzak.name) && zzbf.equal(this.address, com_google_android_gms_location_places_internal_zzak.address) && zzbf.equal(this.zzidq, com_google_android_gms_location_places_internal_zzak.zzidq) && zzbf.equal(this.zzidr, com_google_android_gms_location_places_internal_zzak.zzidr) && zzbf.equal(this.zzids, com_google_android_gms_location_places_internal_zzak.zzids);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.name, this.address, this.zzidq, this.zzidr});
    }

    public final String toString() {
        return zzbf.zzt(this).zzg("name", this.name).zzg("address", this.address).zzg("internationalPhoneNumber", this.zzidq).zzg("regularOpenHours", this.zzidr).zzg("attributions", this.zzids).toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbcn.zze(parcel);
        zzbcn.zza(parcel, 1, this.name, false);
        zzbcn.zza(parcel, 2, this.address, false);
        zzbcn.zza(parcel, 3, this.zzidq, false);
        zzbcn.zza(parcel, 4, this.zzidr, false);
        zzbcn.zzb(parcel, 5, this.zzids, false);
        zzbcn.zzai(parcel, zze);
    }
}
