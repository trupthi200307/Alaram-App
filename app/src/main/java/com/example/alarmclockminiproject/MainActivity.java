package com.example.alarmclockminiproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.alarmclockminiproject.databinding.ActivityMainBinding;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding binding;
    private MaterialTimePicker timePicker;
    private Calendar calender;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        calender = Calendar.getInstance();

        createNotificationChannel();
        binding.selectTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePicker=new MaterialTimePicker.Builder().setTimeFormat(TimeFormat.CLOCK_12H).setHour(12).setMinute(0).setTitleText("Select Alarm Time").build();
                timePicker.show(getSupportFragmentManager(),"androidknowledge");
                timePicker.addOnPositiveButtonClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(timePicker.getHour()>12){
                            binding.selectTime.setText(String.format("%02d",(timePicker.getHour()-12))+":"+String.format("%02d",timePicker.getMinute())+"PM");
                        }
                        else{
                            binding.selectTime.setText(timePicker.getHour()+":"+timePicker.getMinute()+"AM");
                        }
                        //calender = Calendar.getInstance();
                        calender.set(Calendar.HOUR_OF_DAY,timePicker.getHour());
                        calender.set(Calendar.MINUTE,timePicker.getMinute());
                        calender.set(Calendar.SECOND,0);
                        calender.set(Calendar.MILLISECOND,0);
                    }
                });
            }
        });
        binding.setAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alarmManager =(AlarmManager) getSystemService(Context.ALARM_SERVICE);
                Intent intent = new Intent(MainActivity.this, AlarmReciever.class);
                pendingIntent =PendingIntent.getBroadcast(MainActivity.this,0,intent,0);

                alarmManager.setExact(AlarmManager.RTC_WAKEUP,calender.getTimeInMillis(),pendingIntent);
                Toast.makeText(MainActivity.this, "Alarm Set", Toast.LENGTH_SHORT).show();
            }
        });
        binding.cancelAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, AlarmReciever.class);
                pendingIntent=PendingIntent.getBroadcast(MainActivity.this,0,intent,0);
                if(alarmManager== null){
                    alarmManager=(AlarmManager) getSystemService(Context.ALARM_SERVICE);
                }
                alarmManager.cancel(pendingIntent);
                Toast.makeText(MainActivity.this, "Alarm Canceled", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void createNotificationChannel() {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            CharSequence name = "akchannel";
            String desc ="Channel for Alarm Manager";
            int imp= NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel= new NotificationChannel("androidknowledge",name,imp);
            channel.setDescription(desc);
            NotificationManager notificationManager=getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

    }

}