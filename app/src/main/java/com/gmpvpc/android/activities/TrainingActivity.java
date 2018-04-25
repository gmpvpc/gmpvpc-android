package com.gmpvpc.android.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.gmpvpc.android.R;

public class TrainingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);
    }

    public void startTraining (View button) {
        // Add start request here
        Toast.makeText(this, "Start", Toast.LENGTH_SHORT).show();
    }

    public void stopTraining (View button) {
        // Add stop request here
        Toast.makeText(this, "Stop", Toast.LENGTH_SHORT).show();
    }
}
