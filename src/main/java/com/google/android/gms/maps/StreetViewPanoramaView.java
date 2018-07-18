package com.google.android.gms.maps;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.google.android.gms.dynamic.zza;
import java.util.ArrayList;
import java.util.List;

public class StreetViewPanoramaView extends FrameLayout {
    private final zzb zzihw;

    static class zzb extends zza<Object> {
        private final ViewGroup zzihc;
        private final Context zzihd;
        private final List<Object> zziho = new ArrayList();
        private final StreetViewPanoramaOptions zzihz;

        zzb(ViewGroup viewGroup, Context context, StreetViewPanoramaOptions streetViewPanoramaOptions) {
            this.zzihc = viewGroup;
            this.zzihd = context;
            this.zzihz = streetViewPanoramaOptions;
        }
    }

    public StreetViewPanoramaView(Context context) {
        super(context);
        this.zzihw = new zzb(this, context, null);
    }

    public StreetViewPanoramaView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.zzihw = new zzb(this, context, null);
    }

    public StreetViewPanoramaView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.zzihw = new zzb(this, context, null);
    }
}
