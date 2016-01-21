package com.greenpixels.seanecio.telephony;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
import com.greenpixels.seanecio.R;
import com.greenpixels.seanecio.general_classes.Constants;
import com.greenpixels.seanecio.model.BlacklistedPhoneNumber;
import com.greenpixels.seanecio.utils.AlertUtils;
import com.greenpixels.seanecio.utils.NotificationUtils;

import timber.log.Timber;

/**
 * The class will track incoming calls and show notifications or store the last number to be suggested when reporting a phone number
 */

public class IncomingCallReceiver extends BroadcastReceiver {

    private Context _context;
    private Intent _intent;
    /**
     * Local persistence of the number, to be able to show suggestions when reporting a phone number
     */
    private PhoneNumberLocalPersistence _phoneNumberLocalPersistence = new PhoneNumberLocalPersistence();

    /**
     * When a phone call is receive we hook up a phone state listener.
     * @param context
     * @param intent
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        _context = context;
        _intent = intent;
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        int events = PhoneStateListener.LISTEN_CALL_STATE;
        tm.listen(phoneStateListener, events);
    }

    /**
     * Phone State listener to get phone call information and numbers
     */
    private final PhoneStateListener phoneStateListener = new PhoneStateListener() {
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            String callState = "UNKNOWN";
            switch (state) {
                case TelephonyManager.CALL_STATE_IDLE:

                    break;
                case TelephonyManager.CALL_STATE_RINGING:

                    //Save last phone for persistence
                    Timber.d("Saving call locally "+incomingNumber);
                    _phoneNumberLocalPersistence.saveLastPhoneNumber(incomingNumber);

                    checkAndAlertBlacklistedPhoneNumber(incomingNumber);


                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:

                    break;
            }
            super.onCallStateChanged(state, incomingNumber);
        }
    };


    /**
     * Checks and shows an alert from if it founds that the calls belongs to a blacklisted phone number
     * @param phoneNumber
     */
    private final void checkAndAlertBlacklistedPhoneNumber(String phoneNumber)
    {
        //Strips the incoming phone number from country code and digits
        String stripedPhoneNumber = PhoneNumberUtils.stripCountryCodeFromPhoneNumber(phoneNumber, Constants.getDefaultCountryCode());

        //Creates the firebase ref: TODO: This should be injected
        Firebase ref = new Firebase(Constants.getFirebaseUrl());
        ref.keepSynced(true);

        Timber.i("Checking for blacklisted phone number");

        //Creates teh query where the blacklisted phone number is equal to the one we get
        Query queryRef = ref.child(BlacklistedPhoneNumber.collectionName).orderByKey().equalTo(stripedPhoneNumber);

        //Add a listener
        queryRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //A blacklisted phone number was found
               if(dataSnapshot.hasChildren()){

                   for (DataSnapshot child : dataSnapshot.getChildren()) {

                       Timber.i("Found blacklisted number");

                       //Parse the data
                       BlacklistedPhoneNumber blacklistedPhoneNumber = child.getValue(BlacklistedPhoneNumber.class);
                       //Creates the message
                       String message = String.format("Sea Necio!: %s", blacklistedPhoneNumber.getDescription());

                       Timber.i(message);
                       //Shows the message :TODO: this will change to some nicer format instead of a toast
                       CallNotificationHelper.showCallNotification(message,_context);
                   }

               }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Timber.i(firebaseError.getMessage());
            }
        });

    }


}