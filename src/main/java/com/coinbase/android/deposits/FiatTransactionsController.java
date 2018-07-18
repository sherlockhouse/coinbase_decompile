package com.coinbase.android.deposits;

import android.app.ProgressDialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import com.coinbase.ApiConstants;
import com.coinbase.CallbackWithRetrofit;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.buysell.BuySellMadeConnector;
import com.coinbase.android.databinding.FragmentDepositBinding;
import com.coinbase.android.databinding.ListItemQuickstartBinding;
import com.coinbase.android.databinding.SpinnerDropdownDepositBinding;
import com.coinbase.android.databinding.SpinnerOptionDepositBinding;
import com.coinbase.android.db.AccountORM;
import com.coinbase.android.db.DatabaseManager;
import com.coinbase.android.event.ClassConsumableEvent;
import com.coinbase.android.paymentmethods.BankAccountsUpdatedConnector;
import com.coinbase.android.paymentmethods.GetPaymentMethodsTaskRx;
import com.coinbase.android.paymentmethods.PaymentMethodsUpdatedConnector;
import com.coinbase.android.phone.PhoneNumbersUpdatedConnector;
import com.coinbase.android.phone.VerifyPhoneHandler.VerifyPhoneCaller;
import com.coinbase.android.quickstart.QuickstartItem;
import com.coinbase.android.quickstart.QuickstartManager;
import com.coinbase.android.quickstart.QuickstartModule;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.android.ui.MainScheduler;
import com.coinbase.android.utils.AccountUtils;
import com.coinbase.android.utils.MoneyFormatterUtil;
import com.coinbase.android.utils.MoneyFormatterUtil.Options;
import com.coinbase.android.utils.PaymentMethodUtils;
import com.coinbase.android.utils.Utils;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.android.wbl.WithdrawalBasedLimitsApiErrorHandler;
import com.coinbase.api.LoginManager;
import com.coinbase.v2.models.account.Data;
import com.coinbase.v2.models.paymentMethods.PaymentMethods;
import com.coinbase.v2.models.transfers.Transfer;
import com.google.gson.Gson;
import java.math.BigDecimal;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import javax.inject.Inject;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.joda.time.DateTime;
import org.joda.time.Days;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Scheduler;
import rx.subscriptions.CompositeSubscription;

@ControllerScope
public class FiatTransactionsController extends ActionBarController implements VerifyPhoneCaller {
    public static final String ACCOUNT = "DepositActivity_Account";
    public static final String TYPE = "DepositActivity_Type";
    @Inject
    protected DatabaseManager dbManager;
    private boolean isInForeground;
    private Data mAccount;
    @Inject
    AppCompatActivity mAppCompatActivity;
    @Inject
    BankAccountsUpdatedConnector mBankAccountsUpdatedConnector;
    private FragmentDepositBinding mBinding;
    @Inject
    BuySellMadeConnector mBuySellMadeConnector;
    @Inject
    FiatTransactionsConnector mFiatTransactionsConnector;
    @Inject
    LoginManager mLoginManager;
    @MainScheduler
    @Inject
    Scheduler mMainScheduler;
    @Inject
    MixpanelTracking mMixpanelTracking;
    @Inject
    MoneyFormatterUtil mMoneyFormatterUtil;
    private ArrayAdapter<com.coinbase.v2.models.paymentMethods.Data> mPaymentMethodsAdapter;
    @Inject
    GetPaymentMethodsTaskRx mPaymentMethodsTask;
    @Inject
    PaymentMethodsUpdatedConnector mPaymentMethodsUpdateConnector;
    @Inject
    PhoneNumbersUpdatedConnector mPhoneNumbersUpdatedConnector;
    private final CompositeSubscription mQuickStartSubscription = new CompositeSubscription();
    @Inject
    QuickstartManager mQuickstartManager;
    private com.coinbase.v2.models.paymentMethods.Data mSelectedPaymentMethod;
    private final CompositeSubscription mSubscription = new CompositeSubscription();
    private Type mType;
    @Inject
    WithdrawalBasedLimitsApiErrorHandler mWithdrawalBasedLimitsApiErrorHandler;

    public static class DepositConfirmationDialogFragment extends DepositWithdrawConfirmationDialogFragment {
        @Inject
        LoginManager mLoginManager;

        public String getDialogTitle() {
            return getString(R.string.confirm_deposit);
        }

        public int getFirstImage() {
            return R.drawable.buysell_bank;
        }

        public int getSecondImage() {
            return R.drawable.buysell_wallet;
        }

