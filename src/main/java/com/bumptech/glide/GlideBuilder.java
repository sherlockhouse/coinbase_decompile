package com.bumptech.glide;

import android.content.Context;
import com.bumptech.glide.load.engine.Engine;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.engine.bitmap_recycle.LruArrayPool;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.DiskCache.Factory;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemoryCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator.Builder;
import com.bumptech.glide.load.engine.executor.GlideExecutor;
import com.bumptech.glide.manager.ConnectivityMonitorFactory;
import com.bumptech.glide.manager.DefaultConnectivityMonitorFactory;
import com.bumptech.glide.manager.RequestManagerRetriever;
import com.bumptech.glide.manager.RequestManagerRetriever.RequestManagerFactory;
import com.bumptech.glide.request.RequestOptions;

public final class GlideBuilder {
    private ArrayPool arrayPool;
    private BitmapPool bitmapPool;
    private ConnectivityMonitorFactory connectivityMonitorFactory;
    private RequestOptions defaultRequestOptions = new RequestOptions();
    private GlideExecutor diskCacheExecutor;
    private Factory diskCacheFactory;
    private Engine engine;
    private int logLevel = 4;
    private MemoryCache memoryCache;
    private MemorySizeCalculator memorySizeCalculator;
    private RequestManagerFactory requestManagerFactory;
    private GlideExecutor sourceExecutor;

    GlideBuilder setRequestManagerFactory(RequestManagerFactory factory) {
        this.requestManagerFactory = factory;
        return this;
    }

    public Glide build(Context context) {
        if (this.sourceExecutor == null) {
            this.sourceExecutor = GlideExecutor.newSourceExecutor();
        }
        if (this.diskCacheExecutor == null) {
            this.diskCacheExecutor = GlideExecutor.newDiskCacheExecutor();
        }
        if (this.memorySizeCalculator == null) {
            this.memorySizeCalculator = new Builder(context).build();
        }
        if (this.connectivityMonitorFactory == null) {
            this.connectivityMonitorFactory = new DefaultConnectivityMonitorFactory();
        }
        if (this.bitmapPool == null) {
            this.bitmapPool = new LruBitmapPool(this.memorySizeCalculator.getBitmapPoolSize());
        }
        if (this.arrayPool == null) {
            this.arrayPool = new LruArrayPool(this.memorySizeCalculator.getArrayPoolSizeInBytes());
        }
        if (this.memoryCache == null) {
            this.memoryCache = new LruResourceCache(this.memorySizeCalculator.getMemoryCacheSize());
        }
        if (this.diskCacheFactory == null) {
            this.diskCacheFactory = new InternalCacheDiskCacheFactory(context);
        }
        if (this.engine == null) {
            this.engine = new Engine(this.memoryCache, this.diskCacheFactory, this.diskCacheExecutor, this.sourceExecutor, GlideExecutor.newUnlimitedSourceExecutor());
        }
        RequestManagerRetriever requestManagerRetriever = new RequestManagerRetriever(this.requestManagerFactory);
        return new Glide(context, this.engine, this.memoryCache, this.bitmapPool, this.arrayPool, requestManagerRetriever, this.connectivityMonitorFactory, this.logLevel, this.defaultRequestOptions.lock());
    }
}
