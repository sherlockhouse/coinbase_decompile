package com.coinbase.android.identityverification;

import rx.subjects.BehaviorSubject;

public class IdentityVerificationBitmapConnector {
    private final BehaviorSubject<IdentityVerificationBitmapContainer> mIdentityVerificationSubject;

    public IdentityVerificationBitmapConnector() {
        this(BehaviorSubject.create((IdentityVerificationBitmapContainer) null));
    }

    IdentityVerificationBitmapConnector(BehaviorSubject<IdentityVerificationBitmapContainer> identityVerificationSubject) {
        this.mIdentityVerificationSubject = identityVerificationSubject;
    }

    public BehaviorSubject<IdentityVerificationBitmapContainer> get() {
        return this.mIdentityVerificationSubject;
    }
}
