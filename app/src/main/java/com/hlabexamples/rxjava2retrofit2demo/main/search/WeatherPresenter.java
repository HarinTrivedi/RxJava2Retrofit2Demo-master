package com.hlabexamples.rxjava2retrofit2demo.main.search;

import android.text.TextUtils;

import com.hlabexamples.rxjava2retrofit2demo.R;
import com.hlabexamples.rxjava2retrofit2demo.api.ApiClient;
import com.hlabexamples.rxjava2retrofit2demo.common.ICallbackFinishedListener;
import com.hlabexamples.rxjava2retrofit2demo.main.search.model.WeatherData;
import com.hlabexamples.rxjava2retrofit2demo.utils.Utils;

/**
 * Created by Harry's Lab on 29/05/17.
 */

class WeatherPresenter implements WeatherContract.ISearchPresenter, ICallbackFinishedListener<WeatherData> {

    private WeatherContract.ISearchView view;
    private WeatherContract.ISearchInteractor<WeatherData> interactor;

    public WeatherPresenter(WeatherContract.ISearchView view) {
        this.view = view;
        this.interactor = new WeatherInteractor();
    }

    @Override
    public void validate(String place) {
        view.hideKeyboard();
        if (!TextUtils.isEmpty(place))
            initSearchWeatherTask(place.trim());
        else
            view.showValidationError(view.context().getString(R.string.val_enter_city));
    }

    @Override
    public void initSearchWeatherTask(String place) {
        if (Utils.isNetworkAvailable(view.context(), true)) {
            view.showProgress();
            interactor.getWeatherInfo(new String[]{
                            view.context().getString(R.string.api_key),
                            place,
                            view.context().getString(R.string.val_unit)},
                    this);
        }
    }

    @Override
    public void onError() {
        view.hideProgress();
        ApiClient.getApiClient().showCommonError(view.context());
    }

    @Override
    public void onSuccess(WeatherData data) {
        view.hideProgress();
        view.setWeatherData(data);
    }
}
