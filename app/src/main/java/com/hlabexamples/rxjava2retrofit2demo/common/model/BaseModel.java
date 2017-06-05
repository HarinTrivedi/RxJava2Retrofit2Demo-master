package com.hlabexamples.rxjava2retrofit2demo.common.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Harry's Lab on 27/05/17.
 */

public class BaseModel {

    @SerializedName("cod")
    @Expose
    private String cod;
//    @SerializedName("message")
//    @Expose
//    private Integer message;

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

//    public Integer getMessage() {
//        return message;
//    }
//
//    public void setMessage(Integer message) {
//        this.message = message;
//    }
}
