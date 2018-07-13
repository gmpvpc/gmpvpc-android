package com.gmpvpc.android.models;

import java.util.List;

import lombok.Data;

/**
 * Created by malah on 09/04/18.
 */
@Data
public class Hit extends Entity {

    private int duration;
    private float velocity;
    private List<Double> normals;
}
