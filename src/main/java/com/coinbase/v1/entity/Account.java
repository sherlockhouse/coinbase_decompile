package com.coinbase.v1.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.io.Serializable;
import org.joda.money.Money;
import org.joda.time.DateTime;

public class Account implements Serializable {
    private static final long serialVersionUID = 8248810301818113960L;
    private Boolean _active;
    private Money _balance;
    private DateTime _createdAt;
    private String _id;
    private String _name;
    private Money _nativeBalance;
    private Boolean _primary;
    private Type _type;
    private String _uuid;

    public enum Type {
        WALLET("wallet"),
        VAULT("vault"),
        MULTISIG_VAULT("multisig_vault"),
        MULTISIG_WALLET("multisig_wallet"),
        FIAT("fiat");
        
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

    public String getUuid() {
        return this._uuid;
    }

    public void setUuid(String uuid) {
        this._uuid = uuid;
    }

    public String getId() {
        return this._id;
    }

    public void setId(String id) {
        this._id = id;
    }

    public String getName() {
        return this._name;
    }

    public void setName(String name) {
        this._name = name;
    }

    public Money getBalance() {
        return this._balance;
    }

    public void setBalance(Money balance) {
        this._balance = balance;
    }

    public Money getNativeBalance() {
        return this._nativeBalance;
    }

    public void setNativeBalance(Money nativeBalance) {
        this._nativeBalance = nativeBalance;
    }

    public DateTime getCreatedAt() {
        return this._createdAt;
    }

    public void setCreatedAt(DateTime createdAt) {
        this._createdAt = createdAt;
    }

    public Boolean isPrimary() {
        return this._primary;
    }

    public void setPrimary(boolean primary) {
        this._primary = Boolean.valueOf(primary);
    }

    public Boolean isActive() {
        return this._active;
    }

    public void setActive(boolean active) {
        this._active = Boolean.valueOf(active);
    }

    public Type getType() {
        return this._type;
    }

    public void setType(Type type) {
        this._type = type;
    }
}
