package com.coinbase.android.signin.state;

/* compiled from: StateSelectorScreen.kt */
public interface StateSelectorScreen {
    void hideProgressBar();

    void setStateChosen(String str);

    void showProgressBar();

    void showStateSelector();
}
