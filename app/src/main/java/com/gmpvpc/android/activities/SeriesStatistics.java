package com.gmpvpc.android.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.gmpvpc.android.R;
import com.gmpvpc.android.fragments.GraphFragment;

public class SeriesStatistics extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series_statistics);

        GraphFragment graphFragment = (GraphFragment) getFragmentManager().findFragmentById(R.id.graph_fragment);

    }
}
