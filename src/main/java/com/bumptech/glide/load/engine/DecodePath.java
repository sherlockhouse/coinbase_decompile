package com.bumptech.glide.load.engine;

import android.support.v4.util.Pools.Pool;
import android.util.Log;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.data.DataRewinder;
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DecodePath<DataType, ResourceType, Transcode> {
    private final Class<DataType> dataClass;
    private final List<? extends ResourceDecoder<DataType, ResourceType>> decoders;
    private final String failureMessage;
    private final Pool<List<Exception>> listPool;
    private final ResourceTranscoder<ResourceType, Transcode> transcoder;

    interface DecodeCallback<ResourceType> {
        Resource<ResourceType> onResourceDecoded(Resource<ResourceType> resource);
    }

    public DecodePath(Class<DataType> dataClass, Class<ResourceType> resourceClass, Class<Transcode> transcodeClass, List<? extends ResourceDecoder<DataType, ResourceType>> decoders, ResourceTranscoder<ResourceType, Transcode> transcoder, Pool<List<Exception>> listPool) {
        this.dataClass = dataClass;
        this.decoders = decoders;
        this.transcoder = transcoder;
        this.listPool = listPool;
        this.failureMessage = "Failed DecodePath{" + dataClass.getSimpleName() + "->" + resourceClass.getSimpleName() + "->" + transcodeClass.getSimpleName() + "}";
    }

    public Resource<Transcode> decode(DataRewinder<DataType> rewinder, int width, int height, Options options, DecodeCallback<ResourceType> callback) throws GlideException {
        return this.transcoder.transcode(callback.onResourceDecoded(decodeResource(rewinder, width, height, options)));
    }

    private Resource<ResourceType> decodeResource(DataRewinder<DataType> rewinder, int width, int height, Options options) throws GlideException {
        List<Exception> exceptions = (List) this.listPool.acquire();
        try {
            Resource<ResourceType> decodeResourceWithList = decodeResourceWithList(rewinder, width, height, options, exceptions);
            return decodeResourceWithList;
        } finally {
            this.listPool.release(exceptions);
        }
    }

    private Resource<ResourceType> decodeResourceWithList(DataRewinder<DataType> rewinder, int width, int height, Options options, List<Exception> exceptions) throws GlideException {
        Resource<ResourceType> result = null;
        int size = this.decoders.size();
        for (int i = 0; i < size; i++) {
            ResourceDecoder<DataType, ResourceType> decoder = (ResourceDecoder) this.decoders.get(i);
            try {
                if (decoder.handles(rewinder.rewindAndGet(), options)) {
                    result = decoder.decode(rewinder.rewindAndGet(), width, height, options);
                }
            } catch (IOException e) {
                if (Log.isLoggable("DecodePath", 2)) {
                    Log.v("DecodePath", "Failed to decode data for " + decoder, e);
                }
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
        return "DecodePath{ dataClass=" + this.dataClass + ", decoders=" + this.decoders + ", transcoder=" + this.transcoder + '}';
    }
}
