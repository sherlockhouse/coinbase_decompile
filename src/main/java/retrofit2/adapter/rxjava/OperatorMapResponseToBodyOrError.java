package retrofit2.adapter.rxjava;

import retrofit2.Response;
import rx.Observable$Operator;
import rx.Subscriber;

final class OperatorMapResponseToBodyOrError<T> implements Observable$Operator<T, Response<T>> {
    private static final OperatorMapResponseToBodyOrError<Object> INSTANCE = new OperatorMapResponseToBodyOrError();

    OperatorMapResponseToBodyOrError() {
    }

    static <R> OperatorMapResponseToBodyOrError<R> instance() {
        return INSTANCE;
    }

    public Subscriber<? super Response<T>> call(final Subscriber<? super T> child) {
        return new Subscriber<Response<T>>(child) {
            public void onNext(Response<T> response) {
                if (response.isSuccessful()) {
                    child.onNext(response.body());
                } else {
                    child.onError(new HttpException(response));
                }
            }

            public void onCompleted() {
                child.onCompleted();
            }

            public void onError(Throwable e) {
                child.onError(e);
            }
        };
    }
}
