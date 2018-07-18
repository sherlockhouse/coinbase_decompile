package rx_activity_result;

import android.content.Intent;

public class Result<T> {
    private final Intent data;
    private final int resultCode;
    private final T targetUI;

    public Result(T targetUI, int resultCode, Intent data) {
        this.targetUI = targetUI;
        this.resultCode = resultCode;
        this.data = data;
    }
}
