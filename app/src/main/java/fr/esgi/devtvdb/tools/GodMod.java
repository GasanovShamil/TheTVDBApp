package fr.esgi.devtvdb.tools;

import android.content.Context;
import android.content.res.Configuration;

/**
 * Created by shgas on 11/12/2017.
 */

public class GodMod {

    public static int getScreenOrientation(Context ctx) {
        if (ctx.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            return 2;
        else
            return 4;
    }
}
