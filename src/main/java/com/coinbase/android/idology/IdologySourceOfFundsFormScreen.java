package com.coinbase.android.idology;

import com.coinbase.api.internal.models.idology.options.IdologyOptions;
import com.coinbase.api.internal.models.idology.options.IdologyOptions.OptionType;

public interface IdologySourceOfFundsFormScreen {
    String getCurrentEmployerText();

    void prefillCurrentEmployer(String str);

    void setCoinbaseUsesText(String str);

    void setJobTitleText(String str);

    void setOptionsDialog(IdologyOptions idologyOptions, OptionType optionType);

    void setSourceOfFundsText(String str);

    void showEmployerFieldError(String str);
}
