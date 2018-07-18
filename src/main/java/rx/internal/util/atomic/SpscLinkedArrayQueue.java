package rx.internal.util.atomic;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReferenceArray;
import rx.internal.util.unsafe.Pow2;

public final class SpscLinkedArrayQueue<T> implements Queue<T> {
    private static final Object HAS_NEXT = new Object();
    static final int MAX_LOOK_AHEAD_STEP = Integer.getInteger("jctools.spsc.max.lookahead.step", 4096).intValue();
    AtomicReferenceArray<Object> consumerBuffer;
    final AtomicLong consumerIndex = new AtomicLong();
    int consumerMask;
    AtomicReferenceArray<Object> producerBuffer;
    final AtomicLong producerIndex = new AtomicLong();
    long producerLookAhead;
    int producerLookAheadStep;
    int producerMask;

    public SpscLinkedArrayQueue(int bufferSize) {
        int p2capacity = Pow2.roundToPowerOfTwo(bufferSize);
        int mask = p2capacity - 1;
        AtomicReferenceArray<Object> buffer = new AtomicReferenceArray(p2capacity + 1);
        this.producerBuffer = buffer;
        this.producerMask = mask;
        adjustLookAheadStep(p2capacity);
        this.consumerBuffer = buffer;
        this.consumerMask = mask;
        this.producerLookAhead = (long) (mask - 1);
    }

    public boolean offer(T e) {
        AtomicReferenceArray<Object> buffer = this.producerBuffer;
        long index = lpProducerIndex();
        int mask = this.producerMask;
        int offset = calcWrappedOffset(index, mask);
        if (index < this.producerLookAhead) {
            return writeToQueue(buffer, e, index, offset);
        }
        int lookAheadStep = this.producerLookAheadStep;
        if (lvElement(buffer, calcWrappedOffset(((long) lookAheadStep) + index, mask)) == null) {
            this.producerLookAhead = (((long) lookAheadStep) + index) - 1;
            return writeToQueue(buffer, e, index, offset);
        } else if (lvElement(buffer, calcWrappedOffset(1 + index, mask)) == null) {
            return writeToQueue(buffer, e, index, offset);
        } else {
            resize(buffer, index, offset, e, (long) mask);
            return true;
        }
    }

    private boolean writeToQueue(AtomicReferenceArray<Object> buffer, T e, long index, int offset) {
        soElement(buffer, offset, e);
        soProducerIndex(1 + index);
        return true;
    }

    private void resize(AtomicReferenceArray<Object> oldBuffer, long currIndex, int offset, T e, long mask) {
        AtomicReferenceArray<Object> newBuffer = new AtomicReferenceArray(oldBuffer.length());
        this.producerBuffer = newBuffer;
        this.producerLookAhead = (currIndex + mask) - 1;
        soElement(newBuffer, offset, e);
        soNext(oldBuffer, newBuffer);
        soElement(oldBuffer, offset, HAS_NEXT);
        soProducerIndex(currIndex + 1);
    }

    private void soNext(AtomicReferenceArray<Object> curr, AtomicReferenceArray<Object> next) {
        soElement(curr, calcDirectOffset(curr.length() - 1), next);
    }

    private AtomicReferenceArray<Object> lvNext(AtomicReferenceArray<Object> curr) {
        return (AtomicReferenceArray) lvElement(curr, calcDirectOffset(curr.length() - 1));
    }

    public T poll() {
        AtomicReferenceArray<Object> buffer = this.consumerBuffer;
        long index = lpConsumerIndex();
        int mask = this.consumerMask;
        int offset = calcWrappedOffset(index, mask);
        Object e = lvElement(buffer, offset);
        boolean isNextBuffer = e == HAS_NEXT;
        if (e == null || isNextBuffer) {
            return isNextBuffer ? newBufferPoll(lvNext(buffer), index, mask) : null;
        } else {
            soElement(buffer, offset, null);
            soConsumerIndex(1 + index);
            return e;
        }
    }

