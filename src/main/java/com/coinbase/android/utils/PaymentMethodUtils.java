package com.coinbase.android.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Pair;
import android.widget.ImageView;
import com.coinbase.android.ApplicationScope;
import com.coinbase.android.R;
import com.coinbase.android.utils.MoneyFormatterUtil.Options;
import com.coinbase.api.LoginManager;
import com.coinbase.v2.models.paymentMethods.Data;
import com.coinbase.v2.models.paymentMethods.Data.Type;
import com.coinbase.v2.models.paymentMethods.Limit;
import com.coinbase.v2.models.paymentMethods.PickerData;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import javax.inject.Inject;
import org.joda.money.Money;

@ApplicationScope
public class PaymentMethodUtils {
    private static final String OBFUSCATED_ACCOUNT_NUMBER_FORMAT = "*** %1$s";
    private static final String STAR_SEQUENCE = new String(new char[]{'*', '*'});
    private final LoginManager mLoginManager;
    private final MoneyFormatterUtil mMoneyFormatterUtil;

    public enum PaymentGroup {
        BANK,
        CARD,
        OTHER;

        public static PaymentGroup fromType(Type type) {
            switch (type) {
                case ACH_BANK_ACCOUNT:
                case BANK_WIRE:
                case FIAT_ACCOUNT:
                case XFERS:
                case SEPA_BANK_ACCOUNT:
                    return BANK;
                case CREDIT_CARD:
                case DEBIT_CARD:
                case SECURE_3DS:
                case WORLDPAY_CARD:
                    return CARD;
                default:
                    return OTHER;
            }
        }
    }

    @Inject
    public PaymentMethodUtils(LoginManager loginManager, MoneyFormatterUtil moneyFormatterUtil) {
        this.mLoginManager = loginManager;
        this.mMoneyFormatterUtil = moneyFormatterUtil;
    }

    public static boolean isUSUser(LoginManager loginManager) {
        return loginManager.getActiveUserCountryCode().equals("US");
    }

    public static boolean isValidDeposit(Data paymentMethod) {
        if (paymentMethod != null && paymentMethod.getAllowDeposit().booleanValue() && paymentMethod.getVerified().booleanValue() && paymentMethod.getType() != Type.FIAT_ACCOUNT) {
            return true;
        }
        return false;
    }

    public static boolean isValidWithdraw(Data paymentMethod) {
        if (paymentMethod != null && paymentMethod.getAllowWithdraw().booleanValue() && paymentMethod.getVerified().booleanValue() && paymentMethod.getType() != Type.FIAT_ACCOUNT) {
            return true;
        }
        return false;
    }

    public static boolean shouldShowWorldPayInfo(Data paymentMethod) {
        if (paymentMethod == null || paymentMethod.getType() == null) {
            return false;
        }
        Type type = paymentMethod.getType();
        if (type == Type.CREDIT_CARD || type == Type.DEBIT_CARD || type == Type.WORLDPAY_CARD) {
            return true;
        }
        return false;
    }

    public static Pair<String, String> applyPaymentMethodNameNewlineTransformation(String fullName) {
        if (TextUtils.isEmpty(fullName) || !fullName.contains(STAR_SEQUENCE) || fullName.startsWith(STAR_SEQUENCE) || fullName.endsWith(STAR_SEQUENCE)) {
            return null;
        }
        int indexOfStars = fullName.indexOf(STAR_SEQUENCE);
        return new Pair(fullName.substring(0, indexOfStars), fullName.substring(indexOfStars));
    }

    private String getObfuscatedAccountNumber(Data paymentMethod) {
        if (paymentMethod == null || paymentMethod.getPickerData() == null) {
            return null;
        }
        String accountNumber;
        PickerData pickerData = paymentMethod.getPickerData();
        switch (paymentMethod.getType()) {
            case ACH_BANK_ACCOUNT:
            case BANK_WIRE:
            case FIAT_ACCOUNT:
            case XFERS:
                accountNumber = pickerData.getAccountNumber();
                break;
            case CREDIT_CARD:
            case DEBIT_CARD:
            case SECURE_3DS:
            case WORLDPAY_CARD:
                accountNumber = pickerData.getCardLast4();
                break;
            case SEPA_BANK_ACCOUNT:
                accountNumber = pickerData.getSwift();
                break;
            default:
                accountNumber = "";
                break;
        }
        if (accountNumber == null || accountNumber.length() < 4) {
            return accountNumber;
        }
        return String.format(OBFUSCATED_ACCOUNT_NUMBER_FORMAT, new Object[]{accountNumber.substring(accountNumber.length() - 4, accountNumber.length())});
    }

    public List<Data> filterPaymentMethods(List<Data> paymentMethods) {
        List<Data> filteredPaymentMethods = new ArrayList();
        if (!paymentMethods.isEmpty()) {
            Collections.sort(paymentMethods, PaymentMethodUtils$$Lambda$1.lambdaFactory$());
            filteredPaymentMethods.addAll(paymentMethods);
        }
        return filteredPaymentMethods;
    }

    static /* synthetic */ int lambda$filterPaymentMethods$0(Data lhs, Data rhs) {
        PaymentGroup lhsType = lhs.getLimits() == null ? null : PaymentGroup.fromType(lhs.getType());
        PaymentGroup rhsType = rhs.getLimits() == null ? null : PaymentGroup.fromType(rhs.getType());
        if (lhsType == null && rhsType == null) {
            return 0;
        }
        if (lhsType == null) {
            return -1;
        }
        if (rhsType == null) {
            return 1;
        }
        return lhsType.compareTo(rhsType);
    }

