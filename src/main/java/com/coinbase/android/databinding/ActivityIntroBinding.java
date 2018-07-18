package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.coinbase.android.R;
import com.coinbase.android.ui.MaterialProgressBar;

public class ActivityIntroBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final Button btnLoginIntroSignupText;
    public final ImageView logo;
    private long mDirtyFlags = -1;
    private final RelativeLayout mboundView0;
    public final MaterialProgressBar progress;
    public final RelativeLayout rlChartContainer;
    public final RelativeLayout rlContainer;
    public final LinearLayout signupSection;
    public final TextView tvLoginIntroSignin;
    public final TextView tvPleaseWait;

    static {
        sViewsWithIds.put(R.id.progress, 1);
        sViewsWithIds.put(R.id.tvPleaseWait, 2);
        sViewsWithIds.put(R.id.rlContainer, 3);
        sViewsWithIds.put(R.id.rlChartContainer, 4);
        sViewsWithIds.put(R.id.logo, 5);
        sViewsWithIds.put(R.id.signup_section, 6);
        sViewsWithIds.put(R.id.btnLoginIntroSignupText, 7);
        sViewsWithIds.put(R.id.tvLoginIntroSignin, 8);
    }

    public ActivityIntroBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 9, sIncludes, sViewsWithIds);
        this.btnLoginIntroSignupText = (Button) bindings[7];
        this.logo = (ImageView) bindings[5];
        this.mboundView0 = (RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.progress = (MaterialProgressBar) bindings[1];
        this.rlChartContainer = (RelativeLayout) bindings[4];
        this.rlContainer = (RelativeLayout) bindings[3];
        this.signupSection = (LinearLayout) bindings[6];
        this.tvLoginIntroSignin = (TextView) bindings[8];
        this.tvPleaseWait = (TextView) bindings[2];
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 1;
        }
        requestRebind();
    }

    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return false;
        }
    }

    public boolean setVariable(int variableId, Object variable) {
        return false;
    }

    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    protected void executeBindings() {
        synchronized (this) {
            long dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
    }

    public static ActivityIntroBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static ActivityIntroBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (ActivityIntroBinding) DataBindingUtil.inflate(inflater, R.layout.activity_intro, root, attachToRoot, bindingComponent);
    }

    public static ActivityIntroBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static ActivityIntroBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.activity_intro, null, false), bindingComponent);
    }

    public static ActivityIntroBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ActivityIntroBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/activity_intro_0".equals(view.getTag())) {
            return new ActivityIntroBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
