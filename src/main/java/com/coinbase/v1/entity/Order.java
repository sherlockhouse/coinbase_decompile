package com.coinbase.v1.entity;

import com.coinbase.android.splittesting.SplitTestConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.io.Serializable;
import org.joda.money.Money;
import org.joda.time.DateTime;

public class Order implements Serializable {
    private static final long serialVersionUID = -8221149393524031079L;
    private Button _button;
    private DateTime _createdAt;
    private String _custom;
    private String _id;
    private String _receiveAddress;
    private String _refundAddress;
    private Status _status;
    private Money _totalBtc;
    private Money _totalNative;
    private Transaction _transaction;

    public enum Status {
        COMPLETED("completed"),
        CANCELED("canceled"),
        EXPIRED("expired"),
        NEW(SplitTestConstants.LINK_BANK_ACCOUNT_CHANGES_ENABLED),
        MISPAID("mispaid"),
        REFUNDED("refunded");
        
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

    public Status getStatus() {
        return this._status;
    }

    public void setStatus(Status status) {
        this._status = status;
    }

    public Money getTotalBtc() {
        return this._totalBtc;
    }

    public void setTotalBtc(Money totalBtc) {
        this._totalBtc = totalBtc;
    }

    public Money getTotalNative() {
        return this._totalNative;
    }

    public void setTotalNative(Money totalNative) {
        this._totalNative = totalNative;
    }

    public String getCustom() {
        return this._custom;
    }

    public void setCustom(String custom) {
        this._custom = custom;
    }

    public String getReceiveAddress() {
        return this._receiveAddress;
    }

    public void setReceiveAddress(String receiveAddress) {
        this._receiveAddress = receiveAddress;
    }

    public Button getButton() {
        return this._button;
    }

    public void setButton(Button button) {
        this._button = button;
    }

    public String getRefundAddress() {
        return this._refundAddress;
    }

    public void setRefundAddress(String refundAddress) {
        this._refundAddress = refundAddress;
    }

    public Transaction getTransaction() {
        return this._transaction;
    }

    public void setTransaction(Transaction transaction) {
        this._transaction = transaction;
    }
}
