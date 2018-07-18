package com.coinbase.android.pin;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import com.coinbase.android.Constants;
import com.coinbase.android.crypto.ByteArrayUtils;
import com.coinbase.android.crypto.CoinBaseCrypto;
import com.coinbase.android.settings.LocalUserDataUpdatedConnector;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import javax.crypto.Cipher;

public class PINManager {
    private static final int BLOCK_SIZE;
    public static final String[] KEYS = new String[]{Constants.KEY_PIN_ENABLED_APP_OPEN, Constants.KEY_PIN_ENABLED_SEND_MONEY};
    private static boolean isQuitPINLock = false;
    private static SecureRandom rand = new SecureRandom();
    private final LocalUserDataUpdatedConnector mLocalUserDataUpdatedConnector;

    public enum AccessType {
        APP_OPEN,
        SEND_MONEY
    }

    static {
        try {
            BLOCK_SIZE = getCipher().getBlockSize();
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        }
    }

    public PINManager(LocalUserDataUpdatedConnector localUserDataUpdatedConnector) {
        this.mLocalUserDataUpdatedConnector = localUserDataUpdatedConnector;
    }

    public void setPinEntered(Context context, boolean entered) {
        if (context != null) {
            Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
            edit.putBoolean(Constants.KEY_HAS_USER_ENTERED_PIN, entered);
            edit.apply();
        }
    }

    public boolean isPinEnabled(Context context) {
        if (context == null) {
            return false;
        }
        boolean enabled = false;
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        for (String key : KEYS) {
            enabled |= prefs.getBoolean(key, false);
        }
        return enabled;
    }

    private static Cipher getCipher() throws GeneralSecurityException {
        return Cipher.getInstance("AES/CBC/PKCS5Padding");
    }

    private byte[] generateSalt() {
        byte[] result = new byte[BLOCK_SIZE];
        rand.nextBytes(result);
        return result;
    }

    public boolean isProtected(Context context, AccessType accessType) {
        if (context == null || accessType == null) {
            return false;
        }
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        switch (accessType) {
            case APP_OPEN:
                return prefs.getBoolean(Constants.KEY_PIN_ENABLED_APP_OPEN, false);
            case SEND_MONEY:
                return prefs.getBoolean(Constants.KEY_PIN_ENABLED_SEND_MONEY, false);
            default:
                return false;
        }
    }

    public boolean shouldGrantAccess(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(Constants.KEY_HAS_USER_ENTERED_PIN, false);
    }

    void setPin(Context context, String pin) {
        Editor e = PreferenceManager.getDefaultSharedPreferences(context).edit();
        byte[] salt = generateSalt();
        byte[] encodedPin = CoinBaseCrypto.getKey(pin.toCharArray(), salt, 1200, 128);
        e.putString(Constants.KEY_ACCOUNT_SALT, ByteArrayUtils.toHexString(salt));
        e.putString(Constants.KEY_ACCOUNT_PIN, "enc_" + ByteArrayUtils.toHexString(encodedPin));
        e.apply();
        this.mLocalUserDataUpdatedConnector.get().onNext(null);
    }

    boolean verifyPin(Context context, String enteredPin) {
        if (enteredPin == null || enteredPin.equals("")) {
            return false;
        }
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String pin = prefs.getString(Constants.KEY_ACCOUNT_PIN, null);
        if (pin == null) {
            return false;
        }
        if (!pin.startsWith("enc_")) {
            String plain_pin = prefs.getString(Constants.KEY_ACCOUNT_PIN, null);
            if (plain_pin == null) {
                return false;
            }
            byte[] plain_salt = generateSalt();
            byte[] encodedPin = CoinBaseCrypto.getKey(plain_pin.toCharArray(), plain_salt, 1200, 128);
            Editor e = prefs.edit();
            e.remove(Constants.KEY_ACCOUNT_PIN);
            e.putString(Constants.KEY_ACCOUNT_SALT, ByteArrayUtils.toHexString(plain_salt));
            e.putString(Constants.KEY_ACCOUNT_PIN, "enc_" + ByteArrayUtils.toHexString(encodedPin));
            e.apply();
        }
        return ("enc_" + ByteArrayUtils.toHexString(CoinBaseCrypto.getKey(enteredPin.toCharArray(), ByteArrayUtils.hexToBytes(prefs.getString(Constants.KEY_ACCOUNT_SALT, null)), 1200, 128))).equals(prefs.getString(Constants.KEY_ACCOUNT_PIN, null));
    }

    public boolean isQuitPINLock() {
        return isQuitPINLock;
    }

    public void setQuitPINLock(boolean quitPINLock) {
        isQuitPINLock = quitPINLock;
    }
}
