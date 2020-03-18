package com.example.myalarmdemo;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.security.acl.NotOwnerException;

import static android.content.Context.NOTIFICATION_SERVICE;

public class NotificationReceiver extends BroadcastReceiver {
    private NotificationManager notificationManager;
    private static final String CHANNEL_ID = "Channel_1";
    private static int NOTIFICATION_ID = 1;
    private static NotificationCompat.Builder myNotification;




    public NotificationReceiver(){

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"Inside Receiver",Toast.LENGTH_SHORT).show();



        notificationManager = (NotificationManager)context.getSystemService(NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID,"channel_1",NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        Intent i1 = new Intent(context, ActivityOnNotificaion1.class);
        i1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, NOTIFICATION_ID, i1, PendingIntent.FLAG_UPDATE_CURRENT);


        myNotification = new NotificationCompat.Builder(context,CHANNEL_ID);
        myNotification.setContentTitle("Notification One");
        myNotification.setContentText("You need to see this notification-1");
        myNotification.setSmallIcon(R.drawable.ic_launcher_background);
        myNotification.setVibrate(new long[]{100, 200});
        myNotification.setContentIntent(pendingIntent);
        myNotification.setAutoCancel(true);

        notificationManager.notify(NOTIFICATION_ID,myNotification.build());

    }
}
