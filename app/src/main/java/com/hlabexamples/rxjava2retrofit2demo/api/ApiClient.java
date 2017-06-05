package com.hlabexamples.rxjava2retrofit2demo.api;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.hlabexamples.rxjava2retrofit2demo.R;
import com.hlabexamples.rxjava2retrofit2demo.common.model.BaseModel;

import java.util.concurrent.TimeUnit;

import io.reactivex.annotations.NonNull;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Rest client instance to access api call in Api interface
 */
public class ApiClient {

    private static ApiClient apiClient;

    private ApiInterface apiCallback;
    private final int RESULT_OK = 200;

    public static ApiClient getApiClient() {
        if (apiClient == null) {
            apiClient = new ApiClient();
        }
        return apiClient;
    }

    /**
     * Static method to to get api client instance
     *
     * @return ApiInterface instance
     */
    public ApiInterface getInstance() {
        try {
            if (apiCallback == null) {

                OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
                httpClient.connectTimeout(60, TimeUnit.SECONDS);
                httpClient.readTimeout(60, TimeUnit.SECONDS);
                HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
                logging.setLevel(HttpLoggingInterceptor.Level.BODY);
                httpClient.addInterceptor(logging);  // <-- this is the important line!

                Retrofit client = new Retrofit.Builder()
                        .baseUrl(ApiInterface.baseUrl)
                        .client(httpClient.build())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                apiCallback = client.create(ApiInterface.class);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return apiCallback;
    }


    public boolean checkForSuccess(final @NonNull BaseModel weatherData) {
        String code = weatherData.getCod();
        Log.i("onNext :", String.valueOf(code));
        if (Integer.parseInt(code) == RESULT_OK) {
            return true;
        }
//       else if (showError) {
//            if (weatherData.getMessage() != null)
//                Toast.makeText(context, weatherData.getMessage(), Toast.LENGTH_SHORT).show();
//        }
        return false;
    }

    public void showCommonError(Context context) {
        Toast.makeText(context, R.string.str_something_went_wrong, Toast.LENGTH_SHORT).show();
    }

}
