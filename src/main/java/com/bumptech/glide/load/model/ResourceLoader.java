package com.bumptech.glide.load.model;

import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.model.ModelLoader.LoadData;
import java.io.InputStream;

public class ResourceLoader<Data> implements ModelLoader<Integer, Data> {
    private final Resources resources;
    private final ModelLoader<Uri, Data> uriLoader;

    public static class FileDescriptorFactory implements ModelLoaderFactory<Integer, ParcelFileDescriptor> {
        private final Resources resources;

        public FileDescriptorFactory(Resources resources) {
            this.resources = resources;
        }

        public ModelLoader<Integer, ParcelFileDescriptor> build(MultiModelLoaderFactory multiFactory) {
            return new ResourceLoader(this.resources, multiFactory.build(Uri.class, ParcelFileDescriptor.class));
        }
    }

    public static class StreamFactory implements ModelLoaderFactory<Integer, InputStream> {
        private final Resources resources;

        public StreamFactory(Resources resources) {
            this.resources = resources;
        }

        public ModelLoader<Integer, InputStream> build(MultiModelLoaderFactory multiFactory) {
            return new ResourceLoader(this.resources, multiFactory.build(Uri.class, InputStream.class));
        }
    }

    public ResourceLoader(Resources resources, ModelLoader<Uri, Data> uriLoader) {
        this.resources = resources;
        this.uriLoader = uriLoader;
    }

    public LoadData<Data> buildLoadData(Integer model, int width, int height, Options options) {
        Uri uri = getResourceUri(model);
        return uri == null ? null : this.uriLoader.buildLoadData(uri, width, height, options);
    }

    private Uri getResourceUri(Integer model) {
        try {
            return Uri.parse("android.resource://" + this.resources.getResourcePackageName(model.intValue()) + '/' + this.resources.getResourceTypeName(model.intValue()) + '/' + this.resources.getResourceEntryName(model.intValue()));
        } catch (NotFoundException e) {
            if (Log.isLoggable("ResourceLoader", 5)) {
                Log.w("ResourceLoader", "Received invalid resource id: " + model, e);
            }
            return null;
        }
    }

    public boolean handles(Integer model) {
        return true;
    }
}
