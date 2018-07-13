package com.gmpvpc.android.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.gmpvpc.android.activities.TrainingActivity;

public class AMQPService extends Service {
    private LaunchTheHolyHandGrenade launchTheHolyGrenade;

    @Override
    public void onCreate() {
        this.launchTheHolyGrenade = new LaunchTheHolyHandGrenade(this::broadcastMessage);
        this.launchTheHolyGrenade.execute();
    }

    private void broadcastMessage(String message)
    {
        try
        {
            Intent broadCastIntent = new Intent();
            broadCastIntent.setAction(TrainingActivity.BROADCAST_ACTION);

             // uncomment this line if you want to send data
             // broadCastIntent.putExtra("data", "abc");

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

