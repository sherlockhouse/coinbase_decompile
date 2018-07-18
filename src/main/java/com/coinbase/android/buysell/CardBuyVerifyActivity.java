package com.coinbase.android.buysell;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import com.coinbase.android.CoinbaseActivity;
import com.coinbase.android.Log;
import com.coinbase.android.R;
import com.coinbase.android.databinding.ActivityCardBuyVerifyBinding;
import com.coinbase.android.utils.Utils;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.v2.models.transfers.Data;
import com.coinbase.v2.models.transfers.Payload;
import com.google.gson.Gson;

public class CardBuyVerifyActivity extends CoinbaseActivity {
    public static final String PAREQ = "PaReq";
    public static final String REDIRECT = "redirect_param";
    public static final String TERM_URL = "com.coinbase.coinbase";
    public static final String TRANSFER_WITH_3DS = "3ds_transfer";
    ActivityCardBuyVerifyBinding binding;
    private ProgressDialog progressDialog;
    Data transferWith3DS;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mScreenProtector.protect();
        this.binding = (ActivityCardBuyVerifyBinding) DataBindingUtil.setContentView(this, R.layout.activity_card_buy_verify);
        this.transferWith3DS = (Data) new Gson().fromJson(getIntent().getStringExtra(TRANSFER_WITH_3DS), Data.class);
        setUpD3SView();
        makePostTo3DSVerify();
    }

    private void setUpD3SView() {
        this.binding.webview.setDebugMode(false);
        this.binding.webview.setAuthorizationListener(new D3SViewListener() {
            public void onAuthorizationCompleted(String paRes) {
                Intent intent = new Intent();
                intent.putExtra(CardBuyVerifyActivity.REDIRECT, paRes);
                CardBuyVerifyActivity.this.setResult(-1, intent);
                CardBuyVerifyActivity.this.finish();
            }

            public void onAuthorizationStarted(D3SView view) {
                Utils.dismissDialog(CardBuyVerifyActivity.this.progressDialog);
            }

            public void onAuthorizationWebPageLoadingProgressChanged(int progress) {
                Log.d("d3s", MixpanelTracking.PROPERTY_SPLIT_TEST_EXPOSED_TEST);
            }

            public void onAuthorizationWebPageLoadingError(int errorCode, String description, String failingUrl) {
                Utils.dismissDialog(CardBuyVerifyActivity.this.progressDialog);
                Utils.showMessage(CardBuyVerifyActivity.this, (int) R.string.error_occurred_try_again, 1);
                MixpanelTracking.getInstance().trackEvent(MixpanelTracking.EVENT_3DS_LOAD_FAILED, new String[0]);
                CardBuyVerifyActivity.this.finish();
            }

            public void onAuthorizationWebPageLoadingComplete() {
                MixpanelTracking.getInstance().trackEvent(MixpanelTracking.EVENT_3DS_LOAD_COMPLETED, new String[0]);
            }
        });
    }

    private void makePostTo3DSVerify() {
        if (this.transferWith3DS.getSecure3dVerification() == null || this.transferWith3DS.getSecure3dVerification().getPayload() == null) {
            Utils.showMessage((Context) this, (int) R.string.error_occurred_try_again, 1);
            finish();
            return;
        }
        String paReq = "";
        for (Payload payload : this.transferWith3DS.getSecure3dVerification().getPayload()) {
            if (TextUtils.equals(payload.getName(), PAREQ)) {
                paReq = payload.getValue();
                break;
            }
        }
        this.progressDialog = ProgressDialog.show(this, "", getString(R.string.loading));
        this.binding.webview.authorize(this.transferWith3DS.getSecure3dVerification().getUrl(), paReq, TERM_URL);
    }
}
