package com.coinbase.v1.entity;

import com.coinbase.api.internal.ApiConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.io.Serializable;
import org.joda.money.Money;

public class Button implements Serializable {
    private static final long serialVersionUID = -5470904374812796853L;
    private Boolean _autoRedirect;
    private String _callbackUrl;
    private String _cancelUrl;
    private Boolean _choosePrice;
    private String _code;
    private String _custom;
    private Boolean _customSecure;
    private String _description;
    private Boolean _includeAddress;
    private Boolean _includeEmail;
    private String _infoUrl;
    private String _name;
    private Money _price;
    private String _priceCurrencyIso;
    private String _priceString;
    private Repeat _repeat;
    private Style _style;
    private String _successUrl;
    private String _text;
    private Type _type;

    public enum Repeat {
        NEVER("never"),
        DAILY("daily"),
        WEEKLY("weekly"),
        BIWEEKLY("every_two_weeks"),
        MONTHLY("monthly"),
        QUARTERLY("quarterly"),
        YEARLY("yearly");
        
        private String _value;

        private Repeat(String value) {
            this._value = value;
        }

        @JsonValue
        public String toString() {
            return this._value;
        }

        @JsonCreator
        public static Repeat create(String val) {
            for (Repeat repeat : values()) {
                if (repeat.toString().equalsIgnoreCase(val)) {
                    return repeat;
                }
            }
            return null;
        }
    }

    public enum Style {
        BUY_NOW_LARGE("buy_now_large"),
        BUY_NOW_SMALL("buy_now_small"),
        DONATION_LARGE("donation_large"),
        DONATION_SMALL("donation_small"),
        SUBSCRIPTION_LARGE("subscription_large"),
        SUBSCRIPTION_SMALL("subscription_small"),
        CUSTOM_LARGE("custom_large"),
        CUSTOM_SMALL("custom_small"),
        NONE(ApiConstants.NONE);
        
        private String _value;

        private Style(String value) {
            this._value = value;
        }

        @JsonValue
        public String toString() {
            return this._value;
        }

        @JsonCreator
        public static Style create(String val) {
            for (Style style : values()) {
                if (style.toString().equalsIgnoreCase(val)) {
                    return style;
                }
            }
            return null;
        }
    }

    public enum Type {
        BUY_NOW("buy_now"),
        DONATION("donation"),
        SUBSCRIPTION("subscription");
        
        private String _value;

        private Type(String value) {
            this._value = value;
        }

        @JsonValue
        public String toString() {
            return this._value;
        }

        @JsonCreator
        public static Type create(String val) {
            for (Type type : values()) {
                if (type.toString().equalsIgnoreCase(val)) {
                    return type;
                }
            }
            return null;
        }
    }

    public Repeat getRepeat() {
        return this._repeat;
    }

    public void setRepeat(Repeat repeat) {
        this._repeat = repeat;
    }

    public String getId() {
        return this._code;
    }

    public void setId(String id) {
        this._code = id;
    }

    public String getCode() {
        return this._code;
    }

    public void setCode(String code) {
        this._code = code;
    }

    public String getName() {
        return this._name;
    }

    public void setName(String name) {
        this._name = name;
    }

    public String getPriceString() {
        return this._priceString;
    }

    public void setPriceString(String priceString) {
        this._priceString = priceString;
    }

    public String getPriceCurrencyIso() {
        return this._priceCurrencyIso;
    }

    public void setPriceCurrencyIso(String priceCurrencyIso) {
        this._priceCurrencyIso = priceCurrencyIso;
    }

    public Type getType() {
        return this._type;
    }

    public void setType(Type type) {
        this._type = type;
    }

    public Style getStyle() {
        return this._style;
    }

    public void setStyle(Style style) {
        this._style = style;
    }

    public String getText() {
        return this._text;
    }

    public void setText(String text) {
        this._text = text;
    }

    public String getDescription() {
        return this._description;
    }

    public void setDescription(String description) {
        this._description = description;
    }

    public String getCustom() {
        return this._custom;
    }

    public void setCustom(String custom) {
        this._custom = custom;
    }

    public Boolean getCustomSecure() {
        return this._customSecure;
    }

    public void setCustomSecure(Boolean customSecure) {
        this._customSecure = customSecure;
    }

    public String getCallbackUrl() {
        return this._callbackUrl;
    }

    public void setCallbackUrl(String customUrl) {
        this._callbackUrl = customUrl;
    }

    public String getSuccessUrl() {
        return this._successUrl;
    }

    public void setSuccessUrl(String successUrl) {
        this._successUrl = successUrl;
    }

    public String getCancelUrl() {
        return this._cancelUrl;
    }

    public void setCancelUrl(String cancelUrl) {
        this._cancelUrl = cancelUrl;
    }

    public String getInfoUrl() {
        return this._infoUrl;
    }

    public void setInfoUrl(String infoUrl) {
        this._infoUrl = infoUrl;
    }

    public Boolean getAutoRedirect() {
        return this._autoRedirect;
    }

    public void setAutoRedirect(Boolean autoRedirect) {
        this._autoRedirect = autoRedirect;
    }

    public Boolean getChoosePrice() {
        return this._choosePrice;
    }

    public void setChoosePrice(Boolean choosePrice) {
        this._choosePrice = choosePrice;
    }

    public Boolean getIncludeAddress() {
        return this._includeAddress;
    }

    public void setIncludeAddress(Boolean includeAddress) {
        this._includeAddress = includeAddress;
    }

    public Boolean getIncludeEmail() {
        return this._includeEmail;
    }

    public void setIncludeEmail(Boolean includeEmail) {
        this._includeEmail = includeEmail;
    }

    public Money getPrice() {
        return this._price;
    }

    public void setPrice(Money price) {
        this._price = price;
        if (price != null) {
            setPriceString(price.getAmount().toPlainString());
            setPriceCurrencyIso(price.getCurrencyUnit().getCurrencyCode());
            return;
        }
        setPriceString(null);
        setPriceCurrencyIso(null);
    }
}
