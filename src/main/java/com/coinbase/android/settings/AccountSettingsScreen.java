package com.coinbase.android.settings;

import android.content.Intent;
import android.os.Bundle;
import com.coinbase.android.paymentmethods.ConnectedAccountsLayout;
import com.coinbase.v2.models.paymentMethods.Data;
import java.util.List;
import org.joda.money.CurrencyUnit;

public interface AccountSettingsScreen {
    Bundle getArgs();

    void hideProgressDialog();

    void hideRemovePaymentView();

    void hideTiersHeader();

    boolean isRemovePaymentViewVisible();

    boolean isShown();

    void onEmailSettingsItemClicked();

    void onIdentityDocumentItemClicked();

    void onInvestmentTiersClicked();

    void onNameItemClicked(String str);

    void onNativeCurrencyItemClicked(List<CurrencyUnit> list, int i);

    void onPaymentMethodsItemClicked();

    void onPersonalInformationItemClicked();

    void onPhoneNumberItemClicked();

    void onPinItemClicked(boolean z);

    void onPrivacyItemClicked();

    void onShareItemClicked();

    void onSignOutItemClicked();

    void onSupportItemClicked();

    void routeToAddAccounts();

    void routeToVerifyAccount(Data data);

    void setConnectedAccountsLayout(ConnectedAccountsLayout connectedAccountsLayout);

    void setTiersActionButtonText(String str);

    void setTiersBody(String str);

    void setTiersHeader(String str);

    void showProgressDialog();

    void showTiersHeader();

    void signOut();

    void startActivityForResult(Intent intent, int i);

    void updateUserData();
}
