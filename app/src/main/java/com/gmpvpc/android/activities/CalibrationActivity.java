package com.gmpvpc.android.activities;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.gmpvpc.android.R;
import com.gmpvpc.android.amqp.AMQPReceiver;
import com.gmpvpc.android.managers.GloveManager;
import com.gmpvpc.android.models.Glove;
import com.gmpvpc.android.models.Hit;
import com.gmpvpc.android.models.Series;
import com.gmpvpc.android.models.Training;
import com.gmpvpc.android.utils.PollingAsync;

import static com.gmpvpc.android.utils.BroadcastInterface.BROADCAST_ACTION;
import static com.gmpvpc.android.utils.BundleDictionary.GLOVE_ID;

public class CalibrationActivity extends AppCompatActivity {

    private String gloveId;
    private GloveManager gloveManager;

    private AMQPReceiver amqpMessageReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calibration);

        if (getIntent().getExtras() != null && getIntent().getExtras().containsKey(GLOVE_ID)) {
            gloveId = getIntent().getExtras().getString(GLOVE_ID);
        }

        this.gloveManager = GloveManager.getInstance();
        this.gloveManager.calibrate(gloveId);

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

    public void receiveCallback(Object o){
        Toast.makeText(this, "Did i receive something ?", Toast.LENGTH_SHORT).show();
        if (o instanceof Glove){
            Glove glove = (Glove) o;
            if (glove.isCalibrated()){
                setResult(TrainingActivity.CALIBRATION_SUCCESS);
                finish();
            }
        }
    }
}