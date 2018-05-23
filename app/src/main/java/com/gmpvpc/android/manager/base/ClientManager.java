package com.gmpvpc.android.manager.base;

import java.util.List;

/**
 * Created by malah on 12/12/17.
 */
public interface ClientManager<T> {

    void readOne(EntityListener<T> listener, String url);

    T readOneSync(String url);

    void readAll(EntityListener<List<T>> listener, String url);

    void create(EntityListener<T> listener, String url, T data);

    void update(EntityListener<T> listener, String url, T data);

    void delete(EntityListener<T> listener, String url);

    void action(String url);

}
