package com.hlabexamples.rxjava2retrofit2demo.main.search;

import android.util.Log;

import com.hlabexamples.rxjava2retrofit2demo.api.ApiClient;
import com.hlabexamples.rxjava2retrofit2demo.common.ICallbackFinishedListener;
import com.hlabexamples.rxjava2retrofit2demo.main.search.model.WeatherData;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Harry's Lab on 29/05/17.
 */

class WeatherInteractor implements WeatherContract.ISearchInteractor<WeatherData> {

    private ICallbackFinishedListener<WeatherData> listener;
    private Observer<WeatherData> observer;

    @Override
    public void getWeatherInfo(String[] vals, ICallbackFinishedListener<WeatherData> listener) {
        this.listener = listener;
        Observable<WeatherData> observable = ApiClient.getApiClient().getInstance().getWeatherData(vals[0], vals[1], vals[2]);
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getWeatherObserver());
    }

    @Override
    public Observer<WeatherData> getWeatherObserver() {
        if (observer == null) {
            observer = new Observer<WeatherData>() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {

                }

                @Override
                public void onNext(@NonNull WeatherData weatherData) {
                    Log.i("onSuccess :", "");
                    if (weatherData != null) {
                        if (ApiClient.getApiClient().checkForSuccess(weatherData)) {
                            listener.onSuccess(weatherData);
                        } else {
                            listener.onError();
                        }
                    } else {
                        listener.onError();
                    }
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    Log.e("onError :", e.getMessage());
                    listener.onError();
                }

                @Override
                public void onComplete() {
                    Log.i("onComplete :", "");
                }
            };
        }
        return observer;
    }
}
