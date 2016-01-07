package com.greenpixels.seanecio.components;

import com.google.android.gms.analytics.Tracker;
import com.greenpixels.seanecio.activities.ReportPhoneNumberActivity;
import com.greenpixels.seanecio.modules.AnalyticsTrackerProvider;
import com.greenpixels.seanecio.modules.ContextProvider;
import com.greenpixels.seanecio.modules.FirebaseProvider;
import com.greenpixels.seanecio.modules.UtilsProvider;
import com.greenpixels.seanecio.presenters.ReportPhoneNumberPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Dagger2 component for the report phone number activity
 */
@Singleton
@Component(
        dependencies = AppComponent.class,
        modules = {ContextProvider.class, UtilsProvider.class, FirebaseProvider.class, AnalyticsTrackerProvider.class}
)
public interface ReportPhoneNumberComponent {

    void inject(ReportPhoneNumberActivity activity);

    ReportPhoneNumberPresenter presenter();

    Tracker tracker();
}