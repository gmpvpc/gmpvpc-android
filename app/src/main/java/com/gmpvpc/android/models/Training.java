package com.gmpvpc.android.models;

import java.util.List;

import lombok.Data;

@Data
public class Training extends Entity {

    private TrainingStatus status;
    private List<Series> series;

}
