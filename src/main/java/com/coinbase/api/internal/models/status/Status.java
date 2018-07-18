package com.coinbase.api.internal.models.status;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Status {
    @SerializedName("components")
    @Expose
    private List<Component> components = null;
    @SerializedName("page")
    @Expose
    private Page page;

    public Page getPage() {
        return this.page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public List<Component> getComponents() {
        return this.components;
    }

    public void setComponents(List<Component> components) {
        this.components = components;
    }
}
