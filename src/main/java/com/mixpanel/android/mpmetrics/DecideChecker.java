package com.mixpanel.android.mpmetrics;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Build.VERSION;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import com.mixpanel.android.mpmetrics.InAppNotification.Type;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class DecideChecker {
    private final List<DecideUpdates> mChecks = new LinkedList();
    private final MPConfig mConfig;
    private final Context mContext;

    static class Result {
        public final List<InAppNotification> notifications = new ArrayList();
        public final List<Survey> surveys = new ArrayList();
    }

    public DecideChecker(Context context, MPConfig config) {
        this.mContext = context;
        this.mConfig = config;
    }

    public void addDecideCheck(DecideUpdates check) {
        this.mChecks.add(check);
    }

    public void runDecideChecks(ServerMessage poster) {
        Iterator<DecideUpdates> itr = this.mChecks.iterator();
        while (itr.hasNext()) {
            DecideUpdates updates = (DecideUpdates) itr.next();
            if (updates.isDestroyed()) {
                itr.remove();
            } else {
                Result result = runDecideCheck(updates.getToken(), updates.getDistinctId(), poster);
                updates.reportResults(result.surveys, result.notifications);
            }
        }
    }

    private Result runDecideCheck(String token, String distinctId, ServerMessage poster) {
        String responseString = getDecideResponseFromServer(token, distinctId, poster);
        if (MPConfig.DEBUG) {
            Log.d("MixpanelAPI DecideChecker", "Mixpanel decide server response was:\n" + responseString);
        }
        Result parsed = new Result();
        if (responseString != null) {
            parsed = parseDecideResponse(responseString);
        }
        Iterator<InAppNotification> notificationIterator = parsed.notifications.iterator();
        while (notificationIterator.hasNext()) {
            InAppNotification notification = (InAppNotification) notificationIterator.next();
            Bitmap image = getNotificationImage(notification, this.mContext, poster);
            if (image == null) {
                Log.i("MixpanelAPI DecideChecker", "Could not retrieve image for notification " + notification.getId() + ", will not show the notification.");
                notificationIterator.remove();
            } else {
                notification.setImage(image);
            }
        }
        return parsed;
    }

    static Result parseDecideResponse(String responseString) {
        Result ret = new Result();
        try {
            int i;
            JSONObject response = new JSONObject(responseString);
            JSONArray surveys = null;
            if (response.has("surveys")) {
                try {
                    surveys = response.getJSONArray("surveys");
                } catch (JSONException e) {
                    Log.e("MixpanelAPI DecideChecker", "Mixpanel endpoint returned non-array JSON for surveys: " + response);
                }
            }
            if (surveys != null) {
                for (i = 0; i < surveys.length(); i++) {
                    try {
                        ret.surveys.add(new Survey(surveys.getJSONObject(i)));
                    } catch (JSONException e2) {
                        Log.e("MixpanelAPI DecideChecker", "Received a strange response from surveys service: " + surveys.toString());
                    } catch (BadDecideObjectException e3) {
                        Log.e("MixpanelAPI DecideChecker", "Received a strange response from surveys service: " + surveys.toString());
                    }
                }
            }
            JSONArray notifications = null;
            if (response.has("notifications")) {
                try {
                    notifications = response.getJSONArray("notifications");
                } catch (JSONException e4) {
                    Log.e("MixpanelAPI DecideChecker", "Mixpanel endpoint returned non-array JSON for notifications: " + response);
                }
            }
            if (notifications != null) {
                int notificationsToRead = Math.min(notifications.length(), 2);
                i = 0;
                while (notifications != null && i < notificationsToRead) {
                    try {
                        ret.notifications.add(new InAppNotification(notifications.getJSONObject(i)));
                    } catch (JSONException e5) {
                        Log.e("MixpanelAPI DecideChecker", "Received a strange response from notifications service: " + notifications.toString(), e5);
                    } catch (BadDecideObjectException e6) {
                        Log.e("MixpanelAPI DecideChecker", "Received a strange response from notifications service: " + notifications.toString(), e6);
                    } catch (OutOfMemoryError e7) {
                        Log.e("MixpanelAPI DecideChecker", "Not enough memory to show load notification from package: " + notifications.toString(), e7);
                    }
                    i++;
                }
            }
        } catch (JSONException e52) {
            Log.e("MixpanelAPI DecideChecker", "Mixpanel endpoint returned unparsable result:\n" + responseString, e52);
        }
        return ret;
    }

    private String getDecideResponseFromServer(String unescapedToken, String unescapedDistinctId, ServerMessage poster) {
        try {
            String escapedToken = URLEncoder.encode(unescapedToken, "utf-8");
            String checkQuery = "?version=1&lib=android&token=" + escapedToken + "&distinct_id=" + URLEncoder.encode(unescapedDistinctId, "utf-8");
            String[] urls = new String[]{this.mConfig.getDecideEndpoint() + checkQuery, this.mConfig.getDecideFallbackEndpoint() + checkQuery};
            if (MPConfig.DEBUG) {
                Log.d("MixpanelAPI DecideChecker", "Querying decide server at " + urls[0]);
                Log.d("MixpanelAPI DecideChecker", "    (with fallback " + urls[1] + ")");
            }
            byte[] response = poster.getUrls(this.mContext, urls);
            if (response == null) {
                return null;
            }
            try {
                return new String(response, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("UTF not supported on this platform?", e);
            }
        } catch (UnsupportedEncodingException e2) {
            throw new RuntimeException("Mixpanel library requires utf-8 string encoding to be available", e2);
        }
    }

    private static Bitmap getNotificationImage(InAppNotification notification, Context context, ServerMessage poster) {
        String[] urls = new String[]{notification.getImage2xUrl()};
        int displayWidth = getDisplayWidth(((WindowManager) context.getSystemService("window")).getDefaultDisplay());
        if (notification.getType() == Type.TAKEOVER && displayWidth >= 720) {
            urls = new String[]{notification.getImage4xUrl(), notification.getImage2xUrl()};
        }
        byte[] response = poster.getUrls(context, urls);
        if (response != null) {
            return BitmapFactory.decodeByteArray(response, 0, response.length);
        }
        Log.i("MixpanelAPI DecideChecker", "Failed to download images from " + Arrays.toString(urls));
        return null;
    }

    @SuppressLint({"NewApi"})
    private static int getDisplayWidth(Display display) {
        if (VERSION.SDK_INT < 13) {
            return display.getWidth();
        }
        Point displaySize = new Point();
        display.getSize(displaySize);
        return displaySize.x;
    }
}
