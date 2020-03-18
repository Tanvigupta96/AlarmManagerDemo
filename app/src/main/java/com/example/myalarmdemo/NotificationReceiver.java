package com.example.myalarmdemo;


import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.security.acl.NotOwnerException;

public class NotificationReceiver extends BroadcastReceiver {

    public NotificationReceiver(){

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"Inside Receiver",Toast.LENGTH_SHORT).show();

        NotificationManagerCompat myManager = NotificationManagerCompat.from(context);

        NotificationCompat.Builder myNotification = new NotificationCompat.Builder(context);
        myNotification.setContentTitle("Notification One");
        myNotification.setContentText("You need to see this notification-1");
        myNotification.setSmallIcon(R.drawable.ic_launcher_background);

        Intent i1 = new Intent(context, ActivityOnNotificaion1.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, i1, 0);
        myNotification.setContentIntent(pendingIntent);
        myNotification.setAutoCancel(true);

        myManager.notify(1,myNotification.build());

    }
}
