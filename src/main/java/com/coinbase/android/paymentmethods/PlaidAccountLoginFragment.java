package com.coinbase.android.paymentmethods;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import com.coinbase.CallbackWithRetrofit;
import com.coinbase.android.ActivityScope;
import com.coinbase.android.ComponentProvider;
import com.coinbase.android.Constants;
import com.coinbase.android.Log;
import com.coinbase.android.MainActivity;
import com.coinbase.android.R;
import com.coinbase.android.databinding.FragmentPlaidAccountLoginBinding;
import com.coinbase.android.databinding.ListItemMfaBinding;
import com.coinbase.android.databinding.ListItemPlaidMfaBinding;
import com.coinbase.android.databinding.ListItemSelectionBinding;
import com.coinbase.android.dialog.ConfirmationDialogFragment;
import com.coinbase.android.event.ClassConsumableEvent;
import com.coinbase.android.ui.MainScheduler;
import com.coinbase.android.utils.Utils;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.LoginManager;
import com.coinbase.api.internal.ApiConstants;
import com.coinbase.api.internal.ModelGsonAdapterFactory;
import com.coinbase.api.internal.models.achSetupSession.AchSetupSession;
import com.coinbase.api.internal.models.achSetupSession.mfa.Mfa;
import com.coinbase.api.internal.models.achSetupSession.mfa.Mfa.Type;
import com.coinbase.api.internal.models.institutions.Data;
import com.coinbase.v2.models.paymentMethods.PaymentMethod;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.inject.Inject;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Scheduler;
import rx.subscriptions.CompositeSubscription;

@ActivityScope
public class PlaidAccountLoginFragment extends Fragment implements OnClickListener {
    @Inject
    BankAccountsUpdatedConnector mBankAccountsUpdatedConnector;
    FragmentPlaidAccountLoginBinding mBinding;
    private Data mInstitution;
    @Inject
    LoginManager mLoginManager;
    @MainScheduler
    @Inject
    Scheduler mMainScheduler;
    private boolean mParentSuccessRouter;
    private String mPassword;
    private com.coinbase.v2.models.paymentMethods.Data mPaymentMethod;
    @Inject
    PaymentMethodsUpdatedConnector mPaymentMethodsUpdatedConnector;
    private AchSetupSession mSession;
    private final CompositeSubscription mSubscription = new CompositeSubscription();
    private String mUsername;

    public static class ConfirmPlaidCancelDialogFragment extends ConfirmationDialogFragment {
        private PlaidAccountLoginFragment mPlaidAccountLoginFragment;

        void setPlaidAccountLoginFragment(PlaidAccountLoginFragment fragment) {
            this.mPlaidAccountLoginFragment = fragment;
        }

        public String getMessage() {
            return getString(R.string.abandon_add_bank_message);
        }

        public void onUserConfirm() {
        }

        protected int getPositiveButtonText() {
            return R.string.no;
        }

        protected int getNegativeButtonText() {
            return R.string.yes;
        }

        public void onUserCancel() {
            super.onUserCancel();
            if (this.mPlaidAccountLoginFragment != null) {
                this.mPlaidAccountLoginFragment.onUserCancel();
            }
        }
    }

