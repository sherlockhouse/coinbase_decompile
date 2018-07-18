package rx;

public interface CompletableSubscriber {
    void onCompleted();

    void onSubscribe(Subscription subscription);
}
