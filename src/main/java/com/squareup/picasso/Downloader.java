package com.squareup.picasso;

import android.net.Uri;
import java.io.IOException;

public interface Downloader {

    public static class ResponseException extends IOException {
        final boolean localCacheOnly;
        final int responseCode;

        public ResponseException(String message, int networkPolicy, int responseCode) {
            super(message);
            this.localCacheOnly = NetworkPolicy.isOfflineOnly(networkPolicy);
            this.responseCode = responseCode;
        }
    }

    Response load(Uri uri, int i) throws IOException;
}
