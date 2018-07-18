package rx_activity_result;

import android.content.Intent;

class Request {
    private final Intent intent;
    private OnPreResult onPreResult;
    private OnResult onResult;

    Request(Intent intent) {
        this.intent = intent;
    }

    void setOnPreResult(OnPreResult onPreResult) {
        this.onPreResult = onPreResult;
    }

    OnPreResult onPreResult() {
        return this.onPreResult;
    }

    void setOnResult(OnResult onResult) {
        this.onResult = onResult;
    }

    OnResult onResult() {
        return this.onResult;
    }

    Intent intent() {
        return this.intent;
    }
}
