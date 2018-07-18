package android.support.v4.view.accessibility;

import android.graphics.Rect;
import android.os.Build.VERSION;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction;
import android.view.accessibility.AccessibilityNodeInfo.CollectionInfo;
import android.view.accessibility.AccessibilityNodeInfo.CollectionItemInfo;

public class AccessibilityNodeInfoCompat {
    static final AccessibilityNodeInfoBaseImpl IMPL;
    private final AccessibilityNodeInfo mInfo;
    public int mParentVirtualDescendantId = -1;

    public static class AccessibilityActionCompat {
        public static final AccessibilityActionCompat ACTION_ACCESSIBILITY_FOCUS = new AccessibilityActionCompat(64, null);
        public static final AccessibilityActionCompat ACTION_CLEAR_ACCESSIBILITY_FOCUS = new AccessibilityActionCompat(128, null);
        public static final AccessibilityActionCompat ACTION_CLEAR_FOCUS = new AccessibilityActionCompat(2, null);
        public static final AccessibilityActionCompat ACTION_CLEAR_SELECTION = new AccessibilityActionCompat(8, null);
        public static final AccessibilityActionCompat ACTION_CLICK = new AccessibilityActionCompat(16, null);
        public static final AccessibilityActionCompat ACTION_COLLAPSE = new AccessibilityActionCompat(524288, null);
        public static final AccessibilityActionCompat ACTION_CONTEXT_CLICK = new AccessibilityActionCompat(AccessibilityNodeInfoCompat.IMPL.getActionContextClick());
        public static final AccessibilityActionCompat ACTION_COPY = new AccessibilityActionCompat(16384, null);
        public static final AccessibilityActionCompat ACTION_CUT = new AccessibilityActionCompat(65536, null);
        public static final AccessibilityActionCompat ACTION_DISMISS = new AccessibilityActionCompat(1048576, null);
        public static final AccessibilityActionCompat ACTION_EXPAND = new AccessibilityActionCompat(262144, null);
        public static final AccessibilityActionCompat ACTION_FOCUS = new AccessibilityActionCompat(1, null);
        public static final AccessibilityActionCompat ACTION_LONG_CLICK = new AccessibilityActionCompat(32, null);
        public static final AccessibilityActionCompat ACTION_NEXT_AT_MOVEMENT_GRANULARITY = new AccessibilityActionCompat(256, null);
        public static final AccessibilityActionCompat ACTION_NEXT_HTML_ELEMENT = new AccessibilityActionCompat(1024, null);
        public static final AccessibilityActionCompat ACTION_PASTE = new AccessibilityActionCompat(32768, null);
        public static final AccessibilityActionCompat ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY = new AccessibilityActionCompat(512, null);
        public static final AccessibilityActionCompat ACTION_PREVIOUS_HTML_ELEMENT = new AccessibilityActionCompat(2048, null);
        public static final AccessibilityActionCompat ACTION_SCROLL_BACKWARD = new AccessibilityActionCompat(8192, null);
        public static final AccessibilityActionCompat ACTION_SCROLL_DOWN = new AccessibilityActionCompat(AccessibilityNodeInfoCompat.IMPL.getActionScrollDown());
        public static final AccessibilityActionCompat ACTION_SCROLL_FORWARD = new AccessibilityActionCompat(4096, null);
        public static final AccessibilityActionCompat ACTION_SCROLL_LEFT = new AccessibilityActionCompat(AccessibilityNodeInfoCompat.IMPL.getActionScrollLeft());
        public static final AccessibilityActionCompat ACTION_SCROLL_RIGHT = new AccessibilityActionCompat(AccessibilityNodeInfoCompat.IMPL.getActionScrollRight());
        public static final AccessibilityActionCompat ACTION_SCROLL_TO_POSITION = new AccessibilityActionCompat(AccessibilityNodeInfoCompat.IMPL.getActionScrollToPosition());
        public static final AccessibilityActionCompat ACTION_SCROLL_UP = new AccessibilityActionCompat(AccessibilityNodeInfoCompat.IMPL.getActionScrollUp());
        public static final AccessibilityActionCompat ACTION_SELECT = new AccessibilityActionCompat(4, null);
        public static final AccessibilityActionCompat ACTION_SET_PROGRESS = new AccessibilityActionCompat(AccessibilityNodeInfoCompat.IMPL.getActionSetProgress());
        public static final AccessibilityActionCompat ACTION_SET_SELECTION = new AccessibilityActionCompat(131072, null);
        public static final AccessibilityActionCompat ACTION_SET_TEXT = new AccessibilityActionCompat(2097152, null);
        public static final AccessibilityActionCompat ACTION_SHOW_ON_SCREEN = new AccessibilityActionCompat(AccessibilityNodeInfoCompat.IMPL.getActionShowOnScreen());
        final Object mAction;

