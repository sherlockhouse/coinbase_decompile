package android.support.v4.media;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.BadParcelableException;
import android.os.Binder;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import android.support.v4.app.BundleCompat;
import android.support.v4.media.session.IMediaSession;
import android.support.v4.media.session.IMediaSession.Stub;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.MediaSessionCompat.Token;
import android.support.v4.os.ResultReceiver;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Log;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;

public final class MediaBrowserCompat {
    static final boolean DEBUG = Log.isLoggable("MediaBrowserCompat", 3);
    private final MediaBrowserImpl mImpl;

    private static class CallbackHandler extends Handler {
        private final WeakReference<MediaBrowserServiceCallbackImpl> mCallbackImplRef;
        private WeakReference<Messenger> mCallbacksMessengerRef;

        CallbackHandler(MediaBrowserServiceCallbackImpl callbackImpl) {
            this.mCallbackImplRef = new WeakReference(callbackImpl);
        }

        public void handleMessage(Message msg) {
            if (this.mCallbacksMessengerRef != null && this.mCallbacksMessengerRef.get() != null && this.mCallbackImplRef.get() != null) {
                Bundle data = msg.getData();
                data.setClassLoader(MediaSessionCompat.class.getClassLoader());
                MediaBrowserServiceCallbackImpl serviceCallback = (MediaBrowserServiceCallbackImpl) this.mCallbackImplRef.get();
                Messenger callbacksMessenger = (Messenger) this.mCallbacksMessengerRef.get();
                try {
                    switch (msg.what) {
                        case 1:
                            serviceCallback.onServiceConnected(callbacksMessenger, data.getString("data_media_item_id"), (Token) data.getParcelable("data_media_session_token"), data.getBundle("data_root_hints"));
                            return;
                        case 2:
                            serviceCallback.onConnectionFailed(callbacksMessenger);
                            return;
                        case 3:
                            serviceCallback.onLoadChildren(callbacksMessenger, data.getString("data_media_item_id"), data.getParcelableArrayList("data_media_item_list"), data.getBundle("data_options"));
                            return;
                        default:
                            Log.w("MediaBrowserCompat", "Unhandled message: " + msg + "\n  Client version: " + 1 + "\n  Service version: " + msg.arg1);
                            return;
                    }
                } catch (BadParcelableException e) {
                    Log.e("MediaBrowserCompat", "Could not unparcel the data.");
                    if (msg.what == 1) {
                        serviceCallback.onConnectionFailed(callbacksMessenger);
                    }
                }
                Log.e("MediaBrowserCompat", "Could not unparcel the data.");
                if (msg.what == 1) {
                    serviceCallback.onConnectionFailed(callbacksMessenger);
                }
            }
        }

        void setCallbacksMessenger(Messenger callbacksMessenger) {
            this.mCallbacksMessengerRef = new WeakReference(callbacksMessenger);
        }
    }

    public static class ConnectionCallback {
        ConnectionCallbackInternal mConnectionCallbackInternal;
        final Object mConnectionCallbackObj;

        interface ConnectionCallbackInternal {
            void onConnected();

            void onConnectionFailed();

            void onConnectionSuspended();
        }

        private class StubApi21 implements ConnectionCallback {
            StubApi21() {
            }

            public void onConnected() {
                if (ConnectionCallback.this.mConnectionCallbackInternal != null) {
                    ConnectionCallback.this.mConnectionCallbackInternal.onConnected();
                }
                ConnectionCallback.this.onConnected();
            }

            public void onConnectionSuspended() {
                if (ConnectionCallback.this.mConnectionCallbackInternal != null) {
                    ConnectionCallback.this.mConnectionCallbackInternal.onConnectionSuspended();
                }
                ConnectionCallback.this.onConnectionSuspended();
            }

            public void onConnectionFailed() {
                if (ConnectionCallback.this.mConnectionCallbackInternal != null) {
                    ConnectionCallback.this.mConnectionCallbackInternal.onConnectionFailed();
                }
                ConnectionCallback.this.onConnectionFailed();
            }
        }

        public ConnectionCallback() {
            if (VERSION.SDK_INT >= 21) {
                this.mConnectionCallbackObj = MediaBrowserCompatApi21.createConnectionCallback(new StubApi21());
            } else {
                this.mConnectionCallbackObj = null;
            }
        }

        public void onConnected() {
        }

        public void onConnectionSuspended() {
        }

        public void onConnectionFailed() {
        }

        void setInternalConnectionCallback(ConnectionCallbackInternal connectionCallbackInternal) {
            this.mConnectionCallbackInternal = connectionCallbackInternal;
        }
    }

