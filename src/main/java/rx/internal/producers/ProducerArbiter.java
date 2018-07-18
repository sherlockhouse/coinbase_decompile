package rx.internal.producers;

import rx.Producer;

public final class ProducerArbiter implements Producer {
    static final Producer NULL_PRODUCER = new Producer() {
        public void request(long n) {
        }
    };
    Producer currentProducer;
    boolean emitting;
    long missedProduced;
    Producer missedProducer;
    long missedRequested;
    long requested;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void request(long n) {
        if (n < 0) {
            throw new IllegalArgumentException("n >= 0 required");
        } else if (n != 0) {
            synchronized (this) {
                if (this.emitting) {
                    this.missedRequested += n;
                    return;
                }
                this.emitting = true;
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void produced(long n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n > 0 required");
        }
        synchronized (this) {
            if (this.emitting) {
                this.missedProduced += n;
                return;
            }
            this.emitting = true;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setProducer(Producer newProducer) {
        synchronized (this) {
            if (this.emitting) {
                if (newProducer == null) {
                    newProducer = NULL_PRODUCER;
                }
                this.missedProducer = newProducer;
                return;
            }
            this.emitting = true;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void emitLoop() {
        while (true) {
            synchronized (this) {
                long localRequested = this.missedRequested;
                long localProduced = this.missedProduced;
                Producer localProducer = this.missedProducer;
                if (localRequested == 0 && localProduced == 0 && localProducer == null) {
                    this.emitting = false;
                    return;
                }
                this.missedRequested = 0;
                this.missedProduced = 0;
                this.missedProducer = null;
            }
        }
    }
}
