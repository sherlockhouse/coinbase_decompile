package com.google.android.gms.location.places;

import com.google.android.gms.maps.model.LatLng;

public interface Place {
    LatLng getLatLng();

    CharSequence getName();
}
