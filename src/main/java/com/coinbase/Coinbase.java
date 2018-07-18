package com.coinbase;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import au.com.bytecode.opencsv.CSVReader;
import com.coinbase.api.internal.ApiConstants;
import com.coinbase.auth.AccessToken;
import com.coinbase.cache.OkHttpInMemoryLruCache;
import com.coinbase.coinbase_java.BuildConfig;
import com.coinbase.v1.entity.Account;
import com.coinbase.v1.entity.AccountChangesResponse;
import com.coinbase.v1.entity.AccountResponse;
import com.coinbase.v1.entity.AccountsResponse;
import com.coinbase.v1.entity.Address;
import com.coinbase.v1.entity.AddressResponse;
import com.coinbase.v1.entity.AddressesResponse;
import com.coinbase.v1.entity.Application;
import com.coinbase.v1.entity.ApplicationResponse;
import com.coinbase.v1.entity.ApplicationsResponse;
import com.coinbase.v1.entity.Button;
import com.coinbase.v1.entity.ButtonResponse;
import com.coinbase.v1.entity.ContactsResponse;
import com.coinbase.v1.entity.HistoricalPrice;
import com.coinbase.v1.entity.OAuthCodeRequest;
import com.coinbase.v1.entity.OAuthCodeRequest.Meta;
import com.coinbase.v1.entity.OAuthCodeResponse;
import com.coinbase.v1.entity.OAuthTokensRequest;
import com.coinbase.v1.entity.OAuthTokensRequest.GrantType;
import com.coinbase.v1.entity.OAuthTokensResponse;
import com.coinbase.v1.entity.Order;
import com.coinbase.v1.entity.OrderResponse;
import com.coinbase.v1.entity.OrdersResponse;
import com.coinbase.v1.entity.PaymentMethodResponse;
import com.coinbase.v1.entity.PaymentMethodsResponse;
import com.coinbase.v1.entity.Quote;
import com.coinbase.v1.entity.RecurringPayment;
import com.coinbase.v1.entity.RecurringPaymentResponse;
import com.coinbase.v1.entity.RecurringPaymentsResponse;
import com.coinbase.v1.entity.Report;
import com.coinbase.v1.entity.ReportResponse;
import com.coinbase.v1.entity.ReportsResponse;
import com.coinbase.v1.entity.Request;
import com.coinbase.v1.entity.Response;
import com.coinbase.v1.entity.ResponseV1;
import com.coinbase.v1.entity.ResponseV2;
import com.coinbase.v1.entity.RevokeTokenRequest;
import com.coinbase.v1.entity.Token;
import com.coinbase.v1.entity.TokenResponse;
import com.coinbase.v1.entity.Transaction;
import com.coinbase.v1.entity.TransactionResponse;
import com.coinbase.v1.entity.TransactionsResponse;
import com.coinbase.v1.entity.Transfer;
import com.coinbase.v1.entity.TransferResponse;
import com.coinbase.v1.entity.TransfersResponse;
import com.coinbase.v1.entity.User;
import com.coinbase.v1.entity.UserNode;
import com.coinbase.v1.entity.UserResponse;
import com.coinbase.v1.entity.UsersResponse;
import com.coinbase.v1.exception.CoinbaseException;
import com.coinbase.v1.exception.CredentialsIncorrectException;
import com.coinbase.v1.exception.TwoFactorIncorrectException;
import com.coinbase.v1.exception.TwoFactorRequiredException;
import com.coinbase.v1.exception.UnauthorizedDeviceException;
import com.coinbase.v1.exception.UnauthorizedException;
import com.coinbase.v1.exception.UnspecifiedAccount;
import com.coinbase.v2.models.account.Accounts;
import com.coinbase.v2.models.exchangeRates.ExchangeRates;
import com.coinbase.v2.models.paymentMethods.PaymentMethod;
import com.coinbase.v2.models.paymentMethods.PaymentMethods;
import com.coinbase.v2.models.price.Price;
import com.coinbase.v2.models.price.Prices;
import com.coinbase.v2.models.supportedCurrencies.SupportedCurrencies;
import com.coinbase.v2.models.transactions.Transactions;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Protocol;
import okio.Buffer;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.IOUtils;
import org.joda.money.CurrencyUnit;
import org.joda.money.IllegalCurrencyException;
import org.joda.money.Money;
import org.joda.time.DateTime;
import retrofit2.Call;
import retrofit2.CallAdapter.Factory;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Scheduler;
import rx.schedulers.Schedulers;

public class Coinbase {
    private static final int DEFAULT_CACHE_SIZE = 524288;
    protected static final ObjectMapper objectMapper = ObjectMapperProvider.createDefaultMapper();
    protected String _accessToken;
    protected String _accountId;
    protected String _apiKey;
    protected String _apiSecret;
    protected Scheduler _backgroundScheduler;
    protected URL _baseApiUrl;
    protected URL _baseOAuthUrl;
    protected URL _baseV1ApiUrl;
    protected URL _baseV2ApiUrl;
    protected final OkHttpInMemoryLruCache _cache;
    protected CallbackVerifier _callbackVerifier;
    protected Context _context;
    protected SSLSocketFactory _socketFactory;
    protected SSLContext _sslContext;
    protected final HashMap<String, Pair<ApiInterface, Retrofit>> mInitializedServices = new HashMap();
    protected final HashMap<String, Pair<ApiInterfaceRx, Retrofit>> mInitializedServicesRx = new HashMap();

