package com.coinbase.android.ui;

import rx.functions.Func0;

public abstract class ModalBottomNavigationItem extends NavigationItem {
    private boolean mHasNotification = false;

    public static abstract class Builder {
        public abstract ModalBottomNavigationItem build();

        public abstract Builder setClearHasNotificationFunc(Runnable runnable);

        public abstract Builder setHasNotificationFunc(Func0<Boolean> func0);

        public abstract Builder setIcon(int i);

        public abstract Builder setIconNotification(int i);

        public abstract Builder setTitle(int i);

        public abstract Builder setType(Type type);
    }

    public enum Type {
        NEW_BUY(2);
        
        private final int mValue;

        private Type(int value) {
            this.mValue = value;
        }

        public int getValue() {
            return this.mValue;
        }

        public static Type fromValue(int value) {
            for (Type type : values()) {
                if (type.getValue() == value) {
                    return type;
                }
            }
            return NEW_BUY;
        }
    }

    public abstract int getIcon();

    public abstract Type getType();

    public static Builder builder() {
        return new Builder();
    }
}
