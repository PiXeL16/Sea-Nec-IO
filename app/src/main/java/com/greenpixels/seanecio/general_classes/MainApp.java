package com.greenpixels.seanecio.general_classes;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.firebase.client.Firebase;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import timber.log.Timber;

/**
 * Created by chris on 12/14/15.
 */
public class  MainApp extends Application {

    // Monitors Memory Leaks
    private RefWatcher refWatcher;

    private static MainApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        this.init();
    }

    public void init() {

        //Leak Tracking
        refWatcher = LeakCanary.install(this);
        //Logging
        Timber.plant(new Timber.DebugTree());
        //FireBase
        Firebase.setAndroidContext(this);

    }

    public MainApp() {
        instance = this;
    }

    @NonNull
    public static Context getContext() {
        return instance;
    }

    @NonNull
    public static MainApp get(@NonNull Context context) {
        return (MainApp) context.getApplicationContext();
    }

    @NonNull
    public RefWatcher refWatcher() {
        return refWatcher;
    }
}