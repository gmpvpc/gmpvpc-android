package com.gmpvpc.android.model;

import lombok.Data;

@Data
public class HitRef extends Entity {

    private String name;
    private int duration;
    private float velocity;
}
