package com.coinbase.android.buysell;

public interface D3SViewListener {
    void onAuthorizationCompleted(String str);

    void onAuthorizationStarted(D3SView d3SView);

    void onAuthorizationWebPageLoadingComplete();

    void onAuthorizationWebPageLoadingError(int i, String str, String str2);

    void onAuthorizationWebPageLoadingProgressChanged(int i);
}
