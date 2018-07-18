package com.coinbase.android.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.widget.ImageView;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.coinbase.android.Constants;
import com.coinbase.android.GlideApp;
import com.coinbase.android.R;
import com.coinbase.android.utils.MoneyUtils.Currency;
import com.coinbase.v2.models.account.Data;
import com.coinbase.v2.models.account.Data.Type;
import com.coinbase.v2.models.account.NativeBalance;
import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.CustomEvent;
import java.math.RoundingMode;
import java.util.List;
import java.util.Locale;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

public class AccountUtils {
    private static final String EURO_CODE = "EUR";

    public static int getAccountIcon(Data account) {
        if (account == null || account.getType() == null) {
            return R.drawable.account_wallet;
        }
        switch (account.getType()) {
            case FIAT:
                return R.drawable.account_fiat;
            case MULTISIG:
            case MULTISIG_VAULT:
                return R.drawable.account_multisig_vault;
            case VAULT:
                return R.drawable.account_vault;
            default:
                return R.drawable.account_wallet;
        }
    }

    public static Data getAccount(List<Data> accounts, String accountId) {
        if (accountId == null || accounts == null) {
            return null;
        }
        for (Data account : accounts) {
            if (account.getId() != null && account.getId().equalsIgnoreCase(accountId)) {
                return account;
            }
        }
        return null;
    }

    public static Money getAccountBalanceMoney(Data account, MoneyFormatterUtil moneyFormatterUtil) {
        if (account != null) {
            return moneyFormatterUtil.moneyFromValue(account.getBalance().getCurrency(), account.getBalance().getAmount());
        }
        logToAnswers("AccountUtils getAccountBalanceMoney: account is null");
        return null;
    }

    public static void setAccountNativeBalanceMoney(Data account, com.coinbase.v2.models.price.Data spotPrice, MoneyFormatterUtil moneyFormatterUtil) {
        if (account != null && spotPrice != null) {
            Money spotPriceValue = moneyFormatterUtil.moneyFromValue(spotPrice.getCurrency(), spotPrice.getAmount());
            Money balanceValue = getAccountBalanceMoney(account, moneyFormatterUtil);
            if (spotPriceValue != null && balanceValue != null) {
                Money nativeBalanceValue = spotPriceValue.multipliedBy(balanceValue.getAmount(), RoundingMode.HALF_EVEN);
                NativeBalance nativeBalance = new NativeBalance();
                nativeBalance.setCurrency(spotPrice.getCurrency());
                nativeBalance.setAmount(nativeBalanceValue.getAmount().toString());
                account.setNativeBalance(nativeBalance);
            }
        }
    }

    public static Money getAccountNativeBalanceMoney(Data account, MoneyFormatterUtil moneyFormatterUtil) {
        if (account == null) {
            logToAnswers("AccountUtils getAccountNativeBalanceMoney: account is null");
            return null;
        } else if (account.getNativeBalance() != null) {
            return moneyFormatterUtil.moneyFromValue(account.getNativeBalance().getCurrency(), account.getNativeBalance().getAmount());
        } else {
            return null;
        }
    }

    public static void setIfEthAccountsExists(List<Data> accounts, SharedPreferences prefs) {
        if (accounts != null) {
            Editor e = prefs.edit();
            for (Data account : accounts) {
                if (account.getCurrency().getCode().equalsIgnoreCase(Currency.ETH.toString())) {
                    e.putBoolean(Constants.KEY_ACCOUNT_HAS_ETH_ACCOUNT, true);
                }
            }
            e.apply();
        }
    }

    public static boolean hasEthAccount(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(Constants.KEY_ACCOUNT_HAS_ETH_ACCOUNT, false);
    }

    public static String getDisplayableAccountName(Data account) {
        if (account == null) {
            return null;
        }
        switch (account.getType()) {
            case FIAT:
                return account.getCurrency().getCode();
            default:
                return account.getCurrency().getName();
        }
    }

    public static boolean isEuroAccount(Data account) {
        return account.getCurrency().getCode().equals(EURO_CODE);
    }

