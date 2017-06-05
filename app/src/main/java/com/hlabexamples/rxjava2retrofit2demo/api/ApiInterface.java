package com.hlabexamples.rxjava2retrofit2demo.api;

import com.hlabexamples.rxjava2retrofit2demo.main.forecast.model.ForecastData;
import com.hlabexamples.rxjava2retrofit2demo.main.search.model.WeatherData;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Api interface class for api call repository
 */
public interface ApiInterface {

    set api key in constants.xml

    String baseUrl = "http://api.openweathermap.org/data/2.5/";

    @GET("weather")
    Observable<WeatherData> getWeatherData(@Query("appid") String apiKey, @Query("q") String place, @Query("units") String units);


    @GET("forecast/daily")
    Flowable<ForecastData> getForecastData(@Query("appid") String apiKey, @Query("id") String id, @Query("units") String units);
}
