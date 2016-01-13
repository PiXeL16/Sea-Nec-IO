package com.greenpixels.seanecio.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.greenpixels.seanecio.R;

/**
 * View holder class for the blacklisted phone numbers
 *
 */
public class BlacklistedPhoneNumberViewHolder extends RecyclerView.ViewHolder {

    TextView blacklistedDescription;
    TextView blacklistedPhoneNumber;

    public BlacklistedPhoneNumberViewHolder(View itemView) {
        super(itemView);
        blacklistedDescription = (TextView)itemView.findViewById(R.id.blacklisted_description);
        blacklistedPhoneNumber = (TextView) itemView.findViewById(R.id.blacklisted_number);
    }

}
