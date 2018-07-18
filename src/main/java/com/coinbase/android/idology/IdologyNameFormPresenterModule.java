package com.coinbase.android.idology;

import com.coinbase.android.ControllerScope;
import com.coinbase.android.idology.IdologyTrackingContextProvider.IdologyTrackingContext;
import com.coinbase.api.internal.ApiConstants;
import java.util.Arrays;
import java.util.List;

public class IdologyNameFormPresenterModule {
    private final String mIdologyTrackingContext;
    private final IdologyNameFormScreen mScreen;

    public IdologyNameFormPresenterModule(IdologyNameFormScreen screen, String idologyTrackingContext) {
        this.mScreen = screen;
        this.mIdologyTrackingContext = idologyTrackingContext;
    }

    @ControllerScope
    public IdologyNameFormScreen providesIdologyScreen() {
        return this.mScreen;
    }

    @IdologyHandledFields
    @ControllerScope
    List<String> providesIdologyHandledFields() {
        return Arrays.asList(new String[]{ApiConstants.FIRST_NAME, ApiConstants.LAST_NAME, ApiConstants.DOB_DAY, ApiConstants.DOB_MONTH, ApiConstants.DOB_YEAR, ApiConstants.SSN_LAST4});
    }

    @IdologyTrackingContext
    @ControllerScope
    String providesIdologyTrackingContext() {
        return this.mIdologyTrackingContext;
    }
}
