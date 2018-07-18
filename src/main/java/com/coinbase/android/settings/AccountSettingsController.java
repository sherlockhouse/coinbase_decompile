package com.coinbase.android.settings;

import android.app.ProgressDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.coinbase.android.CoinbaseActivity;
import com.coinbase.android.Constants;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.databinding.FragmentAccountSettingsBinding;
import com.coinbase.android.dialog.InputTextDialogFragment;
import com.coinbase.android.dialog.SpinnerDialogFragment;
import com.coinbase.android.identityverification.IdentityVerificationController;
import com.coinbase.android.paymentmethods.ConnectedAccountsLayout;
import com.coinbase.android.paymentmethods.PaymentMethodsController;
import com.coinbase.android.paymentmethods.PaymentMethodsPresenter;
import com.coinbase.android.paymentmethods.PlaidAccountLoginActivity;
import com.coinbase.android.phone.PhoneNumberController;
import com.coinbase.android.pin.PINPromptActivity;
import com.coinbase.android.settings.gdpr.EmailSettingsController;
import com.coinbase.android.settings.gdpr.PrivacyRightsController;
import com.coinbase.android.settings.idology.IdologySettingsController;
import com.coinbase.android.settings.tiers.InvestmentTiersSettingsController;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.android.ui.BottomNavigationConnector;
import com.coinbase.android.ui.BottomNavigationItem.Type;
import com.coinbase.android.ui.PageControllerLifeCycle.PageDestination;
import com.coinbase.android.ui.SignOutFragment;
import com.coinbase.android.utils.Utils;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.v2.models.paymentMethods.Data;
import com.google.gson.Gson;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import org.joda.money.CurrencyUnit;

@ControllerScope
public class AccountSettingsController extends ActionBarController implements AccountSettingsScreen {
    private static final float PERCENTAGE_TO_SHOW_HEADER_AT_TOOLBAR = 0.8f;
    private static final String SUPPORT_URL = "https://support.coinbase.com/customer/en/portal/topics/849213-coinbase-mobile-app/";
    private SettingsPreferenceListAdapter mAdapter;
    private FragmentAccountSettingsBinding mBinding;
    @Inject
    BottomNavigationConnector mBottomNavigationConnector;
    private ConnectedAccountsLayout mConnectedAccountsLayout;
    @Inject
    AccountSettingsPresenter mPresenter;
    private ProgressDialog mProgressDialog;

    public AccountSettingsController(Bundle bundle) {
        super(bundle);
    }

