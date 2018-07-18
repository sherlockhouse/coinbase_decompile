package com.squareup.picasso;

import android.content.Context;
import android.net.Uri;
import android.net.http.HttpResponseCache;
import android.os.Build.VERSION;
import com.squareup.picasso.Downloader.Response;
import com.squareup.picasso.Downloader.ResponseException;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class UrlConnectionDownloader implements Downloader {
    private static final ThreadLocal<StringBuilder> CACHE_HEADER_BUILDER = new ThreadLocal<StringBuilder>() {
        protected StringBuilder initialValue() {
            return new StringBuilder();
        }
    };
    static volatile Object cache;
    private static final Object lock = new Object();
    private final Context context;

    private static class ResponseCacheIcs {
        static Object install(Context context) throws IOException {
            File cacheDir = Utils.createDefaultCacheDir(context);
            HttpResponseCache cache = HttpResponseCache.getInstalled();
            if (cache == null) {
                return HttpResponseCache.install(cacheDir, Utils.calculateDiskCacheSize(cacheDir));
            }
            return cache;
        }
    }

    public UrlConnectionDownloader(Context context) {
        this.context = context.getApplicationContext();
    }

    protected HttpURLConnection openConnection(Uri path) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(path.toString()).openConnection();
        connection.setConnectTimeout(15000);
        connection.setReadTimeout(20000);
        return connection;
    }

    public Response load(Uri uri, int networkPolicy) throws IOException {
        if (VERSION.SDK_INT >= 14) {
            installCacheIfNeeded(this.context);
        }
        HttpURLConnection connection = openConnection(uri);
        connection.setUseCaches(true);
        if (networkPolicy != 0) {
            String headerValue;
            if (NetworkPolicy.isOfflineOnly(networkPolicy)) {
                headerValue = "only-if-cached,max-age=2147483647";
            } else {
                StringBuilder builder = (StringBuilder) CACHE_HEADER_BUILDER.get();
                builder.setLength(0);
                if (!NetworkPolicy.shouldReadFromDiskCache(networkPolicy)) {
                    builder.append("no-cache");
                }
                if (!NetworkPolicy.shouldWriteToDiskCache(networkPolicy)) {
                    if (builder.length() > 0) {
                        builder.append(',');
                    }
                    builder.append("no-store");
                }
                headerValue = builder.toString();
            }
            connection.setRequestProperty("Cache-Control", headerValue);
        }
        int responseCode = connection.getResponseCode();
        if (responseCode >= 300) {
            connection.disconnect();
            throw new ResponseException(responseCode + " " + connection.getResponseMessage(), networkPolicy, responseCode);
        }
        long contentLength = (long) connection.getHeaderFieldInt("Content-Length", -1);
        return new Response(connection.getInputStream(), Utils.parseResponseSourceHeader(connection.getHeaderField("X-Android-Response-Source")), contentLength);
    }

    private static void installCacheIfNeeded(Context context) {
        if (cache == null) {
            try {
                synchronized (lock) {
                    if (cache == null) {
                        cache = ResponseCacheIcs.install(context);
                    }
                }
            } catch (IOException e) {
            }
        }
    }
}
