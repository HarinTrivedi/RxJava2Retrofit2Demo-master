package com.hlabexamples.rxjava2retrofit2demo.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.hlabexamples.rxjava2retrofit2demo.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Harry's Lab on 27/05/17.
 */

public class Utils {

    private static final String DATE_FORMAT = "dd MMM, yyyy";

    public static boolean isNetworkAvailable(Context c, boolean showMessage) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        boolean available = activeNetworkInfo != null && activeNetworkInfo.isConnected();

        if (!available && showMessage)
            Toast.makeText(c, R.string.str_no_internet, Toast.LENGTH_SHORT).show();

        return available;
    }

    public static void replaceFragment(AppCompatActivity activity, Fragment newFragment, boolean addToBackStack) {

        // Add second fragment by replacing first
        FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
        if (addToBackStack) {
            ft.addToBackStack(newFragment.getClass().getSimpleName());
        }
        ft.replace(R.id.container, newFragment);
        // Apply the transaction
        ft.commit();

    }

    public static void addFragment(AppCompatActivity activity, Fragment currentFragment, Fragment newFragment) {

        // Add second fragment by replacing first
        FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
        ft.addToBackStack(newFragment.getClass().getSimpleName());
        ft.add(R.id.container, newFragment);
        ft.hide(currentFragment);
        // Apply the transaction
        ft.commit();

    }
    /* --- Utility methods to manage progress dialog --- */

    /**
     * Shows progress dialog with default message
     *
     * @param context context
     * @return progress dialog instance
     */
    public static ProgressDialog showProgress(Context context) {
        ProgressDialog progress = new ProgressDialog(context);
        progress.setMessage(context.getString(R.string.msg_loading));
        progress.setCancelable(false);
        progress.show();
        return progress;
    }


    /**
     * Hides progress dialog
     *
     * @param progress progress dialog instance to hide
     */
    public static void hideProgress(ProgressDialog progress) {
        if (progress != null && progress.isShowing()) {
            progress.dismiss();
        }
    }

    /**
     * Hide keyboard
     */
    public static void hideKeyboard(Context c, View v) {
        if (v != null) {
            final InputMethodManager imm = (InputMethodManager) c.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

    /**
     * Method to hide keyboard
     *
     * @param activity
     */
    public static void hideSoftKeyboard(Activity activity) {
        final InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (inputMethodManager.isActive()) {
            if (activity.getCurrentFocus() != null) {
                inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
            }
        }
    }

    public static String parseDate(long date) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(date);
            return format.format(c.getTime());
        } catch (Exception ignored) {
        }
        return "";
    }
}
