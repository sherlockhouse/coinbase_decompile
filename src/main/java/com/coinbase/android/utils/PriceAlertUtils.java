package com.coinbase.android.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import com.coinbase.android.ComponentProvider;
import com.coinbase.android.Constants;
import com.coinbase.android.notifications.priceAlerts.LocalPriceAlert;
import com.coinbase.android.notifications.priceAlerts.LocalPriceAlerts;
import com.coinbase.android.ui.CurrenciesUpdatedConnector;
import com.coinbase.api.internal.CoinbaseAdapterFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PriceAlertUtils {
    private static final String HAD_PRICE_ALERT = "had_price_alert";

    private static Gson getGson() {
        return new GsonBuilder().registerTypeAdapterFactory(CoinbaseAdapterFactory.create()).create();
    }

    public static LocalPriceAlerts getSavedPriceAlerts(Context context) {
        if (context == null) {
            return null;
        }
        return getSavedPriceAlerts(context, ((ComponentProvider) context.getApplicationContext()).applicationComponent().currenciesUpdatedConnector());
    }

    public static LocalPriceAlerts getSavedPriceAlerts(Context context, CurrenciesUpdatedConnector currenciesUpdatedConnector) {
        if (context == null) {
            return null;
        }
        LocalPriceAlerts localPriceAlerts = LocalPriceAlerts.builder().setPriceAlerts(new ArrayList()).build();
        if (com.coinbase.android.utils.legacy.PriceAlertUtils.upgradeFromLegacyPriceAlerts(localPriceAlerts, currenciesUpdatedConnector, context)) {
            Collections.sort(localPriceAlerts.getPriceAlerts(), new Comparator<LocalPriceAlert>() {
                public int compare(LocalPriceAlert alert1, LocalPriceAlert alert2) {
                    if (alert1.getCreatedOn() < alert2.getCreatedOn()) {
                        return 1;
                    }
                    return alert1.getCreatedOn() == alert2.getCreatedOn() ? 0 : -1;
                }
            });
            savePriceAlerts(localPriceAlerts, PreferenceManager.getDefaultSharedPreferences(context));
            return localPriceAlerts;
        }
        String json = PreferenceManager.getDefaultSharedPreferences(context).getString(Constants.NOTIFICATION_SET, null);
        if (json == null) {
            return localPriceAlerts;
        }
        localPriceAlerts = (LocalPriceAlerts) getGson().fromJson(json, LocalPriceAlerts.class);
        if (localPriceAlerts == null) {
            return localPriceAlerts;
        }
        Collections.sort(localPriceAlerts.getPriceAlerts(), /* anonymous class already generated */);
        return localPriceAlerts;
    }

    public static void savePriceAlert(Context context, LocalPriceAlert localPriceAlert) {
        if (context != null && localPriceAlert != null) {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            LocalPriceAlerts localPriceAlerts = getSavedPriceAlerts(context);
            if (localPriceAlerts != null && localPriceAlerts.getPriceAlerts() != null) {
                int index = -1;
                List<LocalPriceAlert> priceAlertList = localPriceAlerts.getPriceAlerts();
                for (int i = 0; i < priceAlertList.size(); i++) {
                    LocalPriceAlert aLocalPriceAlert = (LocalPriceAlert) priceAlertList.get(i);
                    if (aLocalPriceAlert.getId() != null && aLocalPriceAlert.getId().equals(localPriceAlert.getId())) {
                        index = i;
                        break;
                    }
                }
                if (index <= -1) {
                    priceAlertList.add(localPriceAlert);
                } else {
                    priceAlertList.set(index, localPriceAlert);
                }
                savePriceAlerts(localPriceAlerts, prefs);
            }
        }
    }

    private static void savePriceAlerts(LocalPriceAlerts localPriceAlerts, SharedPreferences prefs) {
        Editor editor = prefs.edit();
        editor.putString(Constants.NOTIFICATION_SET, getGson().toJson((Object) localPriceAlerts));
        editor.apply();
    }

    public static void deletePriceAlert(Context context, LocalPriceAlert localPriceAlert) {
        Object localPriceAlerts = getSavedPriceAlerts(context);
        if (localPriceAlerts != null && localPriceAlerts.getPriceAlerts() != null) {
            List<LocalPriceAlert> priceAlertList = localPriceAlerts.getPriceAlerts();
            for (LocalPriceAlert aLocalPriceAlert : priceAlertList) {
                if (aLocalPriceAlert.getId() != null && aLocalPriceAlert.getId().equals(localPriceAlert.getId())) {
                    priceAlertList.remove(aLocalPriceAlert);
                    break;
                }
            }
            Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
            editor.putString(Constants.NOTIFICATION_SET, getGson().toJson(localPriceAlerts));
            editor.apply();
        }
    }

    public static String aboveString(boolean above) {
        return above ? "true" : "false";
    }

    public static void setHadPriceAlert(Context context, boolean hadPriceAlert) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(HAD_PRICE_ALERT, hadPriceAlert).apply();
    }

    public static boolean hadPriceAlert(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(HAD_PRICE_ALERT, false);
    }
}
