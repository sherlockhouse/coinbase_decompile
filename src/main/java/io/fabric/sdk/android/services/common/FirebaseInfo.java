package io.fabric.sdk.android.services.common;

import android.content.Context;
import android.text.TextUtils;
import io.fabric.sdk.android.Fabric;

public class FirebaseInfo {
    protected String getApiKeyFromFirebaseAppId(Context context) {
        int id = CommonUtils.getResourcesIdentifier(context, "google_app_id", "string");
        if (id == 0) {
            return null;
        }
        Fabric.getLogger().d("Fabric", "Generating Crashlytics ApiKey from google_app_id in Strings");
        return createApiKeyFromFirebaseAppId(context.getResources().getString(id));
    }

    protected String createApiKeyFromFirebaseAppId(String appId) {
        return CommonUtils.sha256(appId).substring(0, 40);
    }

    public boolean isFirebaseCrashlyticsEnabled(Context context) {
        if (CommonUtils.getBooleanResourceValue(context, "com.crashlytics.useFirebaseAppId", false)) {
            return true;
        }
        boolean hasGoogleAppId;
        if (CommonUtils.getResourcesIdentifier(context, "google_app_id", "string") != 0) {
            hasGoogleAppId = true;
        } else {
            hasGoogleAppId = false;
        }
        boolean hasApiKey;
        if (TextUtils.isEmpty(new ApiKey().getApiKeyFromManifest(context)) && TextUtils.isEmpty(new ApiKey().getApiKeyFromStrings(context))) {
            hasApiKey = false;
        } else {
            hasApiKey = true;
        }
        if (!hasGoogleAppId || hasApiKey) {
            return false;
        }
        return true;
    }
}
