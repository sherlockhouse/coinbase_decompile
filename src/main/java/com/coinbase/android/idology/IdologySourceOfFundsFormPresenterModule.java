package com.coinbase.android.idology;

import com.coinbase.android.ControllerScope;
import com.coinbase.android.idology.IdologyTrackingContextProvider.IdologyTrackingContext;
import com.coinbase.api.internal.ApiConstants;
import java.util.Arrays;
import java.util.List;

public class IdologySourceOfFundsFormPresenterModule {
    private final String mIdologyTrackingContext;
    private final IdologySourceOfFundsFormScreen mScreen;

    public IdologySourceOfFundsFormPresenterModule(IdologySourceOfFundsFormScreen screen, String idologyTrackingContext) {
        this.mScreen = screen;
        this.mIdologyTrackingContext = idologyTrackingContext;
    }

    @ControllerScope
    public IdologySourceOfFundsFormScreen providesIdologyScreen() {
        return this.mScreen;
    }

    @IdologyHandledFields
    @ControllerScope
    List<String> providesIdologyHandledFields() {
        return Arrays.asList(new String[]{ApiConstants.SOURCE_OF_FUNDS, ApiConstants.USES_COINBASE_FOR, ApiConstants.CURRENT_JOB_TITLE, ApiConstants.CURRENT_EMPLOYER});
    }

    @IdologyTrackingContext
    @ControllerScope
    String providesIdologyTrackingContext() {
        return this.mIdologyTrackingContext;
    }
}
