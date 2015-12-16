/*
 * Copyright 2014 Google Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.greenpixels.seanecio.utils;

//import android.app.Activity;
//import android.app.Dialog;
//import android.content.Context;
//import android.content.DialogInterface;
//
//import com.google.android.gms.common.ConnectionResult;
//import com.google.android.gms.common.GooglePlayServicesUtil;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

import timber.log.Timber;

/**
 * Helper for Google Play services-related operations.
 */
public class PlayServicesUtils {


    public static boolean checkGooglePlaySevices(final Activity activity) {

        boolean returnValue = false;

        final int googlePlayServicesCheck = GooglePlayServicesUtil.isGooglePlayServicesAvailable(activity);
        switch (googlePlayServicesCheck) {
            case ConnectionResult.SUCCESS:
                Timber.d("Play service available");
                returnValue = true;
                break;
            case ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED:
            case ConnectionResult.SERVICE_DISABLED:
            case ConnectionResult.SERVICE_INVALID:
            case ConnectionResult.SERVICE_MISSING:
                returnValue = false;
                Timber.e("Play service unavailable");
                Timber.e(GooglePlayServicesUtil.getErrorString(googlePlayServicesCheck));
                showErrorDialog(googlePlayServicesCheck,activity);
                break;
        }
        return returnValue;
    }

    private static void showErrorDialog(int googlePlayServicesCheck ,final Activity activity)
    {
        if (GooglePlayServicesUtil.isUserRecoverableError(googlePlayServicesCheck)) {

            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(googlePlayServicesCheck, activity, 0);
            dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialogInterface) {

                    activity.finish();

                }
            });
            dialog.show();
        }else
        {
            Timber.i("This device is not supported");
        }

    }
}
