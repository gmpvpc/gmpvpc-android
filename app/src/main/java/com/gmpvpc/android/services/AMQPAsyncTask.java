package com.gmpvpc.android.services;

import android.os.AsyncTask;
import android.util.Log;

import com.gmpvpc.android.models.Hit;
import com.gmpvpc.android.models.Training;
import com.gmpvpc.android.utils.JsonUtils;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.tools.json.JSONUtil;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import static com.gmpvpc.android.utils.AppConfig.HUB_IP;
import static com.gmpvpc.android.utils.AppConfig.HUB_PWD;
import static com.gmpvpc.android.utils.AppConfig.HUB_QUEUE_NAME;
import static com.gmpvpc.android.utils.AppConfig.HUB_QUEUE_PORT;
import static com.gmpvpc.android.utils.AppConfig.HUB_USER;

public class AMQPAsyncTask extends AsyncTask<Void, Void, Void>{
    private Connection connection;
    private Channel channel;

    private Callback resultCallback;

    public static Map<String, Class<? extends Serializable>> DICTIONARY = new HashMap<String, Class<? extends Serializable>>(){{
        put("hit", Hit.class);
        put("training", Training.class);
    }};

    public AMQPAsyncTask(Callback callback){
        this.resultCallback = callback;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        this.connect();
        this.createChannel();
        this.configureQueue();

        return null;
    }

    public void connect(){
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername(HUB_USER);
        factory.setPassword(HUB_PWD);
        factory.setHost(HUB_IP);
        factory.setPort(HUB_QUEUE_PORT);

        try {
            connection = factory.newConnection();
            Log.d("AMQPAsyncTask", "Connected ro RabbitMQ server");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void disconnect() {

    }

    private void createChannel() {
        try {
            this.channel = this.connection.createChannel();
            this.channel.queueDeclare(HUB_QUEUE_NAME, false, false, false, null);

            Log.d("AMQPAsyncTask", "Created channel successfully");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void configureQueue() {
        try {
            this.channel.basicConsume(HUB_QUEUE_NAME, true, new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope,
                                           AMQP.BasicProperties properties, byte[] body) throws IOException {

                    String message = new String(body, "UTF-8");
                    Log.d("AMQPAsyncTask", message);


                    if (message.contains(":")) {
                        String[] array = message.split(":", 2);

                        String type = array[0];
                        String json = array[1];

                        if (DICTIONARY.containsKey(type)){
                            Serializable o = JsonUtils.parseToObject(json, DICTIONARY.get(type));
                            AMQPAsyncTask.this.resultCallback.execute(o);
                        }
                    }
                }
            });
            Log.d("AMQPAsyncTask", "Created consumer. Waiting for message...");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public interface Callback {
        void execute(Serializable o);
    }
}
