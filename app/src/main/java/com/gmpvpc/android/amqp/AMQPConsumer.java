package com.gmpvpc.android.amqp;

import android.util.Log;

import com.gmpvpc.android.models.Hit;
import com.gmpvpc.android.models.Training;
import com.gmpvpc.android.utils.JsonUtils;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class AMQPConsumer extends DefaultConsumer {

    private static Map<String, Class<? extends Serializable>> CLASS_MAP = new HashMap<String, Class<? extends Serializable>>() {{
        put("hit", Hit.class);
        put("training", Training.class);
        put("series", Training.class);
    }};

    private Callback callback;

    public AMQPConsumer(Channel channel, Callback callback) {
        super(channel);
        this.callback = callback;
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope,
                               AMQP.BasicProperties properties, byte[] body) throws IOException {

        String message = new String(body, "UTF-8");
        Log.d("AMQPAsyncTask", message);

        if (message.contains(":")) {
            String[] array = message.split(":", 2);

            String type = array[0];
            String json = array[1];

            if (CLASS_MAP.containsKey(type)) {
                Serializable o = JsonUtils.parseToObject(json, CLASS_MAP.get(type));
                callback.execute(o);
            }
        }
    }

    @Override
    public void handleShutdownSignal(String consumerTag, ShutdownSignalException sig) {
        Log.d("AMQP SERVICE", "Queue shutdown");
    }

    public interface Callback {
        void execute(Serializable o);
    }

}