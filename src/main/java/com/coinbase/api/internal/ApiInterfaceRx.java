package com.coinbase.api.internal;

import com.coinbase.api.internal.models.achSetupSession.AchSetupSession;
import com.coinbase.api.internal.models.address.Address;
import com.coinbase.api.internal.models.agreement.UserAgreement;
import com.coinbase.api.internal.models.alerts.Alerts;
import com.coinbase.api.internal.models.auth.Auth;
import com.coinbase.api.internal.models.availablePaymentMethods.AvailablePaymentMethods;
import com.coinbase.api.internal.models.billingAddress.BillingAddress;
import com.coinbase.api.internal.models.billingAddress.BillingAddresses;
import com.coinbase.api.internal.models.config.Config;
import com.coinbase.api.internal.models.contacts.Contacts;
import com.coinbase.api.internal.models.currency.Currencies;
import com.coinbase.api.internal.models.dashboard.Dashboard;
import com.coinbase.api.internal.models.emailPreferences.EmailPreferences;
import com.coinbase.api.internal.models.idology.Idology;
import com.coinbase.api.internal.models.idology.IdologyList;
import com.coinbase.api.internal.models.idology.UserAnswers;
import com.coinbase.api.internal.models.idology.options.IdologyOptions;
import com.coinbase.api.internal.models.instantExchangeQuote.InstantExchangeQuote;
import com.coinbase.api.internal.models.institutions.Institution;
import com.coinbase.api.internal.models.jumio.JumioProfile;
import com.coinbase.api.internal.models.jumio.JumioProfiles;
import com.coinbase.api.internal.models.jumio.supportedDocuments.SupportedDocument;
import com.coinbase.api.internal.models.jumio.supportedDocuments.SupportedDocuments;
import com.coinbase.api.internal.models.paymentMethods.CardSetup;
import com.coinbase.api.internal.models.paymentMethods.verify.Verify;
import com.coinbase.api.internal.models.phoneNumber.PhoneNumber;
import com.coinbase.api.internal.models.phoneNumber.PhoneNumbers;
import com.coinbase.api.internal.models.policyRestrictions.Restrictions;
import com.coinbase.api.internal.models.priceCharts.PriceChart;
import com.coinbase.api.internal.models.privacy.UserPrivacyPolicy;
import com.coinbase.api.internal.models.sepaDepositInfo.SepaDepositInfo;
import com.coinbase.api.internal.models.status.Status;
import com.coinbase.api.internal.models.tiers.Tiers;
import com.coinbase.api.internal.models.transaction.TransactionFee;
import com.coinbase.api.internal.models.wbl.PendingHolds;
import com.coinbase.v2.models.paymentMethods.PaymentMethod;
import com.coinbase.v2.models.transactions.Transactions;
import com.coinbase.v2.models.transfers.Transfer;
import com.coinbase.v2.models.user.User;
import java.util.HashMap;
import okhttp3.RequestBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

public interface ApiInterfaceRx {
    @POST("user/agreement")
    Observable<Response<Void>> acceptUserAgreement();

    @POST("accounts/{account_id}/buys/{buy_id}/complete")
    Observable<Response<Transfer>> completeBuy(@Path("account_id") String str, @Path("buy_id") String str2, @Body HashMap<String, Object> hashMap);

    @POST("payment-methods/{id}/complete-ach-cdv")
    Observable<Response<PaymentMethod>> completeCDVVerification(@Path("id") String str, @Body HashMap<String, Object> hashMap);

    @POST("ach-setup-sessions")
    Observable<Response<AchSetupSession>> createAchSetupSession(@Body HashMap<String, Object> hashMap);

    @PUT("user/address")
    Observable<Response<Address>> createAddress(@Body HashMap<String, Object> hashMap);

    @POST("payment-methods")
    Observable<Response<PaymentMethod>> createBankManually(@Body HashMap<String, Object> hashMap);

    @POST("payment-methods")
    Observable<Response<PaymentMethod>> createBankWithAchSetupSession(@Body HashMap<String, Object> hashMap);

