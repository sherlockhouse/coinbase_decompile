package com.coinbase.android.paymentmethods.card;

import android.support.v4.app.FragmentManager;
import com.coinbase.api.internal.models.billingAddress.Data;
import java.util.List;

interface CardFormScreen {
    public static final int ADD_BILLING_ADDRESS_REQUEST_CODE = 103;
    public static final int BILLING_LIST_REQUEST_CODE = 102;
    public static final String CARD_TYPE = "card_type";
    public static final int CDV_REQUEST_CODE = 104;
    public static final int SCAN_REQUEST_CODE = 101;

    void clearCardNumberError();

    void clearCvvError();

    void clearExpiryError();

    void clearFullNameValid();

    void closeForm();

    String getExpiry();

    FragmentManager getFragmentManager();

    void hideBillingAddressLayout();

    void hideBillingAddressProgressBar();

    void hideBottomSheet();

    void hideContent();

    void hideContinueProgress();

    void hideKeyboard();

    void hideProgress();

    void hideWorldPayInfo();

    void initializeBillingAddress(List<Data> list);

    boolean isShown();

    void notifyBillingAddressAdapterDataSetChanged();

    void popToRoot();

    void setContinueButtonDisabled();

    void setContinueButtonEnabled();

    void setExpiry(String str);

    void setFinishButtonText(String str);

    void setFullName(String str);

    void showAddingCardProgress();

    void showBillingAddressLayout();

    void showBillingAddressProgressBar();

    void showCardNumberError();

    void showCardNumberHelpBottomSheet();

    void showCardNumberValid();

    void showContent();

    void showContinueProgress();

    void showCvvError();

    void showCvvHelpBottomSheet();

    void showCvvValid();

    void showDoesntRequireBillingAddressView();

    void showExpiryError();

    void showExpiryHelpBottomSheet();

    void showExpiryValid();

    void showFullNameError();

    void showFullNameValid();

    void showNonUsZipCode();

    void showProgress();

    void showRequiresBillingAddressView();

    void showWorldPayInfo();

    void switchToAllDoneView(String str);
}
