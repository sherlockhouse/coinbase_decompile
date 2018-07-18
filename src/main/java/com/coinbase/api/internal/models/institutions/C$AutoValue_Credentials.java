package com.coinbase.api.internal.models.institutions;

import com.google.gson.annotations.SerializedName;

abstract class C$AutoValue_Credentials extends Credentials {
    private final String password;
    private final String pin;
    private final String username;

    C$AutoValue_Credentials(String username, String password, String pin) {
        this.username = username;
        this.password = password;
        this.pin = pin;
    }

    @SerializedName("username")
    public String getUsername() {
        return this.username;
    }

    @SerializedName("password")
    public String getPassword() {
        return this.password;
    }

    @SerializedName("pin")
    public String getPin() {
        return this.pin;
    }

    public String toString() {
        return "Credentials{username=" + this.username + ", password=" + this.password + ", pin=" + this.pin + "}";
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Credentials)) {
            return false;
        }
        Credentials that = (Credentials) o;
        if (this.username != null) {
            if (this.username.equals(that.getUsername())) {
            }
            return false;
        }
        if (this.password != null) {
            if (this.password.equals(that.getPassword())) {
            }
            return false;
        }
        if (this.pin == null) {
            if (that.getPin() == null) {
                return true;
            }
        } else if (this.pin.equals(that.getPin())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((((1 * 1000003) ^ (this.username == null ? 0 : this.username.hashCode())) * 1000003) ^ (this.password == null ? 0 : this.password.hashCode())) * 1000003;
        if (this.pin != null) {
            i = this.pin.hashCode();
        }
        return h ^ i;
    }
}
