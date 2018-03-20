package com.gmpvpc.android.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.gmpvpc.android.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startStaticsActivity() {
        launchActivity("SeriesHistory");
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
