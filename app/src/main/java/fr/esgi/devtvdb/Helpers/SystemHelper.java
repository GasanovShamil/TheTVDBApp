package fr.esgi.devtvdb.Helpers;

import android.content.Context;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by shgas on 11/12/2017.
 */

public class SystemHelper {

    public static int getScreenOrientation(Context ctx) {
        if (ctx.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            return 2;
        else
            return 4;
    }

    public static boolean isOnline(Context ctx) {
        ConnectivityManager cm =
                (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }

}
