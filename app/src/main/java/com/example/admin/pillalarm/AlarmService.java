package com.example.admin.pillalarm;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

/**
 * Created by admin on 5/31/2016.
 */
public class AlarmService extends Service {

    MediaPlayer song;
    boolean isPlaying;
    NotificationManager notifyManager;
    int flag;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int StartId) {
        System.out.println("inside onstart command");

        String status = AlarmReceiver.stringValue();
        System.out.println("Status = "+ status);
        if (status != null) {
            if (status.equals("ON")) {
                System.out.println("ON CASE");
                flag = 1;
            }
            else if (status.equals("OFF")) {
                System.out.println("OFF CASE");
                flag = 0;
            }
            else{
                System.out.println("DEFAULT CASE");
                flag = 1000;
            }
        }

        if (!this.isPlaying && flag == 1){
            song = MediaPlayer.create(this, R.raw.ringtone);
            song.start();
            this.isPlaying=true;
            this.flag= 0;

            //Make a notification when the alarm is ringing
            notifyManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            Intent intentInAlarm = new Intent (this.getApplicationContext(), Alarm.class);
            PendingIntent pendingIntentInAlarm = PendingIntent.getActivity(this, 0,intentInAlarm,0 );

            Notification popupNotification = new NotificationCompat.Builder(this)
                    .setContentTitle ("TAKE YOUR PILLS!!!")
                    .setContentText("CLICK HERE TO TURN OFF")
                    .setContentIntent(pendingIntentInAlarm)
                    .setSmallIcon(R.drawable.bell)
                    .setAutoCancel(true)
                    .build();

            // Start the notification
            notifyManager.notify(0, popupNotification);
        }
        else if(this.isPlaying && flag == 0) {
            song.stop();
            song.reset();
            notifyManager.cancelAll();
            this.isPlaying=false;
            this.flag= 0;
        }
        else{
            System.out.println("Code shouldn't enter this. But the emulator is skipping frames");
            System.out.println("Not giving enough time to turn off the alarm before the next starts");
            System.out.println("flag="+ this.flag);
            System.out.println("music playing="+ this.isPlaying);
        }
        return START_NOT_STICKY;
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        this.isPlaying =false;
    }
}
