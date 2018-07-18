package com.coinbase.api.internal.models.emailPreferences;

import com.google.gson.annotations.SerializedName;
import java.util.Map;
import javax.annotation.Nullable;

abstract class C$AutoValue_EmailPreferences extends EmailPreferences {
    private final Map<String, Boolean> data;

    C$AutoValue_EmailPreferences(@Nullable Map<String, Boolean> data) {
        this.data = data;
    }

    @SerializedName("data")
    @Nullable
    public Map<String, Boolean> getData() {
        return this.data;
    }

    public String toString() {
        return "EmailPreferences{data=" + this.data + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof EmailPreferences)) {
            return false;
        }
        EmailPreferences that = (EmailPreferences) o;
        if (this.data != null) {
            return this.data.equals(that.getData());
        }
        if (that.getData() != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (1 * 1000003) ^ (this.data == null ? 0 : this.data.hashCode());
    }
}
