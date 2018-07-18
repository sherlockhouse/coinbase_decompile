package com.bumptech.glide.load.engine;

import android.support.v4.os.TraceCompat;
import android.support.v4.util.Pools.Pool;
import android.util.Log;
import com.bumptech.glide.GlideContext;
import com.bumptech.glide.Priority;
import com.bumptech.glide.Registry.NoResultEncoderAvailableException;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.EncodeStrategy;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceEncoder;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.data.DataRewinder;
import com.bumptech.glide.load.engine.DataFetcherGenerator.FetcherReadyCallback;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.util.LogTime;
import com.bumptech.glide.util.pool.FactoryPools.Poolable;
import com.bumptech.glide.util.pool.StateVerifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class DecodeJob<R> implements FetcherReadyCallback, Poolable, Comparable<DecodeJob<?>>, Runnable {
    private Callback<R> callback;
    private Key currentAttemptingKey;
    private Object currentData;
    private DataSource currentDataSource;
    private DataFetcher<?> currentFetcher;
    private volatile DataFetcherGenerator currentGenerator;
    Key currentSourceKey;
    private Thread currentThread;
    final DecodeHelper<R> decodeHelper = new DecodeHelper();
    final DeferredEncodeManager<?> deferredEncodeManager = new DeferredEncodeManager();
    private final DiskCacheProvider diskCacheProvider;
    DiskCacheStrategy diskCacheStrategy;
    private final List<Exception> exceptions = new ArrayList();
    private GlideContext glideContext;
    int height;
    private volatile boolean isCallbackNotified;
    private volatile boolean isCancelled;
    private EngineKey loadKey;
    private boolean onlyRetrieveFromCache;
    Options options;
    private int order;
    private final Pool<DecodeJob<?>> pool;
    private Priority priority;
    private final ReleaseManager releaseManager = new ReleaseManager();
    private RunReason runReason;
    Key signature;
    private Stage stage;
    private long startFetchTime;
    private final StateVerifier stateVerifier = StateVerifier.newInstance();
    int width;

    interface Callback<R> {
        void onLoadFailed(GlideException glideException);

        void onResourceReady(Resource<R> resource, DataSource dataSource);

        void reschedule(DecodeJob<?> decodeJob);
    }

    private final class DecodeCallback<Z> implements DecodeCallback<Z> {
        private final DataSource dataSource;

        DecodeCallback(DataSource dataSource) {
            this.dataSource = dataSource;
        }

        public Resource<Z> onResourceDecoded(Resource<Z> decoded) {
            ResourceEncoder<Z> encoder;
            EncodeStrategy encodeStrategy;
            Class<Z> resourceSubClass = getResourceClass(decoded);
            Transformation<Z> appliedTransformation = null;
            Resource<Z> transformed = decoded;
            if (this.dataSource != DataSource.RESOURCE_DISK_CACHE) {
                appliedTransformation = DecodeJob.this.decodeHelper.getTransformation(resourceSubClass);
                transformed = appliedTransformation.transform(DecodeJob.this.glideContext, decoded, DecodeJob.this.width, DecodeJob.this.height);
            }
            if (!decoded.equals(transformed)) {
                decoded.recycle();
            }
            if (DecodeJob.this.decodeHelper.isResourceEncoderAvailable(transformed)) {
                encoder = DecodeJob.this.decodeHelper.getResultEncoder(transformed);
                encodeStrategy = encoder.getEncodeStrategy(DecodeJob.this.options);
            } else {
                encoder = null;
                encodeStrategy = EncodeStrategy.NONE;
            }
            Resource<Z> result = transformed;
            if (!DecodeJob.this.diskCacheStrategy.isResourceCacheable(!DecodeJob.this.decodeHelper.isSourceKey(DecodeJob.this.currentSourceKey), this.dataSource, encodeStrategy)) {
                return result;
            }
            if (encoder == null) {
                throw new NoResultEncoderAvailableException(transformed.get().getClass());
            }
            Key key;
            if (encodeStrategy == EncodeStrategy.SOURCE) {
                key = new DataCacheKey(DecodeJob.this.currentSourceKey, DecodeJob.this.signature);
            } else if (encodeStrategy == EncodeStrategy.TRANSFORMED) {
                key = new ResourceCacheKey(DecodeJob.this.currentSourceKey, DecodeJob.this.signature, DecodeJob.this.width, DecodeJob.this.height, appliedTransformation, resourceSubClass, DecodeJob.this.options);
            } else {
                throw new IllegalArgumentException("Unknown strategy: " + encodeStrategy);
            }
            LockedResource<Z> lockedResult = LockedResource.obtain(transformed);
            DecodeJob.this.deferredEncodeManager.init(key, encoder, lockedResult);
            return lockedResult;
        }

        private Class<Z> getResourceClass(Resource<Z> resource) {
            return resource.get().getClass();
        }
    }

    private static class DeferredEncodeManager<Z> {
        private ResourceEncoder<Z> encoder;
        private Key key;
        private LockedResource<Z> toEncode;

        DeferredEncodeManager() {
        }

        <X> void init(Key key, ResourceEncoder<X> encoder, LockedResource<X> toEncode) {
            this.key = key;
            this.encoder = encoder;
            this.toEncode = toEncode;
        }

        void encode(DiskCacheProvider diskCacheProvider, Options options) {
            TraceCompat.beginSection("DecodeJob.encode");
            try {
                diskCacheProvider.getDiskCache().put(this.key, new DataCacheWriter(this.encoder, this.toEncode, options));
            } finally {
                this.toEncode.unlock();
                TraceCompat.endSection();
            }
        }

        boolean hasResourceToEncode() {
            return this.toEncode != null;
        }

        void clear() {
            this.key = null;
            this.encoder = null;
            this.toEncode = null;
        }
    }

    interface DiskCacheProvider {
        DiskCache getDiskCache();
    }

    private static class ReleaseManager {
        private boolean isEncodeComplete;
        private boolean isFailed;
        private boolean isReleased;

        ReleaseManager() {
        }

        synchronized boolean release(boolean isRemovedFromQueue) {
            this.isReleased = true;
            return isComplete(isRemovedFromQueue);
        }

        synchronized boolean onEncodeComplete() {
            this.isEncodeComplete = true;
            return isComplete(false);
        }

        synchronized boolean onFailed() {
            this.isFailed = true;
            return isComplete(false);
        }

        synchronized void reset() {
            this.isEncodeComplete = false;
            this.isReleased = false;
            this.isFailed = false;
        }

        private boolean isComplete(boolean isRemovedFromQueue) {
            return (this.isFailed || isRemovedFromQueue || this.isEncodeComplete) && this.isReleased;
        }
    }

    private enum RunReason {
        INITIALIZE,
        SWITCH_TO_SOURCE_SERVICE,
        DECODE_DATA
    }

    private enum Stage {
        INITIALIZE,
        RESOURCE_CACHE,
        DATA_CACHE,
        SOURCE,
        ENCODE,
        FINISHED
    }

    public void run() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x0075 in list [B:21:0x0066, B:30:0x000c]
	at jadx.core.utils.BlockUtils.getBlockByOffset(BlockUtils.java:42)
	at jadx.core.dex.instructions.IfNode.initBlocks(IfNode.java:60)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.initBlocksInIfNodes(BlockFinish.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
        /*
        r4 = this;
        r1 = "DecodeJob#run";
        android.support.v4.os.TraceCompat.beginSection(r1);
        r1 = r4.isCancelled;	 Catch:{ RuntimeException -> 0x0029, all -> 0x0067 }
        if (r1 == 0) goto L_0x0019;	 Catch:{ RuntimeException -> 0x0029, all -> 0x0067 }
    L_0x0009:
        r4.notifyFailed();	 Catch:{ RuntimeException -> 0x0029, all -> 0x0067 }
        r1 = r4.currentFetcher;
        if (r1 == 0) goto L_0x0015;
    L_0x0010:
        r1 = r4.currentFetcher;
        r1.cleanup();
    L_0x0015:
        android.support.v4.os.TraceCompat.endSection();
    L_0x0018:
        return;
    L_0x0019:
        r4.runWrapped();	 Catch:{ RuntimeException -> 0x0029, all -> 0x0067 }
        r1 = r4.currentFetcher;
        if (r1 == 0) goto L_0x0025;
    L_0x0020:
        r1 = r4.currentFetcher;
        r1.cleanup();
    L_0x0025:
        android.support.v4.os.TraceCompat.endSection();
        goto L_0x0018;
    L_0x0029:
        r0 = move-exception;
        r1 = "DecodeJob";	 Catch:{ RuntimeException -> 0x0029, all -> 0x0067 }
        r2 = 3;	 Catch:{ RuntimeException -> 0x0029, all -> 0x0067 }
        r1 = android.util.Log.isLoggable(r1, r2);	 Catch:{ RuntimeException -> 0x0029, all -> 0x0067 }
        if (r1 == 0) goto L_0x0059;	 Catch:{ RuntimeException -> 0x0029, all -> 0x0067 }
    L_0x0033:
        r1 = "DecodeJob";	 Catch:{ RuntimeException -> 0x0029, all -> 0x0067 }
        r2 = new java.lang.StringBuilder;	 Catch:{ RuntimeException -> 0x0029, all -> 0x0067 }
        r2.<init>();	 Catch:{ RuntimeException -> 0x0029, all -> 0x0067 }
        r3 = "DecodeJob threw unexpectedly, isCancelled: ";	 Catch:{ RuntimeException -> 0x0029, all -> 0x0067 }
        r2 = r2.append(r3);	 Catch:{ RuntimeException -> 0x0029, all -> 0x0067 }
        r3 = r4.isCancelled;	 Catch:{ RuntimeException -> 0x0029, all -> 0x0067 }
        r2 = r2.append(r3);	 Catch:{ RuntimeException -> 0x0029, all -> 0x0067 }
        r3 = ", stage: ";	 Catch:{ RuntimeException -> 0x0029, all -> 0x0067 }
        r2 = r2.append(r3);	 Catch:{ RuntimeException -> 0x0029, all -> 0x0067 }
        r3 = r4.stage;	 Catch:{ RuntimeException -> 0x0029, all -> 0x0067 }
        r2 = r2.append(r3);	 Catch:{ RuntimeException -> 0x0029, all -> 0x0067 }
        r2 = r2.toString();	 Catch:{ RuntimeException -> 0x0029, all -> 0x0067 }
        android.util.Log.d(r1, r2, r0);	 Catch:{ RuntimeException -> 0x0029, all -> 0x0067 }
    L_0x0059:
        r1 = r4.stage;	 Catch:{ RuntimeException -> 0x0029, all -> 0x0067 }
        r2 = com.bumptech.glide.load.engine.DecodeJob.Stage.ENCODE;	 Catch:{ RuntimeException -> 0x0029, all -> 0x0067 }
        if (r1 == r2) goto L_0x0062;	 Catch:{ RuntimeException -> 0x0029, all -> 0x0067 }
    L_0x005f:
        r4.notifyFailed();	 Catch:{ RuntimeException -> 0x0029, all -> 0x0067 }
    L_0x0062:
        r1 = r4.isCancelled;	 Catch:{ RuntimeException -> 0x0029, all -> 0x0067 }
        if (r1 != 0) goto L_0x0075;	 Catch:{ RuntimeException -> 0x0029, all -> 0x0067 }
    L_0x0066:
        throw r0;	 Catch:{ RuntimeException -> 0x0029, all -> 0x0067 }
    L_0x0067:
        r1 = move-exception;
        r2 = r4.currentFetcher;
        if (r2 == 0) goto L_0x0071;
    L_0x006c:
        r2 = r4.currentFetcher;
        r2.cleanup();
    L_0x0071:
        android.support.v4.os.TraceCompat.endSection();
        throw r1;
    L_0x0075:
        r1 = r4.currentFetcher;
        if (r1 == 0) goto L_0x007e;
    L_0x0079:
        r1 = r4.currentFetcher;
        r1.cleanup();
    L_0x007e:
        android.support.v4.os.TraceCompat.endSection();
        goto L_0x0018;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.engine.DecodeJob.run():void");
    }

    DecodeJob(DiskCacheProvider diskCacheProvider, Pool<DecodeJob<?>> pool) {
        this.diskCacheProvider = diskCacheProvider;
        this.pool = pool;
    }

    DecodeJob<R> init(GlideContext glideContext, Object model, EngineKey loadKey, Key signature, int width, int height, Class<?> resourceClass, Class<R> transcodeClass, Priority priority, DiskCacheStrategy diskCacheStrategy, Map<Class<?>, Transformation<?>> transformations, boolean isTransformationRequired, boolean onlyRetrieveFromCache, Options options, Callback<R> callback, int order) {
        this.decodeHelper.init(glideContext, model, signature, width, height, diskCacheStrategy, resourceClass, transcodeClass, priority, options, transformations, isTransformationRequired, this.diskCacheProvider);
        this.glideContext = glideContext;
        this.signature = signature;
        this.priority = priority;
        this.loadKey = loadKey;
        this.width = width;
        this.height = height;
        this.diskCacheStrategy = diskCacheStrategy;
        this.onlyRetrieveFromCache = onlyRetrieveFromCache;
        this.options = options;
        this.callback = callback;
        this.order = order;
        this.runReason = RunReason.INITIALIZE;
        return this;
    }

    boolean willDecodeFromCache() {
        Stage firstStage = getNextStage(Stage.INITIALIZE);
        return firstStage == Stage.RESOURCE_CACHE || firstStage == Stage.DATA_CACHE;
    }

    void release(boolean isRemovedFromQueue) {
        if (this.releaseManager.release(isRemovedFromQueue)) {
            releaseInternal();
        }
    }

    private void onEncodeComplete() {
        if (this.releaseManager.onEncodeComplete()) {
            releaseInternal();
        }
    }

    private void onLoadFailed() {
        if (this.releaseManager.onFailed()) {
            releaseInternal();
        }
    }

    private void releaseInternal() {
        this.releaseManager.reset();
        this.deferredEncodeManager.clear();
        this.decodeHelper.clear();
        this.isCallbackNotified = false;
        this.glideContext = null;
        this.signature = null;
        this.options = null;
        this.priority = null;
        this.loadKey = null;
        this.callback = null;
        this.stage = null;
        this.currentGenerator = null;
        this.currentThread = null;
        this.currentSourceKey = null;
        this.currentData = null;
        this.currentDataSource = null;
        this.currentFetcher = null;
        this.startFetchTime = 0;
        this.isCancelled = false;
        this.exceptions.clear();
        this.pool.release(this);
    }

    public int compareTo(DecodeJob<?> other) {
        int result = getPriority() - other.getPriority();
        if (result == 0) {
            return this.order - other.order;
        }
        return result;
    }

    private int getPriority() {
        return this.priority.ordinal();
    }

    public void cancel() {
        this.isCancelled = true;
        DataFetcherGenerator local = this.currentGenerator;
        if (local != null) {
            local.cancel();
        }
    }

    private void runWrapped() {
        switch (this.runReason) {
            case INITIALIZE:
                this.stage = getNextStage(Stage.INITIALIZE);
                this.currentGenerator = getNextGenerator();
                runGenerators();
                return;
            case SWITCH_TO_SOURCE_SERVICE:
                runGenerators();
                return;
            case DECODE_DATA:
                decodeFromRetrievedData();
                return;
            default:
                throw new IllegalStateException("Unrecognized run reason: " + this.runReason);
        }
    }

    private DataFetcherGenerator getNextGenerator() {
        switch (this.stage) {
            case RESOURCE_CACHE:
                return new ResourceCacheGenerator(this.decodeHelper, this);
            case DATA_CACHE:
                return new DataCacheGenerator(this.decodeHelper, this);
            case SOURCE:
                return new SourceGenerator(this.decodeHelper, this);
            case FINISHED:
                return null;
            default:
                throw new IllegalStateException("Unrecognized stage: " + this.stage);
        }
    }

    private void runGenerators() {
        this.currentThread = Thread.currentThread();
        this.startFetchTime = LogTime.getLogTime();
        boolean isStarted = false;
        while (!this.isCancelled && this.currentGenerator != null) {
            isStarted = this.currentGenerator.startNext();
            if (isStarted) {
                break;
            }
            this.stage = getNextStage(this.stage);
            this.currentGenerator = getNextGenerator();
            if (this.stage == Stage.SOURCE) {
                reschedule();
                return;
            }
        }
        if ((this.stage == Stage.FINISHED || this.isCancelled) && !isStarted) {
            notifyFailed();
        }
    }

    private void notifyFailed() {
        setNotifiedOrThrow();
        this.callback.onLoadFailed(new GlideException("Failed to load resource", new ArrayList(this.exceptions)));
        onLoadFailed();
    }

    private void notifyComplete(Resource<R> resource, DataSource dataSource) {
        setNotifiedOrThrow();
        this.callback.onResourceReady(resource, dataSource);
    }

    private void setNotifiedOrThrow() {
        this.stateVerifier.throwIfRecycled();
        if (this.isCallbackNotified) {
            throw new IllegalStateException("Already notified");
        }
        this.isCallbackNotified = true;
    }

    private Stage getNextStage(Stage current) {
        switch (current) {
            case RESOURCE_CACHE:
                if (this.diskCacheStrategy.decodeCachedData()) {
                    return Stage.DATA_CACHE;
                }
                return getNextStage(Stage.DATA_CACHE);
            case DATA_CACHE:
                return this.onlyRetrieveFromCache ? Stage.FINISHED : Stage.SOURCE;
            case SOURCE:
            case FINISHED:
                return Stage.FINISHED;
            case INITIALIZE:
                if (this.diskCacheStrategy.decodeCachedResource()) {
                    return Stage.RESOURCE_CACHE;
                }
                return getNextStage(Stage.RESOURCE_CACHE);
            default:
                throw new IllegalArgumentException("Unrecognized stage: " + current);
        }
    }

    public void reschedule() {
        this.runReason = RunReason.SWITCH_TO_SOURCE_SERVICE;
        this.callback.reschedule(this);
    }

    public void onDataFetcherReady(Key sourceKey, Object data, DataFetcher<?> fetcher, DataSource dataSource, Key attemptedKey) {
        this.currentSourceKey = sourceKey;
        this.currentData = data;
        this.currentFetcher = fetcher;
        this.currentDataSource = dataSource;
        this.currentAttemptingKey = attemptedKey;
        if (Thread.currentThread() != this.currentThread) {
            this.runReason = RunReason.DECODE_DATA;
            this.callback.reschedule(this);
            return;
        }
        TraceCompat.beginSection("DecodeJob.decodeFromRetrievedData");
        try {
            decodeFromRetrievedData();
        } finally {
            TraceCompat.endSection();
        }
    }

    public void onDataFetcherFailed(Key attemptedKey, Exception e, DataFetcher<?> fetcher, DataSource dataSource) {
        fetcher.cleanup();
        GlideException exception = new GlideException("Fetching data failed", e);
        exception.setLoggingDetails(attemptedKey, dataSource, fetcher.getDataClass());
        this.exceptions.add(exception);
        if (Thread.currentThread() != this.currentThread) {
            this.runReason = RunReason.SWITCH_TO_SOURCE_SERVICE;
            this.callback.reschedule(this);
            return;
        }
        runGenerators();
    }

    private void decodeFromRetrievedData() {
        if (Log.isLoggable("DecodeJob", 2)) {
            logWithTimeAndKey("Retrieved data", this.startFetchTime, "data: " + this.currentData + ", cache key: " + this.currentSourceKey + ", fetcher: " + this.currentFetcher);
        }
        Resource<R> resource = null;
        try {
            resource = decodeFromData(this.currentFetcher, this.currentData, this.currentDataSource);
        } catch (GlideException e) {
            e.setLoggingDetails(this.currentAttemptingKey, this.currentDataSource);
            this.exceptions.add(e);
        }
        if (resource != null) {
            notifyEncodeAndRelease(resource, this.currentDataSource);
        } else {
            runGenerators();
        }
    }

    private void notifyEncodeAndRelease(Resource<R> resource, DataSource dataSource) {
        if (resource instanceof Initializable) {
            ((Initializable) resource).initialize();
        }
        Resource<R> result = resource;
        LockedResource<R> lockedResource = null;
        if (this.deferredEncodeManager.hasResourceToEncode()) {
            lockedResource = LockedResource.obtain(resource);
            result = lockedResource;
        }
        notifyComplete(result, dataSource);
        this.stage = Stage.ENCODE;
        try {
            if (this.deferredEncodeManager.hasResourceToEncode()) {
                this.deferredEncodeManager.encode(this.diskCacheProvider, this.options);
            }
            if (lockedResource != null) {
                lockedResource.unlock();
            }
            onEncodeComplete();
        } catch (Throwable th) {
            if (lockedResource != null) {
                lockedResource.unlock();
            }
            onEncodeComplete();
        }
    }

    private <Data> Resource<R> decodeFromData(DataFetcher<?> fetcher, Data data, DataSource dataSource) throws GlideException {
        if (data == null) {
            fetcher.cleanup();
            return null;
        }
        try {
            long startTime = LogTime.getLogTime();
            Resource<R> result = decodeFromFetcher(data, dataSource);
            if (Log.isLoggable("DecodeJob", 2)) {
                logWithTimeAndKey("Decoded result " + result, startTime);
            }
            fetcher.cleanup();
            return result;
        } catch (Throwable th) {
            fetcher.cleanup();
        }
    }

    private <Data> Resource<R> decodeFromFetcher(Data data, DataSource dataSource) throws GlideException {
        return runLoadPath(data, dataSource, this.decodeHelper.getLoadPath(data.getClass()));
    }

    private <Data, ResourceType> Resource<R> runLoadPath(Data data, DataSource dataSource, LoadPath<Data, ResourceType, R> path) throws GlideException {
        DataRewinder<Data> rewinder = this.glideContext.getRegistry().getRewinder(data);
        try {
            Resource<R> load = path.load(rewinder, this.options, this.width, this.height, new DecodeCallback(dataSource));
            return load;
        } finally {
            rewinder.cleanup();
        }
    }

    private void logWithTimeAndKey(String message, long startTime) {
        logWithTimeAndKey(message, startTime, null);
    }

    private void logWithTimeAndKey(String message, long startTime, String extraArgs) {
        Log.v("DecodeJob", message + " in " + LogTime.getElapsedMillis(startTime) + ", load key: " + this.loadKey + (extraArgs != null ? ", " + extraArgs : "") + ", thread: " + Thread.currentThread().getName());
    }

    public StateVerifier getVerifier() {
        return this.stateVerifier;
    }
}
