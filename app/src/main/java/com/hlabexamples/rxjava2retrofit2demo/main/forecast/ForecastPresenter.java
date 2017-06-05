package com.hlabexamples.rxjava2retrofit2demo.main.forecast;

import com.hlabexamples.rxjava2retrofit2demo.R;
import com.hlabexamples.rxjava2retrofit2demo.api.ApiClient;
import com.hlabexamples.rxjava2retrofit2demo.common.ICallbackFinishedListener;
import com.hlabexamples.rxjava2retrofit2demo.main.forecast.model.ForecastData;
import com.hlabexamples.rxjava2retrofit2demo.utils.Utils;

/**
 * Created by Harry's Lab on 29/05/17.
 */

class ForecastPresenter implements ForecastContract.IForecastPresenter, ICallbackFinishedListener<ForecastData> {

    private ForecastContract.IForecastView view;
    private ForecastContract.IForecastInteractor<ForecastData> interactor;

    ForecastPresenter(ForecastContract.IForecastView view) {
        this.view = view;
        this.interactor = new ForecastInteractor();
    }

    @Override
    public void initForecastTask(String id) {
        if (Utils.isNetworkAvailable(view.context(), true)) {
            view.showProgress();
            interactor.getForecastInfo(new String[]{
                            view.context().getString(R.string.api_key),
                            id,
                            view.context().getString(R.string.val_unit)},
                    this);
        }
    }

    @Override
    public void refreshTask() {
        if (Utils.isNetworkAvailable(view.context(), true)) {
            interactor.reloadForecastData();
        }
    }

    @Override
    public void onError() {
        view.hideProgress();
        ApiClient.getApiClient().showCommonError(view.context());
    }

    @Override
    public void onSuccess(ForecastData data) {
        view.hideProgress();
        view.setForecastData(data);
    }
}
