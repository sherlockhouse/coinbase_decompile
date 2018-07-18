package com.coinbase.android.transfers;

import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.text.TextUtils;
import android.util.Pair;
import android.util.Patterns;
import com.coinbase.android.BackgroundScheduler;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.Log;
import com.coinbase.android.R;
import com.coinbase.android.pin.PINManager;
import com.coinbase.android.pin.PINManager.AccessType;
import com.coinbase.android.ui.CurrenciesUpdatedConnector;
import com.coinbase.android.ui.MainScheduler;
import com.coinbase.android.utils.ApiErrorHandler;
import com.coinbase.android.utils.CryptoUri.InvalidCryptoUriException;
import com.coinbase.android.utils.CryptoUriParser;
import com.coinbase.android.utils.CurrencyUtils;
import com.coinbase.android.utils.MoneyFormatterUtil;
import com.coinbase.android.utils.MoneyFormatterUtil.Options;
import com.coinbase.android.utils.TransactionUtils;
import com.coinbase.android.utils.Utils;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.LoginManager;
import com.coinbase.api.internal.models.contacts.Contacts;
import com.coinbase.api.internal.models.currency.Data;
import com.coinbase.api.internal.models.instantExchangeQuote.InstantExchangeQuote;
import com.coinbase.api.internal.models.transaction.TransactionFee;
import com.coinbase.api.internal.models.transaction.UserFee;
import com.coinbase.v2.models.transactions.Entity;
import com.coinbase.zxing.client.android.Intents.Scan;
import com.google.gson.Gson;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import javax.inject.Inject;
import org.joda.money.BigMoney;
import org.joda.money.BigMoneyProvider;
import org.joda.money.Money;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.subscriptions.CompositeSubscription;

@ControllerScope
public class TransferSendPresenter {
    public static final String ACCOUNT = "TransferActivity_Account";
    public static final String ADDRESS = "TransferActivity_Address";
    public static final String AMOUNT = "TransferActivity_Amount";
    public static final int DIALOG_FRAGMENT = 10003;
    public static final String IS_SEND_MAX = "TransferActivity_isSendMax";
    public static final String MESSAGE = "TransferActivity_Message";
    private static final int REQUEST_PIN = 10001;
    private static final int REQUEST_QR = 10002;
    private String mAddress;
    private final ApiErrorHandler mApiErrorHandler;
    private final Scheduler mBackgroundScheduler;
    private ConfirmDialogData mConfirmDialogData;
    private boolean mContactsPermissionsDenied;
    private final Context mContext;
    private final CryptoUriParser mCryptoUriParser;
    private final CurrenciesUpdatedConnector mCurrenciesUpdatedConnector;
    private Data mCurrency;
    private final CurrencyUtils mCurrencyUtils;
    private String mFilter;
    private GetGoogleSuggestionsTask mGetGoogleSuggestionsTask;
    protected String mIdem;
    private boolean mIsSendMax;
    private final LoginManager mLoginManager;
    private final Scheduler mMainScheduler;
    private String mMessage;
    private final MixpanelTracking mMixpanelTracking;
    private final MoneyFormatterUtil mMoneyFormatterUtil;
    private final PINManager mPinManager;
    private InstantExchangeQuote mQuote;
    private final TransferSendScreen mScreen;
    private com.coinbase.v2.models.account.Data mSelectedAccount;
    private BigMoney mSelectedAmount;
    private Suggestion mSelectedSuggestion;
    private final CompositeSubscription mSubscription = new CompositeSubscription();
    private final TransferMadeConnector mTransferMadeConnector;
    private String mUserFee;

    public class ConfirmDialogData {
        String fee;
        String id;
        String notes;
        String recipient;
        Money roundedAmount;

        ConfirmDialogData(String recipientEmail, String accountId, Money amount, String fee, String notes) {
            this.recipient = recipientEmail;
            this.id = accountId;
            this.roundedAmount = amount;
            this.fee = fee;
            this.notes = notes;
        }
    }

    class GetGoogleSuggestionsTask {
        private String mFilter;

        protected GetGoogleSuggestionsTask(String filter) {
            this.mFilter = filter;
        }

        public Observable<List<Suggestion>> call() {
            return Observable.create(TransferSendPresenter$GetGoogleSuggestionsTask$$Lambda$1.lambdaFactory$(this)).subscribeOn(TransferSendPresenter.this.mBackgroundScheduler);
        }