    public static PlaidAccountLoginFragment newInstance(Data institution, boolean parentSuccessRouter) {
        PlaidAccountLoginFragment f = new PlaidAccountLoginFragment();
        Bundle args = new Bundle();
        args.putString(PlaidAccountLoginActivity.INSTITUTION, new GsonBuilder().registerTypeAdapterFactory(ModelGsonAdapterFactory.create()).create().toJson((Object) institution));
        args.putBoolean(Constants.PARENT_SUCCESS_ROUTER, parentSuccessRouter);
        f.setArguments(args);
        return f;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((ComponentProvider) getActivity().getApplicationContext()).applicationComponent().fragmentSubcomponent().inject(this);
        Bundle args = getArguments();
        Gson gson = new GsonBuilder().registerTypeAdapterFactory(ModelGsonAdapterFactory.create()).create();
        this.mParentSuccessRouter = args.getBoolean(Constants.PARENT_SUCCESS_ROUTER, false);
        this.mInstitution = (Data) gson.fromJson(args.getString(PlaidAccountLoginActivity.INSTITUTION), Data.class);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mBinding = (FragmentPlaidAccountLoginBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_plaid_account_login, container, false);
        return this.mBinding.getRoot();
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (this.mInstitution == null) {
            this.mBinding.plaidAccountLoginContent.tvPlaidLoginManual.setVisibility(8);
        } else if (this.mInstitution.getImage() == null || this.mInstitution.getImage().getPng() == null) {
            showBankInformation(false);
        } else {
            Picasso.with(getContext()).load(this.mInstitution.getImage().getPng()).into(this.mBinding.plaidAccountLoginContent.ivPlaidLoginLogo, new Callback() {
                public void onSuccess() {
                    PlaidAccountLoginFragment.this.showBankInformation(true);
                }

                public void onError() {
                    PlaidAccountLoginFragment.this.showBankInformation(false);
                }
            });
        }
        this.mBinding.plaidAccountLoginContent.btnPlaidLoginSubmit.setOnClickListener(this);
        this.mBinding.plaidAccountLoginContent.btnPlaidPinSubmit.setOnClickListener(this);
        this.mBinding.plaidAccountLoginContent.btnPlaidSendMfaSubmit.setOnClickListener(this);
        this.mBinding.plaidAccountLoginContent.btnPlaidSubmitMfaSubmit.setOnClickListener(this);
        this.mBinding.plaidAccountLoginContent.btnPlaidAccountChooserSubmit.setOnClickListener(this);
        this.mBinding.plaidAccountLoginContent.plaidLoginAllDone.btnPlaidAllDoneSubmit.setOnClickListener(this);
        this.mBinding.plaidAccountLoginContent.tvPlaidLoginManual.setOnClickListener(this);
        this.mBinding.plaidAccountLoginContent.tvPlaidLoginProtectionDetails.setOnClickListener(this);
        this.mBinding.plaidAccountLoginContent.tvPlaidLoginProtectionDetails.setText(Html.fromHtml(getString(R.string.how_is_my_information_protected)));
        this.mBinding.plaidAccountLoginContent.tvPlaidLoginProtectionDetails.setMovementMethod(LinkMovementMethod.getInstance());
        Utils.stripUnderlines(this.mBinding.plaidAccountLoginContent.tvPlaidLoginProtectionDetails);
        if (this.mParentSuccessRouter) {
            this.mBinding.plaidAccountLoginContent.plaidLoginAllDone.btnPlaidAllDoneSubmit.setText(getString(R.string.continue_button));
        } else {
            this.mBinding.plaidAccountLoginContent.plaidLoginAllDone.btnPlaidAllDoneSubmit.setText(getString(R.string.btn_invest_now));
        }
        MixpanelTracking.getInstance().trackEvent(MixpanelTracking.EVENT_P_CREDENTIALS_SCREEN, new String[0]);
    }

    private void showBankInformation(boolean imageLoaded) {
        int i = 8;
        this.mBinding.plaidAccountLoginContent.ivPlaidLoginLogo.setVisibility(imageLoaded ? 0 : 8);
        TextView textView = this.mBinding.plaidAccountLoginContent.tvPlainLoginBankName;
        if (!imageLoaded) {
            i = 0;
        }
        textView.setVisibility(i);
        if (!imageLoaded) {
            this.mBinding.plaidAccountLoginContent.tvPlainLoginBankName.setText(this.mInstitution.getName());
        }
    }

    private void populateAccountSpinner() {
        List<com.coinbase.v2.models.account.Data> accounts = this.mSession.getData().getAccounts();
        if (accounts != null && accounts.size() != 0) {
            this.mBinding.plaidAccountLoginContent.spinnerPlaidChooseAccount.setAdapter(new AccountsAdapter(getActivity(), R.layout.spinner_option_plaid, accounts));
        } else if (getActivity() != null) {
            Utils.showMessage(getActivity(), (int) R.string.no_accounts_found, 1);
        }
    }

