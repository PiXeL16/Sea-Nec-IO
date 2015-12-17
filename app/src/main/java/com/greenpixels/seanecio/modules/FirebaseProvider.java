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
        Firebase firebase = new Firebase(Constants.getFirebaseUrl());
        firebase.keepSynced(true);
        return firebase;
    }
}
