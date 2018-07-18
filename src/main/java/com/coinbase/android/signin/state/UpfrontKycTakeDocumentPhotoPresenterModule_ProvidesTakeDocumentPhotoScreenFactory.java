package com.coinbase.android.signin.state;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class UpfrontKycTakeDocumentPhotoPresenterModule_ProvidesTakeDocumentPhotoScreenFactory implements Factory<UpfrontKycTakeDocumentPhotoScreen> {
    private final UpfrontKycTakeDocumentPhotoPresenterModule module;

    public UpfrontKycTakeDocumentPhotoPresenterModule_ProvidesTakeDocumentPhotoScreenFactory(UpfrontKycTakeDocumentPhotoPresenterModule module) {
        this.module = module;
    }

    public UpfrontKycTakeDocumentPhotoScreen get() {
        return provideInstance(this.module);
    }

    public static UpfrontKycTakeDocumentPhotoScreen provideInstance(UpfrontKycTakeDocumentPhotoPresenterModule module) {
        return proxyProvidesTakeDocumentPhotoScreen(module);
    }

    public static UpfrontKycTakeDocumentPhotoPresenterModule_ProvidesTakeDocumentPhotoScreenFactory create(UpfrontKycTakeDocumentPhotoPresenterModule module) {
        return new UpfrontKycTakeDocumentPhotoPresenterModule_ProvidesTakeDocumentPhotoScreenFactory(module);
    }

    public static UpfrontKycTakeDocumentPhotoScreen proxyProvidesTakeDocumentPhotoScreen(UpfrontKycTakeDocumentPhotoPresenterModule instance) {
        return (UpfrontKycTakeDocumentPhotoScreen) Preconditions.checkNotNull(instance.providesTakeDocumentPhotoScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
