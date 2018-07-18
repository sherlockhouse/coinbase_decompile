package com.bumptech.glide;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import com.bumptech.glide.gifdecoder.GifDecoder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.data.InputStreamRewinder;
import com.bumptech.glide.load.engine.Engine;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.engine.cache.MemoryCache;
import com.bumptech.glide.load.engine.prefill.BitmapPreFiller;
import com.bumptech.glide.load.model.AssetUriLoader;
import com.bumptech.glide.load.model.ByteArrayLoader;
import com.bumptech.glide.load.model.ByteArrayLoader.ByteBufferFactory;
import com.bumptech.glide.load.model.ByteBufferEncoder;
import com.bumptech.glide.load.model.ByteBufferFileLoader;
import com.bumptech.glide.load.model.DataUrlLoader;
import com.bumptech.glide.load.model.FileLoader.FileDescriptorFactory;
import com.bumptech.glide.load.model.FileLoader.StreamFactory;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.MediaStoreFileLoader;
import com.bumptech.glide.load.model.ResourceLoader;
import com.bumptech.glide.load.model.StreamEncoder;
import com.bumptech.glide.load.model.StringLoader;
import com.bumptech.glide.load.model.UnitModelLoader.Factory;
import com.bumptech.glide.load.model.UriLoader;
import com.bumptech.glide.load.model.UrlUriLoader;
import com.bumptech.glide.load.model.stream.HttpGlideUrlLoader;
import com.bumptech.glide.load.model.stream.HttpUriLoader;
import com.bumptech.glide.load.model.stream.MediaStoreImageThumbLoader;
import com.bumptech.glide.load.model.stream.MediaStoreVideoThumbLoader;
import com.bumptech.glide.load.model.stream.UrlLoader;
import com.bumptech.glide.load.resource.bitmap.BitmapDrawableDecoder;
import com.bumptech.glide.load.resource.bitmap.BitmapDrawableEncoder;
import com.bumptech.glide.load.resource.bitmap.BitmapEncoder;
import com.bumptech.glide.load.resource.bitmap.ByteBufferBitmapDecoder;
import com.bumptech.glide.load.resource.bitmap.DefaultImageHeaderParser;
import com.bumptech.glide.load.resource.bitmap.Downsampler;
import com.bumptech.glide.load.resource.bitmap.StreamBitmapDecoder;
import com.bumptech.glide.load.resource.bitmap.VideoBitmapDecoder;
import com.bumptech.glide.load.resource.bytes.ByteBufferRewinder;
import com.bumptech.glide.load.resource.file.FileDecoder;
import com.bumptech.glide.load.resource.gif.ByteBufferGifDecoder;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.load.resource.gif.GifDrawableEncoder;
import com.bumptech.glide.load.resource.gif.GifFrameResourceDecoder;
import com.bumptech.glide.load.resource.gif.StreamGifDecoder;
import com.bumptech.glide.load.resource.transcode.BitmapBytesTranscoder;
import com.bumptech.glide.load.resource.transcode.BitmapDrawableTranscoder;
import com.bumptech.glide.load.resource.transcode.GifDrawableBytesTranscoder;
import com.bumptech.glide.manager.ConnectivityMonitorFactory;
import com.bumptech.glide.manager.RequestManagerRetriever;
import com.bumptech.glide.module.GlideModule;
import com.bumptech.glide.module.ManifestParser;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.ImageViewTargetFactory;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.util.Preconditions;
import com.bumptech.glide.util.Util;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@TargetApi(14)
public class Glide implements ComponentCallbacks2 {
    private static volatile Glide glide;
    private static volatile boolean isInitializing;
    private final ArrayPool arrayPool;
    private final BitmapPool bitmapPool;
    private final BitmapPreFiller bitmapPreFiller;
    private final ConnectivityMonitorFactory connectivityMonitorFactory;
    private final Engine engine;
    private final GlideContext glideContext;
    private final List<RequestManager> managers = new ArrayList();
    private final MemoryCache memoryCache;
    private MemoryCategory memoryCategory = MemoryCategory.NORMAL;
    private final Registry registry;
    private final RequestManagerRetriever requestManagerRetriever;

    public static File getPhotoCacheDir(Context context) {
        return getPhotoCacheDir(context, "image_manager_disk_cache");
    }