        public String getAmountMessage() {
            return String.format(getString(R.string.deposit_bank), new Object[]{getFormattedTotal(this.mTransfer), this.mPaymentMethod.getName()});
        }

        public String getPaymentMessage() {
            return String.format(getString(R.string.deposit_wallet), new Object[]{getFormattedTotal(this.mTransfer), this.mAccount.getName()});
        }

        public void onUserConfirm() {
        }

        public String getPayoutMessage() {
            DateTime payout = Utils.getDateTimeFrom(this.mTransfer.getData().getPayoutAt());
            String date = payout.toString("MMMM dd, yyyy");
            int days = Days.daysBetween(DateTime.now(), payout).getDays();
            return String.format(getString(R.string.buysell_sell_payout), new Object[]{String.valueOf(Math.abs(days)), date});
        }

        public boolean showPayoutDate() {
            return true;
        }
    }

    public enum Type {
        DEPOSIT,
        WITHDRAW
    }

    public static class WithdrawConfirmationDialogFragment extends DepositWithdrawConfirmationDialogFragment {
        @Inject
        LoginManager mLoginManager;

        public String getDialogTitle() {
            return getString(R.string.confirm_withdraw);
        }

        public int getFirstImage() {
            return R.drawable.buysell_bank;
        }

        public int getSecondImage() {
            return R.drawable.buysell_wallet;
        }

        public String getAmountMessage() {
            return String.format(getString(R.string.withdraw_bank), new Object[]{getFormattedTotal(this.mTransfer), this.mPaymentMethod.getName()});
        }

        public String getPaymentMessage() {
            return String.format(getString(R.string.withdraw_wallet), new Object[]{getFormattedTotal(this.mTransfer), this.mAccount.getName()});
        }

        public void onUserConfirm() {
        }

        public String getPayoutMessage() {
            DateTime payout = Utils.getDateTimeFrom(this.mTransfer.getData().getPayoutAt());
            String date = payout.toString("MMMM dd, yyyy");
            int days = Days.daysBetween(DateTime.now(), payout).getDays();
            return String.format(getString(R.string.buysell_sell_payout), new Object[]{String.valueOf(Math.abs(days)), date});
        }

        public boolean showPayoutDate() {
            return true;
        }
    }

    public FiatTransactionsController(Bundle args) {
        super(args);
    }

