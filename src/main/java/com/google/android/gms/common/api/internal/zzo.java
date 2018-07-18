package com.google.android.gms.common.api.internal;

import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import java.util.concurrent.atomic.AtomicReference;

public abstract class zzo extends LifecycleCallback implements OnCancelListener {
    protected volatile boolean mStarted;
    protected final GoogleApiAvailability zzfhl;
    protected final AtomicReference<zzp> zzfiw;
    private final Handler zzfix;

    protected zzo(zzcg com_google_android_gms_common_api_internal_zzcg) {
        this(com_google_android_gms_common_api_internal_zzcg, GoogleApiAvailability.getInstance());
    }

    private zzo(zzcg com_google_android_gms_common_api_internal_zzcg, GoogleApiAvailability googleApiAvailability) {
        super(com_google_android_gms_common_api_internal_zzcg);
        this.zzfiw = new AtomicReference(null);
        this.zzfix = new Handler(Looper.getMainLooper());
        this.zzfhl = googleApiAvailability;
    }

    private static int zza(zzp com_google_android_gms_common_api_internal_zzp) {
        return com_google_android_gms_common_api_internal_zzp == null ? -1 : com_google_android_gms_common_api_internal_zzp.zzagc();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onActivityResult(int i, int i2, Intent intent) {
        Object obj;
        int i3 = 13;
        zzp com_google_android_gms_common_api_internal_zzp = (zzp) this.zzfiw.get();
        switch (i) {
            case 1:
                if (i2 != -1) {
                    if (i2 == 0) {
                        if (intent != null) {
                            i3 = intent.getIntExtra("<<ResolutionFailureErrorDetail>>", 13);
                        }
                        zzp com_google_android_gms_common_api_internal_zzp2 = new zzp(new ConnectionResult(i3, null), zza(com_google_android_gms_common_api_internal_zzp));
                        this.zzfiw.set(com_google_android_gms_common_api_internal_zzp2);
                        com_google_android_gms_common_api_internal_zzp = com_google_android_gms_common_api_internal_zzp2;
                        obj = null;
                        break;
                    }
                }
                i3 = 1;
                break;
            case 2:
                int isGooglePlayServicesAvailable = this.zzfhl.isGooglePlayServicesAvailable(getActivity());
                obj = isGooglePlayServicesAvailable == 0 ? 1 : null;
                if (com_google_android_gms_common_api_internal_zzp == null) {
                    return;
                }
                if (com_google_android_gms_common_api_internal_zzp.zzagd().getErrorCode() == 18 && isGooglePlayServicesAvailable == 18) {
                    return;
                }
            default:
                obj = null;
                break;
        }
        if (obj != null) {
            zzagb();
        } else if (com_google_android_gms_common_api_internal_zzp != null) {
            zza(com_google_android_gms_common_api_internal_zzp.zzagd(), com_google_android_gms_common_api_internal_zzp.zzagc());
        }
    }

    public void onCancel(DialogInterface dialogInterface) {
        zza(new ConnectionResult(13, null), zza((zzp) this.zzfiw.get()));
        zzagb();
    }

    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.zzfiw.set(bundle.getBoolean("resolving_error", false) ? new zzp(new ConnectionResult(bundle.getInt("failed_status"), (PendingIntent) bundle.getParcelable("failed_resolution")), bundle.getInt("failed_client_id", -1)) : null);
        }
    }

    public final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        zzp com_google_android_gms_common_api_internal_zzp = (zzp) this.zzfiw.get();
        if (com_google_android_gms_common_api_internal_zzp != null) {
            bundle.putBoolean("resolving_error", true);
            bundle.putInt("failed_client_id", com_google_android_gms_common_api_internal_zzp.zzagc());
            bundle.putInt("failed_status", com_google_android_gms_common_api_internal_zzp.zzagd().getErrorCode());
            bundle.putParcelable("failed_resolution", com_google_android_gms_common_api_internal_zzp.zzagd().getResolution());
        }
    }

    public void onStart() {
        super.onStart();
        this.mStarted = true;
    }

    public void onStop() {
        super.onStop();
        this.mStarted = false;
    }

    protected abstract void zza(ConnectionResult connectionResult, int i);

    protected abstract void zzafw();

    protected final void zzagb() {
        this.zzfiw.set(null);
        zzafw();
    }

    public final void zzb(ConnectionResult connectionResult, int i) {
        zzp com_google_android_gms_common_api_internal_zzp = new zzp(connectionResult, i);
        if (this.zzfiw.compareAndSet(null, com_google_android_gms_common_api_internal_zzp)) {
            this.zzfix.post(new zzq(this, com_google_android_gms_common_api_internal_zzp));
        }
    }
}
