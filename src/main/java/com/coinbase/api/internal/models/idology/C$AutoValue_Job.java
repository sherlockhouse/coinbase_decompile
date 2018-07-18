package com.coinbase.api.internal.models.idology;

import com.google.gson.annotations.SerializedName;

abstract class C$AutoValue_Job extends Job {
    private final String employer;
    private final String title;
    private final String titleDesc;

    static final class Builder extends com.coinbase.api.internal.models.idology.Job.Builder {
        private String employer;
        private String title;
        private String titleDesc;

        Builder() {
        }

        Builder(Job source) {
            this.employer = source.getEmployer();
            this.title = source.getTitle();
            this.titleDesc = source.getTitleDesc();
        }

        public com.coinbase.api.internal.models.idology.Job.Builder setEmployer(String employer) {
            this.employer = employer;
            return this;
        }

        public com.coinbase.api.internal.models.idology.Job.Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public com.coinbase.api.internal.models.idology.Job.Builder setTitleDesc(String titleDesc) {
            this.titleDesc = titleDesc;
            return this;
        }

        public Job build() {
            return new AutoValue_Job(this.employer, this.title, this.titleDesc);
        }
    }

    C$AutoValue_Job(String employer, String title, String titleDesc) {
        this.employer = employer;
        this.title = title;
        this.titleDesc = titleDesc;
    }

    @SerializedName("employer")
    public String getEmployer() {
        return this.employer;
    }

    @SerializedName("title")
    public String getTitle() {
        return this.title;
    }

    @SerializedName("title_desc")
    public String getTitleDesc() {
        return this.titleDesc;
    }

    public String toString() {
        return "Job{employer=" + this.employer + ", title=" + this.title + ", titleDesc=" + this.titleDesc + "}";
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Job)) {
            return false;
        }
        Job that = (Job) o;
        if (this.employer != null) {
            if (this.employer.equals(that.getEmployer())) {
            }
            return false;
        }
        if (this.title != null) {
            if (this.title.equals(that.getTitle())) {
            }
            return false;
        }
        if (this.titleDesc == null) {
            if (that.getTitleDesc() == null) {
                return true;
            }
        } else if (this.titleDesc.equals(that.getTitleDesc())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((((1 * 1000003) ^ (this.employer == null ? 0 : this.employer.hashCode())) * 1000003) ^ (this.title == null ? 0 : this.title.hashCode())) * 1000003;
        if (this.titleDesc != null) {
            i = this.titleDesc.hashCode();
        }
        return h ^ i;
    }
}
