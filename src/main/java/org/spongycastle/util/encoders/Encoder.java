package org.spongycastle.util.encoders;

import java.io.IOException;
import java.io.OutputStream;

public interface Encoder {
    int encode(byte[] bArr, int i, int i2, OutputStream outputStream) throws IOException;
}
