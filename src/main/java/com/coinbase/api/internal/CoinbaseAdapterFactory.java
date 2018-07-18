package com.coinbase.api.internal;

import com.google.gson.TypeAdapterFactory;

public abstract class CoinbaseAdapterFactory implements TypeAdapterFactory {
    public static TypeAdapterFactory create() {
        return new AutoValueGson_CoinbaseAdapterFactory();
    }
}
