package com.gmpvpc.android.manager.client;

import android.os.AsyncTask;

import com.gmpvpc.android.manager.base.ClientManagerAsync;
import com.gmpvpc.android.manager.base.EntityListener;
import com.gmpvpc.android.utils.JsonUtils;

import java.util.Map;

/**
 * Created by malah on 20/03/18.
 */
public class ClientAsyncTask<T, U> extends AsyncTask<String, Void, String> {

    private Class<U> genericType;
    private ClientManagerAsync<T> clientManagerAsync;
    private EntityListener<U> listener;
    private T object;
    private Map<String, Object> datas;
    private TypeMethod typeMethod;

    public ClientAsyncTask(ClientManagerAsync<T> clientManagerAsync, EntityListener<U> listener, Class<U> genericType, TypeMethod typeMethod) {
        this.clientManagerAsync = clientManagerAsync;
        this.listener = listener;
        this.typeMethod = typeMethod;
        this.genericType = genericType;
    }

    public ClientAsyncTask(ClientManagerAsync<T> clientManagerAsync, EntityListener<U> listener, Class<U> genericType, TypeMethod typeMethod, T object) {
        this.clientManagerAsync = clientManagerAsync;
        this.listener = listener;
        this.genericType = genericType;
        this.typeMethod = typeMethod;
        this.object = object;
    }

    public ClientAsyncTask(ClientManagerAsync<T> clientManagerAsync, EntityListener<U> listener, Class<U> genericType, TypeMethod typeMethod, Map<String, Object> object) {
        this.clientManagerAsync = clientManagerAsync;
        this.listener = listener;
        this.genericType = genericType;
        this.typeMethod = typeMethod;
        this.datas = datas;
    }

    @Override
    protected String doInBackground(String... urls) {
        switch (typeMethod) {
            case READ:
                return clientManagerAsync.get(urls[0]);
            case CREATE:
                return clientManagerAsync.post(urls[0], object);
            case UPDATE:
                return clientManagerAsync.put(urls[0], datas);
            case DELETE:
                return clientManagerAsync.delete(urls[0]);
            case ACTION:
                return clientManagerAsync.put(urls[0]);
            default:
                throw new RuntimeException("wrong method");
        }
    }

    @Override
    protected void onPostExecute(String result) {
        if (listener == null) {
            return;
        }
        U parse = JsonUtils.parse(result, genericType);
        if (parse != null) {
            listener.fireResponse(parse);
        }
    }
}
