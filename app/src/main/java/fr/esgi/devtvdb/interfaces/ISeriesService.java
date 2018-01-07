package fr.esgi.devtvdb.interfaces;

import java.util.ArrayList;
import java.util.List;

import fr.esgi.devtvdb.entities.LoginData;
import fr.esgi.devtvdb.entities.Series;
import fr.esgi.devtvdb.entities.SeriesUpdate;
import fr.esgi.devtvdb.entities.Token;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ISeriesService {

    public static final String BASE_URL = "https://api.thetvdb.com/";
    public static final String HEADER_ACCEPT = "Accept";
    public static final String HEADER_ACCEPT_LANGUAGE = "Accept-Language";
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String PATH_LOGIN = "login";
    public static final String PATH_REFRESH = "refresh_token";
    public static final String PATH_SERIES_BY_ID = "series/{id}";
    public static final String PATH_UPDATED_SERIES = "updated/query";
    public static final String API_KEY = "1E9E68CB47DBB318";



    /**
     * Returns a session token to be included in the rest of the requests. Note that API key authentication is required
     * for all subsequent requests and user auth is required for routes in the User section.
     */
    @POST(PATH_LOGIN)
    Call<Token> login(
            @Body LoginData loginData
    );

    /**
     * Refreshes your current, valid JWT token and returns a new token. Hit this route so that you do not have to post
     * to /login with your API key and credentials once you have already been authenticated.
     */
    @GET(PATH_REFRESH)
    Call<Token> refreshToken();

    @GET(PATH_SERIES_BY_ID)
    Call<Series> getSeriesById(
            @Path("id") Long id,
            @Header(ISeriesService.HEADER_AUTHORIZATION) String token,
            @Header(ISeriesService.HEADER_ACCEPT_LANGUAGE) String language
    );

    @GET(PATH_UPDATED_SERIES)
    Call<ArrayList<SeriesUpdate>> getUpdatedSeries(
            @Query("fromTime") Long fromTime,
            @Header(ISeriesService.HEADER_AUTHORIZATION) String token
            );

}
