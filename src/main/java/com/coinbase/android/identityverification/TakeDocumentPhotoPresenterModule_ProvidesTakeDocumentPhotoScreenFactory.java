package com.coinbase.android.identityverification;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class TakeDocumentPhotoPresenterModule_ProvidesTakeDocumentPhotoScreenFactory implements Factory<TakeDocumentPhotoScreen> {
    private final TakeDocumentPhotoPresenterModule module;

    public TakeDocumentPhotoPresenterModule_ProvidesTakeDocumentPhotoScreenFactory(TakeDocumentPhotoPresenterModule module) {
        this.module = module;
    }

    public TakeDocumentPhotoScreen get() {
        return provideInstance(this.module);
    }

    public static TakeDocumentPhotoScreen provideInstance(TakeDocumentPhotoPresenterModule module) {
        return proxyProvidesTakeDocumentPhotoScreen(module);
    }

    public static TakeDocumentPhotoPresenterModule_ProvidesTakeDocumentPhotoScreenFactory create(TakeDocumentPhotoPresenterModule module) {
        return new TakeDocumentPhotoPresenterModule_ProvidesTakeDocumentPhotoScreenFactory(module);
    }

    public static TakeDocumentPhotoScreen proxyProvidesTakeDocumentPhotoScreen(TakeDocumentPhotoPresenterModule instance) {
        return (TakeDocumentPhotoScreen) Preconditions.checkNotNull(instance.providesTakeDocumentPhotoScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
