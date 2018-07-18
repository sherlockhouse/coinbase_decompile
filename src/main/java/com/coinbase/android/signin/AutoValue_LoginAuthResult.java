package com.coinbase.android.signin;

import com.coinbase.android.signin.AuthManager.ViewType;
import com.coinbase.android.ui.ActionBarController;

final class AutoValue_LoginAuthResult extends LoginAuthResult {
    private final String authCode;
    private final ActionBarController controller;
    private final ViewType viewType;

    static final class Builder extends com.coinbase.android.signin.LoginAuthResult.Builder {
        private String authCode;
        private ActionBarController controller;
        private ViewType viewType;

        Builder() {
        }

        Builder(LoginAuthResult source) {
            this.controller = source.getController();
            this.viewType = source.getViewType();
            this.authCode = source.getAuthCode();
        }

        public com.coinbase.android.signin.LoginAuthResult.Builder setController(ActionBarController controller) {
            this.controller = controller;
            return this;
        }

        public com.coinbase.android.signin.LoginAuthResult.Builder setViewType(ViewType viewType) {
            this.viewType = viewType;
            return this;
        }

        public com.coinbase.android.signin.LoginAuthResult.Builder setAuthCode(String authCode) {
            this.authCode = authCode;
            return this;
        }

        public LoginAuthResult build() {
            String missing = "";
            if (this.viewType == null) {
                missing = missing + " viewType";
            }
            if (missing.isEmpty()) {
                return new AutoValue_LoginAuthResult(this.controller, this.viewType, this.authCode);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    private AutoValue_LoginAuthResult(ActionBarController controller, ViewType viewType, String authCode) {
        this.controller = controller;
        this.viewType = viewType;
        this.authCode = authCode;
    }

    public ActionBarController getController() {
        return this.controller;
    }

    public ViewType getViewType() {
        return this.viewType;
    }

    public String getAuthCode() {
        return this.authCode;
    }

    public String toString() {
        return "LoginAuthResult{controller=" + this.controller + ", viewType=" + this.viewType + ", authCode=" + this.authCode + "}";
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof LoginAuthResult)) {
            return false;
        }
        LoginAuthResult that = (LoginAuthResult) o;
        if (this.controller != null) {
            if (this.controller.equals(that.getController())) {
            }
            return false;
        }
        if (this.viewType.equals(that.getViewType())) {
            if (this.authCode == null) {
                if (that.getAuthCode() == null) {
                    return true;
                }
            } else if (this.authCode.equals(that.getAuthCode())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((((1 * 1000003) ^ (this.controller == null ? 0 : this.controller.hashCode())) * 1000003) ^ this.viewType.hashCode()) * 1000003;
        if (this.authCode != null) {
            i = this.authCode.hashCode();
        }
        return h ^ i;
    }
}
