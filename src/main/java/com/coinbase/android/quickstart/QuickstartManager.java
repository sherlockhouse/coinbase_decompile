package com.coinbase.android.quickstart;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.widget.RelativeLayout;
import com.coinbase.ApiConstants;
import com.coinbase.CallbackWithRetrofit;
import com.coinbase.android.Constants;
import com.coinbase.android.R;
import com.coinbase.android.databinding.ListItemQuickstartBinding;
import com.coinbase.android.identityverification.InAppIdentityAcceptableDocumentsController;
import com.coinbase.android.paymentmethods.GetPaymentMethodsTaskRx;
import com.coinbase.android.paymentmethods.PaymentMethodsController;
import com.coinbase.android.paymentmethods.PlaidAccountLoginActivity;
import com.coinbase.android.phone.PhoneNumbersUpdatedConnector;
import com.coinbase.android.phone.VerifyPhoneHandler;
import com.coinbase.android.phone.VerifyPhoneHandler.VerifyPhoneCaller;
import com.coinbase.android.splittesting.SplitTestConstants;
import com.coinbase.android.splittesting.SplitTesting;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.android.ui.MainScheduler;
import com.coinbase.android.utils.PreferenceUtils;
import com.coinbase.android.utils.Utils;
import com.coinbase.api.LoginManager;
import com.coinbase.api.internal.models.jumio.JumioProfiles;
import com.coinbase.api.internal.models.phoneNumber.PhoneNumbers;
import com.coinbase.api.internal.models.policyRestrictions.PolicyRestrictions;
import com.coinbase.api.internal.models.policyRestrictions.Restrictions;
import com.coinbase.v2.models.paymentMethods.Data;
import com.coinbase.v2.models.paymentMethods.Data.CDVStatus;
import com.coinbase.v2.models.paymentMethods.PaymentMethods;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Scheduler;
import rx.exceptions.Exceptions;

public class QuickstartManager {
    private Context mContext;
    private ActionBarController mController;
    private GetPaymentMethodsTaskRx mGetPaymentMethodsTask;
    private LoginManager mLoginManager;
    private Scheduler mMainScheduler;
    private PhoneNumbersUpdatedConnector mPhoneNumbersUpdatedConnector;
    private SplitTesting mSplitTesting;

    public enum Type {
        BUYS("buys"),
        SELLS(ApiConstants.SELLS),
        DEPOSITS(ApiConstants.DEPOSITS),
        WITHDRAWALS(ApiConstants.WITHDRAWALS),
        SENDS("sends"),
        RECEIVES("receives");
        
        private String _value;

        private Type(String value) {
            this._value = value;
        }

        public static Type fromString(String text) {
            if (text != null) {
                for (Type str : values()) {
                    if (text.equalsIgnoreCase(str.toString())) {
                        return str;
                    }
                }
            }
            return BUYS;
        }

        public String toString() {
            return this._value;
        }
    }

    public enum UserType {
        NEW(SplitTestConstants.LINK_BANK_ACCOUNT_CHANGES_ENABLED),
        ALL("all");
        
        private String _value;

        private UserType(String value) {
            this._value = value;
        }

        public String toString() {
            return this._value;
        }
    }

    public enum VerificationType {
        EMAIL("email"),
        PHONE("phone"),
        PERSONAL_DETAILS("personal_details"),
        JUMIO("jumio"),
        VOGOGO("vogogo"),
        PAYMENT_METHOD_REQUIRED("payment_method_required"),
        ZERO_LIMIT("zero_limit"),
        REGION_UNSUPPORTED("region_unsupported"),
        UNSUPPORTED("unsupported"),
        DEPOSIT_REQUIRED("deposit_required"),
        JUMIO_FACE_MATCH("jumio_face_match"),
        STATE_UNSUPPORTED("state_unsupported");
        
        private String _value;

        private VerificationType(String value) {
            this._value = value;
        }

        public static VerificationType fromString(String text) {
            if (text != null) {
                for (VerificationType str : values()) {
                    if (text.equalsIgnoreCase(str.toString())) {
                        return str;
                    }
                }
            }
            return UNSUPPORTED;
        }

        public String toString() {
            return this._value;
        }
    }

