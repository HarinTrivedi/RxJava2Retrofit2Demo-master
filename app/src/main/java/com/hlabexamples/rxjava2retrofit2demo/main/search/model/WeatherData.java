package com.hlabexamples.rxjava2retrofit2demo.main.search.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.hlabexamples.rxjava2retrofit2demo.common.model.BaseModel;
import com.hlabexamples.rxjava2retrofit2demo.common.model.Coord;
import com.hlabexamples.rxjava2retrofit2demo.common.model.Weather;

import java.util.List;

/**
 * Created by Harry's Lab on 27/05/17.
 */

public class WeatherData extends BaseModel {

    @SerializedName("coord")
    @Expose
    private Coord coord;
    @SerializedName("main")
    @Expose
    private Main main;
    @SerializedName("weather")
    @Expose
    private List<Weather> weather;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }
}
