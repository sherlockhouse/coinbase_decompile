package android.databinding;

import android.databinding.CallbackRegistry.NotifierCallback;
import android.databinding.Observable.OnPropertyChangedCallback;

public class PropertyChangeRegistry extends CallbackRegistry<OnPropertyChangedCallback, Observable, Void> {
    private static final NotifierCallback<OnPropertyChangedCallback, Observable, Void> NOTIFIER_CALLBACK = new NotifierCallback<OnPropertyChangedCallback, Observable, Void>() {
        public void onNotifyCallback(OnPropertyChangedCallback callback, Observable sender, int arg, Void notUsed) {
            callback.onPropertyChanged(sender, arg);
        }
    };

    public PropertyChangeRegistry() {
        super(NOTIFIER_CALLBACK);
    }
}
