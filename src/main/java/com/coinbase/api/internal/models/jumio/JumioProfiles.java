package com.coinbase.api.internal.models.jumio;

import android.content.Context;
import com.coinbase.android.R;
import com.coinbase.api.internal.models.Pagination;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class JumioProfiles {
    @SerializedName("data")
    @Expose
    private List<Data> data = new ArrayList();
    @SerializedName("pagination")
    @Expose
    private Pagination pagination;

    public enum Status {
        COMPLETED,
        FAILED,
        PENDING,
        INCOMPLETE,
        UNKNOW;

        public String toString() {
            return super.toString().toLowerCase(Locale.US);
        }
    }

    public enum Type {
        DRIVERS_LICENCE,
        PASSPORT,
        ID_CARD,
        UNKNOWN;

        public String toString() {
            return super.toString().toLowerCase(Locale.US);
        }
    }

    public Pagination getPagination() {
        return this.pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public List<Data> getData() {
        return this.data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public Status getJumioProfileStatus() {
        if (doesJumioProfilesContainType(Status.COMPLETED)) {
            return Status.COMPLETED;
        }
        if (doesJumioProfilesContainType(Status.PENDING)) {
            return Status.PENDING;
        }
        return Status.INCOMPLETE;
    }

    public boolean doesJumioProfilesContainType(Status status) {
        for (Data profile : getData()) {
            if (status == profile.getStatus()) {
                return true;
            }
        }
        return false;
    }

    public static String getDisplayName(Context context, Type type) {
        if (context == null) {
            return "";
        }
        switch (type) {
            case DRIVERS_LICENCE:
                return context.getString(R.string.drivers_license);
            case PASSPORT:
                return context.getString(R.string.passport);
            case ID_CARD:
                return context.getString(R.string.id_card);
            default:
                return "";
        }
    }
}
