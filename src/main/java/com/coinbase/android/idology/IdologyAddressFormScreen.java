package com.coinbase.android.idology;

public interface IdologyAddressFormScreen {
    String getAddressLine1Text();

    String getAddressLine2Text();

    String getCityText();

    String getStateText();

    String getZipText();

    void hideState();

    void prefillAddress(String str, String str2, String str3, String str4, String str5);

    void setContinueButtonText(String str);

    void showAddress1FieldError(String str);

    void showAddress2FieldError(String str);

    void showAddressAutoComplete();

    void showAutoCompleteView();

    void showCityFieldError(String str);

    void showManualEntryView();

    void showPostalCode();

    void showProgressView();

    void showStateFieldError(String str);

    void showZipFieldError(String str);
}
