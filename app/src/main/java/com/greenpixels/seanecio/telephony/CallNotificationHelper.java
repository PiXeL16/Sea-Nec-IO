package com.greenpixels.seanecio.telephony;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.greenpixels.seanecio.R;
import com.greenpixels.seanecio.utils.NotificationUtils;

/**
 * Helper class for showing the incoming call notification
 */
public class CallNotificationHelper {


    /**
     * Shows the full notification of the call, a toaster and a notification object
     * @param message
     * @param context
     */
    public static void showCallNotification(String message, Context context){

        NotificationUtils.showLocalNotification(message, context.getString(R.string.app_name), R.drawable.small_notification_icon, context);

        showCustomToaster(message, context);
    }

    /**
     * Shows a custom notification toaster. the native one is not very noticed by the user
     * @param message
     * @param context
     */
    public static void showCustomToaster(String message, Context context)
    {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        View view = inflater.inflate(R.layout.toast_alert_view,null);
        TextView textView = (TextView)view.findViewById(R.id.toast_message);

        textView.setText(message);

        Toast toast = new Toast(context);
        toast.setView(view);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
    }
}
