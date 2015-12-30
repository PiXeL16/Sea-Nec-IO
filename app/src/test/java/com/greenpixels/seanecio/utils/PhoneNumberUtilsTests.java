package com.greenpixels.seanecio.utils;

import com.greenpixels.seanecio.telephony.PhoneNumberUtils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the Phone Number utils
 */
public class PhoneNumberUtilsTests {

    /**
     * Test striping the phone number from country code and symbols
     * @throws Exception
     */
    @Test
    public void testStripCountryCode() throws Exception {

        String result = PhoneNumberUtils.stripCountryCodeFromPhoneNumber("+50660-51-10-10", "CR");

        assertEquals(result,"60511010");
    }
}
