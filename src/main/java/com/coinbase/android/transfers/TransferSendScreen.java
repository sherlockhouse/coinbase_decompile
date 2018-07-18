package com.coinbase.android.transfers;

import com.coinbase.v2.models.transactions.Data;
import java.util.List;
import org.joda.money.Money;

public interface TransferSendScreen {
    void clearEmailSuggestions();

    void clearGoogleSuggestions();

    void dismissDialog();

    void dismissWithError();

    String getEnteredNotes();

    String getRecipient();

    void hideContactsHeader();

    void hideFee();

    void hideKeyboard();

    void hideRecentContactsHeader();

    void initializeViews(String str);

    boolean isShowingRecipientList();

    boolean isShown();

    void requestContactsPermission();

    void requestRecipientFocus();

    void routeSuccess();

    void setContactVisible(boolean z, String str, String str2);

    void setMessage(String str);

    void setRecipient(String str);

    void setTitle(String str);

    void showCaptureActivity(String str, int i);

    void showConfirmDialog(String str, String str2, Money money, String str3, boolean z, String str4, String str5, int i);

    void showContactsHeader();

    void showDelayedTransactionDialog(Data data, String str);

    void showEmailSuggestions(List<Suggestion> list);

    void showFee();

    void showFeeDescription(String str);

    void showGoogleSuggestions(List<Suggestion> list);

    void showKeyboardFromRecipient();

    void showNotes(boolean z);

    void showPINPrompt(int i);

    void showProgressDialog(String str);

    void showRecentContactsHeader();

    void showSuggestionsList();

    void showTransferSendTotal(String str);
}
