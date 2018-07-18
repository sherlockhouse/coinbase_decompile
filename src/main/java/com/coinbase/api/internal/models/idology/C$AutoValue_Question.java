package com.coinbase.api.internal.models.idology;

import com.google.gson.annotations.SerializedName;
import java.util.List;

abstract class C$AutoValue_Question extends Question {
    private final List<String> answers;
    private final String prompt;
    private final String type;

    static final class Builder extends com.coinbase.api.internal.models.idology.Question.Builder {
        private List<String> answers;
        private String prompt;
        private String type;

        Builder() {
        }

        Builder(Question source) {
            this.type = source.getType();
            this.prompt = source.getPrompt();
            this.answers = source.getAnswers();
        }

        public com.coinbase.api.internal.models.idology.Question.Builder setType(String type) {
            this.type = type;
            return this;
        }

        public com.coinbase.api.internal.models.idology.Question.Builder setPrompt(String prompt) {
            this.prompt = prompt;
            return this;
        }

        public com.coinbase.api.internal.models.idology.Question.Builder setAnswers(List<String> answers) {
            this.answers = answers;
            return this;
        }

        public Question build() {
            return new AutoValue_Question(this.type, this.prompt, this.answers);
        }
    }

    C$AutoValue_Question(String type, String prompt, List<String> answers) {
        this.type = type;
        this.prompt = prompt;
        this.answers = answers;
    }

    @SerializedName("type")
    public String getType() {
        return this.type;
    }

    @SerializedName("prompt")
    public String getPrompt() {
        return this.prompt;
    }

    @SerializedName("answers")
    public List<String> getAnswers() {
        return this.answers;
    }

    public String toString() {
        return "Question{type=" + this.type + ", prompt=" + this.prompt + ", answers=" + this.answers + "}";
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Question)) {
            return false;
        }
        Question that = (Question) o;
        if (this.type != null) {
            if (this.type.equals(that.getType())) {
            }
            return false;
        }
        if (this.prompt != null) {
            if (this.prompt.equals(that.getPrompt())) {
            }
            return false;
        }
        if (this.answers == null) {
            if (that.getAnswers() == null) {
                return true;
            }
        } else if (this.answers.equals(that.getAnswers())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((((1 * 1000003) ^ (this.type == null ? 0 : this.type.hashCode())) * 1000003) ^ (this.prompt == null ? 0 : this.prompt.hashCode())) * 1000003;
        if (this.answers != null) {
            i = this.answers.hashCode();
        }
        return h ^ i;
    }
}