        public AccessibilityActionCompat(int actionId, CharSequence label) {
            this(AccessibilityNodeInfoCompat.IMPL.newAccessibilityAction(actionId, label));
        }

        AccessibilityActionCompat(Object action) {
            this.mAction = action;
        }
    }

    static class AccessibilityNodeInfoBaseImpl {
        AccessibilityNodeInfoBaseImpl() {
        }

        public Object newAccessibilityAction(int actionId, CharSequence label) {
            return null;
        }

        public boolean removeAction(AccessibilityNodeInfo info, Object action) {
            return false;
        }

        public boolean isVisibleToUser(AccessibilityNodeInfo info) {
            return false;
        }

        public boolean isAccessibilityFocused(AccessibilityNodeInfo info) {
            return false;
        }

        public void setMovementGranularities(AccessibilityNodeInfo info, int granularities) {
        }

        public int getMovementGranularities(AccessibilityNodeInfo info) {
            return 0;
        }

        public void setVisibleToUser(AccessibilityNodeInfo info, boolean visibleToUser) {
        }

        public void setAccessibilityFocused(AccessibilityNodeInfo info, boolean focused) {
        }

        public String getViewIdResourceName(AccessibilityNodeInfo info) {
            return null;
        }

        public void setCollectionInfo(AccessibilityNodeInfo info, Object collectionInfo) {
        }

        public void setCollectionItemInfo(AccessibilityNodeInfo info, Object collectionItemInfo) {
        }

        public Object obtainCollectionInfo(int rowCount, int columnCount, boolean hierarchical, int selectionMode) {
            return null;
        }

        public Object obtainCollectionItemInfo(int rowIndex, int rowSpan, int columnIndex, int columnSpan, boolean heading, boolean selected) {
            return null;
        }

        public void setContentInvalid(AccessibilityNodeInfo info, boolean contentInvalid) {
        }

        public void setError(AccessibilityNodeInfo info, CharSequence error) {
        }

        public void setLabelFor(AccessibilityNodeInfo info, View labeled) {
        }

        public void setCanOpenPopup(AccessibilityNodeInfo info, boolean opensPopup) {
        }

        public Object getActionScrollToPosition() {
            return null;
        }

        public Object getActionSetProgress() {
            return null;
        }

        public Object getActionShowOnScreen() {
            return null;
        }

        public Object getActionScrollUp() {
            return null;
        }

        public Object getActionScrollDown() {
            return null;
        }

        public Object getActionScrollLeft() {
            return null;
        }

        public Object getActionScrollRight() {
            return null;
        }

        public Object getActionContextClick() {
            return null;
        }
    }

    static class AccessibilityNodeInfoApi16Impl extends AccessibilityNodeInfoBaseImpl {
        AccessibilityNodeInfoApi16Impl() {
        }

        public boolean isVisibleToUser(AccessibilityNodeInfo info) {
            return info.isVisibleToUser();
        }

        public void setVisibleToUser(AccessibilityNodeInfo info, boolean visibleToUser) {
            info.setVisibleToUser(visibleToUser);
        }

