package com.gmpvpc.android.models;

import java.util.List;

import lombok.Data;

/**
 * Created by malah on 21/03/18.
 */
@Data
public class Series extends Entity {

    private List<Hit> combinations;

    private int occurrence;

    private int hits;
}
