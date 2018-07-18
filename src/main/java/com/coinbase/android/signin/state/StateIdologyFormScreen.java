package com.coinbase.android.signin.state;

import com.coinbase.api.internal.models.idology.Data;

public interface StateIdologyFormScreen {
    void hideProgress();

    void setContinueButtonEnabled(boolean z);

    void setIdologyData(Data data);

    void showProgress();

    void submitForm(Data data);
}
