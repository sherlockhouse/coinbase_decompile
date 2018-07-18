package com.coinbase.api.internal.models.status;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Component {
    public static String COINBASE_MOBILE = "Coinbase - Mobile";
    public static String MAJOR_OUTAGE = "major_outage";
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("description")
    @Expose
    private Object description;
    @SerializedName("group")
    @Expose
    private Boolean group;
    @SerializedName("group_id")
    @Expose
    private Object groupId;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("only_show_if_degraded")
    @Expose
    private Boolean onlyShowIfDegraded;
    @SerializedName("page_id")
    @Expose
    private String pageId;
    @SerializedName("position")
    @Expose
    private Integer position;
    @SerializedName("showcase")
    @Expose
    private Boolean showcase;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getPosition() {
        return this.position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Object getDescription() {
        return this.description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public Object getGroupId() {
        return this.groupId;
    }

    public void setGroupId(Object groupId) {
        this.groupId = groupId;
    }

    public Boolean getShowcase() {
        return this.showcase;
    }

    public void setShowcase(Boolean showcase) {
        this.showcase = showcase;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPageId() {
        return this.pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public Boolean getGroup() {
        return this.group;
    }

    public void setGroup(Boolean group) {
        this.group = group;
    }

    public Boolean getOnlyShowIfDegraded() {
        return this.onlyShowIfDegraded;
    }

    public void setOnlyShowIfDegraded(Boolean onlyShowIfDegraded) {
        this.onlyShowIfDegraded = onlyShowIfDegraded;
    }
}
