package com.coinbase.android.utils;

import com.coinbase.android.ApplicationScope;
import com.coinbase.android.ui.CurrenciesUpdatedConnector;
import com.coinbase.android.utils.CryptoUri.InvalidCryptoUriException;
import com.coinbase.api.internal.models.currency.Data;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import javax.inject.Inject;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

@ApplicationScope
public class CryptoUriParser {
    private final CurrenciesUpdatedConnector mCurrenciesUpdatedConnector;

    @Inject
    public CryptoUriParser(CurrenciesUpdatedConnector currenciesUpdatedConnector) {
        this.mCurrenciesUpdatedConnector = currenciesUpdatedConnector;
    }

    public CryptoUri parse(String uriString) throws InvalidCryptoUriException {
        String schemeSpecificPart = uriString;
        CryptoUri result = new CryptoUri();
        for (Data currency : (List) this.mCurrenciesUpdatedConnector.get().getValue()) {
            if (uriString.toLowerCase().startsWith(currency.getUriScheme().toLowerCase())) {
                int endIndex = uriString.indexOf(CryptoUri.URI_SCHEME_DELIMETER);
                if (endIndex != -1) {
                    schemeSpecificPart = uriString.substring(endIndex + 1);
                    result = new CryptoUri();
                    result.setScheme(currency.getUriScheme());
                    break;
                }
            }
        }
        parseParams(result, schemeSpecificPart);
        return result;
    }

    private void parseParams(CryptoUri cryptoUri, String schemeSpecificPart) throws InvalidCryptoUriException {
        if (cryptoUri != null && schemeSpecificPart != null) {
            String[] addressAndParams = schemeSpecificPart.split("\\?");
            cryptoUri.setAddress(addressAndParams[0]);
            ArrayList<NameValuePair> params = new ArrayList();
            if (addressAndParams.length > 1) {
                URLEncodedUtils.parse(params, new Scanner(addressAndParams[1]), "UTF-8");
            }
            Iterator it = params.iterator();
            while (it.hasNext()) {
                NameValuePair param = (NameValuePair) it.next();
                if (param.getName() != null) {
                    String name = param.getName();
                    int i = -1;
                    switch (name.hashCode()) {
                        case -1413853096:
                            if (name.equals("amount")) {
                                i = 2;
                                break;
                            }
                            break;
                        case 102727412:
                            if (name.equals(CryptoUri.LABEL)) {
                                i = 0;
                                break;
                            }
                            break;
                        case 111972721:
                            if (name.equals(CryptoUri.VALUE)) {
                                i = 3;
                                break;
                            }
                            break;
                        case 954925063:
                            if (name.equals("message")) {
                                i = 1;
                                break;
                            }
                            break;
                    }
                    switch (i) {
                        case 0:
                            cryptoUri.setLabel(param.getValue());
                            break;
                        case 1:
                            cryptoUri.setMessage(param.getValue());
                            break;
                        case 2:
                            try {
                                cryptoUri.setAmount(new BigDecimal(param.getValue()).setScale(8, RoundingMode.HALF_EVEN));
                                break;
                            } catch (Exception ex) {
                                throw new InvalidCryptoUriException(ex);
                            }
                        case 3:
                            try {
                                cryptoUri.setAmount(new BigDecimal(param.getValue()).setScale(8, RoundingMode.HALF_EVEN));
                                break;
                            } catch (Exception ex2) {
                                throw new InvalidCryptoUriException(ex2);
                            }
                        default:
                            break;
                    }
                }
            }
        }
    }
}
