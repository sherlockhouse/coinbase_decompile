package org.joda.money;

import java.util.List;

public abstract class CurrencyUnitDataProvider {
    protected abstract void registerCurrencies() throws Exception;

    protected final void registerCurrency(String currencyCode, int numericCurrencyCode, int decimalPlaces, List<String> countryCodes) {
        CurrencyUnit.registerCurrency(currencyCode, numericCurrencyCode, decimalPlaces, countryCodes, true);
    }
}
