package com.google.android.gms.maps;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.google.android.gms.dynamic.zza;
import java.util.ArrayList;
import java.util.List;

public class MapView extends FrameLayout {
    private final zzb zzigy;

    static class zzb extends zza<Object> {
        private final List<Object> zzigx = new ArrayList();
        private final ViewGroup zzihc;
        private final Context zzihd;
        private final GoogleMapOptions zzihe;

        zzb(ViewGroup viewGroup, Context context, GoogleMapOptions googleMapOptions) {
            this.zzihc = viewGroup;
            this.zzihd = context;
            this.zzihe = googleMapOptions;
        }
    }

    public MapView(Context context) {
        super(context);
        this.zzigy = new zzb(this, context, null);
        setClickable(true);
    }

    public MapView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.zzigy = new zzb(this, context, GoogleMapOptions.createFromAttributes(context, attributeSet));
        setClickable(true);
    }

    public MapView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.zzigy = new zzb(this, context, GoogleMapOptions.createFromAttributes(context, attributeSet));
        setClickable(true);
    }
}
