package com.gmpvpc.android.manager;

import com.gmpvpc.android.model.Training;

public abstract class TrainingManager {
    public abstract  Training getCurrentTraining();
    public abstract Training createTraining(Training training);

    public static TrainingManager getInstance(){
        return null;
    }
}
