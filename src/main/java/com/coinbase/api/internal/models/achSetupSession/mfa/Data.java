package com.coinbase.api.internal.models.achSetupSession.mfa;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class Data<T> {
    @SerializedName("answers")
    @Expose
    private List<String> answers = new ArrayList();
    @SerializedName("mask")
    @Expose
    private String mask;
    @SerializedName("question")
    @Expose
    private String question;
    @SerializedName("type")
    @Expose
    private String type;

    public enum Type {
        PHONE("phone"),
        EMAIL("email"),
        CARD("card");
        
        private String _value;

        private Type(String value) {
            this._value = value;
        }

        @JsonCreator
        public static Type create(String val) {
            for (Type type : values()) {
                if (type.toString().equalsIgnoreCase(val)) {
                    return type;
                }
            }
            return null;
        }

        @JsonValue
        public String toString() {
            return this._value;
        }
    }

    public String getQuestion() {
        return this.question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getAnswers() {
        return this.answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public String getMask() {
        return this.mask;
    }

    public void setMask(String mask) {
        this.mask = mask;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
