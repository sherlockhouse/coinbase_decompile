package com.coinbase.v2.models.transactions;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Entity {
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("avatar_url")
    @Expose
    private String avatarUrl;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("profile_bio")
    @Expose
    private Object profileBio;
    @SerializedName("profile_location")
    @Expose
    private Object profileLocation;
    @SerializedName("profile_url")
    @Expose
    private Object profileUrl;
    @SerializedName("resource")
    @Expose
    private String resource;
    @SerializedName("resource_path")
    @Expose
    private String resourcePath;
    @SerializedName("username")
    @Expose
    private Object username;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getUsername() {
        return this.username;
    }

    public void setUsername(Object username) {
        this.username = username;
    }

    public Object getProfileLocation() {
        return this.profileLocation;
    }

    public void setProfileLocation(Object profileLocation) {
        this.profileLocation = profileLocation;
    }

    public Object getProfileBio() {
        return this.profileBio;
    }

    public void setProfileBio(Object profileBio) {
        this.profileBio = profileBio;
    }

    public Object getProfileUrl() {
        return this.profileUrl;
    }

    public void setProfileUrl(Object profileUrl) {
        this.profileUrl = profileUrl;
    }

    public String getAvatarUrl() {
        return this.avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getResource() {
        return this.resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getResourcePath() {
        return this.resourcePath;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
