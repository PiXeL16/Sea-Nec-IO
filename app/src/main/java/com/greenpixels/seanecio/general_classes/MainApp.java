package com.greenpixels.seanecio.general_classes;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.crashlytics.android.Crashlytics;
import com.firebase.client.Firebase;
import com.greenpixels.seanecio.components.AppComponent;
import com.greenpixels.seanecio.components.DaggerAppComponent;
import com.greenpixels.seanecio.modules.AppModule;
import com.parse.Parse;
import com.parse.ParseInstallation;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import io.fabric.sdk.android.Fabric;
import timber.log.Timber;

/**
 * MainApp object that initialize all dependencies
 */
public class  MainApp extends Application {

    // Monitors Memory Leaks
    private RefWatcher refWatcher;

    // App instance
    private static MainApp instance;

    //AppComponent for injection
    @Nullable
    private volatile AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        this.init();
    }

    /**
     * Initialize the app object
     */
    public void init() {

        //Leak Tracking
//        refWatcher = LeakCanary.install(this);
        //Logging
        Timber.plant(new Timber.DebugTree());
        //FireBase
        Firebase.setAndroidContext(this);
        //Set local persistence
        Firebase.getDefaultConfig().setPersistenceEnabled(true);

        //Crashes
        Fabric.with(this, new Crashlytics());

        //Pushes
        Parse.initialize(this, "uAWZy2slciWWyH00iQUJvGf0LAfWWCTpLNbUKfs2", "6Ua9Rvta95wnM1pRH83zoke8flSUZ5eCiiDkhkBb");
        ParseInstallation.getCurrentInstallation().saveInBackground();

    }

    public MainApp() {
        instance = this;
    }

    /**
     * Returns the context of the app
     * @return
     */
    @NonNull
    public static Context getContext() {
        return instance;
    }

    /**
     * Returns the app context
     * @param context
     * @return
     */
    @NonNull
    public static MainApp get(@NonNull Context context) {
        return (MainApp) context.getApplicationContext();
    }

    /**
     * Returns the Refwatcher object
     * @return
     */
    @NonNull
    public RefWatcher refWatcher() {
        return refWatcher;
    }


    /**
     * Creates the appComponent object if not created and returns it
     * @return
     */
    @NonNull
    public AppComponent appComponent() {
        if (appComponent == null) {
            synchronized (MainApp.class) {
                if (appComponent == null) {
                    appComponent = createAppComponent();
                }
            }
        }

        return appComponent;
    }

    /**
     * Creates the app component object
     * @return
     */
    @NonNull
    private AppComponent createAppComponent() {
        return DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();

    }
}