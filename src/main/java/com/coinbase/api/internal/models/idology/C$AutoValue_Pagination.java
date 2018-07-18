package com.coinbase.api.internal.models.idology;

import com.google.gson.annotations.SerializedName;

abstract class C$AutoValue_Pagination extends Pagination {
    private final Object endingBefore;
    private final Integer limit;
    private final Object nextUri;
    private final String order;
    private final Object previousUri;
    private final Object startingAfter;

    static final class Builder extends com.coinbase.api.internal.models.idology.Pagination.Builder {
        private Object endingBefore;
        private Integer limit;
        private Object nextUri;
        private String order;
        private Object previousUri;
        private Object startingAfter;

        Builder() {
        }

        Builder(Pagination source) {
            this.endingBefore = source.getEndingBefore();
            this.startingAfter = source.getStartingAfter();
            this.order = source.getOrder();
            this.limit = source.getLimit();
            this.previousUri = source.getPreviousUri();
            this.nextUri = source.getNextUri();
        }

        public com.coinbase.api.internal.models.idology.Pagination.Builder setEndingBefore(Object endingBefore) {
            this.endingBefore = endingBefore;
            return this;
        }

        public com.coinbase.api.internal.models.idology.Pagination.Builder setStartingAfter(Object startingAfter) {
            this.startingAfter = startingAfter;
            return this;
        }

        public com.coinbase.api.internal.models.idology.Pagination.Builder setOrder(String order) {
            this.order = order;
            return this;
        }

        public com.coinbase.api.internal.models.idology.Pagination.Builder setLimit(Integer limit) {
            this.limit = limit;
            return this;
        }

        public com.coinbase.api.internal.models.idology.Pagination.Builder setPreviousUri(Object previousUri) {
            this.previousUri = previousUri;
            return this;
        }

        public com.coinbase.api.internal.models.idology.Pagination.Builder setNextUri(Object nextUri) {
            this.nextUri = nextUri;
            return this;
        }

        public Pagination build() {
            return new AutoValue_Pagination(this.endingBefore, this.startingAfter, this.order, this.limit, this.previousUri, this.nextUri);
        }
    }

    C$AutoValue_Pagination(Object endingBefore, Object startingAfter, String order, Integer limit, Object previousUri, Object nextUri) {
        this.endingBefore = endingBefore;
        this.startingAfter = startingAfter;
        this.order = order;
        this.limit = limit;
        this.previousUri = previousUri;
        this.nextUri = nextUri;
    }

    @SerializedName("ending_before")
    public Object getEndingBefore() {
        return this.endingBefore;
    }

    @SerializedName("starting_after")
    public Object getStartingAfter() {
        return this.startingAfter;
    }

    @SerializedName("order")
    public String getOrder() {
        return this.order;
    }

    @SerializedName("limit")
    public Integer getLimit() {
        return this.limit;
    }

    @SerializedName("previous_uri")
    public Object getPreviousUri() {
        return this.previousUri;
    }

    @SerializedName("next_uri")
    public Object getNextUri() {
        return this.nextUri;
    }

    public String toString() {
        return "Pagination{endingBefore=" + this.endingBefore + ", startingAfter=" + this.startingAfter + ", order=" + this.order + ", limit=" + this.limit + ", previousUri=" + this.previousUri + ", nextUri=" + this.nextUri + "}";
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Pagination)) {
            return false;
        }
        Pagination that = (Pagination) o;
        if (this.endingBefore != null) {
            if (this.endingBefore.equals(that.getEndingBefore())) {
            }
            return false;
        }
        if (this.startingAfter != null) {
            if (this.startingAfter.equals(that.getStartingAfter())) {
            }
            return false;
        }
        if (this.order != null) {
            if (this.order.equals(that.getOrder())) {
            }
            return false;
        }
        if (this.limit != null) {
            if (this.limit.equals(that.getLimit())) {
            }
            return false;
        }
        if (this.previousUri != null) {
            if (this.previousUri.equals(that.getPreviousUri())) {
            }
            return false;
        }
        if (this.nextUri == null) {
            if (that.getNextUri() == null) {
                return true;
            }
        } else if (this.nextUri.equals(that.getNextUri())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((((((((((1 * 1000003) ^ (this.endingBefore == null ? 0 : this.endingBefore.hashCode())) * 1000003) ^ (this.startingAfter == null ? 0 : this.startingAfter.hashCode())) * 1000003) ^ (this.order == null ? 0 : this.order.hashCode())) * 1000003) ^ (this.limit == null ? 0 : this.limit.hashCode())) * 1000003) ^ (this.previousUri == null ? 0 : this.previousUri.hashCode())) * 1000003;
        if (this.nextUri != null) {
            i = this.nextUri.hashCode();
        }
        return h ^ i;
    }
}
