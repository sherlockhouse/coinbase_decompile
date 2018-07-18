package com.coinbase.v1.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.io.Serializable;
import java.util.HashMap;
import org.joda.money.Money;
import org.joda.time.DateTime;

public class Transfer implements Serializable {
    private static final long serialVersionUID = -5717063284463576652L;
    private String _account;
    private Money _btc;
    private String _code;
    private DateTime _createdAt;
    private String _description;
    private HashMap<String, Money> _fees;
    private DateTime _payoutDate;
    private Status _status;
    private Money _subtotal;
    private Money _total;
    private String _transactionId;
    private Type _type;

    public enum Status {
        CREATED("created"),
        PENDING("Pending"),
        COMPLETE("Complete"),
        CANCELED("Canceled"),
        REVERSED("Reversed");
        
        private String _value;

        private Status(String value) {
            this._value = value;
        }

        @JsonValue
        public String toString() {
            return this._value;
        }

        @JsonCreator
        public static Status create(String val) {
            for (Status status : values()) {
                if (status.toString().equalsIgnoreCase(val)) {
                    return status;
                }
            }
            return null;
        }
    }

    public enum Type {
        SELL("Sell"),
        BUY("Buy");
        
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

    public Type getType() {
        return this._type;
    }

    public void setType(Type type) {
        this._type = type;
    }

    public Status getStatus() {
        return this._status;
    }

    public void setStatus(Status status) {
        this._status = status;
    }

    public String getCode() {
        return this._code;
    }

    public void setCode(String code) {
        this._code = code;
    }

    public DateTime getCreatedAt() {
        return this._createdAt;
    }

    public void setCreatedAt(DateTime createdAt) {
        this._createdAt = createdAt;
    }

    public DateTime getPayoutDate() {
        return this._payoutDate;
    }

    public void setPayoutDate(DateTime payoutDate) {
        this._payoutDate = payoutDate;
    }

    public HashMap<String, Money> getFees() {
        return this._fees;
    }

    @JsonDeserialize(as = HashMap.class, contentAs = Money.class, keyAs = String.class)
    public void setFees(HashMap<String, Money> fees) {
        this._fees = fees;
    }

    public String getTransactionId() {
        return this._transactionId;
    }

    public void setTransactionId(String transactionId) {
        this._transactionId = transactionId;
    }

    public Money getBtc() {
        return this._btc;
    }

    public void setBtc(Money btc) {
        this._btc = btc;
    }

    public Money getSubtotal() {
        return this._subtotal;
    }

    public void setSubtotal(Money subtotal) {
        this._subtotal = subtotal;
    }

    public Money getTotal() {
        return this._total;
    }

    public void setTotal(Money total) {
        this._total = total;
    }

    public String getDescription() {
        return this._description;
    }

    public void setDescription(String description) {
        this._description = description;
    }

    public String getAccount() {
        return this._account;
    }

    public void setAccount(String account) {
        this._account = account;
    }
}
