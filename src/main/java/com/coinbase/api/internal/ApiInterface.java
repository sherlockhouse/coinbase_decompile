package com.coinbase.api.internal;

import com.coinbase.api.internal.models.achSetupSession.AchSetupSession;
import com.coinbase.api.internal.models.address.Address;
import com.coinbase.api.internal.models.agreement.UserAgreement;
import com.coinbase.api.internal.models.auth.Auth;
import com.coinbase.api.internal.models.availablePaymentMethods.AvailablePaymentMethods;
import com.coinbase.api.internal.models.billingAddress.BillingAddress;
import com.coinbase.api.internal.models.billingAddress.BillingAddresses;
import com.coinbase.api.internal.models.config.Config;
import com.coinbase.api.internal.models.contacts.Contacts;
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
import com.coinbase.api.internal.models.sepaDepositInfo.SepaDepositInfo;
import com.coinbase.api.internal.models.status.Status;
import com.coinbase.api.internal.models.transaction.TransactionFee;
import com.coinbase.v2.models.paymentMethods.PaymentMethod;
import com.coinbase.v2.models.transfers.Transfer;
import com.coinbase.v2.models.user.User;
import java.util.HashMap;
import okhttp3.RequestBody;
import retrofit2.Call;
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

public interface ApiInterface {
    @POST("user/agreement")
    Call<Void> acceptUserAgreement();

    @POST("accounts/{account_id}/buys/{buy_id}/complete")
    Call<Transfer> completeBuy(@Path("account_id") String str, @Path("buy_id") String str2, @Body HashMap<String, Object> hashMap);

    @POST("payment-methods/{id}/complete-ach-cdv")
    Call<PaymentMethod> completeCDVVerification(@Path("id") String str, @Body HashMap<String, Object> hashMap);

    @POST("ach-setup-sessions")
    Call<AchSetupSession> createAchSetupSession(@Body HashMap<String, Object> hashMap);

    @PUT("user/address")
    Call<Address> createAddress(@Body HashMap<String, Object> hashMap);

    @POST("payment-methods")
    Call<PaymentMethod> createBankManually(@Body HashMap<String, Object> hashMap);

    @POST("payment-methods")
    Call<PaymentMethod> createBankWithAchSetupSession(@Body HashMap<String, Object> hashMap);

    @POST("billing-addresses")
    Call<BillingAddress> createBillingAddress(@Body HashMap<String, Object> hashMap);

    @POST("mobile/device-tokens")
    Call<Void> createDeviceToken(@Body HashMap<String, Object> hashMap);

    @POST("identity-verifications")
    Call<Void> createIdentityVerification(@Body HashMap<String, Object> hashMap);

    @POST("jumio-profiles")
    @Multipart
    Call<JumioProfile> createJumioProfile(@Part("country_code") RequestBody requestBody, @Part("id_type") RequestBody requestBody2, @Part("front_image\"; filename=\"image.png\"") RequestBody requestBody3, @Part("back_image\"; filename=\"image.png\"") RequestBody requestBody4, @Part("face_image\"; filename=\"image.png\"") RequestBody requestBody5);

    @POST("phone-numbers")
    Call<PhoneNumber> createPhoneNumber(@Body HashMap<String, Object> hashMap);

    @POST("mobile/price-alerts")
    Call<Void> createPriceAlert(@Body HashMap<String, Object> hashMap);

    @POST("mobile/users")
    Call<User> createUser(@Body HashMap<String, Object> hashMap);

    @DELETE("ach-setup-sessions/{id}")
    Call<Void> deleteAchSetupSession(@Path("id") String str);

    @DELETE("billing-addresses/{id}")
    Call<Void> deleteBillingAddress(@Path("id") String str);

    @DELETE("payment-methods/{id}")
    Call<Void> deletePaymentMethod(@Path("id") String str);

    @DELETE("phone-numbers/{id}")
    Call<PhoneNumber> deletePhoneNumber(@Path("id") String str);

    @DELETE("mobile/price-alerts/{id}")
    Call<Void> deletePriceAlert(@Path("id") String str, @QueryMap HashMap<String, Object> hashMap);

