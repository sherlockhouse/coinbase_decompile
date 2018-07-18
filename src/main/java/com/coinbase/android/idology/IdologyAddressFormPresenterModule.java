package com.coinbase.android.idology;

import com.coinbase.android.ControllerScope;
import com.coinbase.android.idology.IdologyTrackingContextProvider.IdologyTrackingContext;
import com.coinbase.api.internal.ApiConstants;
import java.util.Arrays;
import java.util.List;

public class IdologyAddressFormPresenterModule {
    private final String mIdologyTrackingContext;
    private final IdologyAddressFormScreen mScreen;

    public IdologyAddressFormPresenterModule(IdologyAddressFormScreen screen, String idologyTrackingContext) {
        this.mScreen = screen;
        this.mIdologyTrackingContext = idologyTrackingContext;
    }

    @ControllerScope
    public IdologyAddressFormScreen providesIdologyScreen() {
        return this.mScreen;
    }

    @IdologyHandledFields
    @ControllerScope
    List<String> providesIdologyHandledFields() {
        return Arrays.asList(new String[]{ApiConstants.ADDRESS1, ApiConstants.ADDRESS2, ApiConstants.CITY, "state", ApiConstants.ZIP});
    }

    @IdologyTrackingContext
    @ControllerScope
    String providesIdologyTrackingContext() {
        return this.mIdologyTrackingContext;
    }
}
