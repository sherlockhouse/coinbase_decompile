package com.coinbase.v1.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.io.Serializable;
import org.joda.money.Money;

public class OAuthCodeRequest implements Serializable {
    private static final long serialVersionUID = 3716938132337502204L;
    private String clientId;
    private String clientSecret;
    private Meta meta;
    private String password;
    private String redirectUri;
    private String referrerId;
    private String scope;
    private String token;
    private String username;

    public static class Meta implements Serializable {
        private static final long serialVersionUID = -5468361596726979847L;
        private String _name;
        private Money _sendLimitAmount;
        private Period _sendLimitPeriod;

        public enum Period {
            DAILY("daily"),
            WEEKLY("weekly"),
            MONTHLY("monthly");
            
            private String _value;

            private Period(String value) {
                this._value = value;
            }

            @JsonValue
            public String toString() {
                return this._value;
            }

            @JsonCreator
            public static Period create(String val) {
                for (Period period : values()) {
                    if (period.toString().equalsIgnoreCase(val)) {
                        return period;
                    }
                }
                return null;
            }
        }

        public String getName() {
            return this._name;
        }

        public void setName(String name) {
            this._name = name;
        }

        public Money getSendLimitAmount() {
            return this._sendLimitAmount;
        }

        public void setSendLimitAmount(Money sendLimitAmount) {
            this._sendLimitAmount = sendLimitAmount;
        }

        public Period getSendLimitPeriod() {
            return this._sendLimitPeriod;
        }

        public void setSendLimitPeriod(Period sendLimitPeriod) {
            this._sendLimitPeriod = sendLimitPeriod;
        }

        public String getSendLimitCurrency() {
            if (this._sendLimitAmount != null) {
                return this._sendLimitAmount.getCurrencyUnit().getCurrencyCode();
            }
            return null;
        }
    }

    public String getRedirectUri() {
        return this.redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    public String getClientId() {
        return this.clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String email) {
        this.username = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getReferrerId() {
        return this.referrerId;
    }

    public void setReferrerId(String referrerId) {
        this.referrerId = referrerId;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getScope() {
        return this.scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public Meta getMeta() {
        return this.meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public String getClientSecret() {
        return this.clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }
}
