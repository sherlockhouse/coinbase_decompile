package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.Toolbar;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.coinbase.android.R;

public class ControllerJumioCompleteBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final TextView allDone;
    public final Button btnJumioContinue;
    public final ImageView checkmark;
    public final Toolbar cvCoinbaseToolbar;
    public final TextView jumioSuccessMessage;
    private long mDirtyFlags = -1;
    private final LinearLayout mboundView0;

    static {
        sViewsWithIds.put(R.id.cvCoinbaseToolbar, 1);
        sViewsWithIds.put(R.id.checkmark, 2);
        sViewsWithIds.put(R.id.all_done, 3);
        sViewsWithIds.put(R.id.jumio_success_message, 4);
        sViewsWithIds.put(R.id.btnJumioContinue, 5);
    }

    public ControllerJumioCompleteBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds);
        this.allDone = (TextView) bindings[3];
        this.btnJumioContinue = (Button) bindings[5];
        this.checkmark = (ImageView) bindings[2];
        this.cvCoinbaseToolbar = (Toolbar) bindings[1];
        this.jumioSuccessMessage = (TextView) bindings[4];
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
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

    public static ControllerJumioCompleteBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerJumioCompleteBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (ControllerJumioCompleteBinding) DataBindingUtil.inflate(inflater, R.layout.controller_jumio_complete, root, attachToRoot, bindingComponent);
    }

    public static ControllerJumioCompleteBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerJumioCompleteBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.controller_jumio_complete, null, false), bindingComponent);
    }

    public static ControllerJumioCompleteBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerJumioCompleteBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/controller_jumio_complete_0".equals(view.getTag())) {
            return new ControllerJumioCompleteBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
