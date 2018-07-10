package com.gmpvpc.android.managers.base;

import com.gmpvpc.android.managers.client.ClientAsyncTask;
import com.gmpvpc.android.managers.client.TypeMethod;

import java.util.List;
import java.util.Map;

/**
 * Created by malah on 12/12/17.
 */
public abstract class ClientManagerAsync<T> extends ClientManagerSync<T> {

    public ClientManagerAsync(Class<T> genericType) {
        super(genericType);
    }

    @Override
    public void readOne(EntityListener<T> listener, String url) {
        new ClientAsyncTask<>(this, listener, genericType, TypeMethod.READ).execute(url);
    }

    @Override
    public void readAll(EntityListener<List<T>> listener, String url) {
        new ClientAsyncTask<>(this, listener, null, TypeMethod.READ).execute(url);
    }

    @Override
    public void create(EntityListener<T> listener, String url, T object) {
        new ClientAsyncTask<>(this, listener, genericType, TypeMethod.CREATE, object).execute(url);
    }

    @Override
    public void update(EntityListener<T> listener, String url, Map<String, Object> datas) {
        new ClientAsyncTask<>(this, listener, genericType, TypeMethod.UPDATE, datas).execute(url);
    }

    @Override
    public void delete(EntityListener<T> listener, String url) {
        new ClientAsyncTask<>(this, listener, genericType, TypeMethod.DELETE).execute(url);
    }

    @Override
    public void action(String url) {
        new ClientAsyncTask<>(this, null, genericType, TypeMethod.ACTION).execute(url);
    }

}
