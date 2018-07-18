package com.coinbase.android.pin;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.coinbase.android.ActivityScope;
import com.coinbase.android.BaseActivityModule;
import com.coinbase.android.CoinbaseActivity;
import com.coinbase.android.CoinbaseActivity.RequiresAuthentication;
import com.coinbase.android.ComponentProvider;
import com.coinbase.android.Constants;
import com.coinbase.android.MainActivity;
import com.coinbase.android.R;
import com.coinbase.android.databinding.ActivityPinpromptBinding;
import com.coinbase.android.pin.PINSettingDialogFragment.PINSettingListener;
import com.coinbase.android.ui.FontManager;
import com.coinbase.android.utils.KeyboardUtils;
import com.coinbase.android.utils.Utils;
import com.coinbase.api.LoginManager;
import com.coinbase.api.internal.ApiConstants;
import java.util.Timer;
import java.util.TimerTask;
import javax.inject.Inject;

@ActivityScope
@RequiresAuthentication
public class PINPromptActivity extends CoinbaseActivity implements PINSettingListener {
    public static final String ACTION_CANCEL = "com.coinbase.android.pin.ACTION_CANCEL";
    public static final String ACTION_DISABLE = "com.coinbase.android.pin.ACTION_DISABLE";
    public static final String ACTION_PROMPT = "com.coinbase.android.pin.ACTION_PROMPT";
    public static final String ACTION_SET = "com.coinbase.android.pin.ACTION_SET";
    public static final String FROM_SETTING = "from_setting";
    public static final int MAX_TRIES_ALLOWED = 5;
    private boolean isFromSetting = false;
    private OnClickListener keyboardClickListener = new OnClickListener() {
        public void onClick(View v) {
            PINPromptActivity.this.onKeyPressed(KeyboardUtils.getKeyStroke(v.getId()));
            PINPromptActivity.this.updateUI();
            if (PINPromptActivity.this.mEnteredValue.length() == 4) {
                PINPromptActivity.this.onSubmit();
            }
        }
    };
    private ActivityPinpromptBinding mBinding;
    private String mConfirmValue;
    private String mEnteredValue;
    private boolean mIsConfirmMode = false;
    private boolean mIsSetMode = false;
    @Inject
    LoginManager mLoginManager;
    @Inject
    protected PINManager mPINManager;
    private SharedPreferences mPrefs;
    private ProgressDialog mProgressDialog;
    private Timer mTimer;

