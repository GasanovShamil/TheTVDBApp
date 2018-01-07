package fr.esgi.devtvdb.Helpers;

import android.content.Context;
import android.content.SharedPreferences;

import fr.esgi.devtvdb.entities.User;

/**
 * Created by shgas on 21/11/2017.
 */

public class SharedPreferencesHelper {
    private static final String _USER_PREFERENCES_FILE = "fr.esgi.devtvdb.userpreferences";

    public static void putUser(Context context, User user) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(_USER_PREFERENCES_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", user.getUsername());
        editor.putString("identifier", user.getIdentifier());
        editor.putString("token", user.getToken());
        editor.commit();
    }

    public static User getUser(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(_USER_PREFERENCES_FILE, Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", null);
        String password = sharedPreferences.getString("identifier", null);
        String token = sharedPreferences.getString("token", null);
        User user = (username != null && password != null)?new User(username,password,token):null;
        return user;
    }

    public static void setLanguage(Context context, String lang) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(_USER_PREFERENCES_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("language", lang);
        editor.commit();
    }

    public static String getDefaultLanguage(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(_USER_PREFERENCES_FILE, Context.MODE_PRIVATE);
        String language = sharedPreferences.getString("language", null);
        return language;
    }

}
