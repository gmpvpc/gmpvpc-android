package com.gmpvpc.android.manager.base;

import com.gmpvpc.android.manager.client.ClientAsyncTask;
import com.gmpvpc.android.manager.client.TypeMethod;

import java.util.List;

/**
 * Created by malah on 12/12/17.
 */
public abstract class ClientManagerAsync<T> implements ClientManager<T> {

    protected Class<T> genericType;

    public ClientManagerAsync(Class<T> genericType) {
        this.genericType = genericType;
    }

    public abstract String get(String url);
    public abstract String post(String url, T object);
    public abstract String put(String url, T object);
    public abstract String delete(String url);

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
    public void update(EntityListener<T> listener, String url, T object) {
        new ClientAsyncTask<>(this, listener, genericType, TypeMethod.UPDATE, object).execute(url);
    }

    @Override
    public void delete(EntityListener<T> listener, String url) {
        new ClientAsyncTask<>(this, listener, genericType, TypeMethod.DELETE).execute(url);
    }

}
