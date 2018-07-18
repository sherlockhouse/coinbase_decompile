package com.coinbase.v2.models.errors;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class Errors {
    @SerializedName("errors")
    @Expose
    private List<ErrorBody> errors = new ArrayList();

    public List<ErrorBody> getErrors() {
        return this.errors;
    }

    public void setErrors(List<ErrorBody> errors) {
        this.errors = errors;
    }
}