    @POST("billing-addresses")
    Observable<Response<BillingAddress>> createBillingAddress(@Body HashMap<String, Object> hashMap);

    @POST("mobile/device-tokens")
    Observable<Response<Void>> createDeviceToken(@Body HashMap<String, Object> hashMap);

    @POST("identity-verifications")
    Observable<Response<Idology>> createIdentityVerification(@Body HashMap<String, Object> hashMap);

    @POST("jumio-profiles")
    @Multipart
    Observable<Response<JumioProfile>> createJumioProfile(@Part("country_code") RequestBody requestBody, @Part("id_type") RequestBody requestBody2, @Part("front_image\"; filename=\"image.png\"") RequestBody requestBody3, @Part("back_image\"; filename=\"image.png\"") RequestBody requestBody4, @Part("face_image\"; filename=\"image.png\"") RequestBody requestBody5);

    @POST("phone-numbers")
    Observable<Response<PhoneNumber>> createPhoneNumber(@Body HashMap<String, Object> hashMap);

    @POST("payment-methods")
    Observable<Response<Void>> createPlaidPaymentMethod(@Body HashMap<String, Object> hashMap);

    @POST("mobile/price-alerts")
    Observable<Response<Void>> createPriceAlert(@Body HashMap<String, Object> hashMap);

    @POST("mobile/users")
    Observable<Response<User>> createUser(@Body HashMap<String, Object> hashMap);

    @DELETE("ach-setup-sessions/{id}")
    Observable<Response<Void>> deleteAchSetupSession(@Path("id") String str);

    @DELETE("billing-addresses/{id}")
    Observable<Response<Void>> deleteBillingAddress(@Path("id") String str);

    @DELETE("payment-methods/{id}")
    Observable<Response<Void>> deletePaymentMethod(@Path("id") String str);

    @DELETE("phone-numbers/{id}")
    Observable<Response<PhoneNumber>> deletePhoneNumber(@Path("id") String str);

    @DELETE("mobile/price-alerts/{id}")
    Observable<Response<Void>> deletePriceAlert(@Path("id") String str, @QueryMap HashMap<String, Object> hashMap);

    @GET("payment-methods/ach-institutions")
    Observable<Response<Institution>> getAchInstitutions();

    @GET("alerts")
    Observable<Response<Alerts>> getAlerts();

    @POST("oauth/authorize/with-credentials")
    Observable<Response<Auth>> getAuthCode(@Body HashMap<String, Object> hashMap);

    @GET("user/available-payment-methods/{type}")
    Observable<Response<AvailablePaymentMethods>> getAvailablePaymentMethods(@Path("type") String str);

    @GET("billing-addresses/{id}")
    Observable<Response<BillingAddress>> getBillingAddress(@Path("id") String str);

    @GET("billing-addresses")
    Observable<Response<BillingAddresses>> getBillingAddresses();

    @GET("api/v2/components.json")
    Observable<Response<Status>> getCoinbaseStatus();

    @GET("identity-verifications/coinbase-uses")
    Observable<Response<IdologyOptions>> getCoinbaseUses();

    @GET("accounts/{account_id}/buys/{buy_id}/status")
    Observable<Response<Transfer>> getCommitBuyStatus(@Path("account_id") String str, @Path("buy_id") String str2);

    @GET("mobile/config")
    Observable<Response<Config>> getConfig();

    @GET("contacts")
    Observable<Response<Contacts>> getContacts(@QueryMap HashMap<String, Object> hashMap);

    @GET("currencies/crypto")
    Observable<Response<Currencies>> getCryptoCurrencies();

    @GET("app/dashboard")
    Observable<Response<Dashboard>> getDashboard();

    @GET("user/email-preferences")
    Observable<Response<EmailPreferences>> getEmailPreferences();

    @GET("hold-balances")
    Observable<Response<PendingHolds>> getHoldBalances();

    @GET("identity-verifications")
    Observable<Response<IdologyList>> getIdentityVerifications();

    @POST("accounts/{id}/instant-exchange-quotes")
    Observable<Response<InstantExchangeQuote>> getInstantExchangeQuote(@Path("id") String str, @Body HashMap<String, Object> hashMap);

