package com.coinbase.android.identityverification;

import com.coinbase.android.ControllerScope;

public class TakeDocumentPhotoPresenterModule {
    private final TakeDocumentPhotoScreen mScreen;

    public TakeDocumentPhotoPresenterModule(TakeDocumentPhotoScreen screen) {
        this.mScreen = screen;
    }

    @ControllerScope
    public TakeDocumentPhotoScreen providesTakeDocumentPhotoScreen() {
        return this.mScreen;
    }
}
