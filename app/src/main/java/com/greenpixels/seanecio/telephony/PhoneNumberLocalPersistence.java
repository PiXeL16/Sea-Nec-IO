package com.greenpixels.seanecio.telephony;

import android.content.Context;
import android.content.SharedPreferences;

import com.greenpixels.seanecio.general_classes.MainApp;

/**
 * Class in charge of saving phone call information locally
 * It will provide last call information as well
 * Using sharedPreference but will change in the future
 */
public class PhoneNumberLocalPersistence {

    private static final String PREFS_NAME = "SeaNecioPrefName";
    private static final String lastCallPrefKey = "lastCall";

    /**
     * Save the phonenumber to local storage
     * @param phoneNumber
     * @param cont
     */
    public void saveLastPhoneNumber(String phoneNumber)
    {
        SharedPreferences settings = MainApp.getContext().getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(lastCallPrefKey, phoneNumber);

        // Commit the edits!
        editor.commit();
    }

    /**
     * Get the phone number information from local storage
     * @param cont
     * @return
     */
    public String getLastPhoneNumber()
    {
        // Restore preferences
        SharedPreferences settings = MainApp.getContext().getSharedPreferences(PREFS_NAME, 0);
        String phoneNumber = settings.getString(lastCallPrefKey, "");
        return phoneNumber;
    }

}
