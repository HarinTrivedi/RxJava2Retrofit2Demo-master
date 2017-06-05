package com.hlabexamples.rxjava2retrofit2demo.main.forecast;

import com.hlabexamples.rxjava2retrofit2demo.common.ICallbackFinishedListener;
import com.hlabexamples.rxjava2retrofit2demo.common.IView;
import com.hlabexamples.rxjava2retrofit2demo.main.forecast.model.ForecastData;

import io.reactivex.functions.Consumer;

/**
 * Created by indianic on 05/06/17.
 */

public final class ForecastContract {
    interface IForecastView extends IView {
        void setForecastData(ForecastData data);
        void setEmptyView();
    }
    interface IForecastPresenter {
        void initForecastTask(String id);
        void refreshTask();
    }
    interface IForecastInteractor<T> {
        void getForecastInfo(String[] vals, ICallbackFinishedListener<T> listener);

        void reloadForecastData();

        //    DisposableSubscriber<T> getObserver();
        Consumer<ForecastData> getConsumer();

        Consumer<Throwable> getThrowable();

        void destroy();
    }
}
