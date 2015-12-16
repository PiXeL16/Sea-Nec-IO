package com.greenpixels.seanecio.modules;

import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.greenrobot.event.EventBus;

/**
 * Created by chris on 12/15/15.
 * General class that provides utils, like EventBus and others dagger2
 */
@Module
public class UtilsProvider {
    @Provides
    @NonNull
    @Singleton
    public EventBus provideEventBus(){
        return EventBus.getDefault();
    }
}

