package com.coinbase.android.accounts;

import android.app.Application;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import android.util.Pair;
import com.coinbase.android.BackgroundScheduler;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.R;
import com.coinbase.android.accounts.AccountCryptoAddressButtonConnector.AccountCryptoAddressButtonEvent;
import com.coinbase.android.db.AccountORM;
import com.coinbase.android.db.DatabaseManager;
import com.coinbase.android.ui.MainScheduler;
import com.coinbase.android.utils.AccountUtils;
import com.coinbase.android.utils.CryptoUri;
import com.coinbase.android.utils.Utils;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.LoginManager;
import com.coinbase.v2.models.account.Data;
import com.coinbase.v2.models.address.Address;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import org.apache.http.util.TextUtils;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Scheduler;
import rx.subscriptions.CompositeSubscription;

@ControllerScope
public class AccountCryptoAddressPresenter {
    private static final String BCASH_ADDR_SUPPORT_URL = "https://support.coinbase.com/customer/portal/articles/2921133-cashaddr-faq";
    private static final String BCH_CURRENCY_CODE = "BCH";
    private static final String CLIP_DATA_LABEL = "Coinbase";
    private static final int DISMISS_DIALOG_DURATION_MS = 1000;
    private static final int QR_CODE_DIMENSION = 300;
    private final AccountCryptoAddressButtonConnector mAccountCryptoAddressButtonConnector;
    private final AccountCryptoAddressUpdatedConnector mAccountCryptoAddressUpdatedConnector;
    private final AccountORM mAccountORM;
    private final Scheduler mBackgroundScheduler;
    private final Context mContext;
    private final DatabaseManager mDbManager;
    private final LoginManager mLoginManager;
    private final Scheduler mMainScheduler;
    private final MixpanelTracking mMixpanelTracking;
    private final AccountCryptoAddressScreen mScreen;
    private Data mSelectedAccount;
    private com.coinbase.api.internal.models.currency.Data mSelectedCurrency;
    private final CompositeSubscription mSubscription = new CompositeSubscription();

    @Inject
    public AccountCryptoAddressPresenter(LoginManager loginManager, DatabaseManager dbManager, AccountORM accountORM, Application app, AccountCryptoAddressScreen screen, AccountCryptoAddressUpdatedConnector accountCryptoAddressUpdatedConnector, AccountCryptoAddressButtonConnector accountCryptoAddressButtonConnector, MixpanelTracking mixpanelTracking, @MainScheduler Scheduler mainScheduler, @BackgroundScheduler Scheduler backgroundScheduler) {
        this.mLoginManager = loginManager;
        this.mDbManager = dbManager;
        this.mAccountORM = accountORM;
        this.mContext = app;
        this.mScreen = screen;
        this.mAccountCryptoAddressUpdatedConnector = accountCryptoAddressUpdatedConnector;
        this.mAccountCryptoAddressButtonConnector = accountCryptoAddressButtonConnector;
        this.mMixpanelTracking = mixpanelTracking;
        this.mMainScheduler = mainScheduler;
        this.mBackgroundScheduler = backgroundScheduler;
    }

    void onViewInit() {
        this.mSubscription.add(this.mAccountCryptoAddressUpdatedConnector.get().observeOn(this.mBackgroundScheduler).map(AccountCryptoAddressPresenter$$Lambda$1.lambdaFactory$(this)).observeOn(this.mMainScheduler).subscribe(AccountCryptoAddressPresenter$$Lambda$2.lambdaFactory$(this)));
        this.mSubscription.add(this.mAccountCryptoAddressButtonConnector.get().onBackpressureLatest().filter(AccountCryptoAddressPresenter$$Lambda$3.lambdaFactory$()).observeOn(this.mBackgroundScheduler).map(AccountCryptoAddressPresenter$$Lambda$4.lambdaFactory$(this)).observeOn(this.mMainScheduler).subscribe(AccountCryptoAddressPresenter$$Lambda$5.lambdaFactory$(this)));
    }

    static /* synthetic */ Boolean lambda$onViewInit$2(AccountCryptoAddressButtonEvent button) {
        return Boolean.valueOf(button == AccountCryptoAddressButtonEvent.COPY);
    }

    static /* synthetic */ void lambda$onViewInit$4(AccountCryptoAddressPresenter this_, String cryptoAddressText) {
        if (TextUtils.isEmpty(cryptoAddressText)) {
            this_.handleGenericError();
            return;
        }
        String str;
        this_.copyAccountCryptoAddress(cryptoAddressText);
        MixpanelTracking mixpanelTracking = this_.mMixpanelTracking;
        String str2 = MixpanelTracking.EVENT_RECEIVE_TAPPED_COPY_ADDRESS;
        String[] strArr = new String[2];
        strArr[0] = "currency";
        if (this_.mSelectedAccount == null) {
            str = "";
        } else {
            str = this_.mSelectedAccount.getCurrency().getCode();
        }
        strArr[1] = str;
        mixpanelTracking.trackEvent(str2, strArr);
    }

    void onDestroy() {
        this.mSubscription.clear();
    }

    void generateAccountCryptoAddress(Data selectedAccount, com.coinbase.api.internal.models.currency.Data selectedCurrency) {
        if (selectedAccount == null || selectedCurrency == null) {
            handleGenericError();
            return;
        }
        this.mSelectedAccount = selectedAccount;
        this.mSelectedCurrency = selectedCurrency;
        setCryptoAddressTitleText();
        setHelpTextVisibility();
        generateAccountCryptoAddress();
    }

