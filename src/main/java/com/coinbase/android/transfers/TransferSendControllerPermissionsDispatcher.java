package com.coinbase.android.transfers;

import java.lang.ref.WeakReference;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.PermissionUtils;

public class TransferSendControllerPermissionsDispatcher {
    private static final String[] PERMISSION_ONCONTACTSPERMISSIONGRANTED = new String[]{"android.permission.READ_CONTACTS"};
    private static final String[] PERMISSION_STARTQRSCAN = new String[]{"android.permission.CAMERA"};
    private static final int REQUEST_ONCONTACTSPERMISSIONGRANTED = 1;
    private static final int REQUEST_STARTQRSCAN = 0;

    private static final class StartQrScanPermissionRequest implements PermissionRequest {
        private final WeakReference<TransferSendController> weakTarget;

        private StartQrScanPermissionRequest(TransferSendController target) {
            this.weakTarget = new WeakReference(target);
        }

        public void proceed() {
            TransferSendController target = (TransferSendController) this.weakTarget.get();
            if (target != null) {
                target.requestPermissions(TransferSendControllerPermissionsDispatcher.PERMISSION_STARTQRSCAN, 0);
            }
        }

        public void cancel() {
            TransferSendController target = (TransferSendController) this.weakTarget.get();
            if (target != null) {
                target.showDeniedForCamera();
            }
        }
    }

    private TransferSendControllerPermissionsDispatcher() {
    }

    static void startQrScanWithCheck(TransferSendController target) {
        if (PermissionUtils.hasSelfPermissions(target.getActivity(), PERMISSION_STARTQRSCAN)) {
            target.startQrScan();
        } else if (PermissionUtils.shouldShowRequestPermissionRationale(target.getActivity(), PERMISSION_STARTQRSCAN)) {
            target.showRationaleForCamera(new StartQrScanPermissionRequest(target));
        } else {
            target.requestPermissions(PERMISSION_STARTQRSCAN, 0);
        }
    }

    static void onContactsPermissionGrantedWithCheck(TransferSendController target) {
        if (PermissionUtils.hasSelfPermissions(target.getActivity(), PERMISSION_ONCONTACTSPERMISSIONGRANTED)) {
            target.onContactsPermissionGranted();
        } else {
            target.requestPermissions(PERMISSION_ONCONTACTSPERMISSIONGRANTED, 1);
        }
    }

    static void onRequestPermissionsResult(TransferSendController target, int requestCode, int[] grantResults) {
        switch (requestCode) {
            case 0:
                if (PermissionUtils.getTargetSdkVersion(target.getActivity()) < 23 && !PermissionUtils.hasSelfPermissions(target.getActivity(), PERMISSION_STARTQRSCAN)) {
                    target.showDeniedForCamera();
                    return;
                } else if (PermissionUtils.verifyPermissions(grantResults)) {
                    target.startQrScan();
                    return;
                } else {
                    target.showDeniedForCamera();
                    return;
                }
            case 1:
                if (PermissionUtils.getTargetSdkVersion(target.getActivity()) < 23 && !PermissionUtils.hasSelfPermissions(target.getActivity(), PERMISSION_ONCONTACTSPERMISSIONGRANTED)) {
                    target.showDeniedForReadContacts();
                    return;
                } else if (PermissionUtils.verifyPermissions(grantResults)) {
                    target.onContactsPermissionGranted();
                    return;
                } else {
                    target.showDeniedForReadContacts();
                    return;
                }
            default:
                return;
        }
    }
}