        static /* synthetic */ void lambda$call$0(GetGoogleSuggestionsTask this_, Subscriber subscriber) {
            String filter;
            ArrayList<Suggestion> emlRecs = new ArrayList();
            HashSet<String> emlRecsHS = new HashSet();
            if (!TransferSendPresenter.this.mScreen.isShown()) {
                subscriber.onNext(null);
            }
            ContentResolver cr = TransferSendPresenter.this.mContext.getContentResolver();
            String[] PROJECTION = new String[]{"data1", "display_name"};
            if (this_.mFilter == null) {
                filter = null;
            } else {
                filter = "data1 LIKE '%" + this_.mFilter + "%' OR " + "display_name" + " LIKE '%" + this_.mFilter + "%'";
            }
            Cursor cur = cr.query(Email.CONTENT_URI, PROJECTION, filter, null, null);
            if (cur.moveToFirst()) {
                do {
                    String emlAddr = cur.getString(0);
                    String name = cur.getString(1);
                    if (emlRecsHS.add(emlAddr.toLowerCase())) {
                        emlRecs.add(new Suggestion(emlAddr, name));
                    }
                } while (cur.moveToNext());
            }
            cur.close();
            subscriber.onNext(emlRecs);
            subscriber.onCompleted();
        }
    }

    static class Suggestion {
        String email;
        String name;

        private Suggestion(String email, String name) {
            this.email = email;
            this.name = name;
        }
    }

    private interface UpdateFeeCallback {
        void run(String str);
    }

    @Inject
    public TransferSendPresenter(TransferSendScreen screen, Application app, CurrenciesUpdatedConnector currenciesUpdatedConnector, LoginManager loginManager, MoneyFormatterUtil moneyFormatterUtil, CurrencyUtils currencyUtils, PINManager pinManager, CryptoUriParser cryptoUriParser, TransferMadeConnector transferMadeConnector, MixpanelTracking mixpanelTracking, ApiErrorHandler apiErrorHandler, @MainScheduler Scheduler mainScheduler, @BackgroundScheduler Scheduler backgroundScheduler) {
        this.mScreen = screen;
        this.mContext = app;
        this.mCurrenciesUpdatedConnector = currenciesUpdatedConnector;
        this.mLoginManager = loginManager;
        this.mMoneyFormatterUtil = moneyFormatterUtil;
        this.mCurrencyUtils = currencyUtils;
        this.mPinManager = pinManager;
        this.mCryptoUriParser = cryptoUriParser;
        this.mTransferMadeConnector = transferMadeConnector;
        this.mMixpanelTracking = mixpanelTracking;
        this.mApiErrorHandler = apiErrorHandler;
        this.mMainScheduler = mainScheduler;
        this.mBackgroundScheduler = backgroundScheduler;
    }

    void onCreate(Bundle args) {
        this.mSelectedAmount = (BigMoney) args.getSerializable(AMOUNT);
        this.mSelectedAccount = (com.coinbase.v2.models.account.Data) new Gson().fromJson(args.getString(ACCOUNT), com.coinbase.v2.models.account.Data.class);
        if (this.mSelectedAccount == null && this.mLoginManager.getAccounts().size() > 0) {
            this.mSelectedAccount = (com.coinbase.v2.models.account.Data) this.mLoginManager.getAccounts().get(0);
        }
        if (this.mSelectedAccount == null) {
            this.mScreen.dismissWithError();
            return;
        }
        this.mCurrency = this.mCurrenciesUpdatedConnector.getCurrencyByCode(this.mSelectedAccount.getCurrency().getCode());
        if (this.mCurrency == null) {
            this.mScreen.dismissWithError();
            return;
        }
        this.mAddress = args.getString(ADDRESS);
        this.mMessage = args.getString(MESSAGE);
        this.mIsSendMax = args.getBoolean(IS_SEND_MAX);
        this.mContactsPermissionsDenied = false;
    }

