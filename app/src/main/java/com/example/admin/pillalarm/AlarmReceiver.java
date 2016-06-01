package com.example.admin.pillalarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by admin on 5/31/2016.
 */
public class AlarmReceiver extends BroadcastReceiver {
    @Override
public void onReceive(Context context, Intent intent){
        System.out.println("It's receiving in the receiver");

        String getTheString = intent.getExtras().getString("extra");
        System.out.println("The string is "+ getTheString);

        Intent serviceIntent = new Intent(context, RingtoneService.class);
        serviceIntent.putExtra("extra", getTheString);
        //Start the ringtone service
        context.startService(serviceIntent);
    }

}
