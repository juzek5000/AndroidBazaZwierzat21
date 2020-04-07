package com.example.bazazwierzat21;


import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class App extends Application {
    public static final String KANAL_GLOWNY = "Kanał główny";
    public static final String KANAL_WYCIELENIA = "Wycielenia";
    public static final String KANAL_ZASUSZENIA = "Zasuszenia";
    public static final String KANAL_PG = "PG";
    public static final String KANAL_OW7 = "OW7";
    public static final String KANAL_SYNCHRONIZACJA = "Synchronizacja";



    @Override
    public void onCreate() {
        super.onCreate();

        createNotificationChannels();
    }

    private void createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel kanalGlowny = new NotificationChannel(
                    KANAL_GLOWNY,
                    "Kanał główny 1",
                    NotificationManager.IMPORTANCE_HIGH
            );
            kanalGlowny.setDescription("Kanał główny do uruchomiania serwisu");


            NotificationChannel wycielenia = new NotificationChannel(
                    KANAL_WYCIELENIA,
                    "Wycielenia",
                    NotificationManager.IMPORTANCE_LOW
            );
            wycielenia.setDescription("Kanał do powiadomień wycieleń");

            NotificationChannel synchronizacja = new NotificationChannel(
                    KANAL_SYNCHRONIZACJA,
                    "Synchronizacja",
                    NotificationManager.IMPORTANCE_LOW
            );
            synchronizacja.setDescription("Kanał do synchronizacji");

            NotificationChannel zasuszenia = new NotificationChannel(
                    KANAL_ZASUSZENIA,
                    "Zasuszenia",
                    NotificationManager.IMPORTANCE_LOW
            );
            zasuszenia.setDescription("Kanał do powiadomień zasuszenia");

            NotificationChannel pg = new NotificationChannel(
                    KANAL_PG,
                    "PG",
                    NotificationManager.IMPORTANCE_LOW
            );
            pg.setDescription("Kanał do powiadomień PG");

            NotificationChannel ow7 = new NotificationChannel(
                    KANAL_OW7,
                    "OW7",
                    NotificationManager.IMPORTANCE_LOW
            );
            ow7.setDescription("Kanał do powiadomień OW7");

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(kanalGlowny);
            manager.createNotificationChannel(wycielenia);
            manager.createNotificationChannel(zasuszenia);
            manager.createNotificationChannel(pg);
            manager.createNotificationChannel(ow7);
            manager.createNotificationChannel(synchronizacja);
        }
    }
}
