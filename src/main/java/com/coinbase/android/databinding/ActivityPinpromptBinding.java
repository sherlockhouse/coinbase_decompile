package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.coinbase.android.R;

public class ActivityPinpromptBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = new IncludedLayouts(7);
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final ImageView ivPinNumber1;
    public final ImageView ivPinNumber2;
    public final ImageView ivPinNumber3;
    public final ImageView ivPinNumber4;
    public final KeypadBinding keypad;
    private long mDirtyFlags = -1;
    private final LinearLayout mboundView0;
    public final TextView tvPinText;

    static {
        sIncludes.setIncludes(0, new String[]{"keypad"}, new int[]{1}, new int[]{R.layout.keypad});
        sViewsWithIds.put(R.id.tvPinText, 2);
        sViewsWithIds.put(R.id.ivPinNumber1, 3);
        sViewsWithIds.put(R.id.ivPinNumber2, 4);
        sViewsWithIds.put(R.id.ivPinNumber3, 5);
        sViewsWithIds.put(R.id.ivPinNumber4, 6);
    }

    public ActivityPinpromptBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 1);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds);
        this.ivPinNumber1 = (ImageView) bindings[3];
        this.ivPinNumber2 = (ImageView) bindings[4];
        this.ivPinNumber3 = (ImageView) bindings[5];
        this.ivPinNumber4 = (ImageView) bindings[6];
        this.keypad = (KeypadBinding) bindings[1];
        setContainedBinding(this.keypad);
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.tvPinText = (TextView) bindings[2];
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 2;
        }
        this.keypad.invalidateAll();
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
                return onChangeKeypad((KeypadBinding) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeKeypad(KeypadBinding Keypad, int fieldId) {
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
        ViewDataBinding.executeBindingsOn(this.keypad);
    }

    public static ActivityPinpromptBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static ActivityPinpromptBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (ActivityPinpromptBinding) DataBindingUtil.inflate(inflater, R.layout.activity_pinprompt, root, attachToRoot, bindingComponent);
    }

    public static ActivityPinpromptBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static ActivityPinpromptBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.activity_pinprompt, null, false), bindingComponent);
    }

    public static ActivityPinpromptBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ActivityPinpromptBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/activity_pinprompt_0".equals(view.getTag())) {
            return new ActivityPinpromptBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
