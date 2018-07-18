package com.coinbase.api.internal;

import com.coinbase.android.notifications.priceAlerts.LocalPriceAlert;
import com.coinbase.android.notifications.priceAlerts.LocalPriceAlerts;
import com.coinbase.android.wbl.AvailableBalance;
import com.coinbase.android.wbl.WithdrawalBasedLimitsApiError;
import com.coinbase.api.internal.models.Country;
import com.coinbase.api.internal.models.Pagination;
import com.coinbase.api.internal.models.achSetupSession.AchSetupSession;
import com.coinbase.api.internal.models.address.Address;
import com.coinbase.api.internal.models.agreement.Data;
import com.coinbase.api.internal.models.agreement.UserAgreement;
import com.coinbase.api.internal.models.alerts.Alerts;
import com.coinbase.api.internal.models.auth.Auth;
import com.coinbase.api.internal.models.auth.Error;
import com.coinbase.api.internal.models.availablePaymentMethods.AvailablePaymentMethod;
import com.coinbase.api.internal.models.availablePaymentMethods.AvailablePaymentMethods;
import com.coinbase.api.internal.models.availablePaymentMethods.BuyingPower;
import com.coinbase.api.internal.models.billingAddress.BillingAddress;
import com.coinbase.api.internal.models.billingAddress.BillingAddresses;
import com.coinbase.api.internal.models.config.Android;
import com.coinbase.api.internal.models.config.Config;
import com.coinbase.api.internal.models.config.Ios;
import com.coinbase.api.internal.models.config.Message;
import com.coinbase.api.internal.models.config.VersionRange;
import com.coinbase.api.internal.models.currency.Currencies;
import com.coinbase.api.internal.models.dashboard.Balance;
import com.coinbase.api.internal.models.dashboard.Dashboard;
import com.coinbase.api.internal.models.dashboard.RemainingVerification;
import com.coinbase.api.internal.models.emailPreferences.EmailPreferences;
import com.coinbase.api.internal.models.idology.Answer;
import com.coinbase.api.internal.models.idology.Idology;
import com.coinbase.api.internal.models.idology.IdologyList;
import com.coinbase.api.internal.models.idology.Job;
import com.coinbase.api.internal.models.idology.Question;
import com.coinbase.api.internal.models.idology.UserAnswers;
import com.coinbase.api.internal.models.instantExchangeQuote.Amount;
import com.coinbase.api.internal.models.instantExchangeQuote.InstantExchangeQuote;
import com.coinbase.api.internal.models.institutions.Credentials;
import com.coinbase.api.internal.models.institutions.Image;
import com.coinbase.api.internal.models.institutions.Institution;
import com.coinbase.api.internal.models.jumio.supportedDocuments.SupportedDocument;
import com.coinbase.api.internal.models.jumio.supportedDocuments.SupportedDocuments;
import com.coinbase.api.internal.models.jumio.supportedDocuments.SupportedIdType;
import com.coinbase.api.internal.models.paymentMethods.CardSetup;
import com.coinbase.api.internal.models.paymentMethods.Mapping;
import com.coinbase.api.internal.models.paymentMethods.verify.Verify;
import com.coinbase.api.internal.models.phoneNumber.PhoneNumber;
import com.coinbase.api.internal.models.policyRestrictions.PolicyRestrictions;
import com.coinbase.api.internal.models.policyRestrictions.Restrictions;
import com.coinbase.api.internal.models.policyRestrictions.Url;
import com.coinbase.api.internal.models.priceCharts.Price;
import com.coinbase.api.internal.models.priceCharts.PriceChart;
import com.coinbase.api.internal.models.tiers.AccountDetails;
import com.coinbase.api.internal.models.tiers.AccountSetup;
import com.coinbase.api.internal.models.tiers.BuySellLimit;
import com.coinbase.api.internal.models.tiers.Currency;
import com.coinbase.api.internal.models.tiers.Level;
import com.coinbase.api.internal.models.tiers.LevelFeature;
import com.coinbase.api.internal.models.tiers.LifetimeLimit;
import com.coinbase.api.internal.models.tiers.LimitsAndFeatures;
import com.coinbase.api.internal.models.tiers.Requirement;
import com.coinbase.api.internal.models.tiers.Status;
import com.coinbase.api.internal.models.tiers.Tiers;
import com.coinbase.api.internal.models.tiers.VerificationLevels;
import com.coinbase.api.internal.models.tiers.Warning;
import com.coinbase.api.internal.models.verifications.allowedPaymentMethods.AllowedPaymentMethods;
import com.coinbase.api.internal.models.wbl.AccountBalance;
import com.coinbase.api.internal.models.wbl.PendingHold;
import com.coinbase.api.internal.models.wbl.PendingHolds;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

