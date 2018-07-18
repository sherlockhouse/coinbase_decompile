package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiActivity;

final class zzq implements Runnable {
    private final zzp zzfja;
    final /* synthetic */ zzo zzfjb;

    zzq(zzo com_google_android_gms_common_api_internal_zzo, zzp com_google_android_gms_common_api_internal_zzp) {
        this.zzfjb = com_google_android_gms_common_api_internal_zzo;
        this.zzfja = com_google_android_gms_common_api_internal_zzp;
    }

    public final void run() {
        if (this.zzfjb.mStarted) {
            ConnectionResult zzagd = this.zzfja.zzagd();
            if (zzagd.hasResolution()) {
                this.zzfjb.zzfoo.startActivityForResult(GoogleApiActivity.zza(this.zzfjb.getActivity(), zzagd.getResolution(), this.zzfja.zzagc(), false), 1);
            } else if (this.zzfjb.zzfhl.isUserResolvableError(zzagd.getErrorCode())) {
                this.zzfjb.zzfhl.zza(this.zzfjb.getActivity(), this.zzfjb.zzfoo, zzagd.getErrorCode(), 2, this.zzfjb);
            } else if (zzagd.getErrorCode() == 18) {
                GoogleApiAvailability.zza(this.zzfjb.getActivity().getApplicationContext(), new zzr(this, GoogleApiAvailability.zza(this.zzfjb.getActivity(), this.zzfjb)));
            } else {
                this.zzfjb.zza(zzagd, this.zzfja.zzagc());
            }
        }
    }
}
