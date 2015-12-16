package com.greenpixels.seanecio.modules;

import android.app.Application;
import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger2 Module for the application object
 * It provides the application object
 */
@Module
public class AppModule {

    @NonNull
    private final Application application;

    public AppModule(@NonNull Application app) {
        this.application = app;
    }

    @Provides
    Application provideApplication() {
        return application;
    }
}
