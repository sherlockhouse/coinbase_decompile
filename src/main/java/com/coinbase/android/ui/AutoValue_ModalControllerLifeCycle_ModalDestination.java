package com.coinbase.android.ui;

import android.os.Bundle;
import com.coinbase.android.ui.ModalBottomNavigationItem.Type;
import com.coinbase.android.ui.ModalControllerLifeCycle.ModalDestination;
import org.apache.commons.lang3.mutable.MutableBoolean;
import rx.functions.Func1;

final class AutoValue_ModalControllerLifeCycle_ModalDestination extends ModalDestination {
    private final MutableBoolean consumed;
    private final Func1<Bundle, ActionBarController> pushModalControllerFunc;
    private final Type type;

    static final class Builder extends com.coinbase.android.ui.ModalControllerLifeCycle.ModalDestination.Builder {
        private MutableBoolean consumed;
        private Func1<Bundle, ActionBarController> pushModalControllerFunc;
        private Type type;

        Builder() {
        }

        Builder(ModalDestination source) {
            this.type = source.getType();
            this.pushModalControllerFunc = source.getPushModalControllerFunc();
            this.consumed = source.getConsumed();
        }

        public com.coinbase.android.ui.ModalControllerLifeCycle.ModalDestination.Builder setType(Type type) {
            this.type = type;
            return this;
        }

        public com.coinbase.android.ui.ModalControllerLifeCycle.ModalDestination.Builder setPushModalControllerFunc(Func1<Bundle, ActionBarController> pushModalControllerFunc) {
            this.pushModalControllerFunc = pushModalControllerFunc;
            return this;
        }

        public com.coinbase.android.ui.ModalControllerLifeCycle.ModalDestination.Builder setConsumed(MutableBoolean consumed) {
            this.consumed = consumed;
            return this;
        }

        public ModalDestination build() {
            String missing = "";
            if (this.consumed == null) {
                missing = missing + " consumed";
            }
            if (missing.isEmpty()) {
                return new AutoValue_ModalControllerLifeCycle_ModalDestination(this.type, this.pushModalControllerFunc, this.consumed);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    private AutoValue_ModalControllerLifeCycle_ModalDestination(Type type, Func1<Bundle, ActionBarController> pushModalControllerFunc, MutableBoolean consumed) {
        this.type = type;
        this.pushModalControllerFunc = pushModalControllerFunc;
        this.consumed = consumed;
    }

    public Type getType() {
        return this.type;
    }

    public Func1<Bundle, ActionBarController> getPushModalControllerFunc() {
        return this.pushModalControllerFunc;
    }

    public MutableBoolean getConsumed() {
        return this.consumed;
    }

    public String toString() {
        return "ModalDestination{type=" + this.type + ", pushModalControllerFunc=" + this.pushModalControllerFunc + ", consumed=" + this.consumed + "}";
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ModalDestination)) {
            return false;
        }
        ModalDestination that = (ModalDestination) o;
        if (this.type != null) {
            if (this.type.equals(that.getType())) {
            }
            return false;
        }
        if (this.pushModalControllerFunc != null) {
            if (this.pushModalControllerFunc.equals(that.getPushModalControllerFunc())) {
            }
            return false;
        }
        if (this.consumed.equals(that.getConsumed())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((1 * 1000003) ^ (this.type == null ? 0 : this.type.hashCode())) * 1000003;
        if (this.pushModalControllerFunc != null) {
            i = this.pushModalControllerFunc.hashCode();
        }
        return ((h ^ i) * 1000003) ^ this.consumed.hashCode();
    }
}