    protected View onCreateView(LayoutInflater inflater, ViewGroup container) {
        this.mBinding = (FragmentAccountSettingsBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_account_settings, container, false);
        onAttachToolbar(this.mBinding.cvCoinbaseToolbar);
        ((MainActivitySubcomponentProvider) getActivity()).mainActivitySubcomponent().addAccountSettingsControllerSubcomponent(new AccountSettingsPresenterModule(this, this)).inject(this);
        updateHeaderView();
        this.mAdapter = new SettingsPreferenceListAdapter(getApplicationContext(), this.mPresenter.getPreferenceList());
        this.mBinding.rvSettings.setAdapter(this.mAdapter);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getApplicationContext(), 1);
        itemDecoration.setDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.account_settings_divider));
        this.mBinding.rvSettings.addItemDecoration(itemDecoration);
        this.mBinding.rvSettings.setLayoutManager(new LinearLayoutManager(getApplicationContext(), 1, false));
        this.mBinding.rvSettings.setNestedScrollingEnabled(false);
        this.mBinding.appBar.addOnOffsetChangedListener(AccountSettingsController$$Lambda$1.lambdaFactory$(this));
        this.mBinding.btnIncreaseYourLimits.setOnClickListener(AccountSettingsController$$Lambda$2.lambdaFactory$(this));
        setForceDisableToolbarThemeUpdate(true);
        return this.mBinding.getRoot();
    }

    public void onShow() {
        super.onShow();
        this.mPresenter.onResume();
    }

    public void onHide() {
        super.onHide();
        this.mPresenter.onDestroy();
        if (this.mConnectedAccountsLayout != null) {
            this.mConnectedAccountsLayout.onHide();
        }
    }

    public SpannableStringBuilder getTitle() {
        if (getApplicationContext() == null) {
            return null;
        }
        return new SpannableStringBuilder(getApplicationContext().getString(R.string.title_account));
    }

    protected boolean onBackPressed() {
        if (this.mPresenter == null) {
            return super.onBackPressed();
        }
        return this.mPresenter.onBackPressed();
    }

    public void updateUserData() {
        this.mAdapter.notifyDataSetChanged();
        updateHeaderView();
    }

    public void showProgressDialog() {
        this.mProgressDialog = ProgressDialog.show(getActivity(), "", getApplicationContext().getString(R.string.please_wait));
    }

    public void hideProgressDialog() {
        Utils.dismissDialog(this.mProgressDialog);
    }

    public void onInvestmentTiersClicked() {
        this.mBottomNavigationConnector.get().onNext(PageDestination.builder().setSourceBottomNavigationItem(Type.MORE).setBottomNavigationItem(Type.MORE).setPushPageController(new InvestmentTiersSettingsController(appendArgs(new Bundle()))).build());
        MixpanelTracking.getInstance().trackEvent(MixpanelTracking.EVENT_INVESTMENT_TIERS_TAPPED_FROM_SETTINGS, new String[0]);
    }

    public void onNativeCurrencyItemClicked(List<CurrencyUnit> currencyList, int currencyUnitIndex) {
        if (((DialogFragment) ((AppCompatActivity) getActivity()).getSupportFragmentManager().findFragmentByTag("change_currency")) == null) {
            DialogFragment dialog = new ChangeNativeCurrencyDialogFragment();
            Bundle args = new Bundle();
            args.putSerializable(ChangeNativeCurrencyDialogFragment.CURRENCIES, (Serializable) currencyList);
            args.putInt(SpinnerDialogFragment.SELECTED_INDEX, currencyUnitIndex);
            dialog.setArguments(args);
            dialog.show(((AppCompatActivity) getActivity()).getSupportFragmentManager(), "change_currency");
        }
    }

    public void onPaymentMethodsItemClicked() {
        this.mBottomNavigationConnector.get().onNext(PageDestination.builder().setSourceBottomNavigationItem(Type.MORE).setBottomNavigationItem(Type.MORE).setPushPageController(new PaymentMethodsController(appendArgs(new Bundle()))).build());
        MixpanelTracking.getInstance().trackEvent(MixpanelTracking.EVENT_PAYMENT_METHODS_FROM_SETTINGS, new String[0]);
    }

    public void onPhoneNumberItemClicked() {
        MixpanelTracking.getInstance().trackEvent(MixpanelTracking.EVENT_MANAGE_PHONE_NUMBERS_SETTINGS, new String[0]);
        this.mBottomNavigationConnector.get().onNext(PageDestination.builder().setSourceBottomNavigationItem(Type.MORE).setBottomNavigationItem(Type.MORE).setPushPageController(new PhoneNumberController(appendArgs(new Bundle()))).build());
    }

    public void onPrivacyItemClicked() {
        MixpanelTracking.getInstance().trackEvent(MixpanelTracking.EVENT_MANAGE_PRIVACY_SETTINGS, new String[0]);
        this.mBottomNavigationConnector.get().onNext(PageDestination.builder().setSourceBottomNavigationItem(Type.MORE).setBottomNavigationItem(Type.MORE).setPushPageController(new PrivacyRightsController(appendArgsWithRoot(new Bundle()))).build());
    }

    public void onEmailSettingsItemClicked() {
        MixpanelTracking.getInstance().trackEvent(MixpanelTracking.EVENT_MANAGE_EMAIL_SETTINGS, new String[0]);
        this.mBottomNavigationConnector.get().onNext(PageDestination.builder().setSourceBottomNavigationItem(Type.MORE).setBottomNavigationItem(Type.MORE).setPushPageController(new EmailSettingsController(appendArgsWithRoot(new Bundle()))).build());
    }

    public void onIdentityDocumentItemClicked() {
        this.mBottomNavigationConnector.get().onNext(PageDestination.builder().setSourceBottomNavigationItem(Type.MORE).setBottomNavigationItem(Type.MORE).setPushPageController(new IdentityVerificationController(appendArgs(new Bundle()))).build());
        MixpanelTracking.getInstance().trackEvent(MixpanelTracking.EVENT_JUMIO_START_FROM_SETTINGS, new String[0]);
    }

    public void onPersonalInformationItemClicked() {
        this.mBottomNavigationConnector.get().onNext(PageDestination.builder().setSourceBottomNavigationItem(Type.MORE).setBottomNavigationItem(Type.MORE).setPushPageController(new IdologySettingsController(appendArgsWithRoot(new Bundle()))).build());
    }

    public void onPinItemClicked(boolean isSwitchOn) {
        Intent intent = new Intent(getActivity(), PINPromptActivity.class);
        if (!isSwitchOn) {
            intent.setAction(PINPromptActivity.ACTION_SET);
        }
        intent.putExtra(PINPromptActivity.FROM_SETTING, true);
        startActivity(intent);
    }

    public void onSupportItemClicked() {
        startActivity(new Intent("android.intent.action.VIEW", Uri.parse(SUPPORT_URL)));
    }

    public void onShareItemClicked() {
        Intent shareIntent = new Intent();
        shareIntent.setAction("android.intent.action.SEND");
        shareIntent.putExtra("android.intent.extra.TEXT", getApplicationContext().getString(R.string.share_text));
        shareIntent.setType("text/plain");
        startActivity(Intent.createChooser(shareIntent, getApplicationContext().getString(R.string.share)));
        MixpanelTracking.getInstance().trackEvent(MixpanelTracking.EVENT_SHARE, new String[0]);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (this.mPresenter == null) {
            super.onActivityResult(requestCode, resultCode, data);
        } else if (!this.mPresenter.onActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void onSignOutItemClicked() {
        new SignOutFragment().show(((AppCompatActivity) getActivity()).getSupportFragmentManager(), "onConfirmSignOut");
    }

    public void onNameItemClicked(String name) {
        if (((DialogFragment) ((AppCompatActivity) getActivity()).getSupportFragmentManager().findFragmentByTag("change_name")) == null) {
            DialogFragment dialog = new ChangeNameDialogFragment();
            Bundle args = new Bundle();
            args.putString(InputTextDialogFragment.VALUE, name);
            dialog.setArguments(args);
            dialog.show(((AppCompatActivity) getActivity()).getSupportFragmentManager(), "change_name");
        }
    }

    public void signOut() {
        ((CoinbaseActivity) getActivity()).signOut();
    }

    public boolean isShown() {
        return getActivity() != null && isAttached();
    }

    public void showTiersHeader() {
        this.mBinding.llTiersHeader.setVisibility(0);
    }

    public void hideTiersHeader() {
        this.mBinding.llTiersHeader.setVisibility(8);
    }

    public void setTiersHeader(String header) {
        this.mBinding.tvHeader.setText(header);
    }

    public void setTiersBody(String body) {
        this.mBinding.tvBody.setText(body);
    }

    public void setTiersActionButtonText(String buttonText) {
        this.mBinding.btnIncreaseYourLimits.setText(buttonText);
    }

    public void routeToAddAccounts() {
        Bundle args = new Bundle();
        args.putBoolean(PaymentMethodsPresenter.SHOWING_AVAILABLE_PAYMENT_METHODS, true);
        this.mBottomNavigationConnector.get().onNext(PageDestination.builder().setSourceBottomNavigationItem(Type.MORE).setBottomNavigationItem(Type.MORE).setPushPageController(new PaymentMethodsController(appendArgsWithRoot(args))).build());
    }

    public void routeToVerifyAccount(Data paymentMethod) {
        boolean z = true;
        Intent intent = new Intent(getActivity(), PlaidAccountLoginActivity.class);
        intent.putExtra("payment_method", new Gson().toJson((Object) paymentMethod));
        intent.putExtra(PlaidAccountLoginActivity.MANUAL, true);
        String str = Constants.PARENT_SUCCESS_ROUTER;
        if (TextUtils.isEmpty(getLocalRoot())) {
            z = false;
        }
        intent.putExtra(str, z);
        startActivityForResult(intent, PaymentMethodsPresenter.INTENT_VERIFY);
    }

    public boolean isRemovePaymentViewVisible() {
        if (this.mConnectedAccountsLayout == null) {
            return false;
        }
        return this.mConnectedAccountsLayout.isRemovePaymentViewVisible();
    }

    public void hideRemovePaymentView() {
        if (this.mConnectedAccountsLayout != null) {
            this.mConnectedAccountsLayout.hideRemovePaymentView();
        }
    }

    public void setConnectedAccountsLayout(ConnectedAccountsLayout layout) {
        this.mConnectedAccountsLayout = layout;
    }

    private void updateHeaderView() {
        NameItem nameItem = new NameItem(this.mPresenter);
        this.mBinding.tvName.setText(nameItem.getDisplayValue());
        this.mBinding.tvName.setOnClickListener(AccountSettingsController$$Lambda$3.lambdaFactory$(nameItem));
        this.mBinding.tvEmail.setText(new EmailItem(this.mPresenter).getDisplayValue());
    }

    private void handlePortfolioTitleHeaderVisibility(AppBarLayout appBarLayout, int verticalOffset) {
        if (getApplicationContext() != null) {
            float percentage = ((float) Math.abs(verticalOffset)) / ((float) appBarLayout.getTotalScrollRange());
            if (percentage > PERCENTAGE_TO_SHOW_HEADER_AT_TOOLBAR) {
                this.mBinding.ctLayout.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.coinbase_blue));
                if (this.mBinding.llUserName.getVisibility() == 0) {
                    this.mBinding.llUserName.setVisibility(4);
                }
            } else {
                this.mBinding.ctLayout.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.header_layout_background));
                if (this.mBinding.llUserName.getVisibility() == 4) {
                    this.mBinding.llUserName.setVisibility(0);
                }
            }
            this.mBinding.llUserName.setAlpha(1.0f - percentage);
        }
    }
}