    public QuickstartManager(LoginManager loginManager, Context context, PhoneNumbersUpdatedConnector phoneNumbersUpdatedConnector, GetPaymentMethodsTaskRx getPaymentMethodsTask, @MainScheduler Scheduler mainScheduler, ActionBarController controller, SplitTesting splitTesting) {
        this.mLoginManager = loginManager;
        this.mContext = context;
        this.mPhoneNumbersUpdatedConnector = phoneNumbersUpdatedConnector;
        this.mGetPaymentMethodsTask = getPaymentMethodsTask;
        this.mMainScheduler = mainScheduler;
        this.mController = controller;
        this.mSplitTesting = splitTesting;
    }

    public static ListItemQuickstartBinding prepareItemView(Context context, QuickstartItem item) {
        ListItemQuickstartBinding binding = (ListItemQuickstartBinding) DataBindingUtil.bind((RelativeLayout) View.inflate(context, R.layout.list_item_quickstart, null));
        binding.tvQuickstartItemTitle.setText(item.getTitleResource());
        binding.ivQuickstartItemIcon.setImageResource(item.getIconResource());
        return binding;
    }

    public List<QuickstartItem> getCachedQuickstartItems() {
        return getCachedQuickstartItems(false);
    }

    public List<QuickstartItem> getCachedQuickstartItems(boolean includeCancelled) {
        List<QuickstartItem> items = new ArrayList();
        for (QuickstartItem item : QuickstartItem.values()) {
            boolean cancelled = PreferenceUtils.getPrefsBool(this.mContext, String.format(Constants.KEY_QUICKSTART_ITEM_CANCELLED, new Object[]{item.name()}), false);
            boolean show = PreferenceUtils.getPrefsBool(this.mContext, String.format(Constants.KEY_QUICKSTART_ITEM_SHOW, new Object[]{item.name()}), false);
            if ((!cancelled || includeCancelled) && show) {
                if (item.getTitleResource() == QuickstartItem.VERIFY_PHONE_NUMBER.getTitleResource()) {
                    items.add(0, item);
                } else {
                    items.add(item);
                }
            }
        }
        return items;
    }

    public Observable<Void> updateQuickstartItems(String type) {
        return this.mLoginManager.getClient().getPolicyRestrictionsRX(type).observeOn(this.mMainScheduler).first().map(QuickstartManager$$Lambda$1.lambdaFactory$(this, new ArrayList(), Type.fromString(type)));
    }

    static /* synthetic */ Void lambda$updateQuickstartItems$0(QuickstartManager this_, List items, Type chosenType, Pair pair) {
        Response<Restrictions> response = pair.first;
        if (!response.isSuccessful()) {
            throw Exceptions.propagate(new Exception("Error getting policy restrictions"));
        } else if (((Restrictions) response.body()).getData() == null) {
            throw Exceptions.propagate(new Exception("Error getting policy restrictions"));
        } else {
            this_.insertQuickstartItems(((Restrictions) response.body()).getData().getPolicyRestrictions(), items, chosenType);
            this_.saveQuickstartItems(items);
            return null;
        }
    }

    public boolean isGoToSettingsEnabled(SplitTesting splitTesting) {
        if (splitTesting == null) {
            return false;
        }
        return splitTesting.isInGroup(SplitTestConstants.SETTINGS_PAYMENT_METHODS_SPLIT_TEST, SplitTestConstants.SETTINGS_PAYMENT_METHODS_ENABLED);
    }

