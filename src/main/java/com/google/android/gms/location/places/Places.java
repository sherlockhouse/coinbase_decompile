package com.google.android.gms.location.places;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zzf;
import com.google.android.gms.location.places.internal.zzab;
import com.google.android.gms.location.places.internal.zzad;
import com.google.android.gms.location.places.internal.zzh;
import com.google.android.gms.location.places.internal.zzn;
import com.google.android.gms.location.places.internal.zzp;
import com.google.android.gms.location.places.internal.zzy;

public class Places {
    public static final Api<PlacesOptions> GEO_DATA_API = new Api("Places.GEO_DATA_API", new zzp(), zzibm);
    public static final GeoDataApi GeoDataApi = new zzh();
    public static final Api<PlacesOptions> PLACE_DETECTION_API = new Api("Places.PLACE_DETECTION_API", new zzad(), zzibn);
    public static final PlaceDetectionApi PlaceDetectionApi = new zzy();
    private static zzf<zzn> zzibm = new zzf();
    private static zzf<zzab> zzibn = new zzf();
}
