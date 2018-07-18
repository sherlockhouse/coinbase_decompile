package com.coinbase.v1.entity;

import com.coinbase.v1.deserializer.MoneyDeserializer;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.io.Serializable;
import java.util.List;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

public class User implements Serializable {
    private static final long serialVersionUID = 7772234749971786319L;
    private Boolean _accept_agreement;
    private List<String> _allowedPaymentMethods;
    private String _avatarUrl;
    private Money _balance;
    private String _bitcoin_units;
    private Integer _buyLevel;
    private Money _buyLimit;
    private String _countryCode;
    private String _dob;
    private String _email;
    private Boolean _email_verified;
    private List<String> _featureFlags;
    private String _firstName;
    private String _id;
    private String _lastName;
    private String _location;
    private Merchant _merchant;
    private String _name;
    private CurrencyUnit _nativeCurrency;
    private Boolean _needsToReacceptUserAgreement;
    private String _password;
    private String _profile_description;
    private String _receiveAddress;
    private String _referrerId;
    private Boolean _regionalAgreementRequired;
    private ResidentialAddress _residentialAddress;
    private Integer _sellLevel;
    private Money _sellLimit;
    private Boolean _stateUnsupported;
    private Boolean _stateVerificationRequired;
    private String _timeZone;
    private String _username;

    public enum Flag {
        INSTANT_EXCHANGE("instant_exchange"),
        NEW_VERIFICATIONS_PAGE("new_verifications_page");
        
        private String _value;

        private Flag(String value) {
            this._value = value;
        }

        @JsonValue
        public String toString() {
            return this._value;
        }

        @JsonCreator
        public static Flag create(String val) {
            for (Flag type : values()) {
                if (type.toString().equalsIgnoreCase(val)) {
                    return type;
                }
            }
            return null;
        }
    }

    public String getPassword() {
        return this._password;
    }

    public void setPassword(String password) {
        this._password = password;
    }

    public String getReferrerId() {
        return this._referrerId;
    }

    public void setReferrerId(String referrerId) {
        this._referrerId = referrerId;
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

    public String getEmail() {
        return this._email;
    }

    public void setEmail(String email) {
        this._email = email;
    }

    public Boolean getEmailVerified() {
        return this._email_verified;
    }

    public void setEmailVerified(Boolean email_verified) {
        this._email_verified = email_verified;
    }

    public String getTimeZone() {
        return this._timeZone;
    }

    public void setTimeZone(String timeZone) {
        this._timeZone = timeZone;
    }

    public CurrencyUnit getNativeCurrency() {
        return this._nativeCurrency;
    }

    public void setNativeCurrency(CurrencyUnit nativeCurrency) {
        this._nativeCurrency = nativeCurrency;
    }

    public Money getBalance() {
        return this._balance;
    }

    @JsonDeserialize(using = MoneyDeserializer.class)
    public void setBalance(Money balance) {
        this._balance = balance;
    }

    public Integer getBuyLevel() {
        return this._buyLevel;
    }

    public void setBuyLevel(Integer buyLevel) {
        this._buyLevel = buyLevel;
    }

    public Integer getSellLevel() {
        return this._sellLevel;
    }

    public void setSellLevel(Integer sellLevel) {
        this._sellLevel = sellLevel;
    }

    public Money getBuyLimit() {
        return this._buyLimit;
    }

    @JsonDeserialize(using = MoneyDeserializer.class)
    public void setBuyLimit(Money buyLimit) {
        this._buyLimit = buyLimit;
    }

    public Money getSellLimit() {
        return this._sellLimit;
    }

    @JsonDeserialize(using = MoneyDeserializer.class)
    public void setSellLimit(Money sellLimit) {
        this._sellLimit = sellLimit;
    }

    public String getReceiveAddress() {
        return this._receiveAddress;
    }

    public void setReceiveAddress(String receiveAddress) {
        this._receiveAddress = receiveAddress;
    }

    public Merchant getMerchant() {
        return this._merchant;
    }

    public void setMerchant(Merchant merchant) {
        this._merchant = merchant;
    }

    public void setAvatarUrl(String avatarUrl) {
        this._avatarUrl = avatarUrl;
    }

    public String getAvatarUrl() {
        return this._avatarUrl;
    }

    public void setUsername(String username) {
        this._username = username;
    }

    public String getUsername() {
        return this._username;
    }

    public String getBitcoinUnits() {
        return this._bitcoin_units;
    }

    public void setBitcoinUnits(String bitcoin_units) {
        this._bitcoin_units = bitcoin_units;
    }

    public String getLocation() {
        return this._location;
    }

    public void setLocation(String location) {
        this._location = location;
    }

    public String getProfileDescription() {
        return this._profile_description;
    }

    public void setProfileDescription(String profile_description) {
        this._profile_description = profile_description;
    }

    public Boolean getAcceptAgreement() {
        return this._accept_agreement;
    }

    public void setAcceptAgreement(Boolean accept_agreement) {
        this._accept_agreement = accept_agreement;
    }

    public String getCountryCode() {
        return this._countryCode;
    }

    public void setCountryCode(String countryCode) {
        this._countryCode = countryCode;
    }

    public ResidentialAddress getResidentialAddress() {
        return this._residentialAddress;
    }

    public void setResidentialAddress(ResidentialAddress residentialAddress) {
        this._residentialAddress = residentialAddress;
    }

    public String getDob() {
        return this._dob;
    }

    public void setDob(String dob) {
        this._dob = dob;
    }

    public String getLastName() {
        return this._lastName;
    }

    public void setLastName(String lastName) {
        this._lastName = lastName;
    }

    public String getFirstName() {
        return this._firstName;
    }

    public void setFirstName(String firstName) {
        this._firstName = firstName;
    }

    public List<String> getAllowedPaymentMethods() {
        return this._allowedPaymentMethods;
    }

    public void setAllowedPaymentMethods(List<String> allowedPaymentMethods) {
        this._allowedPaymentMethods = allowedPaymentMethods;
    }

    public List<String> getFeatureFlags() {
        return this._featureFlags;
    }

    public void setFeatureFlags(List<String> featureFlags) {
        this._featureFlags = featureFlags;
    }

    public Boolean getStateVerificationRequired() {
        return this._stateVerificationRequired;
    }

    public void setStateVerificationRequired(Boolean stateVerificationRequired) {
        this._stateVerificationRequired = stateVerificationRequired;
    }

    public Boolean getStateUnsupported() {
        return this._stateUnsupported;
    }

    public void setStateUnsupported(Boolean stateUnsupported) {
        this._stateUnsupported = stateUnsupported;
    }

    public Boolean getRegionalAgreementRequired() {
        return this._regionalAgreementRequired;
    }

    public void setRegionalAgreementRequired(Boolean regionalAgreementRequired) {
        this._regionalAgreementRequired = regionalAgreementRequired;
    }

    public Boolean getNeedsToReAcceptUserAgreement() {
        return this._needsToReacceptUserAgreement;
    }

    public void setNeedsToReacceptUserAgreement(Boolean needsToReacceptUserAgreement) {
        this._needsToReacceptUserAgreement = needsToReacceptUserAgreement;
    }
}
