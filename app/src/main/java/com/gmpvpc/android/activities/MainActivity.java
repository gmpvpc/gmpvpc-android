package com.gmpvpc.android.activities;

import com.gmpvpc.android.R;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;


import static com.gmpvpc.android.utils.ActivityUtils.launchActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button statistics_btn = findViewById(R.id.statistics_btn);
        statistics_btn.setOnClickListener(x -> launchActivity(this));

        Button training_btn = findViewById(R.id.training_btn);
        training_btn.setOnClickListener(x -> launchActivity(this));
    }
}
