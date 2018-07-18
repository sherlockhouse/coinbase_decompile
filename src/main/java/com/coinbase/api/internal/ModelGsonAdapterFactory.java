package com.coinbase.api.internal;

import com.google.gson.TypeAdapterFactory;

public abstract class ModelGsonAdapterFactory implements TypeAdapterFactory {
    public static TypeAdapterFactory create() {
        return new AutoValueGson_ModelGsonAdapterFactory();
    }
}
