package com.coinbase.android.idology;

import com.coinbase.android.ControllerScope;
import com.coinbase.android.idology.IdologyTrackingContextProvider.IdologyTrackingContext;

public class IdologyFormPresenterModule {
    private final String mIdologyTrackingContext;
    private final IdologyFormScreen mScreen;

    public IdologyFormPresenterModule(IdologyFormScreen screen, String idologyTrackingContext) {
        this.mScreen = screen;
        this.mIdologyTrackingContext = idologyTrackingContext;
    }

    @ControllerScope
    public IdologyFormScreen providesIdologyScreen() {
        return this.mScreen;
    }

    @IdologyTrackingContext
    @ControllerScope
    public String providesIdologyTrackingContext() {
        return this.mIdologyTrackingContext;
    }
}
