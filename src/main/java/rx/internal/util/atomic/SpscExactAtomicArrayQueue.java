package rx.internal.util.atomic;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReferenceArray;
import rx.internal.util.unsafe.Pow2;

public final class SpscExactAtomicArrayQueue<T> extends AtomicReferenceArray<T> implements Queue<T> {
    final int capacitySkip;
    final AtomicLong consumerIndex = new AtomicLong();
    final int mask;
    final AtomicLong producerIndex = new AtomicLong();

    public SpscExactAtomicArrayQueue(int capacity) {
        super(Pow2.roundToPowerOfTwo(capacity));
        int len = length();
        this.mask = len - 1;
        this.capacitySkip = len - capacity;
    }

    public boolean offer(T value) {
        if (value == null) {
            throw new NullPointerException();
        }
        long pi = this.producerIndex.get();
        int m = this.mask;
        if (get(((int) (((long) this.capacitySkip) + pi)) & m) != null) {
            return false;
        }
        int offset = ((int) pi) & m;
        this.producerIndex.lazySet(1 + pi);
        lazySet(offset, value);
        return true;
    }

    public T poll() {
        long ci = this.consumerIndex.get();
        int offset = ((int) ci) & this.mask;
        T value = get(offset);
        if (value == null) {
            return null;
        }
        this.consumerIndex.lazySet(1 + ci);
        lazySet(offset, null);
        return value;
    }

    public T peek() {
        return get(((int) this.consumerIndex.get()) & this.mask);
    }

    public void clear() {
        while (true) {
            if (poll() == null && isEmpty()) {
                return;
            }
        }
    }

    public boolean isEmpty() {
        return this.producerIndex == this.consumerIndex;
    }

    public int size() {
        long ci = this.consumerIndex.get();
        while (true) {
            long pi = this.producerIndex.get();
            long ci2 = this.consumerIndex.get();
            if (ci == ci2) {
                return (int) (pi - ci2);
            }
            ci = ci2;
        }
    }

    public boolean contains(Object o) {
        throw new UnsupportedOperationException();
    }

    public Iterator<T> iterator() {
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
}
