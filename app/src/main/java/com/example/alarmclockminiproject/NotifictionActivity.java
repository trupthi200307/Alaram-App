package com.example.alarmclockminiproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class NotifictionActivity extends AppCompatActivity {
    private static final int ALARM_REQUEST_CODE = 123;
    Button b,s;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifiction);

        b=findViewById(R.id.s);
        s=findViewById(R.id.b2);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                snoozeAlarm(NotifictionActivity.this);
                Toast.makeText(NotifictionActivity.this, "Alarm snoozed", Toast.LENGTH_SHORT).show();
            }
        });

       s.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               cancelSnoozeAlarm(NotifictionActivity.this);
               Intent intent = new Intent(NotifictionActivity.this, MainActivity.class);
               startActivity(intent);
           }
       });


    }
    private void snoozeAlarm(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, snoozeReciever.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, ALARM_REQUEST_CODE, intent, 0);

        long snoozeTime = System.currentTimeMillis() + 1 * 60 * 1000;

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, snoozeTime, pendingIntent);


    }
    private void cancelSnoozeAlarm(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, snoozeReciever.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, ALARM_REQUEST_CODE, intent, 0);
        alarmManager.cancel(pendingIntent);
    }
}