    public static File getPhotoCacheDir(Context context, String cacheName) {
        File cacheDir = context.getCacheDir();
        if (cacheDir != null) {
            File result = new File(cacheDir, cacheName);
            if (result.mkdirs()) {
                return result;
            }
            if (result.exists() && result.isDirectory()) {
                return result;
            }
            return null;
        }
        if (Log.isLoggable("Glide", 6)) {
            Log.e("Glide", "default disk cache dir is null");
        }
        return null;
    }

    public static Glide get(Context context) {
        if (glide == null) {
            synchronized (Glide.class) {
                if (glide == null) {
                    checkAndInitializeGlide(context);
                }
            }
        }
        return glide;
    }

    private static void checkAndInitializeGlide(Context context) {
        if (isInitializing) {
            throw new IllegalStateException("You cannot call Glide.get() in registerComponents(), use the provided Glide instance instead");
        }
        isInitializing = true;
        initializeGlide(context);
        isInitializing = false;
    }

    public static synchronized void init(Glide glide) {
        synchronized (Glide.class) {
            glide = glide;
        }
    }

    public static synchronized void tearDown() {
        synchronized (Glide.class) {
            glide = null;
        }
    }

    private static void initializeGlide(Context context) {
        Context applicationContext = context.getApplicationContext();
        GeneratedAppGlideModule annotationGeneratedModule = getAnnotationGeneratedGlideModules();
        List<GlideModule> manifestModules = Collections.emptyList();
        if (annotationGeneratedModule == null || annotationGeneratedModule.isManifestParsingEnabled()) {
            manifestModules = new ManifestParser(applicationContext).parse();
        }
        if (!(annotationGeneratedModule == null || annotationGeneratedModule.getExcludedModuleClasses().isEmpty())) {
            Set<Class<?>> excludedModuleClasses = annotationGeneratedModule.getExcludedModuleClasses();
            Iterator<GlideModule> iterator = manifestModules.iterator();
            while (iterator.hasNext()) {
                GlideModule current = (GlideModule) iterator.next();
                if (excludedModuleClasses.contains(current.getClass())) {
                    if (Log.isLoggable("Glide", 3)) {
                        Log.d("Glide", "AppGlideModule excludes manifest GlideModule: " + current);
                    }
                    iterator.remove();
                }
            }
        }
        if (Log.isLoggable("Glide", 3)) {
            for (GlideModule glideModule : manifestModules) {
                Log.d("Glide", "Discovered GlideModule from manifest: " + glideModule.getClass());
            }
        }
        GlideBuilder builder = new GlideBuilder().setRequestManagerFactory(annotationGeneratedModule != null ? annotationGeneratedModule.getRequestManagerFactory() : null);
        for (GlideModule module : manifestModules) {
            module.applyOptions(applicationContext, builder);
        }
        if (annotationGeneratedModule != null) {
            annotationGeneratedModule.applyOptions(applicationContext, builder);
        }
        Glide glide = builder.build(applicationContext);
        for (GlideModule module2 : manifestModules) {
            module2.registerComponents(applicationContext, glide, glide.registry);
        }
        if (annotationGeneratedModule != null) {
            annotationGeneratedModule.registerComponents(applicationContext, glide, glide.registry);
        }
        glide = glide;
    }

    private static GeneratedAppGlideModule getAnnotationGeneratedGlideModules() {
        GeneratedAppGlideModule result = null;
        try {
            return (GeneratedAppGlideModule) Class.forName("com.bumptech.glide.GeneratedAppGlideModuleImpl").newInstance();
        } catch (ClassNotFoundException e) {
            if (!Log.isLoggable("Glide", 5)) {
                return result;
            }
            Log.w("Glide", "Failed to find GeneratedAppGlideModule. You should include an annotationProcessor compile dependency on com.github.bumptech.glide:glide:compiler in your application and a @GlideModule annotated AppGlideModule implementation or LibraryGlideModules will be silently ignored");
            return result;
        } catch (InstantiationException e2) {
            throw new IllegalStateException("GeneratedAppGlideModuleImpl is implemented incorrectly. If you've manually implemented this class, remove your implementation. The Annotation processor will generate a correct implementation.", e2);
        } catch (IllegalAccessException e3) {
            throw new IllegalStateException("GeneratedAppGlideModuleImpl is implemented incorrectly. If you've manually implemented this class, remove your implementation. The Annotation processor will generate a correct implementation.", e3);
        }
    }

