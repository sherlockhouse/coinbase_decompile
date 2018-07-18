package com.coinbase.android.transactions;

import android.app.Application;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.Pair;
import com.coinbase.android.ActivityScope;
import com.coinbase.android.R;
import com.coinbase.android.ui.MainScheduler;
import com.coinbase.android.ui.RefreshRequestedConnector;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.utils.MoneyFormatterUtil;
import com.coinbase.android.utils.MoneyFormatterUtil.Options;
import com.coinbase.android.utils.TransactionUtils;
import com.coinbase.android.utils.TransactionUtils.TransactionStatus;
import com.coinbase.android.utils.TransactionUtils.TransactionType;
import com.coinbase.android.utils.Utils;
import com.coinbase.api.LoginManager;
import com.coinbase.v2.models.transactions.Amount;
import com.coinbase.v2.models.transactions.Data;
import java.util.EnumSet;
import javax.inject.Inject;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Scheduler;
import rx.subscriptions.CompositeSubscription;

@ActivityScope
public class TransactionDetailPresenter {
    public static final String DISPLAYABLE_COLOR = "DISPLAYABLE_COLOR";
    public static final String DISPLAYABLE_HEADER = "DISPLAYABLE_HEADER";
    public static final String DISPLAYABLE_VALUE = "DISPLAYABLE_VALUE";
    public static final String DISPLAY_DATE_FORMAT = "h:mm a - MMM d, yyyy";
    private String mAccountId;
    private final Context mContext;
    private final LoginManager mLoginManager;
    private final Scheduler mMainScheduler;
    private final MoneyFormatterUtil mMoneyFormatterUtil;
    private final RefreshRequestedConnector mRefreshRequestedConnector;
    private Data mSelectedTransaction;
    private final SnackBarWrapper mSnackBarWrapper;
    private final CompositeSubscription mSubscription = new CompositeSubscription();
    private final TransactionDetailButtonConnector mTransactionDetailButtonConnector;

    enum ActionType {
        CLOSE,
        CANCEL,
        NONE
    }

    @Inject
    public TransactionDetailPresenter(LoginManager loginManager, Application application, SnackBarWrapper snackBarWrapper, TransactionDetailButtonConnector transactionDetailButtonConnector, RefreshRequestedConnector refreshRequestedConnector, MoneyFormatterUtil moneyFormatterUtil, @MainScheduler Scheduler mainScheduler) {
        this.mLoginManager = loginManager;
        this.mContext = application;
        this.mSnackBarWrapper = snackBarWrapper;
        this.mTransactionDetailButtonConnector = transactionDetailButtonConnector;
        this.mRefreshRequestedConnector = refreshRequestedConnector;
        this.mMoneyFormatterUtil = moneyFormatterUtil;
        this.mMainScheduler = mainScheduler;
    }

    void setSelectedTransaction(String accountId, Data transaction) {
        this.mAccountId = accountId;
        this.mSelectedTransaction = transaction;
    }

    void onButtonClicked(ActionType actionType) {
        this.mTransactionDetailButtonConnector.get().onNext(actionType);
        switch (actionType) {
            case CANCEL:
                cancelTransaction();
                return;
            default:
                return;
        }
    }

    private void cancelTransaction() {
        this.mSubscription.add(this.mLoginManager.getClient().cancelRequestRx(this.mAccountId, this.mSelectedTransaction.getId()).first().observeOn(this.mMainScheduler).subscribe(TransactionDetailPresenter$$Lambda$1.lambdaFactory$(this), TransactionDetailPresenter$$Lambda$2.lambdaFactory$(this)));
    }

    static /* synthetic */ void lambda$cancelTransaction$0(TransactionDetailPresenter this_, Pair pair) {
        Response<Void> response = pair.first;
        if (!response.isSuccessful()) {
            this_.mSnackBarWrapper.showError(response, (Retrofit) pair.second);
        }
        this_.mSnackBarWrapper.show((int) R.string.transactiondetails_action_success_cancel);
        this_.handleRequestCompleted();
    }

