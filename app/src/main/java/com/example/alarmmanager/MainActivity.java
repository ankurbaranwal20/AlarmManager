package com.example.alarmmanager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button b1,b2;
    AlarmManager alarmManager;
    PendingIntent pendingIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 =(Button)findViewById(R.id.bt1);
        b2 = (Button)findViewById(R.id.bt2);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAlert();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopAlarm();
            }
        });

    }

    private void stopAlarm()
    {
        finish();
    }

    private void startAlert()
    {
        EditText ed = (EditText)findViewById(R.id.ed1);
        int i = Integer.parseInt(ed.getText().toString());
        Intent intent = new Intent(this,MyBroadcastReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this.getApplicationContext(),234324243,intent,0);
        alarmManager =(AlarmManager)getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+ (i * 1000),pendingIntent);
        Toast.makeText(this, "Alarm after" + i + " seconds", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onDestroy() {
        Process.killProcess(Process.myPid());
        super.onDestroy();
    }
}
