package com.coinbase.v2.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pagination {
    @SerializedName("ending_before")
    @Expose
    private String endingBefore;
    @SerializedName("limit")
    @Expose
    private Integer limit;
    @SerializedName("next_uri")
    @Expose
    private String nextUri;
    @SerializedName("order")
    @Expose
    private String order;
    @SerializedName("previous_uri")
    @Expose
    private String previousUri;
    @SerializedName("starting_after")
    @Expose
    private String startingAfter;

    public String getEndingBefore() {
        return this.endingBefore;
    }

    public void setEndingBefore(String endingBefore) {
        this.endingBefore = endingBefore;
    }

    public String getStartingAfter() {
        return this.startingAfter;
    }

    public void setStartingAfter(String startingAfter) {
        this.startingAfter = startingAfter;
    }

    public Integer getLimit() {
        return this.limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getOrder() {
        return this.order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getPreviousUri() {
        return this.previousUri;
    }

    public void setPreviousUri(String previousUri) {
        this.previousUri = previousUri;
    }

    public String getNextUri() {
        return this.nextUri;
    }

    public void setNextUri(String nextUri) {
        this.nextUri = nextUri;
    }
}
