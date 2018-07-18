package com.worldpay.cse.jwe;

import java.security.SecureRandom;
import java.util.Random;

class WPKeyGen {
    private static Random randomGen = new SecureRandom();

    public static byte[] generateKey(int keySize) {
        byte[] bytes = new byte[(keySize / 8)];
        randomGen.nextBytes(bytes);
        return bytes;
    }
}
