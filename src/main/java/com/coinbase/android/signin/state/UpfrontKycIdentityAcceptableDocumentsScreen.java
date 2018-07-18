package com.coinbase.android.signin.state;

import java.util.List;
import permissions.dispatcher.PermissionRequest;

public interface UpfrontKycIdentityAcceptableDocumentsScreen {
    void hideProgress();

    void initializeDocsList(List<String> list, List<Integer> list2);

    boolean isShown();

    void requestPermissions(String[] strArr, int i);

    void showRationaleDialog(int i, int i2, PermissionRequest permissionRequest);
}
