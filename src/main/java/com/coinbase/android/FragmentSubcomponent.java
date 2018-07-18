package com.coinbase.android;

import com.coinbase.android.accounts.AccountsFragment;
import com.coinbase.android.accounts.RenameAccountFragment;
import com.coinbase.android.deposits.DepositWithdrawConfirmationDialogFragment;
import com.coinbase.android.idology.IdologyQuestionDialogFragment;
import com.coinbase.android.paymentmethods.PlaidAccountLoginFragment;
import com.coinbase.android.phone.VerifyPhoneDialogFragment;
import com.coinbase.android.phone.VerifyPhoneHandler;
import com.coinbase.android.pin.PINSettingDialogFragment;
import com.coinbase.android.settings.ChangeNativeCurrencyDialogFragment;
import com.coinbase.android.signin.IdologyOptionDialogFragment;
import com.coinbase.android.signin.StateListDialogFragment;
import com.coinbase.android.ui.BlueProgressBar;
import com.coinbase.android.ui.MystiqueListSelectorLayout;
import com.coinbase.android.ui.SignOutFragment;

@ActivityScope
public interface FragmentSubcomponent {
    void inject(AccountsFragment accountsFragment);

    void inject(RenameAccountFragment renameAccountFragment);

    void inject(DepositWithdrawConfirmationDialogFragment depositWithdrawConfirmationDialogFragment);

    void inject(IdologyQuestionDialogFragment idologyQuestionDialogFragment);

    void inject(PlaidAccountLoginFragment plaidAccountLoginFragment);

    void inject(VerifyPhoneDialogFragment verifyPhoneDialogFragment);

    void inject(VerifyPhoneHandler verifyPhoneHandler);

    void inject(PINSettingDialogFragment pINSettingDialogFragment);

    void inject(ChangeNativeCurrencyDialogFragment changeNativeCurrencyDialogFragment);

    void inject(IdologyOptionDialogFragment idologyOptionDialogFragment);

    void inject(StateListDialogFragment stateListDialogFragment);

    void inject(BlueProgressBar blueProgressBar);

    void inject(MystiqueListSelectorLayout mystiqueListSelectorLayout);

    void inject(SignOutFragment signOutFragment);
}
