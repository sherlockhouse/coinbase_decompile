package rx.internal.operators;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;
import rx.Observable;
import rx.Observable$Operator;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.CompositeException;
import rx.exceptions.Exceptions;
import rx.exceptions.MissingBackpressureException;
import rx.exceptions.OnErrorThrowable;
import rx.internal.util.RxRingBuffer;
import rx.internal.util.ScalarSynchronousObservable;
import rx.internal.util.atomic.SpscAtomicArrayQueue;
import rx.internal.util.atomic.SpscExactAtomicArrayQueue;
import rx.internal.util.atomic.SpscUnboundedAtomicArrayQueue;
import rx.internal.util.unsafe.Pow2;
import rx.internal.util.unsafe.SpscArrayQueue;
import rx.internal.util.unsafe.UnsafeAccess;
import rx.subscriptions.CompositeSubscription;

public final class OperatorMerge<T> implements Observable$Operator<T, Observable<? extends T>> {
    final boolean delayErrors;
    final int maxConcurrent;

    static final class HolderDelayErrors {
        static final OperatorMerge<Object> INSTANCE = new OperatorMerge(true, Integer.MAX_VALUE);
    }

    static final class HolderNoDelay {
        static final OperatorMerge<Object> INSTANCE = new OperatorMerge(false, Integer.MAX_VALUE);
    }

    static final class InnerSubscriber<T> extends Subscriber<T> {
        static final int LIMIT = (RxRingBuffer.SIZE / 4);
        volatile boolean done;
        final long id;
        int outstanding;
        final MergeSubscriber<T> parent;
        volatile RxRingBuffer queue;

        public InnerSubscriber(MergeSubscriber<T> parent, long id) {
            this.parent = parent;
            this.id = id;
        }

        public void onStart() {
            this.outstanding = RxRingBuffer.SIZE;
            request((long) RxRingBuffer.SIZE);
        }

        public void onNext(T t) {
            this.parent.tryEmit(this, t);
        }

        public void onError(Throwable e) {
            this.done = true;
            this.parent.getOrCreateErrorQueue().offer(e);
            this.parent.emit();
        }

        public void onCompleted() {
            this.done = true;
            this.parent.emit();
        }

        public void requestMore(long n) {
            int r = this.outstanding - ((int) n);
            if (r > LIMIT) {
                this.outstanding = r;
                return;
            }
            this.outstanding = RxRingBuffer.SIZE;
            int k = RxRingBuffer.SIZE - r;
            if (k > 0) {
                request((long) k);
            }
        }
    }

    static final class MergeProducer<T> extends AtomicLong implements Producer {
        final MergeSubscriber<T> subscriber;

        public MergeProducer(MergeSubscriber<T> subscriber) {
            this.subscriber = subscriber;
        }

        public void request(long n) {
            if (n > 0) {
                if (get() != Long.MAX_VALUE) {
                    BackpressureUtils.getAndAddRequest(this, n);
                    this.subscriber.emit();
                }
            } else if (n < 0) {
                throw new IllegalArgumentException("n >= 0 required");
            }
        }

        public long produced(int n) {
            return addAndGet((long) (-n));
        }
    }

    static final class MergeSubscriber<T> extends Subscriber<Observable<? extends T>> {
        static final InnerSubscriber<?>[] EMPTY = new InnerSubscriber[0];
        final Subscriber<? super T> child;
        final boolean delayErrors;
        volatile boolean done;
        boolean emitting;
        volatile ConcurrentLinkedQueue<Throwable> errors;
        final Object innerGuard = new Object();
        volatile InnerSubscriber<?>[] innerSubscribers = EMPTY;
        long lastId;
        int lastIndex;
        final int maxConcurrent;
        boolean missed;
        MergeProducer<T> producer;
        volatile Queue<Object> queue;
        int scalarEmissionCount;
        final int scalarEmissionLimit;
        volatile CompositeSubscription subscriptions;
        long uniqueId;

        public MergeSubscriber(Subscriber<? super T> child, boolean delayErrors, int maxConcurrent) {
            this.child = child;
            this.delayErrors = delayErrors;
            this.maxConcurrent = maxConcurrent;
            if (maxConcurrent == Integer.MAX_VALUE) {
                this.scalarEmissionLimit = Integer.MAX_VALUE;
                request(Long.MAX_VALUE);
                return;
            }
            this.scalarEmissionLimit = Math.max(1, maxConcurrent >> 1);
            request((long) maxConcurrent);
        }

