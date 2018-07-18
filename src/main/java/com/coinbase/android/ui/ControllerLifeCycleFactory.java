package com.coinbase.android.ui;

import android.os.Bundle;
import com.coinbase.android.ApplicationScope;

@ApplicationScope
public class ControllerLifeCycleFactory {

    public enum LifeCycleType {
        MODAL(0),
        PAGE(1);
        
        private final int mValue;

        private LifeCycleType(int value) {
            this.mValue = value;
        }

        public int getValue() {
            return this.mValue;
        }

        static LifeCycleType fromValue(int fromVal) {
            for (LifeCycleType type : values()) {
                if (fromVal == type.mValue) {
                    return type;
                }
            }
            return MODAL;
        }

        static LifeCycleType fromLifeCycle(ControllerLifeCycle lifeCycle) {
            if (lifeCycle instanceof PageControllerLifeCycle) {
                return PAGE;
            }
            if (lifeCycle instanceof ModalControllerLifeCycle) {
                return MODAL;
            }
            return MODAL;
        }
    }

    public ControllerLifeCycle createFromController(ActionBarController controller) {
        Bundle args = controller.getArgs();
        if (!args.containsKey(ControllerLifeCycle.LIFE_CYCLE_TYPE)) {
            return new ModalControllerLifeCycle(controller);
        }
        switch (LifeCycleType.fromValue(args.getInt(ControllerLifeCycle.LIFE_CYCLE_TYPE))) {
            case PAGE:
                return new PageControllerLifeCycle(controller);
            default:
                return new ModalControllerLifeCycle(controller);
        }
    }
}
