package com.gmpvpc.android.model;

public class Training extends Entity{

    public static final int ON_PAUSE = 0;
    public static final int IN_PROGRESS = 1;
    public static final int FINISHED = 2;

    private int status;

    public long getStatus(){
        return this.status;
    }
}
