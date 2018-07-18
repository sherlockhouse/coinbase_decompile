package com.coinbase.v1.entity;

import com.coinbase.api.internal.ApiConstants;
import com.coinbase.v1.deserializer.MoneyDeserializer;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.io.Serializable;
import java.math.BigDecimal;
import org.joda.money.Money;
import org.joda.time.DateTime;

public class Transaction implements Serializable {
    private static final long serialVersionUID = 2817857314208431664L;
    private Money _amount;
    private String _amountCurrencyIso;
    private String _amountString;
    private Integer _confirmations;
    private DateTime _createdAt;
    private DetailedStatus _detailedStatus;
    private String _from;
    private String _hsh;
    private String _id;
    private String _idem;
    private Boolean _instantBuy;
    private String _instantExchangeQuote;
    private String _notes;
    private String _orderId;
    private User _recipient;
    private Account _recipientAccount;
    private String _recipientAddress;
    private Boolean _request;
    private User _sender;
    private Account _senderAccount;
    private Status _status;
    private String _to;
    @JsonProperty("amount")
    private String _transferBitcoinAmountString;
    private String _userFee;

    public enum DetailedStatus {
        COMPLETED("completed"),
        FAILED("failed"),
        EXPIRED("expired"),
        WAITING_FOR_SIGNATURE("waiting_for_signature"),
        WAITING_FOR_CLEARING(ApiConstants.WAITING_FOR_CLEARING),
        CANCELED("canceled"),
        PENDING("pending");
        
        private String _value;

        private DetailedStatus(String value) {
            this._value = value;
        }

        @JsonValue
        public String toString() {
            return this._value;
        }

        @JsonCreator
        public static DetailedStatus create(String val) {
            for (DetailedStatus status : values()) {
                if (status.toString().equalsIgnoreCase(val)) {
                    return status;
                }
            }
            return null;
        }
    }

    public enum Status {
        PENDING("pending"),
        COMPLETE("complete");
        
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

    public Integer getConfirmations() {
        return this._confirmations;
    }

    public void setConfirmations(Integer confirmations) {
        this._confirmations = confirmations;
    }

    public String getTo() {
        return this._to;
    }

    public void setTo(String to) {
        this._to = to;
    }

    public String getFrom() {
        return this._from;
    }

    public void setFrom(String from) {
        this._from = from;
    }

    public String getAmountString() {
        return this._amountString;
    }

    public void setAmountString(String amountString) {
        this._amountString = amountString;
    }

    public String getAmountCurrencyIso() {
        return this._amountCurrencyIso;
    }

    public void setAmountCurrencyIso(String amountCurrencyIso) {
        this._amountCurrencyIso = amountCurrencyIso;
    }

    public String getInstantExchangeQuote() {
        return this._instantExchangeQuote;
    }

    public void setInstantExchangeQuote(String uuid) {
        this._instantExchangeQuote = uuid;
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

    public String getHsh() {
        return this._hsh;
    }

    public void setHsh(String hsh) {
        this._hsh = hsh;
    }

    public String getHash() {
        return this._hsh;
    }

    public void setHash(String hash) {
        this._hsh = hash;
    }

    public void setTransferBitcoinAmountString(String transferBitcoinAmountString) {
        this._transferBitcoinAmountString = transferBitcoinAmountString;
    }

    public String getTransferBitcoinAmountString() {
        return this._transferBitcoinAmountString;
    }

    public String getNotes() {
        return this._notes;
    }

    public void setNotes(String notes) {
        this._notes = notes;
    }

    @JsonIgnore
    public Money getAmount() {
        return this._amount;
    }

    @JsonProperty
    @JsonDeserialize(using = MoneyDeserializer.class)
    public void setAmount(Money amount) {
        this._amount = amount;
        if (amount != null) {
            setAmountString(amount.getAmount().toPlainString());
            setAmountCurrencyIso(amount.getCurrencyUnit().getCurrencyCode());
            return;
        }
        setAmountString(null);
        setAmountCurrencyIso(null);
    }

    public Boolean isRequest() {
        return this._request;
    }

    public void setRequest(Boolean request) {
        this._request = request;
    }

    public Status getStatus() {
        return this._status;
    }

    public void setStatus(Status status) {
        this._status = status;
    }

    public User getSender() {
        return this._sender;
    }

    public void setSender(User sender) {
        this._sender = sender;
    }

    public User getRecipient() {
        return this._recipient;
    }

    public void setRecipient(User recipient) {
        this._recipient = recipient;
    }

    public String getUserFee() {
        return this._userFee;
    }

    public void setUserFee(BigDecimal userFee) {
        this._userFee = userFee.toPlainString();
    }

    public String getRecipientAddress() {
        return this._recipientAddress;
    }

    public void setRecipientAddress(String recipientAddress) {
        this._recipientAddress = recipientAddress;
    }

    public String getIdem() {
        return this._idem;
    }

    public void setIdem(String idem) {
        this._idem = idem;
    }

    public Boolean getInstantBuy() {
        return this._instantBuy;
    }

    public void setInstantBuy(Boolean instantBuy) {
        this._instantBuy = instantBuy;
    }

    public String getOrderId() {
        return this._orderId;
    }

    public void setOrderId(String orderId) {
        this._orderId = orderId;
    }

    public Account getSenderAccount() {
        return this._senderAccount;
    }

    public void setSenderAccount(Account senderAccount) {
        this._senderAccount = senderAccount;
    }

    public Account getRecipientAccount() {
        return this._recipientAccount;
    }

    public void setRecipientAccount(Account recipientAccount) {
        this._recipientAccount = recipientAccount;
    }

    public DetailedStatus getDetailedStatus() {
        return this._detailedStatus;
    }

    public void setDetailedStatus(DetailedStatus detailedStatus) {
        this._detailedStatus = detailedStatus;
    }
}
