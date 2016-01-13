package com.greenpixels.seanecio.adapters;

import com.firebase.client.Firebase;
import com.firebase.ui.FirebaseRecyclerAdapter;
import com.greenpixels.seanecio.R;
import com.greenpixels.seanecio.general_classes.Constants;
import com.greenpixels.seanecio.model.BlacklistedPhoneNumber;
import com.greenpixels.seanecio.telephony.PhoneNumberUtils;

import javax.inject.Inject;

/**
 * Adapter for the blacklisted phone numbers using the firebase recycle adapter functionality
 */
public class BlacklistedPhoneNumberAdapter extends FirebaseRecyclerAdapter<BlacklistedPhoneNumber, BlacklistedPhoneNumberViewHolder> {

    @Inject
    public BlacklistedPhoneNumberAdapter(Firebase ref) {
        super(BlacklistedPhoneNumber.class, R.layout.blacklisted_number_item, BlacklistedPhoneNumberViewHolder.class, ref.child(BlacklistedPhoneNumber.collectionName));
    }

    @Override
    public void populateViewHolder(BlacklistedPhoneNumberViewHolder viewHolder, BlacklistedPhoneNumber blacklistedPhoneNumber, int position) {
        viewHolder.blacklistedDescription.setText(blacklistedPhoneNumber.getDescription());
        viewHolder.blacklistedPhoneNumber.setText(PhoneNumberUtils.formatPhoneNumber(blacklistedPhoneNumber.getPhoneNumber(), Constants.getDefaultCountryCode()));
    }
}