        public boolean isAccessibilityFocused(AccessibilityNodeInfo info) {
            return info.isAccessibilityFocused();
        }

        public void setAccessibilityFocused(AccessibilityNodeInfo info, boolean focused) {
            info.setAccessibilityFocused(focused);
        }

        public void setMovementGranularities(AccessibilityNodeInfo info, int granularities) {
            info.setMovementGranularities(granularities);
        }

        public int getMovementGranularities(AccessibilityNodeInfo info) {
            return info.getMovementGranularities();
        }
    }

    static class AccessibilityNodeInfoApi17Impl extends AccessibilityNodeInfoApi16Impl {
        AccessibilityNodeInfoApi17Impl() {
        }

        public void setLabelFor(AccessibilityNodeInfo info, View labeled) {
            info.setLabelFor(labeled);
        }
    }

    static class AccessibilityNodeInfoApi18Impl extends AccessibilityNodeInfoApi17Impl {
        AccessibilityNodeInfoApi18Impl() {
        }

        public String getViewIdResourceName(AccessibilityNodeInfo info) {
            return info.getViewIdResourceName();
        }
    }

    static class AccessibilityNodeInfoApi19Impl extends AccessibilityNodeInfoApi18Impl {
        AccessibilityNodeInfoApi19Impl() {
        }

        public void setCollectionInfo(AccessibilityNodeInfo info, Object collectionInfo) {
            info.setCollectionInfo((CollectionInfo) collectionInfo);
        }

        public Object obtainCollectionInfo(int rowCount, int columnCount, boolean hierarchical, int selectionMode) {
            return CollectionInfo.obtain(rowCount, columnCount, hierarchical);
        }

        public Object obtainCollectionItemInfo(int rowIndex, int rowSpan, int columnIndex, int columnSpan, boolean heading, boolean selected) {
            return CollectionItemInfo.obtain(rowIndex, rowSpan, columnIndex, columnSpan, heading);
        }

        public void setCollectionItemInfo(AccessibilityNodeInfo info, Object collectionItemInfo) {
            info.setCollectionItemInfo((CollectionItemInfo) collectionItemInfo);
        }

        public void setContentInvalid(AccessibilityNodeInfo info, boolean contentInvalid) {
            info.setContentInvalid(contentInvalid);
        }

        public void setCanOpenPopup(AccessibilityNodeInfo info, boolean opensPopup) {
            info.setCanOpenPopup(opensPopup);
        }
    }

    static class AccessibilityNodeInfoApi21Impl extends AccessibilityNodeInfoApi19Impl {
        AccessibilityNodeInfoApi21Impl() {
        }

        public Object newAccessibilityAction(int actionId, CharSequence label) {
            return new AccessibilityAction(actionId, label);
        }

        public Object obtainCollectionInfo(int rowCount, int columnCount, boolean hierarchical, int selectionMode) {
            return CollectionInfo.obtain(rowCount, columnCount, hierarchical, selectionMode);
        }

        public boolean removeAction(AccessibilityNodeInfo info, Object action) {
            return info.removeAction((AccessibilityAction) action);
        }

        public Object obtainCollectionItemInfo(int rowIndex, int rowSpan, int columnIndex, int columnSpan, boolean heading, boolean selected) {
            return CollectionItemInfo.obtain(rowIndex, rowSpan, columnIndex, columnSpan, heading, selected);
        }

        public void setError(AccessibilityNodeInfo info, CharSequence error) {
            info.setError(error);
        }
    }

    static class AccessibilityNodeInfoApi22Impl extends AccessibilityNodeInfoApi21Impl {
        AccessibilityNodeInfoApi22Impl() {
        }
    }

    static class AccessibilityNodeInfoApi23Impl extends AccessibilityNodeInfoApi22Impl {
        AccessibilityNodeInfoApi23Impl() {
        }

        public Object getActionScrollToPosition() {
            return AccessibilityAction.ACTION_SCROLL_TO_POSITION;
        }