    void onShow() {
        if (this.mSelectedAccount == null) {
            this.mScreen.dismissWithError();
            return;
        }
        String amount = this.mMoneyFormatterUtil.formatMoney(this.mSelectedAmount, EnumSet.of(Options.ROUND_8_DIGITS));
        this.mScreen.setTitle(String.format(this.mContext.getString(R.string.transfer_send_title), new Object[]{amount}));
        this.mScreen.requestRecipientFocus();
        this.mScreen.showKeyboardFromRecipient();
        if (this.mSelectedAccount.getCurrency() != null && this.mSelectedAccount.getCurrency().getCode() != null) {
            this.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_SEND_VIEWED, "currency", this.mSelectedAccount.getCurrency().getCode());
        }
    }

    void onHide() {
        this.mSubscription.clear();
    }

    void onViewCreated() {
        if (this.mSelectedAccount == null) {
            this.mScreen.dismissWithError();
            return;
        }
        String hintFormat = this.mContext.getString(R.string.transfer_sender);
        String hint = String.format(hintFormat, new Object[]{this.mContext.getString(R.string.bitcoin)});
        if (this.mCurrency != null) {
            hint = String.format(hintFormat, new Object[]{this.mCurrency.getName()});
        }
        this.mScreen.initializeViews(hint);
        onTransferSendFocused(true, this.mScreen.getRecipient());
        updateSuggestions(null);
        if (!TextUtils.isEmpty(this.mAddress)) {
            this.mScreen.setRecipient(this.mAddress);
            updateFee(this.mAddress, null);
        }
        if (!TextUtils.isEmpty(this.mMessage)) {
            this.mScreen.setMessage(this.mMessage);
        }
    }

    void onTransferSendFocused(boolean hasFocus, String recipient) {
        if (hasFocus) {
            this.mScreen.showSuggestionsList();
        }
        updateFee(recipient, null);
        boolean z = (hasFocus || recipient.isEmpty()) ? false : true;
        setContactVisible(z);
    }

    void onRecipientTextChanged(String recipient) {
        this.mSelectedSuggestion = null;
        if (TextUtils.isEmpty(recipient)) {
            this.mScreen.showNotes(false);
        } else {
            updateSuggestions(recipient);
            if (!this.mScreen.isShowingRecipientList()) {
                this.mScreen.showSuggestionsList();
            }
        }
        updateFee(recipient, null);
    }

    void onRecipientClicked(Suggestion selectedSuggestion) {
        this.mScreen.setRecipient(!TextUtils.isEmpty(selectedSuggestion.name) ? selectedSuggestion.name : selectedSuggestion.email);
        this.mSelectedSuggestion = selectedSuggestion;
        this.mScreen.showNotes(true);
        updateFee(selectedSuggestion.email, null);
    }

    void onSendMenuClicked() {
        if (this.mPinManager.isProtected(this.mContext, AccessType.SEND_MONEY)) {
            this.mScreen.showPINPrompt(10001);
        } else {
            initiateSend();
        }
    }

    boolean onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == DIALOG_FRAGMENT) {
            if (!this.mScreen.isShown()) {
                return true;
            }
            Bundle bundle = null;
            if (data != null) {
                bundle = data.getExtras();
            }
            if (resultCode == -1) {
                if (bundle != null) {
                    Utils.showMessage(this.mContext, bundle.getString(ConfirmSendTransferPresenter.MESSAGE), 0);
                }
                this.mScreen.routeSuccess();
            } else if (resultCode == ConfirmSendTransferPresenter.SEND_FAILED) {
                if (bundle != null) {
                    String errorBody = bundle.getString(ConfirmSendTransferPresenter.ERROR_MESSAGE);
                    if (TextUtils.isEmpty(errorBody) || !this.mApiErrorHandler.handleApiError(errorBody, "send")) {
                        handleRequestError(Utils.getErrorMessage(errorBody));
                    } else {
                        String str;
                        MixpanelTracking mixpanelTracking = this.mMixpanelTracking;
                        String str2 = MixpanelTracking.EVENT_SEND_VIEWED_LIMIT_EXCEEDED_ERROR;
                        String[] strArr = new String[4];
                        strArr[0] = "currency";
                        if (this.mSelectedAccount == null) {
                            str = "";
                        } else {
                            str = this.mSelectedAccount.getCurrency().getCode();
                        }
                        strArr[1] = str;
                        strArr[2] = "error_type";
                        strArr[3] = MixpanelTracking.PROPERTY_VALUE_SEND_ERROR_TYPE_SERVER;
                        mixpanelTracking.trackEvent(str2, strArr);
                        return true;
                    }
                }
                Utils.showMessage(this.mContext, (int) R.string.an_error_occurred, 0);
            }
            return true;
        } else if (requestCode == 10001 && resultCode == -1) {
            initiateSend();
            return true;
        } else if (requestCode != REQUEST_QR || resultCode != -1) {
            return false;
        } else {
            String contents = data.getStringExtra(Scan.RESULT);
            if (contents != null) {
                try {
                    this.mAddress = this.mCryptoUriParser.parse(contents).getAddress();
                } catch (InvalidCryptoUriException e) {
                    this.mAddress = contents;
                }
            }
            if (this.mAddress != null) {
                setRecipient(this.mAddress);
                this.mSelectedSuggestion = null;
                this.mScreen.showNotes(true);
            }
            return true;
        }
    }

    void onContactPermissionsDenied() {
        this.mContactsPermissionsDenied = true;
    }

    void onContactsPermissionGranted() {
        this.mScreen.clearGoogleSuggestions();
        this.mSubscription.clear();
        this.mGetGoogleSuggestionsTask = new GetGoogleSuggestionsTask(this.mFilter);
        this.mSubscription.add(this.mGetGoogleSuggestionsTask.call().observeOn(this.mMainScheduler).subscribe(TransferSendPresenter$$Lambda$1.lambdaFactory$(this), TransferSendPresenter$$Lambda$2.lambdaFactory$(this), TransferSendPresenter$$Lambda$3.lambdaFactory$(this)));
    }

    void onStartQrScanClicked() {
        String str;
        String text = null;
        if (!(this.mSelectedAccount == null || this.mCurrenciesUpdatedConnector.getCurrencyByCode(this.mSelectedAccount.getCurrency().getCode()) == null)) {
            text = String.format(this.mContext.getString(R.string.qr_code_viewfinder_description), new Object[]{currency.getName()});
        }
        this.mScreen.showCaptureActivity(text, REQUEST_QR);
        MixpanelTracking mixpanelTracking = this.mMixpanelTracking;
        String str2 = MixpanelTracking.EVENT_SEND_TAPPED_QR_CODE;
        String[] strArr = new String[2];
        strArr[0] = "currency";
        if (this.mSelectedAccount == null) {
            str = "";
        } else {
            str = this.mSelectedAccount.getCurrency().getCode();
        }
        strArr[1] = str;
        mixpanelTracking.trackEvent(str2, strArr);
    }

    void onTransferMoneyContactClicked() {
        String str;
        setContactVisible(false);
        MixpanelTracking mixpanelTracking = this.mMixpanelTracking;
        String str2 = MixpanelTracking.EVENT_SEND_TAPPED_CONTACT;
        String[] strArr = new String[2];
        strArr[0] = "currency";
        if (this.mSelectedAccount == null) {
            str = "";
        } else {
            str = this.mSelectedAccount.getCurrency().getCode();
        }
        strArr[1] = str;
        mixpanelTracking.trackEvent(str2, strArr);
    }

    void onTransferMoneyContactClearClicked() {
        setRecipient("");
        setContactVisible(false);
    }

    void onNotesTapped() {
        String str;
        MixpanelTracking mixpanelTracking = this.mMixpanelTracking;
        String str2 = MixpanelTracking.EVENT_SEND_TAPPED_NOTE;
        String[] strArr = new String[2];
        strArr[0] = "currency";
        if (this.mSelectedAccount == null) {
            str = "";
        } else {
            str = this.mSelectedAccount.getCurrency().getCode();
        }
        strArr[1] = str;
        mixpanelTracking.trackEvent(str2, strArr);
    }

    void onRecipientTapped() {
        String str;
        MixpanelTracking mixpanelTracking = this.mMixpanelTracking;
        String str2 = MixpanelTracking.EVENT_SEND_TAPPED_TO;
        String[] strArr = new String[2];
        strArr[0] = "currency";
        if (this.mSelectedAccount == null) {
            str = "";
        } else {
            str = this.mSelectedAccount.getCurrency().getCode();
        }
        strArr[1] = str;
        mixpanelTracking.trackEvent(str2, strArr);
    }

    private void updateSuggestions(String filter) {
        if (!this.mContactsPermissionsDenied) {
            this.mFilter = filter;
            if (this.mGetGoogleSuggestionsTask != null) {
                this.mSubscription.clear();
            }
            this.mScreen.clearEmailSuggestions();
            getCBSuggestions(filter);
            this.mScreen.requestContactsPermission();
        }
    }

    private void updateFee(String address, UpdateFeeCallback callback) {
        this.mSubscription.add(this.mLoginManager.getClient().getTransactionFee(this.mSelectedAccount.getId(), address).onBackpressureLatest().observeOn(this.mMainScheduler).subscribe(TransferSendPresenter$$Lambda$4.lambdaFactory$(this, callback), TransferSendPresenter$$Lambda$5.lambdaFactory$(callback)));
    }

    static /* synthetic */ void lambda$updateFee$3(TransferSendPresenter this_, UpdateFeeCallback callback, Pair pair) {
        Response<TransactionFee> response = pair.first;
        if (response.isSuccessful()) {
            if (!(response.body() == null || ((TransactionFee) response.body()).getData() == null)) {
                UserFee userFee = ((TransactionFee) response.body()).getData().getUserFee();
                this_.mScreen.showFeeDescription(userFee.getAmount() + " " + userFee.getCurrency());
                this_.mUserFee = userFee.getAmount();
                BigMoneyProvider feeAmount = this_.mMoneyFormatterUtil.moneyFromValue(userFee.getCurrency(), userFee.getAmount());
                if (feeAmount == null) {
                    Utils.showMessage(this_.mContext, (int) R.string.error_occurred_try_again, 1);
                    return;
                }
                BigMoneyProvider totalAmount;
                if (this_.mIsSendMax) {
                    totalAmount = this_.mSelectedAmount.toMoney().toBigMoney().minus(feeAmount);
                } else {
                    totalAmount = this_.mSelectedAmount.toMoney().toBigMoney().plus(feeAmount);
                }
                this_.mScreen.showTransferSendTotal(this_.mMoneyFormatterUtil.formatMoney(totalAmount));
                if (feeAmount.toBigMoney().isPositive()) {
                    this_.mScreen.showFee();
                } else {
                    this_.mScreen.hideFee();
                }
            }
            if (callback != null) {
                callback.run(this_.mUserFee);
            }
        } else if (callback != null) {
            callback.run(null);
        }
    }

    static /* synthetic */ void lambda$updateFee$4(UpdateFeeCallback callback, Throwable t) {
        if (callback != null) {
            callback.run(null);
        }
    }

    private void getCBSuggestions(String filter) {
        this.mSubscription.add(this.mLoginManager.getClient().getContactsListRx(filter).onBackpressureLatest().observeOn(this.mMainScheduler).subscribe(TransferSendPresenter$$Lambda$6.lambdaFactory$(this), TransferSendPresenter$$Lambda$7.lambdaFactory$(this)));
    }

    static /* synthetic */ void lambda$getCBSuggestions$5(TransferSendPresenter this_, Pair pair) {
        Response<Contacts> response = pair.first;
        Retrofit retrofit = pair.second;
        if (response.isSuccessful()) {
            String str;
            List<com.coinbase.api.internal.models.contacts.Data> contacts = ((Contacts) response.body()).getData();
            if (contacts.size() == 0) {
                this_.mScreen.hideRecentContactsHeader();
                this_.mScreen.clearEmailSuggestions();
            } else {
                this_.mScreen.showRecentContactsHeader();
                List<Suggestion> suggestionList = new LinkedList();
                for (com.coinbase.api.internal.models.contacts.Data contact : contacts) {
                    suggestionList.add(new Suggestion(contact.getEmail(), null));
                }
                this_.mScreen.showEmailSuggestions(suggestionList);
            }
            MixpanelTracking mixpanelTracking = this_.mMixpanelTracking;
            String str2 = MixpanelTracking.EVENT_SEND_VIEWED_RECENT;
            String[] strArr = new String[4];
            strArr[0] = "currency";
            if (this_.mSelectedAccount == null) {
                str = "";
            } else {
                str = this_.mSelectedAccount.getCurrency().getCode();
            }
            strArr[1] = str;
            strArr[2] = MixpanelTracking.PROPERTY_NUM_RECENT;
            strArr[3] = String.valueOf(contacts.size());
            mixpanelTracking.trackEvent(str2, strArr);
            return;
        }
        this_.mScreen.hideRecentContactsHeader();
        Utils.showRetrofitErrorMessage(response, retrofit, this_.mContext);
    }

    static /* synthetic */ void lambda$getCBSuggestions$6(TransferSendPresenter this_, Throwable t) {
        this_.mScreen.hideRecentContactsHeader();
        Log.w("Coinbase", "Exception in fetching contact suggestions: " + t.getMessage());
    }

    protected void onSuccess(List<Suggestion> suggestions) {
        if (suggestions != null) {
            String str;
            if (suggestions.size() == 0) {
                this.mScreen.hideContactsHeader();
                this.mScreen.clearGoogleSuggestions();
            } else {
                this.mScreen.showContactsHeader();
                this.mScreen.showGoogleSuggestions(suggestions);
            }
            MixpanelTracking mixpanelTracking = this.mMixpanelTracking;
            String str2 = MixpanelTracking.EVENT_SEND_VIEWED_CONTACTS;
            String[] strArr = new String[4];
            strArr[0] = "currency";
            if (this.mSelectedAccount == null) {
                str = "";
            } else {
                str = this.mSelectedAccount.getCurrency().getCode();
            }
            strArr[1] = str;
            strArr[2] = MixpanelTracking.PROPERTY_NUM_CONTACTS;
            strArr[3] = String.valueOf(suggestions.size());
            mixpanelTracking.trackEvent(str2, strArr);
        }
    }

    protected void onFinally() {
        this.mGetGoogleSuggestionsTask = null;
    }

    private void initiateSend() {
        this.mScreen.hideKeyboard();
        Money roundedAmount = this.mSelectedAmount.toMoney(RoundingMode.HALF_EVEN);
        String recipient = getEnteredRecipient();
        if (TextUtils.isEmpty(recipient)) {
            Utils.showMessage(this.mContext, (int) R.string.transfer_recipient_empty, 0);
        } else if (!isValidRecipient(recipient)) {
            Utils.showMessage(this.mContext, (int) R.string.transfer_invalid_recipient, 0);
        } else if (Utils.isConnectedOrConnecting(this.mContext)) {
            this.mConfirmDialogData = new ConfirmDialogData(recipient, this.mSelectedAccount.getId(), roundedAmount, this.mUserFee, this.mScreen.getEnteredNotes());
            showConfirmSendTransfer();
        } else {
            com.coinbase.v2.models.transactions.Data transaction = new com.coinbase.v2.models.transactions.Data();
            transaction.setDescription(this.mScreen.getEnteredNotes());
            transaction.setAmount(TransactionUtils.amountFromMoney(roundedAmount));
            Entity to = new Entity();
            to.setId(getEnteredRecipient());
            transaction.setTo(to);
            this.mScreen.showDelayedTransactionDialog(transaction, this.mSelectedAccount.getId());
        }
    }

    private void showConfirmSendTransfer() {
        String exchangeId = null;
        if (!(this.mQuote == null || this.mQuote.getData() == null)) {
            exchangeId = this.mQuote.getData().getId();
        }
        this.mScreen.showConfirmDialog(this.mConfirmDialogData.recipient, this.mConfirmDialogData.id, this.mConfirmDialogData.roundedAmount, this.mConfirmDialogData.fee, this.mIsSendMax, this.mConfirmDialogData.notes, exchangeId, DIALOG_FRAGMENT);
        MixpanelTracking.getInstance().trackEvent(MixpanelTracking.EVENT_SEND_REQUESTED, new String[0]);
    }

    private void handleRequestError(String message) {
        if (!this.mScreen.isShown()) {
            return;
        }
        if (message == null) {
            Utils.showMessage(this.mContext, (int) R.string.an_error_occurred, 0);
        } else {
            Utils.showMessage(this.mContext, message, 0);
        }
    }

    private String getEnteredRecipient() {
        if (this.mSelectedSuggestion != null) {
            return this.mSelectedSuggestion.email;
        }
        return this.mScreen.getRecipient();
    }

    private void setRecipient(String recipient) {
        this.mScreen.setRecipient(recipient);
        if (recipient != null && !recipient.isEmpty()) {
            updateFee(recipient, null);
        }
    }

    private void setContactVisible(boolean visible) {
        String email = this.mSelectedSuggestion == null ? this.mScreen.getRecipient() : this.mSelectedSuggestion.email;
        String name = this.mSelectedSuggestion == null ? this.mScreen.getRecipient() : this.mSelectedSuggestion.name;
        if (name == null) {
            name = email;
        }
        this.mScreen.setContactVisible(visible, email, name);
    }

    boolean isValidRecipient(String recipient) {
        if (Patterns.EMAIL_ADDRESS.matcher(recipient).find()) {
            return true;
        }
        return this.mCurrencyUtils.validateAddress(this.mCurrency, recipient);
    }
}
