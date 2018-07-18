package com.coinbase.android.paymentmethods;

import android.app.Activity;
import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
import com.coinbase.android.CoinbaseActivityMystiqueSubcomponentProvider;
import com.coinbase.android.Constants;
import com.coinbase.android.FragmentScope;
import com.coinbase.android.R;
import com.coinbase.android.databinding.FragmentIavLoginBinding;
import com.coinbase.android.dialog.ConfirmationDialogFragment;
import com.coinbase.android.ui.BackgroundDimmer;
import com.coinbase.android.utils.Utils;
import com.coinbase.api.LoginManager;
import com.coinbase.v2.models.paymentMethods.Data;
import com.google.gson.Gson;
import java.util.List;
import javax.inject.Inject;

@FragmentScope
public class IAVLoginFragment extends Fragment implements IAVLoginScreen {
    private static final int HINT_ANIM_ROTATION = 15;
    FragmentIavLoginBinding mBinding;
    @Inject
    BackgroundDimmer mDimmer;
    private TextWatcher mEditTextWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            IAVLoginFragment.this.mPresenter.onFormUpdated();
        }
    };
    @Inject
    LoginManager mLoginManager;
    @Inject
    IAVLoginPresenter mPresenter;
    ProgressDialog mProgressDialog;

    public static class ConfirmPlaidCancelDialogFragment extends ConfirmationDialogFragment {
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
    }

    public static class IAVConfirmPlaidCancelDialogFragment extends ConfirmPlaidCancelDialogFragment {
        private IAVLoginPresenter mPresenter;

        public void setPresenter(IAVLoginPresenter presenter) {
            this.mPresenter = presenter;
        }

        public void onUserCancel() {
            super.onUserCancel();
            if (this.mPresenter != null) {
                this.mPresenter.onConfirmPlaidCancel();
            }
        }
    }

    public /* bridge */ /* synthetic */ Activity getActivity() {
        return super.getActivity();
    }

    public static IAVLoginFragment newInstance(Data method, boolean parentSuccessRouter) {
        IAVLoginFragment f = new IAVLoginFragment();
        Bundle args = new Bundle();
        args.putString("payment_method", new Gson().toJson((Object) method));
        args.putBoolean(Constants.PARENT_SUCCESS_ROUTER, parentSuccessRouter);
        f.setArguments(args);
        return f;
    }

    public static IAVLoginFragment newInstance(boolean parentSuccessRouter) {
        IAVLoginFragment f = new IAVLoginFragment();
        Bundle args = new Bundle();
        args.putBoolean(Constants.PARENT_SUCCESS_ROUTER, parentSuccessRouter);
        f.setArguments(args);
        return f;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((CoinbaseActivityMystiqueSubcomponentProvider) getActivity()).coinbaseActivityMystiqueSubcomponent().iavLoginFragmentSubcomponent(new IAVLoginPresenterModule(this)).inject(this);
        this.mPresenter.onCreate(getArguments());
        this.mBinding = (FragmentIavLoginBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_iav_login, container, false);
        this.mBinding.iavLoginContent.setPresenter(this.mPresenter);
        this.mBinding.iavLoginContent.cardCdvAmountsForm.setPresenter(this.mPresenter);
        this.mBinding.iavLoginContent.bankCdvAmountsForm.setPresenter(this.mPresenter);
        return this.mBinding.getRoot();
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.mBinding.iavLoginContent.btnIavNameFormSubmit.setOnClickListener(IAVLoginFragment$$Lambda$1.lambdaFactory$(this));
        this.mBinding.iavLoginContent.btnIavTypeSubmit.setOnClickListener(IAVLoginFragment$$Lambda$2.lambdaFactory$(this));
        this.mBinding.iavLoginContent.btnIavDetailsSubmit.setOnClickListener(IAVLoginFragment$$Lambda$3.lambdaFactory$(this));
        this.mBinding.iavLoginContent.btnChooseCdv.setOnClickListener(IAVLoginFragment$$Lambda$4.lambdaFactory$(this));
        this.mBinding.iavLoginContent.cardCdvAmountsForm.btnCdvVerificationAmountsSubmit.setOnClickListener(IAVLoginFragment$$Lambda$5.lambdaFactory$(this));
        this.mBinding.iavLoginContent.bankCdvAmountsForm.btnCdvVerificationAmountsSubmit.setOnClickListener(IAVLoginFragment$$Lambda$6.lambdaFactory$(this));
        this.mBinding.iavLoginContent.plaidLoginAllDone.btnPlaidAllDoneSubmit.setOnClickListener(IAVLoginFragment$$Lambda$7.lambdaFactory$(this));
        this.mBinding.iavLoginContent.tvIavShowRoutingDetails.setOnClickListener(IAVLoginFragment$$Lambda$8.lambdaFactory$(this));
        this.mBinding.iavLoginContent.btnCdvAmountSentContinue.setOnClickListener(IAVLoginFragment$$Lambda$9.lambdaFactory$(this));
        this.mBinding.iavLoginContent.llIavNameForm.setVisibility(0);
        this.mBinding.iavLoginContent.llIavTypeForm.setVisibility(8);
        this.mBinding.iavLoginContent.llIavAccountDetailsForm.setVisibility(8);
        this.mBinding.iavLoginContent.llChooseCdvIavForm.setVisibility(8);
        this.mBinding.iavLoginContent.llCdvAmountSentForm.setVisibility(8);
        this.mBinding.iavLoginContent.cardCdvAmountsForm.llCdvVerificationAmountsForm.setVisibility(8);
        this.mBinding.iavLoginContent.bankCdvAmountsForm.llCdvVerificationAmountsForm.setVisibility(8);
        this.mBinding.iavLoginContent.plaidLoginAllDone.llPlaidAllDone.setVisibility(8);
        this.mBinding.iavLoginContent.llIavVerificationInProgressForm.setVisibility(8);
        this.mBinding.iavLoginContent.etFullName.addTextChangedListener(this.mEditTextWatcher);
        this.mBinding.iavLoginContent.etAchAccountNumber.addTextChangedListener(this.mEditTextWatcher);
        this.mBinding.iavLoginContent.etAchRoutingNumber.addTextChangedListener(this.mEditTextWatcher);
        this.mBinding.iavLoginContent.cardCdvAmountsForm.etAmount1.addTextChangedListener(this.mEditTextWatcher);
        this.mBinding.iavLoginContent.cardCdvAmountsForm.etAmount2.addTextChangedListener(this.mEditTextWatcher);
        this.mBinding.iavLoginContent.bankCdvAmountsForm.etAmount1.addTextChangedListener(this.mEditTextWatcher);
        this.mBinding.iavLoginContent.bankCdvAmountsForm.etAmount2.addTextChangedListener(this.mEditTextWatcher);
        this.mBinding.iavLoginContent.cardCdvAmountsForm.etAmount1.setOnClickListener(IAVLoginFragment$$Lambda$10.lambdaFactory$());
        this.mBinding.iavLoginContent.cardCdvAmountsForm.etAmount2.setOnClickListener(IAVLoginFragment$$Lambda$11.lambdaFactory$());
        this.mBinding.iavLoginContent.bankCdvAmountsForm.etAmount1.setOnClickListener(IAVLoginFragment$$Lambda$12.lambdaFactory$());
        this.mBinding.iavLoginContent.bankCdvAmountsForm.etAmount2.setOnClickListener(IAVLoginFragment$$Lambda$13.lambdaFactory$());
        this.mBinding.iavLoginContent.cardCdvAmountsForm.tvTransactionSupport.setMovementMethod(LinkMovementMethod.getInstance());
        this.mBinding.iavLoginContent.bankCdvAmountsForm.tvTransactionSupport.setMovementMethod(LinkMovementMethod.getInstance());
        this.mBinding.iavLoginContent.cardCdvAmountsForm.tvTransactionSupport.setOnClickListener(IAVLoginFragment$$Lambda$14.lambdaFactory$());
        this.mBinding.iavLoginContent.bankCdvAmountsForm.tvTransactionSupport.setOnClickListener(IAVLoginFragment$$Lambda$15.lambdaFactory$());
        this.mPresenter.onActivityCreated();
    }

    public void onStart() {
        super.onStart();
        this.mPresenter.onStart();
    }

    public void onStop() {
        super.onStop();
        this.mPresenter.onStop();
    }

    public void onPause() {
        super.onPause();
        this.mPresenter.onPause();
    }

    public void switchToIAVTypeForm() {
        this.mBinding.iavLoginContent.llIavNameForm.setVisibility(8);
        this.mBinding.iavLoginContent.llIavTypeForm.setVisibility(0);
        Utils.hideKeyboardFrom(getActivity(), this.mBinding.iavLoginContent.btnIavNameFormSubmit);
    }

    public void initializeAccountTypeAdapter(List<AccountTypeSpinnerItem> accountTypeSpinnerItemList) {
        ArrayAdapter<AccountTypeSpinnerItem> adapter = new ArrayAdapter(getActivity(), R.layout.spinner_option_plaid);
        adapter.addAll(accountTypeSpinnerItemList);
        this.mBinding.iavLoginContent.spinnerIavType.setAdapter(adapter);
    }

    public int getSelectedAccountTypePosition() {
        return this.mBinding.iavLoginContent.spinnerIavType.getSelectedItemPosition();
    }

    public void switchToAccountDetailsForm() {
        this.mBinding.iavLoginContent.llIavTypeForm.setVisibility(8);
        this.mBinding.iavLoginContent.llIavAccountDetailsForm.setVisibility(0);
        Utils.hideKeyboardFrom(getActivity(), this.mBinding.iavLoginContent.btnIavNameFormSubmit);
    }

    public void showCreateAchAccountProgress() {
        Utils.hideKeyboardFrom(getActivity(), this.mBinding.iavLoginContent.btnIavNameFormSubmit);
        this.mProgressDialog = ProgressDialog.show(getActivity(), "", getString(R.string.creating));
    }

    public void hideKeyboard() {
        Utils.hideKeyboardFrom(getActivity(), this.mBinding.iavLoginContent.btnIavNameFormSubmit);
    }

    public void switchToAllDoneView(String allDoneText) {
        this.mBinding.iavLoginContent.cardCdvAmountsForm.llCdvVerificationAmountsForm.setVisibility(8);
        this.mBinding.iavLoginContent.bankCdvAmountsForm.llCdvVerificationAmountsForm.setVisibility(8);
        this.mBinding.iavLoginContent.plaidLoginAllDone.llPlaidAllDone.setVisibility(0);
        this.mBinding.iavLoginContent.plaidLoginAllDone.tvPlaidAllDoneDetails.setText(allDoneText);
        this.mBinding.iavLoginContent.ivIavLogo.setVisibility(8);
    }

    public void hideIAVForm() {
        this.mBinding.iavLoginContent.llIavNameForm.setVisibility(8);
    }

    public void showProgressVerifying() {
        this.mProgressDialog = ProgressDialog.show(getActivity(), "", getString(R.string.verifying));
    }

    public void hideProgressDialog() {
        if (this.mProgressDialog != null) {
            Utils.dismissDialog(this.mProgressDialog);
        }
    }

    public void showContinueButtonDisabled() {
        this.mBinding.iavLoginContent.btnIavNameFormSubmit.setEnabled(false);
        this.mBinding.iavLoginContent.btnIavDetailsSubmit.setEnabled(false);
    }

    public void showContinueButtonEnabled() {
        this.mBinding.iavLoginContent.btnIavNameFormSubmit.setEnabled(true);
        this.mBinding.iavLoginContent.btnIavDetailsSubmit.setEnabled(true);
    }

    public boolean showingRoutingDetailsHint() {
        return this.mBinding.iavLoginContent.rlHintContainer.getVisibility() == 0;
    }

    public boolean showingPlaidAllDoneView() {
        return this.mBinding.iavLoginContent.plaidLoginAllDone.llPlaidAllDone.getVisibility() == 0;
    }

    public boolean onBackPressed() {
        return this.mPresenter.onBackPressed();
    }

    public void setInvestNowButtonText(String text) {
        this.mBinding.iavLoginContent.plaidLoginAllDone.btnPlaidAllDoneSubmit.setText(text);
    }

    public void showRoutingDetails() {
        this.mDimmer.dim(this.mBinding.iavLoginContent.rlHintContainer, IAVLoginFragment$$Lambda$16.lambdaFactory$(this), 17);
    }

    static /* synthetic */ void lambda$showRoutingDetails$15(IAVLoginFragment this_) {
        int duration = this_.getResources().getInteger(17694721);
        TranslateAnimation translateAnim = new TranslateAnimation(0.0f, 0.0f, (float) ((Utils.getHeight(this_.getActivity()) / 2) * -1), 0.0f);
        RotateAnimation rotateAnim = new RotateAnimation(-15.0f, 0.0f, 1, 0.5f, 1, 0.5f);
        AnimationSet set = new AnimationSet(true);
        set.setInterpolator(new DecelerateInterpolator());
        set.setDuration((long) duration);
        set.addAnimation(rotateAnim);
        set.addAnimation(translateAnim);
        this_.mBinding.iavLoginContent.ivCheque.startAnimation(set);
        this_.mBinding.iavLoginContent.llRoutingHint.startAnimation(AnimationUtils.loadAnimation(this_.getActivity(), R.anim.abc_fade_in));
        this_.mBinding.iavLoginContent.llAccountHint.startAnimation(AnimationUtils.loadAnimation(this_.getActivity(), R.anim.abc_fade_in));
    }

    public void hideRoutingDetails() {
        int duration = getResources().getInteger(17694721);
        this.mDimmer.undim(null);
        TranslateAnimation translateAnim = new TranslateAnimation(0.0f, 0.0f, 0.0f, (float) (Utils.getHeight(getActivity()) / 2));
        RotateAnimation rotateAnim = new RotateAnimation(0.0f, 15.0f, 1, 0.5f, 1, 0.5f);
        AnimationSet set = new AnimationSet(true);
        set.setInterpolator(new AccelerateInterpolator());
        set.setDuration((long) duration);
        set.addAnimation(rotateAnim);
        set.addAnimation(translateAnim);
        this.mBinding.iavLoginContent.ivCheque.startAnimation(set);
        this.mBinding.iavLoginContent.llRoutingHint.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.abc_fade_out));
        this.mBinding.iavLoginContent.llAccountHint.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.abc_fade_out));
    }

    public void finishActivity() {
        if (getActivity() != null) {
            getActivity().setResult(-1);
            getActivity().finish();
        }
    }

    public void showCdvVerificationForm() {
        this.mBinding.iavLoginContent.llIavAccountDetailsForm.setVisibility(8);
        this.mBinding.iavLoginContent.llCdvAmountSentForm.setVisibility(0);
    }

    public void showCdvVerificationAmountsForm() {
        this.mBinding.iavLoginContent.cardCdvAmountsForm.llCdvVerificationAmountsForm.setVisibility(0);
    }

    public void showBankCdvVerificationAmountsForm() {
        this.mBinding.iavLoginContent.bankCdvAmountsForm.llCdvVerificationAmountsForm.setVisibility(0);
    }

    public void showConfirmPlaidCancelScreen() {
        IAVConfirmPlaidCancelDialogFragment fragment = new IAVConfirmPlaidCancelDialogFragment();
        fragment.setPresenter(this.mPresenter);
        fragment.show(getChildFragmentManager(), "cancel");
    }
}
