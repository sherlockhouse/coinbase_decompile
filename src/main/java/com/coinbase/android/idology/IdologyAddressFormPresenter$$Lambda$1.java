package com.coinbase.android.idology;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;

final /* synthetic */ class IdologyAddressFormPresenter$$Lambda$1 implements OnConnectionFailedListener {
    private final IdologyAddressFormPresenter arg$1;

    private IdologyAddressFormPresenter$$Lambda$1(IdologyAddressFormPresenter idologyAddressFormPresenter) {
        this.arg$1 = idologyAddressFormPresenter;
    }

    public static OnConnectionFailedListener lambdaFactory$(IdologyAddressFormPresenter idologyAddressFormPresenter) {
        return new IdologyAddressFormPresenter$$Lambda$1(idologyAddressFormPresenter);
    }

    public void onConnectionFailed(ConnectionResult connectionResult) {
        IdologyAddressFormPresenter.lambda$onShow$0(this.arg$1, connectionResult);
    }
}
