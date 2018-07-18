package com.coinbase.cache;

import android.util.LruCache;
import android.util.Pair;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Response.Builder;
import okhttp3.ResponseBody;
import org.apache.commons.lang3.StringUtils;

public class OkHttpInMemoryLruCache extends LruCache<String, Pair<String, CachedResponseBody>> {
    private static final int NOT_MODIFIED = 304;
    private String[] mEnabledForcedCachePathPrefixes;
    private volatile boolean mForcedCacheEnabled = false;
    private final Map<String, Long> mForcedCacheUrls = new HashMap();
    private volatile long mTimeoutInMillis;

    static class CachedResponseBody {
        private final byte[] mBody;
        private final MediaType mContentType;
        private final int mSuccessCode;

        CachedResponseBody(MediaType contentType, byte[] body, int successCode) {
            this.mContentType = contentType;
            this.mBody = body;
            this.mSuccessCode = successCode;
        }

        public byte[] body() {
            return this.mBody;
        }

        MediaType contentType() {
            return this.mContentType;
        }

        int successCode() {
            return this.mSuccessCode;
        }
    }

    public OkHttpInMemoryLruCache(int maxSize) {
        super(maxSize);
    }

    protected synchronized int sizeOf(String key, Pair<String, CachedResponseBody> value) {
        return ((CachedResponseBody) value.second).body().length + (key.length() + ((String) value.first).length());
    }

    public Interceptor createInterceptor() {
        return OkHttpInMemoryLruCache$$Lambda$1.lambdaFactory$(this);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ Response lambda$createInterceptor$0(OkHttpInMemoryLruCache this_, Chain chain) throws IOException {
        Request request = chain.request();
        if (request.method().equalsIgnoreCase("GET")) {
            Pair<String, CachedResponseBody> responseBodyPair;
            String url = request.url().toString();
            synchronized (this_) {
                responseBodyPair = (Pair) this_.get(url);
            }
            if (responseBodyPair != null) {
                synchronized (this_) {
                    request = request.newBuilder().header("If-None-Match", (String) ((Pair) this_.get(url)).first).build();
                }
            }
            synchronized (this_) {
                Response cachedResponse = this_.handleForcedCacheResponseIfEnabled(request, responseBodyPair, chain);
                if (cachedResponse != null) {
                    return cachedResponse;
                }
            }
        } else {
            synchronized (this_) {
                this_.mForcedCacheUrls.clear();
            }
            return chain.proceed(request);
        }
    }

    public void setForcedCache(Set<String> paths, long timeoutInMillis) {
        synchronized (this) {
            this.mForcedCacheUrls.clear();
            this.mEnabledForcedCachePathPrefixes = (String[]) paths.toArray(new String[paths.size()]);
            this.mTimeoutInMillis = timeoutInMillis;
        }
    }

    public void clearForcedCache() {
        synchronized (this) {
            this.mForcedCacheUrls.clear();
            this.mEnabledForcedCachePathPrefixes = null;
        }
    }

    public void setForcedCacheEnabled(boolean enabled) {
        synchronized (this) {
            this.mForcedCacheEnabled = enabled;
        }
    }

    public Response handleForcedCacheResponseIfEnabled(Request request, Pair<String, CachedResponseBody> responseBodyPair, Chain chain) {
        if (!this.mForcedCacheEnabled) {
            return null;
        }
        String url = request.url().toString();
        String path = request.url().encodedPath();
        if (this.mEnabledForcedCachePathPrefixes != null && StringUtils.startsWithAny(path, this.mEnabledForcedCachePathPrefixes)) {
            long currentTimeMillis = System.currentTimeMillis();
            if (responseBodyPair != null && this.mForcedCacheUrls.containsKey(url) && currentTimeMillis - ((Long) this.mForcedCacheUrls.get(url)).longValue() < this.mTimeoutInMillis) {
                return new Builder().request(chain.request()).protocol(Protocol.HTTP_1_1).body(ResponseBody.create(((CachedResponseBody) responseBodyPair.second).contentType(), ((CachedResponseBody) responseBodyPair.second).body())).code(((CachedResponseBody) responseBodyPair.second).successCode()).build();
            }
            this.mForcedCacheUrls.put(url, Long.valueOf(currentTimeMillis));
        }
        return null;
    }
}
