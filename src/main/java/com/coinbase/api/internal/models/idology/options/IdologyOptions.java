package com.coinbase.api.internal.models.idology.options;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class IdologyOptions {
    @SerializedName("data")
    @Expose
    private List<Data> data = null;

    public enum OptionType {
        SOURCE_OF_FUNDS,
        COINBASE_USES,
        JOB_TITLES
    }

    public List<Data> getData() {
        return this.data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }
}
