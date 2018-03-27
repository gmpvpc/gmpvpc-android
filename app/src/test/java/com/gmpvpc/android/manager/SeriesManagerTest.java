package com.gmpvpc.android.manager;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by malah on 27/03/18.
 */
public class SeriesManagerTest {

    private SeriesManager seriesManager;

    @Before
    public void setUp() {
        seriesManager = SeriesManager.getInstance();
    }

    @Test
    public void shouldGetAllSeries() {
        seriesManager.getAllSeries(data -> data.forEach(System.out::println));
    }

}
