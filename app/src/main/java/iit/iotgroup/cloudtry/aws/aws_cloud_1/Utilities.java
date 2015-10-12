package iit.iotgroup.cloudtry.aws.aws_cloud_1;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Main Activity which opens when app opens
 *
 * Created by Abhinav Tripathi on 06-Oct-15.
 */
public class Utilities {
    public static String GPS_LOCATION_DOMAIN_NAME = "gps_location";
    public static String GPS_LAT_ATTR_NAME = "latitude";
    public static String GPS_LONG_ATTR_NAME = "longitude";
    public static String GPS_TIMESTAMP_NAME = "timeStamp";

    public static String ACCESS_KEY = "AKIAJ4WB2PVVVTKSJLFQ";
    public static String SECRET_KEY = "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";

    public static boolean isNetworkAvailable(Context ctx)
    {
        ConnectivityManager ctvMngr = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo aNetInfo = ctvMngr.getActiveNetworkInfo();
        return aNetInfo != null && aNetInfo.isAvailable();
    }

    public static final String STORAGE_INTERVAL_PREF_KEY = "NUM_ALARMS_PREFERENCE";
    public static int getPrefInt(Context ctx, String prefKey)
    {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(ctx.getApplicationContext());
        return sp.getInt(prefKey, 0);
    }
    public static void savePrefInt(Context ctx, String prefKey, int prefValue)
    {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(ctx.getApplicationContext());
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(prefKey, prefValue);
        editor.apply();
    }

    public static void storeUpdateInterval(int numAlarm, Context ctx)
    {
        savePrefInt(ctx, STORAGE_INTERVAL_PREF_KEY, numAlarm);
    }
    public static int getUpdateInterval(Context ctx)
    {
        return getPrefInt(ctx, STORAGE_INTERVAL_PREF_KEY);
    }
    public static long getNextMinForAlarm(Context ctx)
    {
        int interval = getUpdateInterval(ctx);
        if(interval<=0)
            return 0;
        // time at which alarm will be scheduled here alarm is scheduled at 1 day from current time,
        // we fetch  the current time in milliseconds and added 1 day time
        // i.e. 24*60*60*1000= 86,400,000   milliseconds in a day
        Calendar cal = new GregorianCalendar();
        int h = cal.get(Calendar.HOUR_OF_DAY), m = cal.get(Calendar.MINUTE);
        int totT = h*60 + m;
        int n = totT/interval;
        return (n+1)*interval - totT;
    }
}
