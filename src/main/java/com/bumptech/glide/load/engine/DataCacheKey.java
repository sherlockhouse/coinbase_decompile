package com.bumptech.glide.load.engine;

import com.bumptech.glide.load.Key;
import java.security.MessageDigest;

final class DataCacheKey implements Key {
    private final Key signature;
    private final Key sourceKey;

    public DataCacheKey(Key sourceKey, Key signature) {
        this.sourceKey = sourceKey;
        this.signature = signature;
    }

    public boolean equals(Object o) {
        if (!(o instanceof DataCacheKey)) {
            return false;
        }
        DataCacheKey other = (DataCacheKey) o;
        if (this.sourceKey.equals(other.sourceKey) && this.signature.equals(other.signature)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (this.sourceKey.hashCode() * 31) + this.signature.hashCode();
    }

    public String toString() {
        return "DataCacheKey{sourceKey=" + this.sourceKey + ", signature=" + this.signature + '}';
    }

    public void updateDiskCacheKey(MessageDigest messageDigest) {
        this.sourceKey.updateDiskCacheKey(messageDigest);
        this.signature.updateDiskCacheKey(messageDigest);
    }
}
