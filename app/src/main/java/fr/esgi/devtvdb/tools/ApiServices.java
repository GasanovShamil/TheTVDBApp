package fr.esgi.devtvdb.tools;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by shgas on 11/12/2017.
 */

public class ApiServices{

    private static ITheTVDB _tvdbService;

    public static ITheTVDB getTvdbInstance() {
        if (_tvdbService == null) { //if there is no instance available... create new one
            synchronized (ApiServices.class) {
                if (_tvdbService == null) _tvdbService = new Retrofit.Builder()
                        .baseUrl(ITheTVDB.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                        .create(ITheTVDB.class);
            }
        }
        return _tvdbService;
    }
}
