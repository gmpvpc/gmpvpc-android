package com.gmpvpc.android.manager;

import com.gmpvpc.android.manager.base.ClientManager;
import com.gmpvpc.android.manager.base.EntityListener;
import com.gmpvpc.android.manager.client.HttpClientManager;
import com.gmpvpc.android.model.Training;

import java.util.Map;

import static com.gmpvpc.android.manager.config.ApiConfig.BY_ID;
import static com.gmpvpc.android.manager.config.ApiConfig.CURRENT_TRAINING_API;
import static com.gmpvpc.android.manager.config.ApiConfig.TRAINING_API;

public class TrainingManager {

    private static final TrainingManager instance = new TrainingManager();

    public static TrainingManager getInstance() {
        return instance;
    }

    private ClientManager<Training> clientManager;

    private TrainingManager() {
        this.clientManager = new HttpClientManager<>(Training.class);
    }

    public void getCurrentTraining(EntityListener<Training> listener){
        this.clientManager.readOne(listener, CURRENT_TRAINING_API);
    }

    public Training getCurrentTrainingSync() {
        return this.clientManager.readOneSync(CURRENT_TRAINING_API);
    }

    public void createTraining(Training training, EntityListener<Training> listener){
        this.clientManager.create(listener, TRAINING_API, training);
    }

    public void updateTraining(long trainingId, Map<String, Object> attributes) {
        this.clientManager.update(null, String.format(TRAINING_API + BY_ID, trainingId), attributes);
    }

}
