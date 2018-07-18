package com.coinbase.android.signin;

public interface SignInPhoneNumberScreen {
    void hideAddPhoneNumber();

    void hideProgressBar();

    void hideProgressDialog();

    void hideSkipPhoneNumber();

    boolean isShown();

    void populateCountryAdapter(CountryCode[] countryCodeArr);

    void setSelectedCountry(int i);

    void showAddPhoneNumber();

    void showProgressBar();

    void showProgressDialog();

    void showSkipPhoneNumber();
}
