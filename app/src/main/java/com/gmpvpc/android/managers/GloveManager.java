package com.gmpvpc.android.managers;

import com.gmpvpc.android.managers.base.ClientManager;
import com.gmpvpc.android.managers.base.EntityListener;
import com.gmpvpc.android.managers.client.HttpClientManager;
import com.gmpvpc.android.models.Glove;

import static com.gmpvpc.android.managers.config.ApiConfig.GLOVE_BY_ID;
import static com.gmpvpc.android.managers.config.ApiConfig.GLOVE_CALIBRATION;

public class GloveManager {

    private static final GloveManager instance = new GloveManager();

    public static GloveManager getInstance() {
        return instance;
    }

    private ClientManager<Glove> clientManager;

    private GloveManager() {
        this.clientManager = new HttpClientManager<>(Glove.class);
    }

    public void calibrate(String gloveId) {
        this.clientManager.action(String.format(GLOVE_CALIBRATION, gloveId));
    }

    public void getGlove(long gloveId, EntityListener<Glove> listener) {
        this.clientManager.readOne(listener, String.format(GLOVE_BY_ID, gloveId));
    }

    public Glove getGloveSync(String gloveId) {
        return this.clientManager.readOneSync(String.format(GLOVE_BY_ID, gloveId));
    }

}
