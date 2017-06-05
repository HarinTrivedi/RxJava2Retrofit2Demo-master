package com.hlabexamples.rxjava2retrofit2demo.utils;

import android.databinding.BindingAdapter;
import android.widget.TextView;

/**
 * Created by Harry's Lab on 29/05/17.
 */

public class CommonBindingUtils {

    @BindingAdapter(value = {"temperature","unit"})
    public static void setTemp(TextView tv, double temp, String unit) {
        tv.setText(String.format("%s %s", String.format("%.1f", temp), unit));
    }

}
