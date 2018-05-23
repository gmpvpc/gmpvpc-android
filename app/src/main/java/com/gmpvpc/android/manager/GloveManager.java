package com.gmpvpc.android.manager;

import com.gmpvpc.android.manager.base.ClientManager;
import com.gmpvpc.android.manager.base.EntityListener;
import com.gmpvpc.android.manager.client.HttpClientManager;
import com.gmpvpc.android.model.Glove;

import static com.gmpvpc.android.manager.config.ApiConfig.BY_ID;
import static com.gmpvpc.android.manager.config.ApiConfig.CALIBRATION;
import static com.gmpvpc.android.manager.config.ApiConfig.GET_GLOVE;

public class GloveManager {

    private static final GloveManager instance = new GloveManager();

    public static GloveManager getInstance() {
        return instance;
    }

    private ClientManager<Glove> clientManager;

    private GloveManager() {
        this.clientManager = new HttpClientManager<>(Glove.class);
    }

    public void calibrate(long gloveId){
        this.clientManager.action(String.format(GET_GLOVE + BY_ID + CALIBRATION, gloveId));
    }

    public void getGlove(long gloveId, EntityListener<Glove> listener){
        this.clientManager.readOne(listener, String.format(GET_GLOVE + BY_ID, gloveId));
    }
}
