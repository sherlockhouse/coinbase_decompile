package com.coinbase.v2.models.errors;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ErrorBody {
    @SerializedName("field")
    @Expose
    private String field;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("message")
    @Expose
    private String message;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getField() {
        return this.field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
