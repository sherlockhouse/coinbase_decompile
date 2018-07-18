package rx.subjects;

import rx.functions.Action1;

class BehaviorSubject$1 implements Action1<SubjectObserver<T>> {
    final /* synthetic */ SubjectSubscriptionManager val$state;

    BehaviorSubject$1(SubjectSubscriptionManager subjectSubscriptionManager) {
        this.val$state = subjectSubscriptionManager;
    }

    public void call(SubjectObserver<T> o) {
        o.emitFirst(this.val$state.getLatest());
    }
}
