package com.gmpvpc.android.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.gmpvpc.android.R;
import com.gmpvpc.android.managers.GloveManager;
import com.gmpvpc.android.models.Glove;
import com.gmpvpc.android.utils.PollingAsync;

import static com.gmpvpc.android.utils.BundleDictionary.GLOVE_ID;

public class CalibrationActivity extends AppCompatActivity {

    private String gloveId;
    private GloveManager gloveManager;

    private PollingAsync calibrationPolling;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calibration);

        if (getIntent().getExtras() != null && getIntent().getExtras().containsKey(GLOVE_ID)) {
            gloveId = getIntent().getExtras().getString(GLOVE_ID);
        }

        this.gloveManager = GloveManager.getInstance();
        this.gloveManager.calibrate(gloveId);

        this.calibrationPolling = new PollingAsync(2000,
                () -> {
                    Glove glove = gloveManager.getGloveSync(this.gloveId);
                    return glove.isCalibrated();
                },
                () -> {
                    setResult(TrainingActivity.CALIBRATION_SUCCESS);
                    finish();
                    return true;
                }
        );

        this.getCalibrationStatus();
    }

    public void getCalibrationStatus() {
        this.calibrationPolling.execute();
    }

    public void cancelPolling(){
        this.calibrationPolling.cancel(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        this.cancelPolling();
    }
}