        public Object getActionShowOnScreen() {
            return AccessibilityAction.ACTION_SHOW_ON_SCREEN;
        }

        public Object getActionScrollUp() {
            return AccessibilityAction.ACTION_SCROLL_UP;
        }

        public Object getActionScrollDown() {
            return AccessibilityAction.ACTION_SCROLL_DOWN;
        }

        public Object getActionScrollLeft() {
            return AccessibilityAction.ACTION_SCROLL_LEFT;
        }

        public Object getActionScrollRight() {
            return AccessibilityAction.ACTION_SCROLL_RIGHT;
        }

        public Object getActionContextClick() {
            return AccessibilityAction.ACTION_CONTEXT_CLICK;
        }
    }

    static class AccessibilityNodeInfoApi24Impl extends AccessibilityNodeInfoApi23Impl {
        AccessibilityNodeInfoApi24Impl() {
        }

        public Object getActionSetProgress() {
            return AccessibilityAction.ACTION_SET_PROGRESS;
        }
    }

    public static class CollectionInfoCompat {
        final Object mInfo;

        public static CollectionInfoCompat obtain(int rowCount, int columnCount, boolean hierarchical, int selectionMode) {
            return new CollectionInfoCompat(AccessibilityNodeInfoCompat.IMPL.obtainCollectionInfo(rowCount, columnCount, hierarchical, selectionMode));
        }

        CollectionInfoCompat(Object info) {
            this.mInfo = info;
        }
    }

    public static class CollectionItemInfoCompat {
        final Object mInfo;

        public static CollectionItemInfoCompat obtain(int rowIndex, int rowSpan, int columnIndex, int columnSpan, boolean heading, boolean selected) {
            return new CollectionItemInfoCompat(AccessibilityNodeInfoCompat.IMPL.obtainCollectionItemInfo(rowIndex, rowSpan, columnIndex, columnSpan, heading, selected));
        }

        CollectionItemInfoCompat(Object info) {
            this.mInfo = info;
        }
    }

    static {
        if (VERSION.SDK_INT >= 24) {
            IMPL = new AccessibilityNodeInfoApi24Impl();
        } else if (VERSION.SDK_INT >= 23) {
            IMPL = new AccessibilityNodeInfoApi23Impl();
        } else if (VERSION.SDK_INT >= 22) {
            IMPL = new AccessibilityNodeInfoApi22Impl();
        } else if (VERSION.SDK_INT >= 21) {
            IMPL = new AccessibilityNodeInfoApi21Impl();
        } else if (VERSION.SDK_INT >= 19) {
            IMPL = new AccessibilityNodeInfoApi19Impl();
        } else if (VERSION.SDK_INT >= 18) {
            IMPL = new AccessibilityNodeInfoApi18Impl();
        } else if (VERSION.SDK_INT >= 17) {
            IMPL = new AccessibilityNodeInfoApi17Impl();
        } else if (VERSION.SDK_INT >= 16) {
            IMPL = new AccessibilityNodeInfoApi16Impl();
        } else {
            IMPL = new AccessibilityNodeInfoBaseImpl();
        }
    }

    private AccessibilityNodeInfoCompat(AccessibilityNodeInfo info) {
        this.mInfo = info;
    }

    public static AccessibilityNodeInfoCompat wrap(AccessibilityNodeInfo info) {
        return new AccessibilityNodeInfoCompat(info);
    }

    public AccessibilityNodeInfo unwrap() {
        return this.mInfo;
    }

    public static AccessibilityNodeInfoCompat obtain(AccessibilityNodeInfoCompat info) {
        return wrap(AccessibilityNodeInfo.obtain(info.mInfo));
    }

    public void setSource(View source) {
        this.mInfo.setSource(source);
    }

    public void addChild(View child) {
        this.mInfo.addChild(child);
    }

    public int getActions() {
        return this.mInfo.getActions();
    }

    public void addAction(int action) {
        this.mInfo.addAction(action);
    }