    public static abstract class CustomActionCallback {
        public void onProgressUpdate(String action, Bundle extras, Bundle data) {
        }

        public void onResult(String action, Bundle extras, Bundle resultData) {
        }

        public void onError(String action, Bundle extras, Bundle data) {
        }
    }

    private static class CustomActionResultReceiver extends ResultReceiver {
        private final String mAction;
        private final CustomActionCallback mCallback;
        private final Bundle mExtras;

        protected void onReceiveResult(int resultCode, Bundle resultData) {
            if (this.mCallback != null) {
                switch (resultCode) {
                    case -1:
                        this.mCallback.onError(this.mAction, this.mExtras, resultData);
                        return;
                    case 0:
                        this.mCallback.onResult(this.mAction, this.mExtras, resultData);
                        return;
                    case 1:
                        this.mCallback.onProgressUpdate(this.mAction, this.mExtras, resultData);
                        return;
                    default:
                        Log.w("MediaBrowserCompat", "Unknown result code: " + resultCode + " (extras=" + this.mExtras + ", resultData=" + resultData + ")");
                        return;
                }
            }
        }
    }

    public static abstract class ItemCallback {
        final Object mItemCallbackObj;

        private class StubApi23 implements ItemCallback {
            StubApi23() {
            }

            public void onItemLoaded(Parcel itemParcel) {
                if (itemParcel == null) {
                    ItemCallback.this.onItemLoaded(null);
                    return;
                }
                itemParcel.setDataPosition(0);
                MediaItem item = (MediaItem) MediaItem.CREATOR.createFromParcel(itemParcel);
                itemParcel.recycle();
                ItemCallback.this.onItemLoaded(item);
            }

            public void onError(String itemId) {
                ItemCallback.this.onError(itemId);
            }
        }

        public ItemCallback() {
            if (VERSION.SDK_INT >= 23) {
                this.mItemCallbackObj = MediaBrowserCompatApi23.createItemCallback(new StubApi23());
            } else {
                this.mItemCallbackObj = null;
            }
        }

        public void onItemLoaded(MediaItem item) {
        }

        public void onError(String itemId) {
        }
    }

    private static class ItemReceiver extends ResultReceiver {
        private final ItemCallback mCallback;
        private final String mMediaId;

        protected void onReceiveResult(int resultCode, Bundle resultData) {
            if (resultData != null) {
                resultData.setClassLoader(MediaBrowserCompat.class.getClassLoader());
            }
            if (resultCode == 0 && resultData != null && resultData.containsKey("media_item")) {
                Parcelable item = resultData.getParcelable("media_item");
                if (item == null || (item instanceof MediaItem)) {
                    this.mCallback.onItemLoaded((MediaItem) item);
                    return;
                } else {
                    this.mCallback.onError(this.mMediaId);
                    return;
                }
            }
            this.mCallback.onError(this.mMediaId);
        }
    }

    interface MediaBrowserImpl {
        void connect();

        void disconnect();

        Token getSessionToken();
    }

    interface MediaBrowserServiceCallbackImpl {
        void onConnectionFailed(Messenger messenger);

        void onLoadChildren(Messenger messenger, String str, List list, Bundle bundle);

        void onServiceConnected(Messenger messenger, String str, Token token, Bundle bundle);
    }

    static class MediaBrowserImplApi21 implements ConnectionCallbackInternal, MediaBrowserImpl, MediaBrowserServiceCallbackImpl {
        protected final Object mBrowserObj;
        protected Messenger mCallbacksMessenger;
        final Context mContext;
        protected final CallbackHandler mHandler = new CallbackHandler(this);
        private Token mMediaSessionToken;
        protected final Bundle mRootHints;
        protected ServiceBinderWrapper mServiceBinderWrapper;
        private final ArrayMap<String, Subscription> mSubscriptions = new ArrayMap();

        public MediaBrowserImplApi21(Context context, ComponentName serviceComponent, ConnectionCallback callback, Bundle rootHints) {
            this.mContext = context;
            if (rootHints == null) {
                rootHints = new Bundle();
            }
            rootHints.putInt("extra_client_version", 1);
            this.mRootHints = new Bundle(rootHints);
            callback.setInternalConnectionCallback(this);
            this.mBrowserObj = MediaBrowserCompatApi21.createBrowser(context, serviceComponent, callback.mConnectionCallbackObj, this.mRootHints);
        }

        public void connect() {
            MediaBrowserCompatApi21.connect(this.mBrowserObj);
        }

