package com.google.android.gms.common;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import com.google.android.gms.R;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.zzbw;
import com.google.android.gms.common.internal.zzbx;
import com.google.android.gms.dynamic.zzq;

public final class SignInButton extends FrameLayout implements OnClickListener {
    private int mColor;
    private int mSize;
    private View zzfgd;
    private OnClickListener zzfge;

    public SignInButton(Context context) {
        this(context, null);
    }

    public SignInButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SignInButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.zzfge = null;
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.SignInButton, 0, 0);
        try {
            this.mSize = obtainStyledAttributes.getInt(R.styleable.SignInButton_buttonSize, 0);
            this.mColor = obtainStyledAttributes.getInt(R.styleable.SignInButton_colorScheme, 2);
            setStyle(this.mSize, this.mColor);
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public final void onClick(View view) {
        if (this.zzfge != null && view == this.zzfgd) {
            this.zzfge.onClick(this);
        }
    }

    public final void setColorScheme(int i) {
        setStyle(this.mSize, i);
    }

    public final void setEnabled(boolean z) {
        super.setEnabled(z);
        this.zzfgd.setEnabled(z);
    }

    public final void setOnClickListener(OnClickListener onClickListener) {
        this.zzfge = onClickListener;
        if (this.zzfgd != null) {
            this.zzfgd.setOnClickListener(this);
        }
    }

    @Deprecated
    public final void setScopes(Scope[] scopeArr) {
        setStyle(this.mSize, this.mColor);
    }

    public final void setSize(int i) {
        setStyle(i, this.mColor);
    }

    public final void setStyle(int i, int i2) {
        this.mSize = i;
        this.mColor = i2;
        Context context = getContext();
        if (this.zzfgd != null) {
            removeView(this.zzfgd);
        }
        try {
            this.zzfgd = zzbw.zzc(context, this.mSize, this.mColor);
        } catch (zzq e) {
            Log.w("SignInButton", "Sign in button not found, using placeholder instead");
            int i3 = this.mSize;
            int i4 = this.mColor;
            View com_google_android_gms_common_internal_zzbx = new zzbx(context);
            com_google_android_gms_common_internal_zzbx.zza(context.getResources(), i3, i4);
            this.zzfgd = com_google_android_gms_common_internal_zzbx;
        }
        addView(this.zzfgd);
        this.zzfgd.setEnabled(isEnabled());
        this.zzfgd.setOnClickListener(this);
    }

    @Deprecated
    public final void setStyle(int i, int i2, Scope[] scopeArr) {
        setStyle(i, i2);
    }
}
