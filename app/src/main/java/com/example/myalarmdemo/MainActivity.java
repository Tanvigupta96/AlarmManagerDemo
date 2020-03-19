package com.example.myalarmdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    AlarmManager myAlarmManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void startSomething(View view) {
        myAlarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        Toast.makeText(this, "Alarm stated", Toast.LENGTH_SHORT).show();


        Intent intent = new Intent(this, NotificationReceiver.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        intent.setAction("com.myown.receiver.Message");
        intent.addCategory("android.intent.category.DEFAULT");
        PendingIntent pd = PendingIntent.getBroadcast(this, 0, intent, 0);

        myAlarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 20000 * 6, pd);


    }

    public void stopSomething(View view) {

        Intent intent = new Intent();
        intent.setAction("com.myown.receiver.Message");
        intent.addCategory("android.intent.category.DEFAULT");
        PendingIntent pd = PendingIntent.getBroadcast(this, 0, intent, 0);
        myAlarmManager.cancel(pd);
        Toast.makeText(this, "Alarm stopped", Toast.LENGTH_SHORT).show();


    }
}
