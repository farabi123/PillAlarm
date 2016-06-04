package com.example.admin.pillalarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by admin on 5/31/2016.
 */
public class AlarmReceiver extends BroadcastReceiver {
    static String getTheString;
    @Override
    public void onReceive(Context context, Intent intent){
        System.out.println("In the receiver");

        getTheString = intent.getExtras().getString("is");
        System.out.println("The string is "+ getTheString);

        //Make an intent for the AlarmService clss
        Intent serviceIntent = new Intent(context, AlarmService.class);
        //Start the alarm service
        context.startService(serviceIntent);
    }
    static public String stringValue(){
        return getTheString;
    }
}
