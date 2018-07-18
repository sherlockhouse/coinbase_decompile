package com.coinbase.android.utils;

import com.coinbase.android.ApplicationScope;
import com.coinbase.api.internal.models.currency.Data;
import java.util.regex.Pattern;

@ApplicationScope
public class CurrencyUtils {
    public boolean validateAddress(Data currency, String input) {
        if (currency.getAddressRegex() != null) {
            return Pattern.compile(currency.getAddressRegex()).matcher(input).find();
        }
        return true;
    }
}