        public void disconnect() {
            if (!(this.mServiceBinderWrapper == null || this.mCallbacksMessenger == null)) {
                try {
                    this.mServiceBinderWrapper.unregisterCallbackMessenger(this.mCallbacksMessenger);
                } catch (RemoteException e) {
                    Log.i("MediaBrowserCompat", "Remote error unregistering client messenger.");
                }
            }
            MediaBrowserCompatApi21.disconnect(this.mBrowserObj);
        }

        public Token getSessionToken() {
            if (this.mMediaSessionToken == null) {
                this.mMediaSessionToken = Token.fromToken(MediaBrowserCompatApi21.getSessionToken(this.mBrowserObj));
            }
            return this.mMediaSessionToken;
        }

        public void onConnected() {
            Bundle extras = MediaBrowserCompatApi21.getExtras(this.mBrowserObj);
            if (extras != null) {
                IBinder serviceBinder = BundleCompat.getBinder(extras, "extra_messenger");
                if (serviceBinder != null) {
                    this.mServiceBinderWrapper = new ServiceBinderWrapper(serviceBinder, this.mRootHints);
                    this.mCallbacksMessenger = new Messenger(this.mHandler);
                    this.mHandler.setCallbacksMessenger(this.mCallbacksMessenger);
                    try {
                        this.mServiceBinderWrapper.registerCallbackMessenger(this.mCallbacksMessenger);
                    } catch (RemoteException e) {
                        Log.i("MediaBrowserCompat", "Remote error registering client messenger.");
                    }
                }
                IMediaSession sessionToken = Stub.asInterface(BundleCompat.getBinder(extras, "extra_session_binder"));
                if (sessionToken != null) {
                    this.mMediaSessionToken = Token.fromToken(MediaBrowserCompatApi21.getSessionToken(this.mBrowserObj), sessionToken);
                }
            }
        }

        public void onConnectionSuspended() {
            this.mServiceBinderWrapper = null;
            this.mCallbacksMessenger = null;
            this.mMediaSessionToken = null;
            this.mHandler.setCallbacksMessenger(null);
        }

        public void onConnectionFailed() {
        }

        public void onServiceConnected(Messenger callback, String root, Token session, Bundle extra) {
        }

        public void onConnectionFailed(Messenger callback) {
        }

        public void onLoadChildren(Messenger callback, String parentId, List list, Bundle options) {
            if (this.mCallbacksMessenger == callback) {
                Subscription subscription = (Subscription) this.mSubscriptions.get(parentId);
                if (subscription != null) {
                    SubscriptionCallback subscriptionCallback = subscription.getCallback(this.mContext, options);
                    if (subscriptionCallback == null) {
                        return;
                    }
                    if (options == null) {
                        if (list == null) {
                            subscriptionCallback.onError(parentId);
                        } else {
                            subscriptionCallback.onChildrenLoaded(parentId, list);
                        }
                    } else if (list == null) {
                        subscriptionCallback.onError(parentId, options);
                    } else {
                        subscriptionCallback.onChildrenLoaded(parentId, list, options);
                    }
                } else if (MediaBrowserCompat.DEBUG) {
                    Log.d("MediaBrowserCompat", "onLoadChildren for id that isn't subscribed id=" + parentId);
                }
            }
        }
    }

    static class MediaBrowserImplApi23 extends MediaBrowserImplApi21 {
        public MediaBrowserImplApi23(Context context, ComponentName serviceComponent, ConnectionCallback callback, Bundle rootHints) {
            super(context, serviceComponent, callback, rootHints);
        }
    }

    static class MediaBrowserImplApi24 extends MediaBrowserImplApi23 {
        public MediaBrowserImplApi24(Context context, ComponentName serviceComponent, ConnectionCallback callback, Bundle rootHints) {
            super(context, serviceComponent, callback, rootHints);
        }
    }

    static class MediaBrowserImplBase implements MediaBrowserImpl, MediaBrowserServiceCallbackImpl {
        final ConnectionCallback mCallback;
        Messenger mCallbacksMessenger;
        final Context mContext;
        private Bundle mExtras;
        final CallbackHandler mHandler = new CallbackHandler(this);
        private Token mMediaSessionToken;
        final Bundle mRootHints;
        private String mRootId;
        ServiceBinderWrapper mServiceBinderWrapper;
        final ComponentName mServiceComponent;
        MediaServiceConnection mServiceConnection;
        int mState = 1;
        private final ArrayMap<String, Subscription> mSubscriptions = new ArrayMap();

        private class MediaServiceConnection implements ServiceConnection {
            MediaServiceConnection() {
            }

