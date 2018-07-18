package com.coinbase.android.signin;

import android.app.Application;
import android.content.Context;
import android.content.IntentSender.SendIntentException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.Log;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.auth.api.credentials.CredentialRequest;
import com.google.android.gms.auth.api.credentials.CredentialRequestResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import javax.inject.Inject;
import rx.Observable;
import rx.subjects.BehaviorSubject;

@ControllerScope
public class CredentialsApiRxWrapper {
    protected static final int RC_READ = 100;
    protected static final int RC_SAVE = 102;
    private ConnectionCallbacks connectionCallbacks = new ConnectionCallbacks() {
        public void onConnected(Bundle var1) {
            Auth.CredentialsApi.request(CredentialsApiRxWrapper.this.mCredentialsClient, CredentialsApiRxWrapper.this.mCredentialRequest).setResultCallback(new ResultCallback<CredentialRequestResult>() {
                public void onResult(CredentialRequestResult result) {
                    if (result.getStatus().isSuccess()) {
                        CredentialsApiRxWrapper.this.mCredentialSubject.onNext(result.getCredential());
                        return;
                    }
                    AnonymousClass1.this.resolveResult(result.getStatus());
                    CredentialsApiRxWrapper.this.mCredentialSubject.onNext(null);
                }
            });
        }

        public void onConnectionSuspended(int var1) {
            CredentialsApiRxWrapper.this.mCredentialSubject.onNext(null);
            Log.w(CredentialsApiRxWrapper.this.mActivity.getLocalClassName(), "onConnectionSuspended");
        }

        protected void resolveResult(Status status) {
            if (status.getStatusCode() == 6) {
                try {
                    status.startResolutionForResult(CredentialsApiRxWrapper.this.mActivity, 100);
                } catch (SendIntentException e) {
                    Log.e(CredentialsApiRxWrapper.this.mActivity.getLocalClassName(), "STATUS: Failed to send resolution.", e);
                }
            }
        }
    };
    private OnConnectionFailedListener connectionFailedListener = new OnConnectionFailedListener() {
        public void onConnectionFailed(ConnectionResult var1) {
            CredentialsApiRxWrapper.this.mCredentialSubject.onNext(null);
            Log.w(CredentialsApiRxWrapper.this.mActivity.getLocalClassName(), "onConnectionFailed.");
        }
    };
    protected boolean didLoadCredentials = false;
    private final AppCompatActivity mActivity;
    private final Context mContext;
    private CredentialRequest mCredentialRequest;
    private volatile BehaviorSubject<Credential> mCredentialSubject;
    private GoogleApiClient mCredentialsClient;
    private volatile BehaviorSubject<Void> mSaveSubject;

    @Inject
    public CredentialsApiRxWrapper(AppCompatActivity activity, Application app) {
        this.mActivity = activity;
        this.mContext = app;
        this.mCredentialsClient = new Builder(this.mContext).addConnectionCallbacks(this.connectionCallbacks).addOnConnectionFailedListener(this.connectionFailedListener).addApi(Auth.CREDENTIALS_API).build();
        this.mCredentialRequest = new CredentialRequest.Builder().setPasswordLoginSupported(true).setAccountTypes("https://accounts.google.com").build();
    }

    protected boolean shouldConnect() {
        if (this.didLoadCredentials || this.mCredentialsClient == null || this.mCredentialsClient.isConnected()) {
            return false;
        }
        return true;
    }

    protected Observable<Credential> connect() {
        this.didLoadCredentials = true;
        this.mCredentialSubject = BehaviorSubject.create();
        this.mCredentialsClient.connect();
        return this.mCredentialSubject;
    }

    protected Observable<Void> saveCredentials(String email, String password, String firstName, String lastName) {
        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            return Observable.just(null);
        }
        if (!this.mCredentialsClient.isConnected()) {
            return Observable.just(null);
        }
        this.mSaveSubject = BehaviorSubject.create();
        Credential.Builder builder = new Credential.Builder(email).setPassword(password);
        if (!(firstName == null || lastName == null)) {
            builder.setName(firstName + " " + lastName);
        }
        Auth.CredentialsApi.save(this.mCredentialsClient, builder.build()).setResultCallback(new ResultCallback<Status>() {
            public void onResult(Status status) {
                CredentialsApiRxWrapper.this.mSaveSubject.onNext(null);
                String className = CredentialsApiRxWrapper.this.mActivity.getLocalClassName();
                if (status.isSuccess()) {
                    Log.d(className, "Credentials save success");
                } else if (status.hasResolution()) {
                    try {
                        status.startResolutionForResult(CredentialsApiRxWrapper.this.mActivity, 102);
                    } catch (SendIntentException e) {
                        Log.e(className, "STATUS: Failed to send resolution.", e);
                    }
                } else {
                    Log.e(className, "Credentials save failure");
                }
            }
        });
        return this.mSaveSubject;
    }
}
