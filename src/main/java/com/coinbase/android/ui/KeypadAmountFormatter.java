package com.coinbase.android.ui;

import android.app.Application;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.ui.NumericKeypadConnector.KeypadType;
import java.math.BigDecimal;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;
import javax.inject.Inject;
import org.joda.money.BigMoney;
import org.joda.money.CurrencyUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ControllerScope
public class KeypadAmountFormatter {
    private static final int FIAT_FRACTION_DIGITS = 2;
    private final Context mContext;
    private final CurrenciesUpdatedConnector mCurrenciesUpdatedConnector;
    private final Logger mLogger = LoggerFactory.getLogger(KeypadAmountFormatter.class);

    @Inject
    public KeypadAmountFormatter(Application application, CurrenciesUpdatedConnector currenciesUpdatedConnector) {
        this.mContext = application;
        this.mCurrenciesUpdatedConnector = currenciesUpdatedConnector;
    }

    public String appendKeystroke(KeypadType keypadType, char keyStroke, String text) {
        switch (keypadType) {
            case DIGIT:
                return formatNumericType(keyStroke, text);
            case DOT:
                return formatDotType(text);
            case DELETE:
                return formatDeleteType(text);
            case LONG_DELETE:
                return formatLongDeleteType(text);
            default:
                return null;
        }
    }

    public SpannableStringBuilder getFormattedPrimaryAmount(CurrencyUnit primaryMoneyCurrency, BigMoney primaryAmount, String enteredValue, int dimColor) {
        if (primaryMoneyCurrency == null || enteredValue == null) {
            return null;
        }
        enteredValue = formatAmountToCurrentLocale(enteredValue);
        char separator = DecimalFormatSymbols.getInstance().getDecimalSeparator();
        boolean isCrypto = isCrypto(primaryMoneyCurrency.getCode());
        int dotIndex = enteredValue.indexOf(separator);
        int minFractionDigits = Math.max(isCrypto ? 2 : primaryMoneyCurrency.getDefaultFractionDigits(), dotIndex >= 0 ? (enteredValue.length() - dotIndex) - 1 : 0);
        int maxFractionDigits = primaryMoneyCurrency.getDefaultFractionDigits();
        BigDecimal amount = primaryAmount.getAmount();
        if (dotIndex < 0) {
            amount = amount.stripTrailingZeros();
            minFractionDigits = 0;
        }
        String amountString = formatCurrencyAmount(amount, true, maxFractionDigits, minFractionDigits);
        SpannableStringBuilder builder = new SpannableStringBuilder(amountString);
        ForegroundColorSpan foregroundColorSpan = getForegroundColorSpan(dimColor);
        if (TextUtils.isEmpty(enteredValue)) {
            builder.setSpan(foregroundColorSpan, 0, amountString.length(), 33);
        } else {
            if (enteredValue.endsWith(Character.toString(separator))) {
                builder.setSpan(foregroundColorSpan, amountString.indexOf(separator) + 1, amountString.length(), 33);
            } else if (dotIndex >= 0 && dotIndex == enteredValue.length() - 2) {
                builder.setSpan(foregroundColorSpan, amountString.length() - 1, amountString.length(), 33);
            }
        }
        if (isCrypto) {
            builder.append(" ").append(primaryMoneyCurrency.getCode());
            return builder;
        }
        builder.insert(0, primaryMoneyCurrency.getSymbol(Locale.getDefault()));
        return builder;
    }

    public SpannableStringBuilder getFormattedSecondaryText(CurrencyUnit secondaryMoneyCurrency, BigMoney secondaryAmount) {
        int maxFractionDigits = secondaryMoneyCurrency.getDefaultFractionDigits();
        boolean isCrypto = isCrypto(secondaryMoneyCurrency.getCode());
        SpannableStringBuilder builder = new SpannableStringBuilder(formatCurrencyAmount(secondaryAmount.getAmount().stripTrailingZeros(), true, maxFractionDigits, isCrypto ? 2 : secondaryMoneyCurrency.getDefaultFractionDigits()));
        if (isCrypto) {
            builder.append(" ").append(secondaryMoneyCurrency.getCode());
        } else {
            builder.insert(0, " ").insert(0, secondaryMoneyCurrency.getSymbol());
        }
        return builder;
    }

    private String formatNumericType(char keyStroke, String text) {
        if (text == null) {
            return null;
        }
        return text + keyStroke;
    }

    private String formatDotType(String text) {
        if (text == null) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        if (text.length() == 0) {
            result.append(NumericKeypadConnector.ZERO);
        }
        return result.append(text).append(NumericKeypadConnector.DOT).toString();
    }

    private String formatDeleteType(String text) {
        if (text == null || text.length() == 0) {
            return null;
        }
        return text.substring(0, text.length() - 1);
    }

    private String formatLongDeleteType(String text) {
        return "";
    }

    private String formatCurrencyAmount(BigDecimal balanceNumber, boolean ignoreSign, int maxFractionDigits, int minFractionDigits) {
        NumberFormat nf = NumberFormat.getInstance(this.mContext.getResources().getConfiguration().locale);
        nf.setMaximumFractionDigits(maxFractionDigits);
        nf.setMinimumFractionDigits(minFractionDigits);
        if (ignoreSign && balanceNumber.compareTo(BigDecimal.ZERO) == -1) {
            balanceNumber = balanceNumber.multiply(new BigDecimal(-1));
        }
        return nf.format(balanceNumber);
    }

    private static String formatAmountToCurrentLocale(String amount) {
        char separator = DecimalFormatSymbols.getInstance().getDecimalSeparator();
        if (!amount.contains(Character.toString(NumericKeypadConnector.DOT)) || separator == NumericKeypadConnector.DOT) {
            return amount;
        }
        return amount.replace(NumericKeypadConnector.DOT, separator);
    }

    private boolean isCrypto(String currencyCode) {
        return this.mCurrenciesUpdatedConnector.isValidCurrency(currencyCode);
    }

    private ForegroundColorSpan getForegroundColorSpan(int color) {
        return new ForegroundColorSpan(ContextCompat.getColor(this.mContext, color));
    }

    public double parseEnteredText(String text) {
        if (TextUtils.isEmpty(text)) {
            return 0.0d;
        }
        try {
            if (text.contains(Character.toString(DecimalFormatSymbols.getInstance().getDecimalSeparator()))) {
                return NumberFormat.getNumberInstance(this.mContext.getResources().getConfiguration().locale).parse(text).doubleValue();
            }
            return Double.parseDouble(text);
        } catch (Exception e) {
            this.mLogger.error("parseEnteredText exception has occurred", e);
            return 0.0d;
        }
    }
}
