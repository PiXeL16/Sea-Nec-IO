package com.greenpixels.seanecio.components;

import android.app.Application;

import com.greenpixels.seanecio.modules.AppModule;

import dagger.Component;

/**
 * App component for dependency injection
 */

@Component(
        modules = AppModule.class
)
public interface AppComponent {

    Application application();

}