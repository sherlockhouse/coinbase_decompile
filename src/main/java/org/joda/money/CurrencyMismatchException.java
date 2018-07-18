package org.joda.money;

public class CurrencyMismatchException extends IllegalArgumentException {
    private final CurrencyUnit firstCurrency;
    private final CurrencyUnit secondCurrency;

    public CurrencyMismatchException(CurrencyUnit firstCurrency, CurrencyUnit secondCurrency) {
        super("Currencies differ: " + (firstCurrency != null ? firstCurrency.getCode() : "null") + '/' + (secondCurrency != null ? secondCurrency.getCode() : "null"));
        this.firstCurrency = firstCurrency;
        this.secondCurrency = secondCurrency;
    }
}
