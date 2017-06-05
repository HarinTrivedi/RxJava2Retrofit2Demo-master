package com.hlabexamples.rxjava2retrofit2demo.main.forecast;

import android.util.Log;

import com.hlabexamples.rxjava2retrofit2demo.api.ApiClient;
import com.hlabexamples.rxjava2retrofit2demo.common.ICallbackFinishedListener;
import com.hlabexamples.rxjava2retrofit2demo.main.forecast.model.ForecastData;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Harry's Lab on 29/05/17.
 */

class ForecastInteractor implements ForecastContract.IForecastInteractor<ForecastData> {

    private ICallbackFinishedListener<ForecastData> listener;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private Flowable<ForecastData> flowable;
    private Consumer<ForecastData> consumer;
    private Consumer<Throwable> throwable;

    @Override
    public void getForecastInfo(final String[] vals, ICallbackFinishedListener<ForecastData> listener) {
        this.listener = listener;
        flowable = ApiClient.getApiClient().getInstance().getForecastData(vals[0], vals[1], vals[2]).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
        mCompositeDisposable.add(flowable.subscribe(getConsumer(), getThrowable()));
    }

    @Override
    public void reloadForecastData() {
        mCompositeDisposable.add(flowable.subscribe(getConsumer(), getThrowable()));
    }

    public Consumer<ForecastData> getConsumer() {
        if (consumer == null) {
            consumer = new Consumer<ForecastData>() {
                @Override
                public void accept(@NonNull ForecastData forecastData) throws Exception {
                    if (forecastData != null) {
                        if (ApiClient.getApiClient().checkForSuccess(forecastData)) {
                            Log.i("onSuccess :", "onSuccess");
                            listener.onSuccess(forecastData);
                        } else {
                            listener.onError();
                        }
                    } else {
                        listener.onError();
                    }
                }
            };
        }
        return consumer;
    }

    public Consumer<Throwable> getThrowable() {
        if (throwable == null) {
            throwable = new Consumer<Throwable>() {
                @Override
                public void accept(@NonNull Throwable e) throws Exception {
                    Log.e("onError :", e.getMessage());
                    listener.onError();
                }
            };
        }
        return throwable;
    }

    @Override
    public void destroy() {
        mCompositeDisposable.clear();
        mCompositeDisposable.dispose();
    }
}
