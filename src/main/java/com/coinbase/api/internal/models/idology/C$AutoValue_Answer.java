package com.coinbase.api.internal.models.idology;

import com.google.gson.annotations.SerializedName;

abstract class C$AutoValue_Answer extends Answer {
    private final String answer;
    private final String type;

    static final class Builder extends com.coinbase.api.internal.models.idology.Answer.Builder {
        private String answer;
        private String type;

        Builder() {
        }

        Builder(Answer source) {
            this.type = source.getType();
            this.answer = source.getAnswer();
        }

        public com.coinbase.api.internal.models.idology.Answer.Builder setType(String type) {
            this.type = type;
            return this;
        }

        public com.coinbase.api.internal.models.idology.Answer.Builder setAnswer(String answer) {
            this.answer = answer;
            return this;
        }

        public Answer build() {
            return new AutoValue_Answer(this.type, this.answer);
        }
    }

    C$AutoValue_Answer(String type, String answer) {
        this.type = type;
        this.answer = answer;
    }

    @SerializedName("type")
    public String getType() {
        return this.type;
    }

    @SerializedName("answer")
    public String getAnswer() {
        return this.answer;
    }

    public String toString() {
        return "Answer{type=" + this.type + ", answer=" + this.answer + "}";
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Answer)) {
            return false;
        }
        Answer that = (Answer) o;
        if (this.type != null) {
            if (this.type.equals(that.getType())) {
            }
            return false;
        }
        if (this.answer == null) {
            if (that.getAnswer() == null) {
                return true;
            }
        } else if (this.answer.equals(that.getAnswer())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((1 * 1000003) ^ (this.type == null ? 0 : this.type.hashCode())) * 1000003;
        if (this.answer != null) {
            i = this.answer.hashCode();
        }
        return h ^ i;
    }
}