            public void onServiceConnected(final ComponentName name, final IBinder binder) {
                postOrRun(new Runnable() {
                    public void run() {
                        if (MediaBrowserCompat.DEBUG) {
                            Log.d("MediaBrowserCompat", "MediaServiceConnection.onServiceConnected name=" + name + " binder=" + binder);
                            MediaBrowserImplBase.this.dump();
                        }
                        if (MediaServiceConnection.this.isCurrent("onServiceConnected")) {
                            MediaBrowserImplBase.this.mServiceBinderWrapper = new ServiceBinderWrapper(binder, MediaBrowserImplBase.this.mRootHints);
                            MediaBrowserImplBase.this.mCallbacksMessenger = new Messenger(MediaBrowserImplBase.this.mHandler);
                            MediaBrowserImplBase.this.mHandler.setCallbacksMessenger(MediaBrowserImplBase.this.mCallbacksMessenger);
                            MediaBrowserImplBase.this.mState = 2;
                            try {
                                if (MediaBrowserCompat.DEBUG) {
                                    Log.d("MediaBrowserCompat", "ServiceCallbacks.onConnect...");
                                    MediaBrowserImplBase.this.dump();
                                }
                                MediaBrowserImplBase.this.mServiceBinderWrapper.connect(MediaBrowserImplBase.this.mContext, MediaBrowserImplBase.this.mCallbacksMessenger);
                            } catch (RemoteException e) {
                                Log.w("MediaBrowserCompat", "RemoteException during connect for " + MediaBrowserImplBase.this.mServiceComponent);
                                if (MediaBrowserCompat.DEBUG) {
                                    Log.d("MediaBrowserCompat", "ServiceCallbacks.onConnect...");
                                    MediaBrowserImplBase.this.dump();
                                }
                            }
                        }
                    }
                });
            }

            public void onServiceDisconnected(final ComponentName name) {
                postOrRun(new Runnable() {
                    public void run() {
                        if (MediaBrowserCompat.DEBUG) {
                            Log.d("MediaBrowserCompat", "MediaServiceConnection.onServiceDisconnected name=" + name + " this=" + this + " mServiceConnection=" + MediaBrowserImplBase.this.mServiceConnection);
                            MediaBrowserImplBase.this.dump();
                        }
                        if (MediaServiceConnection.this.isCurrent("onServiceDisconnected")) {
                            MediaBrowserImplBase.this.mServiceBinderWrapper = null;
                            MediaBrowserImplBase.this.mCallbacksMessenger = null;
                            MediaBrowserImplBase.this.mHandler.setCallbacksMessenger(null);
                            MediaBrowserImplBase.this.mState = 4;
                            MediaBrowserImplBase.this.mCallback.onConnectionSuspended();
                        }
                    }
                });
            }

            private void postOrRun(Runnable r) {
                if (Thread.currentThread() == MediaBrowserImplBase.this.mHandler.getLooper().getThread()) {
                    r.run();
                } else {
                    MediaBrowserImplBase.this.mHandler.post(r);
                }
            }

            boolean isCurrent(String funcName) {
                if (MediaBrowserImplBase.this.mServiceConnection == this && MediaBrowserImplBase.this.mState != 0 && MediaBrowserImplBase.this.mState != 1) {
                    return true;
                }
                if (!(MediaBrowserImplBase.this.mState == 0 || MediaBrowserImplBase.this.mState == 1)) {
                    Log.i("MediaBrowserCompat", funcName + " for " + MediaBrowserImplBase.this.mServiceComponent + " with mServiceConnection=" + MediaBrowserImplBase.this.mServiceConnection + " this=" + this);
                }
                return false;
            }
        }

        public MediaBrowserImplBase(Context context, ComponentName serviceComponent, ConnectionCallback callback, Bundle rootHints) {
            if (context == null) {
                throw new IllegalArgumentException("context must not be null");
            } else if (serviceComponent == null) {
                throw new IllegalArgumentException("service component must not be null");
            } else if (callback == null) {
                throw new IllegalArgumentException("connection callback must not be null");
            } else {
                this.mContext = context;
                this.mServiceComponent = serviceComponent;
                this.mCallback = callback;
                this.mRootHints = rootHints == null ? null : new Bundle(rootHints);
            }
        }

