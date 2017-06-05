package com.hlabexamples.rxjava2retrofit2demo.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hlabexamples.rxjava2retrofit2demo.R;
import com.hlabexamples.rxjava2retrofit2demo.main.search.WeatherFragment;
import com.hlabexamples.rxjava2retrofit2demo.utils.Utils;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0)
            getSupportFragmentManager().popBackStack();
        else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Utils.replaceFragment(this, new WeatherFragment(),false);
    }
}
