package com.greenpixels.seanecio.telephony;


import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

import timber.log.Timber;

/**
 * Utils to parse and handle phone numbers
 */
public class PhoneNumberUtils {

    /**
     * Tries to remove the country code from the phone number to easy parsing if not retuns the same phone number
     * @param phoneNumberToStrip
     * @return
     */
    public static String stripCountryCodeFromPhoneNumber(String phoneNumberToStrip, String countryCode)
    {

        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();

        String returnValue = phoneNumberToStrip;

        try {
            Phonenumber.PhoneNumber phoneNumber = phoneUtil.parse(phoneNumberToStrip,countryCode);
            returnValue =  String.valueOf(phoneNumber.getNationalNumber());
        }
        catch (NumberParseException e)
        {
            Timber.e(e.getLocalizedMessage());
        }

        return returnValue;
    }



}


