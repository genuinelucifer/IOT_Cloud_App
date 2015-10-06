package iit.iotgroup.cloudtry.aws.aws_cloud_1;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

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

    public static String getUserName()
    {
        return "Abhinav";
    }

    public static boolean isNetworkAvailable(Context ctx)
    {
        ConnectivityManager ctvMngr = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo aNetInfo = ctvMngr.getActiveNetworkInfo();
        return aNetInfo != null && aNetInfo.isAvailable();
    }
}