        public void connect() {
            if (this.mState == 0 || this.mState == 1) {
                this.mState = 2;
                this.mHandler.post(new Runnable() {
                    public void run() {
                        if (MediaBrowserImplBase.this.mState != 0) {
                            MediaBrowserImplBase.this.mState = 2;
                            if (MediaBrowserCompat.DEBUG && MediaBrowserImplBase.this.mServiceConnection != null) {
                                throw new RuntimeException("mServiceConnection should be null. Instead it is " + MediaBrowserImplBase.this.mServiceConnection);
                            } else if (MediaBrowserImplBase.this.mServiceBinderWrapper != null) {
                                throw new RuntimeException("mServiceBinderWrapper should be null. Instead it is " + MediaBrowserImplBase.this.mServiceBinderWrapper);
                            } else if (MediaBrowserImplBase.this.mCallbacksMessenger != null) {
                                throw new RuntimeException("mCallbacksMessenger should be null. Instead it is " + MediaBrowserImplBase.this.mCallbacksMessenger);
                            } else {
                                Intent intent = new Intent("android.media.browse.MediaBrowserService");
                                intent.setComponent(MediaBrowserImplBase.this.mServiceComponent);
                                MediaBrowserImplBase.this.mServiceConnection = new MediaServiceConnection();
                                boolean bound = false;
                                try {
                                    bound = MediaBrowserImplBase.this.mContext.bindService(intent, MediaBrowserImplBase.this.mServiceConnection, 1);
                                } catch (Exception e) {
                                    Log.e("MediaBrowserCompat", "Failed binding to service " + MediaBrowserImplBase.this.mServiceComponent);
                                }
                                if (!bound) {
                                    MediaBrowserImplBase.this.forceCloseConnection();
                                    MediaBrowserImplBase.this.mCallback.onConnectionFailed();
                                }
                                if (MediaBrowserCompat.DEBUG) {
                                    Log.d("MediaBrowserCompat", "connect...");
                                    MediaBrowserImplBase.this.dump();
                                }
                            }
                        }
                    }
                });
                return;
            }
            throw new IllegalStateException("connect() called while neigther disconnecting nor disconnected (state=" + getStateLabel(this.mState) + ")");
        }

        public void disconnect() {
            this.mState = 0;
            this.mHandler.post(new Runnable() {
                public void run() {
                    if (MediaBrowserImplBase.this.mCallbacksMessenger != null) {
                        try {
                            MediaBrowserImplBase.this.mServiceBinderWrapper.disconnect(MediaBrowserImplBase.this.mCallbacksMessenger);
                        } catch (RemoteException e) {
                            Log.w("MediaBrowserCompat", "RemoteException during connect for " + MediaBrowserImplBase.this.mServiceComponent);
                        }
                    }
                    int state = MediaBrowserImplBase.this.mState;
                    MediaBrowserImplBase.this.forceCloseConnection();
                    if (state != 0) {
                        MediaBrowserImplBase.this.mState = state;
                    }
                    if (MediaBrowserCompat.DEBUG) {
                        Log.d("MediaBrowserCompat", "disconnect...");
                        MediaBrowserImplBase.this.dump();
                    }
                }
            });
        }

        void forceCloseConnection() {
            if (this.mServiceConnection != null) {
                this.mContext.unbindService(this.mServiceConnection);
            }
            this.mState = 1;
            this.mServiceConnection = null;
            this.mServiceBinderWrapper = null;
            this.mCallbacksMessenger = null;
            this.mHandler.setCallbacksMessenger(null);
            this.mRootId = null;
            this.mMediaSessionToken = null;
        }

        public boolean isConnected() {
            return this.mState == 3;
        }

        public Token getSessionToken() {
            if (isConnected()) {
                return this.mMediaSessionToken;
            }
            throw new IllegalStateException("getSessionToken() called while not connected(state=" + this.mState + ")");
        }

        public void onServiceConnected(Messenger callback, String root, Token session, Bundle extra) {
            if (!isCurrent(callback, "onConnect")) {
                return;
            }
            if (this.mState != 2) {
                Log.w("MediaBrowserCompat", "onConnect from service while mState=" + getStateLabel(this.mState) + "... ignoring");
                return;
            }
            this.mRootId = root;
            this.mMediaSessionToken = session;
            this.mExtras = extra;
            this.mState = 3;
            if (MediaBrowserCompat.DEBUG) {
                Log.d("MediaBrowserCompat", "ServiceCallbacks.onConnect...");
                dump();
            }
            this.mCallback.onConnected();
            try {
                for (Entry<String, Subscription> subscriptionEntry : this.mSubscriptions.entrySet()) {
                    String id = (String) subscriptionEntry.getKey();
                    Subscription sub = (Subscription) subscriptionEntry.getValue();
                    List<SubscriptionCallback> callbackList = sub.getCallbacks();
                    List<Bundle> optionsList = sub.getOptionsList();
                    for (int i = 0; i < callbackList.size(); i++) {
                        this.mServiceBinderWrapper.addSubscription(id, ((SubscriptionCallback) callbackList.get(i)).mToken, (Bundle) optionsList.get(i), this.mCallbacksMessenger);
                    }
                }
            } catch (RemoteException e) {
                Log.d("MediaBrowserCompat", "addSubscription failed with RemoteException.");
            }
        }

