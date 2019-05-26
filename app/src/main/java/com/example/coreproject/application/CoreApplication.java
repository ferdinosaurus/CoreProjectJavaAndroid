package com.example.coreproject.application;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import com.example.coreproject.realm.RealmMigrations;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class CoreApplication extends Application {

    private static Context context;

    public static final String CHANNEL_1_ID = "channel1";
    public static final String CHANNEL_2_ID = "channel2";

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(CoreApplication.this);

        final RealmConfiguration configuration = new RealmConfiguration.Builder().name("CoreProject.realm").schemaVersion(1).migration(new RealmMigrations()).build();

        Realm.setDefaultConfiguration(configuration);
        Realm.getInstance(configuration);
    }


    public static Context getAppContext() {
        return CoreApplication.context;
    }

    private void createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel1 = new NotificationChannel(
                    CHANNEL_1_ID,
                    "Channel 1",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel1.setDescription("This is Channel 1");

            NotificationChannel channel2 = new NotificationChannel(
                    CHANNEL_2_ID,
                    "Channel 2",
                    NotificationManager.IMPORTANCE_LOW
            );
            channel2.setDescription("This is Channel 2");

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);
            manager.createNotificationChannel(channel2);
        }
    }
}
