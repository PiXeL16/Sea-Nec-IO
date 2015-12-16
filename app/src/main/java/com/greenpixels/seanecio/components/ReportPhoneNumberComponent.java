package com.greenpixels.seanecio.components;

import com.greenpixels.seanecio.activities.ReportPhoneNumberActivity;
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
        modules = {ContextProvider.class, UtilsProvider.class, FirebaseProvider.class}
)
public interface ReportPhoneNumberComponent {

    void inject(ReportPhoneNumberActivity activity);

    ReportPhoneNumberPresenter presenter();
}