        public void onConnectionFailed(Messenger callback) {
            Log.e("MediaBrowserCompat", "onConnectFailed for " + this.mServiceComponent);
            if (!isCurrent(callback, "onConnectFailed")) {
                return;
            }
            if (this.mState != 2) {
                Log.w("MediaBrowserCompat", "onConnect from service while mState=" + getStateLabel(this.mState) + "... ignoring");
                return;
            }
            forceCloseConnection();
            this.mCallback.onConnectionFailed();
        }

        public void onLoadChildren(Messenger callback, String parentId, List list, Bundle options) {
            if (isCurrent(callback, "onLoadChildren")) {
                if (MediaBrowserCompat.DEBUG) {
                    Log.d("MediaBrowserCompat", "onLoadChildren for " + this.mServiceComponent + " id=" + parentId);
                }
                Subscription subscription = (Subscription) this.mSubscriptions.get(parentId);
                if (subscription != null) {
                    SubscriptionCallback subscriptionCallback = subscription.getCallback(this.mContext, options);
                    if (subscriptionCallback == null) {
                        return;
                    }
                    if (options == null) {
                        if (list == null) {
                            subscriptionCallback.onError(parentId);
                        } else {
                            subscriptionCallback.onChildrenLoaded(parentId, list);
                        }
                    } else if (list == null) {
                        subscriptionCallback.onError(parentId, options);
                    } else {
                        subscriptionCallback.onChildrenLoaded(parentId, list, options);
                    }
                } else if (MediaBrowserCompat.DEBUG) {
                    Log.d("MediaBrowserCompat", "onLoadChildren for id that isn't subscribed id=" + parentId);
                }
            }
        }

        private static String getStateLabel(int state) {
            switch (state) {
                case 0:
                    return "CONNECT_STATE_DISCONNECTING";
                case 1:
                    return "CONNECT_STATE_DISCONNECTED";
                case 2:
                    return "CONNECT_STATE_CONNECTING";
                case 3:
                    return "CONNECT_STATE_CONNECTED";
                case 4:
                    return "CONNECT_STATE_SUSPENDED";
                default:
                    return "UNKNOWN/" + state;
            }
        }

        private boolean isCurrent(Messenger callback, String funcName) {
            if (this.mCallbacksMessenger == callback && this.mState != 0 && this.mState != 1) {
                return true;
            }
            if (!(this.mState == 0 || this.mState == 1)) {
                Log.i("MediaBrowserCompat", funcName + " for " + this.mServiceComponent + " with mCallbacksMessenger=" + this.mCallbacksMessenger + " this=" + this);
            }
            return false;
        }

        void dump() {
            Log.d("MediaBrowserCompat", "MediaBrowserCompat...");
            Log.d("MediaBrowserCompat", "  mServiceComponent=" + this.mServiceComponent);
            Log.d("MediaBrowserCompat", "  mCallback=" + this.mCallback);
            Log.d("MediaBrowserCompat", "  mRootHints=" + this.mRootHints);
            Log.d("MediaBrowserCompat", "  mState=" + getStateLabel(this.mState));
            Log.d("MediaBrowserCompat", "  mServiceConnection=" + this.mServiceConnection);
            Log.d("MediaBrowserCompat", "  mServiceBinderWrapper=" + this.mServiceBinderWrapper);
            Log.d("MediaBrowserCompat", "  mCallbacksMessenger=" + this.mCallbacksMessenger);
            Log.d("MediaBrowserCompat", "  mRootId=" + this.mRootId);
            Log.d("MediaBrowserCompat", "  mMediaSessionToken=" + this.mMediaSessionToken);
        }
    }

    public static class MediaItem implements Parcelable {
        public static final Creator<MediaItem> CREATOR = new Creator<MediaItem>() {
            public MediaItem createFromParcel(Parcel in) {
                return new MediaItem(in);
            }

            public MediaItem[] newArray(int size) {
                return new MediaItem[size];
            }
        };
        private final MediaDescriptionCompat mDescription;
        private final int mFlags;

        public static MediaItem fromMediaItem(Object itemObj) {
            if (itemObj == null || VERSION.SDK_INT < 21) {
                return null;
            }
            return new MediaItem(MediaDescriptionCompat.fromMediaDescription(MediaItem.getDescription(itemObj)), MediaItem.getFlags(itemObj));
        }

