package com.coinbase.android.transfers;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.Pair;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.R;
import com.coinbase.android.event.TransferMadeEvent;
import com.coinbase.android.ui.MainScheduler;
import com.coinbase.android.utils.MoneyFormatterUtil;
import com.coinbase.android.utils.Utils;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.LoginManager;
import com.coinbase.api.internal.ApiConstants;
import com.coinbase.v2.models.transactions.Transaction;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.UUID;
import javax.inject.Inject;
import org.joda.money.Money;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Response;
import rx.Scheduler;
import rx.subscriptions.CompositeSubscription;

@ControllerScope
public class ConfirmSendTransferPresenter {
    static final String ACCOUNT = "ConfirmTransferFragment_Account";
    static final String AMOUNT = "ConfirmTransferFragment_Amount";
    static final String COUNTERPARTY_EMAIL = "ConfirmTransferFragment_Counteryparty";
    public static final String ERROR_MESSAGE = "ERROR_MESSAGE";
    static final String EXCHANGE_ID = "ConfirmTransferFragment_Exchange_Id";
    static final String FEE = "ConfirmSendTransferFragment_Fee";
    static final String IDEM_TOKEN = "ConfirmTransferFragment_Idem_Token";
    static final String IS_SEND_MAX = "ConfirmTransferFragment_Is_Send_Max";
    public static final String MESSAGE = "MESSAGE";
    static final String NOTES = "ConfirmTransferFragment_Notes";
    public static final int SEND_FAILED = 1234;
    private String mAccount;
    private Money mAmount;
    private final Context mContext;
    private String mCounterparty;
    private String mExchangeId;
    private Money mFee;
    private String mFeeStr;
    private String mIdem;
    private boolean mIsSendMax;
    private final Logger mLogger = LoggerFactory.getLogger(ConfirmSendTransferPresenter.class);
    private final LoginManager mLoginManager;
    private final Scheduler mMainScheduler;
    private final MixpanelTracking mMixpanelTracking;
    private final MoneyFormatterUtil mMoneyFormatterUtil;
    private String mNotes;
    private final ConfirmSendTransferScreen mScreen;
    private final CompositeSubscription mSubscription = new CompositeSubscription();
    private final TransferMadeConnector mTransferMadeConnector;

    @Inject
    public ConfirmSendTransferPresenter(ConfirmSendTransferScreen screen, Application app, MoneyFormatterUtil moneyFormatterUtil, LoginManager loginManager, TransferMadeConnector transferMadeConnector, MixpanelTracking mixpanelTracking, @MainScheduler Scheduler mainScheduler) {
        this.mScreen = screen;
        this.mContext = app;
        this.mMoneyFormatterUtil = moneyFormatterUtil;
        this.mLoginManager = loginManager;
        this.mTransferMadeConnector = transferMadeConnector;
        this.mMixpanelTracking = mixpanelTracking;
        this.mMainScheduler = mainScheduler;
    }

    public void onCreate(Bundle args) {
        this.mCounterparty = args.getString(COUNTERPARTY_EMAIL);
        this.mNotes = args.getString(NOTES);
        this.mAmount = (Money) args.getSerializable(AMOUNT);
        this.mAccount = args.getString(ACCOUNT);
        this.mFeeStr = args.getString(FEE);
        if (!(TextUtils.isEmpty(this.mFeeStr) || this.mAmount == null)) {
            this.mFee = this.mMoneyFormatterUtil.moneyFromValue(this.mAmount.getCurrencyUnit().getCode(), this.mFeeStr);
        }
        this.mExchangeId = args.getString(EXCHANGE_ID);
        if (!args.containsKey(IDEM_TOKEN)) {
            args.putString(IDEM_TOKEN, UUID.randomUUID().toString());
        }
        this.mIdem = args.getString(IDEM_TOKEN);
        this.mIsSendMax = args.getBoolean(IS_SEND_MAX);
        this.mScreen.setMessage(Html.fromHtml(getMessage()));
        this.mScreen.showCounterParty(this.mCounterparty);
    }

