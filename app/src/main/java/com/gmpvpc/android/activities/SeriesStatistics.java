package com.gmpvpc.android.activities;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import com.gmpvpc.android.R;
import com.gmpvpc.android.fragments.GraphFragment;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import static java.lang.Thread.sleep;

public class SeriesStatistics extends AppCompatActivity {
    private static final int RADIO_ACCURACY = R.id.accuracy_radio_btn;
    private static final int RADIO_SPEED = R.id.speed_radio_btn;
    private static final int LINE_COLOR_BROWN = Color.parseColor("#FFE54000");

    private RadioGroup dataType;
    private GraphFragment graphFragment;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series_statistics);

        this.progressDialog = new ProgressDialog(SeriesStatistics.this);
        this.progressDialog.setMessage("Loading...");
        this.progressDialog.setCancelable(false);

        this.dataType = findViewById(R.id.radio_group);

        this.graphFragment = (GraphFragment) getFragmentManager().findFragmentById(R.id.graph_fragment);

        this.dataType.setOnCheckedChangeListener(this::changeDataType);

        this.dataType.check(RADIO_SPEED);
    }

    public void changeDataType(RadioGroup radioGroup, int radioButtonId) {
        this.progressDialog.show();
        switch (radioButtonId) {
            case RADIO_ACCURACY:
                this.loadAccuracy();
                break;
            case RADIO_SPEED:
                this.loadSpeed();
                break;
        }
    }

    public void loadSpeed() {
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });

        series.setColor(LINE_COLOR_BROWN);


        new UselessAsyncTask(() -> {
            this.graphFragment.getGraphView().removeAllSeries();
            this.graphFragment.addSeries(series);
            this.progressDialog.dismiss();
        }).execute();
    }

    public void loadAccuracy() {
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 8),
                new DataPoint(1, 9),
                new DataPoint(2, 7),
                new DataPoint(3, 6),
                new DataPoint(4, 2)
        });

        series.setColor(LINE_COLOR_BROWN);

        new UselessAsyncTask(() -> {
            this.graphFragment.getGraphView().removeAllSeries();
            this.graphFragment.addSeries(series);
            this.progressDialog.dismiss();
        }).execute();
    }

    private class UselessAsyncTask extends AsyncTask<Void, Void, Void> {
        private IUseless useless;

        private UselessAsyncTask(IUseless useless) {
            this.useless = useless;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void voids) {
            useless.doThat();
        }
    }

    private interface IUseless {
        void doThat();
    }
}
