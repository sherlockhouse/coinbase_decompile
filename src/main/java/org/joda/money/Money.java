package org.joda.money;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Iterator;

public final class Money implements Serializable, Comparable<BigMoneyProvider>, BigMoneyProvider {
    static final /* synthetic */ boolean $assertionsDisabled = (!Money.class.desiredAssertionStatus());
    private final BigMoney money;

    public static Money of(CurrencyUnit currency, BigDecimal amount) {
        MoneyUtils.checkNotNull(currency, "Currency must not be null");
        MoneyUtils.checkNotNull(amount, "Amount must not be null");
        if (amount.scale() <= currency.getDecimalPlaces()) {
            return of(currency, amount, RoundingMode.UNNECESSARY);
        }
        throw new ArithmeticException("Scale of amount " + amount + " is greater than the scale of the currency " + currency);
    }

    public static Money of(CurrencyUnit currency, BigDecimal amount, RoundingMode roundingMode) {
        MoneyUtils.checkNotNull(currency, "CurrencyUnit must not be null");
        MoneyUtils.checkNotNull(amount, "Amount must not be null");
        MoneyUtils.checkNotNull(roundingMode, "RoundingMode must not be null");
        return new Money(BigMoney.of(currency, amount.setScale(currency.getDecimalPlaces(), roundingMode)));
    }

    public static Money of(CurrencyUnit currency, double amount) {
        return of(currency, BigDecimal.valueOf(amount));
    }

    public static Money of(CurrencyUnit currency, double amount, RoundingMode roundingMode) {
        return of(currency, BigDecimal.valueOf(amount), roundingMode);
    }

    public static Money ofMinor(CurrencyUnit currency, long amountMinor) {
        return new Money(BigMoney.ofMinor(currency, amountMinor));
    }

    public static Money zero(CurrencyUnit currency) {
        MoneyUtils.checkNotNull(currency, "Currency must not be null");
        return new Money(BigMoney.of(currency, BigDecimal.valueOf(0, currency.getDecimalPlaces())));
    }

    public static Money of(BigMoneyProvider moneyProvider) {
        return of(moneyProvider, RoundingMode.UNNECESSARY);
    }

    public static Money of(BigMoneyProvider moneyProvider, RoundingMode roundingMode) {
        MoneyUtils.checkNotNull(moneyProvider, "BigMoneyProvider must not be null");
        MoneyUtils.checkNotNull(roundingMode, "RoundingMode must not be null");
        return new Money(BigMoney.of(moneyProvider).withCurrencyScale(roundingMode));
    }

    public static Money total(Iterable<Money> monies) {
        MoneyUtils.checkNotNull(monies, "Money iterator must not be null");
        Iterator<Money> it = monies.iterator();
        if (it.hasNext()) {
            Money total = (Money) it.next();
            MoneyUtils.checkNotNull(total, "Money iterator must not contain null entries");
            while (it.hasNext()) {
                total = total.plus((Money) it.next());
            }
            return total;
        }
        throw new IllegalArgumentException("Money iterator must not be empty");
    }

    private Money() {
        this.money = null;
    }

    Money(BigMoney money) {
        if (!$assertionsDisabled && money == null) {
            throw new AssertionError("Joda-Money bug: BigMoney must not be null");
        } else if ($assertionsDisabled || money.isCurrencyScale()) {
            this.money = money;
        } else {
            throw new AssertionError("Joda-Money bug: Only currency scale is valid for Money");
        }
    }

    private Money with(BigMoney newInstance) {
        if (this.money.equals(newInstance)) {
            return this;
        }
        this(newInstance);
        return this;
    }

    public CurrencyUnit getCurrencyUnit() {
        return this.money.getCurrencyUnit();
    }

    public BigDecimal getAmount() {
        return this.money.getAmount();
    }

    public int getMinorPart() {
        return this.money.getMinorPart();
    }

    public boolean isZero() {
        return this.money.isZero();
    }

    public boolean isNegativeOrZero() {
        return this.money.isNegativeOrZero();
    }

    public Money plus(Money moneyToAdd) {
        return with(this.money.plus((BigMoneyProvider) moneyToAdd));
    }

    public Money minus(Money moneyToSubtract) {
        return with(this.money.minus((BigMoneyProvider) moneyToSubtract));
    }

    public Money multipliedBy(BigDecimal valueToMultiplyBy, RoundingMode roundingMode) {
        return with(this.money.multiplyRetainScale(valueToMultiplyBy, roundingMode));
    }

    public Money multipliedBy(long valueToMultiplyBy) {
        return with(this.money.multipliedBy(valueToMultiplyBy));
    }

    public Money convertedTo(CurrencyUnit currency, BigDecimal conversionMultipler, RoundingMode roundingMode) {
        return with(this.money.convertedTo(currency, conversionMultipler).withCurrencyScale(roundingMode));
    }

    public BigMoney toBigMoney() {
        return this.money;
    }

    public int compareTo(BigMoneyProvider other) {
        return this.money.compareTo(other);
    }

    public boolean isLessThan(BigMoneyProvider other) {
        return this.money.isLessThan(other);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Money)) {
            return false;
        }
        return this.money.equals(((Money) other).money);
    }

    public int hashCode() {
        return this.money.hashCode() + 3;
    }

    public String toString() {
        return this.money.toString();
    }
}
