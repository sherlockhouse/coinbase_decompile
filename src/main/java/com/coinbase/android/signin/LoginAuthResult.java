package com.coinbase.android.signin;

import com.coinbase.android.signin.AuthManager.ViewType;
import com.coinbase.android.ui.ActionBarController;

public abstract class LoginAuthResult {

    public static abstract class Builder {
        public abstract LoginAuthResult build();

        public abstract Builder setAuthCode(String str);

        public abstract Builder setController(ActionBarController actionBarController);

        public abstract Builder setViewType(ViewType viewType);
    }

    public abstract String getAuthCode();

    public abstract ActionBarController getController();

    public abstract ViewType getViewType();

    public static Builder builder() {
        return new Builder();
    }
}
