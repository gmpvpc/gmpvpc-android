package com.gmpvpc.android.managers;

import com.gmpvpc.android.managers.base.ClientManager;
import com.gmpvpc.android.managers.base.EntityListener;
import com.gmpvpc.android.managers.client.HttpClientManager;
import com.gmpvpc.android.models.Series;

import java.util.List;

import static com.gmpvpc.android.managers.config.ApiConfig.BY_ID;
import static com.gmpvpc.android.managers.config.ApiConfig.SERIES;

/**
 * Created by malah on 20/03/18.
 */
public class SeriesManager {

    private static final SeriesManager instance = new SeriesManager();

    public static SeriesManager getInstance() {
        return instance;
    }

    private ClientManager<Series> clientManager;

    private SeriesManager() {
        this.clientManager = new HttpClientManager<>(Series.class);
    }

    public void getSeries(long seriesId, EntityListener<Series> listener) {
        this.clientManager.readOne(listener, String.format(SERIES + BY_ID, seriesId));
    }

    public void getAllSeries(EntityListener<List<Series>> listener) {
        this.clientManager.readAll(listener, SERIES);
    }

    public void endSerie(long serieId) {
    }

}
