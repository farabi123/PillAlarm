package com.example.admin.pillalarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

        //Controls back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.context= this;

        //Initialize object values
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmTimePicker = (TimePicker) findViewById(R.id.timePicker);
        updateText = (TextView) findViewById(R.id.updateAlarmStatus);

        //Create an instance of the Calendar
        final Calendar calendar = Calendar.getInstance();
        //Create an intent for the AlarmReceiver class
        final Intent alarmIntent = new Intent(this.context,AlarmReceiver.class);;

        //Initialize the on button
        Button alarmOn=(Button) findViewById(R.id.alarmOn);
        //Create an onclick listener to start the alarm
        alarmOn.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Set hour and minute in calendar
                calendar.set(Calendar.HOUR_OF_DAY,alarmTimePicker.getHour());
                calendar.set(Calendar.MINUTE,alarmTimePicker.getMinute());

                int hour = alarmTimePicker.getHour();
                int min= alarmTimePicker.getMinute();
                System.out.println("hour is:"+hour);
                System.out.println("minute is:"+min);

                String hourString = String.valueOf(hour);
                String minString =  String.valueOf(min);

                //Fix formatting of displayed set time
                if(hour > 12) {hourString = String.valueOf(hour - 12);}
                if(min < 10){minString = "0" + String.valueOf(min);}
                setAlarmText("Alarm set to "+ hourString + ":" + minString);

                // Indicates when on button is clicked for the clock
                alarmIntent.putExtra("is","ON");

                //Give the pending Intent a new ID based on which pill is chosen
                int idNumber=PillsList.getID();
                System.out.println("ID NUMBER ON :"+PillsList.getID());
                waitingIntent = PendingIntent.getBroadcast(Alarm.this, idNumber,alarmIntent, PendingIntent.FLAG_ONE_SHOT);

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
                setAlarmText("Alarm OFF!");
                //cancel the waiting intent
                alarmManager.cancel(waitingIntent);
                // Indicates when off button is clicked for the clock
                alarmIntent.putExtra("is","OFF");
                sendBroadcast(alarmIntent);
            }
        });
    }

    private void setAlarmText(String output) {
        updateText.setText(output);
    }
 }
