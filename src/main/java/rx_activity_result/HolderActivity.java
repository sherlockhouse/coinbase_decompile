package rx_activity_result;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.os.Bundle;
import rx.functions.Action0;

public class HolderActivity extends Activity {
    private static Request request;
    private Intent data;
    private OnPreResult onPreResult;
    private OnResult onResult;
    private int resultCode;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (request == null) {
            finish();
            return;
        }
        this.onPreResult = request.onPreResult();
        this.onResult = request.onResult();
        if (savedInstanceState != null) {
            return;
        }
        if (request instanceof RequestIntentSender) {
            RequestIntentSender requestIntentSender = request;
            if (requestIntentSender.getOptions() == null) {
                startIntentSender(requestIntentSender);
                return;
            } else {
                startIntentSenderWithOptions(requestIntentSender);
                return;
            }
        }
        startActivityForResult(request.intent(), 0);
    }

    private void startIntentSender(RequestIntentSender requestIntentSender) {
        try {
            startIntentSenderForResult(requestIntentSender.getIntentSender(), 0, requestIntentSender.getFillInIntent(), requestIntentSender.getFlagsMask(), requestIntentSender.getFlagsValues(), requestIntentSender.getExtraFlags());
        } catch (SendIntentException exception) {
            exception.printStackTrace();
            this.onResult.response(0, null);
        }
    }

    private void startIntentSenderWithOptions(RequestIntentSender requestIntentSender) {
        try {
            startIntentSenderForResult(requestIntentSender.getIntentSender(), 0, requestIntentSender.getFillInIntent(), requestIntentSender.getFlagsMask(), requestIntentSender.getFlagsValues(), requestIntentSender.getExtraFlags(), requestIntentSender.getOptions());
        } catch (SendIntentException exception) {
            exception.printStackTrace();
            this.onResult.response(0, null);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        this.resultCode = resultCode;
        this.data = data;
        if (this.onPreResult != null) {
            this.onPreResult.response(resultCode, data).doOnCompleted(new Action0() {
                public void call() {
                    HolderActivity.this.finish();
                }
            }).subscribe();
        } else {
            finish();
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.onResult != null) {
            this.onResult.response(this.resultCode, this.data);
        }
    }

    static void setRequest(Request aRequest) {
        request = aRequest;
    }
}