    static /* synthetic */ void lambda$cancelTransaction$1(TransactionDetailPresenter this_, Throwable t) {
        if (t != null && !t.getMessage().equalsIgnoreCase("Canceled")) {
            this_.mSnackBarWrapper.show((int) R.string.transactiondetails_action_failed_cancel);
        }
    }

    private void handleRequestCompleted() {
        this.mRefreshRequestedConnector.get().onNext(null);
    }

    String getHeaderStringValue(int resourceId) {
        return this.mContext.getResources().getString(resourceId);
    }

    String getDisplayableCryptoPrice(Data transaction) {
        if (transaction == null || transaction.getAmount() == null || TransactionUtils.getPricePerCrypto(transaction, this.mMoneyFormatterUtil) == null) {
            return null;
        }
        return String.format(this.mContext.getResources().getString(R.string.transaction_crypto_price), new Object[]{this.mMoneyFormatterUtil.formatMoney(pricePerCrypto, EnumSet.of(Options.ROUND_2_DIGITS)), transaction.getAmount().getCurrency()});
    }

    String getDisplayablePaymentMethodName(Data transaction) {
        if (transaction.getDetails() == null) {
            return null;
        }
        return transaction.getDetails().getPaymetnMethodName();
    }

    String getDisplayableFee(Data transaction) {
        if (transaction == null) {
            return null;
        }
        Amount fee = TransactionUtils.getFee(transaction);
        if (fee != null) {
            return this.mMoneyFormatterUtil.formatCurrencyAmount(fee.getAmount(), fee.getCurrency());
        }
        return null;
    }

    String getDisplayableDate(Data transaction) {
        if (transaction.getCreatedAt() == null) {
            return null;
        }
        return Utils.getDateTimeFrom(transaction.getCreatedAt()).toString(DISPLAY_DATE_FORMAT);
    }

    String getDisplayableNotes(Data transaction) {
        return transaction.getDescription();
    }

    String getDisplayableStatus(Data transaction) {
        TransactionStatus status = TransactionStatus.toStatus(transaction.getStatus());
        if (status == TransactionStatus.UNKNOWN) {
            return null;
        }
        String displayableValue = status.getDisplayValue();
        switch (status) {
            case PENDING:
                if (transaction.getNetwork() == null || transaction.getNetwork().getConfirmations() == null || transaction.getNetwork().getConfirmations().intValue() <= 1) {
                    return displayableValue;
                }
                return String.format(this.mContext.getResources().getString(R.string.transaction_num_confirmation), new Object[]{transaction.getNetwork().getConfirmations()});
            default:
                return displayableValue;
        }
    }

    int getStatusViewColor(Data transaction) {
        int statusViewColor;
        switch (TransactionStatus.toStatus(transaction.getStatus())) {
            case PENDING:
                statusViewColor = R.color.transaction_orange;
                break;
            case COMPLETED:
                statusViewColor = R.color.transaction_positive;
                break;
            default:
                statusViewColor = -1;
                break;
        }
        if (statusViewColor == -1) {
            return statusViewColor;
        }
        return ContextCompat.getColor(this.mContext, statusViewColor);
    }

    ActionType getActionButtonType(Data transaction) {
        ActionType actionType = ActionType.NONE;
        if (transaction == null) {
            return actionType;
        }
        TransactionType type = TransactionType.toType(transaction.getType());
        TransactionStatus status = TransactionStatus.toStatus(transaction.getStatus());
        switch (type) {
            case SEND:
                if (status == TransactionStatus.WAITING_FOR_CLEARING) {
                    actionType = ActionType.CANCEL;
                    break;
                }
                break;
            case REQUEST:
                if (status == TransactionStatus.PENDING) {
                    actionType = ActionType.CANCEL;
                    break;
                }
                break;
        }
        return actionType;
    }
}
