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
    int startId;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int StartId) {
        System.out.println("inside onstart command");

        String status = intent.getExtras().getString("is");
        System.out.println("Status = "+ status);
        if (status != null) {
            switch (status) {
                case "ON":
                    System.out.println("ON CASE");
                    startId = 1;
                    break;
                case "OFF":
                    System.out.println("OFF CASE");
                    startId = 0;
                    break;
                default:
                    System.out.println("DEFAULT CASE");
                    startId = 1000;
                    break;
            }
        }

        if (!this.isPlaying && startId == 1){
            song = MediaPlayer.create(this, R.raw.ringtone);
            song.start();
            this.isPlaying=true;
            this.startId= 0;
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

            // Start notification
            notifyManager.notify(0, popupNotification);
        }
        else if(this.isPlaying && startId == 0) {
            song.stop();
            song.reset();
            notifyManager.cancelAll();
            this.isPlaying=false;
            this.startId= 0;
        }
        else{
            System.out.println("Code shouldn't enter this. WHY ARE YOU HERE?");
            System.out.println("id="+ this.startId);
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
