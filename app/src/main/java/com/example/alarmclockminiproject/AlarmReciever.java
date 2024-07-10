package com.example.alarmclockminiproject;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class AlarmReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("recieve","in on recieve");
        Intent nextActivity = new Intent(context,NotifictionActivity.class);
        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent=PendingIntent.getActivity(context,0,nextActivity,0);
        NotificationCompat.Builder builder= new NotificationCompat.Builder(context,"androidknowledge").setSmallIcon(R.drawable.ic_baseline_notifications_24).setContentTitle("Seize the day!").setContentText("You've Got This!" +
                "").setAutoCancel(true).setDefaults(NotificationCompat.DEFAULT_ALL).setPriority(NotificationCompat.PRIORITY_HIGH).setContentIntent(pendingIntent);
        NotificationManagerCompat notificationManagerCompat= NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(124, builder.build());

    }
}