    public boolean removeAction(AccessibilityActionCompat action) {
        return IMPL.removeAction(this.mInfo, action.mAction);
    }

    public void setMovementGranularities(int granularities) {
        IMPL.setMovementGranularities(this.mInfo, granularities);
    }

    public int getMovementGranularities() {
        return IMPL.getMovementGranularities(this.mInfo);
    }

    public void setParent(View parent) {
        this.mInfo.setParent(parent);
    }

    public void getBoundsInParent(Rect outBounds) {
        this.mInfo.getBoundsInParent(outBounds);
    }

    public void setBoundsInParent(Rect bounds) {
        this.mInfo.setBoundsInParent(bounds);
    }

    public void getBoundsInScreen(Rect outBounds) {
        this.mInfo.getBoundsInScreen(outBounds);
    }

    public void setBoundsInScreen(Rect bounds) {
        this.mInfo.setBoundsInScreen(bounds);
    }

    public boolean isCheckable() {
        return this.mInfo.isCheckable();
    }

    public void setCheckable(boolean checkable) {
        this.mInfo.setCheckable(checkable);
    }

    public boolean isChecked() {
        return this.mInfo.isChecked();
    }

    public void setChecked(boolean checked) {
        this.mInfo.setChecked(checked);
    }

    public boolean isFocusable() {
        return this.mInfo.isFocusable();
    }

    public void setFocusable(boolean focusable) {
        this.mInfo.setFocusable(focusable);
    }

    public boolean isFocused() {
        return this.mInfo.isFocused();
    }

    public void setFocused(boolean focused) {
        this.mInfo.setFocused(focused);
    }

    public boolean isVisibleToUser() {
        return IMPL.isVisibleToUser(this.mInfo);
    }

    public void setVisibleToUser(boolean visibleToUser) {
        IMPL.setVisibleToUser(this.mInfo, visibleToUser);
    }

    public boolean isAccessibilityFocused() {
        return IMPL.isAccessibilityFocused(this.mInfo);
    }

    public void setAccessibilityFocused(boolean focused) {
        IMPL.setAccessibilityFocused(this.mInfo, focused);
    }

    public boolean isSelected() {
        return this.mInfo.isSelected();
    }

    public void setSelected(boolean selected) {
        this.mInfo.setSelected(selected);
    }

    public boolean isClickable() {
        return this.mInfo.isClickable();
    }

    public void setClickable(boolean clickable) {
        this.mInfo.setClickable(clickable);
    }

    public boolean isLongClickable() {
        return this.mInfo.isLongClickable();
    }

    public void setLongClickable(boolean longClickable) {
        this.mInfo.setLongClickable(longClickable);
    }

    public boolean isEnabled() {
        return this.mInfo.isEnabled();
    }

    public void setEnabled(boolean enabled) {
        this.mInfo.setEnabled(enabled);
    }

    public boolean isPassword() {
        return this.mInfo.isPassword();
    }

    public boolean isScrollable() {
        return this.mInfo.isScrollable();
    }

    public void setScrollable(boolean scrollable) {
        this.mInfo.setScrollable(scrollable);
    }

    public CharSequence getPackageName() {
        return this.mInfo.getPackageName();
    }

    public void setPackageName(CharSequence packageName) {
        this.mInfo.setPackageName(packageName);
    }

    public CharSequence getClassName() {
        return this.mInfo.getClassName();
    }

    public void setClassName(CharSequence className) {
        this.mInfo.setClassName(className);
    }

    public CharSequence getText() {
        return this.mInfo.getText();
    }

    public void setText(CharSequence text) {
        this.mInfo.setText(text);
    }

    public CharSequence getContentDescription() {
        return this.mInfo.getContentDescription();
    }

    public void setContentDescription(CharSequence contentDescription) {
        this.mInfo.setContentDescription(contentDescription);
    }

    public void recycle() {
        this.mInfo.recycle();
    }

    public String getViewIdResourceName() {
        return IMPL.getViewIdResourceName(this.mInfo);
    }

