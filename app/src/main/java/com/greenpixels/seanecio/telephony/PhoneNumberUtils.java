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

    /**
     * Formats a phone number and add symbols for easy reading
     * @param phoneNumber
     * @param countryCode
     * @return
     */
    public static String formatPhoneNumber(String phoneNumber, String countryCode)
    {
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();

        String returnValue = phoneNumber;

        try {

            Phonenumber.PhoneNumber phoneNumberObject = phoneUtil.parse(phoneNumber, countryCode);
            returnValue =  phoneUtil.formatNumberForMobileDialing(phoneNumberObject,countryCode,true);
        }
        catch (NumberParseException e)
        {
            Timber.e(e.getLocalizedMessage());
        }

        return returnValue;
    }



}


