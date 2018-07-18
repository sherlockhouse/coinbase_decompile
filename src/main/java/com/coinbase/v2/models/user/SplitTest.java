package com.coinbase.v2.models.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SplitTest {
    @SerializedName("group")
    @Expose
    private String group;
    @SerializedName("is_tracked")
    @Expose
    private Boolean isTracked;
    @SerializedName("test")
    @Expose
    private String test;

    public String getTest() {
        return this.test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public String getGroup() {
        return this.group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Boolean getIsTracked() {
        return this.isTracked;
    }

    public void setIsTracked(Boolean isTracked) {
        this.isTracked = isTracked;
    }
}
