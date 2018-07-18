package com.coinbase;

import java.net.URL;
import javax.net.ssl.SSLContext;
import rx.Scheduler;

public class CoinbaseBuilder {
    String access_token;
    String acct_id;
    String api_key;
    String api_secret;
    URL base_api_url;
    URL base_oauth_url;
    int cacheSize;
    CallbackVerifier callback_verifier;
    Scheduler scheduler;
    SSLContext ssl_context;

    public Coinbase build() {
        return new Coinbase(this);
    }

    public CoinbaseBuilder withAccessToken(String access_token) {
        this.access_token = access_token;
        return this;
    }

    public CoinbaseBuilder withApiKey(String api_key, String api_secret) {
        this.api_key = api_key;
        this.api_secret = api_secret;
        return this;
    }

    public CoinbaseBuilder withAccountId(String acct_id) {
        this.acct_id = acct_id;
        return this;
    }

    public CoinbaseBuilder withCallbackVerifier(CallbackVerifier callback_verifier) {
        this.callback_verifier = callback_verifier;
        return this;
    }

    public CoinbaseBuilder withSSLContext(SSLContext ssl_context) {
        this.ssl_context = ssl_context;
        return this;
    }

    public CoinbaseBuilder withBaseApiURL(URL base_api_url) {
        this.base_api_url = base_api_url;
        return this;
    }

    public CoinbaseBuilder withBaseOAuthURL(URL base_oauth_url) {
        this.base_oauth_url = base_oauth_url;
        return this;
    }

    public CoinbaseBuilder withScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
        return this;
    }

    public CoinbaseBuilder cacheSize(int cacheSize) {
        this.cacheSize = cacheSize;
        return this;
    }
}
