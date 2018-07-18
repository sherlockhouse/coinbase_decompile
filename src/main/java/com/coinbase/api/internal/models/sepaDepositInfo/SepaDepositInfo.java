package com.coinbase.api.internal.models.sepaDepositInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class SepaDepositInfo {
    @SerializedName("data")
    @Expose
    private List<Data> data = null;

    public List<Data> getData() {
        return this.data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }
}
