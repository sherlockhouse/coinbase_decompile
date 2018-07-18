package com.coinbase.android.accounts;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.databinding.FragmentAccountMainBinding;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.android.ui.BottomNavigationConnector;
import com.coinbase.android.ui.BottomNavigationItem.Type;
import com.coinbase.android.ui.PageControllerLifeCycle.PageDestination;
import com.coinbase.api.internal.ApiConstants;
import com.coinbase.api.internal.CoinbaseAdapterFactory;
import com.coinbase.v2.models.account.Data;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.inject.Inject;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

@ControllerScope
/* compiled from: AccountMainController.kt */
public final class AccountMainController extends ActionBarController implements AccountMainScreen {
    public FragmentAccountMainBinding mBinding;
    @Inject
    public BottomNavigationConnector mBottomNavigationConnector;
    @Inject
    public AccountMainPresenter mPresenter;

    public final FragmentAccountMainBinding getMBinding() {
        FragmentAccountMainBinding fragmentAccountMainBinding = this.mBinding;
        if (fragmentAccountMainBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mBinding");
        }
        return fragmentAccountMainBinding;
    }

    public final void setMBinding(FragmentAccountMainBinding <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.mBinding = <set-?>;
    }

    public final AccountMainPresenter getMPresenter() {
        AccountMainPresenter accountMainPresenter = this.mPresenter;
        if (accountMainPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mPresenter");
        }
        return accountMainPresenter;
    }

    public final void setMPresenter(AccountMainPresenter <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.mPresenter = <set-?>;
    }

    public final BottomNavigationConnector getMBottomNavigationConnector() {
        BottomNavigationConnector bottomNavigationConnector = this.mBottomNavigationConnector;
        if (bottomNavigationConnector == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mBottomNavigationConnector");
        }
        return bottomNavigationConnector;
    }

    public final void setMBottomNavigationConnector(BottomNavigationConnector <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.mBottomNavigationConnector = <set-?>;
    }

    public AccountMainController(Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(bundle, "bundle");
        super(bundle);
    }

    protected void onAttach(View view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onAttach(view);
    }

    protected View onCreateView(LayoutInflater inflater, ViewGroup container) {
        Intrinsics.checkParameterIsNotNull(inflater, "inflater");
        Intrinsics.checkParameterIsNotNull(container, "container");
        ViewDataBinding inflate = DataBindingUtil.inflate(inflater, R.layout.fragment_account_main, container, false);
        Intrinsics.checkExpressionValueIsNotNull(inflate, "DataBindingUtil.inflate<…t_main, container, false)");
        this.mBinding = (FragmentAccountMainBinding) inflate;
        FragmentAccountMainBinding fragmentAccountMainBinding = this.mBinding;
        if (fragmentAccountMainBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mBinding");
        }
        onAttachToolbar(fragmentAccountMainBinding.cvCoinbaseToolbar);
        Activity activity = getActivity();
        if (activity == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.coinbase.android.MainActivitySubcomponentProvider");
        }
        ((MainActivitySubcomponentProvider) activity).mainActivitySubcomponent().addAccountListControllerSubcomponent(new AccountMainPresenterModule(this)).inject(this);
        fragmentAccountMainBinding = this.mBinding;
        if (fragmentAccountMainBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mBinding");
        }
        View root = fragmentAccountMainBinding.getRoot();
        Intrinsics.checkExpressionValueIsNotNull(root, "mBinding.root");
        return root;
    }

    protected void onShow() {
        super.onShow();
        AccountMainPresenter accountMainPresenter = this.mPresenter;
        if (accountMainPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mPresenter");
        }
        accountMainPresenter.onResume();
        FragmentAccountMainBinding fragmentAccountMainBinding = this.mBinding;
        if (fragmentAccountMainBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mBinding");
        }
        fragmentAccountMainBinding.layoutAccountList.onResume();
    }

    protected void onHide() {
        super.onHide();
        AccountMainPresenter accountMainPresenter = this.mPresenter;
        if (accountMainPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mPresenter");
        }
        accountMainPresenter.onDestroy();
        FragmentAccountMainBinding fragmentAccountMainBinding = this.mBinding;
        if (fragmentAccountMainBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mBinding");
        }
        fragmentAccountMainBinding.layoutAccountList.onDestroy();
    }

    protected SpannableStringBuilder getTitle() {
        SpannableStringBuilder spannableStringBuilder = null;
        if (getApplicationContext() == null) {
            return null;
        }
        Context applicationContext = getApplicationContext();
        if (applicationContext != null) {
            spannableStringBuilder = applicationContext.getString(R.string.accounts);
        }
        return new SpannableStringBuilder(spannableStringBuilder);
    }

    public void gotoAccountTransactions(Data account, com.coinbase.api.internal.models.currency.Data currency) {
        Intrinsics.checkParameterIsNotNull(account, ApiConstants.ACCOUNT);
        Bundle args = new Bundle();
        args.putString(AccountTransactionsPresenter.ACCOUNT_DATA, new Gson().toJson((Object) account));
        if (currency != null) {
            args.putString(AccountTransactionsPresenter.CURRENCY_DATA, new GsonBuilder().registerTypeAdapterFactory(CoinbaseAdapterFactory.create()).create().toJson((Object) currency));
        }
        BottomNavigationConnector bottomNavigationConnector = this.mBottomNavigationConnector;
        if (bottomNavigationConnector == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mBottomNavigationConnector");
        }
        bottomNavigationConnector.get().onNext(PageDestination.builder().setSourceBottomNavigationItem(Type.ACCOUNTS).setBottomNavigationItem(Type.ACCOUNTS).setPushPageController(new AccountTransactionsController(appendArgs(args))).build());
    }
}
