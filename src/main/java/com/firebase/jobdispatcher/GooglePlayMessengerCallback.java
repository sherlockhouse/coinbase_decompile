package com.firebase.jobdispatcher;

import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

class GooglePlayMessengerCallback implements JobCallback {
    private final Messenger messenger;
    private final String tag;

    GooglePlayMessengerCallback(Messenger messenger, String tag) {
        this.messenger = messenger;
        this.tag = tag;
    }

    public void jobFinished(int status) {
        try {
            this.messenger.send(createResultMessage(status));
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    private Message createResultMessage(int result) {
        Message msg = Message.obtain();
        msg.what = 3;
        msg.arg1 = result;
        Bundle b = new Bundle();
        b.putString("tag", this.tag);
        msg.setData(b);
        return msg;
    }
}
