package android.support.v4.media;

import android.media.browse.MediaBrowser.MediaItem;
import android.os.Bundle;
import java.util.List;

class MediaBrowserCompatApi24 {

    interface SubscriptionCallback extends SubscriptionCallback {
        void onChildrenLoaded(String str, List<?> list, Bundle bundle);

        void onError(String str, Bundle bundle);
    }

    static class SubscriptionCallbackProxy<T extends SubscriptionCallback> extends SubscriptionCallbackProxy<T> {
        public SubscriptionCallbackProxy(T callback) {
            super(callback);
        }

        public void onChildrenLoaded(String parentId, List<MediaItem> children, Bundle options) {
            ((SubscriptionCallback) this.mSubscriptionCallback).onChildrenLoaded(parentId, children, options);
        }

        public void onError(String parentId, Bundle options) {
            ((SubscriptionCallback) this.mSubscriptionCallback).onError(parentId, options);
        }
    }

    public static Object createSubscriptionCallback(SubscriptionCallback callback) {
        return new SubscriptionCallbackProxy(callback);
    }
}
