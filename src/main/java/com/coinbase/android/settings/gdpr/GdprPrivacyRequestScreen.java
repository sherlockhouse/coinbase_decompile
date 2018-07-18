package com.coinbase.android.settings.gdpr;

public interface GdprPrivacyRequestScreen {
    void hideAddlMessageAndButton();

    void hideProgressBar();

    void showAddlMessageAndButton();

    void showProgressBar();

    void updateLegalHeader(String str);

    void updateListItems();

    void updateToolbarTitle(String str);
}
