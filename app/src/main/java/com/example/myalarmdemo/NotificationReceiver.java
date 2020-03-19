package com.example.myalarmdemo;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.print.PrinterId;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.nio.file.ProviderNotFoundException;
import java.security.PrivateKey;
import java.security.acl.NotOwnerException;

import static android.content.Context.NOTIFICATION_SERVICE;

public class NotificationReceiver extends BroadcastReceiver {
    private NotificationManagerCompat notificationManager;
    private static final String CHANNEL_ID1 = "Channel_1";
    private static final String CHANNEL_ID2 = "Channel_2";
    private static int NOTIFICATION_ID1 = 1;
    private static int NOTIFICATION_ID2 = 2;
    private static NotificationCompat.Builder myNotification1;
    private static NotificationCompat.Builder myNotification2;


    public NotificationReceiver() {

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Inside Receiver", Toast.LENGTH_SHORT).show();


        try {

            notificationManager = NotificationManagerCompat.from(context);

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID1, "channel_1", NotificationManager.IMPORTANCE_HIGH);
                notificationManager.createNotificationChannel(notificationChannel);

                NotificationChannel notificationChannel1 = new NotificationChannel(CHANNEL_ID2, "channel_2", NotificationManager.IMPORTANCE_LOW);
                notificationManager.createNotificationChannel(notificationChannel1);
            }


            Intent i1 = new Intent(context, ActivityOnNotificaion1.class);
            i1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, NOTIFICATION_ID1, i1, PendingIntent.FLAG_UPDATE_CURRENT);


            myNotification1 = new NotificationCompat.Builder(context, CHANNEL_ID1);
            myNotification1.setContentTitle("Notification One");
            myNotification1.setContentText("You need to see this notification-1");
            myNotification1.setSmallIcon(R.drawable.ic_launcher_background);
            myNotification1.setVibrate(new long[]{100, 200});
            myNotification1.setContentIntent(pendingIntent);
            myNotification1.setAutoCancel(true);

            notificationManager.notify(NOTIFICATION_ID1, myNotification1.build());


            Thread.sleep(6000);


            Intent i2 = new Intent(context,ActivityOnNotificaion1.class);
            i2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent1 = PendingIntent.getActivity(context,NOTIFICATION_ID2,i2,PendingIntent.FLAG_UPDATE_CURRENT);

            myNotification2 = new NotificationCompat.Builder(context,CHANNEL_ID2);
            myNotification2.setContentTitle("Notification Two");
            myNotification2.setContentText("You need to see this notification-2");
            myNotification2.setSmallIcon(R.drawable.ic_launcher_background);
            myNotification2.setContentIntent(pendingIntent1);
            myNotification2.setAutoCancel(true);

            notificationManager.notify(NOTIFICATION_ID2,myNotification2.build());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }




    }
}
