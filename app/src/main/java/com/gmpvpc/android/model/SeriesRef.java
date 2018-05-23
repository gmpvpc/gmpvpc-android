package com.gmpvpc.android.model;

import java.util.List;

import lombok.Data;

@Data
public class SeriesRef extends Entity {

    private String name;
    private int numberOfSequences;
    private List<HitRef> combination;

}
