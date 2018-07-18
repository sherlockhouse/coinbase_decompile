package org.slf4j.impl;

import com.coinbase.android.logging.HandledExceptionLoggerFactory;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

public class CoinbaseLoggerFactory implements ILoggerFactory {
    public Logger getLogger(String name) {
        return new CoinbaseLoggerAdapter(name, HandledExceptionLoggerFactory.get());
    }
}
