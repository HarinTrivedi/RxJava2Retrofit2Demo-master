package com.hlabexamples.rxjava2retrofit2demo.common;

/**
 * Created by Harry's Lab on 29/05/17.
 */

public interface ICallbackFinishedListener<T> {
    void onError();
    void onSuccess(T data);
}
