package com.google.android.gms.internal;

import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.auth.api.credentials.CredentialRequest;
import com.google.android.gms.auth.api.credentials.CredentialRequestResult;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;

public final class zzash implements CredentialsApi {
    public final PendingResult<CredentialRequestResult> request(GoogleApiClient googleApiClient, CredentialRequest credentialRequest) {
        return googleApiClient.zzd(new zzasi(this, googleApiClient, credentialRequest));
    }

    public final PendingResult<Status> save(GoogleApiClient googleApiClient, Credential credential) {
        return googleApiClient.zze(new zzask(this, googleApiClient, credential));
    }
}
