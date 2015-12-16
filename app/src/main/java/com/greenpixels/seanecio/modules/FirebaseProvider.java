package com.greenpixels.seanecio.modules;

import android.support.annotation.NonNull;

import com.firebase.client.Firebase;
import com.greenpixels.seanecio.general_classes.Constants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.greenrobot.event.EventBus;

/**
 * Dagger2 Module to provide the Firebase Ref
 */
@Module
public class FirebaseProvider {
    @Provides
    @NonNull
    @Singleton
    public Firebase provideFirebase(){
        return new Firebase(Constants.getFirebaseUrl());
    }
}
