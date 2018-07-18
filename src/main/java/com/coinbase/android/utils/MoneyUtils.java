package com.coinbase.android.utils;

import com.coinbase.android.R;

public class MoneyUtils {

    public enum Currency {
        BTC("BTC", R.string.bitcoin_undercase, R.string.btc),
        ETH("ETH", R.string.ethereum_undercase, R.string.eth),
        LTC("LTC", R.string.litecoin_undercase, R.string.ltc),
        USD("USD", R.string.usd, R.string.usd);
        
        private final int _currencyResId;
        private final int _undercaseResId;
        private final String _value;

        private Currency(String value, int undercaseResId, int currencyResId) {
            this._value = value;
            this._undercaseResId = undercaseResId;
            this._currencyResId = currencyResId;
        }

        public static Currency fromString(String text) {
            if (text != null) {
                for (Currency str : values()) {
                    if (text.equalsIgnoreCase(str.toString())) {
                        return str;
                    }
                }
            }
            return null;
        }

        public String toString() {
            return this._value;
        }

        public int getUndercaseResId() {
            return this._undercaseResId;
        }

        public int getCurrencyResId() {
            return this._currencyResId;
        }
    }
}
