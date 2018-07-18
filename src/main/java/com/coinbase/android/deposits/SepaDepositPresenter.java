package com.coinbase.android.deposits;

import android.app.Application;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import com.coinbase.CallbackWithRetrofit;
import com.coinbase.android.FragmentScope;
import com.coinbase.android.R;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.utils.Utils;
import com.coinbase.api.LoginManager;
import com.coinbase.api.internal.models.sepaDepositInfo.Data;
import com.coinbase.api.internal.models.sepaDepositInfo.SepaDepositInfo;
import java.lang.annotation.Documented;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Qualifier;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

@FragmentScope
public class SepaDepositPresenter {
    private final Context mContext;
    private final int mCopyRes;
    private final LoginManager mLoginManager;
    private SepaDepositScreen mSepaDepositScreen;
    private final SnackBarWrapper mSnackBarWrapper;

    @Qualifier
    @Documented
    public @interface CopyAddress {
    }

    @Inject
    public SepaDepositPresenter(LoginManager loginManager, SepaDepositScreen screen, Application app, SnackBarWrapper snackBarWrapper, @CopyAddress int copyRes) {
        this.mLoginManager = loginManager;
        this.mSepaDepositScreen = screen;
        this.mContext = app;
        this.mSnackBarWrapper = snackBarWrapper;
        this.mCopyRes = copyRes;
    }

    public void onViewCreated() {
        if (this.mSepaDepositScreen.getFromDeposit().booleanValue()) {
            this.mSepaDepositScreen.setActivityTitle(this.mContext.getString(R.string.deposit_title));
        } else {
            this.mSepaDepositScreen.setActivityTitle(this.mContext.getString(R.string.add_account));
        }
        getSepaDepositInformation();
    }

    void getSepaDepositInformation() {
        this.mLoginManager.getClient().getSepaDepositInformation(new CallbackWithRetrofit<SepaDepositInfo>() {
            public void onResponse(Call<SepaDepositInfo> call, Response<SepaDepositInfo> response, Retrofit retrofit) {
                if (response.isSuccessful()) {
                    SepaDepositPresenter.this.mSepaDepositScreen.showSepaDepositInfo(SepaDepositPresenter.this.getSepaInfo(((SepaDepositInfo) response.body()).getData()));
                    return;
                }
                SepaDepositPresenter.this.mSepaDepositScreen.showError(Utils.getErrorMessage(response, retrofit));
            }

            public void onFailure(Call<SepaDepositInfo> call, Throwable t) {
                SepaDepositPresenter.this.mSepaDepositScreen.showError(t.getLocalizedMessage());
            }
        });
    }

    void onCopyButtonClicked(String sepaInfo) {
        ((ClipboardManager) this.mContext.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("Sepa", sepaInfo));
        this.mSnackBarWrapper.show(this.mCopyRes);
    }

    private List<Data> getSepaInfo(List<Data> infoFromServer) {
        String string;
        List<Data> info = new ArrayList();
        Data description = new Data();
        if (this.mSepaDepositScreen.getFromDeposit().booleanValue()) {
            string = this.mContext.getString(R.string.sepa_deposit_from_deposit);
        } else {
            string = this.mContext.getString(R.string.sepa_deposit_from_add);
        }
        description.setValue(string);
        description.setHighlight(Boolean.valueOf(false));
        info.add(description);
        info.addAll(infoFromServer);
        Data detail = new Data();
        detail.setValue(this.mContext.getString(R.string.sepa_deposit_detail));
        detail.setHighlight(Boolean.valueOf(false));
        info.add(detail);
        return info;
    }
}
