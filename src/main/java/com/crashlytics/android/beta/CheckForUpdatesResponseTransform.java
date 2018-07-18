package com.crashlytics.android.beta;

import java.io.IOException;
import org.json.JSONObject;

class CheckForUpdatesResponseTransform {
    CheckForUpdatesResponseTransform() {
    }

    public CheckForUpdatesResponse fromJson(JSONObject json) throws IOException {
        if (json == null) {
            return null;
        }
        return new CheckForUpdatesResponse(json.optString("url", null), json.optString("version_string", null), json.optString("display_version", null), json.optString("build_version", null), json.optString("identifier", null), json.optString("instance_identifier", null));
    }
}
