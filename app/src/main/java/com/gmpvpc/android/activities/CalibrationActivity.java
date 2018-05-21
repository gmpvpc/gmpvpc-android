package com.gmpvpc.android.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.gmpvpc.android.R;
import com.gmpvpc.android.manager.GloveManager;
import com.gmpvpc.android.manager.base.EntityListener;
import com.gmpvpc.android.model.Entity;
import com.gmpvpc.android.model.Glove;
import com.gmpvpc.android.utils.PollingAsync;

import static com.gmpvpc.android.utils.ActivityUtils.launchActivity;
import static com.gmpvpc.android.utils.BundleDictionary.GLOVE_ID;

public class CalibrationActivity extends AppCompatActivity {

    private GloveManager gloveManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calibration);

        long gloveId = savedInstanceState.getLong(GLOVE_ID);

        this.gloveManager = GloveManager.getInstance();
        this.gloveManager.calibrate(gloveId);

        this.getCalibrationStatus(gloveId);
    }

    public void getCalibrationStatus(long gloveId) {
        new PollingAsync(2000,
                () -> {
                    Glove glove;
                    gloveManager.getGlove(gloveId, data -> {
                        ;
                    });
                    //
                    return glove.isCalibrated();
                },
                () -> {
                    setResult(TrainingActivity.CALIBRATION_SUCCESS);
                    finish();
                    return true;
                }
        ).execute();
    }
}