        public static List<MediaItem> fromMediaItemList(List<?> itemList) {
            if (itemList == null || VERSION.SDK_INT < 21) {
                return null;
            }
            List<MediaItem> items = new ArrayList(itemList.size());
            for (Object itemObj : itemList) {
                items.add(fromMediaItem(itemObj));
            }
            return items;
        }

        public MediaItem(MediaDescriptionCompat description, int flags) {
            if (description == null) {
                throw new IllegalArgumentException("description cannot be null");
            } else if (TextUtils.isEmpty(description.getMediaId())) {
                throw new IllegalArgumentException("description must have a non-empty media id");
            } else {
                this.mFlags = flags;
                this.mDescription = description;
            }
        }

        MediaItem(Parcel in) {
            this.mFlags = in.readInt();
            this.mDescription = (MediaDescriptionCompat) MediaDescriptionCompat.CREATOR.createFromParcel(in);
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            out.writeInt(this.mFlags);
            this.mDescription.writeToParcel(out, flags);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("MediaItem{");
            sb.append("mFlags=").append(this.mFlags);
            sb.append(", mDescription=").append(this.mDescription);
            sb.append('}');
            return sb.toString();
        }
    }

    public static abstract class SearchCallback {
        public void onSearchResult(String query, Bundle extras, List<MediaItem> list) {
        }

        public void onError(String query, Bundle extras) {
        }
    }

    private static class SearchResultReceiver extends ResultReceiver {
        private final SearchCallback mCallback;
        private final Bundle mExtras;
        private final String mQuery;

        protected void onReceiveResult(int resultCode, Bundle resultData) {
            if (resultData != null) {
                resultData.setClassLoader(MediaBrowserCompat.class.getClassLoader());
            }
            if (resultCode == 0 && resultData != null && resultData.containsKey("search_results")) {
                Parcelable[] items = resultData.getParcelableArray("search_results");
                List<MediaItem> results = null;
                if (items != null) {
                    results = new ArrayList();
                    for (Parcelable item : items) {
                        results.add((MediaItem) item);
                    }
                }
                this.mCallback.onSearchResult(this.mQuery, this.mExtras, results);
                return;
            }
            this.mCallback.onError(this.mQuery, this.mExtras);
        }
    }

    private static class ServiceBinderWrapper {
        private Messenger mMessenger;
        private Bundle mRootHints;

        public ServiceBinderWrapper(IBinder target, Bundle rootHints) {
            this.mMessenger = new Messenger(target);
            this.mRootHints = rootHints;
        }

        void connect(Context context, Messenger callbacksMessenger) throws RemoteException {
            Bundle data = new Bundle();
            data.putString("data_package_name", context.getPackageName());
            data.putBundle("data_root_hints", this.mRootHints);
            sendRequest(1, data, callbacksMessenger);
        }

        void disconnect(Messenger callbacksMessenger) throws RemoteException {
            sendRequest(2, null, callbacksMessenger);
        }

        void addSubscription(String parentId, IBinder callbackToken, Bundle options, Messenger callbacksMessenger) throws RemoteException {
            Bundle data = new Bundle();
            data.putString("data_media_item_id", parentId);
            BundleCompat.putBinder(data, "data_callback_token", callbackToken);
            data.putBundle("data_options", options);
            sendRequest(3, data, callbacksMessenger);
        }

        void registerCallbackMessenger(Messenger callbackMessenger) throws RemoteException {
            Bundle data = new Bundle();
            data.putBundle("data_root_hints", this.mRootHints);
            sendRequest(6, data, callbackMessenger);
        }

        void unregisterCallbackMessenger(Messenger callbackMessenger) throws RemoteException {
            sendRequest(7, null, callbackMessenger);
        }

        private void sendRequest(int what, Bundle data, Messenger cbMessenger) throws RemoteException {
            Message msg = Message.obtain();
            msg.what = what;
            msg.arg1 = 1;
            msg.setData(data);
            msg.replyTo = cbMessenger;
            this.mMessenger.send(msg);
        }
    }

    private static class Subscription {
        private final List<SubscriptionCallback> mCallbacks = new ArrayList();
        private final List<Bundle> mOptionsList = new ArrayList();

        public List<Bundle> getOptionsList() {
            return this.mOptionsList;
        }

        public List<SubscriptionCallback> getCallbacks() {
            return this.mCallbacks;
        }

