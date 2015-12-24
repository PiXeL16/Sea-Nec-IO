package com.greenpixels.seanecio.adapters;

import android.content.Context;
import android.widget.TextView;

import com.greenpixels.seanecio.R;
import com.greenpixels.seanecio.model.BlacklistedPhoneNumber;
import com.hannesdorfmann.annotatedadapter.annotation.Field;
import com.hannesdorfmann.annotatedadapter.annotation.ViewType;
import com.hannesdorfmann.annotatedadapter.support.recyclerview.SupportAnnotatedAdapter;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by chris on 12/24/15.
 */
public class BlacklistedPhoneNumberAdapter extends SupportAnnotatedAdapter implements BlacklistedPhoneNumberAdapterBinder {

    @ViewType(
            layout = R.layout.blacklisted_number_item,
            fields = {
                    @Field(
                            id = R.id.blacklisted_description,
                            name = "blacklisted_description",
                            type = TextView.class),
                    @Field(
                            id = R.id.blacklisted_number,
                            name = "blacklisted_number",
                            type = TextView.class)
            }) public final int VIEWTYPE_BLACKLISTED_NUMBER = 0;


    private List<BlacklistedPhoneNumber> _blacklistedPhoneNumbers;

    public List<BlacklistedPhoneNumber> getBlacklistedPhoneNumbers() {
        return _blacklistedPhoneNumbers;
    }

    public void setBlacklistedPhoneNumbers(List<BlacklistedPhoneNumber> blacklistedPhoneNumbers) {
        _blacklistedPhoneNumbers = blacklistedPhoneNumbers;
    }

    @Inject
    public BlacklistedPhoneNumberAdapter(Context context)
    {
        super(context);
    }

    @Override
    public int getItemCount() {
        return _blacklistedPhoneNumbers == null ? 0 : _blacklistedPhoneNumbers.size();
    }

    @Override
    public void bindViewHolder(BlacklistedPhoneNumberAdapterHolders.VIEWTYPE_BLACKLISTED_NUMBERViewHolder vh, int position) {
        BlacklistedPhoneNumber blacklistedPhoneNumber =  _blacklistedPhoneNumbers.get(position);


        vh.blacklisted_description.setText(blacklistedPhoneNumber.getDescription());
        vh.blacklisted_number.setText(blacklistedPhoneNumber.getPhoneNumber());

//        vh.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Context context = view.getContext();
//                Intent intent = new Intent(context, BirdDetailActivity.class);
//                // intent.putExtra(CheeseDetailActivity.EXTRA_NAME, holder.mBoundString);
//
//                context.startActivity(intent);
//
//            }
//        });

    }

}
