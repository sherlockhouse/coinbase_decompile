package android.support.v7.app;

import android.support.v7.view.ActionMode;
import android.support.v7.view.ActionMode.Callback;

public interface AppCompatCallback {
    void onSupportActionModeFinished(ActionMode actionMode);

    void onSupportActionModeStarted(ActionMode actionMode);

    ActionMode onWindowStartingSupportActionMode(Callback callback);
}
