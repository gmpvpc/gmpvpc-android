package com.gmpvpc.android.manager;

import com.gmpvpc.android.manager.base.ClientManager;
import com.gmpvpc.android.manager.base.EntityListener;
import com.gmpvpc.android.manager.client.HttpClientManager;
import com.gmpvpc.android.model.Training;

import static com.gmpvpc.android.manager.config.ApiConfig.BY_ID;
import static com.gmpvpc.android.manager.config.ApiConfig.GET_TRAINING;

public class TrainingManager {

    private static final TrainingManager instance = new TrainingManager();

    public static TrainingManager getInstance() {
        return instance;
    }

    private ClientManager<Training> clientManager;

    private TrainingManager() {
        this.clientManager = new HttpClientManager<>(Training.class);
    }

    public void getCurrentTraining(long trainingId, EntityListener<Training> listener){
        this.clientManager.readOne(listener, String.format(GET_TRAINING + BY_ID, trainingId));
    };
    public void createTraining(Training training){
//        this.clientManager.create(null, training);
    };


}
