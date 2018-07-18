package com.google.android.gms.location.places.ui;

import android.content.Context;
import android.content.Intent;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.internal.zzbcp;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.internal.PlaceEntity;

class zza {
    public static Place getPlace(Context context, Intent intent) {
        zzbp.zzb((Object) intent, (Object) "intent must not be null");
        zzbp.zzb((Object) context, (Object) "context must not be null");
        return (Place) zzbcp.zza(intent, "selected_place", PlaceEntity.CREATOR);
    }

    public static Status getStatus(Context context, Intent intent) {
        zzbp.zzb((Object) intent, (Object) "intent must not be null");
        zzbp.zzb((Object) context, (Object) "context must not be null");
        return (Status) zzbcp.zza(intent, "status", Status.CREATOR);
    }
}
