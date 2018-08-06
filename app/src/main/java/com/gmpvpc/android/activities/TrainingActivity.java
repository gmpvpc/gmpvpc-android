package com.gmpvpc.android.activities;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.gmpvpc.android.R;
import com.gmpvpc.android.fragments.GraphFragment;
import com.gmpvpc.android.fragments.SeriesFragment;
import com.gmpvpc.android.managers.TrainingManager;
import com.gmpvpc.android.models.Hit;
import com.gmpvpc.android.models.Series;
import com.gmpvpc.android.models.Training;
import com.gmpvpc.android.models.TrainingStatus;
import com.gmpvpc.android.amqp.AMQPReceiver;
import com.gmpvpc.android.services.AMQPService;
import com.gmpvpc.android.utils.AppConfig;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.HashMap;
import java.util.Map;

import static com.gmpvpc.android.utils.BundleDictionary.GLOVE_ID;

public class TrainingActivity extends AppCompatActivity {
    public static final int CALIBRATION_STATUS = 666;
    public static final int CALIBRATION_SUCCESS = 1;
    public static final int FRG_SERIES = R.id.series_fragment;

    private TrainingManager trainingManager;
    private Training training;
    private AMQPReceiver amqpMessageReceiver;

    // layout attributes
    private Button startButton;
    private Button stopButton;
    private GraphFragment hitFragment;
    private SeriesFragment seriesFragment;
    private TextView hitCountText;
    private TextView hitsText;

    public static final String BROADCAST_ACTION = "AMQP.message.received";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        // do the binding
        this.startButton = findViewById(R.id.training_start_btn);
        this.stopButton = findViewById(R.id.training_stop_btn);
        this.hitFragment = (GraphFragment) getFragmentManager().findFragmentById(R.id.training_hit_graph);
        this.seriesFragment = (SeriesFragment) getFragmentManager().findFragmentById(FRG_SERIES);
        this.hitCountText = findViewById(R.id.training_completed_hits_firstvalue);
        this.hitsText = findViewById(R.id.training_completed_hits_secondvalue);

        // disable stop button
        this.stopButton.setEnabled(false);

        this.trainingManager = TrainingManager.getInstance();

        Intent intent = new Intent(this, AMQPService.class);
        startService(intent);

        this.amqpMessageReceiver = new AMQPReceiver(this::receiveCallback);

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
        attributes.put("status", TrainingStatus.FINISHED.toString());
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
            } else {
                Toast.makeText(this, "Calibration failed ! Try again.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        this.unregisterReceiver(this.amqpMessageReceiver);
    }

    public void receiveCallback(Object o){
        if (o instanceof Hit){
            this.updateGraph((Hit) o);
        } else if (o instanceof Series) {
            this.updateSeries((Series) o);
        } else if(o instanceof Training) {
            this.updateTraining((Training) o);
        }
    }

    public void updateGraph(Hit hit){
        DataPoint[] data = new DataPoint[hit.getNormals().size()];

        for (int i = 0; i < hit.getNormals().size(); i++) {
            data[i] = new DataPoint(i, hit.getNormals().get(i));
        }

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(data);

        this.hitFragment.getGraphView().removeAllSeries();
        this.hitFragment.addSeries(series);
    }

    public void updateTraining(Training training) {
        seriesFragment.update(training.getSeries());
    }

    public void updateSeries(Series series){
        Toast.makeText(this, "received series", Toast.LENGTH_SHORT).show();
        this.hitCountText.setText(series.getHits());
        this.hitsText.setText(series.getOccurrence());
    }
}
