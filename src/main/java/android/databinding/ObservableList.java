package android.databinding;

import java.util.List;

public interface ObservableList<T> extends List<T> {

    public static abstract class OnListChangedCallback<T extends ObservableList> {
    }

    void addOnListChangedCallback(OnListChangedCallback<? extends ObservableList<T>> onListChangedCallback);

    void removeOnListChangedCallback(OnListChangedCallback<? extends ObservableList<T>> onListChangedCallback);
}
