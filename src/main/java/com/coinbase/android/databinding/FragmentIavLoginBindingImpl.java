package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import com.coinbase.android.R;

public class FragmentIavLoginBindingImpl extends FragmentIavLoginBinding {
    private static final IncludedLayouts sIncludes = new IncludedLayouts(2);
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final FrameLayout mboundView0;

    static {
        sIncludes.setIncludes(0, new String[]{"fragment_iav_login_content"}, new int[]{1}, new int[]{R.layout.fragment_iav_login_content});
    }

    public FragmentIavLoginBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, ViewDataBinding.mapBindings(bindingComponent, root, 2, sIncludes, sViewsWithIds));
    }

    private FragmentIavLoginBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, (FragmentIavLoginContentBinding) bindings[1]);
        this.mDirtyFlags = -1;
        this.mboundView0 = (FrameLayout) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 2;
        }
        this.iavLoginContent.invalidateAll();
        requestRebind();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
        }
    }

    public boolean setVariable(int variableId, Object variable) {
        return false;
    }

    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeIavLoginContent((FragmentIavLoginContentBinding) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeIavLoginContent(FragmentIavLoginContentBinding IavLoginContent, int fieldId) {
        switch (fieldId) {
            case 0:
                synchronized (this) {
                    this.mDirtyFlags |= 1;
                }
                return true;
            default:
                return false;
        }
    }

    protected void executeBindings() {
        synchronized (this) {
            long dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        ViewDataBinding.executeBindingsOn(this.iavLoginContent);
    }
}
