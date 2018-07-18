package com.coinbase.android.settings;

import com.coinbase.android.ui.CurrencySelectorConnector;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class ChangeNativeCurrencyDialogFragment_MembersInjector implements MembersInjector<ChangeNativeCurrencyDialogFragment> {
    private final Provider<CurrencySelectorConnector> mCurrencySelectorConnectorProvider;

    public ChangeNativeCurrencyDialogFragment_MembersInjector(Provider<CurrencySelectorConnector> mCurrencySelectorConnectorProvider) {
        this.mCurrencySelectorConnectorProvider = mCurrencySelectorConnectorProvider;
    }

    public static MembersInjector<ChangeNativeCurrencyDialogFragment> create(Provider<CurrencySelectorConnector> mCurrencySelectorConnectorProvider) {
        return new ChangeNativeCurrencyDialogFragment_MembersInjector(mCurrencySelectorConnectorProvider);
    }

    public void injectMembers(ChangeNativeCurrencyDialogFragment instance) {
        injectMCurrencySelectorConnector(instance, (CurrencySelectorConnector) this.mCurrencySelectorConnectorProvider.get());
    }

    public static void injectMCurrencySelectorConnector(ChangeNativeCurrencyDialogFragment instance, CurrencySelectorConnector mCurrencySelectorConnector) {
        instance.mCurrencySelectorConnector = mCurrencySelectorConnector;
    }
}
