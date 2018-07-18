package com.coinbase.api.internal.models.idology;

import com.google.gson.annotations.SerializedName;
import java.util.List;

abstract class C$AutoValue_UserAnswers extends UserAnswers {
    private final List<Answer> answers;

    static final class Builder extends com.coinbase.api.internal.models.idology.UserAnswers.Builder {
        private List<Answer> answers;

        Builder() {
        }

        Builder(UserAnswers source) {
            this.answers = source.getAnswers();
        }

        public com.coinbase.api.internal.models.idology.UserAnswers.Builder setAnswers(List<Answer> answers) {
            this.answers = answers;
            return this;
        }

        public UserAnswers build() {
            return new AutoValue_UserAnswers(this.answers);
        }
    }

    C$AutoValue_UserAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    @SerializedName("answers")
    public List<Answer> getAnswers() {
        return this.answers;
    }

    public String toString() {
        return "UserAnswers{answers=" + this.answers + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof UserAnswers)) {
            return false;
        }
        UserAnswers that = (UserAnswers) o;
        if (this.answers != null) {
            return this.answers.equals(that.getAnswers());
        }
        if (that.getAnswers() != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (1 * 1000003) ^ (this.answers == null ? 0 : this.answers.hashCode());
    }
}
