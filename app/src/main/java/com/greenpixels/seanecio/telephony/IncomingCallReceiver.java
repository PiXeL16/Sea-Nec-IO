package com.greenpixels.seanecio.telephony;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import timber.log.Timber;

/**
 * Created by chris on 12/14/15.
 * The class will track incoming calls and show notifications or store the last number to be suggested when reporting a phone number
 */

public class IncomingCallReceiver extends BroadcastReceiver {

    private Context _context;
    private Intent _intent;
    private PhoneNumberLocalPersistence _phoneNumberLocalPersistence = new PhoneNumberLocalPersistence();

    @Override
    public void onReceive(Context context, Intent intent) {
        _context = context;
        _intent = intent;
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        int events = PhoneStateListener.LISTEN_CALL_STATE;
        tm.listen(phoneStateListener, events);
    }

    private final PhoneStateListener phoneStateListener = new PhoneStateListener() {
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            String callState = "UNKNOWN";
            switch (state) {
                case TelephonyManager.CALL_STATE_IDLE:
                    callState = "IDLE";
                    break;
                case TelephonyManager.CALL_STATE_RINGING:

                    //Save last phone for persistence
                    _phoneNumberLocalPersistence.saveLastPhoneCall(incomingNumber, _context);

                    // -- check international call or not.
//                    if (incomingNumber.startsWith("00")) {
//                        Toast.makeText(_context, "International Call- " + incomingNumber, Toast.LENGTH_LONG).show();
//                        callState = "International - Ringing (" + incomingNumber+ ")";
//                    } else {
//                        Toast.makeText(_context, "Local Call - " + incomingNumber, Toast.LENGTH_LONG).show();
//                        callState = "Local - Ringing (" + incomingNumber + ")";
//                    }
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:
//                    String dialingNumber = _intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
//                    if (dialingNumber.startsWith("00")) {
//                        Toast.makeText(_context,"International - " + dialingNumber,Toast.LENGTH_LONG).show();
//                        callState = "International - Dialing (" + dialingNumber+ ")";
//                    } else {
//                        Toast.makeText(_context, "Local Call - " + dialingNumber,Toast.LENGTH_LONG).show();
//                        callState = "Local - Dialing (" + dialingNumber + ")";
//                    }
//                    break;
            }
            Timber.d("onCallStateChanged " + callState);
            super.onCallStateChanged(state, incomingNumber);
        }
    };

}