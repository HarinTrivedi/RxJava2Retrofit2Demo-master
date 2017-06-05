package com.hlabexamples.rxjava2retrofit2demo.main.forecast.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Harry's Lab on 27/05/17.
 */

public class Temp {
    @SerializedName("day")
    @Expose
    private Double day;

    @SerializedName("night")
    @Expose
    private Double night;

    @SerializedName("min")
    @Expose
    private Double min;

    @SerializedName("max")
    @Expose
    private Double max;

    public Double getDay() {
        return day;
    }

    public void setDay(Double day) {
        this.day = day;
    }

    public Double getNight() {
        return night;
    }

    public void setNight(Double night) {
        this.night = night;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }
}