    @GET("identity-verifications/job-titles")
    Observable<Response<IdologyOptions>> getJobTitles();

    @GET("jumio-profiles")
    Observable<Response<JumioProfiles>> getJumioProfiles();

    @GET("jumio-profiles/supported-documents/{country_code}")
    Observable<Response<SupportedDocument>> getJumioSupportedDocument(@Path("country_code") String str);

    @GET("jumio-profiles/supported-documents")
    Observable<Response<SupportedDocuments>> getJumioSupportedDocuments();

    @GET
    Observable<Response<Transactions>> getNextTransactions(@Url String str);

    @GET("payment-methods/{id}/verified")
    Observable<Response<PaymentMethod>> getPaymentMethodVerified(@Path("id") String str);

    @GET("phone-numbers")
    Observable<Response<PhoneNumbers>> getPhoneNumbers();

    @GET("policy-restrictions/{type}")
    Observable<Response<Restrictions>> getPolicyRestrictions(@Path("type") String str);

    @GET("prices/{base_currency}-{fiat_currency}/historic")
    Observable<Response<PriceChart>> getPriceChart(@Path("base_currency") String str, @Path("fiat_currency") String str2, @QueryMap HashMap<String, Object> hashMap);

    @GET("payment-methods/sepa-deposit-information")
    Observable<Response<SepaDepositInfo>> getSepaInformation();

    @GET("identity-verifications/source-of-funds")
    Observable<Response<IdologyOptions>> getSourceOfFunds();

    @GET("tiers")
    Observable<Response<Tiers>> getTiers();

    @GET("accounts/{account_id}/transactions/fee-estimate")
    Observable<Response<TransactionFee>> getTransactionFee(@Path("account_id") String str, @QueryMap HashMap<String, Object> hashMap);

    @GET("user/agreement")
    Observable<Response<UserAgreement>> getUserAgreement();

    @GET("user/privacy-policy")
    Observable<Response<UserPrivacyPolicy>> getUserPrivacyPolicy();

    @POST("payment-methods/payment-card-setup-sessions")
    Observable<Response<CardSetup>> paymentCardSetup();

    @FormUrlEncoded
    @POST
    Observable<Response<Void>> processPaymentCard(@Url String str, @FieldMap HashMap<String, Object> hashMap);

    @POST("phone-numbers/{id}/two-factor-code")
    Observable<Response<Void>> resendTwoFactorCode(@Path("id") String str);

    @POST("user/gdpr-requests")
    Observable<Response<Void>> sendGdprRequests(@Body HashMap<String, Object> hashMap);

    @POST("ach-setup-sessions/{id}/send-mfa")
    Observable<Response<AchSetupSession>> sendMFA(@Path("id") String str, @Body HashMap<String, Object> hashMap);

    @POST("payment-methods/{id}/start-ach-verification")
    Observable<Response<PaymentMethod>> startAchVerification(@Path("id") String str, @Body HashMap<String, Object> hashMap);

    @PUT("identity-verifications/{idology_id}")
    Observable<Response<Idology>> submitAnswers(@Path("idology_id") String str, @Body UserAnswers userAnswers);

    @POST("ach-setup-sessions/{id}/submit-mfa")
    Observable<Response<AchSetupSession>> submitMFA(@Path("id") String str, @Body HashMap<String, Object> hashMap);

    @PUT("user/email-preferences")
    Observable<Response<Void>> updateEmailPreferences(@Body HashMap<String, Object> hashMap);

    @PUT("user")
    Observable<Response<User>> updateUser(@Body HashMap<String, Object> hashMap);

    @POST("payment-methods/{id}/complete-card-verification")
    Observable<Response<Void>> verifyCardCDV(@Path("id") String str, @Body HashMap<String, Object> hashMap);

    @POST("payment-methods")
    Observable<Response<Verify>> verifyPaymentCard(@Body HashMap<String, Object> hashMap);

    @POST("phone-numbers/{id}/verify")
    Observable<Response<PhoneNumber>> verifyPhoneNumber(@Path("id") String str, @Body HashMap<String, Object> hashMap);
}