    private void insertQuickstartItems(List<PolicyRestrictions> restrictions, List<QuickstartItem> items, Type type) {
        for (PolicyRestrictions restriction : restrictions) {
            if (TextUtils.equals(restriction.getUserType(), UserType.NEW.toString()) && restriction.getRequired().booleanValue()) {
                switch (VerificationType.fromString(restriction.getId())) {
                    case EMAIL:
                    case PERSONAL_DETAILS:
                    case VOGOGO:
                        break;
                    case REGION_UNSUPPORTED:
                    case STATE_UNSUPPORTED:
                        items.add(QuickstartItem.REGION_UNSUPPORTED);
                        break;
                    case PHONE:
                        items.add(QuickstartItem.VERIFY_PHONE_NUMBER);
                        break;
                    case PAYMENT_METHOD_REQUIRED:
                        if (!isGoToSettingsEnabled(this.mSplitTesting)) {
                            if (!this.mLoginManager.getActiveUserCountryCode().equalsIgnoreCase("SG") || type != Type.SELLS) {
                                if (!restriction.getPending().booleanValue()) {
                                    items.add(QuickstartItem.ADD_PAYMENT_METHOD);
                                    break;
                                } else {
                                    items.add(QuickstartItem.COMPLETE_CDV);
                                    break;
                                }
                            }
                            items.add(QuickstartItem.ADD_PAYMENT_VIA_WEBSITE);
                            break;
                        }
                        break;
                        break;
                    case DEPOSIT_REQUIRED:
                        items.add(QuickstartItem.DEPOSIT_FUNDS);
                        break;
                    case JUMIO:
                    case ZERO_LIMIT:
                        if (!restriction.getPending().booleanValue()) {
                            items.add(QuickstartItem.VERIFY_IDENTITY);
                            break;
                        } else {
                            items.add(QuickstartItem.VERIFY_IDENTITY_PENDING);
                            break;
                        }
                    case JUMIO_FACE_MATCH:
                        items.add(QuickstartItem.VERIFY_IDENTITY_FACEMATCH);
                        break;
                    default:
                        items.add(QuickstartItem.ADD_PAYMENT_VIA_WEBSITE);
                        break;
                }
            }
        }
        removeDuplicateJumioItems(items);
    }

    private void saveQuickstartItems(List<QuickstartItem> items) {
        for (QuickstartItem item : QuickstartItem.values()) {
            PreferenceUtils.putPrefsBool(this.mContext, String.format(Constants.KEY_QUICKSTART_ITEM_SHOW, new Object[]{item.name()}), items.contains(item));
        }
    }

    public void performActionForItem(QuickstartItem item, Activity activity, VerifyPhoneCaller verifyPhoneCaller) {
        switch (item) {
            case ADD_PAYMENT_METHOD:
                Bundle args = new Bundle();
                args.putBoolean(PaymentMethodsController.INTENDING_TO_ADD_PAYMENT_METHOD, true);
                this.mController.pushModalController(new PaymentMethodsController(this.mController.appendModalArgs(args)));
                return;
            case VERIFY_PHONE_NUMBER:
                getPhoneNumbersAndShowVerify(verifyPhoneCaller);
                return;
            case VERIFY_IDENTITY:
                performJumioAction(activity);
                return;
            case VERIFY_IDENTITY_FACEMATCH:
                performJumioFaceMatchAction();
                return;
            case VERIFY_IDENTITY_PENDING:
                Utils.showMessage(this.mContext, (int) R.string.jumio_pending_message, 1);
                return;
            case COMPLETE_CDV:
                showCdvVerification(activity);
                return;
            case ADD_PAYMENT_VIA_WEBSITE:
            case REGION_UNSUPPORTED:
                activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://www.coinbase.com")));
                return;
            default:
                return;
        }
    }

    private void performJumioFaceMatchAction() {
        this.mController.pushModalController(new InAppIdentityAcceptableDocumentsController(this.mController.appendModalArgs(new Bundle())));
    }

