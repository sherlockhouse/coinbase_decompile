package com.coinbase.android.paymentmethods.card;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.coinbase.CallbackWithRetrofit;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.databinding.PaymentMethodVerificationControllerBinding;
import com.coinbase.android.identityverification.IdentityVerificationConstants;
import com.coinbase.android.identityverification.InAppIdentityAcceptableDocumentsController;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.android.utils.Utils;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.LoginManager;
import com.coinbase.api.internal.models.jumio.JumioProfiles;
import com.coinbase.api.internal.models.jumio.JumioProfiles.Status;
import javax.inject.Inject;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

@ControllerScope
public class PaymentMethodVerificationController extends ActionBarController {
    public static String JUMIO_STATUS = "JUMIO_STATUS";
    private static final int RETRY_WAIT_TIME = 5000;
    PaymentMethodVerificationControllerBinding mBinding;
    @Inject
    LoginManager mLoginManager;
    Status status = Status.INCOMPLETE;
    Handler verificationHandler = new Handler();
    Runnable verificationRunnable = null;

    public PaymentMethodVerificationController(Bundle bundle) {
        super(bundle);
    }

    protected View onCreateView(LayoutInflater inflater, ViewGroup container) {
        this.mBinding = (PaymentMethodVerificationControllerBinding) DataBindingUtil.inflate(inflater, R.layout.payment_method_verification_controller, container, false);
        ((MainActivitySubcomponentProvider) getActivity()).mainActivitySubcomponent().inject(this);
        onAttachToolbar(this.mBinding.cvCoinbaseToolbar);
        this.status = (Status) getArgs().getSerializable(JUMIO_STATUS);
        setVerificationState(this.status);
        this.mBinding.rlId.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (PaymentMethodVerificationController.this.status == Status.INCOMPLETE || PaymentMethodVerificationController.this.status == Status.FAILED) {
                    Bundle bundle = new Bundle();
                    bundle.putInt(ActionBarController.OVERIDDEN_UP_INDICATOR, -1);
                    bundle.putBoolean(IdentityVerificationConstants.SHOULD_NAVIGATE_TO_DEBIT, true);
                    PaymentMethodVerificationController.this.replaceModalController(new InAppIdentityAcceptableDocumentsController(PaymentMethodVerificationController.this.appendModalArgs(bundle)));
                    MixpanelTracking.getInstance().trackEvent(MixpanelTracking.EVENT_JUMIO_START_FROM_PAYMENT_METHOD, new String[0]);
                }
            }
        });
        this.mBinding.rlPayment.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (PaymentMethodVerificationController.this.status == Status.COMPLETED) {
                    PaymentMethodVerificationController.this.replaceModalController(new CardFormController(PaymentMethodVerificationController.this.appendModalArgs(new Bundle())));
                }
            }
        });
        return this.mBinding.getRoot();
    }

    protected void onAttach(View view) {
        super.onAttach(view);
        this.verificationRunnable = new Runnable() {
            public void run() {
                PaymentMethodVerificationController.this.checkJumioProfiles();
                PaymentMethodVerificationController.this.verificationHandler.postDelayed(this, 5000);
            }
        };
        this.verificationRunnable.run();
    }

    protected void onDetach(View view) {
        super.onDetach(view);
        this.verificationHandler.removeCallbacks(this.verificationRunnable);
    }

    protected SpannableStringBuilder getTitle() {
        return new SpannableStringBuilder(getApplicationContext().getString(R.string.card));
    }

    private void setVerificationState(Status status) {
        if (getApplicationContext() != null && status != null) {
            int idIconID = R.drawable.i_d_icon_blue_1;
            int verificationTitleTextColor = ContextCompat.getColor(getApplicationContext(), R.color.primary_dark);
            int verificationDescriptionTextColor = ContextCompat.getColor(getApplicationContext(), R.color.transaction_summary);
            this.mBinding.tvVerificationDescription.setText(getApplicationContext().getString(R.string.identity_verification_description));
            this.mBinding.tvVerificationDescription.setTextColor(verificationDescriptionTextColor);
            int paymentIconID = R.drawable.payment_icon_gray_2;
            int paymentTitleTextColor = ContextCompat.getColor(getApplicationContext(), R.color.dark_grey_text);
            switch (status) {
                case PENDING:
                    idIconID = R.drawable.i_d_icon_gray_1;
                    verificationTitleTextColor = ContextCompat.getColor(getApplicationContext(), R.color.dark_grey_text);
                    String pending = getApplicationContext().getString(R.string.identity_verification_pending);
                    String fullDescription = pending + " " + getApplicationContext().getString(R.string.identity_verification_pending_description);
                    SpannableString spannableDescription = new SpannableString(fullDescription);
                    spannableDescription.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getApplicationContext(), R.color.orange_text)), 0, pending.length(), 0);
                    spannableDescription.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getApplicationContext(), R.color.transaction_summary)), pending.length(), fullDescription.length(), 0);
                    this.mBinding.tvVerificationDescription.setText(spannableDescription);
                    paymentIconID = R.drawable.payment_icon_gray_2;
                    paymentTitleTextColor = ContextCompat.getColor(getApplicationContext(), R.color.dark_grey_text);
                    break;
                case COMPLETED:
                    idIconID = R.drawable.check_icon_gray_1;
                    verificationTitleTextColor = ContextCompat.getColor(getApplicationContext(), R.color.dark_grey_text);
                    verificationDescriptionTextColor = ContextCompat.getColor(getApplicationContext(), R.color.transaction_summary);
                    this.mBinding.tvVerificationDescription.setText(getApplicationContext().getString(R.string.identity_verification_verified));
                    this.mBinding.tvVerificationDescription.setTextColor(verificationDescriptionTextColor);
                    paymentIconID = R.drawable.payment_icon_blue_2;
                    paymentTitleTextColor = ContextCompat.getColor(getApplicationContext(), R.color.primary_dark);
                    break;
            }
            this.mBinding.ivIdIcon.setImageResource(idIconID);
            this.mBinding.tvVerificationTitle.setTextColor(verificationTitleTextColor);
            this.mBinding.ivPaymentIcon.setImageResource(paymentIconID);
            this.mBinding.tvPaymentTitle.setTextColor(paymentTitleTextColor);
        }
    }

    void checkJumioProfiles() {
        this.mLoginManager.getClient().getJumioProfiles(new CallbackWithRetrofit<JumioProfiles>() {
            public void onResponse(Call<JumioProfiles> call, Response<JumioProfiles> response, Retrofit retrofit) {
                if (response.isSuccessful()) {
                    PaymentMethodVerificationController.this.status = ((JumioProfiles) response.body()).getJumioProfileStatus();
                    if (PaymentMethodVerificationController.this.status == Status.COMPLETED) {
                        PaymentMethodVerificationController.this.verificationHandler.removeCallbacks(PaymentMethodVerificationController.this.verificationRunnable);
                    }
                    PaymentMethodVerificationController.this.setVerificationState(PaymentMethodVerificationController.this.status);
                    return;
                }
                Utils.showMessage(PaymentMethodVerificationController.this.getApplicationContext(), Utils.getErrorMessage(response, retrofit), 1);
            }

            public void onFailure(Call<JumioProfiles> call, Throwable t) {
                Utils.showMessage(PaymentMethodVerificationController.this.getApplicationContext(), t, 1);
            }
        });
    }
}
