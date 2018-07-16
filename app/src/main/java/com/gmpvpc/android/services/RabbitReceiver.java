package com.gmpvpc.android.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.gmpvpc.android.models.Hit;
import com.gmpvpc.android.models.Training;
import com.gmpvpc.android.utils.JsonUtils;

import java.util.HashMap;
import java.util.Map;

public class RabbitReceiver extends BroadcastReceiver {
    private Callback callback;
    public static Map<String, Class<?>> DICTIONARY = new HashMap<String, Class<?>>(){{
        put("hit", Hit.class);
        put("training", Training.class);
    }};

    public RabbitReceiver(Callback callback){
        this.callback = callback;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getStringExtra("action");
        String json = intent.getStringExtra("message");


        Object o = JsonUtils.parseToObject(json, DICTIONARY.get(action));

        this.callback.execute(o);
    }

    public interface Callback{
        void execute(Object truc);
    }
}