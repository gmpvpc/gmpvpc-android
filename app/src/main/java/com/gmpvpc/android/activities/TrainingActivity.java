package com.gmpvpc.android.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.gmpvpc.android.R;
import com.gmpvpc.android.manager.TrainingManager;
import com.gmpvpc.android.model.Training;
import com.gmpvpc.android.model.TrainingStatus;
import com.gmpvpc.android.utils.PollingAsync;

import java.util.HashMap;
import java.util.Map;

import static com.gmpvpc.android.utils.ActivityUtils.launchActivityForResult;

public class TrainingActivity extends AppCompatActivity {
    public static final int CALIBRATION_STATUS = 666;
    public static final int CALIBRATION_SUCCESS = 1;

    private TrainingManager trainingManager;
    private Training training;
    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        // do the binding
        this.startButton = findViewById(R.id.training_start_btn);

        // Call API to create default training
        this.trainingManager = TrainingManager.getInstance();
        this.trainingManager.createTraining(new Training(), training -> this.training = training);
    }

    public void startTraining (View button) {
        // Toast.makeText(this, "Start", Toast.LENGTH_SHORT).show();
        launchActivityForResult(this, CalibrationActivity.class, CALIBRATION_STATUS);
    }

    public void stopTraining (View button) {
        // Add stop request here
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("status", TrainingStatus.FINISHED);
        this.trainingManager.updateTraining(this.training.getId(), attributes);
        Toast.makeText(this, "Stop", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CALIBRATION_STATUS) {
            if (resultCode == CALIBRATION_SUCCESS){
                Toast.makeText(this, "Calibration success !", Toast.LENGTH_SHORT).show();
                this.startButton.setClickable(false);
                // @android:style/Widget.Material.Light.Button.Small
                // change Button background to default color
                this.getTrainingPolling();
            } else {
                Toast.makeText(this, "Calibration failed ! Try again.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void getTrainingPolling(){
        new PollingAsync(2000,
                () -> {
                    this.training = this.trainingManager.getCurrentTraining();
                    return (this.training.getStatus() == Training.FINISHED);
                },
                () -> {
                    Toast.makeText(this, "Training ended !", Toast.LENGTH_SHORT).show();
                    return true;
                }
        ).execute();
    }

}