    @GET("payment-methods/ach-institutions")
    Call<Institution> getAchInstitutions();

    @POST("oauth/authorize/with-credentials")
    Call<Auth> getAuthCode(@Body HashMap<String, Object> hashMap);

    @GET("user/available-payment-methods/{type}")
    Call<AvailablePaymentMethods> getAvailablePaymentMethods(@Path("type") String str);

    @GET("billing-addresses/{id}")
    Call<BillingAddress> getBillingAddress(@Path("id") String str);

    @GET("billing-addresses")
    Call<BillingAddresses> getBillingAddresses();

    @GET("api/v2/components.json")
    Call<Status> getCoinbaseStatus();

    @GET("identity-verifications/coinbase-uses")
    Call<IdologyOptions> getCoinbaseUses();

    @GET("mobile/config")
    Call<Config> getConfig();

    @GET("contacts")
    Call<Contacts> getContacts(@QueryMap HashMap<String, Object> hashMap);

    @POST("accounts/{id}/instant-exchange-quotes")
    Call<InstantExchangeQuote> getInstantExchangeQuote(@Path("id") String str, @Body HashMap<String, Object> hashMap);

    @GET("identity-verifications/job-titles")
    Call<IdologyOptions> getJobTitles();

    @GET("jumio-profiles")
    Call<JumioProfiles> getJumioProfiles();

    @GET("jumio-profiles/supported-documents/{country_code}")
    Call<SupportedDocument> getJumioSupportedDocument(@Path("country_code") String str);

    @GET("jumio-profiles/supported-documents")
    Call<SupportedDocuments> getJumioSupportedDocuments();

    @GET("phone-numbers")
    Call<PhoneNumbers> getPhoneNumbers();

    @GET("policy-restrictions/{type}")
    Call<Restrictions> getPolicyRestrictions(@Path("type") String str);

    @GET("prices/{base_currency}-{fiat_currency}/historic")
    Call<PriceChart> getPriceChart(@Path("base_currency") String str, @Path("fiat_currency") String str2, @QueryMap HashMap<String, Object> hashMap);

    @GET("payment-methods/sepa-deposit-information")
    Call<SepaDepositInfo> getSepaInformation();

    @GET("identity-verifications/source-of-funds")
    Call<IdologyOptions> getSourceOfFunds();

    @GET("accounts/{account_id}/transactions/fee-estimate")
    Call<TransactionFee> getTransactionFee(@Path("account_id") String str, @QueryMap HashMap<String, Object> hashMap);

    @GET("user/agreement")
    Call<UserAgreement> getUserAgreement();

    @POST("payment-methods/payment-card-setup-sessions")
    Call<CardSetup> paymentCardSetup();

    @FormUrlEncoded
    @POST
    Call<Void> processPaymentCard(@Url String str, @FieldMap HashMap<String, Object> hashMap);

    @POST("ach-setup-sessions/{id}/send-mfa")
    Call<AchSetupSession> sendMFA(@Path("id") String str, @Body HashMap<String, Object> hashMap);

    @POST("payment-methods/{id}/start-ach-verification")
    Call<PaymentMethod> startAchVerification(@Path("id") String str, @Body HashMap<String, Object> hashMap);

    @POST("ach-setup-sessions/{id}/submit-mfa")
    Call<AchSetupSession> submitMFA(@Path("id") String str, @Body HashMap<String, Object> hashMap);

    @PUT("user")
    Call<User> updateUser(@Body HashMap<String, Object> hashMap);

    @POST("payment-methods/{id}/complete-card-verification")
    Call<Void> verifyCardCDV(@Path("id") String str, @Body HashMap<String, Object> hashMap);

    @POST("payment-methods")
    Call<Verify> verifyPaymentCard(@Body HashMap<String, Object> hashMap);

    @POST("phone-numbers/{id}/verify")
    Call<PhoneNumber> verifyPhoneNumber(@Path("id") String str, @Body HashMap<String, Object> hashMap);
}
