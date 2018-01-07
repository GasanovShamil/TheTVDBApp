package fr.esgi.devtvdb.Services;

import fr.esgi.devtvdb.Interceptors.JsonDataInterseptor;
import fr.esgi.devtvdb.interfaces.ISeriesService;
import fr.esgi.devtvdb.interfaces.IUserService;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by shgas on 11/12/2017.
 */

public class TVDBServices {

    private static String token = "";
    private static IUserService _tvdbUserService;
    private static ISeriesService _tvdbSeriesService;

    public static IUserService getTvdbUserInstance() {
        synchronized (TVDBServices.class) {
            if (_tvdbUserService == null) {
                _tvdbUserService = new Retrofit.Builder()
                        .baseUrl(ISeriesService.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                        .create(IUserService.class);
            }
        }
        return _tvdbUserService;
    }

    public static ISeriesService getTvdbSeriesInstance() {
        synchronized (TVDBServices.class) {
            if (_tvdbSeriesService == null) {
                OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
                httpClient.addInterceptor(new JsonDataInterseptor());
                _tvdbSeriesService = new Retrofit.Builder()
                        .baseUrl(ISeriesService.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(httpClient.build())
                        .build()
                        .create(ISeriesService.class);
            }
        }
        return _tvdbSeriesService;
    }

    public static void setToken(String newToken){
        token = newToken;
    }

    public static String getToken(){
        return token;
    }
}
