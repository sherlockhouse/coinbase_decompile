package com.coinbase.android.settings.idology;

import com.coinbase.android.ui.ActionBarController;
import com.coinbase.api.internal.models.idology.Data;

public interface IdologySettingsScreen {
    ActionBarController getController();

    void setContinueMenuEnabled(boolean z);

    void setIdologyData(Data data);

    void submitForm();
}
