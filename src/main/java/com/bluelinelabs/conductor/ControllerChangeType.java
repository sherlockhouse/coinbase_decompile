package com.bluelinelabs.conductor;

public enum ControllerChangeType {
    PUSH_ENTER(true, true),
    PUSH_EXIT(true, false),
    POP_ENTER(false, true),
    POP_EXIT(false, false);
    
    public boolean isEnter;
    public boolean isPush;

    private ControllerChangeType(boolean isPush, boolean isEnter) {
        this.isPush = isPush;
        this.isEnter = isEnter;
    }
}
