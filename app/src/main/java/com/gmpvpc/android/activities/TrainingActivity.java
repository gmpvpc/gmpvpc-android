package com.gmpvpc.android.activities;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.gmpvpc.android.R;
import com.gmpvpc.android.fragments.GraphFragment;
import com.gmpvpc.android.managers.TrainingManager;
import com.gmpvpc.android.models.Hit;
import com.gmpvpc.android.models.Training;
import com.gmpvpc.android.models.TrainingStatus;
import com.gmpvpc.android.services.AMQPService;
import com.gmpvpc.android.services.RabbitReceiver;
import com.gmpvpc.android.utils.AppConfig;
import com.gmpvpc.android.utils.PollingAsync;
import com.jjoe64.graphview.series.DataPoint;

import java.util.HashMap;
import java.util.Map;

import static com.gmpvpc.android.utils.BundleDictionary.GLOVE_ID;

public class TrainingActivity extends AppCompatActivity {
    public static final int CALIBRATION_STATUS = 666;
    public static final int CALIBRATION_SUCCESS = 1;

    private TrainingManager trainingManager;
    private Training training;
    private RabbitReceiver amqpMessageReceiver;

    // layout attributes
    private Button startButton;
    private Button stopButton;
    private GraphFragment hitFragment;


    public static final String BROADCAST_ACTION = "AMQP.message.received";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        // do the binding
        this.startButton = findViewById(R.id.training_start_btn);
        this.stopButton = findViewById(R.id.training_stop_btn);
        this.hitFragment = (GraphFragment) getFragmentManager().findFragmentById(R.id.training_hit_graph);

        // disable stop button
        this.stopButton.setEnabled(false);

        this.trainingManager = TrainingManager.getInstance();

        Intent intent = new Intent(this, AMQPService.class);
        startService(intent);

        this.amqpMessageReceiver = new RabbitReceiver(this::doTheAction);

        this.registerMyReceiver();
    }

    private void registerMyReceiver() {
        try{
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(BROADCAST_ACTION);

            registerReceiver(this.amqpMessageReceiver, intentFilter);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public void startTraining (View button) {
        // Call API to create default training
        this.trainingManager.createTraining(new Training(), training -> this.training = training);

        Intent i = new Intent(this, CalibrationActivity.class);
        i.putExtra(GLOVE_ID, AppConfig.GLOVE_MAC_ADDR);
        this.startActivityForResult(i, CALIBRATION_STATUS);
    }

    public void stopTraining (View button) {
        // Add stop request here
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("status", TrainingStatus.FINISHED);
        this.trainingManager.updateTraining(this.training.getId(), attributes);
        Toast.makeText(this, "Finished training", Toast.LENGTH_SHORT).show();

        this.startButton.setEnabled(true);
        this.stopButton.setEnabled(false);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CALIBRATION_STATUS) {
            if (resultCode == CALIBRATION_SUCCESS){
                Toast.makeText(this, "Calibration success !", Toast.LENGTH_SHORT).show();

                // disable start button
                this.startButton.setEnabled(false);

                // enable stop button
                this.stopButton.setEnabled(true);

                this.getTrainingPolling();
            } else {
                Toast.makeText(this, "Calibration failed ! Try again.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void getTrainingPolling(){
        new PollingAsync(2000,
                () -> {
                    this.training = this.trainingManager.getCurrentTrainingSync();
                    return this.training != null && this.training.getStatus() == TrainingStatus.FINISHED;
                },
                () -> {
                    Toast.makeText(this, "Training ended !", Toast.LENGTH_LONG).show();

                    this.startButton.setEnabled(true);
                    this.stopButton.setEnabled(false);
                    return true;
                }
        ).execute();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        this.unregisterReceiver(this.amqpMessageReceiver);
    }

    public void doTheAction(Object o){
        if (o instanceof Hit){
            this.updateGraph((Hit) o);
        } else if (o instanceof Training) {
            this.updateSeries((Training) o);
        }
    }

    public void updateGraph(Hit hit){
    }

    public void updateSeries(Training training){
        // mise a jour des series
    }
}
