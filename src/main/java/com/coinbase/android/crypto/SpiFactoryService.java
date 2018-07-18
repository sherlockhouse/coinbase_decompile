package com.coinbase.android.crypto;

import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Provider.Service;
import java.util.List;
import java.util.Map;

public class SpiFactoryService extends Service {
    private SpiFactory<?> factory;

    public SpiFactoryService(Provider provider, String type, String algorithm, String className, List<String> aliases, Map<String, String> attributes, SpiFactory<?> factory) {
        super(provider, type, algorithm, className, aliases, attributes);
        this.factory = factory;
    }

    public Object newInstance(Object constructorParameter) throws NoSuchAlgorithmException {
        return this.factory.create(constructorParameter);
    }
}
