package com.mixpanel.android.mpmetrics;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

class DecideUpdates {
    private final String mDistinctId;
    private final AtomicBoolean mIsDestroyed = new AtomicBoolean(false);
    private final OnNewResultsListener mListener;
    private final Set<Integer> mNotificationIds = new HashSet();
    private final Set<Integer> mSurveyIds = new HashSet();
    private final String mToken;
    private final List<InAppNotification> mUnseenNotifications = new LinkedList();
    private final List<Survey> mUnseenSurveys = new LinkedList();

    public interface OnNewResultsListener {
        void onNewResults(String str);
    }

    public DecideUpdates(String token, String distinctId, OnNewResultsListener listener) {
        this.mToken = token;
        this.mDistinctId = distinctId;
        this.mListener = listener;
    }

    public String getToken() {
        return this.mToken;
    }

    public String getDistinctId() {
        return this.mDistinctId;
    }

    public boolean isDestroyed() {
        return this.mIsDestroyed.get();
    }

    public synchronized void reportResults(List<Survey> newSurveys, List<InAppNotification> newNotifications) {
        boolean newContent = false;
        for (Survey s : newSurveys) {
            int id = s.getId();
            if (!this.mSurveyIds.contains(Integer.valueOf(id))) {
                this.mSurveyIds.add(Integer.valueOf(id));
                this.mUnseenSurveys.add(s);
                newContent = true;
            }
        }
        for (InAppNotification n : newNotifications) {
            id = n.getId();
            if (!this.mNotificationIds.contains(Integer.valueOf(id))) {
                this.mNotificationIds.add(Integer.valueOf(id));
                this.mUnseenNotifications.add(n);
                newContent = true;
            }
        }
        if (newContent && hasUpdatesAvailable() && this.mListener != null) {
            this.mListener.onNewResults(getDistinctId());
        }
    }

    public synchronized Survey getSurvey(boolean replace) {
        Survey survey;
        if (this.mUnseenSurveys.isEmpty()) {
            survey = null;
        } else {
            survey = (Survey) this.mUnseenSurveys.remove(0);
            if (replace) {
                this.mUnseenSurveys.add(this.mUnseenSurveys.size(), survey);
            }
        }
        return survey;
    }

    public synchronized InAppNotification getNotification(boolean replace) {
        InAppNotification inAppNotification;
        if (this.mUnseenNotifications.isEmpty()) {
            inAppNotification = null;
        } else {
            inAppNotification = (InAppNotification) this.mUnseenNotifications.remove(0);
            if (replace) {
                this.mUnseenNotifications.add(this.mUnseenNotifications.size(), inAppNotification);
            }
        }
        return inAppNotification;
    }

    public synchronized boolean hasUpdatesAvailable() {
        boolean z;
        z = (this.mUnseenNotifications.isEmpty() && this.mUnseenSurveys.isEmpty()) ? false : true;
        return z;
    }
}
