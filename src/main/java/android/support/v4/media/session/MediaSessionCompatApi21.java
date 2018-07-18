package android.support.v4.media.session;

import android.media.session.MediaSession.Token;

class MediaSessionCompatApi21 {

    static class QueueItem {
        public static Object getDescription(Object queueItem) {
            return ((android.media.session.MediaSession.QueueItem) queueItem).getDescription();
        }

        public static long getQueueId(Object queueItem) {
            return ((android.media.session.MediaSession.QueueItem) queueItem).getQueueId();
        }
    }

    public static Object verifyToken(Object token) {
        if (token instanceof Token) {
            return token;
        }
        throw new IllegalArgumentException("token is not a valid MediaSession.Token object");
    }
}
