package com.gmpvpc.android.models;

import java.util.Date;

import lombok.Data;

/**
 * Created by malah on 09/04/18.
 */
@Data
public abstract class Entity {

    private String id;
    private Date createdAt;
    private Date updatedAt;

}
