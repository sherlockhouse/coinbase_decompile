package com.coinbase.v1.entity;

import java.io.Serializable;
import org.joda.time.DateTime;

public class Application implements Serializable {
    private static final long serialVersionUID = -8979526541382258919L;
    private String _clientId;
    private String _clientSecret;
    private DateTime _createdAt;
    private String _id;
    private String _name;
    private Integer _numUsers;
    private String _redirectUri;

    public String getId() {
        return this._id;
    }

    public void setId(String id) {
        this._id = id;
    }

    public DateTime getCreatedAt() {
        return this._createdAt;
    }

    public void setCreatedAt(DateTime createdAt) {
        this._createdAt = createdAt;
    }

    public String getName() {
        return this._name;
    }

    public void setName(String name) {
        this._name = name;
    }

    public String getRedirectUri() {
        return this._redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this._redirectUri = redirectUri;
    }

    public Integer getNumUsers() {
        return this._numUsers;
    }

    public void setNumUsers(Integer numUsers) {
        this._numUsers = numUsers;
    }

    public String getClientId() {
        return this._clientId;
    }

    public void setClientId(String clientId) {
        this._clientId = clientId;
    }

    public String getClientSecret() {
        return this._clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this._clientSecret = clientSecret;
    }
}
