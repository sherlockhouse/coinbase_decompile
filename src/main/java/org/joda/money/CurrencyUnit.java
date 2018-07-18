package org.joda.money;

import java.io.Serializable;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.regex.Pattern;

public final class CurrencyUnit implements Serializable, Comparable<CurrencyUnit> {
    static final /* synthetic */ boolean $assertionsDisabled = (!CurrencyUnit.class.desiredAssertionStatus());
    public static final CurrencyUnit AUD = of("AUD");
    public static final CurrencyUnit CAD = of("CAD");
    public static final CurrencyUnit CHF = of("CHF");
    private static final Pattern CODE = Pattern.compile("[A-Z][A-Z][A-Z]");
    public static final CurrencyUnit EUR = of("EUR");
    public static final CurrencyUnit GBP = of("GBP");
    public static final CurrencyUnit JPY = of("JPY");
    public static final CurrencyUnit USD = of("USD");
    private static final ConcurrentMap<String, CurrencyUnit> currenciesByCode = new ConcurrentHashMap();
    private static final ConcurrentMap<String, CurrencyUnit> currenciesByCountry = new ConcurrentHashMap();
    private static final ConcurrentMap<Integer, CurrencyUnit> currenciesByNumericCode = new ConcurrentHashMap();
    private final String code;
    private final short decimalPlaces;
    private final short numericCode;

    static {
        try {
            ((CurrencyUnitDataProvider) CurrencyUnit.class.getClassLoader().loadClass(System.getProperty("org.joda.money.CurrencyUnitDataProvider", "org.joda.money.DefaultCurrencyUnitDataProvider")).asSubclass(CurrencyUnitDataProvider.class).newInstance()).registerCurrencies();
        } catch (SecurityException e) {
            try {
                new DefaultCurrencyUnitDataProvider().registerCurrencies();
            } catch (RuntimeException ex) {
                throw ex;
            } catch (Exception ex2) {
                throw new RuntimeException(ex2.toString(), ex2);
            }
        }
    }

    public static synchronized CurrencyUnit registerCurrency(String currencyCode, int numericCurrencyCode, int decimalPlaces, List<String> countryCodes) {
        CurrencyUnit registerCurrency;
        synchronized (CurrencyUnit.class) {
            registerCurrency = registerCurrency(currencyCode, numericCurrencyCode, decimalPlaces, countryCodes, false);
        }
        return registerCurrency;
    }

    public static synchronized CurrencyUnit registerCurrency(String currencyCode, int numericCurrencyCode, int decimalPlaces, List<String> countryCodes, boolean force) {
        CurrencyUnit currencyUnit;
        synchronized (CurrencyUnit.class) {
            MoneyUtils.checkNotNull(currencyCode, "Currency code must not be null");
            if (currencyCode.length() != 3) {
                throw new IllegalArgumentException("Invalid string code, must be length 3");
            } else if (numericCurrencyCode < -1 || numericCurrencyCode > 999) {
                throw new IllegalArgumentException("Invalid numeric code");
            } else if (decimalPlaces < -1 || decimalPlaces > 9) {
                throw new IllegalArgumentException("Invalid number of decimal places");
            } else {
                MoneyUtils.checkNotNull(countryCodes, "Country codes must not be null");
                CurrencyUnit currency = new CurrencyUnit(currencyCode, (short) numericCurrencyCode, (short) decimalPlaces);
                if (force) {
                    currenciesByCode.remove(currencyCode);
                    currenciesByNumericCode.remove(Integer.valueOf(numericCurrencyCode));
                    for (String countryCode : countryCodes) {
                        currenciesByCountry.remove(countryCode);
                    }
                } else if (currenciesByCode.containsKey(currencyCode) || currenciesByNumericCode.containsKey(Integer.valueOf(numericCurrencyCode))) {
                    throw new IllegalArgumentException("Currency already registered: " + currencyCode);
                } else {
                    for (String countryCode2 : countryCodes) {
                        if (currenciesByCountry.containsKey(countryCode2)) {
                            throw new IllegalArgumentException("Currency already registered for country: " + countryCode2);
                        }
                    }
                }
                currenciesByCode.putIfAbsent(currencyCode, currency);
                if (numericCurrencyCode >= 0) {
                    currenciesByNumericCode.putIfAbsent(Integer.valueOf(numericCurrencyCode), currency);
                }
                for (String countryCode22 : countryCodes) {
                    currenciesByCountry.put(countryCode22, currency);
                }
                currencyUnit = (CurrencyUnit) currenciesByCode.get(currencyCode);
            }
        }
        return currencyUnit;
    }

    public static CurrencyUnit of(String currencyCode) {
        MoneyUtils.checkNotNull(currencyCode, "Currency code must not be null");
        CurrencyUnit currency = (CurrencyUnit) currenciesByCode.get(currencyCode);
        if (currency != null) {
            return currency;
        }
        throw new IllegalCurrencyException("Unknown currency '" + currencyCode + '\'');
    }

    public static CurrencyUnit getInstance(String currencyCode) {
        return of(currencyCode);
    }

    CurrencyUnit(String code, short numericCode, short decimalPlaces) {
        if ($assertionsDisabled || code != null) {
            this.code = code;
            this.numericCode = numericCode;
            this.decimalPlaces = decimalPlaces;
            return;
        }
        throw new AssertionError("Joda-Money bug: Currency code must not be null");
    }

    public String getCode() {
        return this.code;
    }

    public int getNumericCode() {
        return this.numericCode;
    }

    public String getNumeric3Code() {
        if (this.numericCode < (short) 0) {
            return "";
        }
        String str = Integer.toString(this.numericCode);
        if (str.length() == 1) {
            return "00" + str;
        }
        if (str.length() == 2) {
            return "0" + str;
        }
        return str;
    }

    public int getDecimalPlaces() {
        return this.decimalPlaces < (short) 0 ? 0 : this.decimalPlaces;
    }

    public String getCurrencyCode() {
        return this.code;
    }

    public int getDefaultFractionDigits() {
        return this.decimalPlaces;
    }

    public String getSymbol() {
        try {
            return Currency.getInstance(this.code).getSymbol();
        } catch (IllegalArgumentException e) {
            return this.code;
        }
    }

    public String getSymbol(Locale locale) {
        MoneyUtils.checkNotNull(locale, "Locale must not be null");
        try {
            return Currency.getInstance(this.code).getSymbol(locale);
        } catch (IllegalArgumentException e) {
            return this.code;
        }
    }

    public int compareTo(CurrencyUnit other) {
        return this.code.compareTo(other.code);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof CurrencyUnit) {
            return this.code.equals(((CurrencyUnit) obj).code);
        }
        return false;
    }

    public int hashCode() {
        return this.code.hashCode();
    }

    public String toString() {
        return this.code;
    }
}
