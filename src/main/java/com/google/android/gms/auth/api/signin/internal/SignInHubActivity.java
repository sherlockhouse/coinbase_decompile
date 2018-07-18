package com.google.android.gms.auth.api.signin.internal;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import com.google.android.gms.auth.api.signin.SignInAccount;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;

@KeepName
public class SignInHubActivity extends FragmentActivity {
    private zzy zzede;
    private boolean zzedf = false;
    private SignInConfiguration zzedg;
    private boolean zzedh;
    private int zzedi;
    private Intent zzedj;

    class zza implements LoaderCallbacks<Void> {
        private /* synthetic */ SignInHubActivity zzedk;

        private zza(SignInHubActivity signInHubActivity) {
            this.zzedk = signInHubActivity;
        }

        public final Loader<Void> onCreateLoader(int i, Bundle bundle) {
            return new zzb(this.zzedk, GoogleApiClient.zzafo());
        }

        public final /* synthetic */ void onLoadFinished(Loader loader, Object obj) {
            this.zzedk.setResult(this.zzedk.zzedi, this.zzedk.zzedj);
            this.zzedk.finish();
        }

        public final void onLoaderReset(Loader<Void> loader) {
        }
    }

    private final void zzaar() {
        getSupportLoaderManager().initLoader(0, null, new zza());
    }

    private final void zzay(int i) {
        Parcelable status = new Status(i);
        Intent intent = new Intent();
        intent.putExtra("googleSignInStatus", status);
        setResult(0, intent);
        finish();
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return true;
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        if (!this.zzedf) {
            setResult(0);
            switch (i) {
                case 40962:
                    if (intent != null) {
                        SignInAccount signInAccount = (SignInAccount) intent.getParcelableExtra("signInAccount");
                        if (signInAccount != null && signInAccount.getGoogleSignInAccount() != null) {
                            Parcelable googleSignInAccount = signInAccount.getGoogleSignInAccount();
                            this.zzede.zza(googleSignInAccount, this.zzedg.zzaaq());
                            intent.removeExtra("signInAccount");
                            intent.putExtra("googleSignInAccount", googleSignInAccount);
                            this.zzedh = true;
                            this.zzedi = i2;
                            this.zzedj = intent;
                            zzaar();
                            return;
                        } else if (intent.hasExtra("errorCode")) {
                            zzay(intent.getIntExtra("errorCode", 8));
                            return;
                        }
                    }
                    zzay(8);
                    return;
                default:
                    return;
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.zzede = zzy.zzbl(this);
        Intent intent = getIntent();
        if (!"com.google.android.gms.auth.GOOGLE_SIGN_IN".equals(intent.getAction())) {
            String str = "AuthSignInClient";
            String str2 = "Unknown action: ";
            String valueOf = String.valueOf(intent.getAction());
            Log.e(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
            finish();
        }
        this.zzedg = (SignInConfiguration) intent.getParcelableExtra("config");
        if (this.zzedg == null) {
            Log.e("AuthSignInClient", "Activity started with invalid configuration.");
            setResult(0);
            finish();
        } else if (bundle == null) {
            Intent intent2 = new Intent("com.google.android.gms.auth.GOOGLE_SIGN_IN");
            intent2.setPackage("com.google.android.gms");
            intent2.putExtra("config", this.zzedg);
            try {
                startActivityForResult(intent2, 40962);
            } catch (ActivityNotFoundException e) {
                this.zzedf = true;
                Log.w("AuthSignInClient", "Could not launch sign in Intent. Google Play Service is probably being updated...");
                zzay(17);
            }
        } else {
            this.zzedh = bundle.getBoolean("signingInGoogleApiClients");
            if (this.zzedh) {
                this.zzedi = bundle.getInt("signInResultCode");
                this.zzedj = (Intent) bundle.getParcelable("signInResultData");
                zzaar();
            }
        }
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("signingInGoogleApiClients", this.zzedh);
        if (this.zzedh) {
            bundle.putInt("signInResultCode", this.zzedi);
            bundle.putParcelable("signInResultData", this.zzedj);
        }
    }
}
