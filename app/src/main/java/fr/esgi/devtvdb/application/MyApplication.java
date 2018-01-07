package fr.esgi.devtvdb.application;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by shgas on 02/01/2018.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().name("tvdb.realm").build();
        Realm.setDefaultConfiguration(config);
    }

}
