package com.gmpvpc.android.manager.base;

/**
 * Created by malah on 26/03/18.
 */
public interface EntityListener<T> {
    void fireResponse(T data);
}
