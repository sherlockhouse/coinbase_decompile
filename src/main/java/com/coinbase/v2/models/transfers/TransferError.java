package com.coinbase.v2.models.transfers;

import com.coinbase.v2.models.errors.ErrorBody;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class TransferError {
    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("errors")
    @Expose
    private List<ErrorBody> errors = new ArrayList();

    public List<ErrorBody> getErrors() {
        return this.errors;
    }

    public void setErrors(List<ErrorBody> errors) {
        this.errors = errors;
    }

    public Data getData() {
        return this.data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