public final class AutoValueGson_ModelGsonAdapterFactory extends ModelGsonAdapterFactory {
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        Class<T> rawType = type.getRawType();
        if (AvailableBalance.class.isAssignableFrom(rawType)) {
            return AvailableBalance.typeAdapter(gson);
        }
        if (WithdrawalBasedLimitsApiError.class.isAssignableFrom(rawType)) {
            return WithdrawalBasedLimitsApiError.typeAdapter(gson);
        }
        if (LocalPriceAlerts.class.isAssignableFrom(rawType)) {
            return LocalPriceAlerts.typeAdapter(gson);
        }
        if (LocalPriceAlert.class.isAssignableFrom(rawType)) {
            return LocalPriceAlert.typeAdapter(gson);
        }
        if (UserAgreement.class.isAssignableFrom(rawType)) {
            return UserAgreement.typeAdapter(gson);
        }
        if (Data.class.isAssignableFrom(rawType)) {
            return Data.typeAdapter(gson);
        }
        if (PriceChart.class.isAssignableFrom(rawType)) {
            return PriceChart.typeAdapter(gson);
        }
        if (com.coinbase.api.internal.models.priceCharts.Data.class.isAssignableFrom(rawType)) {
            return com.coinbase.api.internal.models.priceCharts.Data.typeAdapter(gson);
        }
        if (Price.class.isAssignableFrom(rawType)) {
            return Price.typeAdapter(gson);
        }
        if (Country.class.isAssignableFrom(rawType)) {
            return Country.typeAdapter(gson);
        }
        if (InstantExchangeQuote.class.isAssignableFrom(rawType)) {
            return InstantExchangeQuote.typeAdapter(gson);
        }
        if (com.coinbase.api.internal.models.instantExchangeQuote.Data.class.isAssignableFrom(rawType)) {
            return com.coinbase.api.internal.models.instantExchangeQuote.Data.typeAdapter(gson);
        }
        if (Amount.class.isAssignableFrom(rawType)) {
            return Amount.typeAdapter(gson);
        }
        if (Restrictions.class.isAssignableFrom(rawType)) {
            return Restrictions.typeAdapter(gson);
        }
        if (com.coinbase.api.internal.models.policyRestrictions.Data.class.isAssignableFrom(rawType)) {
            return com.coinbase.api.internal.models.policyRestrictions.Data.typeAdapter(gson);
        }
        if (Url.class.isAssignableFrom(rawType)) {
            return Url.typeAdapter(gson);
        }
        if (PolicyRestrictions.class.isAssignableFrom(rawType)) {
            return PolicyRestrictions.typeAdapter(gson);
        }
        if (Credentials.class.isAssignableFrom(rawType)) {
            return Credentials.typeAdapter(gson);
        }
        if (com.coinbase.api.internal.models.institutions.Data.class.isAssignableFrom(rawType)) {
            return com.coinbase.api.internal.models.institutions.Data.typeAdapter(gson);
        }
        if (Institution.class.isAssignableFrom(rawType)) {
            return Institution.typeAdapter(gson);
        }
        if (Image.class.isAssignableFrom(rawType)) {
            return Image.typeAdapter(gson);
        }
        if (Dashboard.class.isAssignableFrom(rawType)) {
            return Dashboard.typeAdapter(gson);
        }
        if (com.coinbase.api.internal.models.dashboard.Data.class.isAssignableFrom(rawType)) {
            return com.coinbase.api.internal.models.dashboard.Data.typeAdapter(gson);
        }
        if (Balance.class.isAssignableFrom(rawType)) {
            return Balance.typeAdapter(gson);
        }
        if (com.coinbase.api.internal.models.dashboard.Amount.class.isAssignableFrom(rawType)) {
            return com.coinbase.api.internal.models.dashboard.Amount.typeAdapter(gson);
        }
        if (RemainingVerification.class.isAssignableFrom(rawType)) {
            return RemainingVerification.typeAdapter(gson);
        }
        if (Config.class.isAssignableFrom(rawType)) {
            return Config.typeAdapter(gson);
        }
        if (Ios.class.isAssignableFrom(rawType)) {
            return Ios.typeAdapter(gson);
        }
        if (com.coinbase.api.internal.models.config.Data.class.isAssignableFrom(rawType)) {
            return com.coinbase.api.internal.models.config.Data.typeAdapter(gson);
        }
        if (VersionRange.class.isAssignableFrom(rawType)) {
            return VersionRange.typeAdapter(gson);
        }
        if (Message.class.isAssignableFrom(rawType)) {
            return Message.typeAdapter(gson);
        }
        if (Android.class.isAssignableFrom(rawType)) {
            return Android.typeAdapter(gson);
        }
        if (EmailPreferences.class.isAssignableFrom(rawType)) {
            return EmailPreferences.typeAdapter(gson);
        }
        if (Pagination.class.isAssignableFrom(rawType)) {
            return Pagination.typeAdapter(gson);
        }
        if (com.coinbase.api.internal.models.alerts.Data.class.isAssignableFrom(rawType)) {
            return com.coinbase.api.internal.models.alerts.Data.typeAdapter(gson);
        }
        if (Alerts.class.isAssignableFrom(rawType)) {
            return Alerts.typeAdapter(gson);
        }
        if (SupportedIdType.class.isAssignableFrom(rawType)) {
            return SupportedIdType.typeAdapter(gson);
        }
        if (SupportedDocument.class.isAssignableFrom(rawType)) {
            return SupportedDocument.typeAdapter(gson);
        }
        if (com.coinbase.api.internal.models.jumio.supportedDocuments.Data.class.isAssignableFrom(rawType)) {
            return com.coinbase.api.internal.models.jumio.supportedDocuments.Data.typeAdapter(gson);
        }
        if (SupportedDocuments.class.isAssignableFrom(rawType)) {
            return SupportedDocuments.typeAdapter(gson);
        }
        if (com.coinbase.api.internal.models.currency.Data.class.isAssignableFrom(rawType)) {
            return com.coinbase.api.internal.models.currency.Data.typeAdapter(gson);
        }
        if (Currencies.class.isAssignableFrom(rawType)) {
            return Currencies.typeAdapter(gson);
        }
        if (com.coinbase.api.internal.models.currency.Image.class.isAssignableFrom(rawType)) {
            return com.coinbase.api.internal.models.currency.Image.typeAdapter(gson);
        }
        if (CardSetup.class.isAssignableFrom(rawType)) {
            return CardSetup.typeAdapter(gson);
        }
        if (Mapping.class.isAssignableFrom(rawType)) {
            return Mapping.typeAdapter(gson);
        }
        if (com.coinbase.api.internal.models.paymentMethods.Data.class.isAssignableFrom(rawType)) {
            return com.coinbase.api.internal.models.paymentMethods.Data.typeAdapter(gson);
        }
        if (Verify.class.isAssignableFrom(rawType)) {
            return Verify.typeAdapter(gson);
        }
        if (com.coinbase.api.internal.models.paymentMethods.verify.Data.class.isAssignableFrom(rawType)) {
            return com.coinbase.api.internal.models.paymentMethods.verify.Data.typeAdapter(gson);
        }
        if (Warning.class.isAssignableFrom(rawType)) {
            return Warning.typeAdapter(gson);
        }
        if (AccountSetup.class.isAssignableFrom(rawType)) {
            return AccountSetup.typeAdapter(gson);
        }
        if (LevelFeature.class.isAssignableFrom(rawType)) {
            return LevelFeature.typeAdapter(gson);
        }
        if (VerificationLevels.class.isAssignableFrom(rawType)) {
            return VerificationLevels.typeAdapter(gson);
        }
        if (Tiers.class.isAssignableFrom(rawType)) {
            return Tiers.typeAdapter(gson);
        }
        if (LimitsAndFeatures.class.isAssignableFrom(rawType)) {
            return LimitsAndFeatures.typeAdapter(gson);
        }
        if (Requirement.class.isAssignableFrom(rawType)) {
            return Requirement.typeAdapter(gson);
        }
        if (Currency.class.isAssignableFrom(rawType)) {
            return Currency.typeAdapter(gson);
        }
        if (com.coinbase.api.internal.models.tiers.Data.class.isAssignableFrom(rawType)) {
            return com.coinbase.api.internal.models.tiers.Data.typeAdapter(gson);
        }
        if (Level.class.isAssignableFrom(rawType)) {
            return Level.typeAdapter(gson);
        }
        if (AccountDetails.class.isAssignableFrom(rawType)) {
            return AccountDetails.typeAdapter(gson);
        }
        if (LifetimeLimit.class.isAssignableFrom(rawType)) {
            return LifetimeLimit.typeAdapter(gson);
        }
        if (BuySellLimit.class.isAssignableFrom(rawType)) {
            return BuySellLimit.typeAdapter(gson);
        }
        if (Status.class.isAssignableFrom(rawType)) {
            return Status.typeAdapter(gson);
        }
        if (com.coinbase.api.internal.models.address.Country.class.isAssignableFrom(rawType)) {
            return com.coinbase.api.internal.models.address.Country.typeAdapter(gson);
        }
        if (Address.class.isAssignableFrom(rawType)) {
            return Address.typeAdapter(gson);
        }
        if (com.coinbase.api.internal.models.address.Data.class.isAssignableFrom(rawType)) {
            return com.coinbase.api.internal.models.address.Data.typeAdapter(gson);
        }
        if (PendingHolds.class.isAssignableFrom(rawType)) {
            return PendingHolds.typeAdapter(gson);
        }
        if (com.coinbase.api.internal.models.wbl.Data.class.isAssignableFrom(rawType)) {
            return com.coinbase.api.internal.models.wbl.Data.typeAdapter(gson);
        }
        if (PendingHold.class.isAssignableFrom(rawType)) {
            return PendingHold.typeAdapter(gson);
        }
        if (com.coinbase.api.internal.models.wbl.Amount.class.isAssignableFrom(rawType)) {
            return com.coinbase.api.internal.models.wbl.Amount.typeAdapter(gson);
        }
        if (AccountBalance.class.isAssignableFrom(rawType)) {
            return AccountBalance.typeAdapter(gson);
        }
        if (Auth.class.isAssignableFrom(rawType)) {
            return Auth.typeAdapter(gson);
        }
        if (Error.class.isAssignableFrom(rawType)) {
            return Error.typeAdapter(gson);
        }
        if (BillingAddresses.class.isAssignableFrom(rawType)) {
            return BillingAddresses.typeAdapter(gson);
        }
        if (com.coinbase.api.internal.models.billingAddress.Data.class.isAssignableFrom(rawType)) {
            return com.coinbase.api.internal.models.billingAddress.Data.typeAdapter(gson);
        }
        if (BillingAddress.class.isAssignableFrom(rawType)) {
            return BillingAddress.typeAdapter(gson);
        }
        if (com.coinbase.api.internal.models.idology.Country.class.isAssignableFrom(rawType)) {
            return com.coinbase.api.internal.models.idology.Country.typeAdapter(gson);
        }
        if (com.coinbase.api.internal.models.idology.Address.class.isAssignableFrom(rawType)) {
            return com.coinbase.api.internal.models.idology.Address.typeAdapter(gson);
        }
        if (UserAnswers.class.isAssignableFrom(rawType)) {
            return UserAnswers.typeAdapter(gson);
        }
        if (com.coinbase.api.internal.models.idology.Pagination.class.isAssignableFrom(rawType)) {
            return com.coinbase.api.internal.models.idology.Pagination.typeAdapter(gson);
        }
        if (com.coinbase.api.internal.models.idology.Data.class.isAssignableFrom(rawType)) {
            return com.coinbase.api.internal.models.idology.Data.typeAdapter(gson);
        }
        if (Question.class.isAssignableFrom(rawType)) {
            return Question.typeAdapter(gson);
        }
        if (Answer.class.isAssignableFrom(rawType)) {
            return Answer.typeAdapter(gson);
        }
        if (IdologyList.class.isAssignableFrom(rawType)) {
            return IdologyList.typeAdapter(gson);
        }
        if (Job.class.isAssignableFrom(rawType)) {
            return Job.typeAdapter(gson);
        }
        if (Idology.class.isAssignableFrom(rawType)) {
            return Idology.typeAdapter(gson);
        }
        if (BuyingPower.class.isAssignableFrom(rawType)) {
            return BuyingPower.typeAdapter(gson);
        }
        if (com.coinbase.api.internal.models.availablePaymentMethods.Currency.class.isAssignableFrom(rawType)) {
            return com.coinbase.api.internal.models.availablePaymentMethods.Currency.typeAdapter(gson);
        }
        if (com.coinbase.api.internal.models.availablePaymentMethods.Data.class.isAssignableFrom(rawType)) {
            return com.coinbase.api.internal.models.availablePaymentMethods.Data.typeAdapter(gson);
        }
        if (AvailablePaymentMethods.class.isAssignableFrom(rawType)) {
            return AvailablePaymentMethods.typeAdapter(gson);
        }
        if (AvailablePaymentMethod.class.isAssignableFrom(rawType)) {
            return AvailablePaymentMethod.typeAdapter(gson);
        }
        if (com.coinbase.api.internal.models.achSetupSession.Data.class.isAssignableFrom(rawType)) {
            return com.coinbase.api.internal.models.achSetupSession.Data.typeAdapter(gson);
        }
        if (AchSetupSession.class.isAssignableFrom(rawType)) {
            return AchSetupSession.typeAdapter(gson);
        }
        if (PhoneNumber.class.isAssignableFrom(rawType)) {
            return PhoneNumber.typeAdapter(gson);
        }
        if (com.coinbase.api.internal.models.phoneNumber.Data.class.isAssignableFrom(rawType)) {
            return com.coinbase.api.internal.models.phoneNumber.Data.typeAdapter(gson);
        }
        if (com.coinbase.api.internal.models.verifications.allowedPaymentMethods.Data.class.isAssignableFrom(rawType)) {
            return com.coinbase.api.internal.models.verifications.allowedPaymentMethods.Data.typeAdapter(gson);
        }
        if (AllowedPaymentMethods.class.isAssignableFrom(rawType)) {
            return AllowedPaymentMethods.typeAdapter(gson);
        }
        return null;
    }
}
