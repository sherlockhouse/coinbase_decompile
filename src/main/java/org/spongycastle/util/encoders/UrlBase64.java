package org.spongycastle.util.encoders;

import java.io.ByteArrayOutputStream;

public class UrlBase64 {
    private static final Encoder encoder = new UrlBase64Encoder();

    public static byte[] encode(byte[] data) {
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        try {
            encoder.encode(data, 0, data.length, bOut);
            return bOut.toByteArray();
        } catch (Exception e) {
            throw new EncoderException("exception encoding URL safe base64 data: " + e.getMessage(), e);
        }
    }
}
