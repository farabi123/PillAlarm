package com.example.admin.pillalarm;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class Alarm extends AppCompatActivity {
    AlarmManager alarmManager;
    TimePicker alarmTimePicker;
    TextView updateText;
    Context context;
    PendingIntent waitingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.context= this;

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmTimePicker = (TimePicker) findViewById(R.id.timePicker);
        updateText = (TextView) findViewById(R.id.updateAlarmStatus);

        //Create an instance of the Calendar
        final Calendar calendar = Calendar.getInstance();

        //Make an intent for the AlarmReceiver class
        final Intent alarmIntent = new Intent(this.context,AlarmReceiver.class);;

        //Initialize the on button
        Button alarmOn=(Button) findViewById(R.id.alarmOn);

        //Create an onclick listener to start the alarm
        alarmOn.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v){

                calendar.set(Calendar.HOUR_OF_DAY,alarmTimePicker.getHour());
                calendar.set(Calendar.MINUTE,alarmTimePicker.getMinute());

                int hour = alarmTimePicker.getHour();
                int min= alarmTimePicker.getMinute();
                System.out.println("hour is:"+hour);
                System.out.println("minute is:"+min);

                String hourString = String.valueOf(hour);
                String minString =  String.valueOf(min);

                if(hour > 12) {
                    hourString = String.valueOf(hour - 12);
                }
                if(min < 10){
                    minString = "0" + String.valueOf(min);
                }
                set_alarm_text("Time is set to "+ hourString + ":" + minString);

                // Indicates when on button is clicked fro the clock
                alarmIntent.putExtra("extra","ON");

                //Set_alarm_text("Alarm ON!");
                waitingIntent = PendingIntent.getBroadcast(Alarm.this,
                        0,alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                //Set the alarm Manager
                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),waitingIntent);

            }
        });

        //Initialize the off button
        Button alarmOff=(Button) findViewById(R.id.alarmOff);
        //Create an onclick listeer to turn off the alarm
        alarmOff.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                set_alarm_text("Alarm OFF!");
                //cancel the waiting intent
                alarmManager.cancel(waitingIntent);
                // Indicates when off button is clicked fro the clock
                alarmIntent.putExtra("extra","OFF");
                sendBroadcast(alarmIntent);
            }
        });
    }

    private void set_alarm_text(String output) {
        updateText.setText(output);
    }
 }
