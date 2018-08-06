package com.gmpvpc.android.amqp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AMQPReceiver extends BroadcastReceiver {

    private Callback receiveCallback;

    public AMQPReceiver(Callback receiveCallback) {
        this.receiveCallback = receiveCallback;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Object o = intent.getSerializableExtra("object");
        this.receiveCallback.execute(o);
    }

    public interface Callback {
        void execute(Object o);
    }
}