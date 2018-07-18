package com.coinbase.v1.entity;

import com.coinbase.api.internal.ApiConstants;
import com.coinbase.v1.deserializer.MoneyDeserializer;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.io.Serializable;
import org.joda.money.Money;
import org.joda.time.DateTime;

public class AccountChange implements Serializable {
    private static final long serialVersionUID = 8367935513400871799L;
    private Money _amount;
    private Cache _cache;
    private Boolean _confirmed;
    private DateTime _createdAt;
    private String _id;
    private String _transaction_id;

    public static class Cache implements Serializable {
        private static final long serialVersionUID = 4007178459010851945L;
        private Category _category;
        private Boolean _fiat;
        private Boolean _notes_present;
        private Account _other_account;
        private User _other_user;
        private PaymentMethod _payment_method;
        private String _status;

        public enum Category {
            TRANSFER("transfer"),
            TRANSACTION("tx"),
            REQUEST("request"),
            INVOICE("invoice"),
            ORDER("order"),
            DEPOSIT_WITHDRAWAL("deposit_withdraw"),
            TRANSFER_MONEY("transfer_money"),
            PAYMENT_REQUEST("payment_request"),
            ORDER_REFUND("order_refund"),
            CANCELED("canceled"),
            MISPAYMENT_REFUND("mispayment_refund"),
            VAULT_WITHDRAWAL(ApiConstants.VAULT_WITHDRAWAL);
            
            private String _value;

            private Category(String value) {
                this._value = value;
            }

            @JsonValue
            public String toString() {
                return this._value;
            }

            @JsonCreator
            public static Category create(String val) {
                for (Category category : values()) {
                    if (category.toString().equalsIgnoreCase(val)) {
                        return category;
                    }
                }
                return null;
            }
        }

        public Category getCategory() {
            return this._category;
        }

        public void setCategory(Category category) {
            this._category = category;
        }

        public Boolean isFiat() {
            return this._fiat;
        }

        public void setFiat(Boolean fiat) {
            this._fiat = fiat;
        }

        public Boolean isNotesPresent() {
            return this._notes_present;
        }

        public void setNotesPresent(Boolean notes_present) {
            this._notes_present = notes_present;
        }

        public User getOtherUser() {
            return this._other_user;
        }

        public void setOtherUser(User other_user) {
            this._other_user = other_user;
        }

        public PaymentMethod getPaymentMethod() {
            return this._payment_method;
        }

        public void setPaymentMethod(PaymentMethod payment_method) {
            this._payment_method = payment_method;
        }

        public String getStatus() {
            return this._status;
        }

        public void setStatus(String status) {
            this._status = status;
        }

        public Account getOtherAccount() {
            return this._other_account;
        }

        public void setOtherAccount(Account other_account) {
            this._other_account = other_account;
        }
    }

    public Cache getCache() {
        return this._cache;
    }

    public void setCache(Cache cache) {
        this._cache = cache;
    }

    public Boolean isConfirmed() {
        return this._confirmed;
    }

    public void setConfirmed(Boolean confirmed) {
        this._confirmed = confirmed;
    }

    public String getId() {
        return this._id;
    }

    public void setId(String id) {
        this._id = id;
    }

    public DateTime getCreatedAt() {
        return this._createdAt;
    }

    public void setCreatedAt(DateTime createdAt) {
        this._createdAt = createdAt;
    }

    public Money getAmount() {
        return this._amount;
    }

    @JsonDeserialize(using = MoneyDeserializer.class)
    public void setAmount(Money amount) {
        this._amount = amount;
    }

    public String getTransactionId() {
        return this._transaction_id;
    }

    public void setTransactionId(String transaction_id) {
        this._transaction_id = transaction_id;
    }
}
