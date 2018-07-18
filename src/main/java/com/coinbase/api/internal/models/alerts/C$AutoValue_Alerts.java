package com.coinbase.api.internal.models.alerts;

import com.google.gson.annotations.SerializedName;
import java.util.List;

abstract class C$AutoValue_Alerts extends Alerts {
    private final List<Data> data;

    C$AutoValue_Alerts(List<Data> data) {
        if (data == null) {
            throw new NullPointerException("Null data");
        }
        this.data = data;
    }

    @SerializedName("data")
    public List<Data> getData() {
        return this.data;
    }

    public String toString() {
        return "Alerts{data=" + this.data + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Alerts)) {
            return false;
        }
        return this.data.equals(((Alerts) o).getData());
    }

    public int hashCode() {
        return (1 * 1000003) ^ this.data.hashCode();
    }
}
