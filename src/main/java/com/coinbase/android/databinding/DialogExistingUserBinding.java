package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.coinbase.android.R;

public class DialogExistingUserBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final Button btnAccountLevels;
    public final Button btnLearnMore;
    public final FrameLayout flDismissContainer;
    public final ImageView ivImage;
    public final LinearLayout llExistingUserContainer;
    private long mDirtyFlags = -1;
    private final LinearLayout mboundView0;
    public final TextView tvDescription;
    public final TextView tvTitle;

    static {
        sViewsWithIds.put(R.id.llExistingUserContainer, 1);
        sViewsWithIds.put(R.id.ivImage, 2);
        sViewsWithIds.put(R.id.tvTitle, 3);
        sViewsWithIds.put(R.id.tvDescription, 4);
        sViewsWithIds.put(R.id.btnAccountLevels, 5);
        sViewsWithIds.put(R.id.btnLearnMore, 6);
        sViewsWithIds.put(R.id.flDismissContainer, 7);
    }

    public DialogExistingUserBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 8, sIncludes, sViewsWithIds);
        this.btnAccountLevels = (Button) bindings[5];
        this.btnLearnMore = (Button) bindings[6];
        this.flDismissContainer = (FrameLayout) bindings[7];
        this.ivImage = (ImageView) bindings[2];
        this.llExistingUserContainer = (LinearLayout) bindings[1];
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.tvDescription = (TextView) bindings[4];
        this.tvTitle = (TextView) bindings[3];
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

    public static DialogExistingUserBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static DialogExistingUserBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (DialogExistingUserBinding) DataBindingUtil.inflate(inflater, R.layout.dialog_existing_user, root, attachToRoot, bindingComponent);
    }

    public static DialogExistingUserBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static DialogExistingUserBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.dialog_existing_user, null, false), bindingComponent);
    }

    public static DialogExistingUserBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static DialogExistingUserBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/dialog_existing_user_0".equals(view.getTag())) {
            return new DialogExistingUserBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
