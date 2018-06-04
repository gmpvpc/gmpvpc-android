package com.gmpvpc.android.utils;

import android.os.AsyncTask;

public class PollingAsync extends AsyncTask<Void, Void, Void> {

    private long interval;
    private Callback pollingCallback;
    private Callback resultCallback;

    public PollingAsync(long interval, Callback pollingCallback, Callback resultCallback) {
        this.interval = interval;
        this.resultCallback = resultCallback;
        this.pollingCallback = pollingCallback;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            boolean result;
            do {
                result = pollingCallback.execute();
                Thread.sleep(this.interval);
            } while (!result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        resultCallback.execute();
    }

    public interface Callback {
        boolean execute();
    }
}