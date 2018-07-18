package com.coinbase.android.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.coinbase.android.Constants;
import com.coinbase.android.GlideApp;
import com.coinbase.android.R;
import com.coinbase.api.internal.models.instantExchangeQuote.InstantExchangeQuote;
import com.coinbase.v2.models.transactions.Amount;
import com.coinbase.v2.models.transactions.Data;
import com.coinbase.v2.models.transactions.NativeAmount;
import com.coinbase.v2.models.transactions.Trade;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.joda.money.BigMoney;
import org.joda.money.BigMoneyProvider;
import org.joda.money.Money;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;

public class TransactionUtils {

    public enum TransactionStatus {
        PENDING("Pending"),
        COMPLETED("Complete"),
        FAILED("Failure"),
        EXPIRED("Expired"),
        CANCELED("Canceled"),
        WAITING_FOR_SIGNATURE("Waiting for approval"),
        WAITING_FOR_CLEARING("Waiting to clear"),
        UNKNOWN("");
        
        String mDisplayValue;

        private TransactionStatus(String displayValue) {
            this.mDisplayValue = displayValue;
        }

        public String toString() {
            return super.toString().toLowerCase();
        }

        public String getDisplayValue() {
            return this.mDisplayValue;
        }

        public static TransactionStatus toStatus(String value) {
            try {
                return valueOf(value.toUpperCase());
            } catch (Exception e) {
                return UNKNOWN;
            }
        }
    }

    public enum TransactionType {
        SEND,
        REQUEST,
        TRANSFER,
        BUY,
        SELL,
        FIAT_DEPOSIT,
        FIAT_WITHDRAWAL,
        EXCHANGE_DEPOSIT,
        VAULT_WITHDRAWAL,
        DELAYED_CANCELED,
        UNKNOWN;

        public String toString() {
            return super.toString().toLowerCase();
        }

        public static TransactionType toType(String value) {
            try {
                return valueOf(value.toUpperCase());
            } catch (Exception e) {
                return UNKNOWN;
            }
        }
    }

    public static Money calculateFee(BigMoney amount, InstantExchangeQuote quote) {
        if (amount == null || quote == null) {
            return null;
        }
        Money roundedAmount = amount.toMoney(RoundingMode.HALF_EVEN);
        Money fee = Money.of(Utils.getCurrencyUnitByCode(quote.getData().getFiat().getCurrency()), Double.valueOf(quote.getData().getFiat().getAmount()).doubleValue());
        if (roundedAmount.getCurrencyUnit() != fee.getCurrencyUnit()) {
            return null;
        }
        return fee.minus(roundedAmount);
    }

    public static Amount getFee(Data transaction) {
        Amount fee = null;
        if (transaction == null) {
            return null;
        }
        if (transaction.getBuy() != null && transaction.getBuy().getFee() != null) {
            fee = transaction.getBuy().getFee();
        } else if (!(transaction.getSell() == null || transaction.getSell().getFee() == null)) {
            fee = transaction.getSell().getFee();
        }
        return fee;
    }

    public static BigMoneyProvider getPricePerCrypto(Data tx, MoneyFormatterUtil moneyFormatterUtil) {
        if (tx == null) {
            return null;
        }
        Trade selectedTrade = null;
        if (tx.getBuy() != null) {
            selectedTrade = tx.getBuy();
        } else if (tx.getSell() != null) {
            selectedTrade = tx.getSell();
        }
        if (selectedTrade == null || selectedTrade.getAmount() == null || selectedTrade.getSubtotal() == null) {
            return null;
        }
        Money amountMoney = moneyFormatterUtil.moneyFromValue(selectedTrade.getAmount().getCurrency(), selectedTrade.getAmount().getAmount());
        if (amountMoney == null) {
            return null;
        }
        BigDecimal divisor = amountMoney.toBigMoney().getAmount();
        if (divisor.compareTo(BigDecimal.ZERO) <= 0) {
            return null;
        }
        BigMoneyProvider subtotal = moneyFormatterUtil.moneyFromValue(selectedTrade.getSubtotal().getCurrency(), selectedTrade.getSubtotal().getAmount());
        if (divisor == null || subtotal == null) {
            return null;
        }
        return subtotal.toBigMoney().dividedBy(divisor, RoundingMode.CEILING);
    }

    public static BigMoney moneyFromAmount(Amount amount) {
        if (amount == null) {
            return null;
        }
        return BigMoney.of(Utils.getCurrencyUnitByCode(amount.getCurrency()), Double.valueOf(amount.getAmount()).doubleValue());
    }

    public static BigMoney moneyFromNativeAmount(NativeAmount amount) {
        if (amount == null) {
            return null;
        }
        return BigMoney.of(Utils.getCurrencyUnitByCode(amount.getCurrency()), Double.valueOf(amount.getAmount()).doubleValue());
    }

    public static Amount amountFromMoney(Money money) {
        if (money == null) {
            return null;
        }
        Amount amount = new Amount();
        amount.setCurrency(money.getCurrencyUnit().toString());
        amount.setAmount(money.getAmount().toPlainString());
        return amount;
    }

    public static LocalDate createdAtDateFromTransaction(Data tx) {
        if (tx == null || tx.getCreatedAt() == null) {
            return new LocalDate();
        }
        try {
            return DateTimeFormat.forPattern(Constants.DATE_FORMAT).parseLocalDate(tx.getCreatedAt());
        } catch (IllegalArgumentException e) {
            return new LocalDate();
        }
    }

    public static void setTransactionImage(final Context context, final ImageView imageView, Data transaction, final int backgroundColor) {
        if (transaction != null && transaction.getImage() != null) {
            if (transaction.getImage() != null) {
                GlideApp.with(context).load(transaction.getImage().getUrl()).listener(new RequestListener<Drawable>() {
                    public boolean onLoadFailed(GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        TransactionUtils.showDefaultTransactionDrawable(context, imageView, backgroundColor);
                        return true;
                    }

                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        if (backgroundColor != -1) {
                            Utils.updateBackgroundColorWithAlpha(imageView, backgroundColor);
                        }
                        return false;
                    }
                }).into(imageView);
            } else {
                showDefaultTransactionDrawable(context, imageView, backgroundColor);
            }
        }
    }

    public static void showDefaultTransactionDrawable(Context context, final ImageView imageView, final int backgroundColor) {
        if (imageView != null) {
            GlideApp.with(context).load(Integer.valueOf(R.drawable.temp_transaction)).listener(new RequestListener<Drawable>() {
                public boolean onLoadFailed(GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                    return false;
                }

                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                    if (backgroundColor != -1) {
                        Utils.updateBackgroundColorWithAlpha(imageView, backgroundColor);
                    }
                    return false;
                }
            }).into(imageView);
        }
    }
}
