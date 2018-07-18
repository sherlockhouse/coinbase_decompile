package io.fabric.sdk.android.services.settings;

import io.fabric.sdk.android.services.common.CurrentTimeProvider;
import org.json.JSONException;
import org.json.JSONObject;

class DefaultSettingsJsonTransform implements SettingsJsonTransform {
    DefaultSettingsJsonTransform() {
    }

    public SettingsData buildFromJson(CurrentTimeProvider currentTimeProvider, JSONObject json) throws JSONException {
        int settingsVersion = json.optInt("settings_version", 0);
        int cacheDuration = json.optInt("cache_duration", 3600);
        return new SettingsData(getExpiresAtFrom(currentTimeProvider, (long) cacheDuration, json), buildAppDataFrom(json.getJSONObject("app")), buildSessionDataFrom(json.getJSONObject("session")), buildPromptDataFrom(json.getJSONObject("prompt")), buildFeaturesSessionDataFrom(json.getJSONObject("features")), buildAnalyticsSessionDataFrom(json.getJSONObject("analytics")), buildBetaSettingsDataFrom(json.getJSONObject("beta")), settingsVersion, cacheDuration);
    }

    private AppSettingsData buildAppDataFrom(JSONObject json) throws JSONException {
        String identifier = json.getString("identifier");
        String status = json.getString("status");
        String url = json.getString("url");
        String reportsUrl = json.getString("reports_url");
        String ndkReportsUrl = json.getString("ndk_reports_url");
        boolean updateRequired = json.optBoolean("update_required", false);
        AppIconSettingsData icon = null;
        if (json.has("icon") && json.getJSONObject("icon").has("hash")) {
            icon = buildIconDataFrom(json.getJSONObject("icon"));
        }
        return new AppSettingsData(identifier, status, url, reportsUrl, ndkReportsUrl, updateRequired, icon);
    }

    private AppIconSettingsData buildIconDataFrom(JSONObject iconJson) throws JSONException {
        return new AppIconSettingsData(iconJson.getString("hash"), iconJson.getInt("width"), iconJson.getInt("height"));
    }

    private FeaturesSettingsData buildFeaturesSessionDataFrom(JSONObject json) {
        return new FeaturesSettingsData(json.optBoolean("prompt_enabled", false), json.optBoolean("collect_logged_exceptions", true), json.optBoolean("collect_reports", true), json.optBoolean("collect_analytics", false));
    }

    private AnalyticsSettingsData buildAnalyticsSessionDataFrom(JSONObject json) {
        return new AnalyticsSettingsData(json.optString("url", "https://e.crashlytics.com/spi/v2/events"), json.optInt("flush_interval_secs", 600), json.optInt("max_byte_size_per_file", 8000), json.optInt("max_file_count_per_send", 1), json.optInt("max_pending_send_file_count", 100), json.optBoolean("forward_to_google_analytics", false), json.optBoolean("analytics_include_purchase_events_in_forwarded_events", false), json.optBoolean("track_custom_events", true), json.optBoolean("track_predefined_events", true), json.optInt("sampling_rate", 1), json.optBoolean("flush_on_background", true));
    }

    private SessionSettingsData buildSessionDataFrom(JSONObject json) throws JSONException {
        return new SessionSettingsData(json.optInt("log_buffer_size", 64000), json.optInt("max_chained_exception_depth", 8), json.optInt("max_custom_exception_events", 64), json.optInt("max_custom_key_value_pairs", 64), json.optInt("identifier_mask", 255), json.optBoolean("send_session_without_crash", false), json.optInt("max_complete_sessions_count", 4));
    }

    private PromptSettingsData buildPromptDataFrom(JSONObject json) throws JSONException {
        return new PromptSettingsData(json.optString("title", "Send Crash Report?"), json.optString("message", "Looks like we crashed! Please help us fix the problem by sending a crash report."), json.optString("send_button_title", "Send"), json.optBoolean("show_cancel_button", true), json.optString("cancel_button_title", "Don't Send"), json.optBoolean("show_always_send_button", true), json.optString("always_send_button_title", "Always Send"));
    }

    private BetaSettingsData buildBetaSettingsDataFrom(JSONObject json) throws JSONException {
        return new BetaSettingsData(json.optString("update_endpoint", SettingsJsonConstants.BETA_UPDATE_ENDPOINT_DEFAULT), json.optInt("update_suspend_duration", 3600));
    }

    private long getExpiresAtFrom(CurrentTimeProvider currentTimeProvider, long cacheDurationSeconds, JSONObject json) throws JSONException {
        if (json.has("expires_at")) {
            return json.getLong("expires_at");
        }
        return currentTimeProvider.getCurrentTimeMillis() + (1000 * cacheDurationSeconds);
    }
}
