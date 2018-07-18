package com.coinbase.android.ui;

import dagger.MembersInjector;
import javax.inject.Provider;

public final class NumericKeyboardLayout_MembersInjector implements MembersInjector<NumericKeyboardLayout> {
    private final Provider<NumericKeypadConnector> mNumericKeypadConnectorProvider;

    public NumericKeyboardLayout_MembersInjector(Provider<NumericKeypadConnector> mNumericKeypadConnectorProvider) {
        this.mNumericKeypadConnectorProvider = mNumericKeypadConnectorProvider;
    }

    public static MembersInjector<NumericKeyboardLayout> create(Provider<NumericKeypadConnector> mNumericKeypadConnectorProvider) {
        return new NumericKeyboardLayout_MembersInjector(mNumericKeypadConnectorProvider);
    }

    public void injectMembers(NumericKeyboardLayout instance) {
        injectMNumericKeypadConnector(instance, (NumericKeypadConnector) this.mNumericKeypadConnectorProvider.get());
    }

    public static void injectMNumericKeypadConnector(NumericKeyboardLayout instance, NumericKeypadConnector mNumericKeypadConnector) {
        instance.mNumericKeypadConnector = mNumericKeypadConnector;
    }
}
