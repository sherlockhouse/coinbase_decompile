package android.databinding;

import android.databinding.Observable.OnPropertyChangedCallback;

public class BaseObservable implements Observable {
    private transient PropertyChangeRegistry mCallbacks;

    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        synchronized (this) {
            if (this.mCallbacks == null) {
                this.mCallbacks = new PropertyChangeRegistry();
            }
        }
        this.mCallbacks.add(callback);
    }

    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        synchronized (this) {
            if (this.mCallbacks == null) {
                return;
            }
            this.mCallbacks.remove(callback);
        }
    }

    public void notifyChange() {
        synchronized (this) {
            if (this.mCallbacks == null) {
                return;
            }
            this.mCallbacks.notifyCallbacks(this, 0, null);
        }
    }

    public void notifyPropertyChanged(int fieldId) {
        synchronized (this) {
            if (this.mCallbacks == null) {
                return;
            }
            this.mCallbacks.notifyCallbacks(this, fieldId, null);
        }
    }
}
