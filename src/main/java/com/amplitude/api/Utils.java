package com.amplitude.api;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Utils {
    private static AmplitudeLog logger = AmplitudeLog.getLogger();

    static JSONObject cloneJSONObject(JSONObject obj) {
        if (obj == null) {
            return null;
        }
        JSONArray nameArray = null;
        try {
            nameArray = obj.names();
        } catch (ArrayIndexOutOfBoundsException e) {
            logger.e("com.amplitude.api.Utils", e.toString());
        }
        int len = nameArray != null ? nameArray.length() : 0;
        String[] names = new String[len];
        for (int i = 0; i < len; i++) {
            names[i] = nameArray.optString(i);
        }
        try {
            return new JSONObject(obj, names);
        } catch (JSONException e2) {
            logger.e("com.amplitude.api.Utils", e2.toString());
            return null;
        }
    }

    public static boolean isEmptyString(String s) {
        return s == null || s.length() == 0;
    }

    static String normalizeInstanceName(String instance) {
        if (isEmptyString(instance)) {
            instance = "$default_instance";
        }
        return instance.toLowerCase();
    }
}