    void onShowAddressClicked() {
        this.mScreen.showAddressView();
    }

    void onHelpButtonClicked() {
        Uri uri = Uri.parse(BCASH_ADDR_SUPPORT_URL);
        if (uri != null) {
            this.mScreen.displaySupportUrl(uri);
        }
    }

    private void generateAccountCryptoAddress() {
        this.mSubscription.add(this.mLoginManager.getClient().generateAddressRx(this.mSelectedAccount.getId()).first().map(AccountCryptoAddressPresenter$$Lambda$6.lambdaFactory$(this)).subscribeOn(this.mBackgroundScheduler).observeOn(this.mMainScheduler).subscribe(AccountCryptoAddressPresenter$$Lambda$7.lambdaFactory$(this), AccountCryptoAddressPresenter$$Lambda$8.lambdaFactory$(this)));
    }

    static /* synthetic */ Pair lambda$generateAccountCryptoAddress$5(AccountCryptoAddressPresenter this_, Pair pair) {
        Response<Address> response = pair.first;
        if (response.isSuccessful()) {
            try {
                this_.mAccountORM.setReceiveAddress(this_.mDbManager.openDatabase(), this_.mSelectedAccount.getId(), ((Address) response.body()).getData().getAddress());
            } finally {
                this_.mDbManager.closeDatabase();
            }
        }
        return pair;
    }

    static /* synthetic */ void lambda$generateAccountCryptoAddress$6(AccountCryptoAddressPresenter this_, Pair pair) {
        Response<Address> response = pair.first;
        if (response.isSuccessful()) {
            com.coinbase.v2.models.address.Data address = ((Address) response.body()).getData();
            if (!(address.getWarningTitle() == null && address.getWarningDetails() == null)) {
                this_.mScreen.showWarningScreen(address, this_.mSelectedAccount);
            }
            this_.mAccountCryptoAddressUpdatedConnector.get().onNext(null);
            return;
        }
        this_.showErrorMessage(Utils.getErrorMessage(response, (Retrofit) pair.second));
        this_.showErrorView();
    }

    static /* synthetic */ void lambda$generateAccountCryptoAddress$7(AccountCryptoAddressPresenter this_, Throwable t) {
        this_.mScreen.showFailureMessage(t);
        this_.showErrorView();
    }

    private void setCryptoAddressTitleText() {
        if (!TextUtils.isEmpty(AccountUtils.getDisplayableAccountName(this.mSelectedAccount))) {
            this.mScreen.setCryptoAddressTitle(String.format(this.mContext.getString(R.string.my_account_address), new Object[]{accountName}));
        }
    }

    private void setHelpTextVisibility() {
        this.mScreen.showHelpTextVisible(this.mSelectedCurrency.getCode().equals(BCH_CURRENCY_CODE));
    }

    private Bitmap getCryptoAddressBitmap() {
        Bitmap bitmap = null;
        String cryptoAddressText = getCryptoAddressText();
        if (cryptoAddressText != null) {
            CryptoUri uri = new CryptoUri();
            uri.setScheme(this.mSelectedCurrency.getUriScheme());
            uri.setAddress(cryptoAddressText);
            try {
                bitmap = Utils.createBarcode(uri.toString().replace("?", ""), BarcodeFormat.QR_CODE, QR_CODE_DIMENSION, QR_CODE_DIMENSION);
            } catch (WriterException e) {
                Log.w(CLIP_DATA_LABEL, "Could not create QR code: " + e.getMessage());
            }
        }
        return bitmap;
    }

    private String getCryptoAddressText() {
        if (this.mSelectedAccount == null) {
            return null;
        }
        return this.mLoginManager.getReceiveAddress(this.mSelectedAccount.getId());
    }

    private void onReceiveCryptoAddressUpdated(Bitmap cryptoAddressBitmap) {
        if (cryptoAddressBitmap != null) {
            this.mScreen.setCryptoAddressBitmap(cryptoAddressBitmap);
        } else {
            handleGenericError();
        }
    }

    private void copyAccountCryptoAddress(String cryptoAddressText) {
        ((ClipboardManager) this.mContext.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText(CLIP_DATA_LABEL, cryptoAddressText));
        showCopySuccessful(String.format(this.mContext.getResources().getString(R.string.account_address_copied), new Object[]{AccountUtils.getDisplayableAccountName(this.mSelectedAccount)}));
    }

    private void showCopySuccessful(String copiedTitleText) {
        this.mSubscription.add(Observable.timer(1000, TimeUnit.MILLISECONDS).observeOn(this.mMainScheduler).subscribe(AccountCryptoAddressPresenter$$Lambda$9.lambdaFactory$(this)));
        this.mScreen.showCopySuccessfulDialog(copiedTitleText);
    }

    private void handleGenericError() {
        this.mScreen.showGenericErrorMessage();
        showErrorView();
    }

    private void showErrorMessage(String errorMessage) {
        if (TextUtils.isEmpty(errorMessage)) {
            this.mScreen.showGenericErrorMessage();
        } else {
            this.mScreen.showErrorMessage(errorMessage);
        }
    }

    private void showErrorView() {
        this.mScreen.setCryptoAddressBitmapError();
    }
}
