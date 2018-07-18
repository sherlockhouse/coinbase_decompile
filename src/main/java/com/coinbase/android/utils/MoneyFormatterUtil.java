package com.coinbase.android.utils;

import com.coinbase.android.ApplicationScope;
import com.coinbase.android.ui.CurrenciesUpdatedConnector;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.EnumSet;
import java.util.Locale;
import java.util.Set;
import javax.inject.Inject;
import org.joda.money.BigMoney;
import org.joda.money.BigMoneyProvider;
import org.joda.money.CurrencyUnit;
import org.joda.money.IllegalCurrencyException;
import org.joda.money.Money;
import org.joda.money.format.MoneyAmountStyle;
import org.joda.money.format.MoneyFormatter;
import org.joda.money.format.MoneyFormatterBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScope
public class MoneyFormatterUtil {
    private static final String ERROR_MESSAGE = "MoneyFormatterUtil:%1$s; currency=%2$s; amount=%3$s; locale=%4$s";
    private final CurrenciesUpdatedConnector mCurrenciesUpdatedConnector;
    private final Logger mLogger = LoggerFactory.getLogger(MoneyFormatterUtil.class);

    public enum Options {
        NONE,
        EXCLUDE_CURRENCY_SYMBOL,
        INCLUDE_PARENTHESIS,
        PREFIX_SIGNED,
        ROUND_0_DIGITS,
        ROUND_2_DIGITS,
        ROUND_4_DIGITS,
        ROUND_8_DIGITS,
        STRIP_TRAILING_ZEROS
    }

    @Inject
    public MoneyFormatterUtil(CurrenciesUpdatedConnector currenciesUpdatedConnector) {
        this.mCurrenciesUpdatedConnector = currenciesUpdatedConnector;
    }

    boolean isValidCurrency(String currencyCode) {
        return this.mCurrenciesUpdatedConnector.isValidCurrency(currencyCode);
    }

    private BigMoneyProvider generateMoney(BigMoneyProvider money, Set<Options> options) {
        if (money == null) {
            this.mLogger.error(createErrorMessage("generateMoney=money is NULL", "", ""));
            return null;
        }
        int scale = money.toBigMoney().getScale();
        if (options.contains(Options.ROUND_0_DIGITS)) {
            scale = 0;
        } else if (options.contains(Options.ROUND_2_DIGITS)) {
            scale = 2;
        } else if (options.contains(Options.ROUND_4_DIGITS)) {
            scale = 4;
        } else if (options.contains(Options.ROUND_8_DIGITS)) {
            scale = 8;
        }
        if (!isValidCurrency(money.toBigMoney().getCurrencyUnit().getCurrencyCode().toUpperCase())) {
            if (scale < 2 && !options.contains(Options.ROUND_0_DIGITS)) {
                scale = 2;
            }
            if (scale > 2) {
                scale = 2;
            }
        } else if (scale < 4 && !options.contains(Options.ROUND_0_DIGITS)) {
            scale = 4;
        }
        BigDecimal displayAmount = money.toBigMoney().getAmount().setScale(scale, RoundingMode.HALF_EVEN);
        if (options.contains(Options.STRIP_TRAILING_ZEROS)) {
            displayAmount = displayAmount.stripTrailingZeros();
        }
        if (options.contains(Options.PREFIX_SIGNED)) {
            displayAmount = displayAmount.abs();
        }
        return BigMoney.of(money.toBigMoney().getCurrencyUnit(), displayAmount);
    }

    private MoneyFormatter generateFormatter(BigMoneyProvider money, Set<Options> options) {
        String currencyCode = money.toBigMoney().getCurrencyUnit().getCurrencyCode();
        MoneyFormatterBuilder builder = new MoneyFormatterBuilder();
        if (options.contains(Options.INCLUDE_PARENTHESIS)) {
            builder.appendLiteral("(");
        }
        if (options.contains(Options.PREFIX_SIGNED) && money.toBigMoney().isNegative()) {
            builder.appendLiteral("-");
        }
        if (options.contains(Options.EXCLUDE_CURRENCY_SYMBOL)) {
            if (isValidCurrency(currencyCode) || !isGroupingEnabled()) {
                builder.appendAmount(MoneyAmountStyle.LOCALIZED_NO_GROUPING);
            } else {
                builder.appendAmountLocalized();
            }
        } else if (isValidCurrency(currencyCode) || !isGroupingEnabled()) {
            builder.appendAmount(MoneyAmountStyle.LOCALIZED_NO_GROUPING).appendLiteral(" ").appendCurrencyCode();
        } else {
            builder.appendCurrencySymbolLocalized().appendAmountLocalized();
        }
        if (options.contains(Options.INCLUDE_PARENTHESIS)) {
            builder.appendLiteral(")");
        }
        return builder.toFormatter();
    }

