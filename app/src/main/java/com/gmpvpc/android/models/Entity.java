package com.gmpvpc.android.models;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * Created by malah on 09/04/18.
 */
@Data
public abstract class Entity implements Serializable {

    private String id;
    private Date createdAt;
    private Date updatedAt;

}
