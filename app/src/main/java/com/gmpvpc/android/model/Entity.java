package com.gmpvpc.android.model;

import java.time.Instant;

import lombok.Data;

/**
 * Created by malah on 09/04/18.
 */
@Data
public abstract class Entity {

    private long id;
    private Instant creationDate;
    private Instant updateDate;

}
