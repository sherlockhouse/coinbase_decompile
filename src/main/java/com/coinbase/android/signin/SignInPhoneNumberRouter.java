package com.coinbase.android.signin;

import android.os.Bundle;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.ui.ActionBarController;
import java.util.ArrayList;
import javax.inject.Inject;

@ControllerScope
public class SignInPhoneNumberRouter {
    private final ActionBarController mController;

    @Inject
    public SignInPhoneNumberRouter(ActionBarController controller) {
        this.mController = controller;
    }

    public void showVerifyPhoneDialogController(String id, String phone, String maskedPhoneNumber) {
        Bundle args = new Bundle();
        args.putString("phone_id", id);
        args.putString("phone_number", phone);
        args.putString(SignInVerifyPhoneNumberPresenter.MASKED_PHONE_NUMBER, maskedPhoneNumber);
        this.mController.pushModalController(new SignInVerifyPhoneNumberController(this.mController.appendModalArgs(args)));
    }

    public void showVerifyPhoneDialogControllerMultipleNumbers(ArrayList<String> ids) {
        Bundle args = new Bundle();
        args.putStringArrayList(SignInVerifyPhoneNumberPresenter.PHONE_IDS, ids);
        this.mController.pushModalController(new SignInVerifyPhoneNumberController(this.mController.appendModalArgs(args)));
    }
}