    private void populateSendOptionsSpinner() {
        this.mBinding.plaidAccountLoginContent.spinnerPlaidMfa.setAdapter(new SendOptionsAdapter(getActivity(), R.layout.spinner_option_plaid, this.mSession.getData().getMfa().getData()));
    }

    private void populateSubmitMFAOptions() {
        this.mBinding.plaidAccountLoginContent.llPlaidMfaQuestionsContainer.removeAllViews();
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        Mfa mfa = this.mSession.getData().getMfa();
        switch (mfa.getType()) {
            case CODE:
                View view = inflater.inflate(R.layout.list_item_plaid_mfa, this.mBinding.plaidAccountLoginContent.llPlaidMfaQuestionsContainer, false);
                ListItemPlaidMfaBinding plaidMfaBinding = (ListItemPlaidMfaBinding) DataBindingUtil.bind(view);
                plaidMfaBinding.tvLabel.setVisibility(8);
                plaidMfaBinding.etInput.setHint(R.string.verification_code_hint);
                view.setTag(plaidMfaBinding);
                this.mBinding.plaidAccountLoginContent.llPlaidMfaQuestionsContainer.addView(view);
                return;
            case SELECTIONS:
                for (com.coinbase.api.internal.models.achSetupSession.mfa.Data data : mfa.getData()) {
                    View sView = inflater.inflate(R.layout.list_item_selection, this.mBinding.plaidAccountLoginContent.llPlaidMfaQuestionsContainer, false);
                    ListItemSelectionBinding selectionBinding = (ListItemSelectionBinding) DataBindingUtil.bind(sView);
                    selectionBinding.tvLabel.setText(data.getQuestion());
                    selectionBinding.spinnerInput.setAdapter(new ArrayAdapter(getActivity(), R.layout.spinner_option_plaid, data.getAnswers()));
                    sView.setTag(selectionBinding);
                    this.mBinding.plaidAccountLoginContent.llPlaidMfaQuestionsContainer.addView(sView, this.mBinding.plaidAccountLoginContent.llPlaidMfaQuestionsContainer.getChildCount());
                }
                return;
            case QUESTIONS:
                for (com.coinbase.api.internal.models.achSetupSession.mfa.Data data2 : mfa.getData()) {
                    View qView = inflater.inflate(R.layout.list_item_mfa, this.mBinding.plaidAccountLoginContent.llPlaidMfaQuestionsContainer, false);
                    ListItemMfaBinding mfaBinding = (ListItemMfaBinding) DataBindingUtil.bind(qView);
                    mfaBinding.tvLabel.setText(data2.getQuestion());
                    qView.setTag(mfaBinding);
                    this.mBinding.plaidAccountLoginContent.llPlaidMfaQuestionsContainer.addView(qView, this.mBinding.plaidAccountLoginContent.llPlaidMfaQuestionsContainer.getChildCount());
                }
                return;
            default:
                return;
        }
    }

    public void onStart() {
        super.onStart();
    }

    public void onStop() {
        super.onStop();
        this.mSubscription.clear();
    }

