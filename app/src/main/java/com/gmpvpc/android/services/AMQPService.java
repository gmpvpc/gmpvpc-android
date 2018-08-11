package com.gmpvpc.android.services;

import android.content.Intent;
import android.util.Log;

import com.gmpvpc.android.amqp.AMQPAsyncTask;

import java.io.Serializable;

import static com.gmpvpc.android.utils.BroadcastInterface.BROADCAST_ACTION;
import static com.gmpvpc.android.utils.BundleDictionary.OBJECT;

public class AMQPService extends BaseService {

    private AMQPAsyncTask amqpAsyncTask;

    @Override
    public void onCreate() {
        this.amqpAsyncTask = new AMQPAsyncTask(this::broadcastMessage);
    }

    private void broadcastMessage(Serializable o) {
        try {
            Intent broadCastIntent = new Intent();
            broadCastIntent.setAction(BROADCAST_ACTION);
            broadCastIntent.putExtra(OBJECT, o);
            sendBroadcast(broadCastIntent);
            Log.d("AMQP Receiver", "Broadcast sent.");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        this.amqpAsyncTask.cancel(true);
        Log.e("AMQP SERVICE", "Service destroyed !");
    }
}

