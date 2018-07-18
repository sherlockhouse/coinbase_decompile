package com.bumptech.glide;

import android.content.Context;
import android.util.Log;
import com.coinbase.android.GlideAppModule;
import java.util.Collections;
import java.util.Set;

final class GeneratedAppGlideModuleImpl extends GeneratedAppGlideModule {
    private final GlideAppModule appGlideModule = new GlideAppModule();

    GeneratedAppGlideModuleImpl() {
        if (Log.isLoggable("Glide", 3)) {
            Log.d("Glide", "Discovered AppGlideModule from annotation: com.coinbase.android.GlideAppModule");
        }
    }

    public void applyOptions(Context context, GlideBuilder builder) {
        this.appGlideModule.applyOptions(context, builder);
    }

    public void registerComponents(Context context, Glide glide, Registry registry) {
        this.appGlideModule.registerComponents(context, glide, registry);
    }

    public boolean isManifestParsingEnabled() {
        return this.appGlideModule.isManifestParsingEnabled();
    }

    public Set<Class<?>> getExcludedModuleClasses() {
        return Collections.emptySet();
    }

    GeneratedRequestManagerFactory getRequestManagerFactory() {
        return new GeneratedRequestManagerFactory();
    }
}
