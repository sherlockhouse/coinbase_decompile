package com.coinbase.android.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

public class CryptoUri {
    public static final String AMOUNT = "amount";
    public static final int CRYPTO_SCALE = 8;
    private static final String ETHEREUM_URI_SCHEME = "ethereum:";
    public static final String LABEL = "label";
    public static final String MESSAGE = "message";
    public static final String URI_SCHEME_DELIMETER = ":";
    public static final String VALUE = "value";
    private String address;
    private BigDecimal amount;
    private String label;
    private String message;
    private String scheme;

    public static class InvalidCryptoUriException extends Exception {
        InvalidCryptoUriException() {
        }

        InvalidCryptoUriException(Throwable ex) {
            super(ex);
        }
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
        if (scheme != null && !scheme.endsWith(URI_SCHEME_DELIMETER)) {
            this.scheme += URI_SCHEME_DELIMETER;
        }
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean equals(Object other) {
        if (other instanceof CryptoUri) {
            return toString().equals(other.toString());
        }
        return false;
    }

    public String toString() {
        StringBuilder uriBuilder = new StringBuilder("");
        if (this.scheme != null) {
            uriBuilder = new StringBuilder(this.scheme);
        }
        uriBuilder.append(this.address);
        ArrayList<NameValuePair> params = new ArrayList();
        if (this.amount != null) {
            if (this.scheme.equalsIgnoreCase(ETHEREUM_URI_SCHEME)) {
                params.add(new BasicNameValuePair(VALUE, this.amount.toPlainString()));
            } else {
                params.add(new BasicNameValuePair("amount", this.amount.toPlainString()));
            }
        }
        if (this.message != null) {
            params.add(new BasicNameValuePair("message", this.message));
        }
        if (this.label != null) {
            params.add(new BasicNameValuePair(LABEL, this.label));
        }
        uriBuilder.append('?');
        uriBuilder.append(URLEncodedUtils.format(params, "UTF-8"));
        return uriBuilder.toString();
    }

    public int hashCode() {
        return toString().hashCode();
    }
}