    public void setCollectionInfo(Object collectionInfo) {
        IMPL.setCollectionInfo(this.mInfo, ((CollectionInfoCompat) collectionInfo).mInfo);
    }

    public void setCollectionItemInfo(Object collectionItemInfo) {
        IMPL.setCollectionItemInfo(this.mInfo, ((CollectionItemInfoCompat) collectionItemInfo).mInfo);
    }

    public void setContentInvalid(boolean contentInvalid) {
        IMPL.setContentInvalid(this.mInfo, contentInvalid);
    }

    public void setError(CharSequence error) {
        IMPL.setError(this.mInfo, error);
    }

    public void setLabelFor(View labeled) {
        IMPL.setLabelFor(this.mInfo, labeled);
    }

    public void setCanOpenPopup(boolean opensPopup) {
        IMPL.setCanOpenPopup(this.mInfo, opensPopup);
    }

    public int hashCode() {
        return this.mInfo == null ? 0 : this.mInfo.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        AccessibilityNodeInfoCompat other = (AccessibilityNodeInfoCompat) obj;
        if (this.mInfo == null) {
            if (other.mInfo != null) {
                return false;
            }
            return true;
        } else if (this.mInfo.equals(other.mInfo)) {
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(super.toString());
        Rect bounds = new Rect();
        getBoundsInParent(bounds);
        builder.append("; boundsInParent: " + bounds);
        getBoundsInScreen(bounds);
        builder.append("; boundsInScreen: " + bounds);
        builder.append("; packageName: ").append(getPackageName());
        builder.append("; className: ").append(getClassName());
        builder.append("; text: ").append(getText());
        builder.append("; contentDescription: ").append(getContentDescription());
        builder.append("; viewId: ").append(getViewIdResourceName());
        builder.append("; checkable: ").append(isCheckable());
        builder.append("; checked: ").append(isChecked());
        builder.append("; focusable: ").append(isFocusable());
        builder.append("; focused: ").append(isFocused());
        builder.append("; selected: ").append(isSelected());
        builder.append("; clickable: ").append(isClickable());
        builder.append("; longClickable: ").append(isLongClickable());
        builder.append("; enabled: ").append(isEnabled());
        builder.append("; password: ").append(isPassword());
        builder.append("; scrollable: " + isScrollable());
        builder.append("; [");
        int actionBits = getActions();
        while (actionBits != 0) {
            int action = 1 << Integer.numberOfTrailingZeros(actionBits);
            actionBits &= action ^ -1;
            builder.append(getActionSymbolicName(action));
            if (actionBits != 0) {
                builder.append(", ");
            }
        }
        builder.append("]");
        return builder.toString();
    }

    private static String getActionSymbolicName(int action) {
        switch (action) {
            case 1:
                return "ACTION_FOCUS";
            case 2:
                return "ACTION_CLEAR_FOCUS";
            case 4:
                return "ACTION_SELECT";
            case 8:
                return "ACTION_CLEAR_SELECTION";
            case 16:
                return "ACTION_CLICK";
            case 32:
                return "ACTION_LONG_CLICK";
            case 64:
                return "ACTION_ACCESSIBILITY_FOCUS";
            case 128:
                return "ACTION_CLEAR_ACCESSIBILITY_FOCUS";
            case 256:
                return "ACTION_NEXT_AT_MOVEMENT_GRANULARITY";
            case 512:
                return "ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY";
            case 1024:
                return "ACTION_NEXT_HTML_ELEMENT";
            case 2048:
                return "ACTION_PREVIOUS_HTML_ELEMENT";
            case 4096:
                return "ACTION_SCROLL_FORWARD";
            case 8192:
                return "ACTION_SCROLL_BACKWARD";
            case 16384:
                return "ACTION_COPY";
            case 32768:
                return "ACTION_PASTE";
            case 65536:
                return "ACTION_CUT";
            case 131072:
                return "ACTION_SET_SELECTION";
            default:
                return "ACTION_UNKNOWN";
        }
    }
}
