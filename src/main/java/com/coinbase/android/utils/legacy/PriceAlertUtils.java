package com.coinbase.android.utils.legacy;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.coinbase.android.Constants;
import com.coinbase.android.R;
import com.coinbase.android.notifications.priceAlerts.LocalPriceAlerts;
import com.coinbase.android.notifications.priceAlerts.legacy.LocalPriceAlert;
import com.coinbase.android.ui.CurrenciesUpdatedConnector;
import com.coinbase.android.utils.MoneyUtils.Currency;
import com.coinbase.android.utils.Utils;
import com.coinbase.api.internal.models.currency.Data;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.joda.money.CurrencyUnit;

public class PriceAlertUtils {
    private static List<LocalPriceAlert> getSavedPriceAlerts(Context context) {
        if (context == null) {
            return null;
        }
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        List<LocalPriceAlert> localPriceAlerts = new ArrayList();
        LocalPriceAlert[] localPriceAlertArray = (LocalPriceAlert[]) new Gson().fromJson(prefs.getString(Constants.NOTIFICATION_SET_LEGACY, null), LocalPriceAlert[].class);
        if (localPriceAlertArray == null) {
            return localPriceAlerts;
        }
        localPriceAlerts.addAll(Arrays.asList(localPriceAlertArray));
        return localPriceAlerts;
    }

    public static boolean hadLegacyPriceAlerts(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).contains(Constants.NOTIFICATION_SET_LEGACY);
    }

    public static boolean upgradeFromLegacyPriceAlerts(LocalPriceAlerts localPriceAlerts, CurrenciesUpdatedConnector currenciesUpdatedConnector, Context context) {
        if (context == null || !hadLegacyPriceAlerts(context) || currenciesUpdatedConnector == null) {
            return false;
        }
        List<com.coinbase.android.notifications.priceAlerts.LocalPriceAlert> updatedPriceAlerts = localPriceAlerts.getPriceAlerts();
        List<Data> currencies = (List) currenciesUpdatedConnector.get().getValue();
        if (currencies == null || currencies.isEmpty()) {
            return false;
        }
        Map<Currency, Data> currencyDataMap = getCurrencyDataMap(currencies, context);
        List<LocalPriceAlert> savedPriceAlerts = getSavedPriceAlerts(context);
        CurrencyUnit unit = Utils.getCurrencyUnitByCode(PreferenceManager.getDefaultSharedPreferences(context).getString(Constants.KEY_ACCOUNT_NATIVE_CURRENCY, null));
        for (LocalPriceAlert savedPriceAlert : savedPriceAlerts) {
            if (currencyDataMap.containsKey(savedPriceAlert.baseCurrency)) {
                boolean isAbove = Boolean.valueOf(savedPriceAlert.isAbove).booleanValue();
                String emoji = isAbove ? "📈 " : "📉 ";
                String message = savedPriceAlert.message;
                if (message.contains(emoji)) {
                    message = message.replace(emoji, "");
                }
                updatedPriceAlerts.add(com.coinbase.android.notifications.priceAlerts.LocalPriceAlert.builder().setAmount(savedPriceAlert.amount).setCreatedOn(savedPriceAlert.createdOn).setCurrency((Data) currencyDataMap.get(savedPriceAlert.baseCurrency)).setCurrencyUnit(unit).setId(savedPriceAlert.id).setIsAbove(isAbove).setDisplayText(message).setNotificationTitle(String.format(context.getString(R.string.price_alerts_notification_title), new Object[]{((Data) currencyDataMap.get(savedPriceAlert.baseCurrency)).getCode()})).setNotificationMessage(message).setTriggeredOn(savedPriceAlert.triggeredOn).setEnabled(savedPriceAlert.enabled).build());
            }
        }
        clearLegacyPriceAlerts(context);
        return !updatedPriceAlerts.isEmpty();
    }

    public static Map<Currency, Data> getCurrencyDataMap(List<Data> currencies, Context context) {
        Map<Currency, Data> currencyDataMap = new HashMap();
        Map<String, Currency> currencyCodeMap = new HashMap();
        for (Currency currencyEnum : Currency.values()) {
            currencyCodeMap.put(currencyEnum.toString().toLowerCase(), currencyEnum);
        }
        for (Data currency : currencies) {
            if (currencyCodeMap.containsKey(currency.getCode().toLowerCase())) {
                currencyDataMap.put(currencyCodeMap.get(currency.getCode().toLowerCase()), currency);
            }
        }
        return currencyDataMap;
    }

    public static void clearLegacyPriceAlerts(Context context) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().remove(Constants.NOTIFICATION_SET_LEGACY).apply();
    }
}
