package com.gmpvpc.android.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.gmpvpc.android.activities.TrainingActivity;
import static com.gmpvpc.android.utils.BundleDictionary.OBJECT;

import java.io.Serializable;

public class AMQPService extends Service {
    private AMQPAsyncTask launchTheHolyGrenade;

    @Override
    public void onCreate() {
        this.launchTheHolyGrenade = new AMQPAsyncTask(this::broadcastMessage);
        this.launchTheHolyGrenade.execute();
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
        this.launchTheHolyGrenade.cancel(true);
    }
}

