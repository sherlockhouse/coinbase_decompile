package com.google.android.gms.location.places;

import com.google.android.gms.common.api.Api.ApiOptions.Optional;
import com.google.android.gms.common.internal.zzbf;
import java.util.Arrays;
import java.util.Locale;

public final class PlacesOptions implements Optional {
    public final String zzdxf;
    public final String zzibt;
    public final String zzibu;
    public final int zzibv;
    public final Locale zzibw;

    public static class Builder {
        private int zzibv = 0;

        public PlacesOptions build() {
            return new PlacesOptions();
        }
    }

    private PlacesOptions(Builder builder) {
        this.zzibt = null;
        this.zzibu = null;
        this.zzibv = 0;
        this.zzdxf = null;
        this.zzibw = null;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof PlacesOptions) && zzbf.equal(null, null) && zzbf.equal(null, null) && zzbf.equal(Integer.valueOf(0), Integer.valueOf(0)) && zzbf.equal(null, null) && zzbf.equal(null, null);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{null, null, Integer.valueOf(0), null, null});
    }
}
