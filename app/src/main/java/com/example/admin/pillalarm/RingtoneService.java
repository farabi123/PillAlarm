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
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int StartId){
        System.out.println("inside onstart command");
        song = MediaPlayer.create(this, R.raw.ringtone);
        song.start();
        return START_NOT_STICKY;
    }
    @Override
    public void onDestroy(){
        Toast.makeText(this,"inside onDestroy", Toast.LENGTH_SHORT).show();
    }
}
