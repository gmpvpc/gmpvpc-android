package com.gmpvpc.android.manager;

import com.gmpvpc.android.manager.base.ClientManager;
import com.gmpvpc.android.manager.client.HttpClientManager;
import com.gmpvpc.android.model.Glove;

public abstract class GloveManager {

    private static final GloveManager instance = null; // new GloveManager()

    public static GloveManager getInstance() {
        return instance;
    }

    private ClientManager<Glove> clientManager;

    private GloveManager() {
        this.clientManager = new HttpClientManager<>(Glove.class);
    }

    public abstract void calibrate(long gloveId);

    public abstract Glove getGlove(long gloveId);
}
