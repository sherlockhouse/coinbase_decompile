package io.fabric.sdk.android.services.settings;

import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import android.text.TextUtils;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import io.fabric.sdk.android.services.common.AbstractSpiCall;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.network.HttpMethod;
import io.fabric.sdk.android.services.network.HttpRequest;
import io.fabric.sdk.android.services.network.HttpRequest.HttpRequestException;
import io.fabric.sdk.android.services.network.HttpRequestFactory;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

class DefaultSettingsSpiCall extends AbstractSpiCall implements SettingsSpiCall {
    public DefaultSettingsSpiCall(Kit kit, String protocolAndHostOverride, String url, HttpRequestFactory requestFactory) {
        this(kit, protocolAndHostOverride, url, requestFactory, HttpMethod.GET);
    }

    DefaultSettingsSpiCall(Kit kit, String protocolAndHostOverride, String url, HttpRequestFactory requestFactory, HttpMethod method) {
        super(kit, protocolAndHostOverride, url, requestFactory, method);
    }

    public JSONObject invoke(SettingsRequest requestData) {
        JSONObject toReturn;
        HttpRequest httpRequest = null;
        try {
            Map<String, String> queryParams = getQueryParamsFor(requestData);
            httpRequest = applyHeadersTo(getHttpRequest(queryParams), requestData);
            Fabric.getLogger().d("Fabric", "Requesting settings from " + getUrl());
            Fabric.getLogger().d("Fabric", "Settings query params were: " + queryParams);
            toReturn = handleResponse(httpRequest);
            if (httpRequest != null) {
                Fabric.getLogger().d("Fabric", "Settings request ID: " + httpRequest.header("X-REQUEST-ID"));
            }
        } catch (HttpRequestException e) {
            Fabric.getLogger().e("Fabric", "Settings request failed.", e);
            toReturn = null;
            if (httpRequest != null) {
                Fabric.getLogger().d("Fabric", "Settings request ID: " + httpRequest.header("X-REQUEST-ID"));
            }
        } catch (Throwable th) {
            if (httpRequest != null) {
                Fabric.getLogger().d("Fabric", "Settings request ID: " + httpRequest.header("X-REQUEST-ID"));
            }
        }
        return toReturn;
    }

    JSONObject handleResponse(HttpRequest httpRequest) {
        int statusCode = httpRequest.code();
        Fabric.getLogger().d("Fabric", "Settings result was: " + statusCode);
        if (requestWasSuccessful(statusCode)) {
            return getJsonObjectFrom(httpRequest.body());
        }
        Fabric.getLogger().e("Fabric", "Failed to retrieve settings from " + getUrl());
        return null;
    }

    boolean requestWasSuccessful(int httpStatusCode) {
        return httpStatusCode == Callback.DEFAULT_DRAG_ANIMATION_DURATION || httpStatusCode == 201 || httpStatusCode == 202 || httpStatusCode == 203;
    }

    private JSONObject getJsonObjectFrom(String httpRequestBody) {
        try {
            return new JSONObject(httpRequestBody);
        } catch (Exception e) {
            Fabric.getLogger().d("Fabric", "Failed to parse settings JSON from " + getUrl(), e);
            Fabric.getLogger().d("Fabric", "Settings response " + httpRequestBody);
            return null;
        }
    }

    private Map<String, String> getQueryParamsFor(SettingsRequest requestData) {
        Map<String, String> queryParams = new HashMap();
        queryParams.put("build_version", requestData.buildVersion);
        queryParams.put("display_version", requestData.displayVersion);
        queryParams.put("source", Integer.toString(requestData.source));
        if (requestData.iconHash != null) {
            queryParams.put("icon_hash", requestData.iconHash);
        }
        String instanceId = requestData.instanceId;
        if (!CommonUtils.isNullOrEmpty(instanceId)) {
            queryParams.put("instance", instanceId);
        }
        return queryParams;
    }

    private HttpRequest applyHeadersTo(HttpRequest request, SettingsRequest requestData) {
        applyNonNullHeader(request, "X-CRASHLYTICS-API-KEY", requestData.apiKey);
        applyNonNullHeader(request, "X-CRASHLYTICS-API-CLIENT-TYPE", "android");
        applyNonNullHeader(request, "X-CRASHLYTICS-API-CLIENT-VERSION", this.kit.getVersion());
        applyNonNullHeader(request, "Accept", "application/json");
        applyNonNullHeader(request, "X-CRASHLYTICS-DEVICE-MODEL", requestData.deviceModel);
        applyNonNullHeader(request, "X-CRASHLYTICS-OS-BUILD-VERSION", requestData.osBuildVersion);
        applyNonNullHeader(request, "X-CRASHLYTICS-OS-DISPLAY-VERSION", requestData.osDisplayVersion);
        applyNonNullHeader(request, "X-CRASHLYTICS-INSTALLATION-ID", requestData.installationId);
        if (TextUtils.isEmpty(requestData.advertisingId)) {
            applyNonNullHeader(request, "X-CRASHLYTICS-ANDROID-ID", requestData.androidId);
        } else {
            applyNonNullHeader(request, "X-CRASHLYTICS-ADVERTISING-TOKEN", requestData.advertisingId);
        }
        return request;
    }

    private void applyNonNullHeader(HttpRequest request, String key, String value) {
        if (value != null) {
            request.header(key, value);
        }
    }
}
