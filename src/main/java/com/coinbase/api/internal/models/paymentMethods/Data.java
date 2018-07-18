package com.coinbase.api.internal.models.paymentMethods;

import com.coinbase.api.internal.models.paymentMethods.AutoValue_Data.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public abstract class Data {

    public static abstract class Builder {
        public abstract Data build();

        public abstract Builder setMapping(List<Mapping> list);

        public abstract Builder setOneTimeToken(String str);

        public abstract Builder setProcessUrl(String str);
    }

    @SerializedName("mapping")
    public abstract List<Mapping> getMapping();

    @SerializedName("one_time_token")
    public abstract String getOneTimeToken();

    @SerializedName("process_url")
    public abstract String getProcessUrl();

    public static Builder builder() {
        return new Builder().setMapping(new ArrayList());
    }

    public static TypeAdapter<Data> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson).setDefaultMapping(new ArrayList());
    }
}