    private void performJumioAction(final Activity activity) {
        final ProgressDialog dialog = ProgressDialog.show(activity, "", this.mContext.getString(R.string.please_wait));
        this.mLoginManager.getClient().getJumioProfiles(new CallbackWithRetrofit<JumioProfiles>() {
            public void onResponse(Call<JumioProfiles> call, Response<JumioProfiles> response, Retrofit retrofit) {
                Utils.dismissDialog(dialog);
                if (response.isSuccessful()) {
                    switch (((JumioProfiles) response.body()).getJumioProfileStatus()) {
                        case FAILED:
                        case INCOMPLETE:
                            QuickstartManager.this.mController.pushModalController(new InAppIdentityAcceptableDocumentsController(QuickstartManager.this.mController.appendModalArgs(new Bundle())));
                            return;
                        case PENDING:
                            new Builder(QuickstartManager.this.mContext).setTitle(QuickstartManager.this.mContext.getString(R.string.identity_verification)).setMessage(QuickstartManager.this.mContext.getString(R.string.identity_verification_pending_description)).setPositiveButton(17039379, new OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            }).setIcon(17301543).show();
                            return;
                        default:
                            return;
                    }
                }
                Utils.showMessage(activity, Utils.getErrorMessage(response, retrofit), 1);
            }

            public void onFailure(Call<JumioProfiles> call, Throwable t) {
                Utils.dismissDialog(dialog);
                Utils.showMessage(activity, t, 1);
            }
        });
    }

    private void showCdvVerification(Activity activity) {
        this.mGetPaymentMethodsTask.getPaymentMethodsExcludeLimits().first().observeOn(this.mMainScheduler).subscribe(QuickstartManager$$Lambda$2.lambdaFactory$(this, ProgressDialog.show(activity, "", this.mContext.getString(R.string.quickstart_loading_cdv)), activity), QuickstartManager$$Lambda$3.lambdaFactory$());
    }

    static /* synthetic */ void lambda$showCdvVerification$1(QuickstartManager this_, ProgressDialog dialog, final Activity activity, Pair pair) {
        if (pair.first.isSuccessful()) {
            List<Data> paymentMethods = ((PaymentMethods) ((Response) pair.first).body()).getData();
            Utils.dismissDialog(dialog);
            boolean didFindCDV = false;
            for (final Data method : paymentMethods) {
                if (!method.getVerified().booleanValue() && method.getCdvStatus() == CDVStatus.IN_PROGRESS) {
                    activity.runOnUiThread(new Runnable() {
                        public void run() {
                            Intent intent = new Intent(activity, PlaidAccountLoginActivity.class);
                            intent.putExtra("payment_method", new Gson().toJson(method));
                            intent.putExtra(PlaidAccountLoginActivity.MANUAL, true);
                            activity.startActivity(intent);
                        }
                    });
                    didFindCDV = true;
                    break;
                }
            }
            if (!didFindCDV) {
                Bundle args = new Bundle();
                args.putBoolean(PaymentMethodsController.INTENDING_TO_ADD_PAYMENT_METHOD, true);
                this_.mController.pushModalController(new PaymentMethodsController(this_.mController.appendModalArgs(args)));
            }
        }
    }

    static /* synthetic */ void lambda$showCdvVerification$2(Throwable t) {
    }

    private void getPhoneNumbersAndShowVerify(VerifyPhoneCaller verifyPhoneCaller) {
        this.mLoginManager.getClient().getPhoneNumbersRx().first().observeOn(this.mMainScheduler).subscribe(QuickstartManager$$Lambda$4.lambdaFactory$(this, verifyPhoneCaller), QuickstartManager$$Lambda$5.lambdaFactory$(this, verifyPhoneCaller));
    }

    static /* synthetic */ void lambda$getPhoneNumbersAndShowVerify$3(QuickstartManager this_, VerifyPhoneCaller verifyPhoneCaller, Pair pair) {
        Response<PhoneNumbers> response = pair.first;
        if (!response.isSuccessful()) {
            this_.showAddPhoneNumber(verifyPhoneCaller);
        }
        List<com.coinbase.api.internal.models.phoneNumber.Data> phoneNumbers = ((PhoneNumbers) response.body()).getData();
        if (phoneNumbers == null || phoneNumbers.isEmpty() || Utils.isPhoneVerified(phoneNumbers)) {
            this_.showAddPhoneNumber(verifyPhoneCaller);
        } else {
            this_.showVerifyPhoneNumber((com.coinbase.api.internal.models.phoneNumber.Data) phoneNumbers.get(0), verifyPhoneCaller);
        }
    }

    private void showAddPhoneNumber(VerifyPhoneCaller verifyPhoneCaller) {
        if (verifyPhoneCaller.isForeground()) {
            new VerifyPhoneHandler(verifyPhoneCaller, this.mPhoneNumbersUpdatedConnector).addPhone();
        }
    }

    private void showVerifyPhoneNumber(com.coinbase.api.internal.models.phoneNumber.Data phone, VerifyPhoneCaller verifyPhoneCaller) {
        if (verifyPhoneCaller.isForeground()) {
            new VerifyPhoneHandler(verifyPhoneCaller, this.mPhoneNumbersUpdatedConnector).showVerifyPhoneDialogFragment(phone.getId(), phone.getNumber(), null, null);
        }
    }

    private void removeDuplicateJumioItems(List<QuickstartItem> items) {
        if (items.contains(QuickstartItem.VERIFY_IDENTITY_FACEMATCH)) {
            items.removeAll(Collections.singletonList(QuickstartItem.VERIFY_IDENTITY));
        }
    }
}
