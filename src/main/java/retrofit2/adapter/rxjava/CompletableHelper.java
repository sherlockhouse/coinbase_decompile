package retrofit2.adapter.rxjava;

import java.lang.reflect.Type;
import retrofit2.Call;
import retrofit2.CallAdapter;
import rx.Completable;
import rx.Completable.CompletableOnSubscribe;
import rx.Scheduler;

final class CompletableHelper {

    static class CompletableCallAdapter implements CallAdapter<Completable> {
        private final Scheduler scheduler;

        CompletableCallAdapter(Scheduler scheduler) {
            this.scheduler = scheduler;
        }

        public Type responseType() {
            return Void.class;
        }

        public Completable adapt(Call call) {
            Completable completable = Completable.create(new CompletableCallOnSubscribe(call));
            if (this.scheduler != null) {
                return completable.subscribeOn(this.scheduler);
            }
            return completable;
        }
    }

    private static final class CompletableCallOnSubscribe implements CompletableOnSubscribe {
        private final Call originalCall;

        CompletableCallOnSubscribe(Call originalCall) {
            this.originalCall = originalCall;
        }
    }

    static CallAdapter<Completable> createCallAdapter(Scheduler scheduler) {
        return new CompletableCallAdapter(scheduler);
    }
}
