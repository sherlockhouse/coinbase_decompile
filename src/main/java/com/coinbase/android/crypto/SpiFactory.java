package com.coinbase.android.crypto;

import java.security.NoSuchAlgorithmException;

public interface SpiFactory<T> {
    T create(Object obj) throws NoSuchAlgorithmException;
}
