package com.example.admin.pillalarm;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by admin on 5/31/2016.
 */
public class RingtoneService extends Service {

    MediaPlayer song;
    boolean isPlaying;
    int startId;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int StartId) {
        System.out.println("inside onstart command");

        String status = intent.getExtras().getString("extra");
        System.out.println("Status = "+ status);
        if (status != null) {
            switch (status) {
                case "ON":
                    System.out.println("ON CASE");
                    startId = 1;
                    break;
                case "Off":
                    System.out.println("OFF CASE");
                    startId = 0;
                    break;
                default:
                    System.out.println("DEFAULT CASE");
                    startId = 0;
                    break;
            }
        }

        if (!this.isPlaying && startId == 1){
            song = MediaPlayer.create(this, R.raw.ringtone);
            song.start();
            this.isPlaying=true;
            this.startId= 0;
        }
        else if(this.isPlaying && startId == 0) {
            song.stop();
            song.reset();
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
        Toast.makeText(this,"inside onDestroy", Toast.LENGTH_SHORT).show();
    }
}
