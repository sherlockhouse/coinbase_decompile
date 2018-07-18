package rx.internal.util;

import java.util.Queue;
import rx.Subscription;
import rx.exceptions.MissingBackpressureException;
import rx.internal.operators.NotificationLite;
import rx.internal.util.atomic.SpscAtomicArrayQueue;
import rx.internal.util.unsafe.SpmcArrayQueue;
import rx.internal.util.unsafe.SpscArrayQueue;
import rx.internal.util.unsafe.UnsafeAccess;

public class RxRingBuffer implements Subscription {
    public static final int SIZE;
    private Queue<Object> queue;
    private final int size;
    public volatile Object terminalState;

    static {
        int defaultSize = 128;
        if (PlatformDependent.isAndroid()) {
            defaultSize = 16;
        }
        String sizeFromProperty = System.getProperty("rx.ring-buffer.size");
        if (sizeFromProperty != null) {
            try {
                defaultSize = Integer.parseInt(sizeFromProperty);
            } catch (NumberFormatException e) {
                System.err.println("Failed to set 'rx.buffer.size' with value " + sizeFromProperty + " => " + e.getMessage());
            }
        }
        SIZE = defaultSize;
    }

    public static RxRingBuffer getSpscInstance() {
        if (UnsafeAccess.isUnsafeAvailable()) {
            return new RxRingBuffer(false, SIZE);
        }
        return new RxRingBuffer();
    }

    private RxRingBuffer(Queue<Object> queue, int size) {
        this.queue = queue;
        this.size = size;
    }

    private RxRingBuffer(boolean spmc, int size) {
        this.queue = spmc ? new SpmcArrayQueue(size) : new SpscArrayQueue(size);
        this.size = size;
    }

    public synchronized void release() {
    }

    public void unsubscribe() {
        release();
    }

    RxRingBuffer() {
        this(new SpscAtomicArrayQueue(SIZE), SIZE);
    }

    public void onNext(Object o) throws MissingBackpressureException {
        boolean iae = false;
        boolean mbe = false;
        synchronized (this) {
            Queue<Object> q = this.queue;
            if (q != null) {
                mbe = !q.offer(NotificationLite.next(o));
            } else {
                iae = true;
            }
        }
        if (iae) {
            throw new IllegalStateException("This instance has been unsubscribed and the queue is no longer usable.");
        } else if (mbe) {
            throw new MissingBackpressureException();
        }
    }

    public boolean isEmpty() {
        Queue<Object> q = this.queue;
        return q == null || q.isEmpty();
    }

    public Object poll() {
        Object obj = null;
        synchronized (this) {
            Queue<Object> q = this.queue;
            if (q == null) {
            } else {
                obj = q.poll();
                Object ts = this.terminalState;
                if (obj == null && ts != null && q.peek() == null) {
                    obj = ts;
                    this.terminalState = null;
                }
            }
        }
        return obj;
    }

    public boolean isUnsubscribed() {
        return this.queue == null;
    }
}
