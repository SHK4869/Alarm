package com.example.humaira.alarm;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class RingtoneService extends Service{

    public static MediaPlayer tone;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        //start playing ringtone


        Log.e("Humaira: ", "service started");
        tone = MediaPlayer.create(this, R.raw.ring);
        if(intent.getStringExtra("Humaira").equals("Yes")){
            tone.start();
            tone.setLooping(true);
            Log.e("Humaira: ", "playing");
        }
        else {
            //tone.stop();
            //DOESN'T WORK, tone object is lost too after the app is destroyed it seems....
            if(tone.isPlaying()) {
                tone.reset();
                tone.stop();
                tone.release();
                Log.e("Humaira: ", "stopped");
            }


        }
        return Service.START_STICKY;
    }

    public void onDestroy() {
        super.onDestroy();

        Log.e("MyActivity", "on Destroy");
    }
}
