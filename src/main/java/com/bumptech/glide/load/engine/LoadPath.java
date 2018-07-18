package com.bumptech.glide.load.engine;

import android.support.v4.util.Pools.Pool;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.data.DataRewinder;
import com.bumptech.glide.util.Preconditions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class LoadPath<Data, ResourceType, Transcode> {
    private final Class<Data> dataClass;
    private final List<? extends DecodePath<Data, ResourceType, Transcode>> decodePaths;
    private final String failureMessage;
    private final Pool<List<Exception>> listPool;

    public LoadPath(Class<Data> dataClass, Class<ResourceType> resourceClass, Class<Transcode> transcodeClass, List<DecodePath<Data, ResourceType, Transcode>> decodePaths, Pool<List<Exception>> listPool) {
        this.dataClass = dataClass;
        this.listPool = listPool;
        this.decodePaths = (List) Preconditions.checkNotEmpty((Collection) decodePaths);
        this.failureMessage = "Failed LoadPath{" + dataClass.getSimpleName() + "->" + resourceClass.getSimpleName() + "->" + transcodeClass.getSimpleName() + "}";
    }

    public Resource<Transcode> load(DataRewinder<Data> rewinder, Options options, int width, int height, DecodeCallback<ResourceType> decodeCallback) throws GlideException {
        List<Exception> exceptions = (List) this.listPool.acquire();
        try {
            Resource<Transcode> loadWithExceptionList = loadWithExceptionList(rewinder, options, width, height, decodeCallback, exceptions);
            return loadWithExceptionList;
        } finally {
            this.listPool.release(exceptions);
        }
    }

    private Resource<Transcode> loadWithExceptionList(DataRewinder<Data> rewinder, Options options, int width, int height, DecodeCallback<ResourceType> decodeCallback, List<Exception> exceptions) throws GlideException {
        int size = this.decodePaths.size();
        Resource<Transcode> result = null;
        for (int i = 0; i < size; i++) {
            try {
                result = ((DecodePath) this.decodePaths.get(i)).decode(rewinder, width, height, options, decodeCallback);
            } catch (GlideException e) {
                exceptions.add(e);
            }
            if (result != null) {
                break;
            }
        }
        if (result != null) {
            return result;
        }
        throw new GlideException(this.failureMessage, new ArrayList(exceptions));
    }

    public String toString() {
        return "LoadPath{decodePaths=" + Arrays.toString(this.decodePaths.toArray(new DecodePath[this.decodePaths.size()])) + '}';
    }
}
