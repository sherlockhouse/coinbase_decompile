package com.coinbase.android.buysell;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import com.coinbase.android.R;
import com.coinbase.android.confirmation.ConfirmationDetailListAdapter.ConfirmationDetail;
import com.coinbase.android.confirmation.ConfirmationPresenter;
import com.coinbase.android.confirmation.ItemType;
import com.coinbase.android.splittesting.SplitTestConstants;
import com.coinbase.android.splittesting.SplitTesting;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.utils.PaymentMethodUtils;
import com.coinbase.android.utils.TransferUtils;
import com.coinbase.android.utils.Utils;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.internal.ApiConstants;
import com.coinbase.v2.models.account.Data;
import com.coinbase.v2.models.errors.ErrorBody;
import com.coinbase.v2.models.transfers.Transfer;
import com.coinbase.v2.models.transfers.TransferError;
import com.coinbase.v2.models.transfers.UserWarning;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.List;
import okhttp3.ResponseBody;

abstract class AbstractBuySellConfirmationPresenter implements ConfirmationPresenter {
    public static final String ACCOUNT = "BUY_SELL_CONFIRMATION_ACCOUNT";
    private static final String AVAILABLE_DATE_FORMAT = "MMMM dd, yyyy";
    public static final String FEE_HELP_URL = "https://support.coinbase.com/customer/en/portal/articles/2109597-buy-sell-bank-transfer-fees";
    public static final String PAYMENT_METHOD = "BUY_SELL_CONFIRMATION_PAYMENT_METHOD";
    public static final String PAYMENT_METHOD_LIST = "BUY_SELL_CONFIRMATION_PAYMENT_METHOD_LIST";
    public static final String TRANSFER = "BUY_SELL_CONFIRMATION_TRANSFER";
    protected Data mAccount;
    private final Context mContext;
    private List<ConfirmationDetail> mDetailList = new ArrayList();
    private final MixpanelTracking mMixpanelTracking;
    protected com.coinbase.v2.models.paymentMethods.Data mPaymentMethod;
    protected List<com.coinbase.v2.models.paymentMethods.Data> mPaymentMethodList;
    private final PaymentMethodUtils mPaymentMethodUtils;
    private final AbstractBuySellConfirmationScreen mScreen;
    private final SnackBarWrapper mSnackBarWrapper;
    private final SplitTesting mSplitTesting;
    protected Transfer mTransfer;
    protected com.coinbase.v2.models.transfers.Data mTransferData;
    private final TransferUtils mTransferUtils;

    protected abstract String getPaymentMethodHeader();

    protected AbstractBuySellConfirmationPresenter(Application app, AbstractBuySellConfirmationScreen screen, PaymentMethodUtils paymentMethodUtils, TransferUtils transferUtils, MixpanelTracking mixpanelTracking, SnackBarWrapper snackBarWrapper, SplitTesting splitTesting) {
        this.mContext = app;
        this.mScreen = screen;
        this.mPaymentMethodUtils = paymentMethodUtils;
        this.mTransferUtils = transferUtils;
        this.mMixpanelTracking = mixpanelTracking;
        this.mSnackBarWrapper = snackBarWrapper;
        this.mSplitTesting = splitTesting;
    }

    void onViewCreated(Bundle args) {
        Gson gson = new Gson();
        this.mTransfer = (Transfer) gson.fromJson(args.getString("BUY_SELL_CONFIRMATION_TRANSFER"), Transfer.class);
        this.mAccount = (Data) gson.fromJson(args.getString("BUY_SELL_CONFIRMATION_ACCOUNT"), Data.class);
        if (this.mAccount == null) {
            this.mAccount = (Data) gson.fromJson(args.getString("selected_account"), Data.class);
        }
        this.mPaymentMethod = (com.coinbase.v2.models.paymentMethods.Data) gson.fromJson(args.getString("BUY_SELL_CONFIRMATION_PAYMENT_METHOD"), com.coinbase.v2.models.paymentMethods.Data.class);
        if (this.mTransfer == null || this.mTransfer.getData() == null || this.mAccount == null) {
            showGenericError();
            return;
        }
        this.mTransferData = this.mTransfer.getData();
        String paymentMethodString = args.getString(PAYMENT_METHOD_LIST);
        if (paymentMethodString != null) {
            this.mPaymentMethodList = (List) new Gson().fromJson(paymentMethodString, new TypeToken<ArrayList<com.coinbase.v2.models.paymentMethods.Data>>() {
            }.getType());
        }
        createDetailList();
        setWarning();
        setCryptoAmount();
        this.mScreen.setButtonText(getTitle());
    }

    public void onFeeHelpClicked() {
        this.mScreen.displayFeeHelpUrl(this.mTransferData.getFeeExplanationUrl() != null ? this.mTransferData.getFeeExplanationUrl() : FEE_HELP_URL);
    }

    public List<ConfirmationDetail> getDetailList() {
        return this.mDetailList;
    }

    protected void trackEvent(String event, String... properties) {
        this.mMixpanelTracking.trackEvent(event, properties);
    }

    protected void confirmCompleted() {
        this.mScreen.onConfirmCompleted();
    }

    protected void showGenericError() {
        this.mSnackBarWrapper.showGenericError();
    }

