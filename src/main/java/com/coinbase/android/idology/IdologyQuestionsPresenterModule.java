package com.coinbase.android.idology;

import com.coinbase.android.ControllerScope;
import com.coinbase.android.idology.IdologyTrackingContextProvider.IdologyTrackingContext;

public class IdologyQuestionsPresenterModule {
    private final String mIdologyTrackingContext;
    private final IdologyQuestionsScreen mScreen;

    public IdologyQuestionsPresenterModule(IdologyQuestionsScreen screen, String idologyTrackingContext) {
        this.mScreen = screen;
        this.mIdologyTrackingContext = idologyTrackingContext;
    }

    @ControllerScope
    public IdologyQuestionsScreen providesIdologyQuestionsScreen() {
        return this.mScreen;
    }

    @IdologyTrackingContext
    @ControllerScope
    String providesIdologyTrackingContext() {
        return this.mIdologyTrackingContext;
    }
}