    public void onUserConfirm() {
        if (this.mAmount == null) {
            this.mLogger.error("Amount null in user confirm in transfer send presenter, shouldn't happen");
            return;
        }
        this.mScreen.showTransferSendProgress();
        HashMap<String, Object> params = new HashMap();
        params.put("to", this.mCounterparty);
        String amount = parseAmountAndDismissIfInvalid();
        if (amount != null) {
            String str;
            params.put("amount", amount);
            params.put("currency", this.mAmount.getCurrencyUnit().getCurrencyCode());
            if (!(this.mFee == null || this.mFee.isZero())) {
                params.put(ApiConstants.USER_FEE, this.mFeeStr);
            }
            params.put("description", this.mNotes);
            params.put("idem", this.mIdem);
            params.put("instant_exchange_quote", this.mExchangeId);
            this.mSubscription.add(this.mLoginManager.getClient().sendMoneyRx(this.mAccount, params).onBackpressureLatest().observeOn(this.mMainScheduler).subscribe(ConfirmSendTransferPresenter$$Lambda$1.lambdaFactory$(this, String.format(this.mContext.getString(R.string.delayed_notification_success_send), new Object[]{this.mCounterparty, this.mMoneyFormatterUtil.formatMoney(this.mAmount)})), ConfirmSendTransferPresenter$$Lambda$2.lambdaFactory$(this)));
            MixpanelTracking mixpanelTracking = this.mMixpanelTracking;
            String str2 = MixpanelTracking.EVENT_SEND_TAPPED_CONFIRM;
            String[] strArr = new String[2];
            strArr[0] = "currency";
            if (this.mAmount == null) {
                str = "";
            } else {
                str = this.mAmount.getCurrencyUnit().getCode();
            }
            strArr[1] = str;
            mixpanelTracking.trackEvent(str2, strArr);
        }
    }

    static /* synthetic */ void lambda$onUserConfirm$0(ConfirmSendTransferPresenter this_, String message, Pair pair) {
        Response<Transaction> response = pair.first;
        this_.mScreen.dismissDialog();
        if (response.isSuccessful()) {
            String str;
            this_.handleRequestSuccess(message);
            this_.mTransferMadeConnector.get().onNext(new TransferMadeEvent(((Transaction) response.body()).getData(), (Transaction) response.body()));
            MixpanelTracking mixpanelTracking = this_.mMixpanelTracking;
            String str2 = MixpanelTracking.EVENT_SEND_SUCCESS;
            String[] strArr = new String[2];
            strArr[0] = "currency";
            if (this_.mAmount == null) {
                str = "";
            } else {
                str = this_.mAmount.getCurrencyUnit().getCode();
            }
            strArr[1] = str;
            mixpanelTracking.trackEvent(str2, strArr);
            return;
        }
        this_.handleRequestError(Utils.getErrorBody(response.errorBody()));
    }

    static /* synthetic */ void lambda$onUserConfirm$1(ConfirmSendTransferPresenter this_, Throwable t) {
        this_.mScreen.dismissDialog();
        this_.handleRequestError(null);
    }

    private String parseAmountAndDismissIfInvalid() {
        double userFee = 0.0d;
        if (!(this.mFee == null || this.mFee.isZero())) {
            userFee = this.mFee.getAmount().doubleValue();
        }
        double calculatedAmount = this.mAmount.getAmount().doubleValue();
        if (this.mIsSendMax) {
            calculatedAmount -= userFee;
            if (calculatedAmount < 0.0d) {
                this.mScreen.dismissDialog();
                handleRequestError(this.mContext.getString(R.string.cannot_send_max));
                return null;
            }
        }
        DecimalFormat df = new DecimalFormat("#");
        df.setMaximumFractionDigits(8);
        String formattedAmount = df.format(calculatedAmount);
        String formattedAmountParam = formattedAmount;
        if (formattedAmountParam.contains(".")) {
            return formattedAmountParam;
        }
        return formattedAmount.replace(",", ".");
    }

    private String getMessage() {
        if (this.mFee == null || this.mFee.isZero()) {
            return String.format(this.mContext.getString(R.string.transfer_confirm_message_send), new Object[]{this.mMoneyFormatterUtil.formatMoney(this.mAmount), this.mCounterparty});
        }
        return String.format(this.mContext.getString(R.string.transfer_confirm_message_send_fee), new Object[]{this.mMoneyFormatterUtil.formatMoney(this.mAmount), this.mCounterparty, this.mMoneyFormatterUtil.formatMoney(this.mFee)});
    }

    private void handleRequestError(String errorMessage) {
        Intent intent = new Intent();
        intent.putExtra(ERROR_MESSAGE, errorMessage);
        this.mScreen.handleRequestError(intent, SEND_FAILED);
    }

    private void handleRequestSuccess(String message) {
        Intent intent = new Intent();
        intent.putExtra(MESSAGE, message);
        this.mScreen.handleSuccess(intent);
    }
}
