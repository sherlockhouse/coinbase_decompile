package com.coinbase.api.internal.models.achSetupSession.mfa;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class Mfa {
    @SerializedName("data")
    @Expose
    private List<Data> data = new ArrayList();
    private Type type;

    public enum Type {
        QUESTIONS,
        CODE,
        SELECTIONS,
        UNKONWN;

        public String toString() {
            return super.toString().toLowerCase();
        }
    }

    public Type getType() {
        return this.type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<Data> getData() {
        return this.data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }
}
