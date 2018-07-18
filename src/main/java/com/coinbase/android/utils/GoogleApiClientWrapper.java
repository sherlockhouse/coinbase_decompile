package com.coinbase.android.utils;

import android.app.Application;
import android.content.Context;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.places.PlacesOptions;

public class GoogleApiClientWrapper {
    private Api<PlacesOptions>[] mApiOptions;
    private ConnectionCallbacks mConnectionCallbacks;
    private final Context mContext;
    private GoogleApiClient mGoogleApiClient;
    private OnConnectionFailedListener mOnConnectionFailedListener;

    public GoogleApiClientWrapper(Application app) {
        this.mContext = app;
    }

    public void init(Api<PlacesOptions>[] apiOptions, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        this.mApiOptions = apiOptions;
        this.mConnectionCallbacks = connectionCallbacks;
        this.mOnConnectionFailedListener = onConnectionFailedListener;
        Builder googleApiClientBuilder = new Builder(this.mContext);
        for (Api<PlacesOptions> placesOptionsApi : this.mApiOptions) {
            googleApiClientBuilder = googleApiClientBuilder.addApi(placesOptionsApi);
        }
        this.mGoogleApiClient = googleApiClientBuilder.addConnectionCallbacks(this.mConnectionCallbacks).addOnConnectionFailedListener(this.mOnConnectionFailedListener).build();
    }

    public void connect() {
        this.mGoogleApiClient.connect();
    }

    public void disconnect() {
        this.mGoogleApiClient.disconnect();
    }
}