    public static class ConfirmCancelDialogFragment extends DialogFragment {
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            TextView message = new TextView(getActivity());
            message.setBackgroundColor(-1);
            message.setTextColor(-16777216);
            message.setTextSize(2, 18.0f);
            int paddingPx = (int) ((15.0f * getResources().getDisplayMetrics().density) + 0.5f);
            message.setPadding(paddingPx, paddingPx, paddingPx, paddingPx);
            message.setText(getMessage());
            Builder builder = new Builder(getActivity());
            builder.setView(message).setPositiveButton(R.string.btn_sign_out, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    ConfirmCancelDialogFragment.this.onUserConfirm();
                }
            }).setNegativeButton(R.string.never_mind, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                }
            });
            return builder.create();
        }

        public String getMessage() {
            return getString(R.string.do_you_want_to_exit_the_app);
        }

        public void onUserConfirm() {
            ((PINPromptActivity) getActivity()).onUserCancel();
        }
    }

    private void onUserCancel() {
        signout();
    }

    public void onCreate(Bundle arg0) {
        boolean z;
        boolean z2 = true;
        super.onCreate(arg0);
        this.mScreenProtector.protect();
        ((ComponentProvider) getApplicationContext()).applicationComponent().coinbaseActivitySubcomponent(new BaseActivityModule(this)).inject(this);
        if (getIntent().getAction() == null || !ACTION_SET.equals(getIntent().getAction())) {
            z = false;
        } else {
            z = true;
        }
        this.mIsSetMode = z;
        if (getIntent().getExtras() == null || !getIntent().getExtras().getBoolean(FROM_SETTING)) {
            z2 = false;
        }
        this.isFromSetting = z2;
        this.mEnteredValue = "";
        this.mBinding = (ActivityPinpromptBinding) DataBindingUtil.setContentView(this, R.layout.activity_pinprompt);
        this.mPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        this.mBinding.tvPinText.setTypeface(FontManager.getFont(this, "Roboto-Light"));
        String action = getIntent().getAction();
        if (ACTION_DISABLE.equals(action) || ACTION_PROMPT.equals(action)) {
            this.mBinding.tvPinText.setText(R.string.pin_text);
        }
        initKeyboard();
    }

    private void initKeyboard() {
        this.mBinding.keypad.llAmountKeyboard0.setOnClickListener(this.keyboardClickListener);
        this.mBinding.keypad.llAmountKeyboard1.setOnClickListener(this.keyboardClickListener);
        this.mBinding.keypad.llAmountKeyboard2.setOnClickListener(this.keyboardClickListener);
        this.mBinding.keypad.llAmountKeyboard3.setOnClickListener(this.keyboardClickListener);
        this.mBinding.keypad.llAmountKeyboard4.setOnClickListener(this.keyboardClickListener);
        this.mBinding.keypad.llAmountKeyboard5.setOnClickListener(this.keyboardClickListener);
        this.mBinding.keypad.llAmountKeyboard6.setOnClickListener(this.keyboardClickListener);
        this.mBinding.keypad.llAmountKeyboard7.setOnClickListener(this.keyboardClickListener);
        this.mBinding.keypad.llAmountKeyboard8.setOnClickListener(this.keyboardClickListener);
        this.mBinding.keypad.llAmountKeyboard9.setOnClickListener(this.keyboardClickListener);
        this.mBinding.keypad.llAmountKeyboardCancel.setOnClickListener(this.keyboardClickListener);
        this.mBinding.keypad.llAmountKeyboardBack.setOnClickListener(this.keyboardClickListener);
    }

    private void updateUI() {
        this.mBinding.ivPinNumber1.setImageResource(R.drawable.pin_circle_unentered);
        this.mBinding.ivPinNumber2.setImageResource(R.drawable.pin_circle_unentered);
        this.mBinding.ivPinNumber3.setImageResource(R.drawable.pin_circle_unentered);
        this.mBinding.ivPinNumber4.setImageResource(R.drawable.pin_circle_unentered);
        int len = this.mEnteredValue.length();
        if (len > 0) {
            this.mBinding.ivPinNumber1.setImageResource(R.drawable.pin_circle_entered);
            if (len > 1) {
                this.mBinding.ivPinNumber2.setImageResource(R.drawable.pin_circle_entered);
                if (len > 2) {
                    this.mBinding.ivPinNumber3.setImageResource(R.drawable.pin_circle_entered);
                    if (len > 3) {
                        this.mBinding.ivPinNumber4.setImageResource(R.drawable.pin_circle_entered);
                    }
                }
            }
        }
    }

    private void onKeyPressed(char index) {
        switch (index) {
            case '-':
                if (this.mIsSetMode) {
                    setResult(0);
                    finish();
                    return;
                }
                new ConfirmCancelDialogFragment().show(getSupportFragmentManager(), "cancel");
                return;
            case '<':
                if (this.mEnteredValue.length() > 0) {
                    this.mEnteredValue = this.mEnteredValue.substring(0, this.mEnteredValue.length() - 1);
                    return;
                }
                return;
            default:
                this.mEnteredValue += index;
                return;
        }
    }

    private void onSubmit() {
        if (!this.mIsSetMode) {
            Editor editor = this.mPrefs.edit();
            if (this.mPINManager.verifyPin(getApplicationContext(), this.mEnteredValue)) {
                onPinEntered(this.mEnteredValue);
                editor.putInt(Constants.KEY_INCORRECT_PIN_TRIES, 0);
                editor.apply();
                return;
            }
            int numTries = this.mPrefs.getInt(Constants.KEY_INCORRECT_PIN_TRIES, 0) + 1;
            this.mEnteredValue = "";
            updateUI();
            int triesLeft = 5 - numTries;
            Utils.showMessage((Context) this, getWarningMessage(triesLeft), 1);
            if (triesLeft <= 0) {
                signout();
                editor.putInt(Constants.KEY_INCORRECT_PIN_TRIES, 0);
            } else {
                editor.putInt(Constants.KEY_INCORRECT_PIN_TRIES, numTries);
            }
            editor.apply();
        } else if (!this.mIsConfirmMode) {
            this.mBinding.tvPinText.setText(R.string.confirm_wallet_passcode);
            this.mIsConfirmMode = true;
            this.mConfirmValue = this.mEnteredValue;
            this.mEnteredValue = "";
            updateUI();
        } else if (this.mEnteredValue.equals(this.mConfirmValue)) {
            onPinEntered(this.mEnteredValue);
        } else {
            this.mEnteredValue = "";
            updateUI();
            this.mIsConfirmMode = false;
            this.mBinding.tvPinText.setText(R.string.setup_pin_text);
            Utils.showMessage((Context) this, (int) R.string.pins_do_not_match, 1);
        }
    }

    private String getWarningMessage(int triesLeft) {
        if (triesLeft > 1) {
            return String.format(getString(R.string.pin_incorrect), new Object[]{Integer.valueOf(triesLeft)});
        } else if (triesLeft == 1) {
            return getString(R.string.pin_incorrect_last_try);
        } else {
            return getString(R.string.pin_incorrect_signing_out);
        }
    }

    private void onPinEntered(String pin) {
        if (this.mIsSetMode) {
            this.mPINManager.setPin(this, pin);
        }
        this.mPINManager.setPinEntered(getApplicationContext(), true);
        if (this.isFromSetting) {
            PINSettingDialogFragment.newInstance(this.mIsSetMode).show(getSupportFragmentManager(), ApiConstants.PIN);
            return;
        }
        setResult(-1);
        finish();
    }

    public void finish() {
        super.finish();
        if (!this.mIsSetMode) {
            overridePendingTransition(R.anim.pin_stop_enter, R.anim.pin_stop_exit);
        }
    }

    public void onBackPressed() {
        if (this.mIsSetMode) {
            setResult(0);
            super.onBackPressed();
        } else {
            new ConfirmCancelDialogFragment().show(getSupportFragmentManager(), "cancel");
        }
        if (this.mPrefs.getBoolean(MainActivity.ACTION_URI_TRANSFER, false)) {
            this.mPrefs.edit().putBoolean(MainActivity.ACTION_URI_TRANSFER, false).apply();
            this.mPrefs.edit().putBoolean(ACTION_CANCEL, true).apply();
        }
    }

    private void signout() {
        if (this.mTimer == null) {
            this.mTimer = new Timer();
        }
        if (this.mProgressDialog == null) {
            this.mProgressDialog = ProgressDialog.show(this, "", getString(R.string.signing_out));
        }
        this.mTimer.schedule(new TimerTask() {
            public void run() {
                if (PINPromptActivity.this.mLoginManager.signout()) {
                    Utils.dismissDialog(PINPromptActivity.this.mProgressDialog);
                    PINPromptActivity.this.redirectToIntro();
                    return;
                }
                PINPromptActivity.this.signout();
            }
        }, 100);
    }

    public void onDismiss() {
        finish();
    }
}