    private T newBufferPoll(AtomicReferenceArray<Object> nextBuffer, long index, int mask) {
        this.consumerBuffer = nextBuffer;
        int offsetInNew = calcWrappedOffset(index, mask);
        T n = lvElement(nextBuffer, offsetInNew);
        if (n == null) {
            return null;
        }
        soElement(nextBuffer, offsetInNew, null);
        soConsumerIndex(1 + index);
        return n;
    }

    public T peek() {
        AtomicReferenceArray<Object> buffer = this.consumerBuffer;
        long index = lpConsumerIndex();
        int mask = this.consumerMask;
        Object e = lvElement(buffer, calcWrappedOffset(index, mask));
        if (e == HAS_NEXT) {
            return newBufferPeek(lvNext(buffer), index, mask);
        }
        return e;
    }

    public void clear() {
        while (true) {
            if (poll() == null && isEmpty()) {
                return;
            }
        }
    }

    private T newBufferPeek(AtomicReferenceArray<Object> nextBuffer, long index, int mask) {
        this.consumerBuffer = nextBuffer;
        return lvElement(nextBuffer, calcWrappedOffset(index, mask));
    }

    public int size() {
        long currentProducerIndex;
        long after = lvConsumerIndex();
        long before;
        do {
            before = after;
            currentProducerIndex = lvProducerIndex();
            after = lvConsumerIndex();
        } while (before != after);
        return (int) (currentProducerIndex - after);
    }

    public boolean isEmpty() {
        return lvProducerIndex() == lvConsumerIndex();
    }

    private void adjustLookAheadStep(int capacity) {
        this.producerLookAheadStep = Math.min(capacity / 4, MAX_LOOK_AHEAD_STEP);
    }

    private long lvProducerIndex() {
        return this.producerIndex.get();
    }

    private long lvConsumerIndex() {
        return this.consumerIndex.get();
    }

    private long lpProducerIndex() {
        return this.producerIndex.get();
    }

    private long lpConsumerIndex() {
        return this.consumerIndex.get();
    }

    private void soProducerIndex(long v) {
        this.producerIndex.lazySet(v);
    }

    private void soConsumerIndex(long v) {
        this.consumerIndex.lazySet(v);
    }

    private static int calcWrappedOffset(long index, int mask) {
        return calcDirectOffset(((int) index) & mask);
    }

    private static int calcDirectOffset(int index) {
        return index;
    }

    private static void soElement(AtomicReferenceArray<Object> buffer, int offset, Object e) {
        buffer.lazySet(offset, e);
    }

    private static <E> Object lvElement(AtomicReferenceArray<Object> buffer, int offset) {
        return buffer.get(offset);
    }

    public Iterator<T> iterator() {
        throw new UnsupportedOperationException();
    }

    public boolean contains(Object o) {
        throw new UnsupportedOperationException();
    }

    public Object[] toArray() {
        throw new UnsupportedOperationException();
    }

    public <E> E[] toArray(E[] eArr) {
        throw new UnsupportedOperationException();
    }

    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    public boolean containsAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    public boolean addAll(Collection<? extends T> collection) {
        throw new UnsupportedOperationException();
    }

    public boolean removeAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    public boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    public boolean add(T t) {
        throw new UnsupportedOperationException();
    }

    public T remove() {
        throw new UnsupportedOperationException();
    }

    public T element() {
        throw new UnsupportedOperationException();
    }

    public boolean offer(T first, T second) {
        AtomicReferenceArray<Object> buffer = this.producerBuffer;
        long p = lvProducerIndex();
        int m = this.producerMask;
        int pi;
        if (lvElement(buffer, calcWrappedOffset(p + 2, m)) == null) {
            pi = calcWrappedOffset(p, m);
            soElement(buffer, pi + 1, second);
            soElement(buffer, pi, first);
            soProducerIndex(p + 2);
        } else {
            AtomicReferenceArray<Object> newBuffer = new AtomicReferenceArray(buffer.length());
            this.producerBuffer = newBuffer;
            pi = calcWrappedOffset(p, m);
            soElement(newBuffer, pi + 1, second);
            soElement(newBuffer, pi, first);
            soNext(buffer, newBuffer);
            soElement(buffer, pi, HAS_NEXT);
            soProducerIndex(p + 2);
        }
        return true;
    }
}
