package com.squareup.picasso;

import android.graphics.Bitmap;
import java.io.InputStream;

public class Downloader$Response {
    final Bitmap bitmap;
    final boolean cached;
    final long contentLength;
    final InputStream stream;

    public Downloader$Response(InputStream stream, boolean loadedFromCache, long contentLength) {
        if (stream == null) {
            throw new IllegalArgumentException("Stream may not be null.");
        }
        this.stream = stream;
        this.bitmap = null;
        this.cached = loadedFromCache;
        this.contentLength = contentLength;
    }

    public InputStream getInputStream() {
        return this.stream;
    }

    @Deprecated
    public Bitmap getBitmap() {
        return this.bitmap;
    }

    public long getContentLength() {
        return this.contentLength;
    }
}
