package com.example.alarmclockminiproject;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class snoozeReciever extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        showNotification(context);
    }

    private void showNotification(Context context) {
        // Use NotificationManager to create and show the notification
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // Intent to open the MainActivity when the user taps the notification
        Intent mainIntent = new Intent(context, NotifictionActivity.class);
        mainIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, mainIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Build the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "androidknowledge")
                .setContentTitle("Alarm")
                .setContentText("Snoozing!")
                .setSmallIcon(R.drawable.ic_alarm)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        // Show the notification
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "androidknowledge";
            NotificationChannel channel = new NotificationChannel(channelId, "Alarm Channel", NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
            builder.setChannelId(channelId);
        }

        notificationManager.notify(1, builder.build());
    }

}