        public SubscriptionCallback getCallback(Context context, Bundle options) {
            if (options != null) {
                options.setClassLoader(context.getClassLoader());
            }
            for (int i = 0; i < this.mOptionsList.size(); i++) {
                if (MediaBrowserCompatUtils.areSameOptions((Bundle) this.mOptionsList.get(i), options)) {
                    return (SubscriptionCallback) this.mCallbacks.get(i);
                }
            }
            return null;
        }
    }

    public static abstract class SubscriptionCallback {
        private final Object mSubscriptionCallbackObj;
        WeakReference<Subscription> mSubscriptionRef;
        private final IBinder mToken;

        private class StubApi21 implements SubscriptionCallback {
            StubApi21() {
            }

            public void onChildrenLoaded(String parentId, List<?> children) {
                Subscription sub = SubscriptionCallback.this.mSubscriptionRef == null ? null : (Subscription) SubscriptionCallback.this.mSubscriptionRef.get();
                if (sub == null) {
                    SubscriptionCallback.this.onChildrenLoaded(parentId, MediaItem.fromMediaItemList(children));
                    return;
                }
                List<MediaItem> itemList = MediaItem.fromMediaItemList(children);
                List<SubscriptionCallback> callbacks = sub.getCallbacks();
                List<Bundle> optionsList = sub.getOptionsList();
                for (int i = 0; i < callbacks.size(); i++) {
                    Bundle options = (Bundle) optionsList.get(i);
                    if (options == null) {
                        SubscriptionCallback.this.onChildrenLoaded(parentId, itemList);
                    } else {
                        SubscriptionCallback.this.onChildrenLoaded(parentId, applyOptions(itemList, options), options);
                    }
                }
            }

            public void onError(String parentId) {
                SubscriptionCallback.this.onError(parentId);
            }

            List<MediaItem> applyOptions(List<MediaItem> list, Bundle options) {
                if (list == null) {
                    return null;
                }
                int page = options.getInt("android.media.browse.extra.PAGE", -1);
                int pageSize = options.getInt("android.media.browse.extra.PAGE_SIZE", -1);
                if (page == -1 && pageSize == -1) {
                    return list;
                }
                int fromIndex = pageSize * page;
                int toIndex = fromIndex + pageSize;
                if (page < 0 || pageSize < 1 || fromIndex >= list.size()) {
                    return Collections.EMPTY_LIST;
                }
                if (toIndex > list.size()) {
                    toIndex = list.size();
                }
                return list.subList(fromIndex, toIndex);
            }
        }

        private class StubApi24 extends StubApi21 implements SubscriptionCallback {
            StubApi24() {
                super();
            }

            public void onChildrenLoaded(String parentId, List<?> children, Bundle options) {
                SubscriptionCallback.this.onChildrenLoaded(parentId, MediaItem.fromMediaItemList(children), options);
            }

            public void onError(String parentId, Bundle options) {
                SubscriptionCallback.this.onError(parentId, options);
            }
        }

        public SubscriptionCallback() {
            if (VERSION.SDK_INT >= 26) {
                this.mSubscriptionCallbackObj = MediaBrowserCompatApi24.createSubscriptionCallback(new StubApi24());
                this.mToken = null;
            } else if (VERSION.SDK_INT >= 21) {
                this.mSubscriptionCallbackObj = MediaBrowserCompatApi21.createSubscriptionCallback(new StubApi21());
                this.mToken = new Binder();
            } else {
                this.mSubscriptionCallbackObj = null;
                this.mToken = new Binder();
            }
        }

        public void onChildrenLoaded(String parentId, List<MediaItem> list) {
        }

        public void onChildrenLoaded(String parentId, List<MediaItem> list, Bundle options) {
        }

        public void onError(String parentId) {
        }

        public void onError(String parentId, Bundle options) {
        }
    }

    public MediaBrowserCompat(Context context, ComponentName serviceComponent, ConnectionCallback callback, Bundle rootHints) {
        if (VERSION.SDK_INT >= 26) {
            this.mImpl = new MediaBrowserImplApi24(context, serviceComponent, callback, rootHints);
        } else if (VERSION.SDK_INT >= 23) {
            this.mImpl = new MediaBrowserImplApi23(context, serviceComponent, callback, rootHints);
        } else if (VERSION.SDK_INT >= 21) {
            this.mImpl = new MediaBrowserImplApi21(context, serviceComponent, callback, rootHints);
        } else {
            this.mImpl = new MediaBrowserImplBase(context, serviceComponent, callback, rootHints);
        }
    }

    public void connect() {
        this.mImpl.connect();
    }

    public void disconnect() {
        this.mImpl.disconnect();
    }

    public Token getSessionToken() {
        return this.mImpl.getSessionToken();
    }
}
