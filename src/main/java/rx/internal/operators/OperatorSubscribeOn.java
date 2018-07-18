package rx.internal.operators;

import rx.Observable;
import rx.Observable$OnSubscribe;
import rx.Producer;
import rx.Scheduler;
import rx.Scheduler$Worker;
import rx.Subscriber;
import rx.functions.Action0;

public final class OperatorSubscribeOn<T> implements Observable$OnSubscribe<T> {
    final Scheduler scheduler;
    final Observable<T> source;

    public OperatorSubscribeOn(Observable<T> source, Scheduler scheduler) {
        this.scheduler = scheduler;
        this.source = source;
    }

    public void call(final Subscriber<? super T> subscriber) {
        final Scheduler$Worker inner = this.scheduler.createWorker();
        subscriber.add(inner);
        inner.schedule(new Action0() {
            public void call() {
                final Thread t = Thread.currentThread();
                OperatorSubscribeOn.this.source.unsafeSubscribe(new Subscriber<T>(subscriber) {
                    public void onNext(T t) {
                        subscriber.onNext(t);
                    }

                    public void onError(Throwable e) {
                        try {
                            subscriber.onError(e);
                        } finally {
                            inner.unsubscribe();
                        }
                    }

                    public void onCompleted() {
                        try {
                            subscriber.onCompleted();
                        } finally {
                            inner.unsubscribe();
                        }
                    }

                    public void setProducer(final Producer p) {
                        subscriber.setProducer(new Producer() {
                            public void request(final long n) {
                                if (t == Thread.currentThread()) {
                                    p.request(n);
                                } else {
                                    inner.schedule(new Action0() {
                                        public void call() {
                                            p.request(n);
                                        }
                                    });
                                }
                            }
                        });
                    }
                });
            }
        });
    }
}
