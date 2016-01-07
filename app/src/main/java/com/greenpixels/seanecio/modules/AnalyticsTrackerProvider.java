package com.greenpixels.seanecio.modules;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.greenpixels.seanecio.R;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Analitics tracker provider for injection
 */
@Module
public class AnalyticsTrackerProvider {

    //Analytics Tracker
    private Tracker _tracker;
    private Context _context;

    public AnalyticsTrackerProvider(Context context)
    {
        _context = context;
    }

    @Provides
    @NonNull
    @Singleton
    synchronized public Tracker provideTracker(){

        if (_tracker == null) {
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(_context);
            // To enable debug logging use: adb shell setprop log.tag.GAv4 DEBUG
            _tracker = analytics.newTracker(R.xml.global_tracker);
        }
        return _tracker;
    }


}