    public void onClick(View v) {
        if (v == this.mBinding.plaidAccountLoginContent.btnPlaidLoginSubmit) {
            this.mUsername = this.mBinding.plaidAccountLoginContent.etPlaidLoginUserName.getText().toString();
            this.mPassword = this.mBinding.plaidAccountLoginContent.etPlaidLoginPassword.getText().toString();
            if (TextUtils.isEmpty(this.mUsername) || TextUtils.isEmpty(this.mPassword)) {
                Utils.showMessage(getActivity(), (int) R.string.username_and_password_required, 1);
                return;
            } else if (this.mInstitution.getCredentials() == null || this.mInstitution.getCredentials().getPin() == null) {
                Utils.hideKeyboardFrom(getActivity(), this.mBinding.plaidAccountLoginContent.btnPlaidLoginSubmit);
                createAchSetupSession(this.mInstitution.getType(), this.mUsername, this.mPassword, null);
                return;
            } else {
                this.mBinding.plaidAccountLoginContent.llPLaidAccountLoginForm.setVisibility(8);
                this.mBinding.plaidAccountLoginContent.llPlaidAccountPinForm.setVisibility(0);
                MixpanelTracking.getInstance().trackEvent(MixpanelTracking.EVENT_P_PIN_SCREEN, new String[0]);
                return;
            }
        }
        if (v == this.mBinding.plaidAccountLoginContent.btnPlaidPinSubmit) {
            String mPin = this.mBinding.plaidAccountLoginContent.etPlaidLoginPin.getText().toString();
            if (TextUtils.isEmpty(mPin)) {
                Utils.showMessage(getActivity(), (int) R.string.pin_required, 1);
                return;
            }
            Utils.hideKeyboardFrom(getActivity(), this.mBinding.plaidAccountLoginContent.btnPlaidLoginSubmit);
            createAchSetupSession(this.mInstitution.getType(), this.mUsername, this.mPassword, mPin);
            return;
        }
        if (v == this.mBinding.plaidAccountLoginContent.btnPlaidSendMfaSubmit) {
            sendMFA(((com.coinbase.api.internal.models.achSetupSession.mfa.Data) this.mBinding.plaidAccountLoginContent.spinnerPlaidMfa.getAdapter().getItem(this.mBinding.plaidAccountLoginContent.spinnerPlaidMfa.getSelectedItemPosition())).getMask(), this.mSession.getData().getId());
            return;
        }
        if (v == this.mBinding.plaidAccountLoginContent.btnPlaidSubmitMfaSubmit) {
            String code = null;
            List<String> answers = null;
            String event = this.mInstitution == null ? MixpanelTracking.EVENT_M_IAV_MFA_REQUESTED : MixpanelTracking.EVENT_P_MFA_REQUESTED;
            int i;
            switch (this.mSession.getData().getMfa().getType()) {
                case CODE:
                    code = ((ListItemPlaidMfaBinding) this.mBinding.plaidAccountLoginContent.llPlaidMfaQuestionsContainer.getChildAt(0).getTag()).etInput.getText().toString();
                    MixpanelTracking.getInstance().trackEvent(event, "Property: MFA type:  code");
                    break;
                case SELECTIONS:
                    answers = new ArrayList();
                    for (i = 0; i < this.mBinding.plaidAccountLoginContent.llPlaidMfaQuestionsContainer.getChildCount(); i++) {
                        ListItemSelectionBinding selectionBinding = (ListItemSelectionBinding) this.mBinding.plaidAccountLoginContent.llPlaidMfaQuestionsContainer.getChildAt(i).getTag();
                        answers.add((String) selectionBinding.spinnerInput.getAdapter().getItem(selectionBinding.spinnerInput.getSelectedItemPosition()));
                    }
                    MixpanelTracking.getInstance().trackEvent(event, "Property: MFA type:  selections");
                    break;
                case QUESTIONS:
                    answers = new ArrayList();
                    for (i = 0; i < this.mBinding.plaidAccountLoginContent.llPlaidMfaQuestionsContainer.getChildCount(); i++) {
                        answers.add(((ListItemMfaBinding) this.mBinding.plaidAccountLoginContent.llPlaidMfaQuestionsContainer.getChildAt(i).getTag()).etInput.getText().toString());
                    }
                    MixpanelTracking.getInstance().trackEvent(event, "Property: MFA type:  questions");
                    break;
            }
            submitMFA(this.mSession.getData().getId(), code, answers);
            return;
        }
        if (v == this.mBinding.plaidAccountLoginContent.btnPlaidAccountChooserSubmit) {
            SpinnerAdapter adapter = this.mBinding.plaidAccountLoginContent.spinnerPlaidChooseAccount.getAdapter();
            if (adapter == null) {
                populateAccountSpinner();
                return;
            }
            String accountId = ((com.coinbase.v2.models.account.Data) adapter.getItem(this.mBinding.plaidAccountLoginContent.spinnerPlaidChooseAccount.getSelectedItemPosition())).getId();
            Utils.hideKeyboardFrom(getActivity(), this.mBinding.plaidAccountLoginContent.btnPlaidLoginSubmit);
            createBankAccount(accountId);
            return;
        }
        if (v == this.mBinding.plaidAccountLoginContent.plaidLoginAllDone.btnPlaidAllDoneSubmit) {
            this.mBinding.plaidAccountLoginContent.plaidLoginAllDone.llPlaidAllDone.setVisibility(8);
            if (this.mParentSuccessRouter) {
                getActivity().setResult(Constants.ACTIVITY_RESULT_PARENT_SUCCESS_ROUTER);
            } else {
                routeToMainActivity();
            }
            getActivity().finish();
            MixpanelTracking.getInstance().trackEvent(MixpanelTracking.EVENT_BANK_ACCOUNT_FIRST_PURCHASE_SCREEN_SHOWN, new String[0]);
            return;
        }
        if (v == this.mBinding.plaidAccountLoginContent.tvPlaidLoginManual) {
            ((PlaidAccountLoginActivity) getActivity()).showManualLogin();
            return;
        }
        if (v == this.mBinding.plaidAccountLoginContent.tvPlaidLoginProtectionDetails) {
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://support.coinbase.com/customer/portal/articles/957255-how-is-my-bank-account-information-protected-")));
        }
    }

