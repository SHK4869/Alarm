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
        Log.e("Humaira: ",String.valueOf(intent.getExtras().get("Humaira").equals("Yes")));
        Log.e("Humaira: ",String.valueOf(intent.getStringExtra("Humaira").equals("Yes")));
        //tone = MediaPlayer.create(this, R.raw.ring);
        if(intent.getStringExtra("Humaira").equals("Yes")){

            tone = MediaPlayer.create(this, R.raw.ring);
            tone.start();
            tone.setLooping(true);
            Log.e("Humaira: ", "playing");
        }
        else {
            //tone.stop();
                if(tone!=null){
                    tone.stop();
                    tone.reset();
                    Log.e("Humaira: ", "playing stopped");
                }

            Log.e("Humaira: ", "Service stopped");
            stopSelf();



        }
        return Service.START_NOT_STICKY;
    }

    public void onDestroy() {
        super.onDestroy();

        Log.e("MyActivity", "on Destroy");
    }
}
