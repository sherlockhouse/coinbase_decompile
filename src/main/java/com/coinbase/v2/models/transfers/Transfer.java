package com.coinbase.v2.models.transfers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class Transfer {
    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("user_warnings")
    @Expose
    private List<UserWarning> userWarnings = new ArrayList();

    public Data getData() {
        return this.data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public List<UserWarning> getUserWarnings() {
        return this.userWarnings;
    }

    public void setUserWarnings(List<UserWarning> userWarnings) {
        this.userWarnings = userWarnings;
    }
}
