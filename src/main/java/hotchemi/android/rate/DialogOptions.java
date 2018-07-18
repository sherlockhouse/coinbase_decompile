package hotchemi.android.rate;

import android.content.Context;
import android.view.View;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

final class DialogOptions {
    private boolean cancelable = false;
    private Reference<OnClickButtonListener> listener;
    private int messageResId = R.string.rate_dialog_message;
    private String messageText = null;
    private String negativeText = null;
    private String neutralText = null;
    private String positiveText = null;
    private boolean showNegativeButton = true;
    private boolean showNeutralButton = true;
    private boolean showTitle = true;
    private StoreType storeType = StoreType.GOOGLEPLAY;
    private int textNegativeResId = R.string.rate_dialog_no;
    private int textNeutralResId = R.string.rate_dialog_cancel;
    private int textPositiveResId = R.string.rate_dialog_ok;
    private int titleResId = R.string.rate_dialog_title;
    private String titleText = null;
    private View view;

    DialogOptions() {
    }

    public boolean shouldShowNeutralButton() {
        return this.showNeutralButton;
    }

    public void setShowNeutralButton(boolean showNeutralButton) {
        this.showNeutralButton = showNeutralButton;
    }

    public boolean shouldShowNegativeButton() {
        return this.showNegativeButton;
    }

    public boolean shouldShowTitle() {
        return this.showTitle;
    }

    public boolean getCancelable() {
        return this.cancelable;
    }

    public StoreType getStoreType() {
        return this.storeType;
    }

    public void setMessageResId(int messageResId) {
        this.messageResId = messageResId;
    }

    public View getView() {
        return this.view;
    }

    public OnClickButtonListener getListener() {
        return this.listener != null ? (OnClickButtonListener) this.listener.get() : null;
    }

    public void setListener(OnClickButtonListener listener) {
        this.listener = new WeakReference(listener);
    }

    public String getTitleText(Context context) {
        if (this.titleText == null) {
            return context.getString(this.titleResId);
        }
        return this.titleText;
    }

    public String getMessageText(Context context) {
        if (this.messageText == null) {
            return context.getString(this.messageResId);
        }
        return this.messageText;
    }

    public String getPositiveText(Context context) {
        if (this.positiveText == null) {
            return context.getString(this.textPositiveResId);
        }
        return this.positiveText;
    }

    public String getNeutralText(Context context) {
        if (this.neutralText == null) {
            return context.getString(this.textNeutralResId);
        }
        return this.neutralText;
    }

    public String getNegativeText(Context context) {
        if (this.negativeText == null) {
            return context.getString(this.textNegativeResId);
        }
        return this.negativeText;
    }
}