    private boolean isGroupingEnabled() {
        NumberFormat format = NumberFormat.getCurrencyInstance();
        return format == null || format.isGroupingUsed();
    }

    public String formatMoney(BigMoneyProvider money) {
        return formatMoney(money, null);
    }

    public String formatMoney(BigMoneyProvider money, Set<Options> options) {
        String result = "";
        if (money == null) {
            this.mLogger.error(createErrorMessage("formatMoney=money is null", "", ""));
            return result;
        }
        if (options == null) {
            options = EnumSet.of(Options.NONE);
        }
        try {
            result = generateFormatter(money, options).print(generateMoney(money, options));
        } catch (Exception e) {
            this.mLogger.error(createErrorMessage("formatMoney=Exception", "", ""), e);
        }
        return result;
    }

    public String getCurrencySymbol(BigMoneyProvider money) {
        String result = "";
        if (money == null) {
            this.mLogger.error(createErrorMessage("getCurrencySymbol=money is NULL", "", ""));
        } else {
            try {
                result = money.toBigMoney().getCurrencyUnit().getSymbol();
            } catch (Exception e) {
                this.mLogger.error(createErrorMessage("getCurrencySymbol=Exception", "", ""), e);
            }
        }
        return result;
    }

    public String formatCurrencyAmount(String amount, String currency) {
        BigMoneyProvider money = moneyFromValue(currency, amount);
        if (money != null) {
            return formatMoney(money);
        }
        this.mLogger.error(createErrorMessage("formatCurrencyAmount: money is null", "", ""));
        return null;
    }

    public Money moneyFromValue(String currency, String amount) {
        Money money = null;
        if (currency == null || amount == null) {
            this.mLogger.error(createErrorMessage("moneyFromValue: input is null", currency, amount));
            return money;
        } else if (amount.contains(",")) {
            return moneyFromValue(currency, amount, Locale.GERMANY);
        } else {
            try {
                CurrencyUnit unit = CurrencyUnit.of(currency);
                Double amountFormatted = Double.valueOf(Double.parseDouble(amount));
                try {
                    return Money.of(unit, amountFormatted.doubleValue(), RoundingMode.HALF_DOWN);
                } catch (ArithmeticException e) {
                    this.mLogger.error(createErrorMessage("moneyFromValue: ArithmeticException", currency, amount), e);
                    return BigMoney.of(unit, amountFormatted.doubleValue()).rounded(unit.getDecimalPlaces(), RoundingMode.HALF_DOWN).toMoney();
                }
            } catch (IllegalCurrencyException c) {
                this.mLogger.error(createErrorMessage("moneyFromValue: IllegalCurrencyException", currency, amount), c);
                return money;
            } catch (NumberFormatException n) {
                this.mLogger.error(createErrorMessage("moneyFromValue: NumberFormatException", currency, amount), n);
                return moneyFromValue(currency, amount, Locale.getDefault());
            }
        }
    }

    private Money moneyFromValue(String currency, String amount, Locale locale) {
        Money money = null;
        if (!(currency == null || amount == null)) {
            try {
                CurrencyUnit unit = CurrencyUnit.of(currency);
                Double amountFormatted = Double.valueOf(NumberFormat.getInstance(locale).parse(amount).doubleValue());
                try {
                    money = Money.of(unit, amountFormatted.doubleValue());
                } catch (ArithmeticException e) {
                    money = BigMoney.of(unit, amountFormatted.doubleValue()).rounded(unit.getDecimalPlaces(), RoundingMode.HALF_DOWN).toMoney();
                }
            } catch (IllegalCurrencyException e2) {
            } catch (ParseException p) {
                this.mLogger.error(createErrorMessage("moneyFromValue: ParseException", currency, amount), p);
            }
        }
        return money;
    }

    String createErrorMessage(String logMessage, String currency, String amount) {
        return String.format(ERROR_MESSAGE, new Object[]{logMessage, currency, amount, Locale.getDefault()});
    }
}
