package com.coinbase;

import com.coinbase.auth.AccessToken;
import com.coinbase.v2.models.account.Account;
import com.coinbase.v2.models.account.Accounts;
import com.coinbase.v2.models.address.Address;
import com.coinbase.v2.models.exchangeRates.ExchangeRates;
import com.coinbase.v2.models.paymentMethods.PaymentMethod;
import com.coinbase.v2.models.paymentMethods.PaymentMethods;
import com.coinbase.v2.models.price.Price;
import com.coinbase.v2.models.price.Prices;
import com.coinbase.v2.models.supportedCurrencies.SupportedCurrencies;
import com.coinbase.v2.models.transactions.Transaction;
import com.coinbase.v2.models.transactions.Transactions;
import com.coinbase.v2.models.transfers.Transfer;
import com.coinbase.v2.models.user.User;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiInterface {
    @POST("accounts/{id}/buys")
    Call<Transfer> buyBitcoin(@Path("id") String str, @Body HashMap<String, Object> hashMap);

    @DELETE("accounts/{account_id}/transactions/{transaction_id}")
    Call<Void> cancelTransaction(@Path("account_id") String str, @Path("transaction_id") String str2);

    @POST("accounts/{account_id}/buys/{buy_id}/commit")
    Call<Transfer> commitBuyBitcoin(@Path("account_id") String str, @Path("buy_id") String str2);

    @POST("accounts/{account_id}/deposits/{deposit_id}/commit")
    Call<Transfer> commitDeposit(@Path("account_id") String str, @Path("deposit_id") String str2);

    @POST("accounts/{account_id}/sells/{sell_id}/commit")
    Call<Transfer> commitSellBitcoin(@Path("account_id") String str, @Path("sell_id") String str2);

    @POST("accounts/{account_id}/withdrawals/{withdrawal_id}/commit")
    Call<Transfer> commitWithdraw(@Path("account_id") String str, @Path("withdrawal_id") String str2);

    @POST("accounts/{account_id}/transactions/{transaction_id}/complete")
    Call<Void> completeRequest(@Path("account_id") String str, @Path("transaction_id") String str2);

    @POST("accounts")
    Call<Account> createAccount(@Body HashMap<String, Object> hashMap);

    @DELETE("accounts/{id}")
    Call<Void> deleteAccount(@Path("id") String str);

    @POST("accounts/{account_id}/deposits")
    Call<Transfer> depositFunds(@Path("account_id") String str, @Body HashMap<String, Object> hashMap);

    @POST("accounts/{account_id}/addresses")
    Call<Address> generateAddress(@Path("account_id") String str);

    @GET("accounts/{id}")
    Call<Account> getAccount(@Path("id") String str);

    @GET("accounts")
    Call<Accounts> getAccounts(@QueryMap Map<String, Object> map);

    @GET("prices/{base_currency}-{fiat_currency}/buy")
    Call<Price> getBuyPrice(@Path("base_currency") String str, @Path("fiat_currency") String str2, @QueryMap HashMap<String, Object> hashMap);

    @GET("exchange-rates")
    Call<ExchangeRates> getExchangeRates(@QueryMap HashMap<String, Object> hashMap);

    @GET("payment-methods/{id}")
    Call<PaymentMethod> getPaymentMethod(@Path("id") String str);

    @GET("payment-methods")
    Call<PaymentMethods> getPaymentMethods(@QueryMap HashMap<String, Object> hashMap);

    @GET("prices/{base_currency}-{fiat_currency}/sell")
    Call<Price> getSellPrice(@Path("base_currency") String str, @Path("fiat_currency") String str2, @QueryMap HashMap<String, Object> hashMap);

    @GET("prices/{base_currency}-{fiat_currency}/spot")
    Call<Price> getSpotPrice(@Path("base_currency") String str, @Path("fiat_currency") String str2, @QueryMap HashMap<String, Object> hashMap);

    @GET("prices/{fiat_currency}/spot")
    Call<Prices> getSpotPrices(@Path("fiat_currency") String str, @QueryMap HashMap<String, Object> hashMap);

    @GET("currencies")
    Call<SupportedCurrencies> getSupportedCurrencies();

    @GET("accounts/{account_id}/transactions/{transaction_id}")
    Call<Transaction> getTransaction(@Path("account_id") String str, @Path("transaction_id") String str2, @Query("expand[]") List<String> list);

    @GET("accounts/{id}/transactions")
    Call<Transactions> getTransactions(@Path("id") String str, @Query("expand[]") List<String> list, @QueryMap Map<String, Object> map);

    @GET("user")
    Call<User> getUser();

    @POST("token")
    Call<AccessToken> refreshTokens(@Body HashMap<String, Object> hashMap);

    @POST("accounts/{id}/transactions")
    Call<Transaction> requestMoney(@Path("id") String str, @Body HashMap<String, Object> hashMap);

    @POST("accounts/{account_id}/transactions/{transaction_id}/resend")
    Call<Void> resendRequest(@Path("account_id") String str, @Path("transaction_id") String str2);

    @POST("revoke")
    Call<Void> revokeToken(@Body HashMap<String, Object> hashMap);

    @POST("accounts/{id}/sells")
    Call<Transfer> sellBitcoin(@Path("id") String str, @Body HashMap<String, Object> hashMap);

    @POST("accounts/{id}/transactions")
    Call<Transaction> sendMoney(@Path("id") String str, @Body HashMap<String, Object> hashMap);

    @POST("accounts/{id}/primary")
    Call<Void> setAccountPrimary(@Path("id") String str);

    @POST("accounts/{id}/transactions")
    Call<Transaction> transferMoney(@Path("id") String str, @Body HashMap<String, Object> hashMap);

    @PUT("accounts/{id}")
    Call<Account> updateAccount(@Path("id") String str, @Body HashMap<String, Object> hashMap);

    @PUT("user")
    Call<User> updateUser(@Body HashMap<String, Object> hashMap);

    @POST("accounts/{account_id}/withdrawals")
    Call<Transfer> withdrawFunds(@Path("account_id") String str, @Body HashMap<String, Object> hashMap);
}
