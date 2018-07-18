package rx.subjects;

public final class PublishSubject<T> extends Subject<T, T> {
    final PublishSubjectState<T> state;

    public static <T> PublishSubject<T> create() {
        return new PublishSubject(new PublishSubjectState());
    }

    protected PublishSubject(PublishSubjectState<T> state) {
        super(state);
        this.state = state;
    }

    public void onNext(T v) {
        this.state.onNext(v);
    }

    public void onError(Throwable e) {
        this.state.onError(e);
    }

    public void onCompleted() {
        this.state.onCompleted();
    }
}
