package android.support.v4.media.session;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.IBinder.DeathRecipient;
import android.os.Message;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.support.v4.app.BundleCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.IMediaControllerCallback.Stub;
import android.support.v4.media.session.MediaSessionCompat.QueueItem;
import android.support.v4.media.session.MediaSessionCompat.Token;
import android.util.Log;
import android.view.KeyEvent;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public final class MediaControllerCompat {
    private final MediaControllerImpl mImpl;
    private final HashSet<Callback> mRegisteredCallbacks = new HashSet();
    private final Token mToken;

    public static abstract class Callback implements DeathRecipient {
        private final Object mCallbackObj;
        MessageHandler mHandler;
        boolean mHasExtraCallback;

        private class MessageHandler extends Handler {
            boolean mRegistered;
            final /* synthetic */ Callback this$0;

            public void handleMessage(Message msg) {
                if (this.mRegistered) {
                    switch (msg.what) {
                        case 1:
                            this.this$0.onSessionEvent((String) msg.obj, msg.getData());
                            return;
                        case 2:
                            this.this$0.onPlaybackStateChanged((PlaybackStateCompat) msg.obj);
                            return;
                        case 3:
                            this.this$0.onMetadataChanged((MediaMetadataCompat) msg.obj);
                            return;
                        case 4:
                            this.this$0.onAudioInfoChanged((PlaybackInfo) msg.obj);
                            return;
                        case 5:
                            this.this$0.onQueueChanged((List) msg.obj);
                            return;
                        case 6:
                            this.this$0.onQueueTitleChanged((CharSequence) msg.obj);
                            return;
                        case 7:
                            this.this$0.onExtrasChanged((Bundle) msg.obj);
                            return;
                        case 8:
                            this.this$0.onSessionDestroyed();
                            return;
                        case 9:
                            this.this$0.onRepeatModeChanged(((Integer) msg.obj).intValue());
                            return;
                        case 10:
                            this.this$0.onShuffleModeChanged(((Boolean) msg.obj).booleanValue());
                            return;
                        case 11:
                            this.this$0.onCaptioningEnabledChanged(((Boolean) msg.obj).booleanValue());
                            return;
                        case 12:
                            this.this$0.onShuffleModeChanged(((Integer) msg.obj).intValue());
                            return;
                        default:
                            return;
                    }
                }
            }
        }

        private static class StubApi21 implements android.support.v4.media.session.MediaControllerCompatApi21.Callback {
            private final WeakReference<Callback> mCallback;

            StubApi21(Callback callback) {
                this.mCallback = new WeakReference(callback);
            }

            public void onSessionDestroyed() {
                Callback callback = (Callback) this.mCallback.get();
                if (callback != null) {
                    callback.onSessionDestroyed();
                }
            }

            public void onSessionEvent(String event, Bundle extras) {
                Callback callback = (Callback) this.mCallback.get();
                if (callback == null) {
                    return;
                }
                if (!callback.mHasExtraCallback || VERSION.SDK_INT >= 23) {
                    callback.onSessionEvent(event, extras);
                }
            }

            public void onPlaybackStateChanged(Object stateObj) {
                Callback callback = (Callback) this.mCallback.get();
                if (callback != null && !callback.mHasExtraCallback) {
                    callback.onPlaybackStateChanged(PlaybackStateCompat.fromPlaybackState(stateObj));
                }
            }

            public void onMetadataChanged(Object metadataObj) {
                Callback callback = (Callback) this.mCallback.get();
                if (callback != null) {
                    callback.onMetadataChanged(MediaMetadataCompat.fromMediaMetadata(metadataObj));
                }
            }

            public void onQueueChanged(List<?> queue) {
                Callback callback = (Callback) this.mCallback.get();
                if (callback != null) {
                    callback.onQueueChanged(QueueItem.fromQueueItemList(queue));
                }
            }

            public void onQueueTitleChanged(CharSequence title) {
                Callback callback = (Callback) this.mCallback.get();
                if (callback != null) {
                    callback.onQueueTitleChanged(title);
                }
            }

            public void onExtrasChanged(Bundle extras) {
                Callback callback = (Callback) this.mCallback.get();
                if (callback != null) {
                    callback.onExtrasChanged(extras);
                }
            }

            public void onAudioInfoChanged(int type, int stream, int control, int max, int current) {
                Callback callback = (Callback) this.mCallback.get();
                if (callback != null) {
                    callback.onAudioInfoChanged(new PlaybackInfo(type, stream, control, max, current));
                }
            }
        }

        private static class StubCompat extends Stub {
            private final WeakReference<Callback> mCallback;

            StubCompat(Callback callback) {
                this.mCallback = new WeakReference(callback);
            }

            public void onEvent(String event, Bundle extras) throws RemoteException {
                Callback callback = (Callback) this.mCallback.get();
                if (callback != null) {
                    callback.postToHandler(1, event, extras);
                }
            }

            public void onSessionDestroyed() throws RemoteException {
                Callback callback = (Callback) this.mCallback.get();
                if (callback != null) {
                    callback.postToHandler(8, null, null);
                }
            }

            public void onPlaybackStateChanged(PlaybackStateCompat state) throws RemoteException {
                Callback callback = (Callback) this.mCallback.get();
                if (callback != null) {
                    callback.postToHandler(2, state, null);
                }
            }

            public void onMetadataChanged(MediaMetadataCompat metadata) throws RemoteException {
                Callback callback = (Callback) this.mCallback.get();
                if (callback != null) {
                    callback.postToHandler(3, metadata, null);
                }
            }

            public void onQueueChanged(List<QueueItem> queue) throws RemoteException {
                Callback callback = (Callback) this.mCallback.get();
                if (callback != null) {
                    callback.postToHandler(5, queue, null);
                }
            }

            public void onQueueTitleChanged(CharSequence title) throws RemoteException {
                Callback callback = (Callback) this.mCallback.get();
                if (callback != null) {
                    callback.postToHandler(6, title, null);
                }
            }

            public void onCaptioningEnabledChanged(boolean enabled) throws RemoteException {
                Callback callback = (Callback) this.mCallback.get();
                if (callback != null) {
                    callback.postToHandler(11, Boolean.valueOf(enabled), null);
                }
            }

            public void onRepeatModeChanged(int repeatMode) throws RemoteException {
                Callback callback = (Callback) this.mCallback.get();
                if (callback != null) {
                    callback.postToHandler(9, Integer.valueOf(repeatMode), null);
                }
            }

            public void onShuffleModeChangedDeprecated(boolean enabled) throws RemoteException {
                Callback callback = (Callback) this.mCallback.get();
                if (callback != null) {
                    callback.postToHandler(10, Boolean.valueOf(enabled), null);
                }
            }

            public void onShuffleModeChanged(int shuffleMode) throws RemoteException {
                Callback callback = (Callback) this.mCallback.get();
                if (callback != null) {
                    callback.postToHandler(12, Integer.valueOf(shuffleMode), null);
                }
            }

            public void onExtrasChanged(Bundle extras) throws RemoteException {
                Callback callback = (Callback) this.mCallback.get();
                if (callback != null) {
                    callback.postToHandler(7, extras, null);
                }
            }

            public void onVolumeInfoChanged(ParcelableVolumeInfo info) throws RemoteException {
                Callback callback = (Callback) this.mCallback.get();
                if (callback != null) {
                    PlaybackInfo pi = null;
                    if (info != null) {
                        pi = new PlaybackInfo(info.volumeType, info.audioStream, info.controlType, info.maxVolume, info.currentVolume);
                    }
                    callback.postToHandler(4, pi, null);
                }
            }
        }

        public Callback() {
            if (VERSION.SDK_INT >= 21) {
                this.mCallbackObj = MediaControllerCompatApi21.createCallback(new StubApi21(this));
            } else {
                this.mCallbackObj = new StubCompat(this);
            }
        }

        public void onSessionDestroyed() {
        }

        public void onSessionEvent(String event, Bundle extras) {
        }

        public void onPlaybackStateChanged(PlaybackStateCompat state) {
        }

        public void onMetadataChanged(MediaMetadataCompat metadata) {
        }

        public void onQueueChanged(List<QueueItem> list) {
        }

        public void onQueueTitleChanged(CharSequence title) {
        }

        public void onExtrasChanged(Bundle extras) {
        }

        public void onAudioInfoChanged(PlaybackInfo info) {
        }

        public void onCaptioningEnabledChanged(boolean enabled) {
        }

        public void onRepeatModeChanged(int repeatMode) {
        }

        @Deprecated
        public void onShuffleModeChanged(boolean enabled) {
        }

        public void onShuffleModeChanged(int shuffleMode) {
        }

        void postToHandler(int what, Object obj, Bundle data) {
            if (this.mHandler != null) {
                Message msg = this.mHandler.obtainMessage(what, obj);
                msg.setData(data);
                msg.sendToTarget();
            }
        }
    }

    interface MediaControllerImpl {
        boolean dispatchMediaButtonEvent(KeyEvent keyEvent);
    }

    static class MediaControllerImplApi21 implements MediaControllerImpl {
        private HashMap<Callback, ExtraCallback> mCallbackMap = new HashMap();
        protected final Object mControllerObj;
        private IMediaSession mExtraBinder;
        private final List<Callback> mPendingCallbacks = new ArrayList();

        private static class ExtraBinderRequestResultReceiver extends ResultReceiver {
            private WeakReference<MediaControllerImplApi21> mMediaControllerImpl;

            public ExtraBinderRequestResultReceiver(MediaControllerImplApi21 mediaControllerImpl, Handler handler) {
                super(handler);
                this.mMediaControllerImpl = new WeakReference(mediaControllerImpl);
            }

            protected void onReceiveResult(int resultCode, Bundle resultData) {
                MediaControllerImplApi21 mediaControllerImpl = (MediaControllerImplApi21) this.mMediaControllerImpl.get();
                if (mediaControllerImpl != null && resultData != null) {
                    mediaControllerImpl.mExtraBinder = IMediaSession.Stub.asInterface(BundleCompat.getBinder(resultData, "android.support.v4.media.session.EXTRA_BINDER"));
                    mediaControllerImpl.processPendingCallbacks();
                }
            }
        }

        private static class ExtraCallback extends StubCompat {
            ExtraCallback(Callback callback) {
                super(callback);
            }

            public void onSessionDestroyed() throws RemoteException {
                throw new AssertionError();
            }

            public void onMetadataChanged(MediaMetadataCompat metadata) throws RemoteException {
                throw new AssertionError();
            }

            public void onQueueChanged(List<QueueItem> list) throws RemoteException {
                throw new AssertionError();
            }

            public void onQueueTitleChanged(CharSequence title) throws RemoteException {
                throw new AssertionError();
            }

            public void onExtrasChanged(Bundle extras) throws RemoteException {
                throw new AssertionError();
            }

            public void onVolumeInfoChanged(ParcelableVolumeInfo info) throws RemoteException {
                throw new AssertionError();
            }
        }

        public MediaControllerImplApi21(Context context, Token sessionToken) throws RemoteException {
            this.mControllerObj = MediaControllerCompatApi21.fromToken(context, sessionToken.getToken());
            if (this.mControllerObj == null) {
                throw new RemoteException();
            }
            this.mExtraBinder = sessionToken.getExtraBinder();
            if (this.mExtraBinder == null) {
                requestExtraBinder();
            }
        }

        public boolean dispatchMediaButtonEvent(KeyEvent event) {
            return MediaControllerCompatApi21.dispatchMediaButtonEvent(this.mControllerObj, event);
        }

        public void sendCommand(String command, Bundle params, ResultReceiver cb) {
            MediaControllerCompatApi21.sendCommand(this.mControllerObj, command, params, cb);
        }

        private void requestExtraBinder() {
            sendCommand("android.support.v4.media.session.command.GET_EXTRA_BINDER", null, new ExtraBinderRequestResultReceiver(this, new Handler()));
        }

        private void processPendingCallbacks() {
            if (this.mExtraBinder != null) {
                synchronized (this.mPendingCallbacks) {
                    for (Callback callback : this.mPendingCallbacks) {
                        ExtraCallback extraCallback = new ExtraCallback(callback);
                        this.mCallbackMap.put(callback, extraCallback);
                        callback.mHasExtraCallback = true;
                        try {
                            this.mExtraBinder.registerCallbackListener(extraCallback);
                        } catch (RemoteException e) {
                            Log.e("MediaControllerCompat", "Dead object in registerCallback.", e);
                        }
                    }
                    this.mPendingCallbacks.clear();
                }
            }
        }
    }

    static class MediaControllerImplApi23 extends MediaControllerImplApi21 {
        public MediaControllerImplApi23(Context context, Token sessionToken) throws RemoteException {
            super(context, sessionToken);
        }
    }

    static class MediaControllerImplApi24 extends MediaControllerImplApi23 {
        public MediaControllerImplApi24(Context context, Token sessionToken) throws RemoteException {
            super(context, sessionToken);
        }
    }

    static class MediaControllerImplBase implements MediaControllerImpl {
        private IMediaSession mBinder;

        public MediaControllerImplBase(Token token) {
            this.mBinder = IMediaSession.Stub.asInterface((IBinder) token.getToken());
        }

        public boolean dispatchMediaButtonEvent(KeyEvent event) {
            if (event == null) {
                throw new IllegalArgumentException("event may not be null.");
            }
            try {
                this.mBinder.sendMediaButton(event);
            } catch (RemoteException e) {
                Log.e("MediaControllerCompat", "Dead object in dispatchMediaButtonEvent.", e);
            }
            return false;
        }
    }

    public static final class PlaybackInfo {
        private final int mAudioStream;
        private final int mCurrentVolume;
        private final int mMaxVolume;
        private final int mPlaybackType;
        private final int mVolumeControl;

        PlaybackInfo(int type, int stream, int control, int max, int current) {
            this.mPlaybackType = type;
            this.mAudioStream = stream;
            this.mVolumeControl = control;
            this.mMaxVolume = max;
            this.mCurrentVolume = current;
        }
    }

    public MediaControllerCompat(Context context, Token sessionToken) throws RemoteException {
        if (sessionToken == null) {
            throw new IllegalArgumentException("sessionToken must not be null");
        }
        this.mToken = sessionToken;
        if (VERSION.SDK_INT >= 24) {
            this.mImpl = new MediaControllerImplApi24(context, sessionToken);
        } else if (VERSION.SDK_INT >= 23) {
            this.mImpl = new MediaControllerImplApi23(context, sessionToken);
        } else if (VERSION.SDK_INT >= 21) {
            this.mImpl = new MediaControllerImplApi21(context, sessionToken);
        } else {
            this.mImpl = new MediaControllerImplBase(this.mToken);
        }
    }

    public boolean dispatchMediaButtonEvent(KeyEvent keyEvent) {
        if (keyEvent != null) {
            return this.mImpl.dispatchMediaButtonEvent(keyEvent);
        }
        throw new IllegalArgumentException("KeyEvent may not be null");
    }
}
