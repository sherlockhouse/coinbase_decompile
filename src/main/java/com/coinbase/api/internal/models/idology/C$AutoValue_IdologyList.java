package com.coinbase.api.internal.models.idology;

import com.google.gson.annotations.SerializedName;
import java.util.List;

abstract class C$AutoValue_IdologyList extends IdologyList {
    private final List<Data> data;
    private final Pagination pagination;

    static final class Builder extends com.coinbase.api.internal.models.idology.IdologyList.Builder {
        private List<Data> data;
        private Pagination pagination;

        Builder() {
        }

        Builder(IdologyList source) {
            this.pagination = source.getPagination();
            this.data = source.getData();
        }

        public com.coinbase.api.internal.models.idology.IdologyList.Builder setPagination(Pagination pagination) {
            this.pagination = pagination;
            return this;
        }

        public com.coinbase.api.internal.models.idology.IdologyList.Builder setData(List<Data> data) {
            this.data = data;
            return this;
        }

        public IdologyList build() {
            return new AutoValue_IdologyList(this.pagination, this.data);
        }
    }

    C$AutoValue_IdologyList(Pagination pagination, List<Data> data) {
        this.pagination = pagination;
        this.data = data;
    }

    @SerializedName("pagination")
    public Pagination getPagination() {
        return this.pagination;
    }

    @SerializedName("data")
    public List<Data> getData() {
        return this.data;
    }

    public String toString() {
        return "IdologyList{pagination=" + this.pagination + ", data=" + this.data + "}";
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof IdologyList)) {
            return false;
        }
        IdologyList that = (IdologyList) o;
        if (this.pagination != null) {
            if (this.pagination.equals(that.getPagination())) {
            }
            return false;
        }
        if (this.data == null) {
            if (that.getData() == null) {
                return true;
            }
        } else if (this.data.equals(that.getData())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((1 * 1000003) ^ (this.pagination == null ? 0 : this.pagination.hashCode())) * 1000003;
        if (this.data != null) {
            i = this.data.hashCode();
        }
        return h ^ i;
    }
}