    public static boolean isGBPAccount(Data account) {
        if (account == null || account.getCurrency() == null || account.getCurrency().getCode() == null) {
            return false;
        }
        return TextUtils.equals(account.getCurrency().getCode().toLowerCase(), CurrencyUnit.GBP.getCode().toLowerCase());
    }

    public static int getAccountColor(Data account) {
        int color = -1;
        if (account == null || account.getCurrency() == null) {
            return color;
        }
        String colorString = account.getCurrency().getColor();
        if (org.apache.http.util.TextUtils.isEmpty(colorString)) {
            return color;
        }
        try {
            color = Color.parseColor(colorString);
        } catch (IllegalArgumentException e) {
        }
        return color;
    }

    public static void setAccountImage(final Context context, final ImageView imageView, final Data account) {
        if (context != null && imageView != null && account != null && account.getCurrency() != null) {
            if (account.getCurrency().getImage() != null) {
                setAccountDrawableBackgroundColor(imageView, account);
                GlideApp.with(context).load(account.getCurrency().getImage().getUrl()).placeholder(getDefaultCurrencyDrawable(context, account.getCurrency().getCode())).listener(new RequestListener<Drawable>() {
                    public boolean onLoadFailed(GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        AccountUtils.showDefaultAccountImage(context, imageView, account);
                        return true;
                    }

                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        AccountUtils.setAccountDrawableBackgroundColor(imageView, account);
                        return false;
                    }
                }).into(imageView);
                return;
            }
            showDefaultAccountImage(context, imageView, account);
        }
    }

    private static void showDefaultAccountImage(Context context, ImageView imageView, Data account) {
        if (context == null || imageView == null || account == null || account.getCurrency() == null || account.getCurrency().getCode() == null) {
            showDefaultAccountDrawable(context, imageView, 0, account);
            return;
        }
        switch (account.getType()) {
            case FIAT:
                setAccountDrawableBackgroundColor(imageView, account);
                imageView.setImageDrawable(null);
                return;
            default:
                showDefaultAccountDrawable(context, imageView, getDefaultCurrencyDrawable(context, account.getCurrency().getCode()), account);
                return;
        }
    }

    public static int getDefaultCurrencyDrawable(Context context, String currencyCode) {
        int i = -1;
        switch (currencyCode.hashCode()) {
            case 65575:
                if (currencyCode.equals("BCH")) {
                    i = 1;
                    break;
                }
                break;
            case 66097:
                if (currencyCode.equals("BTC")) {
                    i = 0;
                    break;
                }
                break;
            case 68985:
                if (currencyCode.equals("ETH")) {
                    i = 2;
                    break;
                }
                break;
            case 75707:
                if (currencyCode.equals("LTC")) {
                    i = 3;
                    break;
                }
                break;
        }
        switch (i) {
            case 0:
            case 1:
                return R.drawable.account_btc;
            case 2:
                return R.drawable.account_eth;
            case 3:
                return R.drawable.account_ltc;
            default:
                return 0;
        }
    }

    private static void showDefaultAccountDrawable(Context context, final ImageView imageView, int drawable, final Data account) {
        if (context != null && imageView != null) {
            GlideApp.with(context).load(Integer.valueOf(drawable)).error(null).listener(new RequestListener<Drawable>() {
                public boolean onLoadFailed(GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                    AccountUtils.setAccountDrawableBackgroundColor(imageView, account);
                    return false;
                }

                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                    AccountUtils.setAccountDrawableBackgroundColor(imageView, account);
                    return false;
                }
            }).into(imageView);
        }
    }

    private static void setAccountDrawableBackgroundColor(ImageView imageView, Data account) {
        int color = getAccountColor(account);
        if (color != -1) {
            Utils.updateBackgroundColorWithAlpha(imageView, color);
        }
    }

    public static String getFiatCurrencySymbol(Data account) {
        if (account == null || account.getType() != Type.FIAT) {
            return null;
        }
        return Utils.getCurrencyUnitByCode(account.getCurrency().getCode()).getSymbol(Locale.getDefault());
    }

    private static void logToAnswers(String logMessage) {
        Answers.getInstance().logCustom((CustomEvent) new CustomEvent(logMessage).putCustomAttribute("version", Integer.valueOf(1)));
    }
}
