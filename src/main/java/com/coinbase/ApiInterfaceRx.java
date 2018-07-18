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
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

public interface ApiInterfaceRx {
    @POST("accounts/{id}/buys")
    Observable<Response<Transfer>> buyBitcoin(@Path("id") String str, @Body HashMap<String, Object> hashMap);

    @DELETE("accounts/{account_id}/transactions/{transaction_id}")
    Observable<Response<Void>> cancelTransaction(@Path("account_id") String str, @Path("transaction_id") String str2);

    @POST("accounts/{account_id}/buys/{buy_id}/commit")
    Observable<Response<Transfer>> commitBuyBitcoin(@Path("account_id") String str, @Path("buy_id") String str2);

    @POST("accounts/{account_id}/deposits/{deposit_id}/commit")
    Observable<Response<Transfer>> commitDeposit(@Path("account_id") String str, @Path("deposit_id") String str2);

    @POST("accounts/{account_id}/sells/{sell_id}/commit")
    Observable<Response<Transfer>> commitSellBitcoin(@Path("account_id") String str, @Path("sell_id") String str2);

    @POST("accounts/{account_id}/withdrawals/{withdrawal_id}/commit")
    Observable<Response<Transfer>> commitWithdraw(@Path("account_id") String str, @Path("withdrawal_id") String str2);

    @POST("accounts/{account_id}/transactions/{transaction_id}/complete")
    Observable<Response<Void>> completeRequest(@Path("account_id") String str, @Path("transaction_id") String str2);

    @POST("accounts")
    Observable<Response<Account>> createAccount(@Body HashMap<String, Object> hashMap);

    @DELETE("accounts/{id}")
    Observable<Response<Void>> deleteAccount(@Path("id") String str);

    @POST("accounts/{account_id}/deposits")
    Observable<Response<Transfer>> depositFunds(@Path("account_id") String str, @Body HashMap<String, Object> hashMap);

    @POST("accounts/{account_id}/addresses")
    Observable<Response<Address>> generateAddress(@Path("account_id") String str);

    @GET("accounts/{id}")
    Observable<Response<Account>> getAccount(@Path("id") String str);

    @GET("accounts")
    Observable<Response<Accounts>> getAccounts(@QueryMap Map<String, Object> map);

    @GET("prices/{base_currency}-{fiat_currency}/buy")
    Observable<Response<Price>> getBuyPrice(@Path("base_currency") String str, @Path("fiat_currency") String str2, @QueryMap HashMap<String, Object> hashMap);

    @GET("exchange-rates")
    Observable<Response<ExchangeRates>> getExchangeRates(@QueryMap HashMap<String, Object> hashMap);

    @GET("payment-methods/{id}")
    Observable<Response<PaymentMethod>> getPaymentMethod(@Path("id") String str);

    @GET("payment-methods/{id}/verified")
    Observable<Response<PaymentMethod>> getPaymentMethodVerified(@Path("id") String str);

    @GET("payment-methods")
    Observable<Response<PaymentMethods>> getPaymentMethods(@QueryMap HashMap<String, Object> hashMap);

    @GET("prices/{base_currency}-{fiat_currency}/sell")
    Observable<Response<Price>> getSellPrice(@Path("base_currency") String str, @Path("fiat_currency") String str2, @QueryMap HashMap<String, Object> hashMap);

    @GET("prices/{base_currency}-{fiat_currency}/spot")
    Observable<Response<Price>> getSpotPrice(@Path("base_currency") String str, @Path("fiat_currency") String str2, @QueryMap HashMap<String, Object> hashMap);

    @GET("prices/{fiat_currency}/spot")
    Observable<Response<Prices>> getSpotPrices(@Path("fiat_currency") String str, @QueryMap HashMap<String, Object> hashMap);

    @GET("currencies")
    Observable<Response<SupportedCurrencies>> getSupportedCurrencies();

    @GET("accounts/{account_id}/transactions/{transaction_id}")
    Observable<Response<Transaction>> getTransaction(@Path("account_id") String str, @Path("transaction_id") String str2, @Query("expand[]") List<String> list);

    @GET("accounts/{id}/transactions")
    Observable<Response<Transactions>> getTransactions(@Path("id") String str, @Query("expand[]") List<String> list, @QueryMap Map<String, Object> map);

    @GET("user")
    Observable<Response<User>> getUser();

    @POST("token")
    Observable<Response<AccessToken>> refreshTokens(@Body HashMap<String, Object> hashMap);

    @POST("accounts/{id}/transactions")
    Observable<Response<Transaction>> requestMoney(@Path("id") String str, @Body HashMap<String, Object> hashMap);

    @POST("accounts/{account_id}/transactions/{transaction_id}/resend")
    Observable<Response<Void>> resendRequest(@Path("account_id") String str, @Path("transaction_id") String str2);

    @POST("revoke")
    Observable<Response<Void>> revokeToken(@Body HashMap<String, Object> hashMap);

    @POST("accounts/{id}/sells")
    Observable<Response<Transfer>> sellBitcoin(@Path("id") String str, @Body HashMap<String, Object> hashMap);

    @POST("accounts/{id}/transactions")
    Observable<Response<Transaction>> sendMoney(@Path("id") String str, @Body HashMap<String, Object> hashMap);

    @POST("accounts/{id}/primary")
    Observable<Response<Void>> setAccountPrimary(@Path("id") String str);

    @POST("accounts/{id}/transactions")
    Observable<Response<Transaction>> transferMoney(@Path("id") String str, @Body HashMap<String, Object> hashMap);

    @PUT("accounts/{id}")
    Observable<Response<Account>> updateAccount(@Path("id") String str, @Body HashMap<String, Object> hashMap);

    @PUT("user")
    Observable<Response<User>> updateUser(@Body HashMap<String, Object> hashMap);

    @POST("accounts/{account_id}/withdrawals")
    Observable<Response<Transfer>> withdrawFunds(@Path("account_id") String str, @Body HashMap<String, Object> hashMap);
}
