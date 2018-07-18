package com.coinbase.v1.entity;

import com.coinbase.api.internal.ApiConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.io.Serializable;

public class PaymentMethod implements Serializable {
    private static final long serialVersionUID = -3574818318535801143L;
    private Account _account;
    private Boolean _allowBuy;
    private Boolean _allowDeposit;
    private Boolean _allowSell;
    private Boolean _allowWithdraw;
    private String _bankName;
    private CDVStatus _cdvStatus;
    private String _currency;
    private Account _fiatAccount;
    private IAVField[] _iavFields;
    private IAVStatus _iavStatus;
    private String _iban;
    private String _id;
    private String _name;
    private Boolean _primaryBuy;
    private Boolean _primarySell;
    private String _swift;
    private Type _type;
    private String _uuid;
    private VerificationMethod _verificationMethod;
    private Boolean _verified;

    public enum CDVStatus {
        READY("ready"),
        IN_PROGRESS("in_progress");
        
        private String _value;

        private CDVStatus(String value) {
            this._value = value;
        }

        @JsonValue
        public String toString() {
            return this._value;
        }

        @JsonCreator
        public static Type create(String val) {
            for (Type type : Type.values()) {
                if (type.toString().equalsIgnoreCase(val)) {
                    return type;
                }
            }
            return null;
        }
    }

    public enum IAVStatus {
        READY("ready"),
        UNAVAILABLE("unavailable"),
        MFA_REQUIRED("mfa_required"),
        IN_PROGRESS("in_progress"),
        FAILED("failed");
        
        private String _value;

        private IAVStatus(String value) {
            this._value = value;
        }

        @JsonValue
        public String toString() {
            return this._value;
        }

        @JsonCreator
        public static Type create(String val) {
            for (Type type : Type.values()) {
                if (type.toString().equalsIgnoreCase(val)) {
                    return type;
                }
            }
            return null;
        }
    }

    public enum Type {
        ACH_BANK_ACCOUNT(ApiConstants.ACH_BANK_ACCOUNT),
        CREDIT_CARD("credit_card"),
        DEBIT_CARD(ApiConstants.DEBIT_CARD),
        SEPA_BANK_ACCOUNT("sepa_bank_account"),
        FIAT_ACCOUNT("fiat_account"),
        BANK_WIRE("bank_wire"),
        BANK_ACCOUNT("bank_account"),
        COINBASE_ACCOUNT("coinbase_account"),
        COINBASE_FIAT_ACCOUNT("coinbase_fiat_account"),
        FUTURE_MERCHANT_PAYOUT("future_merchant_payout"),
        SEPA_PAYMENT_METHOD("sepa_payment_method"),
        PAYPAL_ACCOUNT("paypal_account");
        
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

    public enum VerificationMethod {
        CDV(ApiConstants.CDV),
        IAV("iav"),
        ACH_SETUP_SESSION("ach_setup_session");
        
        private String _value;

        private VerificationMethod(String value) {
            this._value = value;
        }

        @JsonValue
        public String toString() {
            return this._value;
        }

        @JsonCreator
        public static Type create(String val) {
            for (Type type : Type.values()) {
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

    public Boolean allowBuy() {
        return this._allowBuy;
    }

    public void setAllowBuy(Boolean allowBuy) {
        this._allowBuy = allowBuy;
    }

    public Boolean allowSell() {
        return this._allowSell;
    }

    public void setAllowSell(Boolean allowSell) {
        this._allowSell = allowSell;
    }

    public Boolean allowDeposit() {
        return this._allowDeposit;
    }

    public void setAllowDeposit(Boolean allowDeposit) {
        this._allowDeposit = allowDeposit;
    }

    public Boolean allowWithdraw() {
        return this._allowWithdraw;
    }

    public void setAllowWithdraw(Boolean allowWithdraw) {
        this._allowWithdraw = allowWithdraw;
    }

    public Account getAccount() {
        return this._account;
    }

    public void setAccount(Account account) {
        this._account = account;
    }

    public Account getFiatAccount() {
        return this._fiatAccount;
    }

    public void setFiatAccount(Account account) {
        this._fiatAccount = account;
    }

    public String getCurrency() {
        return this._currency;
    }

    public void setCurrency(String currency) {
        this._currency = currency;
    }

    public Type getType() {
        return this._type;
    }

    public void setType(Type type) {
        this._type = type;
    }

    public Boolean getVerified() {
        return this._verified;
    }

    public void setVerified(Boolean verified) {
        this._verified = verified;
    }

    public String getBankName() {
        return this._bankName;
    }

    public void setBankName(String bankName) {
        this._bankName = bankName;
    }

    public String getIban() {
        return this._iban;
    }

    public void setIban(String iban) {
        this._iban = iban;
    }

    public String getSwift() {
        return this._swift;
    }

    public void setSwift(String swift) {
        this._swift = swift;
    }

    public Boolean getPrimaryBuy() {
        return this._primaryBuy;
    }

    public void setPrimaryBuy(Boolean primaryBuy) {
        this._primaryBuy = primaryBuy;
    }

    public Boolean getPrimarySell() {
        return this._primarySell;
    }

    public void setPrimarySell(Boolean primarySell) {
        this._primarySell = primarySell;
    }

    public VerificationMethod getVerificationMethod() {
        return this._verificationMethod;
    }

    public void setVerificationMethod(VerificationMethod verificationMethod) {
        this._verificationMethod = verificationMethod;
    }

    public IAVStatus getIavStatus() {
        return this._iavStatus;
    }

    public void setIavStatus(IAVStatus iavStatus) {
        this._iavStatus = iavStatus;
    }

    public CDVStatus getCdvStatus() {
        return this._cdvStatus;
    }

    public void setCdvStatus(CDVStatus cdvStatus) {
        this._cdvStatus = cdvStatus;
    }

    public IAVField[] getIavFields() {
        return this._iavFields;
    }

    public void setIavFields(IAVField[] iavFields) {
        this._iavFields = iavFields;
    }
}
