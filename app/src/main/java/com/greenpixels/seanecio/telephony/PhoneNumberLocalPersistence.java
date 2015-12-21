package com.greenpixels.seanecio.telephony;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Class in charge of saving phone call information locally
 * It will provide last call information as well
 * Using sharedPreference but will change in the future
 */
public class PhoneNumberLocalPersistence {

    private static final String PREFS_NAME = "SeaNecioPrefName";
    private static final String lastCallPrefKey = "lastCall";

    public void saveLastPhoneCall(String phoneNumber, Context cont)
    {
        SharedPreferences settings = cont.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(lastCallPrefKey, phoneNumber);

        // Commit the edits!
        editor.commit();
    }

    public String getLastPhoneCallNumber(Context cont)
    {
        // Restore preferences
        SharedPreferences settings = cont.getSharedPreferences(PREFS_NAME, 0);
        String phoneNumber = settings.getString(lastCallPrefKey, "");
        return phoneNumber;
    }

}
