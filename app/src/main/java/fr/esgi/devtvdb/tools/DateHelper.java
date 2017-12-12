package fr.esgi.devtvdb.tools;

import java.util.Calendar;

/**
 * Created by shgas on 12/12/2017.
 */

public class DateHelper {
    public static Long getEpochTime(int day){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, day);

        return cal.getTimeInMillis()/1000;
    }
}
