package rx_activity_result;

import android.content.Intent;
import rx.Observable;

public interface OnPreResult<T> {
    Observable<T> response(int i, Intent intent);
}
