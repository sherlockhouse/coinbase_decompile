package com.worldpay.cse.jwe;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class WPJWEHeader {
    @SerializedName("alg")
    private String algorithm;
    @SerializedName("com.worldpay.apiVersion")
    private String apiVersion;
    @SerializedName("com.worldpay.channel")
    private String channel;
    @SerializedName("enc")
    private String encryption;
    @SerializedName("kid")
    private String kid;
    @SerializedName("com.worldpay.libVersion")
    private String libVersion;

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public void setEncryption(String encryption) {
        this.encryption = encryption;
    }

    public void setKid(String kid) {
        this.kid = kid;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public void setLibVersion(String libVersion) {
        this.libVersion = libVersion;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String toString() {
        return new Gson().toJson((Object) this);
    }
}
