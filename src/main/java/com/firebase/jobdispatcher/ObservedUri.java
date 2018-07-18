package com.firebase.jobdispatcher;

import android.net.Uri;

public final class ObservedUri {
    private final int flags;
    private final Uri uri;

    public ObservedUri(Uri uri, int flags) {
        if (uri == null) {
            throw new IllegalArgumentException("URI must not be null.");
        }
        this.uri = uri;
        this.flags = flags;
    }

    public Uri getUri() {
        return this.uri;
    }

    public int getFlags() {
        return this.flags;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ObservedUri)) {
            return false;
        }
        ObservedUri otherUri = (ObservedUri) o;
        if (this.flags == otherUri.flags && this.uri.equals(otherUri.uri)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.uri.hashCode() ^ this.flags;
    }
}
