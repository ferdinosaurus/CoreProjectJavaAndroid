package com.example.coreproject.helper;

import android.app.Activity;
import android.app.Notification;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.example.coreproject.R;


import static com.example.coreproject.application.CoreApplication.CHANNEL_1_ID;

public class NotificationHelper {

    Activity activity;

    public NotificationHelper(Activity activity) {
        this.activity = activity;
    }

    public void createNotification(String title,String message) {
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(activity);

        Notification notification = new NotificationCompat.Builder(activity, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManager.notify(1, notification);
    }
}
