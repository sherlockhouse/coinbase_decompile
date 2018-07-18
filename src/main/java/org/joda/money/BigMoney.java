package org.joda.money;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.regex.Pattern;

public final class BigMoney implements Serializable, Comparable<BigMoneyProvider>, BigMoneyProvider {
    static final /* synthetic */ boolean $assertionsDisabled = (!BigMoney.class.desiredAssertionStatus());
    private static final Pattern PARSE_REGEX = Pattern.compile("[+-]?[0-9]*[.]?[0-9]*");
    private final BigDecimal amount;
    private final CurrencyUnit currency;

    public static BigMoney of(CurrencyUnit currency, BigDecimal amount) {
        MoneyUtils.checkNotNull(currency, "Currency must not be null");
        MoneyUtils.checkNotNull(amount, "Amount must not be null");
        if (amount.getClass() != BigDecimal.class) {
            BigInteger value = amount.unscaledValue();
            if (value == null) {
                throw new IllegalArgumentException("Illegal BigDecimal subclass");
            }
            if (value.getClass() != BigInteger.class) {
                value = new BigInteger(value.toString());
            }
            amount = new BigDecimal(value, amount.scale());
        }
        return new BigMoney(currency, amount);
    }

    public static BigMoney of(CurrencyUnit currency, double amount) {
        MoneyUtils.checkNotNull(currency, "Currency must not be null");
        return of(currency, BigDecimal.valueOf(amount).stripTrailingZeros());
    }

    public static BigMoney ofMinor(CurrencyUnit currency, long amountMinor) {
        MoneyUtils.checkNotNull(currency, "CurrencyUnit must not be null");
        return of(currency, BigDecimal.valueOf(amountMinor, currency.getDecimalPlaces()));
    }

    public static BigMoney of(BigMoneyProvider moneyProvider) {
        MoneyUtils.checkNotNull(moneyProvider, "BigMoneyProvider must not be null");
        BigMoney money = moneyProvider.toBigMoney();
        MoneyUtils.checkNotNull(money, "BigMoneyProvider must not return null");
        return money;
    }

    private BigMoney() {
        this.currency = null;
        this.amount = null;
    }

    BigMoney(CurrencyUnit currency, BigDecimal amount) {
        if (!$assertionsDisabled && currency == null) {
            throw new AssertionError("Joda-Money bug: Currency must not be null");
        } else if ($assertionsDisabled || amount != null) {
            this.currency = currency;
            if (amount.scale() < 0) {
                amount = amount.setScale(0);
            }
            this.amount = amount;
        } else {
            throw new AssertionError("Joda-Money bug: Amount must not be null");
        }
    }

    public CurrencyUnit getCurrencyUnit() {
        return this.currency;
    }

    public int getScale() {
        return this.amount.scale();
    }

    public boolean isCurrencyScale() {
        return this.amount.scale() == this.currency.getDecimalPlaces();
    }

    public BigMoney withScale(int scale, RoundingMode roundingMode) {
        MoneyUtils.checkNotNull(roundingMode, "RoundingMode must not be null");
        return scale == this.amount.scale() ? this : of(this.currency, this.amount.setScale(scale, roundingMode));
    }