    protected void setSelectedPaymentMethod(com.coinbase.v2.models.paymentMethods.Data paymentMethod) {
        this.mPaymentMethod = paymentMethod;
        this.mScreen.getArgs().putString("BUY_SELL_CONFIRMATION_PAYMENT_METHOD", new Gson().toJson(this.mPaymentMethod));
    }

    protected void updateTransfer(Transfer transfer) {
        if (transfer != null) {
            this.mTransfer = transfer;
            this.mTransferData = this.mTransfer.getData();
            this.mScreen.getArgs().putString("BUY_SELL_CONFIRMATION_TRANSFER", new Gson().toJson(this.mTransfer));
            setCryptoAmount();
            createDetailList();
            setWarning();
        }
    }

    protected String getError(ResponseBody errorBody) {
        if (errorBody == null) {
            return this.mContext.getString(R.string.error_occurred_try_again);
        }
        try {
            ErrorBody error = this.mTransferUtils.getFirstError((TransferError) new Gson().fromJson(Utils.getErrorBody(errorBody), TransferError.class));
            if (error == null) {
                return this.mContext.getString(R.string.error_occurred_try_again);
            }
            return error.getMessage();
        } catch (JsonSyntaxException e) {
            return this.mContext.getString(R.string.error_occurred_try_again);
        }
    }

    private void setCryptoAmount() {
        String amount = this.mTransferUtils.getCurrencyAmountString(this.mTransferData);
        if (amount != null) {
            this.mScreen.setCryptoAmount(amount);
        }
    }

    private void createDetailList() {
        int holdDays;
        this.mDetailList.clear();
        this.mDetailList.add(new ConfirmationDetail(String.format(getString(R.string.confirm_price_header), new Object[]{this.mAccount.getCurrency().getCode()}), this.mTransferUtils.getPricePerCrypto(this.mTransferData), ItemType.DETAIL));
        this.mDetailList.add(new ConfirmationDetail(getAvailableHeader(), getAvailableMessage(), ItemType.DETAIL));
        if (this.mTransferData.getHoldDays() != null) {
            holdDays = this.mTransferData.getHoldDays().intValue();
        } else {
            holdDays = 0;
        }
        if (holdDays > 0) {
            this.mDetailList.add(new ConfirmationDetail(getString(R.string.confirm_available_hold_days), this.mContext.getResources().getQuantityString(R.plurals.in_days, holdDays, new Object[]{Integer.valueOf(holdDays)}), ItemType.DETAIL));
        }
        if (this.mPaymentMethod != null) {
            if (this.mSplitTesting.isInGroup(SplitTestConstants.REMOVE_BUY_SELL_PAYMENT_METHOD_SPLIT_TEST, "tEsTdIsAbLeD")) {
                this.mDetailList.add(new ConfirmationDetail(getPaymentMethodHeader(), this.mPaymentMethodUtils.getShortenedName(this.mPaymentMethod), this.mPaymentMethod.getImage(), ItemType.PAYMENT_METHOD));
            } else {
                this.mDetailList.add(new ConfirmationDetail(getPaymentMethodHeader(), this.mPaymentMethodUtils.getShortenedName(this.mPaymentMethod), this.mPaymentMethod.getImage(), ItemType.DETAIL));
            }
        }
        if (this.mTransferData.getFee() != null) {
            this.mDetailList.add(new ConfirmationDetail(getString(R.string.confirm_coinbase_fee_header), this.mTransferUtils.getTotalFeeString(this.mTransferData), ItemType.FEE));
        }
        if (this.mTransferData.getPaymentMethodFee() != null) {
            this.mDetailList.add(new ConfirmationDetail(getString(R.string.confirm_payment_method_fee_header), this.mTransferUtils.getPaymentMethodFeeString(this.mTransferData), ItemType.FEE));
        }
        this.mDetailList.add(new ConfirmationDetail(getString(R.string.confirm_credit_total_header), this.mTransferUtils.getTotalAmount(this.mTransferData), ItemType.TOTAL));
        this.mScreen.notifyDataSetChanged();
    }

    private void setWarning() {
        UserWarning userWarning = getWarningMessage();
        if (userWarning == null || userWarning.getMessage() == null) {
            this.mScreen.hideWarningMessage();
        } else {
            this.mScreen.showWarningMessage(userWarning.getMessage());
        }
    }

    boolean isInstant() {
        return this.mTransferUtils.isInstant(this.mTransferData);
    }

    private String getAvailableMessage() {
        if (isInstant()) {
            return getString(R.string.confirm_available_instantly);
        }
        return Utils.getDateTimeFrom(this.mTransferData.getPayoutAt()).toString(AVAILABLE_DATE_FORMAT);
    }

    private UserWarning getWarningMessage() {
        for (UserWarning warning : this.mTransfer.getUserWarnings()) {
            String warningId = warning.getId();
            if (warningId != null && !warningId.equalsIgnoreCase(ApiConstants.SELLS_UNSUPPORTED)) {
                return warning;
            }
        }
        return null;
    }

    protected String getString(int resourceId) {
        return this.mContext.getString(resourceId);
    }

    protected String getAvailableHeader() {
        return getString(R.string.confirm_available_header);
    }
}