    private void routeToMainActivity() {
        Intent intent = new Intent(getContext(), MainActivity.class);
        intent.setFlags(67108864);
        intent.setAction(MainActivity.ACTION_BUY);
        startActivity(intent);
    }

    public boolean onBackPressed() {
        if (this.mBinding.plaidAccountLoginContent.plaidLoginAllDone.llPlaidAllDone.getVisibility() == 0) {
            return false;
        }
        Activity activity = getActivity();
        ConfirmPlaidCancelDialogFragment fragment = new ConfirmPlaidCancelDialogFragment();
        fragment.setPlaidAccountLoginFragment(this);
        fragment.show(getChildFragmentManager(), "cancel");
        return true;
    }

    void onUserCancel() {
        if (this.mSession != null && this.mSession.getData().getId() != null) {
            deleteAchSetupSession();
        } else if (getActivity() != null) {
            getActivity().finish();
        }
    }

    private void deleteAchSetupSession() {
        final ProgressDialog progressDialog = ProgressDialog.show(getContext(), "", getString(R.string.deleting));
        this.mLoginManager.getClient().deleteAchSetupSession(this.mSession.getData().getId(), new CallbackWithRetrofit<Void>() {
            public void onResponse(Call<Void> call, Response<Void> response, Retrofit retrofit) {
                Utils.dismissDialog(progressDialog);
                if (response.isSuccessful()) {
                    Utils.showMessage(PlaidAccountLoginFragment.this.getContext(), (int) R.string.payment_method_deleted, 1);
                    PlaidAccountLoginFragment.this.mPaymentMethodsUpdatedConnector.get().onNext(new ClassConsumableEvent());
                    if (PlaidAccountLoginFragment.this.getActivity() != null) {
                        PlaidAccountLoginFragment.this.getActivity().finish();
                        return;
                    }
                    return;
                }
                Utils.showRetrofitErrorMessage(response, retrofit, PlaidAccountLoginFragment.this.getContext());
                if (PlaidAccountLoginFragment.this.getActivity() != null) {
                    PlaidAccountLoginFragment.this.getActivity().finish();
                }
            }

            public void onFailure(Call<Void> call, Throwable t) {
                Utils.showMessage(PlaidAccountLoginFragment.this.getContext(), t, 1);
                if (PlaidAccountLoginFragment.this.getActivity() != null) {
                    PlaidAccountLoginFragment.this.getActivity().finish();
                }
            }
        });
    }

