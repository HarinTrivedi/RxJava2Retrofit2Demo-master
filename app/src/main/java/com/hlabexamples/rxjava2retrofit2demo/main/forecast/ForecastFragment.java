package com.hlabexamples.rxjava2retrofit2demo.main.forecast;


import android.app.Activity;
import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hlabexamples.rxjava2retrofit2demo.R;
import com.hlabexamples.rxjava2retrofit2demo.adapter.ForecastAdapter;
import com.hlabexamples.rxjava2retrofit2demo.databinding.FragmentForecastBinding;
import com.hlabexamples.rxjava2retrofit2demo.main.forecast.model.Forecast;
import com.hlabexamples.rxjava2retrofit2demo.main.forecast.model.ForecastData;
import com.hlabexamples.rxjava2retrofit2demo.utils.Utils;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ForecastFragment extends Fragment implements ForecastContract.IForecastView, SwipeRefreshLayout.OnRefreshListener {

    private FragmentForecastBinding binding;
    private ForecastPresenter presenter;
    private ProgressDialog progressDialog;

    private List<Forecast> forecastItems;
    private ForecastAdapter forecastAdapter;

    public ForecastFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_forecast, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter = new ForecastPresenter(this);
        binding.swipe.setColorSchemeColors(ContextCompat.getColor(getContext(), R.color.colorPrimary));
        binding.swipe.setOnRefreshListener(this);
        binding.rvForecast.setLayoutManager(new LinearLayoutManager(getActivity()));
        Bundle bundle = getArguments();
        if (bundle != null)
            presenter.initForecastTask(String.valueOf(bundle.getInt(getString(R.string.arg_id))));

        binding.include.toolbarCommonTitle.setText(R.string.title_forecast);
    }

    @Override
    public Activity context() {
        return getActivity();
    }

    @Override
    public void showProgress() {
        progressDialog = Utils.showProgress(getActivity());
    }

    @Override
    public void hideProgress() {
        if (binding.swipe.isRefreshing())
            binding.swipe.setRefreshing(false);
        Utils.hideProgress(progressDialog);
    }

    @Override
    public void setForecastData(ForecastData data) {
        binding.tvCity.setText(data.getCity().getName());
        this.forecastItems = data.getList();
        setAdapter();
    }

    @Override
    public void onRefresh() {
        presenter.refreshTask();
    }

    @Override
    public void setEmptyView() {

    }

    public void setAdapter() {
        if (forecastItems != null && forecastItems.size() > 0) {
            forecastAdapter = new ForecastAdapter(getActivity(), forecastItems);
            binding.rvForecast.setAdapter(forecastAdapter);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        onDestroyView();
    }

    @Override
    public void destroyView() {
        presenter = null;
    }

}