    public Coinbase() {
        try {
            this._baseApiUrl = new URL("https://api.coinbase.com/");
            this._baseV1ApiUrl = new URL("https://api.coinbase.com/v1/");
            this._baseV2ApiUrl = new URL("https://api.coinbase.com/v2/");
            this._baseOAuthUrl = new URL("https://www.coinbase.com/oauth/");
            this._sslContext = CoinbaseSSL.getSSLContext();
            this._socketFactory = this._sslContext.getSocketFactory();
            this._callbackVerifier = new CallbackVerifierImpl();
            this._backgroundScheduler = Schedulers.io();
            this._cache = new OkHttpInMemoryLruCache(DEFAULT_CACHE_SIZE);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    protected Builder generateClientBuilder(SSLContext sslContext) {
        Builder clientBuilder = new Builder();
        if (sslContext != null) {
            clientBuilder.sslSocketFactory(sslContext.getSocketFactory());
        }
        clientBuilder.protocols(Collections.singletonList(Protocol.HTTP_1_1));
        clientBuilder.readTimeout(30, TimeUnit.SECONDS);
        clientBuilder.connectTimeout(30, TimeUnit.SECONDS);
        return clientBuilder;
    }

    public void setBaseUrl(String url) {
        try {
            setBaseUrl(url, SSLContext.getDefault());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setBaseUrl(String url, SSLContext sslContext) {
        try {
            this._baseApiUrl = new URL(url + "/");
            this._baseV1ApiUrl = new URL(url + "/v1/");
            this._baseV2ApiUrl = new URL(url + "/v2/");
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void setBackgroundScheduler(Scheduler backgroundScheduler) {
        this._backgroundScheduler = backgroundScheduler;
    }

    public void init(Context context, String apiKey, String apiSecret) {
        this._apiKey = apiKey;
        this._apiSecret = apiSecret;
        this._context = context;
        this._cache.evictAll();
        this._cache.clearForcedCache();
    }

    public void init(Context context, String accessToken) {
        if (!TextUtils.equals(this._accessToken, accessToken)) {
            this.mInitializedServices.clear();
            this.mInitializedServicesRx.clear();
            this._cache.evictAll();
            this._cache.clearForcedCache();
        }
        this._accessToken = accessToken;
        this._context = context;
    }

    Coinbase(CoinbaseBuilder builder) {
        this._baseV1ApiUrl = builder.base_api_url;
        this._baseOAuthUrl = builder.base_oauth_url;
        this._apiKey = builder.api_key;
        this._apiSecret = builder.api_secret;
        this._accessToken = builder.access_token;
        this._accountId = builder.acct_id;
        this._sslContext = builder.ssl_context;
        this._callbackVerifier = builder.callback_verifier;
        this._backgroundScheduler = builder.scheduler;
        this._cache = new OkHttpInMemoryLruCache(builder.cacheSize > 0 ? builder.cacheSize : DEFAULT_CACHE_SIZE);
        try {
            if (this._baseV1ApiUrl == null) {
                this._baseV1ApiUrl = new URL("https://coinbase.com/api/v1/");
            }
            if (this._baseOAuthUrl == null) {
                this._baseOAuthUrl = new URL("https://www.coinbase.com/oauth/");
            }
            if (this._baseV2ApiUrl == null) {
                this._baseV2ApiUrl = new URL("https://api.coinbase.com/v2/");
            }
            try {
                CurrencyUnit.registerCurrency("BTC", -1, 8, new ArrayList());
            } catch (IllegalArgumentException e) {
            }
            if (this._sslContext != null) {
                this._socketFactory = this._sslContext.getSocketFactory();
            } else {
                this._sslContext = CoinbaseSSL.getSSLContext();
                this._socketFactory = this._sslContext.getSocketFactory();
            }
            if (this._callbackVerifier == null) {
                this._callbackVerifier = new CallbackVerifierImpl();
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public User getUser() throws IOException, CoinbaseException {
        try {
            return ((UserNode) ((UsersResponse) get(new URL(this._baseV1ApiUrl, ApiConstants.USERS), UsersResponse.class)).getUsers().get(0)).getUser();
        } catch (MalformedURLException ex) {
            throw new AssertionError(ex);
        }
    }

    public AccountsResponse getAccounts() throws IOException, CoinbaseException {
        return getAccounts(1, 25, false);
    }

    public AccountsResponse getAccounts(int page) throws IOException, CoinbaseException {
        return getAccounts(page, 25, false);
    }

    public AccountsResponse getAccounts(int page, int limit) throws IOException, CoinbaseException {
        return getAccounts(page, limit, false);
    }

    public AccountsResponse getAccounts(int page, int limit, boolean includeInactive) throws IOException, CoinbaseException {
        try {
            String str;
            URL url = this._baseV1ApiUrl;
            StringBuilder append = new StringBuilder().append("accounts?&page=").append(page).append("&limit=").append(limit).append("&all_accounts=");
            if (includeInactive) {
                str = "true";
            } else {
                str = "false";
            }
            return (AccountsResponse) get(new URL(url, append.append(str).toString()), AccountsResponse.class);
        } catch (MalformedURLException ex) {
            throw new AssertionError(ex);
        }
    }

    public Money getBalance() throws IOException, CoinbaseException {
        if (this._accountId != null) {
            return getBalance(this._accountId);
        }
        throw new UnspecifiedAccount();
    }

    public Money getBalance(String accountId) throws IOException, CoinbaseException {
        try {
            return (Money) deserialize(doHttp(new URL(this._baseV1ApiUrl, "accounts/" + accountId + "/balance"), "GET", null), Money.class);
        } catch (MalformedURLException e) {
            throw new CoinbaseException("Invalid account id");
        }
    }

    public void setPrimaryAccount(String accountId) throws CoinbaseException, IOException {
        try {
            post(new URL(this._baseV1ApiUrl, "accounts/" + accountId + "/primary"), new Request(), Response.class);
        } catch (MalformedURLException e) {
            throw new CoinbaseException("Invalid account id");
        }
    }

    public void deleteAccount(String accountId) throws CoinbaseException, IOException {
        try {
            delete(new URL(this._baseV1ApiUrl, "accounts/" + accountId), Response.class);
        } catch (MalformedURLException e) {
            throw new CoinbaseException("Invalid account id");
        }
    }

    public void setPrimaryAccount() throws CoinbaseException, IOException {
        if (this._accountId != null) {
            setPrimaryAccount(this._accountId);
            return;
        }
        throw new UnspecifiedAccount();
    }

    public void deleteAccount() throws CoinbaseException, IOException {
        if (this._accountId != null) {
            deleteAccount(this._accountId);
            return;
        }
        throw new UnspecifiedAccount();
    }

    public void updateAccount(Account account) throws CoinbaseException, IOException, UnspecifiedAccount {
        if (this._accountId != null) {
            updateAccount(this._accountId, account);
            return;
        }
        throw new UnspecifiedAccount();
    }

    public Account createAccount(Account account) throws CoinbaseException, IOException {
        try {
            URL accountsUrl = new URL(this._baseV1ApiUrl, "accounts");
            Request request = new Request();
            request.setAccount(account);
            return ((AccountResponse) post(accountsUrl, request, AccountResponse.class)).getAccount();
        } catch (MalformedURLException ex) {
            throw new AssertionError(ex);
        }
    }

    public void updateAccount(String accountId, Account account) throws CoinbaseException, IOException {
        try {
            URL accountUrl = new URL(this._baseV1ApiUrl, "accounts/" + accountId);
            Request request = new Request();
            request.setAccount(account);
            put(accountUrl, request, Response.class);
        } catch (MalformedURLException e) {
            throw new CoinbaseException("Invalid account id");
        }
    }

    public Money getSpotPrice(CurrencyUnit currency) throws IOException, CoinbaseException {
        try {
            return (Money) deserialize(doHttp(new URL(this._baseV1ApiUrl, "prices/spot_rate?currency=" + URLEncoder.encode(currency.getCurrencyCode(), "UTF-8")), "GET", null), Money.class);
        } catch (MalformedURLException ex) {
            throw new AssertionError(ex);
        }
    }

    public Quote getBuyQuote(Money amount) throws IOException, CoinbaseException {
        return getBuyQuote(amount, null);
    }

    public Quote getBuyQuote(Money amount, String paymentMethodId) throws IOException, CoinbaseException {
        String qtyParam;
        if (amount.getCurrencyUnit().getCode().equals("BTC")) {
            qtyParam = "qty";
        } else {
            qtyParam = "native_qty";
        }
        try {
            return (Quote) deserialize(doHttp(new URL(this._baseV1ApiUrl, "prices/buy?" + qtyParam + "=" + URLEncoder.encode(amount.getAmount().toPlainString(), "UTF-8") + (this._accountId != null ? "&account_id=" + this._accountId : "") + (paymentMethodId != null ? "&payment_method_id=" + paymentMethodId : "")), "GET", null), Quote.class);
        } catch (MalformedURLException ex) {
            throw new AssertionError(ex);
        }
    }

    public Quote getSellQuote(Money amount) throws IOException, CoinbaseException {
        return getSellQuote(amount, null);
    }

    public Quote getSellQuote(Money amount, String paymentMethodId) throws IOException, CoinbaseException {
        String qtyParam;
        if (amount.getCurrencyUnit().getCode().equals("BTC")) {
            qtyParam = "qty";
        } else {
            qtyParam = "native_qty";
        }
        try {
            return (Quote) deserialize(doHttp(new URL(this._baseV1ApiUrl, "prices/sell?" + qtyParam + "=" + URLEncoder.encode(amount.getAmount().toPlainString(), "UTF-8") + (this._accountId != null ? "&account_id=" + this._accountId : "") + (paymentMethodId != null ? "&payment_method_id=" + paymentMethodId : "")), "GET", null), Quote.class);
        } catch (MalformedURLException ex) {
            throw new AssertionError(ex);
        }
    }

    public TransactionsResponse getTransactions(int page) throws IOException, CoinbaseException {
        try {
            return (TransactionsResponse) get(new URL(this._baseV1ApiUrl, "transactions?page=" + page + (this._accountId != null ? "&account_id=" + this._accountId : "")), TransactionsResponse.class);
        } catch (MalformedURLException e) {
            throw new CoinbaseException("Invalid account id");
        }
    }

    public TransactionsResponse getTransactions() throws IOException, CoinbaseException {
        return getTransactions(1);
    }

    public Transaction getTransaction(String id) throws IOException, CoinbaseException {
        try {
            return ((TransactionResponse) get(new URL(this._baseV1ApiUrl, "transactions/" + id), TransactionResponse.class)).getTransaction();
        } catch (MalformedURLException e) {
            throw new CoinbaseException("Invalid transaction id");
        }
    }

    public Transaction requestMoney(Transaction transaction) throws CoinbaseException, IOException {
        try {
            URL requestMoneyUrl = new URL(this._baseV1ApiUrl, "transactions/request_money");
            if (transaction.getAmount() == null) {
                throw new CoinbaseException("Amount is a required field");
            }
            Request request = newAccountSpecificRequest();
            request.setTransaction(transaction);
            return ((TransactionResponse) post(requestMoneyUrl, request, TransactionResponse.class)).getTransaction();
        } catch (MalformedURLException ex) {
            throw new AssertionError(ex);
        }
    }

    public void resendRequest(String id) throws CoinbaseException, IOException {
        try {
            put(new URL(this._baseV1ApiUrl, "transactions/" + id + "/resend_request"), newAccountSpecificRequest(), Response.class);
        } catch (MalformedURLException e) {
            throw new CoinbaseException("Invalid transaction id");
        }
    }

    public void deleteRequest(String id) throws CoinbaseException, IOException {
        try {
            delete(new URL(this._baseV1ApiUrl, "transactions/" + id + "/cancel_request"), Response.class);
        } catch (MalformedURLException e) {
            throw new CoinbaseException("Invalid transaction id");
        }
    }

    public Transaction completeRequest(String id) throws CoinbaseException, IOException {
        try {
            return ((TransactionResponse) put(new URL(this._baseV1ApiUrl, "transactions/" + id + "/complete_request"), newAccountSpecificRequest(), TransactionResponse.class)).getTransaction();
        } catch (MalformedURLException e) {
            throw new CoinbaseException("Invalid transaction id");
        }
    }

    public Transaction sendMoney(Transaction transaction) throws CoinbaseException, IOException {
        try {
            URL sendMoneyUrl = new URL(this._baseV1ApiUrl, "transactions/send_money");
            if (transaction.getAmount() == null) {
                throw new CoinbaseException("Amount is a required field");
            }
            Request request = newAccountSpecificRequest();
            request.setTransaction(transaction);
            return ((TransactionResponse) post(sendMoneyUrl, request, TransactionResponse.class)).getTransaction();
        } catch (MalformedURLException ex) {
            throw new AssertionError(ex);
        }
    }

    public TransfersResponse getTransfers(int page) throws IOException, CoinbaseException {
        try {
            return (TransfersResponse) get(new URL(this._baseV1ApiUrl, "transfers?page=" + page + (this._accountId != null ? "&account_id=" + this._accountId : "")), TransfersResponse.class);
        } catch (MalformedURLException e) {
            throw new CoinbaseException("Invalid account id");
        }
    }

    public TransfersResponse getTransfers() throws IOException, CoinbaseException {
        return getTransfers(1);
    }

    public Transaction transferMoneyBetweenAccounts(String amount, String toAccountId) throws CoinbaseException, IOException {
        try {
            URL transferMoneyURL = new URL(this._baseV1ApiUrl, "transactions/transfer_money");
            Transaction transaction = new Transaction();
            transaction.setTo(toAccountId);
            transaction.setTransferBitcoinAmountString(amount);
            Request request = newAccountSpecificRequest();
            request.setTransaction(transaction);
            return ((TransactionResponse) post(transferMoneyURL, request, TransactionResponse.class)).getTransaction();
        } catch (MalformedURLException ex) {
            throw new AssertionError(ex);
        }
    }

    public Transfer sell(Money amount) throws CoinbaseException, IOException {
        return sell(amount, null);
    }

    public Transfer sell(Money amount, String paymentMethodId) throws CoinbaseException, IOException {
        return sell(amount, paymentMethodId, null);
    }

    public Transfer sell(Money amount, String paymentMethodId, Boolean commit) throws CoinbaseException, IOException {
        try {
            URL sellsUrl = new URL(this._baseV1ApiUrl, ApiConstants.SELLS);
            Request request = newAccountSpecificRequest();
            request.setQty(Double.valueOf(amount.getAmount().doubleValue()));
            request.setPaymentMethodId(paymentMethodId);
            request.setCurrency(amount.getCurrencyUnit().getCurrencyCode());
            request.setCommit(commit);
            return ((TransferResponse) post(sellsUrl, request, TransferResponse.class)).getTransfer();
        } catch (MalformedURLException ex) {
            throw new AssertionError(ex);
        }
    }

    public Transfer buy(Money amount) throws CoinbaseException, IOException {
        return buy(amount, null);
    }

    public Transfer buy(Money amount, String paymentMethodId) throws CoinbaseException, IOException {
        return buy(amount, paymentMethodId, null);
    }

    public Transfer buy(Money amount, String paymentMethodId, Boolean commit) throws CoinbaseException, IOException {
        try {
            URL buysUrl = new URL(this._baseV1ApiUrl, "buys");
            Request request = newAccountSpecificRequest();
            request.setQty(Double.valueOf(amount.getAmount().doubleValue()));
            request.setPaymentMethodId(paymentMethodId);
            request.setCurrency(amount.getCurrencyUnit().getCurrencyCode());
            request.setCommit(commit);
            return ((TransferResponse) post(buysUrl, request, TransferResponse.class)).getTransfer();
        } catch (MalformedURLException ex) {
            throw new AssertionError(ex);
        }
    }

    public Transfer commitTransfer(String accountId, String transactionId) throws CoinbaseException, IOException {
        try {
            URL commitUrl = new URL(this._baseV1ApiUrl, "transfers/" + transactionId + "/commit");
            Request request = new Request();
            request.setAccountId(accountId);
            return ((TransferResponse) post(commitUrl, request, TransferResponse.class)).getTransfer();
        } catch (MalformedURLException e) {
            throw new CoinbaseException("Invalid transaction id");
        }
    }

    public Order getOrder(String idOrCustom) throws IOException, CoinbaseException {
        try {
            return ((OrderResponse) get(new URL(this._baseV1ApiUrl, "orders/" + idOrCustom), OrderResponse.class)).getOrder();
        } catch (MalformedURLException e) {
            throw new CoinbaseException("Invalid order id/custom");
        }
    }

    public OrdersResponse getOrders(int page) throws IOException, CoinbaseException {
        try {
            return (OrdersResponse) get(new URL(this._baseV1ApiUrl, "orders?page=" + page + (this._accountId != null ? "&account_id=" + this._accountId : "")), OrdersResponse.class);
        } catch (MalformedURLException e) {
            throw new CoinbaseException("Invalid  account id");
        }
    }

    public OrdersResponse getOrders() throws IOException, CoinbaseException {
        return getOrders(1);
    }

    public AddressesResponse getAddresses(int page) throws IOException, CoinbaseException {
        try {
            return (AddressesResponse) get(new URL(this._baseV1ApiUrl, "addresses?page=" + page + (this._accountId != null ? "&account_id=" + this._accountId : "")), AddressesResponse.class);
        } catch (MalformedURLException e) {
            throw new CoinbaseException("Invalid account id");
        }
    }

    public AddressesResponse getAddresses() throws IOException, CoinbaseException {
        return getAddresses(1);
    }

    public ContactsResponse getContacts(int page) throws IOException, CoinbaseException {
        try {
            return (ContactsResponse) get(new URL(this._baseV1ApiUrl, "contacts?page=" + page), ContactsResponse.class);
        } catch (MalformedURLException ex) {
            throw new AssertionError(ex);
        }
    }

    public ContactsResponse getContacts() throws IOException, CoinbaseException {
        return getContacts(1);
    }

    public ContactsResponse getContacts(String query, int page) throws IOException, CoinbaseException {
        try {
            return (ContactsResponse) get(new URL(this._baseV1ApiUrl, "contacts?page=" + page + "&query=" + URLEncoder.encode(query, "UTF-8")), ContactsResponse.class);
        } catch (MalformedURLException ex) {
            throw new AssertionError(ex);
        }
    }

    public ContactsResponse getContacts(String query) throws IOException, CoinbaseException {
        return getContacts(query, 1);
    }

    public Map<String, BigDecimal> getExchangeRates() throws IOException, CoinbaseException {
        try {
            return (Map) deserialize(doHttp(new URL(this._baseV1ApiUrl, "currencies/exchange_rates"), "GET", null), new TypeReference<HashMap<String, BigDecimal>>() {
            });
        } catch (MalformedURLException ex) {
            throw new AssertionError(ex);
        }
    }

    public List<CurrencyUnit> getSupportedCurrencies() throws IOException, CoinbaseException {
        try {
            List<List<String>> rawResponse = (List) deserialize(doHttp(new URL(this._baseV1ApiUrl, ApiConstants.CURRENCIES), "GET", null), new TypeReference<List<List<String>>>() {
            });
            List<CurrencyUnit> result = new ArrayList();
            for (List<String> currency : rawResponse) {
                try {
                    result.add(CurrencyUnit.getInstance((String) currency.get(1)));
                } catch (IllegalCurrencyException e) {
                }
            }
            return result;
        } catch (MalformedURLException ex) {
            throw new AssertionError(ex);
        }
    }

    public List<HistoricalPrice> getHistoricalPrices(int page) throws CoinbaseException, IOException {
        try {
            CSVReader reader = new CSVReader(new StringReader(doHttp(new URL(this._baseV1ApiUrl, "prices/historical?page=" + page), "GET", null)));
            ArrayList<HistoricalPrice> result = new ArrayList();
            while (true) {
                try {
                    String[] nextLine = reader.readNext();
                    if (nextLine != null) {
                        HistoricalPrice hp = new HistoricalPrice();
                        hp.setTime(DateTime.parse(nextLine[0]));
                        hp.setSpotPrice(Money.of(CurrencyUnit.USD, new BigDecimal(nextLine[1])));
                        result.add(hp);
                    } else {
                        try {
                            break;
                        } catch (IOException e) {
                        }
                    }
                } catch (IOException e2) {
                    throw new CoinbaseException("Error parsing csv response");
                } catch (Throwable th) {
                    try {
                        reader.close();
                    } catch (IOException e3) {
                    }
                }
            }
            reader.close();
            return result;
        } catch (MalformedURLException ex) {
            throw new AssertionError(ex);
        }
    }

    public List<HistoricalPrice> getHistoricalPrices() throws CoinbaseException, IOException {
        return getHistoricalPrices(1);
    }

    public Button createButton(Button button) throws CoinbaseException, IOException {
        try {
            URL buttonsUrl = new URL(this._baseV1ApiUrl, "buttons");
            Request request = newAccountSpecificRequest();
            request.setButton(button);
            return ((ButtonResponse) post(buttonsUrl, request, ButtonResponse.class)).getButton();
        } catch (MalformedURLException ex) {
            throw new AssertionError(ex);
        }
    }

    public Order createOrder(Button button) throws CoinbaseException, IOException {
        try {
            URL ordersUrl = new URL(this._baseV1ApiUrl, "orders");
            Request request = newAccountSpecificRequest();
            request.setButton(button);
            return ((OrderResponse) post(ordersUrl, request, OrderResponse.class)).getOrder();
        } catch (MalformedURLException ex) {
            throw new AssertionError(ex);
        }
    }

    public Order createOrderForButton(String buttonCode) throws CoinbaseException, IOException {
        try {
            return ((OrderResponse) post(new URL(this._baseV1ApiUrl, "buttons/" + buttonCode + "/create_order"), new Request(), OrderResponse.class)).getOrder();
        } catch (MalformedURLException e) {
            throw new CoinbaseException("Invalid button code");
        }
    }

    public PaymentMethodsResponse getPaymentMethods() throws IOException, CoinbaseException {
        try {
            return (PaymentMethodsResponse) get(new URL(this._baseV2ApiUrl, "payment-methods"), getV2VersionHeaders(), PaymentMethodsResponse.class);
        } catch (MalformedURLException ex) {
            throw new AssertionError(ex);
        }
    }

    public PaymentMethodResponse getPaymentMethod(String id) throws CoinbaseException, IOException {
        try {
            return (PaymentMethodResponse) get(new URL(this._baseV2ApiUrl, "payment-methods/" + id), getV2VersionHeaders(), PaymentMethodResponse.class);
        } catch (MalformedURLException ex) {
            throw new AssertionError(ex);
        }
    }

    public void deletePaymentMethod(String id) throws CoinbaseException, IOException {
        try {
            doHttp(new URL(this._baseV2ApiUrl, "payment-methods/" + id), "DELETE", null, getV2VersionHeaders());
        } catch (MalformedURLException ex) {
            throw new AssertionError(ex);
        }
    }

    public RecurringPaymentsResponse getSubscribers(int page) throws IOException, CoinbaseException {
        try {
            return (RecurringPaymentsResponse) get(new URL(this._baseV1ApiUrl, "subscribers?page=" + page), RecurringPaymentsResponse.class);
        } catch (MalformedURLException ex) {
            throw new AssertionError(ex);
        }
    }

    public RecurringPaymentsResponse getSubscribers() throws IOException, CoinbaseException {
        return getSubscribers(1);
    }

    public RecurringPaymentsResponse getRecurringPayments(int page) throws IOException, CoinbaseException {
        try {
            return (RecurringPaymentsResponse) get(new URL(this._baseV1ApiUrl, "recurring_payments?page=" + page), RecurringPaymentsResponse.class);
        } catch (MalformedURLException ex) {
            throw new AssertionError(ex);
        }
    }

    public RecurringPaymentsResponse getRecurringPayments() throws IOException, CoinbaseException {
        return getRecurringPayments(1);
    }

    public RecurringPayment getRecurringPayment(String id) throws CoinbaseException, IOException {
        try {
            return ((RecurringPaymentResponse) get(new URL(this._baseV1ApiUrl, "recurring_payments/" + id), RecurringPaymentResponse.class)).getRecurringPayment();
        } catch (MalformedURLException e) {
            throw new CoinbaseException("Invalid payment id");
        }
    }

    public RecurringPayment getSubscriber(String id) throws CoinbaseException, IOException {
        try {
            return ((RecurringPaymentResponse) get(new URL(this._baseV1ApiUrl, "subscribers/" + id), RecurringPaymentResponse.class)).getRecurringPayment();
        } catch (MalformedURLException e) {
            throw new CoinbaseException("Invalid subscriber id");
        }
    }

    public AddressResponse generateReceiveAddress(Address addressParams) throws CoinbaseException, IOException {
        try {
            URL generateAddressUrl = new URL(this._baseV1ApiUrl, "account/generate_receive_address");
            Request request = newAccountSpecificRequest();
            request.setAddress(addressParams);
            return (AddressResponse) post(generateAddressUrl, request, AddressResponse.class);
        } catch (MalformedURLException ex) {
            throw new AssertionError(ex);
        }
    }

    public AddressResponse generateReceiveAddress() throws CoinbaseException, IOException {
        return generateReceiveAddress(null);
    }

    public User createUser(User userParams) throws CoinbaseException, IOException {
        try {
            URL usersUrl = new URL(this._baseV1ApiUrl, ApiConstants.USERS);
            Request request = new Request();
            request.setUser(userParams);
            return ((UserResponse) post(usersUrl, request, UserResponse.class)).getUser();
        } catch (MalformedURLException ex) {
            throw new AssertionError(ex);
        }
    }

    public User createUser(User userParams, String clientId, String scope) throws CoinbaseException, IOException {
        try {
            URL usersUrl = new URL(this._baseV1ApiUrl, ApiConstants.USERS);
            Request request = new Request();
            request.setUser(userParams);
            request.setScopes(scope);
            request.setClientId(clientId);
            return ((UserResponse) post(usersUrl, request, UserResponse.class)).getUser();
        } catch (MalformedURLException ex) {
            throw new AssertionError(ex);
        }
    }

    public UserResponse createUserWithOAuth(User userParams, String clientId, String scope) throws CoinbaseException, IOException {
        try {
            URL usersUrl = new URL(this._baseV1ApiUrl, ApiConstants.USERS);
            Request request = new Request();
            request.setUser(userParams);
            request.setScopes(scope);
            request.setClientId(clientId);
            return (UserResponse) post(usersUrl, request, UserResponse.class);
        } catch (MalformedURLException ex) {
            throw new AssertionError(ex);
        }
    }

    public User updateUser(String userId, User userParams) throws CoinbaseException, IOException {
        try {
            URL userUrl = new URL(this._baseV1ApiUrl, "users/" + userId);
            Request request = new Request();
            request.setUser(userParams);
            return ((UserResponse) put(userUrl, request, UserResponse.class)).getUser();
        } catch (MalformedURLException e) {
            throw new CoinbaseException("Invalid user id");
        }
    }

    public Token createToken() throws CoinbaseException, IOException {
        try {
            return ((TokenResponse) post(new URL(this._baseV1ApiUrl, "tokens"), new Request(), TokenResponse.class)).getToken();
        } catch (MalformedURLException ex) {
            throw new AssertionError(ex);
        }
    }

    public void redeemToken(String tokenId) throws CoinbaseException, IOException {
        try {
            URL redeemTokenUrl = new URL(this._baseV1ApiUrl, "tokens/redeem");
            Request request = new Request();
            request.setTokenId(tokenId);
            post(redeemTokenUrl, request, Response.class);
        } catch (MalformedURLException ex) {
            throw new AssertionError(ex);
        }
    }

    public Application createApplication(Application applicationParams) throws CoinbaseException, IOException {
        try {
            URL applicationsUrl = new URL(this._baseV1ApiUrl, "oauth/applications");
            Request request = new Request();
            request.setApplication(applicationParams);
            return ((ApplicationResponse) post(applicationsUrl, request, ApplicationResponse.class)).getApplication();
        } catch (MalformedURLException ex) {
            throw new AssertionError(ex);
        }
    }

    public ApplicationsResponse getApplications() throws IOException, CoinbaseException {
        try {
            return (ApplicationsResponse) get(new URL(this._baseV1ApiUrl, "oauth/applications"), ApplicationsResponse.class);
        } catch (MalformedURLException ex) {
            throw new AssertionError(ex);
        }
    }

    public Application getApplication(String id) throws IOException, CoinbaseException {
        try {
            return ((ApplicationResponse) get(new URL(this._baseV1ApiUrl, "oauth/applications/" + id), ApplicationResponse.class)).getApplication();
        } catch (MalformedURLException e) {
            throw new CoinbaseException("Invalid application id");
        }
    }

    public Report createReport(Report reportParams) throws CoinbaseException, IOException {
        try {
            URL reportsUrl = new URL(this._baseV1ApiUrl, "reports");
            Request request = newAccountSpecificRequest();
            request.setReport(reportParams);
            return ((ReportResponse) post(reportsUrl, request, ReportResponse.class)).getReport();
        } catch (MalformedURLException ex) {
            throw new AssertionError(ex);
        }
    }

    public Report getReport(String reportId) throws IOException, CoinbaseException {
        try {
            return ((ReportResponse) get(new URL(this._baseV1ApiUrl, "reports/" + reportId + (this._accountId != null ? "?account_id=" + this._accountId : "")), ReportResponse.class)).getReport();
        } catch (MalformedURLException e) {
            throw new CoinbaseException("Invalid report id");
        }
    }

    public ReportsResponse getReports(int page) throws IOException, CoinbaseException {
        try {
            return (ReportsResponse) get(new URL(this._baseV1ApiUrl, "reports?page=" + page + (this._accountId != null ? "&account_id=" + this._accountId : "")), ReportsResponse.class);
        } catch (MalformedURLException e) {
            throw new CoinbaseException("Invalid account id");
        }
    }

    public ReportsResponse getReports() throws IOException, CoinbaseException {
        return getReports(1);
    }

    public AccountChangesResponse getAccountChanges(int page) throws IOException, CoinbaseException {
        try {
            return (AccountChangesResponse) get(new URL(this._baseV1ApiUrl, "account_changes?page=" + page + (this._accountId != null ? "&account_id=" + this._accountId : "")), AccountChangesResponse.class);
        } catch (MalformedURLException e) {
            throw new CoinbaseException("Invalid account id");
        }
    }

    public AccountChangesResponse getAccountChanges() throws IOException, CoinbaseException {
        return getAccountChanges(1);
    }

    public String getAuthCode(OAuthCodeRequest request) throws CoinbaseException, IOException {
        try {
            return ((OAuthCodeResponse) post(new URL(this._baseOAuthUrl, "authorize/with_credentials"), request, OAuthCodeResponse.class)).getCode();
        } catch (MalformedURLException ex) {
            throw new AssertionError(ex);
        }
    }

    public OAuthTokensResponse getTokens(String clientId, String clientSecret, String authCode, String redirectUri) throws UnauthorizedDeviceException, CoinbaseException, IOException {
        try {
            URL tokenUrl = new URL(this._baseOAuthUrl, "token");
            OAuthTokensRequest request = new OAuthTokensRequest();
            request.setClientId(clientId);
            request.setClientSecret(clientSecret);
            request.setGrantType(GrantType.AUTHORIZATION_CODE);
            request.setCode(authCode);
            if (redirectUri == null) {
                redirectUri = "2_legged";
            }
            request.setRedirectUri(redirectUri);
            return (OAuthTokensResponse) post(tokenUrl, request, OAuthTokensResponse.class);
        } catch (MalformedURLException ex) {
            throw new AssertionError(ex);
        }
    }

    public void revokeToken() throws CoinbaseException, IOException {
        if (this._accessToken == null) {
            throw new CoinbaseException("This client must have been initialized with an access token in order to call revokeToken()");
        }
        try {
            URL revokeTokenUrl = new URL(this._baseOAuthUrl, ApiConstants.REVOKE);
            RevokeTokenRequest request = new RevokeTokenRequest();
            request.setToken(this._accessToken);
            post(revokeTokenUrl, request, Response.class);
            this._accessToken = null;
        } catch (MalformedURLException ex) {
            throw new AssertionError(ex);
        }
    }

    public OAuthTokensResponse refreshTokens(String clientId, String clientSecret, String refreshToken) throws CoinbaseException, IOException {
        try {
            URL tokenUrl = new URL(this._baseOAuthUrl, "token");
            OAuthTokensRequest request = new OAuthTokensRequest();
            request.setClientId(clientId);
            request.setClientSecret(clientSecret);
            request.setGrantType(GrantType.REFRESH_TOKEN);
            request.setRefreshToken(refreshToken);
            return (OAuthTokensResponse) post(tokenUrl, request, OAuthTokensResponse.class);
        } catch (MalformedURLException ex) {
            throw new AssertionError(ex);
        }
    }

    public void sendSMS(String clientId, String clientSecret, String email, String password) throws CoinbaseException, IOException {
        try {
            URL smsUrl = new URL(this._baseOAuthUrl, "authorize/with_credentials/sms_token");
            OAuthCodeRequest request = new OAuthCodeRequest();
            request.setClientId(clientId);
            request.setClientSecret(clientSecret);
            request.setUsername(email);
            request.setPassword(password);
            post(smsUrl, request, Response.class);
        } catch (MalformedURLException ex) {
            throw new AssertionError(ex);
        }
    }

    public Uri getAuthorizationUri(OAuthCodeRequest params) throws CoinbaseException {
        try {
            Uri.Builder uriBuilder = Uri.parse(new URL(this._baseOAuthUrl, "authorize").toURI().toString()).buildUpon();
            uriBuilder.appendQueryParameter("response_type", "code");
            if (params.getClientId() != null) {
                uriBuilder.appendQueryParameter("client_id", params.getClientId());
                if (params.getRedirectUri() != null) {
                    uriBuilder.appendQueryParameter(ApiConstants.REDIRECT_URI, params.getRedirectUri());
                    if (params.getScope() != null) {
                        uriBuilder.appendQueryParameter(ApiConstants.SCOPE, params.getScope());
                        if (params.getMeta() != null) {
                            Meta meta = params.getMeta();
                            if (meta.getName() != null) {
                                uriBuilder.appendQueryParameter("meta[name]", meta.getName());
                            }
                            if (meta.getSendLimitAmount() != null) {
                                Money sendLimit = meta.getSendLimitAmount();
                                uriBuilder.appendQueryParameter("meta[send_limit_amount]", sendLimit.getAmount().toPlainString());
                                uriBuilder.appendQueryParameter("meta[send_limit_currency]", sendLimit.getCurrencyUnit().getCurrencyCode());
                                if (meta.getSendLimitPeriod() != null) {
                                    uriBuilder.appendQueryParameter("meta[send_limit_period]", meta.getSendLimitPeriod().toString());
                                }
                            }
                        }
                        return uriBuilder.build();
                    }
                    throw new CoinbaseException("scope is required");
                }
                throw new CoinbaseException("redirect_uri is required");
            }
            throw new CoinbaseException("client_id is required");
        } catch (URISyntaxException ex) {
            throw new AssertionError(ex);
        } catch (MalformedURLException ex2) {
            throw new AssertionError(ex2);
        }
    }

    public boolean verifyCallback(String body, String signature) {
        return this._callbackVerifier.verifyCallback(body, signature);
    }

    protected void doHmacAuthentication(URL url, String body, HttpsURLConnection conn) throws IOException {
        String nonce = String.valueOf(System.currentTimeMillis());
        StringBuilder append = new StringBuilder().append(nonce).append(url.toString());
        if (body == null) {
            body = "";
        }
        String message = append.append(body).toString();
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(this._apiSecret.getBytes(), "HmacSHA256"));
            String signature = new String(Hex.encodeHex(mac.doFinal(message.getBytes())));
            conn.setRequestProperty("ACCESS_KEY", this._apiKey);
            conn.setRequestProperty("ACCESS_SIGNATURE", signature);
            conn.setRequestProperty("ACCESS_NONCE", nonce);
        } catch (Throwable t) {
            IOException iOException = new IOException(t);
        }
    }

    protected void doAccessTokenAuthentication(HttpsURLConnection conn) {
        conn.setRequestProperty("Authorization", "Bearer " + this._accessToken);
    }

    protected String doHttp(URL url, String method, Object requestBody) throws IOException, CoinbaseException {
        return doHttp(url, method, requestBody, null);
    }

    protected String doHttp(URL url, String method, Object requestBody, HashMap<String, String> headers) throws IOException, CoinbaseException {
        String errorBody;
        URLConnection urlConnection = url.openConnection();
        if (urlConnection instanceof HttpsURLConnection) {
            HttpsURLConnection conn = (HttpsURLConnection) urlConnection;
            conn.setSSLSocketFactory(this._socketFactory);
            conn.setRequestMethod(method);
            String body = null;
            if (requestBody != null) {
                body = objectMapper.writeValueAsString(requestBody);
                conn.setRequestProperty("Content-Type", "application/json");
            }
            if (this._accessToken != null) {
                doAccessTokenAuthentication(conn);
            } else if (!(this._apiKey == null || this._apiSecret == null)) {
                doHmacAuthentication(url, body, conn);
            }
            if (headers != null) {
                for (String key : headers.keySet()) {
                    conn.setRequestProperty(key, (String) headers.get(key));
                }
            }
            if (body != null) {
                conn.setDoOutput(true);
                OutputStream outputStream = conn.getOutputStream();
                try {
                    outputStream.write(body.getBytes(Charset.forName("UTF-8")));
                } finally {
                    outputStream.close();
                }
            }
            InputStream is = null;
            InputStream es = null;
            try {
                is = conn.getInputStream();
                String str = IOUtils.toString(is, "UTF-8");
                if (is != null) {
                    is.close();
                }
                if (es != null) {
                    es.close();
                }
                return str;
            } catch (Exception e) {
                throw new CoinbaseException(errorBody);
            } catch (IOException e2) {
                if (402 == conn.getResponseCode()) {
                    throw new TwoFactorRequiredException();
                }
                es = conn.getErrorStream();
                errorBody = null;
                if (es != null) {
                    errorBody = IOUtils.toString(es, "UTF-8");
                    if (errorBody != null && conn.getContentType().toLowerCase().contains("json")) {
                        Response coinbaseResponse;
                        try {
                            coinbaseResponse = (Response) deserialize(errorBody, ResponseV1.class);
                        } catch (Exception e3) {
                            coinbaseResponse = (Response) deserialize(errorBody, ResponseV2.class);
                        }
                        handleErrors(coinbaseResponse);
                    }
                }
                if (401 == conn.getResponseCode()) {
                    throw new UnauthorizedException(errorBody);
                }
                throw e2;
            } catch (Throwable th) {
                if (is != null) {
                    is.close();
                }
                if (es != null) {
                    es.close();
                }
            }
        } else {
            throw new RuntimeException("Custom Base URL must return javax.net.ssl.HttpsURLConnection on openConnection.");
        }
    }

    protected static <T> T deserialize(String json, Class<T> clazz) throws IOException {
        return objectMapper.readValue(json, (Class) clazz);
    }

    protected static <T> T deserialize(String json, TypeReference<T> typeReference) throws IOException {
        return objectMapper.readValue(json, (TypeReference) typeReference);
    }

    protected <T extends Response> T get(URL url, Class<T> responseClass) throws IOException, CoinbaseException {
        return get(url, null, responseClass);
    }

    protected <T extends Response> T get(URL url, HashMap<String, String> headers, Class<T> responseClass) throws IOException, CoinbaseException {
        return handleErrors((Response) deserialize(doHttp(url, "GET", null, headers), (Class) responseClass));
    }

    protected <T extends Response> T post(URL url, Object entity, Class<T> responseClass) throws CoinbaseException, IOException {
        return handleErrors((Response) deserialize(doHttp(url, "POST", entity), (Class) responseClass));
    }

    protected <T extends Response> T put(URL url, Object entity, Class<T> responseClass) throws CoinbaseException, IOException {
        return handleErrors((Response) deserialize(doHttp(url, "PUT", entity), (Class) responseClass));
    }

    protected <T extends Response> T delete(URL url, Class<T> responseClass) throws CoinbaseException, IOException {
        return handleErrors((Response) deserialize(doHttp(url, "DELETE", null), (Class) responseClass));
    }

    protected static <T extends Response> T handleErrors(T response) throws CoinbaseException {
        String errors = response.getErrors();
        if (errors != null) {
            if (errors.contains("device_confirmation_required")) {
                throw new UnauthorizedDeviceException();
            } else if (errors.contains(ApiConstants.TWO_FACTOR_REQUIRED)) {
                throw new TwoFactorRequiredException();
            } else if (errors.contains(ApiConstants.TWO_FACTOR_INCORRECT)) {
                throw new TwoFactorIncorrectException();
            } else if (errors.contains(ApiConstants.INCORRECT_CREDENTIALS)) {
                throw new CredentialsIncorrectException();
            } else {
                throw new CoinbaseException(response.getErrors());
            }
        } else if (response.isSuccess() == null || response.isSuccess().booleanValue()) {
            return response;
        } else {
            throw new CoinbaseException("Unknown error");
        }
    }

    protected Request newAccountSpecificRequest() {
        Request request = new Request();
        if (this._accountId != null) {
            request.setAccountId(this._accountId);
        }
        return request;
    }

    protected HashMap<String, String> getV2VersionHeaders() {
        HashMap<String, String> headers = new HashMap();
        headers.put("CB-VERSION", ApiConstants.VERSION);
        headers.put("CB-CLIENT", getPackageVersionName());
        return headers;
    }

    protected Interceptor buildOAuthInterceptor() {
        return new Interceptor() {
            public okhttp3.Response intercept(Chain chain) throws IOException {
                return chain.proceed(chain.request().newBuilder().addHeader("Authorization", "Bearer " + Coinbase.this._accessToken).build());
            }
        };
    }

    protected Interceptor buildHmacAuthInterceptor() {
        return new Interceptor() {
            public okhttp3.Response intercept(Chain chain) throws IOException {
                okhttp3.Request request = chain.request();
                String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
                String method = request.method().toUpperCase();
                String path = request.url().url().getFile();
                String body = "";
                if (request.body() != null) {
                    okhttp3.Request requestCopy = request.newBuilder().build();
                    Buffer buffer = new Buffer();
                    requestCopy.body().writeTo(buffer);
                    body = buffer.readUtf8();
                }
                String message = timestamp + method + path + body;
                try {
                    Mac mac = Mac.getInstance("HmacSHA256");
                    mac.init(new SecretKeySpec(Coinbase.this._apiSecret.getBytes(), "HmacSHA256"));
                    return chain.proceed(request.newBuilder().addHeader("CB-ACCESS-KEY", Coinbase.this._apiKey).addHeader("CB-ACCESS_SIGN", new String(Hex.encodeHex(mac.doFinal(message.getBytes())))).addHeader("CB-ACCESS-TIMESTAMP", timestamp).build());
                } catch (Throwable t) {
                    IOException iOException = new IOException(t);
                }
            }
        };
    }

    private String getPackageVersionName() {
        String packageName = "";
        String versionName = "";
        if (this._context != null) {
            packageName = this._context.getPackageName();
        }
        try {
            versionName = getVersionName() + "/" + getVersionCode();
        } catch (Throwable th) {
        }
        return packageName + "/" + versionName;
    }

    protected String getVersionName() {
        return String.valueOf(BuildConfig.VERSION_NAME);
    }

    protected String getVersionCode() {
        return String.valueOf(1);
    }

    protected Interceptor buildVersionInterceptor() {
        return new Interceptor() {
            public okhttp3.Response intercept(Chain chain) throws IOException {
                return chain.proceed(chain.request().newBuilder().addHeader("CB-VERSION", ApiConstants.VERSION).addHeader("CB-CLIENT", Coinbase.this.getPackageVersionName()).addHeader("X-App-Version", Coinbase.this.getVersionName()).addHeader("X-App-Build-Number", Coinbase.this.getVersionCode()).build());
            }
        };
    }

    protected Interceptor languageInterceptor() {
        return new Interceptor() {
            public okhttp3.Response intercept(Chain chain) throws IOException {
                return chain.proceed(chain.request().newBuilder().addHeader("Accept-Language", Locale.getDefault().getLanguage()).build());
            }
        };
    }

    protected Interceptor deviceInfoInterceptor() {
        return new Interceptor() {
            public okhttp3.Response intercept(Chain chain) throws IOException {
                return chain.proceed(chain.request().newBuilder().build());
            }
        };
    }

    protected Interceptor networkSniffingInterceptor() {
        return Coinbase$$Lambda$1.lambdaFactory$();
    }

    protected Interceptor loggingInterceptor() {
        return Coinbase$$Lambda$4.lambdaFactory$();
    }

    protected Pair<ApiInterface, Retrofit> getOAuthApiService() {
        return getService(this._baseOAuthUrl.toString());
    }

    protected Pair<ApiInterface, Retrofit> getApiService() {
        return getService(this._baseV2ApiUrl.toString());
    }

    private synchronized Pair<ApiInterface, Retrofit> getService(String url) {
        Pair<ApiInterface, Retrofit> pair;
        if (this.mInitializedServices.containsKey(url)) {
            pair = (Pair) this.mInitializedServices.get(url);
        } else {
            Builder clientBuilder = generateClientBuilder(this._sslContext);
            if (this._accessToken != null) {
                clientBuilder.addInterceptor(buildOAuthInterceptor());
            }
            clientBuilder.addInterceptor(buildVersionInterceptor());
            clientBuilder.addInterceptor(languageInterceptor());
            clientBuilder.addInterceptor(deviceInfoInterceptor());
            clientBuilder.addInterceptor(this._cache.createInterceptor());
            clientBuilder.addInterceptor(loggingInterceptor());
            clientBuilder.addNetworkInterceptor(networkSniffingInterceptor());
            Retrofit retrofit = new Retrofit.Builder().baseUrl(url).client(clientBuilder.build()).addConverterFactory(GsonConverterFactory.create()).build();
            Pair<ApiInterface, Retrofit> servicePair = new Pair((ApiInterface) retrofit.create(ApiInterface.class), retrofit);
            this.mInitializedServices.put(url, servicePair);
            pair = servicePair;
        }
        return pair;
    }

    protected Pair<ApiInterfaceRx, Retrofit> getOAuthApiServiceRx() {
        return getServiceRx(this._baseOAuthUrl.toString());
    }

    protected Pair<ApiInterfaceRx, Retrofit> getApiServiceRx() {
        return getServiceRx(this._baseV2ApiUrl.toString());
    }

    private synchronized Pair<ApiInterfaceRx, Retrofit> getServiceRx(String url) {
        Pair<ApiInterfaceRx, Retrofit> pair;
        if (this.mInitializedServicesRx.containsKey(url)) {
            pair = (Pair) this.mInitializedServicesRx.get(url);
        } else {
            Factory create;
            Builder clientBuilder = generateClientBuilder(this._sslContext);
            if (this._accessToken != null) {
                clientBuilder.addInterceptor(buildOAuthInterceptor());
            }
            clientBuilder.addInterceptor(buildVersionInterceptor());
            clientBuilder.addInterceptor(languageInterceptor());
            clientBuilder.addInterceptor(deviceInfoInterceptor());
            clientBuilder.addInterceptor(this._cache.createInterceptor());
            clientBuilder.addInterceptor(loggingInterceptor());
            clientBuilder.addNetworkInterceptor(networkSniffingInterceptor());
            Retrofit.Builder client = new Retrofit.Builder().baseUrl(url).client(clientBuilder.build());
            if (this._backgroundScheduler == null) {
                create = RxJavaCallAdapterFactory.create();
            } else {
                create = RxJavaCallAdapterFactory.createWithScheduler(this._backgroundScheduler);
            }
            Retrofit retrofit = client.addCallAdapterFactory(create).addConverterFactory(GsonConverterFactory.create()).build();
            Pair<ApiInterfaceRx, Retrofit> servicePair = new Pair((ApiInterfaceRx) retrofit.create(ApiInterfaceRx.class), retrofit);
            this.mInitializedServicesRx.put(url, servicePair);
            pair = servicePair;
        }
        return pair;
    }

    public Call refreshTokens(String clientId, String clientSecret, String refreshToken, final CallbackWithRetrofit<AccessToken> callback) {
        HashMap<String, Object> params = new HashMap();
        params.put("client_id", clientId);
        params.put("client_secret", clientSecret);
        params.put(ApiConstants.REFRESH_TOKEN, refreshToken);
        params.put(ApiConstants.GRANT_TYPE, ApiConstants.REFRESH_TOKEN);
        final Pair<ApiInterface, Retrofit> apiRetrofitPair = getOAuthApiService();
        Call call = ((ApiInterface) apiRetrofitPair.first).refreshTokens(params);
        call.enqueue(new Callback<AccessToken>() {
            public void onResponse(Call<AccessToken> call, retrofit2.Response<AccessToken> response) {
                if (callback != null) {
                    callback.onResponse(call, response, (Retrofit) apiRetrofitPair.second);
                }
                if (response != null && response.body() != null) {
                    Coinbase.this._accessToken = ((AccessToken) response.body()).getAccessToken();
                }
            }

            public void onFailure(Call<AccessToken> call, Throwable t) {
                if (callback != null) {
                    callback.onFailure(call, t);
                }
            }
        });
        return call;
    }

    public Observable<Pair<retrofit2.Response<AccessToken>, Retrofit>> refreshTokensRx(String clientId, String clientSecret, String refreshToken) {
        HashMap<String, Object> params = getRefreshTokensParams(clientId, clientSecret, refreshToken);
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getOAuthApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).refreshTokens(params), Observable.just(apiRetrofitPair.second), Coinbase$$Lambda$5.lambdaFactory$());
    }

    public Call revokeToken(final CallbackWithRetrofit<Void> callback) {
        if (this._accessToken == null) {
            Log.w("Coinbase Error", "This client must have been initialized with an access token in order to call revokeToken()");
            return null;
        }
        HashMap<String, Object> params = new HashMap();
        params.put("token", this._accessToken);
        final Pair<ApiInterface, Retrofit> apiRetrofitPair = getOAuthApiService();
        Call call = ((ApiInterface) apiRetrofitPair.first).revokeToken(params);
        call.enqueue(new Callback<Void>() {
            public void onResponse(Call<Void> call, retrofit2.Response<Void> response) {
                if (response.isSuccessful()) {
                    Coinbase.this._accessToken = null;
                }
                if (callback != null) {
                    callback.onResponse(call, response, (Retrofit) apiRetrofitPair.second);
                }
            }

            public void onFailure(Call<Void> call, Throwable t) {
                if (callback != null) {
                    callback.onFailure(call, t);
                }
            }
        });
        return call;
    }

    public Observable<Pair<retrofit2.Response<Void>, Retrofit>> revokeTokenRx() {
        if (this._accessToken == null) {
            Log.w("Coinbase Error", "This client must have been initialized with an access token in order to call revokeToken()");
            return null;
        }
        HashMap<String, Object> params = new HashMap();
        params.put("token", this._accessToken);
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getOAuthApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).revokeToken(params), Observable.just(apiRetrofitPair.second), Coinbase$$Lambda$6.lambdaFactory$(this));
    }

    public Call getUser(final CallbackWithRetrofit<com.coinbase.v2.models.user.User> callback) {
        final Pair<ApiInterface, Retrofit> apiRetrofitPair = getApiService();
        Call call = ((ApiInterface) apiRetrofitPair.first).getUser();
        call.enqueue(new Callback<com.coinbase.v2.models.user.User>() {
            public void onResponse(Call<com.coinbase.v2.models.user.User> call, retrofit2.Response<com.coinbase.v2.models.user.User> response) {
                if (callback != null) {
                    callback.onResponse(call, response, (Retrofit) apiRetrofitPair.second);
                }
            }

            public void onFailure(Call<com.coinbase.v2.models.user.User> call, Throwable t) {
                if (callback != null) {
                    callback.onFailure(call, t);
                }
            }
        });
        return call;
    }

    public Observable<Pair<retrofit2.Response<com.coinbase.v2.models.user.User>, Retrofit>> getUserRx() {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).getUser(), Observable.just(apiRetrofitPair.second), Coinbase$$Lambda$7.lambdaFactory$());
    }

    public Call updateUser(String name, String timeZone, String nativeCurrency, final CallbackWithRetrofit<com.coinbase.v2.models.user.User> callback) {
        HashMap<String, Object> params = getUpdateUserParams(name, timeZone, nativeCurrency);
        final Pair<ApiInterface, Retrofit> apiRetrofitPair = getApiService();
        Call call = ((ApiInterface) apiRetrofitPair.first).updateUser(params);
        call.enqueue(new Callback<com.coinbase.v2.models.user.User>() {
            public void onResponse(Call<com.coinbase.v2.models.user.User> call, retrofit2.Response<com.coinbase.v2.models.user.User> response) {
                if (callback != null) {
                    callback.onResponse(call, response, (Retrofit) apiRetrofitPair.second);
                }
            }

            public void onFailure(Call<com.coinbase.v2.models.user.User> call, Throwable t) {
                if (callback != null) {
                    callback.onFailure(call, t);
                }
            }
        });
        return call;
    }

    public Observable<Pair<retrofit2.Response<com.coinbase.v2.models.user.User>, Retrofit>> updateUserRx(String name, String timeZone, String nativeCurrency) {
        HashMap<String, Object> params = getUpdateUserParams(name, timeZone, nativeCurrency);
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).updateUser(params), Observable.just(apiRetrofitPair.second), Coinbase$$Lambda$8.lambdaFactory$());
    }

    public Call getAccount(String accountId, final CallbackWithRetrofit<com.coinbase.v2.models.account.Account> callback) {
        final Pair<ApiInterface, Retrofit> apiRetrofitPair = getApiService();
        Call call = ((ApiInterface) apiRetrofitPair.first).getAccount(accountId);
        call.enqueue(new Callback<com.coinbase.v2.models.account.Account>() {
            public void onResponse(Call<com.coinbase.v2.models.account.Account> call, retrofit2.Response<com.coinbase.v2.models.account.Account> response) {
                if (callback != null) {
                    callback.onResponse(call, response, (Retrofit) apiRetrofitPair.second);
                }
            }

            public void onFailure(Call<com.coinbase.v2.models.account.Account> call, Throwable t) {
                if (callback != null) {
                    callback.onFailure(call, t);
                }
            }
        });
        return call;
    }

    public Observable<Pair<retrofit2.Response<com.coinbase.v2.models.account.Account>, Retrofit>> getAccountRx(String accountId) {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).getAccount(accountId), Observable.just(apiRetrofitPair.second), Coinbase$$Lambda$9.lambdaFactory$());
    }

    public Call getAccounts(HashMap<String, Object> options, final CallbackWithRetrofit<Accounts> callback) {
        final Pair<ApiInterface, Retrofit> apiRetrofitPair = getApiService();
        Call call = ((ApiInterface) apiRetrofitPair.first).getAccounts(cleanQueryMap(options));
        call.enqueue(new Callback<Accounts>() {
            public void onResponse(Call<Accounts> call, retrofit2.Response<Accounts> response) {
                if (callback != null) {
                    callback.onResponse(call, response, (Retrofit) apiRetrofitPair.second);
                }
            }

            public void onFailure(Call<Accounts> call, Throwable t) {
                if (callback != null) {
                    callback.onFailure(call, t);
                }
            }
        });
        return call;
    }

    public Observable<Pair<retrofit2.Response<Accounts>, Retrofit>> getAccountsRx(HashMap<String, Object> options) {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).getAccounts(cleanQueryMap(options)), Observable.just(apiRetrofitPair.second), Coinbase$$Lambda$10.lambdaFactory$());
    }

    public Call createAccount(HashMap<String, Object> options, final CallbackWithRetrofit<com.coinbase.v2.models.account.Account> callback) {
        final Pair<ApiInterface, Retrofit> apiRetrofitPair = getApiService();
        Call call = ((ApiInterface) apiRetrofitPair.first).createAccount(options);
        call.enqueue(new Callback<com.coinbase.v2.models.account.Account>() {
            public void onResponse(Call<com.coinbase.v2.models.account.Account> call, retrofit2.Response<com.coinbase.v2.models.account.Account> response) {
                if (callback != null) {
                    callback.onResponse(call, response, (Retrofit) apiRetrofitPair.second);
                }
            }

            public void onFailure(Call<com.coinbase.v2.models.account.Account> call, Throwable t) {
                if (callback != null) {
                    callback.onFailure(call, t);
                }
            }
        });
        return call;
    }

    public Observable<Pair<retrofit2.Response<com.coinbase.v2.models.account.Account>, Retrofit>> createAccountRx(HashMap<String, Object> options) {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).createAccount(options), Observable.just(apiRetrofitPair.second), Coinbase$$Lambda$11.lambdaFactory$());
    }

    public Call setAccountPrimary(String accountId, final CallbackWithRetrofit<Void> callback) {
        final Pair<ApiInterface, Retrofit> apiRetrofitPair = getApiService();
        Call call = ((ApiInterface) apiRetrofitPair.first).setAccountPrimary(accountId);
        call.enqueue(new Callback() {
            public void onResponse(Call call, retrofit2.Response response) {
                if (callback != null) {
                    callback.onResponse(call, response, (Retrofit) apiRetrofitPair.second);
                }
            }

            public void onFailure(Call call, Throwable t) {
                if (callback != null) {
                    callback.onFailure(call, t);
                }
            }
        });
        return call;
    }

    public Observable<Pair<retrofit2.Response<Void>, Retrofit>> setAccountPrimaryRx(String accountId) {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).setAccountPrimary(accountId), Observable.just(apiRetrofitPair.second), Coinbase$$Lambda$12.lambdaFactory$());
    }

    public Call updateAccount(String accountId, HashMap<String, Object> options, final CallbackWithRetrofit<com.coinbase.v2.models.account.Account> callback) {
        final Pair<ApiInterface, Retrofit> apiRetrofitPair = getApiService();
        Call call = ((ApiInterface) apiRetrofitPair.first).updateAccount(accountId, options);
        call.enqueue(new Callback<com.coinbase.v2.models.account.Account>() {
            public void onResponse(Call<com.coinbase.v2.models.account.Account> call, retrofit2.Response<com.coinbase.v2.models.account.Account> response) {
                if (callback != null) {
                    callback.onResponse(call, response, (Retrofit) apiRetrofitPair.second);
                }
            }

            public void onFailure(Call<com.coinbase.v2.models.account.Account> call, Throwable t) {
                if (callback != null) {
                    callback.onFailure(call, t);
                }
            }
        });
        return call;
    }

    public Observable<Pair<retrofit2.Response<com.coinbase.v2.models.account.Account>, Retrofit>> updateAccountRx(String accountId, HashMap<String, Object> options) {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).updateAccount(accountId, options), Observable.just(apiRetrofitPair.second), Coinbase$$Lambda$13.lambdaFactory$());
    }

    public Call deleteAccount(String accountId, final CallbackWithRetrofit<Void> callback) {
        final Pair<ApiInterface, Retrofit> apiRetrofitPair = getApiService();
        Call call = ((ApiInterface) apiRetrofitPair.first).deleteAccount(accountId);
        call.enqueue(new Callback() {
            public void onResponse(Call call, retrofit2.Response response) {
                if (callback != null) {
                    callback.onResponse(call, response, (Retrofit) apiRetrofitPair.second);
                }
            }

            public void onFailure(Call call, Throwable t) {
                if (callback != null) {
                    callback.onFailure(call, t);
                }
            }
        });
        return call;
    }

    public Observable<Pair<retrofit2.Response<Void>, Retrofit>> deleteAccountRx(String accountId) {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).deleteAccount(accountId), Observable.just(apiRetrofitPair.second), Coinbase$$Lambda$14.lambdaFactory$());
    }

    public Call getTransactions(String accountId, HashMap<String, Object> options, List<String> expandOptions, final CallbackWithRetrofit<Transactions> callback) {
        final Pair<ApiInterface, Retrofit> apiRetrofitPair = getApiService();
        Call call = ((ApiInterface) apiRetrofitPair.first).getTransactions(accountId, expandOptions, cleanQueryMap(options));
        call.enqueue(new Callback<Transactions>() {
            public void onResponse(Call<Transactions> call, retrofit2.Response<Transactions> response) {
                if (callback != null) {
                    callback.onResponse(call, response, (Retrofit) apiRetrofitPair.second);
                }
            }

            public void onFailure(Call<Transactions> call, Throwable t) {
                if (callback != null) {
                    callback.onFailure(call, t);
                }
            }
        });
        return call;
    }

    public Observable<Pair<retrofit2.Response<Transactions>, Retrofit>> getTransactionsRx(String accountId, HashMap<String, Object> options, List<String> expandOptions) {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).getTransactions(accountId, expandOptions, cleanQueryMap(options)), Observable.just(apiRetrofitPair.second), Coinbase$$Lambda$15.lambdaFactory$());
    }

    public Call getTransaction(String accountId, String transactionId, final CallbackWithRetrofit<com.coinbase.v2.models.transactions.Transaction> callback) {
        final Pair<ApiInterface, Retrofit> apiRetrofitPair = getApiService();
        Call call = ((ApiInterface) apiRetrofitPair.first).getTransaction(accountId, transactionId, getTransactionExpandOptions());
        call.enqueue(new Callback<com.coinbase.v2.models.transactions.Transaction>() {
            public void onResponse(Call<com.coinbase.v2.models.transactions.Transaction> call, retrofit2.Response<com.coinbase.v2.models.transactions.Transaction> response) {
                if (callback != null) {
                    callback.onResponse(call, response, (Retrofit) apiRetrofitPair.second);
                }
            }

            public void onFailure(Call<com.coinbase.v2.models.transactions.Transaction> call, Throwable t) {
                if (callback != null) {
                    callback.onFailure(call, t);
                }
            }
        });
        return call;
    }

    public Observable<Pair<retrofit2.Response<com.coinbase.v2.models.transactions.Transaction>, Retrofit>> getTransactionRx(String accountId, String transactionId) {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).getTransaction(accountId, transactionId, getTransactionExpandOptions()), Observable.just(apiRetrofitPair.second), Coinbase$$Lambda$16.lambdaFactory$());
    }

    public Call completeRequest(String accountId, String transactionId, final CallbackWithRetrofit<Void> callback) {
        final Pair<ApiInterface, Retrofit> apiRetrofitPair = getApiService();
        Call call = ((ApiInterface) apiRetrofitPair.first).completeRequest(accountId, transactionId);
        call.enqueue(new Callback<Void>() {
            public void onResponse(Call<Void> call, retrofit2.Response<Void> response) {
                if (callback != null) {
                    callback.onResponse(call, response, (Retrofit) apiRetrofitPair.second);
                }
            }

            public void onFailure(Call<Void> call, Throwable t) {
                if (callback != null) {
                    callback.onFailure(call, t);
                }
            }
        });
        return call;
    }

    public Observable<Pair<retrofit2.Response<Void>, Retrofit>> completeRequestRx(String accountId, String transactionId) {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).completeRequest(accountId, transactionId), Observable.just(apiRetrofitPair.second), Coinbase$$Lambda$17.lambdaFactory$());
    }

    public Call resendRequest(String accountId, String transactionId, final CallbackWithRetrofit<Void> callback) {
        final Pair<ApiInterface, Retrofit> apiRetrofitPair = getApiService();
        Call call = ((ApiInterface) apiRetrofitPair.first).resendRequest(accountId, transactionId);
        call.enqueue(new Callback<Void>() {
            public void onResponse(Call<Void> call, retrofit2.Response<Void> response) {
                if (callback != null) {
                    callback.onResponse(call, response, (Retrofit) apiRetrofitPair.second);
                }
            }

            public void onFailure(Call<Void> call, Throwable t) {
                if (callback != null) {
                    callback.onFailure(call, t);
                }
            }
        });
        return call;
    }

    public Observable<Pair<retrofit2.Response<Void>, Retrofit>> resendRequestRx(String accountId, String transactionId) {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).resendRequest(accountId, transactionId), Observable.just(apiRetrofitPair.second), Coinbase$$Lambda$18.lambdaFactory$());
    }

    public Call cancelRequest(String accountId, String transactionId, final CallbackWithRetrofit<Void> callback) {
        final Pair<ApiInterface, Retrofit> apiRetrofitPair = getApiService();
        Call call = ((ApiInterface) apiRetrofitPair.first).cancelTransaction(accountId, transactionId);
        call.enqueue(new Callback<Void>() {
            public void onResponse(Call<Void> call, retrofit2.Response<Void> response) {
                if (callback != null) {
                    callback.onResponse(call, response, (Retrofit) apiRetrofitPair.second);
                }
            }

            public void onFailure(Call<Void> call, Throwable t) {
                if (callback != null) {
                    callback.onFailure(call, t);
                }
            }
        });
        return call;
    }

    public Observable<Pair<retrofit2.Response<Void>, Retrofit>> cancelRequestRx(String accountId, String transactionId) {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).cancelTransaction(accountId, transactionId), Observable.just(apiRetrofitPair.second), Coinbase$$Lambda$19.lambdaFactory$());
    }

    public Call sendMoney(String accountId, HashMap<String, Object> params, final CallbackWithRetrofit<com.coinbase.v2.models.transactions.Transaction> callback) {
        params.put("type", "send");
        final Pair<ApiInterface, Retrofit> apiRetrofitPair = getApiService();
        Call call = ((ApiInterface) apiRetrofitPair.first).sendMoney(accountId, params);
        call.enqueue(new Callback<com.coinbase.v2.models.transactions.Transaction>() {
            public void onResponse(Call<com.coinbase.v2.models.transactions.Transaction> call, retrofit2.Response<com.coinbase.v2.models.transactions.Transaction> response) {
                if (callback != null) {
                    callback.onResponse(call, response, (Retrofit) apiRetrofitPair.second);
                }
            }

            public void onFailure(Call<com.coinbase.v2.models.transactions.Transaction> call, Throwable t) {
                if (callback != null) {
                    callback.onFailure(call, t);
                }
            }
        });
        return call;
    }

    public Observable<Pair<retrofit2.Response<com.coinbase.v2.models.transactions.Transaction>, Retrofit>> sendMoneyRx(String accountId, HashMap<String, Object> params) {
        params.put("type", "send");
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).sendMoney(accountId, params), Observable.just(apiRetrofitPair.second), Coinbase$$Lambda$20.lambdaFactory$());
    }

    public Call requestMoney(String accountId, HashMap<String, Object> params, final CallbackWithRetrofit<com.coinbase.v2.models.transactions.Transaction> callback) {
        params.put("type", "request");
        final Pair<ApiInterface, Retrofit> apiRetrofitPair = getApiService();
        Call call = ((ApiInterface) apiRetrofitPair.first).requestMoney(accountId, params);
        call.enqueue(new Callback<com.coinbase.v2.models.transactions.Transaction>() {
            public void onResponse(Call<com.coinbase.v2.models.transactions.Transaction> call, retrofit2.Response<com.coinbase.v2.models.transactions.Transaction> response) {
                if (callback != null) {
                    callback.onResponse(call, response, (Retrofit) apiRetrofitPair.second);
                }
            }

            public void onFailure(Call<com.coinbase.v2.models.transactions.Transaction> call, Throwable t) {
                if (callback != null) {
                    callback.onFailure(call, t);
                }
            }
        });
        return call;
    }

    public Observable<Pair<retrofit2.Response<com.coinbase.v2.models.transactions.Transaction>, Retrofit>> requestMoneyRx(String accountId, HashMap<String, Object> params) {
        params.put("type", "request");
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).requestMoney(accountId, params), Observable.just(apiRetrofitPair.second), Coinbase$$Lambda$21.lambdaFactory$());
    }

    public Call transferMoney(String accountId, HashMap<String, Object> params, final CallbackWithRetrofit<com.coinbase.v2.models.transactions.Transaction> callback) {
        params.put("type", "transfer");
        final Pair<ApiInterface, Retrofit> apiRetrofitPair = getApiService();
        Call call = ((ApiInterface) apiRetrofitPair.first).transferMoney(accountId, params);
        call.enqueue(new Callback<com.coinbase.v2.models.transactions.Transaction>() {
            public void onResponse(Call<com.coinbase.v2.models.transactions.Transaction> call, retrofit2.Response<com.coinbase.v2.models.transactions.Transaction> response) {
                if (callback != null) {
                    callback.onResponse(call, response, (Retrofit) apiRetrofitPair.second);
                }
            }

            public void onFailure(Call<com.coinbase.v2.models.transactions.Transaction> call, Throwable t) {
                if (callback != null) {
                    callback.onFailure(call, t);
                }
            }
        });
        return call;
    }

    public Observable<Pair<retrofit2.Response<com.coinbase.v2.models.transactions.Transaction>, Retrofit>> transferMoneyRx(String accountId, HashMap<String, Object> params) {
        params.put("type", "transfer");
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).transferMoney(accountId, params), Observable.just(apiRetrofitPair.second), Coinbase$$Lambda$22.lambdaFactory$());
    }

    public Call buyBitcoin(String accountId, HashMap<String, Object> params, final CallbackWithRetrofit<com.coinbase.v2.models.transfers.Transfer> callback) {
        final Pair<ApiInterface, Retrofit> apiRetrofitPair = getApiService();
        Call call = ((ApiInterface) apiRetrofitPair.first).buyBitcoin(accountId, params);
        call.enqueue(new Callback<com.coinbase.v2.models.transfers.Transfer>() {
            public void onResponse(Call<com.coinbase.v2.models.transfers.Transfer> call, retrofit2.Response<com.coinbase.v2.models.transfers.Transfer> response) {
                if (callback != null) {
                    callback.onResponse(call, response, (Retrofit) apiRetrofitPair.second);
                }
            }

            public void onFailure(Call<com.coinbase.v2.models.transfers.Transfer> call, Throwable t) {
                if (callback != null) {
                    callback.onFailure(call, t);
                }
            }
        });
        return call;
    }

    public Observable<Pair<retrofit2.Response<com.coinbase.v2.models.transfers.Transfer>, Retrofit>> buyBitcoinRx(String accountId, HashMap<String, Object> params) {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).buyBitcoin(accountId, params), Observable.just(apiRetrofitPair.second), Coinbase$$Lambda$23.lambdaFactory$());
    }

    public Call commitBuyBitcoin(String accountId, String buyId, final CallbackWithRetrofit<com.coinbase.v2.models.transfers.Transfer> callback) {
        final Pair<ApiInterface, Retrofit> apiRetrofitPair = getApiService();
        Call call = ((ApiInterface) apiRetrofitPair.first).commitBuyBitcoin(accountId, buyId);
        call.enqueue(new Callback<com.coinbase.v2.models.transfers.Transfer>() {
            public void onResponse(Call<com.coinbase.v2.models.transfers.Transfer> call, retrofit2.Response<com.coinbase.v2.models.transfers.Transfer> response) {
                if (callback != null) {
                    callback.onResponse(call, response, (Retrofit) apiRetrofitPair.second);
                }
            }

            public void onFailure(Call<com.coinbase.v2.models.transfers.Transfer> call, Throwable t) {
                if (callback != null) {
                    callback.onFailure(call, t);
                }
            }
        });
        return call;
    }

    public Observable<Pair<retrofit2.Response<com.coinbase.v2.models.transfers.Transfer>, Retrofit>> commitBuyBitcoinRx(String accountId, String buyId) {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).commitBuyBitcoin(accountId, buyId), Observable.just(apiRetrofitPair.second), Coinbase$$Lambda$24.lambdaFactory$());
    }

    public Call sellBitcoin(String accountId, HashMap<String, Object> params, final CallbackWithRetrofit<com.coinbase.v2.models.transfers.Transfer> callback) {
        final Pair<ApiInterface, Retrofit> apiRetrofitPair = getApiService();
        Call call = ((ApiInterface) apiRetrofitPair.first).sellBitcoin(accountId, params);
        call.enqueue(new Callback<com.coinbase.v2.models.transfers.Transfer>() {
            public void onResponse(Call<com.coinbase.v2.models.transfers.Transfer> call, retrofit2.Response<com.coinbase.v2.models.transfers.Transfer> response) {
                if (callback != null) {
                    callback.onResponse(call, response, (Retrofit) apiRetrofitPair.second);
                }
            }

            public void onFailure(Call<com.coinbase.v2.models.transfers.Transfer> call, Throwable t) {
                if (callback != null) {
                    callback.onFailure(call, t);
                }
            }
        });
        return call;
    }

    public Observable<Pair<retrofit2.Response<com.coinbase.v2.models.transfers.Transfer>, Retrofit>> sellBitcoinRx(String accountId, HashMap<String, Object> params) {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).sellBitcoin(accountId, params), Observable.just(apiRetrofitPair.second), Coinbase$$Lambda$25.lambdaFactory$());
    }

    public Call commitSellBitcoin(String accountId, String sellId, final CallbackWithRetrofit<com.coinbase.v2.models.transfers.Transfer> callback) {
        final Pair<ApiInterface, Retrofit> apiRetrofitPair = getApiService();
        Call call = ((ApiInterface) apiRetrofitPair.first).commitSellBitcoin(accountId, sellId);
        call.enqueue(new Callback<com.coinbase.v2.models.transfers.Transfer>() {
            public void onResponse(Call<com.coinbase.v2.models.transfers.Transfer> call, retrofit2.Response<com.coinbase.v2.models.transfers.Transfer> response) {
                if (callback != null) {
                    callback.onResponse(call, response, (Retrofit) apiRetrofitPair.second);
                }
            }

            public void onFailure(Call<com.coinbase.v2.models.transfers.Transfer> call, Throwable t) {
                if (callback != null) {
                    callback.onFailure(call, t);
                }
            }
        });
        return call;
    }

    public Observable<Pair<retrofit2.Response<com.coinbase.v2.models.transfers.Transfer>, Retrofit>> commitSellBitcoinRx(String accountId, String sellId) {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).commitSellBitcoin(accountId, sellId), Observable.just(apiRetrofitPair.second), Coinbase$$Lambda$26.lambdaFactory$());
    }

    public Call getSellPrice(String baseCurrency, String fiatCurrency, HashMap<String, Object> params, final CallbackWithRetrofit<Price> callback) {
        final Pair<ApiInterface, Retrofit> apiRetrofitPair = getApiService();
        Call call = ((ApiInterface) apiRetrofitPair.first).getSellPrice(baseCurrency, fiatCurrency, cleanQueryMap(params));
        call.enqueue(new Callback<Price>() {
            public void onResponse(Call<Price> call, retrofit2.Response<Price> response) {
                if (callback != null) {
                    callback.onResponse(call, response, (Retrofit) apiRetrofitPair.second);
                }
            }

            public void onFailure(Call<Price> call, Throwable t) {
                if (callback != null) {
                    callback.onFailure(call, t);
                }
            }
        });
        return call;
    }

    public Observable<Pair<retrofit2.Response<Price>, Retrofit>> getSellPriceRx(String baseCurrency, String fiatCurrency, HashMap<String, Object> params) {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).getSellPrice(baseCurrency, fiatCurrency, cleanQueryMap(params)), Observable.just(apiRetrofitPair.second), Coinbase$$Lambda$27.lambdaFactory$());
    }

    public Call getBuyPrice(String baseCurrency, String fiatCurrency, HashMap<String, Object> params, final CallbackWithRetrofit<Price> callback) {
        final Pair<ApiInterface, Retrofit> apiRetrofitPair = getApiService();
        Call call = ((ApiInterface) apiRetrofitPair.first).getBuyPrice(baseCurrency, fiatCurrency, cleanQueryMap(params));
        call.enqueue(new Callback<Price>() {
            public void onResponse(Call<Price> call, retrofit2.Response<Price> response) {
                if (callback != null) {
                    callback.onResponse(call, response, (Retrofit) apiRetrofitPair.second);
                }
            }

            public void onFailure(Call<Price> call, Throwable t) {
                if (callback != null) {
                    callback.onFailure(call, t);
                }
            }
        });
        return call;
    }

    public Observable<Pair<retrofit2.Response<Price>, Retrofit>> getBuyPriceRx(String baseCurrency, String fiatCurrency, HashMap<String, Object> params) {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).getBuyPrice(baseCurrency, fiatCurrency, cleanQueryMap(params)), Observable.just(apiRetrofitPair.second), Coinbase$$Lambda$28.lambdaFactory$());
    }

    public Call getSpotPrice(String baseCurrency, String fiatCurrency, HashMap<String, Object> params, final CallbackWithRetrofit<Price> callback) {
        final Pair<ApiInterface, Retrofit> apiRetrofitPair = getApiService();
        Call call = ((ApiInterface) apiRetrofitPair.first).getSpotPrice(baseCurrency, fiatCurrency, cleanQueryMap(params));
        call.enqueue(new Callback<Price>() {
            public void onResponse(Call<Price> call, retrofit2.Response<Price> response) {
                if (callback != null) {
                    callback.onResponse(call, response, (Retrofit) apiRetrofitPair.second);
                }
            }

            public void onFailure(Call<Price> call, Throwable t) {
                if (callback != null) {
                    callback.onFailure(call, t);
                }
            }
        });
        return call;
    }

    public Observable<Pair<retrofit2.Response<Price>, Retrofit>> getSpotPriceRx(String baseCurrency, String fiatCurrency, HashMap<String, Object> params) {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).getSpotPrice(baseCurrency, fiatCurrency, cleanQueryMap(params)), Observable.just(apiRetrofitPair.second), Coinbase$$Lambda$29.lambdaFactory$());
    }

    public Call getSpotPrices(String fiatCurrency, HashMap<String, Object> params, final CallbackWithRetrofit<Prices> callback) {
        final Pair<ApiInterface, Retrofit> apiRetrofitPair = getApiService();
        Call call = ((ApiInterface) apiRetrofitPair.first).getSpotPrices(fiatCurrency, cleanQueryMap(params));
        call.enqueue(new Callback<Prices>() {
            public void onResponse(Call<Prices> call, retrofit2.Response<Prices> response) {
                if (callback != null) {
                    callback.onResponse(call, response, (Retrofit) apiRetrofitPair.second);
                }
            }

            public void onFailure(Call<Prices> call, Throwable t) {
                if (callback != null) {
                    callback.onFailure(call, t);
                }
            }
        });
        return call;
    }

    public Observable<Pair<retrofit2.Response<Prices>, Retrofit>> getSpotPricesRx(String fiatCurrency, HashMap<String, Object> params) {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).getSpotPrices(fiatCurrency, cleanQueryMap(params)), Observable.just(apiRetrofitPair.second), Coinbase$$Lambda$30.lambdaFactory$());
    }

    public Call generateAddress(String accountId, final CallbackWithRetrofit<com.coinbase.v2.models.address.Address> callback) {
        final Pair<ApiInterface, Retrofit> apiRetrofitPair = getApiService();
        Call call = ((ApiInterface) apiRetrofitPair.first).generateAddress(accountId);
        call.enqueue(new Callback<com.coinbase.v2.models.address.Address>() {
            public void onResponse(Call<com.coinbase.v2.models.address.Address> call, retrofit2.Response<com.coinbase.v2.models.address.Address> response) {
                if (callback != null) {
                    callback.onResponse(call, response, (Retrofit) apiRetrofitPair.second);
                }
            }

            public void onFailure(Call<com.coinbase.v2.models.address.Address> call, Throwable t) {
                if (callback != null) {
                    callback.onFailure(call, t);
                }
            }
        });
        return call;
    }

    public Observable<Pair<retrofit2.Response<com.coinbase.v2.models.address.Address>, Retrofit>> generateAddressRx(String accountId) {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).generateAddress(accountId), Observable.just(apiRetrofitPair.second), Coinbase$$Lambda$31.lambdaFactory$());
    }

    public Call depositFunds(String accountId, HashMap<String, Object> params, final CallbackWithRetrofit<com.coinbase.v2.models.transfers.Transfer> callback) {
        final Pair<ApiInterface, Retrofit> apiRetrofitPair = getApiService();
        Call call = ((ApiInterface) apiRetrofitPair.first).depositFunds(accountId, params);
        call.enqueue(new Callback<com.coinbase.v2.models.transfers.Transfer>() {
            public void onResponse(Call<com.coinbase.v2.models.transfers.Transfer> call, retrofit2.Response<com.coinbase.v2.models.transfers.Transfer> response) {
                if (callback != null) {
                    callback.onResponse(call, response, (Retrofit) apiRetrofitPair.second);
                }
            }

            public void onFailure(Call<com.coinbase.v2.models.transfers.Transfer> call, Throwable t) {
                if (callback != null) {
                    callback.onFailure(call, t);
                }
            }
        });
        return call;
    }

    public Observable<Pair<retrofit2.Response<com.coinbase.v2.models.transfers.Transfer>, Retrofit>> depositFundsRx(String accountId, HashMap<String, Object> params) {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).depositFunds(accountId, params), Observable.just(apiRetrofitPair.second), Coinbase$$Lambda$32.lambdaFactory$());
    }

    public Call commitDeposit(String accountId, String depositId, final CallbackWithRetrofit<com.coinbase.v2.models.transfers.Transfer> callback) {
        final Pair<ApiInterface, Retrofit> apiRetrofitPair = getApiService();
        Call call = ((ApiInterface) apiRetrofitPair.first).commitDeposit(accountId, depositId);
        call.enqueue(new Callback<com.coinbase.v2.models.transfers.Transfer>() {
            public void onResponse(Call<com.coinbase.v2.models.transfers.Transfer> call, retrofit2.Response<com.coinbase.v2.models.transfers.Transfer> response) {
                if (callback != null) {
                    callback.onResponse(call, response, (Retrofit) apiRetrofitPair.second);
                }
            }

            public void onFailure(Call<com.coinbase.v2.models.transfers.Transfer> call, Throwable t) {
                if (callback != null) {
                    callback.onFailure(call, t);
                }
            }
        });
        return call;
    }

    public Observable<Pair<retrofit2.Response<com.coinbase.v2.models.transfers.Transfer>, Retrofit>> commitDepositRx(String accountId, String depositId) {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).commitDeposit(accountId, depositId), Observable.just(apiRetrofitPair.second), Coinbase$$Lambda$33.lambdaFactory$());
    }

    public Call withdrawFunds(String accountId, HashMap<String, Object> params, final CallbackWithRetrofit<com.coinbase.v2.models.transfers.Transfer> callback) {
        final Pair<ApiInterface, Retrofit> apiRetrofitPair = getApiService();
        Call call = ((ApiInterface) apiRetrofitPair.first).withdrawFunds(accountId, params);
        call.enqueue(new Callback<com.coinbase.v2.models.transfers.Transfer>() {
            public void onResponse(Call<com.coinbase.v2.models.transfers.Transfer> call, retrofit2.Response<com.coinbase.v2.models.transfers.Transfer> response) {
                if (callback != null) {
                    callback.onResponse(call, response, (Retrofit) apiRetrofitPair.second);
                }
            }

            public void onFailure(Call<com.coinbase.v2.models.transfers.Transfer> call, Throwable t) {
                if (callback != null) {
                    callback.onFailure(call, t);
                }
            }
        });
        return call;
    }

    public Observable<Pair<retrofit2.Response<com.coinbase.v2.models.transfers.Transfer>, Retrofit>> withdrawFundsRx(String accountId, HashMap<String, Object> params) {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).withdrawFunds(accountId, params), Observable.just(apiRetrofitPair.second), Coinbase$$Lambda$34.lambdaFactory$());
    }

    public Call commitWithdraw(String accountId, String withdrawId, final CallbackWithRetrofit<com.coinbase.v2.models.transfers.Transfer> callback) {
        final Pair<ApiInterface, Retrofit> apiRetrofitPair = getApiService();
        Call call = ((ApiInterface) apiRetrofitPair.first).commitWithdraw(accountId, withdrawId);
        call.enqueue(new Callback<com.coinbase.v2.models.transfers.Transfer>() {
            public void onResponse(Call<com.coinbase.v2.models.transfers.Transfer> call, retrofit2.Response<com.coinbase.v2.models.transfers.Transfer> response) {
                if (callback != null) {
                    callback.onResponse(call, response, (Retrofit) apiRetrofitPair.second);
                }
            }

            public void onFailure(Call<com.coinbase.v2.models.transfers.Transfer> call, Throwable t) {
                if (callback != null) {
                    callback.onFailure(call, t);
                }
            }
        });
        return call;
    }

    public Observable<Pair<retrofit2.Response<com.coinbase.v2.models.transfers.Transfer>, Retrofit>> commitWithdrawRx(String accountId, String withdrawId) {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).commitWithdraw(accountId, withdrawId), Observable.just(apiRetrofitPair.second), Coinbase$$Lambda$35.lambdaFactory$());
    }

    public Call getPaymentMethod(String paymentMethodId, final CallbackWithRetrofit<PaymentMethod> callback) {
        final Pair<ApiInterface, Retrofit> apiRetrofitPair = getApiService();
        Call call = ((ApiInterface) apiRetrofitPair.first).getPaymentMethod(paymentMethodId);
        call.enqueue(new Callback<PaymentMethod>() {
            public void onResponse(Call<PaymentMethod> call, retrofit2.Response<PaymentMethod> response) {
                if (callback != null) {
                    callback.onResponse(call, response, (Retrofit) apiRetrofitPair.second);
                }
            }

            public void onFailure(Call<PaymentMethod> call, Throwable t) {
                if (callback != null) {
                    callback.onFailure(call, t);
                }
            }
        });
        return call;
    }

    public Observable<Pair<retrofit2.Response<PaymentMethod>, Retrofit>> getPaymentMethodRx(String paymentMethodId) {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).getPaymentMethod(paymentMethodId), Observable.just(apiRetrofitPair.second), Coinbase$$Lambda$36.lambdaFactory$());
    }

    public Observable<Pair<retrofit2.Response<PaymentMethod>, Retrofit>> getPaymentMethodVerifiedRx(String paymentMethodId) {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).getPaymentMethodVerified(paymentMethodId), Observable.just(apiRetrofitPair.second), Coinbase$$Lambda$37.lambdaFactory$());
    }

    public Call getPaymentMethods(HashMap<String, Object> options, final CallbackWithRetrofit<PaymentMethods> callback) {
        final Pair<ApiInterface, Retrofit> apiRetrofitPair = getApiService();
        Call call = ((ApiInterface) apiRetrofitPair.first).getPaymentMethods(cleanQueryMap(options));
        call.enqueue(new Callback<PaymentMethods>() {
            public void onResponse(Call<PaymentMethods> call, retrofit2.Response<PaymentMethods> response) {
                if (callback != null) {
                    callback.onResponse(call, response, (Retrofit) apiRetrofitPair.second);
                }
            }

            public void onFailure(Call<PaymentMethods> call, Throwable t) {
                if (callback != null) {
                    callback.onFailure(call, t);
                }
            }
        });
        return call;
    }

    public Observable<Pair<retrofit2.Response<PaymentMethods>, Retrofit>> getPaymentMethodsRx(HashMap<String, Object> options) {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).getPaymentMethods(cleanQueryMap(options)), Observable.just(apiRetrofitPair.second), Coinbase$$Lambda$38.lambdaFactory$());
    }

    public Call getExchangeRates(HashMap<String, Object> currency, final CallbackWithRetrofit<ExchangeRates> callback) {
        final Pair<ApiInterface, Retrofit> apiRetrofitPair = getApiService();
        Call call = ((ApiInterface) apiRetrofitPair.first).getExchangeRates(cleanQueryMap(currency));
        call.enqueue(new Callback<ExchangeRates>() {
            public void onResponse(Call<ExchangeRates> call, retrofit2.Response<ExchangeRates> response) {
                if (callback != null) {
                    callback.onResponse(call, response, (Retrofit) apiRetrofitPair.second);
                }
            }

            public void onFailure(Call<ExchangeRates> call, Throwable t) {
                if (callback != null) {
                    callback.onFailure(call, t);
                }
            }
        });
        return call;
    }

    public Observable<Pair<retrofit2.Response<ExchangeRates>, Retrofit>> getExchangeRatesRx(HashMap<String, Object> currency) {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).getExchangeRates(cleanQueryMap(currency)), Observable.just(apiRetrofitPair.second), Coinbase$$Lambda$39.lambdaFactory$());
    }

    public Call getSupportedCurrencies(final CallbackWithRetrofit<SupportedCurrencies> callback) {
        final Pair<ApiInterface, Retrofit> apiRetrofitPair = getApiService();
        Call call = ((ApiInterface) apiRetrofitPair.first).getSupportedCurrencies();
        call.enqueue(new Callback<SupportedCurrencies>() {
            public void onResponse(Call<SupportedCurrencies> call, retrofit2.Response<SupportedCurrencies> response) {
                if (callback != null) {
                    callback.onResponse(call, response, (Retrofit) apiRetrofitPair.second);
                }
            }

            public void onFailure(Call<SupportedCurrencies> call, Throwable t) {
                if (callback != null) {
                    callback.onFailure(call, t);
                }
            }
        });
        return call;
    }

    public Observable<Pair<retrofit2.Response<SupportedCurrencies>, Retrofit>> getSupportedCurrenciesRx() {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).getSupportedCurrencies(), Observable.just(apiRetrofitPair.second), Coinbase$$Lambda$40.lambdaFactory$());
    }

    protected HashMap<String, Object> cleanQueryMap(HashMap<String, Object> options) {
        if (options == null) {
            return new HashMap();
        }
        HashMap<String, Object> optionsCopy = new HashMap(options);
        for (String key : options.keySet()) {
            if (optionsCopy.get(key) == null) {
                optionsCopy.remove(key);
            }
        }
        return optionsCopy;
    }

    private HashMap<String, Object> getRefreshTokensParams(String clientId, String clientSecret, String refreshToken) {
        HashMap<String, Object> params = new HashMap();
        params.put("client_id", clientId);
        params.put("client_secret", clientSecret);
        params.put(ApiConstants.REFRESH_TOKEN, refreshToken);
        params.put(ApiConstants.GRANT_TYPE, ApiConstants.REFRESH_TOKEN);
        return params;
    }

    private HashMap<String, Object> getUpdateUserParams(String name, String timeZone, String nativeCurrency) {
        HashMap<String, Object> params = new HashMap();
        if (name != null) {
            params.put("name", name);
        }
        if (timeZone != null) {
            params.put(ApiConstants.TIME_ZONE, timeZone);
        }
        if (nativeCurrency != null) {
            params.put("native_currency", nativeCurrency);
        }
        return params;
    }

    private List<String> getTransactionExpandOptions() {
        return Arrays.asList(new String[]{"from", "to", "buy", "sell"});
    }
}
