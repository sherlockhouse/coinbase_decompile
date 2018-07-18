package com.coinbase.android;

import android.app.Application;
import com.coinbase.android.utils.GoogleApiClientWrapper;
import rx.functions.Func0;

public class GoogleClientApiModule {
    @ApplicationScope
    public Func0<GoogleApiClientWrapper> providesGoogleApiClient(Application app) {
        return GoogleClientApiModule$$Lambda$1.lambdaFactory$(app);
    }
}