    private void createAchSetupSession(String institution, String userName, String password, String pin) {
        ProgressDialog progressDialog = ProgressDialog.show(getContext(), "", getString(R.string.creating));
        HashMap<String, Object> params = new HashMap();
        params.put(ApiConstants.INSTITUTION, institution);
        params.put(ApiConstants.USERNAME, userName);
        params.put("password", password);
        if (pin != null) {
            params.put(ApiConstants.PIN, pin);
        }
        this.mSubscription.add(this.mLoginManager.getClient().createAchSetupSessionRx(params).observeOn(this.mMainScheduler).subscribe(PlaidAccountLoginFragment$$Lambda$1.lambdaFactory$(this, progressDialog), PlaidAccountLoginFragment$$Lambda$2.lambdaFactory$(this, progressDialog)));
    }

    static /* synthetic */ void lambda$createAchSetupSession$0(PlaidAccountLoginFragment this_, ProgressDialog progressDialog, Pair pair) {
        Response<AchSetupSession> response = pair.first;
        Retrofit retrofit = pair.second;
        Utils.dismissDialog(progressDialog);
        if (response.isSuccessful()) {
            this_.mSession = (AchSetupSession) response.body();
            this_.mBinding.plaidAccountLoginContent.llPLaidAccountLoginForm.setVisibility(8);
            this_.mBinding.plaidAccountLoginContent.llPlaidAccountPinForm.setVisibility(8);
            Mfa mfa = this_.mSession.getData().getMfa();
            if (mfa == null) {
                this_.mBinding.plaidAccountLoginContent.llPlaidChooseAccountForm.setVisibility(0);
                this_.populateAccountSpinner();
            } else if (mfa.getType() == Type.CODE) {
                this_.mBinding.plaidAccountLoginContent.llPlaidSendMfa.setVisibility(0);
                this_.populateSendOptionsSpinner();
            } else {
                this_.mBinding.plaidAccountLoginContent.tvPlaidSubmitMfaTitle.setText(R.string.security_question);
                this_.mBinding.plaidAccountLoginContent.llPlaidSubmitMfa.setVisibility(0);
                this_.populateSubmitMFAOptions();
            }
        } else if (this_.getContext() != null) {
            String errorMessage = Utils.getErrorMessage(response, retrofit);
            Context context = this_.getContext();
            if (TextUtils.isEmpty(errorMessage)) {
                errorMessage = this_.getContext().getString(R.string.error_occurred_try_again);
            }
            Utils.showMessage(context, errorMessage, 1);
        }
    }

    static /* synthetic */ void lambda$createAchSetupSession$1(PlaidAccountLoginFragment this_, ProgressDialog progressDialog, Throwable t) {
        Utils.dismissDialog(progressDialog);
        Utils.showMessage(this_.getContext(), t, 1);
    }

    private void sendMFA(String mask, String sessionId) {
        final ProgressDialog progressDialog = ProgressDialog.show(getContext(), "", getString(R.string.sending));
        HashMap<String, Object> params = new HashMap();
        params.put(ApiConstants.MASK, mask);
        this.mLoginManager.getClient().sendMFAToken(params, sessionId, new CallbackWithRetrofit<AchSetupSession>() {
            public void onResponse(Call<AchSetupSession> call, Response<AchSetupSession> response, Retrofit retrofit) {
                Utils.dismissDialog(progressDialog);
                if (response.isSuccessful()) {
                    Log.d("Coinbase", "MFA token sent successfully");
                    PlaidAccountLoginFragment.this.mSession = (AchSetupSession) response.body();
                    PlaidAccountLoginFragment.this.mBinding.plaidAccountLoginContent.llPlaidSendMfa.setVisibility(8);
                    PlaidAccountLoginFragment.this.mBinding.plaidAccountLoginContent.tvPlaidSubmitMfaTitle.setText(R.string.enter_verification_code);
                    PlaidAccountLoginFragment.this.mBinding.plaidAccountLoginContent.llPlaidSubmitMfa.setVisibility(0);
                    PlaidAccountLoginFragment.this.populateSubmitMFAOptions();
                }
            }

            public void onFailure(Call<AchSetupSession> call, Throwable t) {
                Utils.dismissDialog(progressDialog);
                Utils.showMessage(PlaidAccountLoginFragment.this.getContext(), t, 1);
            }
        });
    }

