package retrofit2;

import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response.Builder;
import okhttp3.ResponseBody;

public final class Response<T> {
    private final T body;
    private final ResponseBody errorBody;
    private final okhttp3.Response rawResponse;

    public static <T> Response<T> success(T body) {
        return success(body, new Builder().code(Callback.DEFAULT_DRAG_ANIMATION_DURATION).message("OK").protocol(Protocol.HTTP_1_1).request(new Request.Builder().url("http://localhost/").build()).build());
    }

    public static <T> Response<T> success(T body, okhttp3.Response rawResponse) {
        if (rawResponse == null) {
            throw new NullPointerException("rawResponse == null");
        } else if (rawResponse.isSuccessful()) {
            return new Response(rawResponse, body, null);
        } else {
            throw new IllegalArgumentException("rawResponse must be successful response");
        }
    }

    public static <T> Response<T> error(ResponseBody body, okhttp3.Response rawResponse) {
        if (body == null) {
            throw new NullPointerException("body == null");
        } else if (rawResponse == null) {
            throw new NullPointerException("rawResponse == null");
        } else if (!rawResponse.isSuccessful()) {
            return new Response(rawResponse, null, body);
        } else {
            throw new IllegalArgumentException("rawResponse should not be successful response");
        }
    }

    private Response(okhttp3.Response rawResponse, T body, ResponseBody errorBody) {
        this.rawResponse = rawResponse;
        this.body = body;
        this.errorBody = errorBody;
    }

    public int code() {
        return this.rawResponse.code();
    }

    public String message() {
        return this.rawResponse.message();
    }

    public boolean isSuccessful() {
        return this.rawResponse.isSuccessful();
    }

    public T body() {
        return this.body;
    }

    public ResponseBody errorBody() {
        return this.errorBody;
    }
}
