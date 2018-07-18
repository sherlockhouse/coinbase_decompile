package com.coinbase.android.transfers;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.coinbase.android.R;
import com.coinbase.android.databinding.LayoutTransferSendBinding;
import com.coinbase.android.databinding.ListItemContactSuggestionBinding;
import com.coinbase.android.pin.PINPromptActivity;
import com.coinbase.android.qrscanner.CaptureActivityIntentBuilder;
import com.coinbase.android.utils.RoundedTransformation;
import com.coinbase.android.utils.Utils;
import com.coinbase.v2.models.transactions.Data;
import com.commonsware.cwac.merge.MergeAdapter;
import com.squareup.picasso.Picasso;
import java.util.List;
import org.joda.money.Money;
import permissions.dispatcher.PermissionRequest;
import rx.functions.Func0;
import rx.functions.Func2;
import rx.functions.Func8;

public class TransferSendLayout extends LinearLayout implements TransferSendScreen {
    private Func0<Activity> mActivityFunc;
    private Func0<Void> mBackFunc;
    private final LayoutTransferSendBinding mBinding;
    private EmailSuggestionAdapter mCBSuggestionsAdapter;
    private Func8<String, String, Money, String, Boolean, String, String, Integer, Void> mConfirmSendDialogFunc;
    private TextView mContactsHeader;
    private final Context mContext;
    private ProgressDialog mDialog;
    private EmailSuggestionAdapter mGoogleSuggestionsAdapter;
    TransferSendPresenter mPresenter;
    private TextView mRecentContactsHeader;
    private Func0<Void> mRequestContactsPermissionFunc;
    private Func2<Data, String, Void> mShowDelayedTransactionDialogFunc;
    private Func2<Intent, Integer, Void> mStartActivityForResultFunc;
    private Func0<Void> mStartQrScanWithCheckFunc;
    private Func0<Void> mSuccessFunc;

