package com.fasterxml.jackson.core.util;

import android.support.v7.widget.helper.ItemTouchHelper.Callback;

public class BufferRecycler {
    private static final int[] BYTE_BUFFER_LENGTHS = new int[]{8000, 8000, 2000, 2000};
    private static final int[] CHAR_BUFFER_LENGTHS = new int[]{4000, 4000, Callback.DEFAULT_DRAG_ANIMATION_DURATION, Callback.DEFAULT_DRAG_ANIMATION_DURATION};
    protected final byte[][] _byteBuffers;
    protected final char[][] _charBuffers;

    public BufferRecycler() {
        this(4, 4);
    }

    protected BufferRecycler(int bbCount, int cbCount) {
        this._byteBuffers = new byte[bbCount][];
        this._charBuffers = new char[cbCount][];
    }

    public final byte[] allocByteBuffer(int ix) {
        return allocByteBuffer(ix, 0);
    }

    public byte[] allocByteBuffer(int ix, int minSize) {
        int DEF_SIZE = charBufferLength(ix);
        if (minSize < DEF_SIZE) {
            minSize = DEF_SIZE;
        }
        byte[] buffer = this._byteBuffers[ix];
        if (buffer == null || buffer.length < minSize) {
            return balloc(byteBufferLength(ix));
        }
        this._byteBuffers[ix] = null;
        return buffer;
    }

    public final void releaseByteBuffer(int ix, byte[] buffer) {
        this._byteBuffers[ix] = buffer;
    }

    public final char[] allocCharBuffer(int ix) {
        return allocCharBuffer(ix, 0);
    }

    public char[] allocCharBuffer(int ix, int minSize) {
        int DEF_SIZE = charBufferLength(ix);
        if (minSize < DEF_SIZE) {
            minSize = DEF_SIZE;
        }
        char[] buffer = this._charBuffers[ix];
        if (buffer == null || buffer.length < minSize) {
            return calloc(minSize);
        }
        this._charBuffers[ix] = null;
        return buffer;
    }

    public void releaseCharBuffer(int ix, char[] buffer) {
        this._charBuffers[ix] = buffer;
    }

    protected int byteBufferLength(int ix) {
        return BYTE_BUFFER_LENGTHS[ix];
    }

    protected int charBufferLength(int ix) {
        return CHAR_BUFFER_LENGTHS[ix];
    }

    protected byte[] balloc(int size) {
        return new byte[size];
    }

    protected char[] calloc(int size) {
        return new char[size];
    }
}
