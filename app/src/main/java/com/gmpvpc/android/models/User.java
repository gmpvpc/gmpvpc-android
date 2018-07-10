package com.gmpvpc.android.models;

import lombok.Data;

@Data
public class User extends Entity {

    private String name;
    private String password;
    private String email;
}
