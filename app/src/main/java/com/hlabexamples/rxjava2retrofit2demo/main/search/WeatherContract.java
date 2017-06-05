package com.hlabexamples.rxjava2retrofit2demo.main.search;

import com.hlabexamples.rxjava2retrofit2demo.common.ICallbackFinishedListener;
import com.hlabexamples.rxjava2retrofit2demo.common.IView;
import com.hlabexamples.rxjava2retrofit2demo.main.search.model.WeatherData;

import io.reactivex.Observer;

/**
 * Created by Harry's Lab on 05/06/17.
 */

public final class WeatherContract {
    interface ISearchView extends IView {
        void hideKeyboard();

        void showValidationError(String message);

        void setWeatherData(WeatherData data);

        void goToMoreInfo();
    }
    interface ISearchPresenter {
        void validate(String place);
        void initSearchWeatherTask(String place);
    }
    interface ISearchInteractor<T> {
        void getWeatherInfo(String[] vals, ICallbackFinishedListener<T> listener);

        Observer<T> getWeatherObserver();
    }
}
