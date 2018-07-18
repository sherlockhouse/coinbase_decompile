package com.amplitude.api;

import java.util.HashSet;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

public class Identify {
    protected Set<String> userProperties = new HashSet();
    protected JSONObject userPropertiesOperations = new JSONObject();

    private void addToUserProperties(String operation, String property, Object value) {
        if (Utils.isEmptyString(property)) {
            AmplitudeLog.getLogger().w("com.amplitude.api.Identify", String.format("Attempting to perform operation %s with a null or empty string property, ignoring", new Object[]{operation}));
        } else if (value == null) {
            AmplitudeLog.getLogger().w("com.amplitude.api.Identify", String.format("Attempting to perform operation %s with null value for property %s, ignoring", new Object[]{operation, property}));
        } else if (this.userPropertiesOperations.has("$clearAll")) {
            AmplitudeLog.getLogger().w("com.amplitude.api.Identify", String.format("This Identify already contains a $clearAll operation, ignoring operation %s", new Object[]{operation}));
        } else if (this.userProperties.contains(property)) {
            AmplitudeLog.getLogger().w("com.amplitude.api.Identify", String.format("Already used property %s in previous operation, ignoring operation %s", new Object[]{property, operation}));
        } else {
            try {
                if (!this.userPropertiesOperations.has(operation)) {
                    this.userPropertiesOperations.put(operation, new JSONObject());
                }
                this.userPropertiesOperations.getJSONObject(operation).put(property, value);
                this.userProperties.add(property);
            } catch (JSONException e) {
                AmplitudeLog.getLogger().e("com.amplitude.api.Identify", e.toString());
            }
        }
    }

    Identify setUserProperty(String property, Object value) {
        addToUserProperties("$set", property, value);
        return this;
    }
}
