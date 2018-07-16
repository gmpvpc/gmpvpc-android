package com.gmpvpc.android.services;

import android.os.AsyncTask;
import android.util.Log;

import com.gmpvpc.android.activities.TrainingActivity;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

import static com.gmpvpc.android.utils.AppConfig.HUB_IP;
import static com.gmpvpc.android.utils.AppConfig.HUB_PWD;
import static com.gmpvpc.android.utils.AppConfig.HUB_QUEUE_NAME;
import static com.gmpvpc.android.utils.AppConfig.HUB_QUEUE_PORT;
import static com.gmpvpc.android.utils.AppConfig.HUB_USER;

public class LaunchTheHolyHandGrenade extends AsyncTask<Void, Void, Void>{
    private Connection connection;
    private Channel channel;

    private Callback resultCallback;

    public LaunchTheHolyHandGrenade(Callback callback){
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
            Log.d("TheHolyHandGrenade", "Connected ro RabbitMQ server");
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

            Log.d("TheHolyHandGrenade", "Created channel successfully");
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
                    Log.d("TheHolyHandGrenade", message);


                    if (message.contains(":")) {
                        String[] array = message.split(":", 2);

                        String action = array[0];
                        String json = array[1];

                        LaunchTheHolyHandGrenade.this.resultCallback.execute(action, json);
                    }

                }
            });
            Log.d("TheHolyHandGrenade", "Created consumer. Waiting for message...");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public interface Callback {
        void execute(String action, String message);
    }
}
