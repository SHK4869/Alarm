package com.example.humaira.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.content.Intent;


import java.util.Calendar;

public class MainActivity extends AppCompatActivity {


    TimePicker timePicker;
    Button alarmON;
    Button alarmOFF;
    TextView display;
    Calendar calendar;
    //AlarmReceiver alarmReceiver;
    PendingIntent pendingIntent;
    AlarmManager alarmManager;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("Humaira: ", "ON CREATE");

        //SharedPreferences x = getSharedPreferences("pref", Context.MODE_PRIVATE);
        //pendingIntent = x.get
        //if(savedInstanceState != null)
          //  pendingIntent = (PendingIntent) savedInstanceState.get("asd");

        //initialize the UI
        setUI();

        //initalize calendar
        calendar = Calendar.getInstance();

        //initliaze intent
        intent = new Intent(this, AlarmReceiver.class);


        //alarm manager servce
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        //get time from timepicker and display on textview if  alarm on is pressed
        alarmON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hours   = timePicker.getHour() == 0 ? 12 : timePicker.getHour();
                int minutes = timePicker.getMinute();
                display.setText("Alarm set for " + String.valueOf(hours) + " hours " + String.valueOf(minutes) + " minutes");

                //set time
                calendar.set(Calendar.HOUR_OF_DAY, timePicker.getHour());
                calendar.set(Calendar.MINUTE, timePicker.getMinute());

                //Log.e("Humaira: ", String.valueOf(calendar.getTimeInMillis()));

                intent.putExtra("Humaira","Yes");
                //intent.setAction(Long.toString(System.currentTimeMillis()));
                pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 34, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                Log.e("Humaira: ", "after pending intent is intialized");
                //set the alarm manager
                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                Log.e("Humaira: ", "after alarm manager is set");
                //pendingIntent.

            }

        });

        alarmOFF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display.setText("Alarm cancelled");
                intent.putExtra("Humaira","No");
                //following line was creating issues dunno why
                //intent.setAction(Long.toString(System.currentTimeMillis()));
                sendBroadcast(intent);
                pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 34, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                alarmManager.cancel(pendingIntent); //ERROR, cancel() called on null pending intent
                Log.e("Humaira: ", "after alarm manager is stopped");

            }
        });




    }
/*
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("asd",pendingIntent);
    }
*/
    private void setUI() {
        timePicker = findViewById(R.id.time);
        alarmOFF   = findViewById(R.id.off);
        alarmON    = findViewById(R.id.on);
        display    = findViewById(R.id.status);
    }


}
