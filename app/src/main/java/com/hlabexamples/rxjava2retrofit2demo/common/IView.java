package com.hlabexamples.rxjava2retrofit2demo.common;

import android.app.Activity;

/**
 * Created by Harry's Lab on 29/05/17.
 */

public interface IView {
    Activity context();

    void destroyView();

    void showProgress();

    void hideProgress();
}
