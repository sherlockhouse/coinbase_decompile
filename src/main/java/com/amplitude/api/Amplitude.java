package com.amplitude.api;

import java.util.HashMap;
import java.util.Map;

public class Amplitude {
    static final Map<String, AmplitudeClient> instances = new HashMap();

    public static AmplitudeClient getInstance() {
        return getInstance(null);
    }

    public static synchronized AmplitudeClient getInstance(String instance) {
        AmplitudeClient client;
        synchronized (Amplitude.class) {
            instance = Utils.normalizeInstanceName(instance);
            client = (AmplitudeClient) instances.get(instance);
            if (client == null) {
                client = new AmplitudeClient(instance);
                instances.put(instance, client);
            }
        }
        return client;
    }
}
