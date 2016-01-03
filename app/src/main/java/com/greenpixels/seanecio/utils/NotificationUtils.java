package com.greenpixels.seanecio.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v7.app.NotificationCompat;

import com.greenpixels.seanecio.R;

/**
 * Utils for showing local notifications
 */
public class NotificationUtils {


    /**
     * Shows a notification with text, title and icons
     * @param notificationText
     * @param notificationTitle
     * @param notificationSmallIconResource
     * @param context
     */
    public static void showLocalNotification(String notificationText,String notificationTitle,int notificationSmallIconResource, Context context)
    {
        Notification notification = new NotificationCompat.Builder(context)
                .setTicker(notificationText)
                .setSmallIcon(notificationSmallIconResource)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher))
                .setContentTitle(notificationTitle)
                .setColor(context.getResources().getColor(R.color.colorPrimaryDark))
                .setContentText(notificationText)
                .setPriority(Notification.PRIORITY_HIGH)
                .setCategory(Notification.CATEGORY_CALL)
                .setAutoCancel(true)
                .build();

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);
    }
}