    protected View onCreateView(LayoutInflater inflater, ViewGroup container) {
        ((MainActivitySubcomponentProvider) getActivity()).mainActivitySubcomponent().fiatTransactionsControllerSubcomponent(new QuickstartModule(getContext(), this), new FiatTransactionsControllerModule(this)).inject(this);
        setHasOptionsMenu(true);
        this.mAccount = (Data) new Gson().fromJson(getArgs().getString(ACCOUNT), Data.class);
        this.mType = (Type) getArgs().getSerializable(TYPE);
        this.mSubscription.add(this.mBuySellMadeConnector.get().observeOn(this.mMainScheduler).subscribe(FiatTransactionsController$$Lambda$1.lambdaFactory$(this)));
        this.mSubscription.add(this.mBankAccountsUpdatedConnector.get().filter(FiatTransactionsController$$Lambda$2.lambdaFactory$()).observeOn(this.mMainScheduler).subscribe(FiatTransactionsController$$Lambda$3.lambdaFactory$(this)));
        this.mSubscription.add(this.mPaymentMethodsUpdateConnector.get().observeOn(this.mMainScheduler).filter(FiatTransactionsController$$Lambda$4.lambdaFactory$()).subscribe(FiatTransactionsController$$Lambda$5.lambdaFactory$(this)));
        this.mSubscription.add(this.mPhoneNumbersUpdatedConnector.get().observeOn(this.mMainScheduler).subscribe(FiatTransactionsController$$Lambda$6.lambdaFactory$(this)));
        this.mSubscription.add(this.mFiatTransactionsConnector.get().observeOn(this.mMainScheduler).subscribe(FiatTransactionsController$$Lambda$7.lambdaFactory$(this)));
        this.mBinding = (FragmentDepositBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_deposit, container, false);
        onAttachToolbar(this.mBinding.cvCoinbaseToolbar);
        this.mPaymentMethodsAdapter = new ArrayAdapter<com.coinbase.v2.models.paymentMethods.Data>(getActivity(), R.layout.fragment_transfer_type) {
            public View getView(int position, View convertView, ViewGroup parent) {
                SpinnerOptionDepositBinding binding;
                if (convertView == null) {
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.spinner_option_deposit, parent, false);
                    binding = (SpinnerOptionDepositBinding) DataBindingUtil.bind(convertView);
                    convertView.setTag(binding);
                } else {
                    binding = (SpinnerOptionDepositBinding) convertView.getTag();
                }
                com.coinbase.v2.models.paymentMethods.Data method = (com.coinbase.v2.models.paymentMethods.Data) getItem(position);
                String balance = "";
                if (method.getFiatAccount() != null) {
                    Data account = AccountORM.find(FiatTransactionsController.this.dbManager.openDatabase(), method.getFiatAccount().getId());
                    if (!(account == null || account.getBalance() == null)) {
                        balance = " - " + FiatTransactionsController.this.mMoneyFormatterUtil.formatMoney(AccountUtils.getAccountBalanceMoney(account, FiatTransactionsController.this.mMoneyFormatterUtil), EnumSet.of(Options.ROUND_4_DIGITS));
                    }
                }
                binding.tvText.setText(method.getName() + balance);
                return convertView;
            }

            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                SpinnerDropdownDepositBinding binding;
                if (convertView == null) {
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.spinner_dropdown_deposit, parent, false);
                    binding = (SpinnerDropdownDepositBinding) DataBindingUtil.bind(convertView);
                    convertView.setTag(binding);
                } else {
                    binding = (SpinnerDropdownDepositBinding) convertView.getTag();
                }
                com.coinbase.v2.models.paymentMethods.Data method = (com.coinbase.v2.models.paymentMethods.Data) getItem(position);
                String balance = "";
                if (method.getFiatAccount() != null) {
                    Data account = AccountORM.find(FiatTransactionsController.this.dbManager.openDatabase(), method.getFiatAccount().getId());
                    if (!(account == null || account.getBalance() == null)) {
                        balance = " - " + FiatTransactionsController.this.mMoneyFormatterUtil.formatMoney(AccountUtils.getAccountBalanceMoney(account, FiatTransactionsController.this.mMoneyFormatterUtil), EnumSet.of(Options.ROUND_4_DIGITS));
                    }
                }
                binding.tvText.setText(method.getName() + balance);
                return convertView;
            }
        };
        this.mBinding.spinnerBank.setAdapter(this.mPaymentMethodsAdapter);
        this.mBinding.spinnerBank.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                FiatTransactionsController.this.mSelectedPaymentMethod = (com.coinbase.v2.models.paymentMethods.Data) FiatTransactionsController.this.mPaymentMethodsAdapter.getItem(position);
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        this.mBinding.btnSubmit.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                FiatTransactionsController.this.initiateDeposit();
            }
        });
        this.mBinding.tvBankLabel.setText(this.mType == Type.DEPOSIT ? R.string.label_pay_in : R.string.label_pay_out);
        this.mBinding.btnSubmit.setText(this.mType == Type.DEPOSIT ? getApplicationContext().getString(R.string.deposit) : getApplicationContext().getString(R.string.withdraw));
        return this.mBinding.getRoot();
    }

    static /* synthetic */ Boolean lambda$onCreateView$1(ClassConsumableEvent v) {
        return Boolean.valueOf(!v.consumeIfNotConsumed(FiatTransactionsController.class));
    }

    static /* synthetic */ Boolean lambda$onCreateView$3(ClassConsumableEvent v) {
        return Boolean.valueOf(!v.consumeIfNotConsumed(FiatTransactionsController.class));
    }

    public void onDestroy() {
        super.onDestroy();
        this.mSubscription.clear();
        this.mQuickStartSubscription.clear();
    }

    protected void onAttach(View view) {
        super.onAttach(view);
        if (this.mType == Type.DEPOSIT) {
            this.mBinding.quickstartContent.tvOverlayDetails.setText(String.format(getApplicationContext().getString(R.string.quickstart_deposit), new Object[]{this.mAccount.getName()}));
        } else if (this.mType == Type.WITHDRAW) {
            this.mBinding.quickstartContent.tvOverlayDetails.setText(String.format(getApplicationContext().getString(R.string.quickstart_withdraw), new Object[]{this.mAccount.getName()}));
        }
        this.mBinding.quickstartContent.ivOverlayIcon.setImageResource(getOverlayIcon());
        this.mBinding.quickstartContent.lvOverlayQuickstart.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                QuickstartItem item = (QuickstartItem) parent.getItemAtPosition(position);
                MixpanelTracking.logTransactionEvent(item);
                FiatTransactionsController.this.mQuickstartManager.performActionForItem(item, FiatTransactionsController.this.getActivity(), FiatTransactionsController.this);
            }
        });
        refreshData();
        this.isInForeground = true;
    }

    protected void onDetach(View view) {
        super.onDetach(view);
        this.isInForeground = false;
    }

    private int getOverlayIcon() {
        return R.drawable.bank_large;
    }

    protected SpannableStringBuilder getTitle() {
        String title = this.mAccount == null ? "?" : this.mAccount.getName();
        if (this.mType == Type.DEPOSIT) {
            title = getApplicationContext().getString(R.string.deposit_to) + title;
        } else {
            title = getApplicationContext().getString(R.string.withdraw_from) + title;
        }
        return new SpannableStringBuilder(title);
    }

    private void updateSetupViews() {
        List<QuickstartItem> items = this.mQuickstartManager.getCachedQuickstartItems();
        if (items.isEmpty()) {
            this.mBinding.quickstartContent.rlQuickStart.setVisibility(4);
            return;
        }
        this.mBinding.quickstartContent.rlQuickStart.setVisibility(0);
        this.mBinding.quickstartContent.llOverlay.setVisibility(0);
        this.mBinding.quickstartContent.llUnavailable.setVisibility(4);
        this.mBinding.quickstartContent.lvOverlayQuickstart.setAdapter(new ArrayAdapter<QuickstartItem>(getActivity(), R.layout.list_item_quickstart, items) {
            public View getView(int position, View convertView, ViewGroup parent) {
                ListItemQuickstartBinding binding = QuickstartManager.prepareItemView(FiatTransactionsController.this.getActivity(), (QuickstartItem) getItem(position));
                binding.ibtnQuickstartItemDismiss.setVisibility(4);
                return binding.getRoot();
            }
        });
        Utils.hideKeyboard(getActivity());
    }

    private void initiateDeposit() {
        Money amountEntered = getEnteredAmount();
        if (amountEntered.isNegativeOrZero()) {
            Utils.showMessage(getActivity(), (int) R.string.please_enter_an_amount, 0);
        } else if (this.mSelectedPaymentMethod == null) {
            Utils.showMessage(getActivity(), (int) R.string.please_add_a_bank_account_to_deposit_withdraw, 0);
        } else if (this.mAccount == null) {
            Utils.showMessage(getActivity(), (int) R.string.account_details_missing, 0);
        } else if (this.mType == Type.DEPOSIT) {
            deposit(amountEntered);
        } else {
            withdraw(amountEntered);
        }
    }

    private void deposit(Money amount) {
        final ProgressDialog progressDialog = ProgressDialog.show(getActivity(), "", getApplicationContext().getString(R.string.deposit_progress));
        HashMap<String, Object> params = new HashMap();
        params.put("amount", amount.getAmount().toPlainString());
        params.put("currency", amount.getCurrencyUnit().toString());
        params.put("payment_method", this.mSelectedPaymentMethod.getId());
        params.put("commit", Boolean.valueOf(false));
        this.mLoginManager.getClient().depositFunds(this.mAccount.getId(), params, new CallbackWithRetrofit<Transfer>() {
            public void onResponse(Call<Transfer> call, Response<Transfer> response, Retrofit retrofit) {
                Utils.dismissDialog(progressDialog);
                if (response.isSuccessful()) {
                    DepositWithdrawConfirmationDialogFragment f = new DepositConfirmationDialogFragment();
                    Bundle args = new Bundle();
                    Gson gson = new Gson();
                    args.putString(DepositWithdrawConfirmationDialogFragment.TRANSFER, gson.toJson(response.body()));
                    args.putString(DepositWithdrawConfirmationDialogFragment.ACCOUNT, gson.toJson(FiatTransactionsController.this.mAccount));
                    args.putString(DepositWithdrawConfirmationDialogFragment.PAYMENT_METHOD, gson.toJson(FiatTransactionsController.this.mSelectedPaymentMethod));
                    f.setArguments(args);
                    f.show(FiatTransactionsController.this.mAppCompatActivity.getSupportFragmentManager(), "confirm");
                    return;
                }
                Utils.showRetrofitErrorMessage(response, retrofit, FiatTransactionsController.this.getActivity());
            }

            public void onFailure(Call<Transfer> call, Throwable t) {
                Utils.dismissDialog(progressDialog);
                Utils.showMessage(FiatTransactionsController.this.getActivity(), t, 1);
            }
        });
    }

    private void withdraw(Money amount) {
        final ProgressDialog progressDialog = ProgressDialog.show(getActivity(), "", getApplicationContext().getString(R.string.withdrawal_progress));
        HashMap<String, Object> params = new HashMap();
        params.put("amount", amount.getAmount().toPlainString());
        params.put("currency", amount.getCurrencyUnit().toString());
        params.put("payment_method", this.mSelectedPaymentMethod.getId());
        params.put("commit", Boolean.valueOf(false));
        this.mLoginManager.getClient().withdrawFunds(this.mAccount.getId(), params, new CallbackWithRetrofit<Transfer>() {
            public void onResponse(Call<Transfer> call, Response<Transfer> response, Retrofit retrofit) {
                Utils.dismissDialog(progressDialog);
                if (response.isSuccessful()) {
                    DepositWithdrawConfirmationDialogFragment f = new WithdrawConfirmationDialogFragment();
                    Bundle args = new Bundle();
                    Gson gson = new Gson();
                    args.putString(DepositWithdrawConfirmationDialogFragment.TRANSFER, gson.toJson(response.body()));
                    args.putString(DepositWithdrawConfirmationDialogFragment.ACCOUNT, gson.toJson(FiatTransactionsController.this.mAccount));
                    args.putString(DepositWithdrawConfirmationDialogFragment.PAYMENT_METHOD, gson.toJson(FiatTransactionsController.this.mSelectedPaymentMethod));
                    f.setArguments(args);
                    f.show(FiatTransactionsController.this.mAppCompatActivity.getSupportFragmentManager(), "confirm");
                    return;
                }
                String errorBody = Utils.getErrorBody(response.errorBody());
                if (FiatTransactionsController.this.mWithdrawalBasedLimitsApiErrorHandler.handleApiError(errorBody, ApiConstants.WITHDRAWALS)) {
                    MixpanelTracking mixpanelTracking = FiatTransactionsController.this.mMixpanelTracking;
                    String str = MixpanelTracking.EVENT_WITHDRAW_VIEWED_LIMIT_EXCEEDED_ERROR;
                    String[] strArr = new String[2];
                    strArr[0] = "currency";
                    strArr[1] = FiatTransactionsController.this.mAccount == null ? "" : FiatTransactionsController.this.mAccount.getCurrency().getCode();
                    mixpanelTracking.trackEvent(str, strArr);
                    return;
                }
                Utils.showMessage(FiatTransactionsController.this.getApplicationContext(), Utils.getErrorMessage(errorBody), 1);
            }

            public void onFailure(Call<Transfer> call, Throwable t) {
                Utils.dismissDialog(progressDialog);
                Utils.showMessage(FiatTransactionsController.this.getActivity(), t, 1);
            }
        });
    }

    private void commitDeposit(Transfer transfer) {
        final ProgressDialog progressDialog = ProgressDialog.show(getActivity(), null, getApplicationContext().getString(R.string.deposit_progress));
        this.mLoginManager.getClient().commitDeposit(this.mAccount.getId(), transfer.getData().getId(), new CallbackWithRetrofit<Transfer>() {
            public void onResponse(Call<Transfer> call, Response<Transfer> response, Retrofit retrofit) {
                Utils.dismissDialog(progressDialog);
                if (response.isSuccessful()) {
                    FiatTransactionsController.this.getRouter().popCurrentController();
                    String successFormat = FiatTransactionsController.this.getApplicationContext().getString(R.string.deposit_success);
                    Utils.showMessage(FiatTransactionsController.this.getActivity(), String.format(successFormat, new Object[]{((Transfer) response.body()).getData().getAmount().getAmount()}), 1);
                    return;
                }
                Utils.showRetrofitErrorMessage(response, retrofit, FiatTransactionsController.this.getActivity());
            }

            public void onFailure(Call<Transfer> call, Throwable t) {
                Utils.dismissDialog(progressDialog);
                Utils.showMessage(FiatTransactionsController.this.getActivity(), t, 1);
            }
        });
    }

    private void commitWithdraw(Transfer transfer) {
        final ProgressDialog progressDialog = ProgressDialog.show(getActivity(), null, getApplicationContext().getString(R.string.withdrawal_progress));
        this.mLoginManager.getClient().commitWithdraw(this.mAccount.getId(), transfer.getData().getId(), new CallbackWithRetrofit<Transfer>() {
            public void onResponse(Call<Transfer> call, Response<Transfer> response, Retrofit retrofit) {
                Utils.dismissDialog(progressDialog);
                if (response.isSuccessful()) {
                    FiatTransactionsController.this.getRouter().popCurrentController();
                    String successFormat = FiatTransactionsController.this.getApplicationContext().getString(R.string.withdrawal_success);
                    Utils.showMessage(FiatTransactionsController.this.getActivity(), String.format(successFormat, new Object[]{((Transfer) response.body()).getData().getAmount().getAmount()}), 1);
                    return;
                }
                Utils.showRetrofitErrorMessage(response, retrofit, FiatTransactionsController.this.getActivity());
            }

            public void onFailure(Call<Transfer> call, Throwable t) {
                Utils.dismissDialog(progressDialog);
                Utils.showMessage(FiatTransactionsController.this.getActivity(), t, 1);
            }
        });
    }

    private void onDepositWithdrawConfirmed(Transfer transfer) {
        if (this.mType == Type.DEPOSIT) {
            commitDeposit(transfer);
        } else {
            commitWithdraw(transfer);
        }
    }

    private Money getEnteredAmount() {
        String text = String.valueOf(this.mBinding.etAmount.getText());
        BigDecimal value = BigDecimal.ZERO;
        if (!TextUtils.isEmpty(text)) {
            try {
                value = BigDecimal.valueOf(Double.parseDouble(text));
            } catch (NumberFormatException e) {
            }
        }
        CurrencyUnit accountCurrencyUnit = CurrencyUnit.getInstance(this.mAccount.getCurrency().getCode());
        if (value.scale() > accountCurrencyUnit.getDecimalPlaces()) {
            value = value.setScale(accountCurrencyUnit.getDecimalPlaces(), 5);
        }
        return Money.of(accountCurrencyUnit, value);
    }

    private void refreshData() {
        String type = this.mType == Type.DEPOSIT ? com.coinbase.android.quickstart.QuickstartManager.Type.DEPOSITS.toString() : com.coinbase.android.quickstart.QuickstartManager.Type.WITHDRAWALS.toString();
        this.mQuickStartSubscription.clear();
        this.mQuickStartSubscription.add(this.mQuickstartManager.updateQuickstartItems(type.toString()).onBackpressureLatest().observeOn(this.mMainScheduler).subscribe(FiatTransactionsController$$Lambda$8.lambdaFactory$(this), FiatTransactionsController$$Lambda$9.lambdaFactory$(this)));
        this.mSubscription.add(this.mPaymentMethodsTask.getPaymentMethodsExcludeLimits().first().observeOn(this.mMainScheduler).subscribe(FiatTransactionsController$$Lambda$10.lambdaFactory$(this), FiatTransactionsController$$Lambda$11.lambdaFactory$()));
    }

    static /* synthetic */ void lambda$refreshData$9(FiatTransactionsController this_, Pair pair) {
        if (pair.first.isSuccessful()) {
            List<com.coinbase.v2.models.paymentMethods.Data> paymentMethods = ((PaymentMethods) ((Response) pair.first).body()).getData();
            this_.mPaymentMethodsAdapter.clear();
            for (com.coinbase.v2.models.paymentMethods.Data method : paymentMethods) {
                if ((this_.mType == Type.DEPOSIT && PaymentMethodUtils.isValidDeposit(method)) || (this_.mType == Type.WITHDRAW && PaymentMethodUtils.isValidWithdraw(method))) {
                    this_.mPaymentMethodsAdapter.add(method);
                }
            }
            this_.mPaymentMethodsAdapter.notifyDataSetChanged();
            if (this_.mPaymentMethodsAdapter.getCount() > 0) {
                this_.mSelectedPaymentMethod = (com.coinbase.v2.models.paymentMethods.Data) this_.mPaymentMethodsAdapter.getItem(0);
            }
        }
    }

    static /* synthetic */ void lambda$refreshData$10(Throwable t) {
    }

    private void onBuySellMade(Data account) {
        getActivity().finish();
    }

    private void onPhoneNumbersUpdated(Object event) {
        refreshData();
        Utils.processPhoneNumbersUpdatedEvent(event, getContext());
    }

    private void onBanksUpdated() {
        refreshData();
    }

    private void onPaymentMethodsUpdated() {
        refreshData();
    }

    public FragmentManager getCallerFragmentManager() {
        return this.mAppCompatActivity.getSupportFragmentManager();
    }

    public ActionBarController getCallingController() {
        return this;
    }

    public Context getContext() {
        return getActivity();
    }

    public boolean isForeground() {
        return this.isInForeground;
    }
}
