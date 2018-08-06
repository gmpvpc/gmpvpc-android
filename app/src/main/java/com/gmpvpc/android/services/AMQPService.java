package com.gmpvpc.android.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.gmpvpc.android.activities.TrainingActivity;
import static com.gmpvpc.android.utils.BundleDictionary.OBJECT;

import java.io.Serializable;

public class AMQPService extends Service {
    private AMQPAsyncTask amqpAsyncTask;

    @Override
    public void onCreate() {
        this.amqpAsyncTask = new AMQPAsyncTask(this::broadcastMessage);
        this.amqpAsyncTask.execute();
    }

    private void broadcastMessage(Serializable o)
    {
        try
        {
            Intent broadCastIntent = new Intent();
            broadCastIntent.setAction(TrainingActivity.BROADCAST_ACTION);

            broadCastIntent.putExtra(OBJECT, o);

            sendBroadcast(broadCastIntent);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        this.amqpAsyncTask.cancel(true);
        Log.e("AMQP SERVICE", "Service destroyed !");
    }
}