    private class EmailSuggestionAdapter extends ArrayAdapter<Suggestion> {
        public EmailSuggestionAdapter(Context context) {
            super(context, 0);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ListItemContactSuggestionBinding binding;
            if (convertView == null) {
                binding = (ListItemContactSuggestionBinding) DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.list_item_contact_suggestion, parent, false);
                convertView = binding.getRoot();
                convertView.setTag(binding);
            } else {
                binding = (ListItemContactSuggestionBinding) convertView.getTag();
            }
            String email = ((Suggestion) getItem(position)).email;
            String name = ((Suggestion) getItem(position)).name;
            String display = email;
            if (!(name == null || name.equals(email))) {
                display = name;
            }
            Picasso.with(getContext()).load(Utils.getGravatarUrl(email)).error(R.drawable.circle_blue).placeholder(R.drawable.circle_blue).transform(new RoundedTransformation(60, 0)).into(binding.ivGravatar);
            binding.tvName.setText(display);
            if (display.equals(email)) {
                binding.tvEmail.setVisibility(8);
            } else {
                binding.tvEmail.setText(email);
                binding.tvEmail.setVisibility(0);
            }
            return convertView;
        }
    }

    public TransferSendLayout(Context context) {
        this(context, null);
    }

    public TransferSendLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TransferSendLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mBinding = LayoutTransferSendBinding.inflate(LayoutInflater.from(context), this, true);
        this.mContext = context;
    }

    public void onCreate(TransferSendPresenter presenter, Bundle args, Func0<Activity> activityFunc, Func0<Void> startQrScanWithCheck, Func8<String, String, Money, String, Boolean, String, String, Integer, Void> confirmSendDialogFunc, Func2<Data, String, Void> showDelayedTransactionDialogFunc, Func0<Void> requestContactsPermissionsFunc, Func2<Intent, Integer, Void> startActivityForResult, Func0<Void> backFunc, Func0<Void> successFunc) {
        this.mPresenter = presenter;
        this.mActivityFunc = activityFunc;
        this.mStartQrScanWithCheckFunc = startQrScanWithCheck;
        this.mConfirmSendDialogFunc = confirmSendDialogFunc;
        this.mShowDelayedTransactionDialogFunc = showDelayedTransactionDialogFunc;
        this.mRequestContactsPermissionFunc = requestContactsPermissionsFunc;
        this.mStartActivityForResultFunc = startActivityForResult;
        this.mBackFunc = backFunc;
        this.mSuccessFunc = successFunc;
        this.mPresenter.onCreate(args);
    }

    public void onViewCreated() {
        this.mPresenter.onViewCreated();
    }

    public void initializeViews(String hint) {
        this.mBinding.tvTransferMoneyRecipientLabel.setText(R.string.transfer_send_recipient_label);
        this.mBinding.etTransferMoneyRecipient.setHint(hint);
        this.mBinding.ivTransferMoneyQrScan.setVisibility(0);
        this.mBinding.etTransferMoneyRecipient.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                TransferSendLayout.this.mPresenter.onTransferSendFocused(hasFocus, TransferSendLayout.this.mBinding.etTransferMoneyRecipient.getText().toString());
            }
        });
        this.mBinding.etTransferMoneyRecipient.setOnEditorActionListener(new OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                TransferSendLayout.this.showNotes(true);
                return true;
            }
        });
        this.mBinding.etTransferMoneyRecipient.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void afterTextChanged(Editable s) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                TransferSendLayout.this.mPresenter.onRecipientTextChanged(s.toString());
            }
        });
        this.mCBSuggestionsAdapter = new EmailSuggestionAdapter((Context) this.mActivityFunc.call());
        final MergeAdapter mAdapter = new MergeAdapter();
        LayoutInflater inflater = LayoutInflater.from((Context) this.mActivityFunc.call());
        this.mRecentContactsHeader = (TextView) inflater.inflate(R.layout.list_header_suggestions, this.mBinding.lvSuggestedRecipientsList, false);
        this.mRecentContactsHeader.setText(R.string.recent_contacts_header);
        this.mRecentContactsHeader.setVisibility(8);
        mAdapter.addView(this.mRecentContactsHeader);
        mAdapter.addAdapter(this.mCBSuggestionsAdapter);
        this.mGoogleSuggestionsAdapter = new EmailSuggestionAdapter((Context) this.mActivityFunc.call());
        this.mContactsHeader = (TextView) inflater.inflate(R.layout.list_header_suggestions, this.mBinding.lvSuggestedRecipientsList, false);
        this.mContactsHeader.setText(R.string.contacts_header);
        this.mContactsHeader.setVisibility(8);
        mAdapter.addView(this.mContactsHeader);
        mAdapter.addAdapter(this.mGoogleSuggestionsAdapter);
        this.mBinding.lvSuggestedRecipientsList.setAdapter(mAdapter);
        this.mBinding.lvSuggestedRecipientsList.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                TransferSendLayout.this.mPresenter.onRecipientClicked((Suggestion) mAdapter.getItem(position));
            }
        });
        this.mBinding.ivTransferMoneyQrScan.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                TransferSendLayout.this.mStartQrScanWithCheckFunc.call();
            }
        });
        this.mBinding.llTransferMoneyContact.setVisibility(8);
        this.mBinding.llTransferMoneyContact.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                TransferSendLayout.this.mPresenter.onTransferMoneyContactClicked();
                TransferSendLayout.this.mBinding.etTransferMoneyRecipient.requestFocus();
            }
        });
        this.mBinding.ibtnTransferMoneyContactClear.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                TransferSendLayout.this.mPresenter.onTransferMoneyContactClearClicked();
                TransferSendLayout.this.mBinding.etTransferMoneyRecipient.requestFocus();
            }
        });
        this.mBinding.btnInfo.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                ((Activity) TransferSendLayout.this.mActivityFunc.call()).startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://support.coinbase.com/customer/portal/articles/815435-does-coinbase-pay-bitcoin-miner-fees-")));
            }
        });
        showNotes(false);
        hideFee();
        setupFieldsMixpanelTracking();
    }

    public void showTransferSendTotal(String total) {
        this.mBinding.tvTransferTotalDescription.setText(total);
    }

    public void setRecipient(String recipient) {
        this.mBinding.etTransferMoneyRecipient.setText(recipient);
    }

    public void showFee() {
        this.mBinding.llTransferMoneyFeeContainer.setVisibility(0);
        this.mBinding.vTransferDivider3.setVisibility(0);
    }

    public void hideFee() {
        this.mBinding.llTransferMoneyFeeContainer.setVisibility(8);
        this.mBinding.vTransferDivider3.setVisibility(8);
    }

    public void showFeeDescription(String feeDesc) {
        this.mBinding.tvTransferFeeDescription.setText(feeDesc);
    }

    public void showNotes(boolean requestFocusOnNotes) {
        this.mBinding.lvSuggestedRecipientsList.setVisibility(8);
        this.mBinding.etTransferMoneyNotes.setVisibility(0);
        this.mBinding.tvTransferMoneyNotesLabel.setVisibility(0);
        this.mBinding.vTransferDivider2.setVisibility(0);
        if (requestFocusOnNotes) {
            this.mBinding.etTransferMoneyNotes.requestFocus();
        }
    }

    public void showSuggestionsList() {
        this.mBinding.lvSuggestedRecipientsList.setVisibility(0);
    }

    public void setContactVisible(boolean visible, String email, String name) {
        if (visible) {
            this.mBinding.llTransferMoneyContact.setVisibility(0);
            this.mBinding.vTransferMoneyContactSpacer.setVisibility(0);
            this.mBinding.etTransferMoneyRecipient.setVisibility(8);
            this.mBinding.tvTransferMoneyContactName.setText(name);
            Picasso.with((Context) this.mActivityFunc.call()).load(Utils.getGravatarUrl(email)).error(R.drawable.circle_blue).placeholder(R.drawable.circle_blue).transform(new RoundedTransformation((int) (15.0f * getResources().getDisplayMetrics().density), 0)).into(this.mBinding.ivTransferMoneyContactAvatar);
            return;
        }
        this.mBinding.llTransferMoneyContact.setVisibility(8);
        this.mBinding.vTransferMoneyContactSpacer.setVisibility(8);
        this.mBinding.etTransferMoneyRecipient.setVisibility(0);
    }

    public void setTitle(String title) {
        ActionBar actionBar = ((AppCompatActivity) this.mActivityFunc.call()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(title);
        }
    }

    public void hideKeyboard() {
        Utils.hideKeyboardFrom((Activity) this.mActivityFunc.call(), this.mBinding.etTransferMoneyRecipient);
    }

    public void requestContactsPermission() {
        this.mRequestContactsPermissionFunc.call();
    }

    public void onContactsPermissionGranted() {
        this.mPresenter.onContactsPermissionGranted();
    }

    void showDeniedForReadContacts() {
        Utils.showMessage((Context) this.mActivityFunc.call(), (int) R.string.permission_read_contacts_denied, 0);
        this.mPresenter.onContactPermissionsDenied();
    }

    public void showCaptureActivity(String text, int requestCode) {
        CaptureActivityIntentBuilder intentBuilder = new CaptureActivityIntentBuilder((Activity) this.mActivityFunc.call()).makeAction().withQrCodeMode();
        if (!TextUtils.isEmpty(text)) {
            intentBuilder.withMessage(text);
        }
        this.mStartActivityForResultFunc.call(intentBuilder.build(), Integer.valueOf(requestCode));
    }

    void showRationaleForCamera(PermissionRequest request) {
        showRationaleDialog(R.string.permission_camera_rationale, request);
    }

    void showDeniedForCamera() {
        Utils.showMessage((Context) this.mActivityFunc.call(), (int) R.string.permission_camera_denied, 0);
    }

    public void showPINPrompt(int requestPinCode) {
        Intent intent = new Intent((Context) this.mActivityFunc.call(), PINPromptActivity.class);
        intent.setAction(PINPromptActivity.ACTION_PROMPT);
        this.mStartActivityForResultFunc.call(intent, Integer.valueOf(requestPinCode));
    }

    private void showRationaleDialog(int messageResId, final PermissionRequest request) {
        new Builder((Context) this.mActivityFunc.call()).setPositiveButton(R.string.button_allow, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                request.proceed();
            }
        }).setNegativeButton(R.string.button_deny, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                request.cancel();
            }
        }).setCancelable(false).setMessage(messageResId).show();
    }

    public void dismissWithError() {
        if (this.mActivityFunc.call() != null) {
            Utils.showMessage(getContext(), (int) R.string.charts_accounts_error, 0);
            this.mBackFunc.call();
        }
    }

    public void dismissDialog() {
        Utils.dismissDialog(this.mDialog);
    }

    public void showRecentContactsHeader() {
        this.mRecentContactsHeader.setVisibility(0);
    }

    public void hideRecentContactsHeader() {
        this.mRecentContactsHeader.setVisibility(8);
    }

    public void showContactsHeader() {
        this.mContactsHeader.setVisibility(0);
    }

    public void hideContactsHeader() {
        this.mContactsHeader.setVisibility(8);
    }

    public void showEmailSuggestions(List<Suggestion> suggestionList) {
        this.mCBSuggestionsAdapter.addAll(suggestionList);
        this.mCBSuggestionsAdapter.notifyDataSetChanged();
    }

    public void clearEmailSuggestions() {
        this.mCBSuggestionsAdapter.clear();
        this.mCBSuggestionsAdapter.notifyDataSetChanged();
    }

    public boolean isShown() {
        return this.mActivityFunc.call() != null;
    }

    public void showDelayedTransactionDialog(Data transaction, String id) {
        this.mShowDelayedTransactionDialogFunc.call(transaction, id);
    }

    public void showProgressDialog(String text) {
        this.mDialog = ProgressDialog.show((Context) this.mActivityFunc.call(), null, text);
    }

    public String getRecipient() {
        return this.mBinding.etTransferMoneyRecipient.getText().toString();
    }

    public void setMessage(String message) {
        this.mBinding.etTransferMoneyNotes.setText(message);
    }

    public String getEnteredNotes() {
        return this.mBinding.etTransferMoneyNotes.getText().toString();
    }

    public void routeSuccess() {
        if (this.mActivityFunc.call() != null) {
            this.mSuccessFunc.call();
        }
    }

    public void showConfirmDialog(String recipient, String id, Money amount, String fee, boolean isSendMax, String notes, String exchangeId, int onActivityResultTarget) {
        this.mConfirmSendDialogFunc.call(recipient, id, amount, fee, Boolean.valueOf(isSendMax), notes, exchangeId, Integer.valueOf(onActivityResultTarget));
    }

    public boolean isShowingRecipientList() {
        return this.mBinding.lvSuggestedRecipientsList.getVisibility() == 0;
    }

    public void clearGoogleSuggestions() {
        this.mGoogleSuggestionsAdapter.clear();
        this.mGoogleSuggestionsAdapter.notifyDataSetChanged();
    }

    public void showGoogleSuggestions(List<Suggestion> suggestionList) {
        this.mGoogleSuggestionsAdapter.addAll(suggestionList);
        this.mGoogleSuggestionsAdapter.notifyDataSetChanged();
    }

    public void requestRecipientFocus() {
        this.mBinding.etTransferMoneyRecipient.requestFocus();
    }

    public void showKeyboardFromRecipient() {
        Utils.postShowKeyboardFrom((Activity) this.mActivityFunc.call(), this.mBinding.etTransferMoneyRecipient);
    }

    private void setupFieldsMixpanelTracking() {
        this.mBinding.etTransferMoneyNotes.setOnFocusChangeListener(TransferSendLayout$$Lambda$1.lambdaFactory$(this));
        this.mBinding.etTransferMoneyRecipient.setOnFocusChangeListener(TransferSendLayout$$Lambda$2.lambdaFactory$(this));
    }
}
