package com.gmpvpc.android.manager;

import com.gmpvpc.android.manager.base.ClientManager;
import com.gmpvpc.android.manager.base.EntityListener;
import com.gmpvpc.android.manager.client.HttpClientManager;
import com.gmpvpc.android.model.Series;

import java.util.List;

import static com.gmpvpc.android.manager.config.ApiConfig.BY_ID;
import static com.gmpvpc.android.manager.config.ApiConfig.GET_SERIES;

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
        this.clientManager.readOne(listener, String.format(GET_SERIES + BY_ID, seriesId));
    }

    public void getAllSeries(EntityListener<List<Series>> listener) {
        this.clientManager.readAll(listener, GET_SERIES);
    }

    public void endSerie(long serieId) {
    }

}
