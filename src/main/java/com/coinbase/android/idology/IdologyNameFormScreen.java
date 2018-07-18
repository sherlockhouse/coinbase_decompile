package com.coinbase.android.idology;

public interface IdologyNameFormScreen {
    String getDobText();

    String getFirstNameText();

    String getLastNameText();

    String getSsnText();

    void hideDatePickerLayout();

    void hideSsnText();

    boolean isDatePickerLayoutVisible();

    void prefillName(String str, String str2);

    void prefillSsnLast4(String str);

    void setDobText(String str);

    void showDatePickerLayout(int i, int i2, int i3);

    void showDobFieldError(String str);

    void showFirstNameFieldError(String str);

    void showLastNameFieldError(String str);

    void showSsnLast4FieldError(String str);
}
