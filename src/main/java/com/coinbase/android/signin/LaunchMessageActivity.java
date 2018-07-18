package com.coinbase.android.signin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.coinbase.android.R;

public class LaunchMessageActivity extends AppCompatActivity {
    public static final String ACTION = "ACTION";
    public static final String DESCRIPTION = "DESCRIPTION";
    public static final String DISMISSIBLE = "DISMISSIBLE";
    public static final String IMAGE = "IMAGE";
    public static final String TITLE = "TITLE";
    public static final String URL = "URL";
    private static boolean mIsDismissible = true;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_launch_message);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mIsDismissible = extras.getBoolean(DISMISSIBLE);
            LaunchMessageDialogFragment.newInstance(extras.getString(TITLE), extras.getString(DESCRIPTION), extras.getString(URL), extras.getString(ACTION), extras.getString(IMAGE), mIsDismissible).show(getSupportFragmentManager(), "Forced Update");
        }
    }

    public void onBackPressed() {
        if (mIsDismissible) {
            super.onBackPressed();
        }
    }
}
