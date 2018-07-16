package com.gmpvpc.android.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.gmpvpc.android.activities.TrainingActivity;
import com.gmpvpc.android.models.Training;

public class AMQPService extends Service {
    private LaunchTheHolyHandGrenade launchTheHolyGrenade;

    @Override
    public void onCreate() {
        this.launchTheHolyGrenade = new LaunchTheHolyHandGrenade(this::broadcastMessage);
        this.launchTheHolyGrenade.execute();
    }

    private void broadcastMessage(String action, String message)
    {
        try
        {
            Intent broadCastIntent = new Intent();
            broadCastIntent.setAction(TrainingActivity.BROADCAST_ACTION);
            broadCastIntent.putExtra("action", action);
            broadCastIntent.putExtra("message", message);

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

