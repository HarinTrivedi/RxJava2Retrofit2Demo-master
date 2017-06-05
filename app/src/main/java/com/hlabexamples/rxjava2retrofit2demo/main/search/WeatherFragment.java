package com.hlabexamples.rxjava2retrofit2demo.main.search;


import android.app.Activity;
import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hlabexamples.rxjava2retrofit2demo.R;
import com.hlabexamples.rxjava2retrofit2demo.databinding.FragmentSearchBinding;
import com.hlabexamples.rxjava2retrofit2demo.main.forecast.ForecastFragment;
import com.hlabexamples.rxjava2retrofit2demo.main.search.model.WeatherData;
import com.hlabexamples.rxjava2retrofit2demo.utils.Utils;


/**
 * A simple {@link Fragment} subclass.
 */
public class WeatherFragment extends Fragment implements WeatherContract.ISearchView, View.OnClickListener {

    private FragmentSearchBinding binding;
    private WeatherPresenter presenter;

    private ProgressDialog progressDialog;
    private WeatherData weatherData;

    public WeatherFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new WeatherPresenter(this);
        binding.setListener(this);

        binding.include.toolbarCommonTitle.setText(R.string.title_weather);
    }

    @Override
    public void onClick(View v) {
        if (v == binding.btnGet) {
            presenter.validate(binding.edtCity.getText().toString());
        } else if (v == binding.btnMore) {
            goToMoreInfo();
        }
    }

    @Override
    public void showProgress() {
        progressDialog = Utils.showProgress(getActivity());
    }

    @Override
    public void hideProgress() {
        Utils.hideProgress(progressDialog);
    }

    @Override
    public void hideKeyboard() {
        Utils.hideSoftKeyboard(context());
    }

    @Override
    public void showValidationError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setWeatherData(WeatherData data) {
        weatherData = data;
        binding.setData(data);
        if (weatherData.getWeather() != null) {
            binding.tvDesc.setText(weatherData.getWeather().get(0).getMain());
        }
    }

    @Override
    public void goToMoreInfo() {
        hideKeyboard();
        Fragment fragment = new ForecastFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(getString(R.string.arg_id), weatherData.getId());
        fragment.setArguments(bundle);
        Utils.addFragment((AppCompatActivity) getActivity(), WeatherFragment.this, fragment);
    }

    @Override
    public Activity context() {
        return getActivity();
    }

    @Override
    public void destroyView() {
        presenter = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        destroyView();
    }
}