        Queue<Throwable> getOrCreateErrorQueue() {
            ConcurrentLinkedQueue<Throwable> q = this.errors;
            if (q == null) {
                synchronized (this) {
                    try {
                        q = this.errors;
                        if (q == null) {
                            ConcurrentLinkedQueue<Throwable> q2 = new ConcurrentLinkedQueue();
                            try {
                                this.errors = q2;
                                q = q2;
                            } catch (Throwable th) {
                                Throwable th2 = th;
                                q = q2;
                                throw th2;
                            }
                        }
                    } catch (Throwable th3) {
                        th2 = th3;
                        throw th2;
                    }
                }
            }
            return q;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        CompositeSubscription getOrCreateComposite() {
            Throwable th;
            CompositeSubscription c = this.subscriptions;
            if (c == null) {
                boolean shouldAdd = false;
                synchronized (this) {
                    c = this.subscriptions;
                    if (c == null) {
                        CompositeSubscription c2 = new CompositeSubscription();
                        try {
                            this.subscriptions = c2;
                            shouldAdd = true;
                            c = c2;
                        } catch (Throwable th2) {
                            th = th2;
                            c = c2;
                            throw th;
                        }
                    }
                    try {
                    } catch (Throwable th3) {
                        th = th3;
                        throw th;
                    }
                }
            }
            return c;
        }

        public void onNext(Observable<? extends T> t) {
            if (t != null) {
                if (t == Observable.empty()) {
                    emitEmpty();
                } else if (t instanceof ScalarSynchronousObservable) {
                    tryEmit(((ScalarSynchronousObservable) t).get());
                } else {
                    long j = this.uniqueId;
                    this.uniqueId = 1 + j;
                    InnerSubscriber<T> inner = new InnerSubscriber(this, j);
                    addInner(inner);
                    t.unsafeSubscribe(inner);
                    emit();
                }
            }
        }

        void emitEmpty() {
            int produced = this.scalarEmissionCount + 1;
            if (produced == this.scalarEmissionLimit) {
                this.scalarEmissionCount = 0;
                requestMore((long) produced);
                return;
            }
            this.scalarEmissionCount = produced;
        }

        private void reportError() {
            List<Throwable> list = new ArrayList(this.errors);
            if (list.size() == 1) {
                this.child.onError((Throwable) list.get(0));
            } else {
                this.child.onError(new CompositeException(list));
            }
        }

        public void onError(Throwable e) {
            getOrCreateErrorQueue().offer(e);
            this.done = true;
            emit();
        }

        public void onCompleted() {
            this.done = true;
            emit();
        }

        void addInner(InnerSubscriber<T> inner) {
            getOrCreateComposite().add(inner);
            synchronized (this.innerGuard) {
                InnerSubscriber<?>[] a = this.innerSubscribers;
                int n = a.length;
                InnerSubscriber<?>[] b = new InnerSubscriber[(n + 1)];
                System.arraycopy(a, 0, b, 0, n);
                b[n] = inner;
                this.innerSubscribers = b;
            }
        }

        void removeInner(InnerSubscriber<T> inner) {
            RxRingBuffer q = inner.queue;
            if (q != null) {
                q.release();
            }
            this.subscriptions.remove(inner);
            synchronized (this.innerGuard) {
                InnerSubscriber<?>[] a = this.innerSubscribers;
                int n = a.length;
                int j = -1;
                for (int i = 0; i < n; i++) {
                    if (inner.equals(a[i])) {
                        j = i;
                        break;
                    }
                }
                if (j < 0) {
                } else if (n == 1) {
                    this.innerSubscribers = EMPTY;
                } else {
                    InnerSubscriber<?>[] b = new InnerSubscriber[(n - 1)];
                    System.arraycopy(a, 0, b, 0, j);
                    System.arraycopy(a, j + 1, b, j, (n - j) - 1);
                    this.innerSubscribers = b;
                }
            }
        }

        void tryEmit(InnerSubscriber<T> subscriber, T value) {
            boolean success = false;
            long r = this.producer.get();
            if (r != 0) {
                synchronized (this) {
                    r = this.producer.get();
                    if (!(this.emitting || r == 0)) {
                        this.emitting = true;
                        success = true;
                    }
                }
            }
            if (success) {
                RxRingBuffer subscriberQueue = subscriber.queue;
                if (subscriberQueue == null || subscriberQueue.isEmpty()) {
                    emitScalar(subscriber, value, r);
                    return;
                }
                queueScalar(subscriber, value);
                emitLoop();
                return;
            }
            queueScalar(subscriber, value);
            emit();
        }

        protected void queueScalar(InnerSubscriber<T> subscriber, T value) {
            RxRingBuffer q = subscriber.queue;
            if (q == null) {
                q = RxRingBuffer.getSpscInstance();
                subscriber.add(q);
                subscriber.queue = q;
            }
            try {
                q.onNext(NotificationLite.next(value));
            } catch (MissingBackpressureException ex) {
                subscriber.unsubscribe();
                subscriber.onError(ex);
            } catch (IllegalStateException ex2) {
                if (!subscriber.isUnsubscribed()) {
                    subscriber.unsubscribe();
                    subscriber.onError(ex2);
                }
            }
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        protected void emitScalar(InnerSubscriber<T> subscriber, T value, long r) {
            boolean skipFinal = false;
            try {
                this.child.onNext(value);
            } catch (Throwable th) {
                if (!skipFinal) {
                    synchronized (this) {
                        this.emitting = false;
                    }
                }
            }
            if (r != Long.MAX_VALUE) {
                this.producer.produced(1);
            }
            subscriber.requestMore(1);
            synchronized (this) {
                skipFinal = true;
                if (this.missed) {
                    this.missed = false;
                } else {
                    this.emitting = false;
                }
            }
        }

        public void requestMore(long n) {
            request(n);
        }

        void tryEmit(T value) {
            boolean success = false;
            long r = this.producer.get();
            if (r != 0) {
                synchronized (this) {
                    r = this.producer.get();
                    if (!(this.emitting || r == 0)) {
                        this.emitting = true;
                        success = true;
                    }
                }
            }
            if (success) {
                Queue<Object> mainQueue = this.queue;
                if (mainQueue == null || mainQueue.isEmpty()) {
                    emitScalar(value, r);
                    return;
                }
                queueScalar(value);
                emitLoop();
                return;
            }
            queueScalar(value);
            emit();
        }

        protected void queueScalar(T value) {
            Queue<Object> q = this.queue;
            if (q == null) {
                int mc = this.maxConcurrent;
                if (mc == Integer.MAX_VALUE) {
                    q = new SpscUnboundedAtomicArrayQueue(RxRingBuffer.SIZE);
                } else if (!Pow2.isPowerOfTwo(mc)) {
                    q = new SpscExactAtomicArrayQueue(mc);
                } else if (UnsafeAccess.isUnsafeAvailable()) {
                    q = new SpscArrayQueue(mc);
                } else {
                    q = new SpscAtomicArrayQueue(mc);
                }
                this.queue = q;
            }
            if (!q.offer(NotificationLite.next(value))) {
                unsubscribe();
                onError(OnErrorThrowable.addValueAsLastCause(new MissingBackpressureException(), value));
            }
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        protected void emitScalar(T value, long r) {
            boolean skipFinal = false;
            try {
                this.child.onNext(value);
            } catch (Throwable th) {
                if (!skipFinal) {
                    synchronized (this) {
                        this.emitting = false;
                    }
                }
            }
            if (r != Long.MAX_VALUE) {
                this.producer.produced(1);
            }
            int produced = this.scalarEmissionCount + 1;
            if (produced == this.scalarEmissionLimit) {
                this.scalarEmissionCount = 0;
                requestMore((long) produced);
            } else {
                this.scalarEmissionCount = produced;
            }
            synchronized (this) {
                skipFinal = true;
                if (this.missed) {
                    this.missed = false;
                } else {
                    this.emitting = false;
                }
            }
        }

        void emit() {
            synchronized (this) {
                if (this.emitting) {
                    this.missed = true;
                    return;
                }
                this.emitting = true;
                emitLoop();
            }
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        void emitLoop() {
            boolean skipFinal = false;
            Subscriber<? super T> child = this.child;
            while (!checkTerminate()) {
                Object obj;
                Queue<Object> svq = this.queue;
                long r = this.producer.get();
                boolean unbounded = r == Long.MAX_VALUE;
                int i = 0;
                if (svq != null) {
                    do {
                        int scalarEmission = 0;
                        obj = null;
                        while (r > 0) {
                            obj = svq.poll();
                            if (checkTerminate()) {
                                if (!true) {
                                    synchronized (this) {
                                        this.emitting = false;
                                    }
                                    return;
                                }
                                return;
                            } else if (obj == null) {
                                break;
                            } else {
                                try {
                                    child.onNext(NotificationLite.getValue(obj));
                                } catch (Throwable th) {
                                    if (!skipFinal) {
                                        synchronized (this) {
                                            this.emitting = false;
                                        }
                                    }
                                }
                                i++;
                                scalarEmission++;
                                r--;
                            }
                        }
                        if (scalarEmission > 0) {
                            if (unbounded) {
                                r = Long.MAX_VALUE;
                            } else {
                                r = this.producer.produced(scalarEmission);
                            }
                        }
                        if (r != 0) {
                        }
                    } while (obj != null);
                }
                boolean d = this.done;
                svq = this.queue;
                InnerSubscriber<?>[] inner = this.innerSubscribers;
                int n = inner.length;
                if (d && ((svq == null || svq.isEmpty()) && n == 0)) {
                    Queue<Throwable> e = this.errors;
                    if (e == null || e.isEmpty()) {
                        child.onCompleted();
                    } else {
                        reportError();
                    }
                    if (!true) {
                        synchronized (this) {
                            this.emitting = false;
                        }
                        return;
                    }
                    return;
                }
                boolean innerCompleted = false;
                if (n > 0) {
                    int j;
                    int i2;
                    long startId = this.lastId;
                    int index = this.lastIndex;
                    if (n <= index || inner[index].id != startId) {
                        if (n <= index) {
                            index = 0;
                        }
                        j = index;
                        for (i2 = 0; i2 < n && inner[j].id != startId; i2++) {
                            j++;
                            if (j == n) {
                                j = 0;
                            }
                        }
                        index = j;
                        this.lastIndex = j;
                        this.lastId = inner[j].id;
                    }
                    j = index;
                    i2 = 0;
                    while (i2 < n) {
                        if (!checkTerminate()) {
                            InnerSubscriber<T> is = inner[j];
                            obj = null;
                            do {
                                int produced = 0;
                                while (r > 0) {
                                    if (!checkTerminate()) {
                                        RxRingBuffer q = is.queue;
                                        if (q != null) {
                                            obj = q.poll();
                                            if (obj == null) {
                                                break;
                                            }
                                            try {
                                                child.onNext(NotificationLite.getValue(obj));
                                                r--;
                                                produced++;
                                            } catch (Throwable t) {
                                                skipFinal = true;
                                                Exceptions.throwIfFatal(t);
                                                child.onError(t);
                                                if (1 == null) {
                                                    synchronized (this) {
                                                        this.emitting = false;
                                                        return;
                                                    }
                                                }
                                                return;
                                            } finally {
                                                unsubscribe();
                                            }
                                        } else {
                                            break;
                                        }
                                    } else if (!true) {
                                        synchronized (this) {
                                            this.emitting = false;
                                        }
                                        return;
                                    } else {
                                        return;
                                    }
                                }
                                if (produced > 0) {
                                    if (unbounded) {
                                        r = Long.MAX_VALUE;
                                    } else {
                                        r = this.producer.produced(produced);
                                    }
                                    is.requestMore((long) produced);
                                }
                                if (r == 0) {
                                    break;
                                }
                            } while (obj != null);
                            boolean innerDone = is.done;
                            RxRingBuffer innerQueue = is.queue;
                            if (innerDone && (innerQueue == null || innerQueue.isEmpty())) {
                                removeInner(is);
                                if (!checkTerminate()) {
                                    i++;
                                    innerCompleted = true;
                                } else if (!true) {
                                    synchronized (this) {
                                        this.emitting = false;
                                    }
                                    return;
                                } else {
                                    return;
                                }
                            }
                            if (r == 0) {
                                break;
                            }
                            j++;
                            if (j == n) {
                                j = 0;
                            }
                            i2++;
                        } else if (!true) {
                            synchronized (this) {
                                this.emitting = false;
                            }
                            return;
                        } else {
                            return;
                        }
                    }
                    this.lastIndex = j;
                    this.lastId = inner[j].id;
                }
                if (i > 0) {
                    request((long) i);
                }
                if (!innerCompleted) {
                    synchronized (this) {
                        if (this.missed) {
                            this.missed = false;
                        } else {
                            this.emitting = false;
                        }
                    }
                }
            }
            if (!true) {
                synchronized (this) {
                    this.emitting = false;
                }
            }
        }

        boolean checkTerminate() {
            if (this.child.isUnsubscribed()) {
                return true;
            }
            Queue<Throwable> e = this.errors;
            if (this.delayErrors || e == null || e.isEmpty()) {
                return false;
            }
            try {
                reportError();
                return true;
            } finally {
                unsubscribe();
            }
        }
    }

    public static <T> OperatorMerge<T> instance(boolean delayErrors) {
        if (delayErrors) {
            return HolderDelayErrors.INSTANCE;
        }
        return HolderNoDelay.INSTANCE;
    }

    OperatorMerge(boolean delayErrors, int maxConcurrent) {
        this.delayErrors = delayErrors;
        this.maxConcurrent = maxConcurrent;
    }

    public Subscriber<Observable<? extends T>> call(Subscriber<? super T> child) {
        MergeSubscriber<T> subscriber = new MergeSubscriber(child, this.delayErrors, this.maxConcurrent);
        MergeProducer<T> producer = new MergeProducer(subscriber);
        subscriber.producer = producer;
        child.add(subscriber);
        child.setProducer(producer);
        return subscriber;
    }
}
