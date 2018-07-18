package com.bumptech.glide;

import com.bumptech.glide.manager.RequestManagerRetriever.RequestManagerFactory;
import com.bumptech.glide.module.AppGlideModule;
import java.util.Set;

abstract class GeneratedAppGlideModule extends AppGlideModule {
    @Deprecated
    abstract Set<Class<?>> getExcludedModuleClasses();

    GeneratedAppGlideModule() {
    }

    RequestManagerFactory getRequestManagerFactory() {
        return null;
    }
}
