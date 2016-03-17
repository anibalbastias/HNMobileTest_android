package anibalbastias.hnmobiletest.application;

import android.app.Application;
import android.util.Log;

import anibalbastias.hnmobiletest.network.CustomCookieManager;

/**
 * Created by anibalbastias on 14-03-16.
 */
public class HNMobileApp extends Application {
    private final static String LOG_TAG = Application.class.getSimpleName();
    private CustomCookieManager customCookieManager;

    @Override
    public void onCreate() {
        Log.d(LOG_TAG, "Application.onCreate - Initializing application...");

        super.onCreate();
        customCookieManager = new CustomCookieManager(this);

        Log.d(LOG_TAG, "Application.onCreate - Application initialized OK");
    }

    public CustomCookieManager getCustomCookieManager() {
        return customCookieManager;
    }
}
