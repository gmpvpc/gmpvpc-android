package com.gmpvpc.android.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class AMQPService extends Service {
    private LaunchTheHolyHandGrenade launchTheHolyGrenade;

    @Override
    public void onCreate() {
        this.launchTheHolyGrenade = new LaunchTheHolyHandGrenade();
        this.launchTheHolyGrenade.execute();
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

