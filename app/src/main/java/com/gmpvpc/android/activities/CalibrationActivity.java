package com.gmpvpc.android.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.gmpvpc.android.R;
import com.gmpvpc.android.manager.GloveManager;
import com.gmpvpc.android.model.Glove;

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
        @SuppressLint("StaticFieldLeak") AsyncTask truc = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                Glove myGlove = null;
                try {
                    do {
                        myGlove = CalibrationActivity.this.gloveManager.getGlove(gloveId);
                        Thread.sleep(2000);
                    } while (myGlove == null || !myGlove.isCalibrated());
                    // The glove is calibrated
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                launchActivity(CalibrationActivity.this, TrainingActivity.class);
            }
        };
    }
}
