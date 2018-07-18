package android.databinding.adapters;

import android.databinding.InverseBindingListener;
import android.text.Editable;
import android.text.Spanned;
import android.text.TextWatcher;
import android.widget.TextView;
import com.android.databinding.library.baseAdapters.R;

public class TextViewBindingAdapter {

    public interface AfterTextChanged {
        void afterTextChanged(Editable editable);
    }

    public interface BeforeTextChanged {
        void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3);
    }

    public interface OnTextChanged {
        void onTextChanged(CharSequence charSequence, int i, int i2, int i3);
    }

    public static void setText(TextView view, CharSequence text) {
        CharSequence oldText = view.getText();
        if (text == oldText) {
            return;
        }
        if (text != null || oldText.length() != 0) {
            if (text instanceof Spanned) {
                if (text.equals(oldText)) {
                    return;
                }
            } else if (!haveContentsChanged(text, oldText)) {
                return;
            }
            view.setText(text);
        }
    }

    public static String getTextString(TextView view) {
        return view.getText().toString();
    }

    private static boolean haveContentsChanged(CharSequence str1, CharSequence str2) {
        boolean z;
        boolean z2;
        if (str1 == null) {
            z = true;
        } else {
            z = false;
        }
        if (str2 == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z != z2) {
            return true;
        }
        if (str1 == null) {
            return false;
        }
        int length = str1.length();
        if (length != str2.length()) {
            return true;
        }
        for (int i = 0; i < length; i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                return true;
            }
        }
        return false;
    }

    public static void setTextWatcher(TextView view, final BeforeTextChanged before, final OnTextChanged on, final AfterTextChanged after, final InverseBindingListener textAttrChanged) {
        TextWatcher newValue;
        if (before == null && after == null && on == null && textAttrChanged == null) {
            newValue = null;
        } else {
            newValue = new TextWatcher() {
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    if (before != null) {
                        before.beforeTextChanged(s, start, count, after);
                    }
                }

                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (on != null) {
                        on.onTextChanged(s, start, before, count);
                    }
                    if (textAttrChanged != null) {
                        textAttrChanged.onChange();
                    }
                }

                public void afterTextChanged(Editable s) {
                    if (after != null) {
                        after.afterTextChanged(s);
                    }
                }
            };
        }
        TextWatcher oldValue = (TextWatcher) ListenerUtil.trackListener(view, newValue, R.id.textWatcher);
        if (oldValue != null) {
            view.removeTextChangedListener(oldValue);
        }
        if (newValue != null) {
            view.addTextChangedListener(newValue);
        }
    }
}
