package com.crashlytics.android.core;

import android.content.Context;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.settings.PromptSettingsData;

class DialogStringResolver {
    private final Context context;
    private final PromptSettingsData promptData;

    public DialogStringResolver(Context context, PromptSettingsData promptData) {
        this.context = context;
        this.promptData = promptData;
    }

    public String getTitle() {
        return resourceOrFallbackValue("com.crashlytics.CrashSubmissionPromptTitle", this.promptData.title);
    }

    public String getMessage() {
        return resourceOrFallbackValue("com.crashlytics.CrashSubmissionPromptMessage", this.promptData.message);
    }

    public String getSendButtonTitle() {
        return resourceOrFallbackValue("com.crashlytics.CrashSubmissionSendTitle", this.promptData.sendButtonTitle);
    }

    public String getAlwaysSendButtonTitle() {
        return resourceOrFallbackValue("com.crashlytics.CrashSubmissionAlwaysSendTitle", this.promptData.alwaysSendButtonTitle);
    }

    public String getCancelButtonTitle() {
        return resourceOrFallbackValue("com.crashlytics.CrashSubmissionCancelTitle", this.promptData.cancelButtonTitle);
    }

    private String resourceOrFallbackValue(String resourceName, String settingsValue) {
        return stringOrFallback(CommonUtils.getStringsFileValue(this.context, resourceName), settingsValue);
    }

    private String stringOrFallback(String firstChoice, String fallback) {
        return isNullOrEmpty(firstChoice) ? fallback : firstChoice;
    }

    private boolean isNullOrEmpty(String s) {
        return s == null || s.length() == 0;
    }
}
