package com.coinbase.android.utils;

import android.text.TextUtils;
import com.coinbase.android.ApplicationScope;
import com.coinbase.android.utils.MoneyFormatterUtil.Options;
import com.coinbase.v2.models.errors.ErrorBody;
import com.coinbase.v2.models.transfers.Data;
import com.coinbase.v2.models.transfers.TransferError;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.EnumSet;
import javax.inject.Inject;
import org.joda.money.BigMoneyProvider;
import org.joda.money.Money;

@ApplicationScope
public class TransferUtils {
    private final MoneyFormatterUtil mMoneyFormatterUtil;

    @Inject
    public TransferUtils(MoneyFormatterUtil moneyFormatterUtil) {
        this.mMoneyFormatterUtil = moneyFormatterUtil;
    }

    public String getPricePerCrypto(Data transfer) {
        if (transfer == null || transfer.getAmount() == null || transfer.getSubtotal() == null) {
            return null;
        }
        BigMoneyProvider currencyAmount = getCurrencyAmount(transfer);
        if (currencyAmount == null) {
            return null;
        }
        BigDecimal divisor = currencyAmount.toBigMoney().getAmount();
        if (divisor.compareTo(BigDecimal.ZERO) <= 0) {
            return null;
        }
        BigMoneyProvider subtotal = this.mMoneyFormatterUtil.moneyFromValue(transfer.getSubtotal().getCurrency(), transfer.getSubtotal().getAmount());
        if (divisor == null || subtotal == null) {
            return null;
        }
        BigMoneyProvider price = subtotal.toBigMoney().dividedBy(divisor, RoundingMode.CEILING);
        if (price != null) {
            return this.mMoneyFormatterUtil.formatMoney(price);
        }
        return null;
    }

    public Money getTotalFee(Data transfer) {
        if (transfer == null || transfer.getFee() == null) {
            return null;
        }
        Money totalFee = this.mMoneyFormatterUtil.moneyFromValue(transfer.getFee().getCurrency(), transfer.getFee().getAmount());
        if (totalFee == null) {
            return null;
        }
        if (totalFee.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            return totalFee.multipliedBy(-1);
        }
        return totalFee;
    }

    public String getTotalFeeString(Data transfer) {
        if (transfer == null) {
            return null;
        }
        Money totalFee = getTotalFee(transfer);
        if (totalFee != null) {
            return this.mMoneyFormatterUtil.formatMoney(totalFee);
        }
        return null;
    }

    public Money getPaymentMethodFee(Data transfer) {
        if (transfer == null || transfer.getPaymentMethodFee() == null) {
            return null;
        }
        Money paymentMethodFee = this.mMoneyFormatterUtil.moneyFromValue(transfer.getPaymentMethodFee().getCurrency(), transfer.getPaymentMethodFee().getAmount());
        if (paymentMethodFee == null) {
            return null;
        }
        if (paymentMethodFee.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            return paymentMethodFee.multipliedBy(-1);
        }
        return paymentMethodFee;
    }

    public String getPaymentMethodFeeString(Data transfer) {
        if (transfer == null) {
            return null;
        }
        Money paymentMethodFee = getPaymentMethodFee(transfer);
        if (paymentMethodFee != null) {
            return this.mMoneyFormatterUtil.formatMoney(paymentMethodFee);
        }
        return null;
    }

    public String getCryptoCurrencyAmount(Data transfer) {
        String cryptoAmount = "";
        BigMoneyProvider money = getCurrencyAmount(transfer);
        if (money == null) {
            return cryptoAmount;
        }
        return this.mMoneyFormatterUtil.formatMoney(money, EnumSet.of(Options.EXCLUDE_CURRENCY_SYMBOL, Options.ROUND_8_DIGITS));
    }

    public String getCryptoCurrencyAmountWithSymbol(Data transfer) {
        String cryptoAmount = "";
        BigMoneyProvider money = getCurrencyAmount(transfer);
        if (money == null) {
            return cryptoAmount;
        }
        return this.mMoneyFormatterUtil.formatMoney(money, EnumSet.of(Options.ROUND_8_DIGITS));
    }

    public String getTotalAmount(Data transfer) {
        if (transfer == null) {
            return null;
        }
        return this.mMoneyFormatterUtil.formatCurrencyAmount(transfer.getTotal().getAmount(), transfer.getTotal().getCurrency());
    }

    public String getSubtotalAmount(Data transfer) {
        if (transfer == null || transfer.getSubtotal() == null) {
            return null;
        }
        return this.mMoneyFormatterUtil.formatCurrencyAmount(transfer.getSubtotal().getAmount(), transfer.getSubtotal().getCurrency());
    }

    public String getCurrencyAmountString(Data transfer) {
        if (transfer == null) {
            return null;
        }
        return this.mMoneyFormatterUtil.formatCurrencyAmount(transfer.getAmount().getAmount(), transfer.getAmount().getCurrency());
    }

    public ErrorBody getFirstError(TransferError transferError) {
        if (transferError == null || transferError.getErrors() == null || transferError.getErrors().isEmpty() || transferError.getErrors().get(0) == null || TextUtils.isEmpty(((ErrorBody) transferError.getErrors().get(0)).getMessage())) {
            return null;
        }
        return (ErrorBody) transferError.getErrors().get(0);
    }

    private BigMoneyProvider getCurrencyAmount(Data transfer) {
        if (transfer == null || transfer.getAmount() == null) {
            return null;
        }
        return this.mMoneyFormatterUtil.moneyFromValue(transfer.getAmount().getCurrency(), transfer.getAmount().getAmount());
    }

    public boolean isInstant(Data transferData) {
        if (transferData == null || transferData.getInstant() == null) {
            return false;
        }
        return transferData.getInstant().booleanValue();
    }
}