    public BigMoney withCurrencyScale(RoundingMode roundingMode) {
        return withScale(this.currency.getDecimalPlaces(), roundingMode);
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public int getMinorPart() {
        int cdp = getCurrencyUnit().getDecimalPlaces();
        return this.amount.setScale(cdp, RoundingMode.DOWN).remainder(BigDecimal.ONE).movePointRight(cdp).intValueExact();
    }

    public boolean isZero() {
        return this.amount.compareTo(BigDecimal.ZERO) == 0;
    }

    public boolean isPositive() {
        return this.amount.compareTo(BigDecimal.ZERO) > 0;
    }

    public boolean isPositiveOrZero() {
        return this.amount.compareTo(BigDecimal.ZERO) >= 0;
    }

    public boolean isNegative() {
        return this.amount.compareTo(BigDecimal.ZERO) < 0;
    }

    public boolean isNegativeOrZero() {
        return this.amount.compareTo(BigDecimal.ZERO) <= 0;
    }

    private BigMoney checkCurrencyEqual(BigMoneyProvider moneyProvider) {
        BigMoney money = of(moneyProvider);
        if (isSameCurrency(money)) {
            return money;
        }
        throw new CurrencyMismatchException(getCurrencyUnit(), money.getCurrencyUnit());
    }

    public BigMoney plus(BigMoneyProvider moneyToAdd) {
        return plus(checkCurrencyEqual(moneyToAdd).getAmount());
    }

    public BigMoney plus(BigDecimal amountToAdd) {
        MoneyUtils.checkNotNull(amountToAdd, "Amount must not be null");
        if (amountToAdd.compareTo(BigDecimal.ZERO) == 0) {
            return this;
        }
        return of(this.currency, this.amount.add(amountToAdd));
    }

    public BigMoney minus(BigMoneyProvider moneyToSubtract) {
        return minus(checkCurrencyEqual(moneyToSubtract).getAmount());
    }

    public BigMoney minus(BigDecimal amountToSubtract) {
        MoneyUtils.checkNotNull(amountToSubtract, "Amount must not be null");
        if (amountToSubtract.compareTo(BigDecimal.ZERO) == 0) {
            return this;
        }
        return of(this.currency, this.amount.subtract(amountToSubtract));
    }

    public BigMoney multipliedBy(long valueToMultiplyBy) {
        if (valueToMultiplyBy == 1) {
            return this;
        }
        return of(this.currency, this.amount.multiply(BigDecimal.valueOf(valueToMultiplyBy)));
    }

    public BigMoney multiplyRetainScale(BigDecimal valueToMultiplyBy, RoundingMode roundingMode) {
        MoneyUtils.checkNotNull(valueToMultiplyBy, "Multiplier must not be null");
        MoneyUtils.checkNotNull(roundingMode, "RoundingMode must not be null");
        if (valueToMultiplyBy.compareTo(BigDecimal.ONE) == 0) {
            return this;
        }
        return of(this.currency, this.amount.multiply(valueToMultiplyBy).setScale(getScale(), roundingMode));
    }

    public BigMoney dividedBy(BigDecimal valueToDivideBy, RoundingMode roundingMode) {
        MoneyUtils.checkNotNull(valueToDivideBy, "Divisor must not be null");
        MoneyUtils.checkNotNull(roundingMode, "RoundingMode must not be null");
        if (valueToDivideBy.compareTo(BigDecimal.ONE) == 0) {
            return this;
        }
        return of(this.currency, this.amount.divide(valueToDivideBy, roundingMode));
    }

    public BigMoney negated() {
        return isZero() ? this : of(this.currency, this.amount.negate());
    }

    public BigMoney abs() {
        return isNegative() ? negated() : this;
    }

    public BigMoney rounded(int scale, RoundingMode roundingMode) {
        MoneyUtils.checkNotNull(roundingMode, "RoundingMode must not be null");
        if (scale >= getScale()) {
            return this;
        }
        return of(this.currency, this.amount.setScale(scale, roundingMode).setScale(this.amount.scale()));
    }

    public BigMoney convertedTo(CurrencyUnit currency, BigDecimal conversionMultipler) {
        MoneyUtils.checkNotNull(currency, "CurrencyUnit must not be null");
        MoneyUtils.checkNotNull(conversionMultipler, "Multiplier must not be null");
        if (this.currency == currency) {
            if (conversionMultipler.compareTo(BigDecimal.ONE) == 0) {
                return this;
            }
            throw new IllegalArgumentException("Cannot convert to the same currency");
        } else if (conversionMultipler.compareTo(BigDecimal.ZERO) >= 0) {
            return of(currency, this.amount.multiply(conversionMultipler));
        } else {
            throw new IllegalArgumentException("Cannot convert using a negative conversion multiplier");
        }
    }

    public BigMoney toBigMoney() {
        return this;
    }

    public Money toMoney() {
        return Money.of(this);
    }

    public Money toMoney(RoundingMode roundingMode) {
        return Money.of((BigMoneyProvider) this, roundingMode);
    }

    public boolean isSameCurrency(BigMoneyProvider money) {
        return this.currency.equals(of(money).getCurrencyUnit());
    }

    public int compareTo(BigMoneyProvider other) {
        BigMoney otherMoney = of(other);
        if (this.currency.equals(otherMoney.currency)) {
            return this.amount.compareTo(otherMoney.amount);
        }
        throw new CurrencyMismatchException(getCurrencyUnit(), otherMoney.getCurrencyUnit());
    }

    public boolean isLessThan(BigMoneyProvider other) {
        return compareTo(other) < 0;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BigMoney)) {
            return false;
        }
        BigMoney otherMoney = (BigMoney) other;
        if (this.currency.equals(otherMoney.getCurrencyUnit()) && this.amount.equals(otherMoney.amount)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.currency.hashCode() ^ this.amount.hashCode();
    }

    public String toString() {
        return this.currency.getCode() + ' ' + this.amount.toPlainString();
    }
}
