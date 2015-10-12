package iit.iotgroup.cloudtry.aws.aws_cloud_1;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Toast;

import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {

	Button setButton;
    NumberPicker npHours, npMinutes;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setButton = (Button) findViewById(R.id.btnSetInterval);
        npHours   = (NumberPicker) findViewById(R.id.npNumHours);
        npMinutes = (NumberPicker) findViewById(R.id.npNumMinutes);

        npHours.setMinValue(0);
        npHours.setMaxValue(24);
        npMinutes.setMinValue(0);
        npMinutes.setMaxValue(59);

        int n = Utilities.getUpdateInterval(this);
        npHours.setValue(n/60);
        npMinutes.setValue(n%60);

        setButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n = npHours.getValue()*60 + npMinutes.getValue();
                Utilities.storeUpdateInterval(n, MainActivity.this);
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                // create an Intent and set the class which will execute when Alarm triggers, here we have
                // given AlarmReciever in the Intent, the onRecieve() method of this class will execute when
                Intent intentAlarm = new Intent(MainActivity.this, AlarmBroadcastReceiver.class);
                if(n==0)
                {
                    alarmManager.cancel(PendingIntent.getBroadcast(MainActivity.this, 1, intentAlarm, PendingIntent.FLAG_CANCEL_CURRENT));
                    Toast.makeText(MainActivity.this, "Alarms Canceled!", Toast.LENGTH_SHORT).show();
                    return;
                }

                long alt = Utilities.getNextMinForAlarm(MainActivity.this);
                //Toast.makeText(NumAlarms.this, "Stopped at " + curh + ":" + curm, Toast.LENGTH_SHORT).show();
                Long time = new GregorianCalendar().getTimeInMillis()+alt*60*1000;

                //set the alarm for particular time
                alarmManager.set(AlarmManager.RTC_WAKEUP, time, PendingIntent.getBroadcast(MainActivity.this, 1, intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT));
                //Toast.makeText(NumAlarms.this, "Alarm Scheduled for Tommrrow", Toast.LENGTH_LONG).show();
                Toast.makeText(MainActivity.this, "Alarms Set... for " + alt + " mins...", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
