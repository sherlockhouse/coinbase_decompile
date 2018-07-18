package com.bluelinelabs.conductor;

import android.os.Bundle;
import com.bluelinelabs.conductor.internal.TransactionIndexer;

public class RouterTransaction {
    private static int INVALID_INDEX = -1;
    private boolean attachedToRouter;
    final Controller controller;
    private ControllerChangeHandler popControllerChangeHandler;
    private ControllerChangeHandler pushControllerChangeHandler;
    private String tag;
    int transactionIndex = INVALID_INDEX;

    public static RouterTransaction with(Controller controller) {
        return new RouterTransaction(controller);
    }

    private RouterTransaction(Controller controller) {
        this.controller = controller;
    }

    RouterTransaction(Bundle bundle) {
        this.controller = Controller.newInstance(bundle.getBundle("RouterTransaction.controller.bundle"));
        this.pushControllerChangeHandler = ControllerChangeHandler.fromBundle(bundle.getBundle("RouterTransaction.pushControllerChangeHandler"));
        this.popControllerChangeHandler = ControllerChangeHandler.fromBundle(bundle.getBundle("RouterTransaction.popControllerChangeHandler"));
        this.tag = bundle.getString("RouterTransaction.tag");
        this.transactionIndex = bundle.getInt("RouterTransaction.transactionIndex");
        this.attachedToRouter = bundle.getBoolean("RouterTransaction.attachedToRouter");
    }

    void onAttachedToRouter() {
        this.attachedToRouter = true;
    }

    public Controller controller() {
        return this.controller;
    }

    public String tag() {
        return this.tag;
    }

    public RouterTransaction tag(String tag) {
        if (this.attachedToRouter) {
            throw new RuntimeException(getClass().getSimpleName() + "s can not be modified after being added to a Router.");
        }
        this.tag = tag;
        return this;
    }

    public ControllerChangeHandler pushChangeHandler() {
        ControllerChangeHandler handler = this.controller.getOverriddenPushHandler();
        if (handler == null) {
            return this.pushControllerChangeHandler;
        }
        return handler;
    }

    public RouterTransaction pushChangeHandler(ControllerChangeHandler handler) {
        if (this.attachedToRouter) {
            throw new RuntimeException(getClass().getSimpleName() + "s can not be modified after being added to a Router.");
        }
        this.pushControllerChangeHandler = handler;
        return this;
    }

    public ControllerChangeHandler popChangeHandler() {
        ControllerChangeHandler handler = this.controller.getOverriddenPopHandler();
        if (handler == null) {
            return this.popControllerChangeHandler;
        }
        return handler;
    }

    public RouterTransaction popChangeHandler(ControllerChangeHandler handler) {
        if (this.attachedToRouter) {
            throw new RuntimeException(getClass().getSimpleName() + "s can not be modified after being added to a Router.");
        }
        this.popControllerChangeHandler = handler;
        return this;
    }

    void ensureValidIndex(TransactionIndexer indexer) {
        if (indexer == null) {
            throw new RuntimeException();
        } else if (this.transactionIndex == INVALID_INDEX && indexer != null) {
            this.transactionIndex = indexer.nextIndex();
        }
    }

    public Bundle saveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putBundle("RouterTransaction.controller.bundle", this.controller.saveInstanceState());
        if (this.pushControllerChangeHandler != null) {
            bundle.putBundle("RouterTransaction.pushControllerChangeHandler", this.pushControllerChangeHandler.toBundle());
        }
        if (this.popControllerChangeHandler != null) {
            bundle.putBundle("RouterTransaction.popControllerChangeHandler", this.popControllerChangeHandler.toBundle());
        }
        bundle.putString("RouterTransaction.tag", this.tag);
        bundle.putInt("RouterTransaction.transactionIndex", this.transactionIndex);
        bundle.putBoolean("RouterTransaction.attachedToRouter", this.attachedToRouter);
        return bundle;
    }
}
