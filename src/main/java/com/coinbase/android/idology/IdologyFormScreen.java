package com.coinbase.android.idology;

import android.net.Uri;
import com.coinbase.api.internal.models.idology.options.IdologyOptions;
import com.coinbase.api.internal.models.idology.options.IdologyOptions.OptionType;

public interface IdologyFormScreen {
    void displaySupportUrl(Uri uri);

    String getAddressLine1Text();

    String getAddressLine2Text();

    String getCityText();

    String getCurrentEmployerText();

    String getDobText();

    String getFirstNameText();

    String getLastNameText();

    String getSsnText();

    String getStateText();

    String getZipText();

    void hideDatePickerLayout();

    void hideSsn();

    void hideState();

    boolean isDatePickerLayoutVisible();

    void prefillAddress(String str, String str2, String str3, String str4, String str5);

    void prefillCurrentEmployer(String str);

    void prefillName(String str, String str2);

    void prefillSsnLast4(String str);

    void setCoinbaseUsesText(String str);

    void setDobText(String str, boolean z);

    void setJobTitleText(String str, boolean z);

    void setOptionsDialog(IdologyOptions idologyOptions, OptionType optionType);

    void setSourceOfFundsText(String str);

    void showDatePickerLayout(int i, int i2, int i3);

    void showPostalCode();
}
