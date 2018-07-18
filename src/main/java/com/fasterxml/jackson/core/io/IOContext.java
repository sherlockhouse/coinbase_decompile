package com.fasterxml.jackson.core.io;

import com.fasterxml.jackson.core.util.BufferRecycler;
import com.fasterxml.jackson.core.util.TextBuffer;

public class IOContext {
    protected byte[] _base64Buffer = null;
    protected final BufferRecycler _bufferRecycler;
    protected char[] _concatCBuffer = null;
    protected final boolean _managedResource;
    protected char[] _nameCopyBuffer = null;
    protected byte[] _readIOBuffer = null;
    protected final Object _sourceRef;
    protected char[] _tokenCBuffer = null;
    protected byte[] _writeEncodingBuffer = null;

    public IOContext(BufferRecycler br, Object sourceRef, boolean managedResource) {
        this._bufferRecycler = br;
        this._sourceRef = sourceRef;
        this._managedResource = managedResource;
    }

    public Object getSourceReference() {
        return this._sourceRef;
    }

    public boolean isResourceManaged() {
        return this._managedResource;
    }

    public TextBuffer constructTextBuffer() {
        return new TextBuffer(this._bufferRecycler);
    }

    public byte[] allocBase64Buffer() {
        _verifyAlloc(this._base64Buffer);
        byte[] allocByteBuffer = this._bufferRecycler.allocByteBuffer(3);
        this._base64Buffer = allocByteBuffer;
        return allocByteBuffer;
    }

    public char[] allocTokenBuffer() {
        _verifyAlloc(this._tokenCBuffer);
        char[] allocCharBuffer = this._bufferRecycler.allocCharBuffer(0);
        this._tokenCBuffer = allocCharBuffer;
        return allocCharBuffer;
    }

    public char[] allocTokenBuffer(int minSize) {
        _verifyAlloc(this._tokenCBuffer);
        char[] allocCharBuffer = this._bufferRecycler.allocCharBuffer(0, minSize);
        this._tokenCBuffer = allocCharBuffer;
        return allocCharBuffer;
    }

    public char[] allocConcatBuffer() {
        _verifyAlloc(this._concatCBuffer);
        char[] allocCharBuffer = this._bufferRecycler.allocCharBuffer(1);
        this._concatCBuffer = allocCharBuffer;
        return allocCharBuffer;
    }

    public char[] allocNameCopyBuffer(int minSize) {
        _verifyAlloc(this._nameCopyBuffer);
        char[] allocCharBuffer = this._bufferRecycler.allocCharBuffer(3, minSize);
        this._nameCopyBuffer = allocCharBuffer;
        return allocCharBuffer;
    }

    public void releaseBase64Buffer(byte[] buf) {
        if (buf != null) {
            _verifyRelease(buf, this._base64Buffer);
            this._base64Buffer = null;
            this._bufferRecycler.releaseByteBuffer(3, buf);
        }
    }

    public void releaseTokenBuffer(char[] buf) {
        if (buf != null) {
            _verifyRelease(buf, this._tokenCBuffer);
            this._tokenCBuffer = null;
            this._bufferRecycler.releaseCharBuffer(0, buf);
        }
    }

    public void releaseConcatBuffer(char[] buf) {
        if (buf != null) {
            _verifyRelease(buf, this._concatCBuffer);
            this._concatCBuffer = null;
            this._bufferRecycler.releaseCharBuffer(1, buf);
        }
    }

    public void releaseNameCopyBuffer(char[] buf) {
        if (buf != null) {
            _verifyRelease(buf, this._nameCopyBuffer);
            this._nameCopyBuffer = null;
            this._bufferRecycler.releaseCharBuffer(3, buf);
        }
    }

    protected void _verifyAlloc(Object buffer) {
        if (buffer != null) {
            throw new IllegalStateException("Trying to call same allocXxx() method second time");
        }
    }

    protected void _verifyRelease(byte[] toRelease, byte[] src) {
        if (toRelease != src && toRelease.length <= src.length) {
            throw wrongBuf();
        }
    }

    protected void _verifyRelease(char[] toRelease, char[] src) {
        if (toRelease != src && toRelease.length <= src.length) {
            throw wrongBuf();
        }
    }

    private IllegalArgumentException wrongBuf() {
        return new IllegalArgumentException("Trying to release buffer not owned by the context");
    }
}