    private void submitMFA(String sessionId, String code, List<String> answers) {
        final ProgressDialog progressDialog = ProgressDialog.show(getContext(), "", getString(R.string.submitting));
        HashMap<String, Object> params = new HashMap();
        if (code != null) {
            params.put(ApiConstants.MFA, code);
        } else {
            params.put(ApiConstants.MFA, answers);
        }
        this.mLoginManager.getClient().submitMFAToken(params, sessionId, new CallbackWithRetrofit<AchSetupSession>() {
            public void onResponse(Call<AchSetupSession> call, Response<AchSetupSession> response, Retrofit retrofit) {
                Utils.dismissDialog(progressDialog);
                if (response.isSuccessful()) {
                    Log.d("Coinbase", "Completed MFA");
                    PlaidAccountLoginFragment.this.mSession = (AchSetupSession) response.body();
                    PlaidAccountLoginFragment.this.populateAccountSpinner();
                    PlaidAccountLoginFragment.this.mBinding.plaidAccountLoginContent.llPlaidSubmitMfa.setVisibility(8);
                    PlaidAccountLoginFragment.this.mBinding.plaidAccountLoginContent.llPlaidChooseAccountForm.setVisibility(0);
                }
            }

            public void onFailure(Call<AchSetupSession> call, Throwable t) {
                Utils.dismissDialog(progressDialog);
                Utils.showMessage(PlaidAccountLoginFragment.this.getContext(), t, 1);
            }
        });
    }

    private void createBankAccount(String achAccountId) {
        ProgressDialog progressDialog = ProgressDialog.show(getActivity(), "", getString(R.string.creating));
        HashMap<String, Object> params = new HashMap();
        params.put("type", ApiConstants.ACH_BANK_ACCOUNT);
        params.put(ApiConstants.ACH_SETUP_SESSION_ID, this.mSession.getData().getId());
        params.put(ApiConstants.ACH_ACCOUNT_ID, achAccountId);
        this.mSubscription.add(this.mLoginManager.getClient().createBankWithAchSetupSessionRx(params).observeOn(this.mMainScheduler).subscribe(PlaidAccountLoginFragment$$Lambda$3.lambdaFactory$(this, progressDialog), PlaidAccountLoginFragment$$Lambda$4.lambdaFactory$(this, progressDialog)));
    }

    static /* synthetic */ void lambda$createBankAccount$2(PlaidAccountLoginFragment this_, ProgressDialog progressDialog, Pair pair) {
        Response<PaymentMethod> response = pair.first;
        Retrofit retrofit = pair.second;
        Utils.dismissDialog(progressDialog);
        if (response.isSuccessful()) {
            this_.mPaymentMethod = ((PaymentMethod) response.body()).getData();
            this_.mBinding.plaidAccountLoginContent.plaidLoginAllDone.llPlaidAllDone.setVisibility(0);
            this_.mBinding.plaidAccountLoginContent.plaidLoginAllDone.tvPlaidAllDoneDetails.setText(String.format(this_.getString(R.string.all_set_details), new Object[]{this_.mPaymentMethod.getName()}));
            this_.mBinding.plaidAccountLoginContent.ivPlaidLoginLogo.setVisibility(8);
            this_.mBinding.plaidAccountLoginContent.llPlaidChooseAccountForm.setVisibility(8);
            this_.mBankAccountsUpdatedConnector.get().onNext(new ClassConsumableEvent());
            MixpanelTracking.getInstance().trackEvent(MixpanelTracking.EVENT_P_SUCCESSFUL, new String[0]);
            return;
        }
        Utils.showRetrofitErrorMessage(response, retrofit, this_.getActivity());
    }

    static /* synthetic */ void lambda$createBankAccount$3(PlaidAccountLoginFragment this_, ProgressDialog progressDialog, Throwable t) {
        Utils.dismissDialog(progressDialog);
        Utils.showMessage(this_.getActivity(), t, 1);
    }
}
