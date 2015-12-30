package com.greenpixels.seanecio.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.ui.FirebaseRecyclerAdapter;
import com.greenpixels.seanecio.model.BlacklistedPhoneNumber;

import javax.inject.Inject;

/**
 * Adapter for the blacklisted using the firebase recycle adapter functionality
 */
public class BlacklistedPhoneNumberAdapter extends FirebaseRecyclerAdapter<BlacklistedPhoneNumber, BlacklistedPhoneNumberAdapter.BlacklistedPhoneNumberViewHolder> {

    @Inject
    public BlacklistedPhoneNumberAdapter(Firebase ref) {
        super(BlacklistedPhoneNumber.class, android.R.layout.two_line_list_item, BlacklistedPhoneNumberViewHolder.class, ref.child(BlacklistedPhoneNumber.collectionName));
    }

    @Override
    public void populateViewHolder(BlacklistedPhoneNumberViewHolder viewHolder, BlacklistedPhoneNumber blacklistedPhoneNumber, int position) {
        viewHolder.blacklistedDescription.setText(blacklistedPhoneNumber.getDescription());
        viewHolder.blacklistedPhoneNumber.setText(blacklistedPhoneNumber.getPhoneNumber());
    }


    /**
     * ViewHolder for the BlacklistedPhoneNumberActivity
     */
     static class BlacklistedPhoneNumberViewHolder extends RecyclerView.ViewHolder {

        TextView blacklistedDescription;
        TextView blacklistedPhoneNumber;

        public BlacklistedPhoneNumberViewHolder(View itemView) {
            super(itemView);
            blacklistedDescription = (TextView)itemView.findViewById(android.R.id.text1);
            blacklistedPhoneNumber = (TextView) itemView.findViewById(android.R.id.text2);
        }
    }
}