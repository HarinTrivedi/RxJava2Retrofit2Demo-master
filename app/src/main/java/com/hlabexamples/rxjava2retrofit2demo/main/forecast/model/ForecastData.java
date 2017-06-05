package com.hlabexamples.rxjava2retrofit2demo.main.forecast.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.hlabexamples.rxjava2retrofit2demo.common.model.BaseModel;

import java.util.List;

/**
 * Created by Harry's Lab on 27/05/17.
 */

public class ForecastData extends BaseModel {

    @SerializedName("city")
    @Expose
    private City city;
    @SerializedName("cnt")
    @Expose
    private Integer cnt;
    @SerializedName("list")
    @Expose
    private List<Forecast> list = null;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public List<Forecast> getList() {
        return list;
    }

    public void setList(List<Forecast> list) {
        this.list = list;
    }
}
