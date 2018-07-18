package com.coinbase.android.settings;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import com.coinbase.android.ComponentProvider;
import com.coinbase.android.Constants;
import com.coinbase.android.Log;
import com.coinbase.android.task.SyncAccountsTask;
import com.coinbase.android.task.SyncAccountsTask.SyncAccountsListener;
import java.util.concurrent.Semaphore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PreferenceUpgrade {
    public static final String KEY_ACCOUNT_ACCESS_TOKEN = "account_%d_access_token";
    public static final String KEY_ACCOUNT_APP_USAGE = "account_%1$d_app_usage";
    public static final String KEY_ACCOUNT_ENABLE_TIPPING = "account_%1$d_enable_tipping";
    public static final String KEY_ACCOUNT_FIRST_LAUNCH = "account_%1$d_first_launch";
    public static final String KEY_ACCOUNT_FULL_NAME = "account_%d_full_name";
    public static final String KEY_ACCOUNT_ID = "account_%d_id";
    public static final String KEY_ACCOUNT_LIMIT = "account_%1$d_limit_%2$s";
    public static final String KEY_ACCOUNT_LIMIT_CURRENCY = "account_%1$d_limit_currency_%2$s";
    public static final String KEY_ACCOUNT_NAME = "account_%d_name";
    public static final String KEY_ACCOUNT_NATIVE_CURRENCY = "account_%d_native_currency";
    public static final String KEY_ACCOUNT_PIN = "account_%d_pin";
    public static final String KEY_ACCOUNT_PIN_VIEW_ALLOWED = "account_%d_pin_view_allowed";
    public static final String KEY_ACCOUNT_POS_BTC_AMT = "account_%1$d_pos_btc_amt";
    public static final String KEY_ACCOUNT_POS_NOTES = "account_%1$d_pos_notes";
    public static final String KEY_ACCOUNT_RATE_NOTICE_STATE = "account_%1$d_rate_notice_state";
    public static final String KEY_ACCOUNT_REFRESH_TOKEN = "account_%d_refresh_token";
    public static final String KEY_ACCOUNT_SHOW_BALANCE = "account_%1$d_show_balance";
    public static final String KEY_ACCOUNT_TIME_ZONE = "account_%d_time_zone";
    public static final String KEY_ACCOUNT_TOKEN_EXPIRES_AT = "account_%d_token_expires_at";
    public static final String KEY_ACCOUNT_TRANSFER_CURRENCY_BTC = "account_%1$d_transfer_currency_btc";
    public static final String KEY_ACCOUNT_VALID = "account_%d_valid";
    public static final String KEY_ACCOUNT_VALID_DESC = "account_%d_valid_desc";
    public static final String KEY_ACTIVE_ACCOUNT = "active_account";
    private static final Logger LOGGER = LoggerFactory.getLogger(PreferenceUpgrade.class);

    public static void perform(Application context) {
        Log.i("PreferenceUpgrade", "In perform");
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        final Editor e = prefs.edit();
        upgradePINPreferences(prefs, context);
        int activeAccountIndex = prefs.getInt(KEY_ACTIVE_ACCOUNT, -1);
        if (activeAccountIndex > -1) {
            try {
                LOGGER.error("perform_preference_upgrade");
                if (prefs.getBoolean(String.format(KEY_ACCOUNT_VALID, new Object[]{Integer.valueOf(activeAccountIndex)}), true)) {
                    Log.i("PreferenceUpgrade", "Upgrading preferences from old version");
                    upgradeString(prefs, KEY_ACCOUNT_ACCESS_TOKEN, activeAccountIndex, Constants.KEY_ACCOUNT_ACCESS_TOKEN);
                    upgradeString(prefs, KEY_ACCOUNT_REFRESH_TOKEN, activeAccountIndex, Constants.KEY_ACCOUNT_REFRESH_TOKEN);
                    upgradeLong(prefs, KEY_ACCOUNT_TOKEN_EXPIRES_AT, activeAccountIndex, Constants.KEY_ACCOUNT_TOKEN_EXPIRES_AT);
                    upgradeString(prefs, KEY_ACCOUNT_VALID_DESC, activeAccountIndex, Constants.KEY_ACCOUNT_VALID_DESC);
                    upgradeString(prefs, KEY_ACCOUNT_NAME, activeAccountIndex, Constants.KEY_ACCOUNT_EMAIL);
                    upgradeString(prefs, KEY_ACCOUNT_FULL_NAME, activeAccountIndex, Constants.KEY_ACCOUNT_FULL_NAME);
                    upgradeString(prefs, KEY_ACCOUNT_TIME_ZONE, activeAccountIndex, Constants.KEY_ACCOUNT_TIME_ZONE);
                    upgradeString(prefs, KEY_ACCOUNT_POS_NOTES, activeAccountIndex, Constants.KEY_ACCOUNT_POS_NOTES);
                    upgradeBoolean(prefs, KEY_ACCOUNT_POS_BTC_AMT, activeAccountIndex, Constants.KEY_ACCOUNT_POS_BTC_AMT);
                    upgradeBoolean(prefs, KEY_ACCOUNT_SHOW_BALANCE, activeAccountIndex, Constants.KEY_ACCOUNT_SHOW_BALANCE);
                    upgradeBoolean(prefs, KEY_ACCOUNT_FIRST_LAUNCH, activeAccountIndex, Constants.KEY_ACCOUNT_FIRST_LAUNCH);
                    upgradeString(prefs, KEY_ACCOUNT_RATE_NOTICE_STATE, activeAccountIndex, Constants.KEY_ACCOUNT_RATE_NOTICE_STATE);
                    upgradeInt(prefs, KEY_ACCOUNT_APP_USAGE, activeAccountIndex, Constants.KEY_ACCOUNT_APP_USAGE);
                    upgradeBoolean(prefs, KEY_ACCOUNT_TRANSFER_CURRENCY_BTC, activeAccountIndex, Constants.KEY_ACCOUNT_TRANSFER_CURRENCY_BTC);
                    upgradeBoolean(prefs, KEY_ACCOUNT_ENABLE_TIPPING, activeAccountIndex, Constants.KEY_ACCOUNT_ENABLE_TIPPING);
                    upgradeString(prefs, KEY_ACCOUNT_PIN, activeAccountIndex, Constants.KEY_ACCOUNT_PIN);
                    upgradeBoolean(prefs, KEY_ACCOUNT_PIN_VIEW_ALLOWED, activeAccountIndex, Constants.KEY_ACCOUNT_PIN_VIEW_ALLOWED);
                    upgradeString(prefs, KEY_ACCOUNT_NATIVE_CURRENCY, activeAccountIndex, Constants.KEY_ACCOUNT_NATIVE_CURRENCY);
                    upgradeString(prefs, KEY_ACCOUNT_ID, activeAccountIndex, Constants.KEY_USER_ID);
                    final Semaphore sem = new Semaphore(0);
                    new SyncAccountsTask(context, new SyncAccountsListener() {
                        public void onPreExecute() {
                            try {
                                sem.acquire();
                            } catch (Exception e) {
                                Log.e("PreferenceUpgrade", "Failed to upgrade, giving up and clearing preferences");
                            }
                        }

                        public void onException() {
                        }

                        public void onFinally() {
                            sem.release();
                            e.remove(PreferenceUpgrade.KEY_ACTIVE_ACCOUNT);
                            e.apply();
                            Log.i("PreferenceUpgrade", "Successfully upgraded preferences");
                        }
                    }).syncAccounts();
                    return;
                }
                throw new Exception("Account invalid, not upgrading");
            } catch (Exception ex) {
                LOGGER.error("perform_preference_upgrade_exception", ex);
                Log.e("PreferenceUpgrade", "Failed to upgrade, giving up and clearing preferences");
                ex.printStackTrace();
                e.clear();
                e.apply();
                return;
            }
        }
        Log.i("PreferenceUpgrade", "Missing KEY_ACTIVE_ACCOUNT, already upgraded");
    }

    @SuppressLint({"CommitPrefEdits"})
    private static void upgradePINPreferences(SharedPreferences prefs, Application context) {
        if (!((ComponentProvider) context).applicationComponent().pinManager().isPinEnabled(context)) {
            boolean hasPin = prefs.getString(Constants.KEY_ACCOUNT_PIN, null) != null;
            boolean pinViewAllowed = prefs.getBoolean(Constants.KEY_ACCOUNT_PIN_VIEW_ALLOWED, false);
            Editor e = prefs.edit();
            if (hasPin) {
                e.putBoolean(Constants.KEY_PIN_ENABLED_APP_OPEN, true);
                if (pinViewAllowed) {
                    e.putBoolean(Constants.KEY_PIN_ENABLED_APP_OPEN, false);
                    e.putBoolean(Constants.KEY_PIN_ENABLED_SEND_MONEY, true);
                }
            }
            e.commit();
        }
    }

    private static void upgradeString(SharedPreferences prefs, String oldKey, int accountIndex, String newKey) {
        String oldKeyFormatted = String.format(oldKey, new Object[]{Integer.valueOf(accountIndex)});
        if (prefs.contains(oldKeyFormatted)) {
            String oldValue = prefs.getString(oldKeyFormatted, null);
            Editor e = prefs.edit();
            e.putString(newKey, oldValue);
            e.remove(oldKeyFormatted);
            e.apply();
        }
    }

    private static void upgradeInt(SharedPreferences prefs, String oldKey, int accountIndex, String newKey) {
        String oldKeyFormatted = String.format(oldKey, new Object[]{Integer.valueOf(accountIndex)});
        if (prefs.contains(oldKeyFormatted)) {
            int oldValue = prefs.getInt(oldKeyFormatted, 0);
            Editor e = prefs.edit();
            e.putInt(newKey, oldValue);
            e.remove(oldKeyFormatted);
            e.apply();
        }
    }

    private static void upgradeLong(SharedPreferences prefs, String oldKey, int accountIndex, String newKey) {
        String oldKeyFormatted = String.format(oldKey, new Object[]{Integer.valueOf(accountIndex)});
        if (prefs.contains(oldKeyFormatted)) {
            Long oldValue = Long.valueOf(prefs.getLong(oldKeyFormatted, 0));
            Editor e = prefs.edit();
            e.putLong(newKey, oldValue.longValue());
            e.remove(oldKeyFormatted);
            e.apply();
        }
    }

    private static void upgradeBoolean(SharedPreferences prefs, String oldKey, int accountIndex, String newKey) {
        String oldKeyFormatted = String.format(oldKey, new Object[]{Integer.valueOf(accountIndex)});
        if (prefs.contains(oldKeyFormatted)) {
            boolean oldValue = prefs.getBoolean(oldKeyFormatted, false);
            Editor e = prefs.edit();
            e.putBoolean(newKey, oldValue);
            e.remove(oldKeyFormatted);
            e.apply();
        }
    }
}
