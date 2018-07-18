package rx_activity_result;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import java.util.List;
import rx.Observable;
import rx.functions.Action1;
import rx.subjects.PublishSubject;

public class RxActivityResult$Builder<T> {
    final Class clazz;
    final PublishSubject<Result<T>> subject = PublishSubject.create();
    private final boolean uiTargetActivity;

    public RxActivityResult$Builder(T t) {
        if (RxActivityResult.activitiesLifecycle == null) {
            throw new IllegalStateException("You must call RxActivityResult.register(application) before attempting to use startIntent");
        }
        this.clazz = t.getClass();
        this.uiTargetActivity = t instanceof Activity;
    }

    public Observable<Result<T>> startIntent(Intent intent, OnPreResult onPreResult) {
        return startHolderActivity(new Request(intent), onPreResult);
    }

    private Observable<Result<T>> startHolderActivity(Request request, OnPreResult onPreResult) {
        request.setOnResult(this.uiTargetActivity ? onResultActivity() : onResultFragment());
        request.setOnPreResult(onPreResult);
        HolderActivity.setRequest(request);
        RxActivityResult.activitiesLifecycle.getOLiveActivity().subscribe(new Action1<Activity>() {
            public void call(Activity activity) {
                activity.startActivity(new Intent(activity, HolderActivity.class).addFlags(65536));
            }
        });
        return this.subject.asObservable();
    }

    private OnResult onResultActivity() {
        return new OnResult() {
            public void response(int resultCode, Intent data) {
                if (RxActivityResult.activitiesLifecycle.getLiveActivity() != null && RxActivityResult.activitiesLifecycle.getLiveActivity().getClass() == RxActivityResult$Builder.this.clazz) {
                    RxActivityResult$Builder.this.subject.onNext(new Result(RxActivityResult.activitiesLifecycle.getLiveActivity(), resultCode, data));
                    RxActivityResult$Builder.this.subject.onCompleted();
                }
            }
        };
    }

    private OnResult onResultFragment() {
        return new OnResult() {
            public void response(int resultCode, Intent data) {
                if (RxActivityResult.activitiesLifecycle.getLiveActivity() != null) {
                    Fragment targetFragment = RxActivityResult$Builder.this.getTargetFragment(((FragmentActivity) RxActivityResult.activitiesLifecycle.getLiveActivity()).getSupportFragmentManager().getFragments());
                    if (targetFragment != null) {
                        RxActivityResult$Builder.this.subject.onNext(new Result(targetFragment, resultCode, data));
                        RxActivityResult$Builder.this.subject.onCompleted();
                    }
                }
            }
        };
    }

    Fragment getTargetFragment(List<Fragment> fragments) {
        if (fragments == null) {
            return null;
        }
        for (Fragment fragment : fragments) {
            if (fragment != null && fragment.isVisible() && fragment.getClass() == this.clazz) {
                return fragment;
            }
            if (!(fragment == null || fragment.getChildFragmentManager() == null)) {
                Fragment candidate = getTargetFragment(fragment.getChildFragmentManager().getFragments());
                if (candidate != null) {
                    return candidate;
                }
            }
        }
        return null;
    }
}
