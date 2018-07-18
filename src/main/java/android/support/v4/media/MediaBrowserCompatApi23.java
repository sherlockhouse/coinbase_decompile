package android.support.v4.media;

import android.media.browse.MediaBrowser.MediaItem;
import android.os.Parcel;

class MediaBrowserCompatApi23 {

    interface ItemCallback {
        void onError(String str);

        void onItemLoaded(Parcel parcel);
    }

    static class ItemCallbackProxy<T extends ItemCallback> extends android.media.browse.MediaBrowser.ItemCallback {
        protected final T mItemCallback;

        public ItemCallbackProxy(T callback) {
            this.mItemCallback = callback;
        }

        public void onItemLoaded(MediaItem item) {
            if (item == null) {
                this.mItemCallback.onItemLoaded(null);
                return;
            }
            Parcel parcel = Parcel.obtain();
            item.writeToParcel(parcel, 0);
            this.mItemCallback.onItemLoaded(parcel);
        }

        public void onError(String itemId) {
            this.mItemCallback.onError(itemId);
        }
    }

    public static Object createItemCallback(ItemCallback callback) {
        return new ItemCallbackProxy(callback);
    }
}
