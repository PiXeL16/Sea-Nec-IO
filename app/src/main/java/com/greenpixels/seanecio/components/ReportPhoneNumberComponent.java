package com.greenpixels.seanecio.components;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.greenpixels.seanecio.activities.ReportPhoneNumberActivity;
import com.greenpixels.seanecio.modules.ContextProvider;
import com.greenpixels.seanecio.modules.FirebaseAnalyticsTrackerProvider;
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
        modules = {ContextProvider.class, UtilsProvider.class, FirebaseProvider.class, FirebaseAnalyticsTrackerProvider.class}
)
public interface ReportPhoneNumberComponent {

    void inject(ReportPhoneNumberActivity activity);

    ReportPhoneNumberPresenter presenter();

    FirebaseAnalytics firebaseAnalytics();
}