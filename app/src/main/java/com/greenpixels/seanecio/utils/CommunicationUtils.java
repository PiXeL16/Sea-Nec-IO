package com.greenpixels.seanecio.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.telephony.TelephonyManager;

public class CommunicationUtils {
	public static void sendEmail(String[] to, String subject, String message, Activity activity){
		Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT,subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);
        emailIntent.setType("message/rfc822");
        activity.startActivity(Intent.createChooser(emailIntent, "Seleccione una opción"));
	}

    public static boolean isTelephonyEnabled(Context context){
            TelephonyManager tm = (TelephonyManager)context.getSystemService(context.TELEPHONY_SERVICE);
            return tm != null && tm.getSimState()== TelephonyManager.SIM_STATE_READY;
    }
	
	public static void makeCall(String phoneNumber, Context context){
		String number = String.format("tel:%s", phoneNumber);
        Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse(number));
        context.startActivity(callIntent);
	}
}
