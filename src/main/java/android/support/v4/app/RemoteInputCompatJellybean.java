package android.support.v4.app;

import android.os.Bundle;
import android.support.v4.app.RemoteInputCompatBase.RemoteInput;
import com.coinbase.android.utils.CryptoUri;
import java.util.ArrayList;
import java.util.Set;

class RemoteInputCompatJellybean {
    static Bundle toBundle(RemoteInput remoteInput) {
        Bundle data = new Bundle();
        data.putString("resultKey", remoteInput.getResultKey());
        data.putCharSequence(CryptoUri.LABEL, remoteInput.getLabel());
        data.putCharSequenceArray("choices", remoteInput.getChoices());
        data.putBoolean("allowFreeFormInput", remoteInput.getAllowFreeFormInput());
        data.putBundle("extras", remoteInput.getExtras());
        Set<String> allowedDataTypes = remoteInput.getAllowedDataTypes();
        if (!(allowedDataTypes == null || allowedDataTypes.isEmpty())) {
            ArrayList<String> allowedDataTypesAsList = new ArrayList(allowedDataTypes.size());
            for (String type : allowedDataTypes) {
                allowedDataTypesAsList.add(type);
            }
            data.putStringArrayList("allowedDataTypes", allowedDataTypesAsList);
        }
        return data;
    }

    static Bundle[] toBundleArray(RemoteInput[] remoteInputs) {
        if (remoteInputs == null) {
            return null;
        }
        Bundle[] bundles = new Bundle[remoteInputs.length];
        for (int i = 0; i < remoteInputs.length; i++) {
            bundles[i] = toBundle(remoteInputs[i]);
        }
        return bundles;
    }
}