    @TargetApi(14)
    Glide(Context context, Engine engine, MemoryCache memoryCache, BitmapPool bitmapPool, ArrayPool arrayPool, RequestManagerRetriever requestManagerRetriever, ConnectivityMonitorFactory connectivityMonitorFactory, int logLevel, RequestOptions defaultRequestOptions) {
        this.engine = engine;
        this.bitmapPool = bitmapPool;
        this.arrayPool = arrayPool;
        this.memoryCache = memoryCache;
        this.requestManagerRetriever = requestManagerRetriever;
        this.connectivityMonitorFactory = connectivityMonitorFactory;
        this.bitmapPreFiller = new BitmapPreFiller(memoryCache, bitmapPool, (DecodeFormat) defaultRequestOptions.getOptions().get(Downsampler.DECODE_FORMAT));
        Resources resources = context.getResources();
        this.registry = new Registry();
        this.registry.register(new DefaultImageHeaderParser());
        Downsampler downsampler = new Downsampler(this.registry.getImageHeaderParsers(), resources.getDisplayMetrics(), bitmapPool, arrayPool);
        ByteBufferGifDecoder byteBufferGifDecoder = new ByteBufferGifDecoder(context, this.registry.getImageHeaderParsers(), bitmapPool, arrayPool);
        this.registry.register(ByteBuffer.class, new ByteBufferEncoder()).register(InputStream.class, new StreamEncoder(arrayPool)).append(ByteBuffer.class, Bitmap.class, new ByteBufferBitmapDecoder(downsampler)).append(InputStream.class, Bitmap.class, new StreamBitmapDecoder(downsampler, arrayPool)).append(ParcelFileDescriptor.class, Bitmap.class, new VideoBitmapDecoder(bitmapPool)).register(Bitmap.class, new BitmapEncoder()).append(ByteBuffer.class, BitmapDrawable.class, new BitmapDrawableDecoder(resources, bitmapPool, new ByteBufferBitmapDecoder(downsampler))).append(InputStream.class, BitmapDrawable.class, new BitmapDrawableDecoder(resources, bitmapPool, new StreamBitmapDecoder(downsampler, arrayPool))).append(ParcelFileDescriptor.class, BitmapDrawable.class, new BitmapDrawableDecoder(resources, bitmapPool, new VideoBitmapDecoder(bitmapPool))).register(BitmapDrawable.class, new BitmapDrawableEncoder(bitmapPool, new BitmapEncoder())).prepend(InputStream.class, GifDrawable.class, new StreamGifDecoder(this.registry.getImageHeaderParsers(), byteBufferGifDecoder, arrayPool)).prepend(ByteBuffer.class, GifDrawable.class, byteBufferGifDecoder).register(GifDrawable.class, new GifDrawableEncoder()).append(GifDecoder.class, GifDecoder.class, new Factory()).append(GifDecoder.class, Bitmap.class, new GifFrameResourceDecoder(bitmapPool)).register(new ByteBufferRewinder.Factory()).append(File.class, ByteBuffer.class, new ByteBufferFileLoader.Factory()).append(File.class, InputStream.class, new StreamFactory()).append(File.class, File.class, new FileDecoder()).append(File.class, ParcelFileDescriptor.class, new FileDescriptorFactory()).append(File.class, File.class, new Factory()).register(new InputStreamRewinder.Factory(arrayPool)).append(Integer.TYPE, InputStream.class, new ResourceLoader.StreamFactory(resources)).append(Integer.TYPE, ParcelFileDescriptor.class, new ResourceLoader.FileDescriptorFactory(resources)).append(Integer.class, InputStream.class, new ResourceLoader.StreamFactory(resources)).append(Integer.class, ParcelFileDescriptor.class, new ResourceLoader.FileDescriptorFactory(resources)).append(String.class, InputStream.class, new DataUrlLoader.StreamFactory()).append(String.class, InputStream.class, new StringLoader.StreamFactory()).append(String.class, ParcelFileDescriptor.class, new StringLoader.FileDescriptorFactory()).append(Uri.class, InputStream.class, new HttpUriLoader.Factory()).append(Uri.class, InputStream.class, new AssetUriLoader.StreamFactory(context.getAssets())).append(Uri.class, ParcelFileDescriptor.class, new AssetUriLoader.FileDescriptorFactory(context.getAssets())).append(Uri.class, InputStream.class, new MediaStoreImageThumbLoader.Factory(context)).append(Uri.class, InputStream.class, new MediaStoreVideoThumbLoader.Factory(context)).append(Uri.class, InputStream.class, new UriLoader.StreamFactory(context.getContentResolver())).append(Uri.class, ParcelFileDescriptor.class, new UriLoader.FileDescriptorFactory(context.getContentResolver())).append(Uri.class, InputStream.class, new UrlUriLoader.StreamFactory()).append(URL.class, InputStream.class, new UrlLoader.StreamFactory()).append(Uri.class, File.class, new MediaStoreFileLoader.Factory(context)).append(GlideUrl.class, InputStream.class, new HttpGlideUrlLoader.Factory()).append(byte[].class, ByteBuffer.class, new ByteBufferFactory()).append(byte[].class, InputStream.class, new ByteArrayLoader.StreamFactory()).register(Bitmap.class, BitmapDrawable.class, new BitmapDrawableTranscoder(resources, bitmapPool)).register(Bitmap.class, byte[].class, new BitmapBytesTranscoder()).register(GifDrawable.class, byte[].class, new GifDrawableBytesTranscoder());
        Context context2 = context;
        this.glideContext = new GlideContext(context2, this.registry, new ImageViewTargetFactory(), defaultRequestOptions, engine, this, logLevel);
    }

