package org.slf4j.impl;

import org.slf4j.ILoggerFactory;

public class StaticLoggerBinder {
    public static String REQUESTED_API_VERSION = "1.7.25";
    private static final StaticLoggerBinder SINGLETON = new StaticLoggerBinder();
    private static final String loggerFactoryClassStr = CoinbaseLoggerFactory.class.getName();
    private final ILoggerFactory mLoggerFactory = new CoinbaseLoggerFactory();

    public static final StaticLoggerBinder getSingleton() {
        return SINGLETON;
    }

    private StaticLoggerBinder() {
    }

    public ILoggerFactory getLoggerFactory() {
        return this.mLoggerFactory;
    }

    public String getLoggerFactoryClassStr() {
        return loggerFactoryClassStr;
    }
}
