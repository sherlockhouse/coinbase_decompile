package com.coinbase.v2.models.paymentMethods;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pagination {
    @SerializedName("ending_before")
    @Expose
    private Object endingBefore;
    @SerializedName("limit")
    @Expose
    private Integer limit;
    @SerializedName("next_uri")
    @Expose
    private Object nextUri;
    @SerializedName("order")
    @Expose
    private String order;
    @SerializedName("previous_uri")
    @Expose
    private Object previousUri;
    @SerializedName("starting_after")
    @Expose
    private Object startingAfter;

    public Object getEndingBefore() {
        return this.endingBefore;
    }

    public void setEndingBefore(Object endingBefore) {
        this.endingBefore = endingBefore;
    }

    public Object getStartingAfter() {
        return this.startingAfter;
    }

    public void setStartingAfter(Object startingAfter) {
        this.startingAfter = startingAfter;
    }

    public String getOrder() {
        return this.order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Integer getLimit() {
        return this.limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Object getPreviousUri() {
        return this.previousUri;
    }

    public void setPreviousUri(Object previousUri) {
        this.previousUri = previousUri;
    }

    public Object getNextUri() {
        return this.nextUri;
    }

    public void setNextUri(Object nextUri) {
        this.nextUri = nextUri;
    }
}
