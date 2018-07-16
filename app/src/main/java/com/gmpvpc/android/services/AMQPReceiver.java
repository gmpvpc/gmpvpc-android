package com.gmpvpc.android.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AMQPReceiver extends BroadcastReceiver {
    private Callback callback;

    public AMQPReceiver(Callback callback){
        this.callback = callback;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Object o = intent.getSerializableExtra("object");

        this.callback.execute(o);
    }

    public interface Callback{
        void execute(Object o);
    }
}