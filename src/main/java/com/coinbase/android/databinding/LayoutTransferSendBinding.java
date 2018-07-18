package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.coinbase.android.R;

public class LayoutTransferSendBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final ImageView btnInfo;
    public final EditText etTransferMoneyNotes;
    public final EditText etTransferMoneyRecipient;
    public final ImageButton ibtnTransferMoneyContactClear;
    public final ImageView ivTransferMoneyContactAvatar;
    public final ImageView ivTransferMoneyQrScan;
    public final LinearLayout llTransferMoneyContact;
    public final RelativeLayout llTransferMoneyFeeContainer;
    public final LinearLayout llTransferMoneyNotesContainer;
    public final LinearLayout llTransferMoneyRecipientContainer;
    public final ListView lvSuggestedRecipientsList;
    private long mDirtyFlags = -1;
    private final RelativeLayout mboundView0;
    public final TextView tvTranferTotalLabel;
    public final TextView tvTransferFeeDescription;
    public final TextView tvTransferFeeLabel;
    public final TextView tvTransferMoneyContactName;
    public final TextView tvTransferMoneyNotesLabel;
    public final TextView tvTransferMoneyRecipientLabel;
    public final TextView tvTransferTotalDescription;
    public final View vTransferDivider1;
    public final View vTransferDivider2;
    public final View vTransferDivider3;
    public final View vTransferMoneyContactSpacer;

    static {
        sViewsWithIds.put(R.id.llTransferMoneyRecipientContainer, 1);
        sViewsWithIds.put(R.id.tvTransferMoneyRecipientLabel, 2);
        sViewsWithIds.put(R.id.llTransferMoneyContact, 3);
        sViewsWithIds.put(R.id.ivTransferMoneyContactAvatar, 4);
        sViewsWithIds.put(R.id.tvTransferMoneyContactName, 5);
        sViewsWithIds.put(R.id.ibtnTransferMoneyContactClear, 6);
        sViewsWithIds.put(R.id.vTransferMoneyContactSpacer, 7);
        sViewsWithIds.put(R.id.etTransferMoneyRecipient, 8);
        sViewsWithIds.put(R.id.ivTransferMoneyQrScan, 9);
        sViewsWithIds.put(R.id.vTransferDivider1, 10);
        sViewsWithIds.put(R.id.llTransferMoneyNotesContainer, 11);
        sViewsWithIds.put(R.id.tvTransferMoneyNotesLabel, 12);
        sViewsWithIds.put(R.id.etTransferMoneyNotes, 13);
        sViewsWithIds.put(R.id.vTransferDivider2, 14);
        sViewsWithIds.put(R.id.llTransferMoneyFeeContainer, 15);
        sViewsWithIds.put(R.id.tvTransferFeeLabel, 16);
        sViewsWithIds.put(R.id.tvTransferFeeDescription, 17);
        sViewsWithIds.put(R.id.btnInfo, 18);
        sViewsWithIds.put(R.id.tvTranferTotalLabel, 19);
        sViewsWithIds.put(R.id.tvTransferTotalDescription, 20);
        sViewsWithIds.put(R.id.vTransferDivider3, 21);
        sViewsWithIds.put(R.id.lvSuggestedRecipientsList, 22);
    }

    public LayoutTransferSendBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 23, sIncludes, sViewsWithIds);
        this.btnInfo = (ImageView) bindings[18];
        this.etTransferMoneyNotes = (EditText) bindings[13];
        this.etTransferMoneyRecipient = (EditText) bindings[8];
        this.ibtnTransferMoneyContactClear = (ImageButton) bindings[6];
        this.ivTransferMoneyContactAvatar = (ImageView) bindings[4];
        this.ivTransferMoneyQrScan = (ImageView) bindings[9];
        this.llTransferMoneyContact = (LinearLayout) bindings[3];
        this.llTransferMoneyFeeContainer = (RelativeLayout) bindings[15];
        this.llTransferMoneyNotesContainer = (LinearLayout) bindings[11];
        this.llTransferMoneyRecipientContainer = (LinearLayout) bindings[1];
        this.lvSuggestedRecipientsList = (ListView) bindings[22];
        this.mboundView0 = (RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.tvTranferTotalLabel = (TextView) bindings[19];
        this.tvTransferFeeDescription = (TextView) bindings[17];
        this.tvTransferFeeLabel = (TextView) bindings[16];
        this.tvTransferMoneyContactName = (TextView) bindings[5];
        this.tvTransferMoneyNotesLabel = (TextView) bindings[12];
        this.tvTransferMoneyRecipientLabel = (TextView) bindings[2];
        this.tvTransferTotalDescription = (TextView) bindings[20];
        this.vTransferDivider1 = (View) bindings[10];
        this.vTransferDivider2 = (View) bindings[14];
        this.vTransferDivider3 = (View) bindings[21];
        this.vTransferMoneyContactSpacer = (View) bindings[7];
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

    public static LayoutTransferSendBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static LayoutTransferSendBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (LayoutTransferSendBinding) DataBindingUtil.inflate(inflater, R.layout.layout_transfer_send, root, attachToRoot, bindingComponent);
    }

    public static LayoutTransferSendBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static LayoutTransferSendBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.layout_transfer_send, null, false), bindingComponent);
    }

    public static LayoutTransferSendBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static LayoutTransferSendBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/layout_transfer_send_0".equals(view.getTag())) {
            return new LayoutTransferSendBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
