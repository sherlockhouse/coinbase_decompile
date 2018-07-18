package org.spongycastle.util.encoders;

import java.io.IOException;
import java.io.OutputStream;

public class Base64Encoder implements Encoder {
    protected final byte[] decodingTable = new byte[128];
    protected final byte[] encodingTable = new byte[]{(byte) 65, (byte) 66, (byte) 67, (byte) 68, (byte) 69, (byte) 70, (byte) 71, (byte) 72, (byte) 73, (byte) 74, (byte) 75, (byte) 76, (byte) 77, (byte) 78, (byte) 79, (byte) 80, (byte) 81, (byte) 82, (byte) 83, (byte) 84, (byte) 85, (byte) 86, (byte) 87, (byte) 88, (byte) 89, (byte) 90, (byte) 97, (byte) 98, (byte) 99, (byte) 100, (byte) 101, (byte) 102, (byte) 103, (byte) 104, (byte) 105, (byte) 106, (byte) 107, (byte) 108, (byte) 109, (byte) 110, (byte) 111, (byte) 112, (byte) 113, (byte) 114, (byte) 115, (byte) 116, (byte) 117, (byte) 118, (byte) 119, (byte) 120, (byte) 121, (byte) 122, (byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 43, (byte) 47};
    protected byte padding = (byte) 61;

    protected void initialiseDecodingTable() {
        int i;
        for (i = 0; i < this.decodingTable.length; i++) {
            this.decodingTable[i] = (byte) -1;
        }
        for (i = 0; i < this.encodingTable.length; i++) {
            this.decodingTable[this.encodingTable[i]] = (byte) i;
        }
    }

    public Base64Encoder() {
        initialiseDecodingTable();
    }

    public int encode(byte[] data, int off, int length, OutputStream out) throws IOException {
        int i;
        int modulus = length % 3;
        int dataLength = length - modulus;
        for (int i2 = off; i2 < off + dataLength; i2 += 3) {
            int a1 = data[i2] & 255;
            int a2 = data[i2 + 1] & 255;
            int a3 = data[i2 + 2] & 255;
            out.write(this.encodingTable[(a1 >>> 2) & 63]);
            out.write(this.encodingTable[((a1 << 4) | (a2 >>> 4)) & 63]);
            out.write(this.encodingTable[((a2 << 2) | (a3 >>> 6)) & 63]);
            out.write(this.encodingTable[a3 & 63]);
        }
        int d1;
        int b2;
        OutputStream outputStream;
        switch (modulus) {
            case 1:
                d1 = data[off + dataLength] & 255;
                b2 = (d1 << 4) & 63;
                outputStream = out;
                outputStream.write(this.encodingTable[(d1 >>> 2) & 63]);
                out.write(this.encodingTable[b2]);
                out.write(this.padding);
                out.write(this.padding);
                break;
            case 2:
                d1 = data[off + dataLength] & 255;
                int d2 = data[(off + dataLength) + 1] & 255;
                b2 = ((d1 << 4) | (d2 >>> 4)) & 63;
                int b3 = (d2 << 2) & 63;
                outputStream = out;
                outputStream.write(this.encodingTable[(d1 >>> 2) & 63]);
                out.write(this.encodingTable[b2]);
                out.write(this.encodingTable[b3]);
                out.write(this.padding);
                break;
        }
        int i3 = (dataLength / 3) * 4;
        if (modulus == 0) {
            i = 0;
        } else {
            i = 4;
        }
        return i + i3;
    }
}
