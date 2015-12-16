package com.greenpixels.seanecio.modules;

import android.app.Application;
import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;

/**
 * Created by chris on 12/15/15.
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
