package com.fasterxml.jackson.core.util;

import java.util.concurrent.ConcurrentHashMap;

public final class InternCache extends ConcurrentHashMap<String, String> {
    public static final InternCache instance = new InternCache();
    private final Object lock = new Object();

    private InternCache() {
        super(180, 0.8f, 4);
    }

    public String intern(String input) {
        String result = (String) get(input);
        if (result != null) {
            return result;
        }
        if (size() >= 180) {
            synchronized (this.lock) {
                if (size() >= 180) {
                    clear();
                }
            }
        }
        result = input.intern();
        put(result, result);
        return result;
    }
}
