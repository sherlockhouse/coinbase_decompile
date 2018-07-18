package android.support.v4.media;

import android.content.ComponentName;
import android.content.Context;
import android.media.browse.MediaBrowser;
import android.os.Bundle;
import java.util.List;

class MediaBrowserCompatApi21 {

    interface ConnectionCallback {
        void onConnected();

        void onConnectionFailed();

        void onConnectionSuspended();
    }

    interface SubscriptionCallback {
        void onChildrenLoaded(String str, List<?> list);

        void onError(String str);
    }

    static class ConnectionCallbackProxy<T extends ConnectionCallback> extends android.media.browse.MediaBrowser.ConnectionCallback {
        protected final T mConnectionCallback;

        public ConnectionCallbackProxy(T connectionCallback) {
            this.mConnectionCallback = connectionCallback;
        }

        public void onConnected() {
            this.mConnectionCallback.onConnected();
        }

        public void onConnectionSuspended() {
            this.mConnectionCallback.onConnectionSuspended();
        }

        public void onConnectionFailed() {
            this.mConnectionCallback.onConnectionFailed();
        }
    }

    static class MediaItem {
        public static int getFlags(Object itemObj) {
            return ((android.media.browse.MediaBrowser.MediaItem) itemObj).getFlags();
        }

        public static Object getDescription(Object itemObj) {
            return ((android.media.browse.MediaBrowser.MediaItem) itemObj).getDescription();
        }
    }

    static class SubscriptionCallbackProxy<T extends SubscriptionCallback> extends android.media.browse.MediaBrowser.SubscriptionCallback {
        protected final T mSubscriptionCallback;

        public SubscriptionCallbackProxy(T callback) {
            this.mSubscriptionCallback = callback;
        }

        public void onChildrenLoaded(String parentId, List<android.media.browse.MediaBrowser.MediaItem> children) {
            this.mSubscriptionCallback.onChildrenLoaded(parentId, children);
        }

        public void onError(String parentId) {
            this.mSubscriptionCallback.onError(parentId);
        }
    }

    public static Object createConnectionCallback(ConnectionCallback callback) {
        return new ConnectionCallbackProxy(callback);
    }

    public static Object createBrowser(Context context, ComponentName serviceComponent, Object callback, Bundle rootHints) {
        return new MediaBrowser(context, serviceComponent, (android.media.browse.MediaBrowser.ConnectionCallback) callback, rootHints);
    }

    public static void connect(Object browserObj) {
        ((MediaBrowser) browserObj).connect();
    }

    public static void disconnect(Object browserObj) {
        ((MediaBrowser) browserObj).disconnect();
    }

    public static Bundle getExtras(Object browserObj) {
        return ((MediaBrowser) browserObj).getExtras();
    }

    public static Object getSessionToken(Object browserObj) {
        return ((MediaBrowser) browserObj).getSessionToken();
    }

    public static Object createSubscriptionCallback(SubscriptionCallback callback) {
        return new SubscriptionCallbackProxy(callback);
    }
}
