package com.coinbase.android.accounts;

import android.graphics.Bitmap;
import android.net.Uri;
import com.coinbase.v2.models.address.Data;

public interface AccountCryptoAddressScreen {
    void displaySupportUrl(Uri uri);

    void hideCopySuccessfulDialog();

    void setCryptoAddressBitmap(Bitmap bitmap);

    void setCryptoAddressBitmapError();

    void setCryptoAddressTitle(String str);

    void showAddressView();

    void showCopySuccessfulDialog(String str);

    void showErrorMessage(String str);

    void showFailureMessage(Throwable th);

    void showGenericErrorMessage();

    void showHelpTextVisible(boolean z);

    void showWarningScreen(Data data, com.coinbase.v2.models.account.Data data2);
}
