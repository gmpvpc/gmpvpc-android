package com.gmpvpc.android.amqp;

import android.os.AsyncTask;
import android.util.Log;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

import static com.gmpvpc.android.amqp.AMQPConsumer.Callback;
import static com.gmpvpc.android.utils.AppConfig.HUB_IP;
import static com.gmpvpc.android.utils.AppConfig.HUB_PWD;
import static com.gmpvpc.android.utils.AppConfig.HUB_QUEUE_NAME;
import static com.gmpvpc.android.utils.AppConfig.HUB_QUEUE_PORT;
import static com.gmpvpc.android.utils.AppConfig.HUB_USER;

public class AMQPAsyncTask extends AsyncTask<Void, Void, Void> {

    private Connection connection;
    private Channel channel;

    private Callback callback;

    public AMQPAsyncTask(Callback callback) {
        this.callback = callback;
        execute();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        this.connect();
        this.createChannel();
        this.configureQueue();
        return null;
    }

    public void connect() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername(HUB_USER);
        factory.setPassword(HUB_PWD);
        factory.setHost(HUB_IP);
        factory.setPort(HUB_QUEUE_PORT);
        factory.setAutomaticRecoveryEnabled(true);

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
            this.channel.queuePurge(HUB_QUEUE_NAME);
            Log.d("AMQPAsyncTask", "Created channel successfully");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void configureQueue() {
        try {
            this.channel.basicConsume(HUB_QUEUE_NAME, true, new AMQPConsumer(this.channel, callback));
            Log.d("AMQPAsyncTask", "Created consumer. Waiting for message...");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
