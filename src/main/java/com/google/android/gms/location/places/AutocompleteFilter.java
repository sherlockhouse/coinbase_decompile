package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbf;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;
import java.util.Arrays;
import java.util.List;

public class AutocompleteFilter extends zzbck implements ReflectedParcelable {
    public static final Creator<AutocompleteFilter> CREATOR = new zzc();
    private int zzdxs;
    private boolean zziam;
    private List<Integer> zzian;
    private String zziao;
    private int zziap;

    public static final class Builder {
        private boolean zziam = false;
        private String zziao = "";
        private int zziap = 0;

        public final AutocompleteFilter build() {
            return new AutocompleteFilter(1, false, Arrays.asList(new Integer[]{Integer.valueOf(this.zziap)}), this.zziao);
        }

        public final Builder setCountry(String str) {
            this.zziao = str;
            return this;
        }

        public final Builder setTypeFilter(int i) {
            this.zziap = i;
            return this;
        }
    }

    AutocompleteFilter(int i, boolean z, List<Integer> list, String str) {
        boolean z2 = false;
        this.zzdxs = i;
        this.zzian = list;
        int intValue = (list == null || list.isEmpty()) ? 0 : ((Integer) list.iterator().next()).intValue();
        this.zziap = intValue;
        this.zziao = str;
        if (this.zzdxs <= 0) {
            if (!z) {
                z2 = true;
            }
            this.zziam = z2;
            return;
        }
        this.zziam = z;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AutocompleteFilter)) {
            return false;
        }
        AutocompleteFilter autocompleteFilter = (AutocompleteFilter) obj;
        return this.zziap == autocompleteFilter.zziap && this.zziam == autocompleteFilter.zziam && this.zziao == autocompleteFilter.zziao;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Boolean.valueOf(this.zziam), Integer.valueOf(this.zziap), this.zziao});
    }

    public String toString() {
        return zzbf.zzt(this).zzg("includeQueryPredictions", Boolean.valueOf(this.zziam)).zzg("typeFilter", Integer.valueOf(this.zziap)).zzg("country", this.zziao).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbcn.zze(parcel);
        zzbcn.zza(parcel, 1, this.zziam);
        zzbcn.zza(parcel, 2, this.zzian, false);
        zzbcn.zza(parcel, 3, this.zziao, false);
        zzbcn.zzc(parcel, 1000, this.zzdxs);
        zzbcn.zzai(parcel, zze);
    }
}
