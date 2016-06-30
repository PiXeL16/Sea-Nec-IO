package com.greenpixels.seanecio.components;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.greenpixels.seanecio.activities.MainActivity;
import com.greenpixels.seanecio.adapters.BlacklistedPhoneNumberAdapter;
import com.greenpixels.seanecio.modules.ContextProvider;
import com.greenpixels.seanecio.modules.FirebaseAnalyticsTrackerProvider;
import com.greenpixels.seanecio.modules.FirebaseProvider;
import com.greenpixels.seanecio.modules.UtilsProvider;
import com.greenpixels.seanecio.presenters.BlacklistedPhoneNumberListPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Dagger2 component for the blacklisted phone number activity
 */
@Singleton
@Component(
        dependencies = AppComponent.class,
        modules = {ContextProvider.class, UtilsProvider.class, FirebaseProvider.class, FirebaseAnalyticsTrackerProvider.class}
)
public interface BlacklistedPhoneNumberListComponent {

    void inject(MainActivity activity);

    BlacklistedPhoneNumberListPresenter presenter();

    BlacklistedPhoneNumberAdapter adapter();

    FirebaseAnalytics firebaseAnalytics();

}