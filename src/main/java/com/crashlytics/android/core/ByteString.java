package com.crashlytics.android.core;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

final class ByteString {
    public static final ByteString EMPTY = new ByteString(new byte[0]);
    private final byte[] bytes;
    private volatile int hash = 0;

    private ByteString(byte[] bytes) {
        this.bytes = bytes;
    }

    public int size() {
        return this.bytes.length;
    }

    public static ByteString copyFrom(byte[] bytes, int offset, int size) {
        byte[] copy = new byte[size];
        System.arraycopy(bytes, offset, copy, 0, size);
        return new ByteString(copy);
    }

    public static ByteString copyFromUtf8(String text) {
        try {
            return new ByteString(text.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 not supported.", e);
        }
    }

    public void copyTo(byte[] target, int sourceOffset, int targetOffset, int size) {
        System.arraycopy(this.bytes, sourceOffset, target, targetOffset, size);
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ByteString)) {
            return false;
        }
        ByteString other = (ByteString) o;
        int size = this.bytes.length;
        if (size != other.bytes.length) {
            return false;
        }
        byte[] thisBytes = this.bytes;
        byte[] otherBytes = other.bytes;
        for (int i = 0; i < size; i++) {
            if (thisBytes[i] != otherBytes[i]) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int h = this.hash;
        if (h == 0) {
            byte[] thisBytes = this.bytes;
            int size = this.bytes.length;
            h = size;
            for (int i = 0; i < size; i++) {
                h = (h * 31) + thisBytes[i];
            }
            if (h == 0) {
                h = 1;
            }
            this.hash = h;
        }
        return h;
    }

    public InputStream newInput() {
        return new ByteArrayInputStream(this.bytes);
    }
}