    public List<Object> insertHeadersIntoPaymentMethodsList(List<Data> paymentMethods) {
        if (paymentMethods == null || paymentMethods.isEmpty()) {
            return new LinkedList(paymentMethods);
        }
        List<Object> paymentMethodsWithHeaders = new LinkedList();
        PaymentGroup currentType = null;
        for (Data paymentMethod : paymentMethods) {
            PaymentGroup group = PaymentGroup.fromType(paymentMethod.getType());
            if (currentType != group) {
                paymentMethodsWithHeaders.add(group);
                currentType = group;
            }
            paymentMethodsWithHeaders.add(paymentMethod);
        }
        return paymentMethodsWithHeaders;
    }

    public String getShortenedName(Data paymentMethod) {
        if (paymentMethod == null) {
            return null;
        }
        PickerData pickerData = paymentMethod.getPickerData();
        if (pickerData != null) {
            if (pickerData.getInstitutionName() != null) {
                return pickerData.getInstitutionName();
            }
            if (pickerData.getAccountName() != null) {
                return pickerData.getAccountName();
            }
        }
        Pair<String, String> nameAndMaskedNumber = applyPaymentMethodNameNewlineTransformation(paymentMethod.getName());
        if (nameAndMaskedNumber != null) {
            return (String) nameAndMaskedNumber.first;
        }
        return paymentMethod.getName();
    }

    public Pair<String, String> getFormattedNameAndNumberDisplay(Data paymentMethod) {
        if (paymentMethod == null) {
            return null;
        }
        String name;
        String number;
        if (paymentMethod.getPickerData() == null) {
            Pair<String, String> nameAndMaskedNumber = applyPaymentMethodNameNewlineTransformation(paymentMethod.getName());
            if (nameAndMaskedNumber == null) {
                name = paymentMethod.getName();
                number = "";
            } else {
                name = (String) nameAndMaskedNumber.first;
                number = (String) nameAndMaskedNumber.second;
            }
        } else {
            name = getShortenedName(paymentMethod);
            number = getObfuscatedAccountNumber(paymentMethod);
        }
        return new Pair(name, number);
    }

    public String getFiatAccountCurrencySymbol(Data paymentMethod) {
        if (paymentMethod == null || paymentMethod.getCurrency() == null || paymentMethod.getType() != Type.FIAT_ACCOUNT) {
            return null;
        }
        return Utils.getCurrencyUnitByCode(paymentMethod.getCurrency()).getSymbol(Locale.getDefault());
    }

    public String getFiatBalanceFromAmount(Data paymentMethod) {
        if (paymentMethod == null) {
            return null;
        }
        com.coinbase.v2.models.account.Data account = getFiatAccount(paymentMethod);
        if (account == null) {
            return null;
        }
        Money accountBalanceMoney = AccountUtils.getAccountBalanceMoney(account, this.mMoneyFormatterUtil);
        if (accountBalanceMoney != null) {
            return this.mMoneyFormatterUtil.formatMoney(accountBalanceMoney, EnumSet.of(Options.ROUND_2_DIGITS));
        }
        return null;
    }

    public void setFiatImageBackground(Context context, ImageView imageView, Data paymentMethod) {
        if (imageView != null && paymentMethod != null) {
            com.coinbase.v2.models.account.Data account = getFiatAccount(paymentMethod);
            if (account != null) {
                AccountUtils.setAccountImage(context, imageView, account);
            }
        }
    }

    public String getLimit(Data paymentMethod) {
        if (paymentMethod == null || paymentMethod.getLimits() == null || paymentMethod.getLimits().getBuy() == null) {
            return null;
        }
        Limit limit = (Limit) paymentMethod.getLimits().getBuy().get(0);
        if (limit == null || limit.getRemaining() == null) {
            return null;
        }
        return this.mMoneyFormatterUtil.formatCurrencyAmount(limit.getRemaining().getAmount(), limit.getRemaining().getCurrency());
    }

    public boolean isLimitReached(Data paymentMethod) {
        if (paymentMethod == null || paymentMethod.getLimits() == null || paymentMethod.getLimits().getBuy() == null) {
            return false;
        }
        Limit limit = (Limit) paymentMethod.getLimits().getBuy().get(0);
        if (limit == null || limit.getRemaining() == null) {
            return false;
        }
        Money amountValue = this.mMoneyFormatterUtil.moneyFromValue(limit.getRemaining().getCurrency(), limit.getRemaining().getAmount());
        if (amountValue == null || !amountValue.isZero()) {
            return false;
        }
        return true;
    }

    private com.coinbase.v2.models.account.Data getFiatAccount(Data paymentMethod) {
        List<com.coinbase.v2.models.account.Data> accounts = this.mLoginManager.getAccounts();
        if (accounts == null) {
            return null;
        }
        for (com.coinbase.v2.models.account.Data account : accounts) {
            if (account.getType() == com.coinbase.v2.models.account.Data.Type.FIAT && account.getCurrency() != null && account.getCurrency().getCode() != null && account.getCurrency().getCode().equals(paymentMethod.getCurrency())) {
                return account;
            }
        }
        return null;
    }

    public int getResourceForType(Type type) {
        switch (type) {
            case ACH_BANK_ACCOUNT:
            case BANK_WIRE:
            case FIAT_ACCOUNT:
            case XFERS:
            case SEPA_BANK_ACCOUNT:
                return R.drawable.connected_account_bank;
            case PAYPAL_ACCOUNT:
                return R.drawable.connected_account_paypal;
            default:
                return R.drawable.connected_account_credit;
        }
    }
}
