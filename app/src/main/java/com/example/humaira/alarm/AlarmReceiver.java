package com.example.humaira.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;


public class AlarmReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("Humaira: ", "Received broadcast");
        Intent intent1 = new Intent(context, RingtoneService.class);
        intent1.putExtra("Humaira",intent.getStringExtra("Humaira"));
        Log.e("Humaira: ", "intent ccreated");
        context.startService(intent1);
    }


}
