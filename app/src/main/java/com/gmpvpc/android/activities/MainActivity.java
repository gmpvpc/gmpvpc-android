package com.gmpvpc.android.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import com.gmpvpc.android.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button statistics_btn = findViewById(R.id.statistics_btn);
        statistics_btn.setOnClickListener(x -> launchActivity("GlobalStatisticsActivity"));

        Button training_btn = findViewById(R.id.training_btn);
        training_btn.setOnClickListener(x -> { Toast.makeText(this, "Not implemented yet", Toast.LENGTH_SHORT).show(); });
    }

    public void launchActivity(String className) {
        try {
            Intent i = new Intent(this,
                    Class.forName("com.gmpvpc.android.activities." + className));
            startActivity(i);
        } catch (ClassNotFoundException e) {
            Toast.makeText(this, "Activity not found", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
