package com.coinbase.android.signin.state;

import com.coinbase.api.internal.models.idology.Data;

public interface StateIdologyRetryFormScreen {
    void hideProgress();

    boolean isContinueButtonEnabled();

    void setContinueButtonEnabled(boolean z);

    void setIdologyData(Data data);

    void showProgress();

    void submitForm();
}
