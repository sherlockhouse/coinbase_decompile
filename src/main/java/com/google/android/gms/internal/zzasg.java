package com.google.android.gms.internal;

import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.auth.api.credentials.CredentialRequestResult;
import com.google.android.gms.common.api.Status;

public final class zzasg implements CredentialRequestResult {
    private final Status mStatus;
    private final Credential zzebg;

    public zzasg(Status status, Credential credential) {
        this.mStatus = status;
        this.zzebg = credential;
    }

    public static zzasg zzf(Status status) {
        return new zzasg(status, null);
    }

    public final Credential getCredential() {
        return this.zzebg;
    }

    public final Status getStatus() {
        return this.mStatus;
    }
}