    public BitmapPool getBitmapPool() {
        return this.bitmapPool;
    }

    public ArrayPool getArrayPool() {
        return this.arrayPool;
    }

    public Context getContext() {
        return this.glideContext.getBaseContext();
    }

    ConnectivityMonitorFactory getConnectivityMonitorFactory() {
        return this.connectivityMonitorFactory;
    }

    GlideContext getGlideContext() {
        return this.glideContext;
    }

    public void clearMemory() {
        Util.assertMainThread();
        this.memoryCache.clearMemory();
        this.bitmapPool.clearMemory();
        this.arrayPool.clearMemory();
    }

    public void trimMemory(int level) {
        Util.assertMainThread();
        this.memoryCache.trimMemory(level);
        this.bitmapPool.trimMemory(level);
        this.arrayPool.trimMemory(level);
    }

    public RequestManagerRetriever getRequestManagerRetriever() {
        return this.requestManagerRetriever;
    }

    private static RequestManagerRetriever getRetriever(Context context) {
        Preconditions.checkNotNull(context, "You cannot start a load on a not yet attached View or a  Fragment where getActivity() returns null (which usually occurs when getActivity() is called before the Fragment is attached or after the Fragment is destroyed).");
        return get(context).getRequestManagerRetriever();
    }

    public static RequestManager with(Context context) {
        return getRetriever(context).get(context);
    }

    public static RequestManager with(Activity activity) {
        return getRetriever(activity).get(activity);
    }

    public static RequestManager with(FragmentActivity activity) {
        return getRetriever(activity).get(activity);
    }

    public static RequestManager with(Fragment fragment) {
        return getRetriever(fragment.getActivity()).get(fragment);
    }

    public static RequestManager with(android.support.v4.app.Fragment fragment) {
        return getRetriever(fragment.getActivity()).get(fragment);
    }

    public static RequestManager with(View view) {
        return getRetriever(view.getContext()).get(view);
    }

    public Registry getRegistry() {
        return this.registry;
    }

    void removeFromManagers(Target<?> target) {
        synchronized (this.managers) {
            for (RequestManager requestManager : this.managers) {
                if (requestManager.untrack(target)) {
                    return;
                }
            }
            throw new IllegalStateException("Failed to remove target from managers");
        }
    }

    void registerRequestManager(RequestManager requestManager) {
        synchronized (this.managers) {
            if (this.managers.contains(requestManager)) {
                throw new IllegalStateException("Cannot register already registered manager");
            }
            this.managers.add(requestManager);
        }
    }

    void unregisterRequestManager(RequestManager requestManager) {
        synchronized (this.managers) {
            if (this.managers.contains(requestManager)) {
                this.managers.remove(requestManager);
            } else {
                throw new IllegalStateException("Cannot register not yet registered manager");
            }
        }
    }

    public void onTrimMemory(int level) {
        trimMemory(level);
    }

    public void onConfigurationChanged(Configuration newConfig) {
    }

    public void onLowMemory() {
        clearMemory();
    }
}
