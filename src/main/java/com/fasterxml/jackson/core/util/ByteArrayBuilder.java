package com.fasterxml.jackson.core.util;

import java.io.OutputStream;
import java.util.Iterator;
import java.util.LinkedList;

public final class ByteArrayBuilder extends OutputStream {
    private static final byte[] NO_BYTES = new byte[0];
    private final BufferRecycler _bufferRecycler;
    private byte[] _currBlock;
    private int _currBlockPtr;
    private final LinkedList<byte[]> _pastBlocks;
    private int _pastLen;

    public ByteArrayBuilder() {
        this(null);
    }

    public ByteArrayBuilder(BufferRecycler br) {
        this(br, 500);
    }

    public ByteArrayBuilder(int firstBlockSize) {
        this(null, firstBlockSize);
    }

    public ByteArrayBuilder(BufferRecycler br, int firstBlockSize) {
        this._pastBlocks = new LinkedList();
        this._bufferRecycler = br;
        this._currBlock = br == null ? new byte[firstBlockSize] : br.allocByteBuffer(2);
    }

    public void reset() {
        this._pastLen = 0;
        this._currBlockPtr = 0;
        if (!this._pastBlocks.isEmpty()) {
            this._pastBlocks.clear();
        }
    }

    public void append(int i) {
        if (this._currBlockPtr >= this._currBlock.length) {
            _allocMore();
        }
        byte[] bArr = this._currBlock;
        int i2 = this._currBlockPtr;
        this._currBlockPtr = i2 + 1;
        bArr[i2] = (byte) i;
    }

    public void appendTwoBytes(int b16) {
        if (this._currBlockPtr + 1 < this._currBlock.length) {
            byte[] bArr = this._currBlock;
            int i = this._currBlockPtr;
            this._currBlockPtr = i + 1;
            bArr[i] = (byte) (b16 >> 8);
            bArr = this._currBlock;
            i = this._currBlockPtr;
            this._currBlockPtr = i + 1;
            bArr[i] = (byte) b16;
            return;
        }
        append(b16 >> 8);
        append(b16);
    }

    public void appendThreeBytes(int b24) {
        if (this._currBlockPtr + 2 < this._currBlock.length) {
            byte[] bArr = this._currBlock;
            int i = this._currBlockPtr;
            this._currBlockPtr = i + 1;
            bArr[i] = (byte) (b24 >> 16);
            bArr = this._currBlock;
            i = this._currBlockPtr;
            this._currBlockPtr = i + 1;
            bArr[i] = (byte) (b24 >> 8);
            bArr = this._currBlock;
            i = this._currBlockPtr;
            this._currBlockPtr = i + 1;
            bArr[i] = (byte) b24;
            return;
        }
        append(b24 >> 16);
        append(b24 >> 8);
        append(b24);
    }

    public byte[] toByteArray() {
        int totalLen = this._pastLen + this._currBlockPtr;
        if (totalLen == 0) {
            return NO_BYTES;
        }
        byte[] result = new byte[totalLen];
        int offset = 0;
        Iterator i$ = this._pastBlocks.iterator();
        while (i$.hasNext()) {
            byte[] block = (byte[]) i$.next();
            int len = block.length;
            System.arraycopy(block, 0, result, offset, len);
            offset += len;
        }
        System.arraycopy(this._currBlock, 0, result, offset, this._currBlockPtr);
        offset += this._currBlockPtr;
        if (offset != totalLen) {
            throw new RuntimeException("Internal error: total len assumed to be " + totalLen + ", copied " + offset + " bytes");
        } else if (this._pastBlocks.isEmpty()) {
            return result;
        } else {
            reset();
            return result;
        }
    }

    public void write(byte[] b) {
        write(b, 0, b.length);
    }

    public void write(byte[] b, int off, int len) {
        while (true) {
            int toCopy = Math.min(this._currBlock.length - this._currBlockPtr, len);
            if (toCopy > 0) {
                System.arraycopy(b, off, this._currBlock, this._currBlockPtr, toCopy);
                off += toCopy;
                this._currBlockPtr += toCopy;
                len -= toCopy;
            }
            if (len > 0) {
                _allocMore();
            } else {
                return;
            }
        }
    }

    public void write(int b) {
        append(b);
    }

    public void close() {
    }

    public void flush() {
    }

    private void _allocMore() {
        this._pastLen += this._currBlock.length;
        int newSize = Math.max(this._pastLen >> 1, 1000);
        if (newSize > 262144) {
            newSize = 262144;
        }
        this._pastBlocks.add(this._currBlock);
        this._currBlock = new byte[newSize];
        this._currBlockPtr = 0;
    }
}
