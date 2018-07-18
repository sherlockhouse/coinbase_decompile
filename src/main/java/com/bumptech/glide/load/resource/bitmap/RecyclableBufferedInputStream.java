package com.bumptech.glide.load.resource.bitmap;

import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class RecyclableBufferedInputStream extends FilterInputStream {
    private volatile byte[] buf;
    private final ArrayPool byteArrayPool;
    private int count;
    private int marklimit;
    private int markpos;
    private int pos;

    public static class InvalidMarkException extends IOException {
        public InvalidMarkException(String detailMessage) {
            super(detailMessage);
        }
    }

    public RecyclableBufferedInputStream(InputStream in, ArrayPool byteArrayPool) {
        this(in, byteArrayPool, 65536);
    }

    RecyclableBufferedInputStream(InputStream in, ArrayPool byteArrayPool, int bufferSize) {
        super(in);
        this.markpos = -1;
        this.byteArrayPool = byteArrayPool;
        this.buf = (byte[]) byteArrayPool.get(bufferSize, byte[].class);
    }

    public synchronized int available() throws IOException {
        InputStream localIn;
        localIn = this.in;
        if (this.buf == null || localIn == null) {
            throw streamClosed();
        }
        return (this.count - this.pos) + localIn.available();
    }

    private static IOException streamClosed() throws IOException {
        throw new IOException("BufferedInputStream is closed");
    }

    public synchronized void fixMarkLimit() {
        this.marklimit = this.buf.length;
    }

    public synchronized void release() {
        if (this.buf != null) {
            this.byteArrayPool.put(this.buf, byte[].class);
            this.buf = null;
        }
    }

    public void close() throws IOException {
        if (this.buf != null) {
            this.byteArrayPool.put(this.buf, byte[].class);
            this.buf = null;
        }
        InputStream localIn = this.in;
        this.in = null;
        if (localIn != null) {
            localIn.close();
        }
    }

    private int fillbuf(InputStream localIn, byte[] localBuf) throws IOException {
        if (this.markpos == -1 || this.pos - this.markpos >= this.marklimit) {
            int result = localIn.read(localBuf);
            if (result > 0) {
                this.markpos = -1;
                this.pos = 0;
                this.count = result;
            }
            return result;
        }
        int i;
        if (this.markpos == 0 && this.marklimit > localBuf.length && this.count == localBuf.length) {
            int newLength = localBuf.length * 2;
            if (newLength > this.marklimit) {
                newLength = this.marklimit;
            }
            byte[] newbuf = (byte[]) this.byteArrayPool.get(newLength, byte[].class);
            System.arraycopy(localBuf, 0, newbuf, 0, localBuf.length);
            byte[] oldbuf = localBuf;
            this.buf = newbuf;
            localBuf = newbuf;
            this.byteArrayPool.put(oldbuf, byte[].class);
        } else if (this.markpos > 0) {
            System.arraycopy(localBuf, this.markpos, localBuf, 0, localBuf.length - this.markpos);
        }
        this.pos -= this.markpos;
        this.markpos = 0;
        this.count = 0;
        int bytesread = localIn.read(localBuf, this.pos, localBuf.length - this.pos);
        if (bytesread <= 0) {
            i = this.pos;
        } else {
            i = this.pos + bytesread;
        }
        this.count = i;
        return bytesread;
    }

    public synchronized void mark(int readlimit) {
        this.marklimit = Math.max(this.marklimit, readlimit);
        this.markpos = this.pos;
    }

    public boolean markSupported() {
        return true;
    }

    public synchronized int read() throws IOException {
        int i = -1;
        synchronized (this) {
            byte[] localBuf = this.buf;
            InputStream localIn = this.in;
            if (localBuf == null || localIn == null) {
                throw streamClosed();
            }
            if (this.pos < this.count || fillbuf(localIn, localBuf) != -1) {
                if (localBuf != this.buf) {
                    localBuf = this.buf;
                    if (localBuf == null) {
                        throw streamClosed();
                    }
                }
                if (this.count - this.pos > 0) {
                    i = this.pos;
                    this.pos = i + 1;
                    i = localBuf[i] & 255;
                }
            }
        }
        return i;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int read(byte[] buffer, int offset, int byteCount) throws IOException {
        int i = -1;
        synchronized (this) {
            byte[] localBuf = this.buf;
            if (localBuf == null) {
                throw streamClosed();
            }
            if (byteCount == 0) {
                i = 0;
            } else {
                InputStream localIn = this.in;
                if (localIn == null) {
                    throw streamClosed();
                }
                int required;
                if (this.pos < this.count) {
                    int copylength = this.count - this.pos >= byteCount ? byteCount : this.count - this.pos;
                    System.arraycopy(localBuf, this.pos, buffer, offset, copylength);
                    this.pos += copylength;
                    if (copylength == byteCount || localIn.available() == 0) {
                        i = copylength;
                    } else {
                        offset += copylength;
                        required = byteCount - copylength;
                    }
                } else {
                    required = byteCount;
                }
                while (true) {
                    int read;
                    if (this.markpos == -1 && required >= localBuf.length) {
                        read = localIn.read(buffer, offset, required);
                        if (read == -1) {
                            break;
                        }
                    } else if (fillbuf(localIn, localBuf) == -1) {
                        break;
                    } else {
                        if (localBuf != this.buf) {
                            localBuf = this.buf;
                            if (localBuf == null) {
                                throw streamClosed();
                            }
                        }
                        read = this.count - this.pos >= required ? required : this.count - this.pos;
                        System.arraycopy(localBuf, this.pos, buffer, offset, read);
                        this.pos += read;
                    }
                    required -= read;
                    if (required == 0) {
                        break;
                    } else if (localIn.available() == 0) {
                        break;
                    } else {
                        offset += read;
                    }
                }
                i = byteCount - required;
            }
        }
        return i;
    }

    public synchronized void reset() throws IOException {
        if (this.buf == null) {
            throw new IOException("Stream is closed");
        } else if (-1 == this.markpos) {
            throw new InvalidMarkException("Mark has been invalidated, pos: " + this.pos + " markLimit: " + this.marklimit);
        } else {
            this.pos = this.markpos;
        }
    }

    public synchronized long skip(long byteCount) throws IOException {
        byte[] localBuf = this.buf;
        InputStream localIn = this.in;
        if (localBuf == null) {
            throw streamClosed();
        } else if (byteCount < 1) {
            byteCount = 0;
        } else if (localIn == null) {
            throw streamClosed();
        } else if (((long) (this.count - this.pos)) >= byteCount) {
            this.pos = (int) (((long) this.pos) + byteCount);
        } else {
            long read = (long) (this.count - this.pos);
            this.pos = this.count;
            if (this.markpos == -1 || byteCount > ((long) this.marklimit)) {
                byteCount = read + localIn.skip(byteCount - read);
            } else if (fillbuf(localIn, localBuf) == -1) {
                byteCount = read;
            } else if (((long) (this.count - this.pos)) >= byteCount - read) {
                this.pos = (int) (((long) this.pos) + (byteCount - read));
            } else {
                read = (((long) this.count) + read) - ((long) this.pos);
                this.pos = this.count;
                byteCount = read;
            }
        }
        return byteCount;
    }
}
