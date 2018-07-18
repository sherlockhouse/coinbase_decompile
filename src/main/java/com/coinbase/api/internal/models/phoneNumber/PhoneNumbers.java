package com.coinbase.api.internal.models.phoneNumber;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class PhoneNumbers {
    @SerializedName("data")
    @Expose
    private List<Data> data = new ArrayList();

    public List<Data> getData() {
        return this.data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }
}
