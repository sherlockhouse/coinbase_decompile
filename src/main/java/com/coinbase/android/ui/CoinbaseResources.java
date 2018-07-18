package com.coinbase.android.ui;

import java.lang.annotation.Documented;
import javax.inject.Qualifier;

public interface CoinbaseResources {

    @Qualifier
    @Documented
    public @interface GenericErrorMessage {
    }

    @Qualifier
    @Documented
    public @interface GenericErrorTryAgainMessage {
    }
}
