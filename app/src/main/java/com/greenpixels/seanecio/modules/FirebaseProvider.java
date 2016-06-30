package com.greenpixels.seanecio.modules;

import android.support.annotation.NonNull;

import com.google.firebase.database.FirebaseDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger2 Module to provide the Firebase Ref
 */
@Module
public class FirebaseProvider {
    @Provides
    @NonNull
    @Singleton
    public FirebaseDatabase provideFirebase(){
        FirebaseDatabase firebase = FirebaseDatabase.getInstance();
        firebase.getReference().keepSynced(true);
        return firebase;
    }
}
