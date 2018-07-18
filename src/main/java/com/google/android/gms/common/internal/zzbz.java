package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.res.Resources;
import com.google.android.gms.R;

public final class zzbz {
    private final Resources zzfwf;
    private final String zzfwg = this.zzfwf.getResourcePackageName(R.string.common_google_play_services_unknown_issue);

    public zzbz(Context context) {
        zzbp.zzu(context);
        this.zzfwf = context.getResources();
    }

    public final String getString(String str) {
        int identifier = this.zzfwf.getIdentifier(str, "string", this.zzfwg);
        return identifier == 0 ? null : this.zzfwf.getString(identifier);
    }
}
