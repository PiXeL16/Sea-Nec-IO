package com.greenpixels.seanecio.modules;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.firebase.analytics.FirebaseAnalytics;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Analitics tracker provider for injection
 */
@Module
public class FirebaseAnalyticsTrackerProvider {

    //Analytics Tracker
    private FirebaseAnalytics _firebaseAnalytics;
    private Context _context;

    public FirebaseAnalyticsTrackerProvider(Context context)
    {
        _context = context;
    }

    @Provides
    @NonNull
    @Singleton
    synchronized public FirebaseAnalytics provideFirebaseAnalytics(){

        if (_firebaseAnalytics == null) {

           _firebaseAnalytics = FirebaseAnalytics.getInstance(_context);
        }
        return _firebaseAnalytics;
    }


}
