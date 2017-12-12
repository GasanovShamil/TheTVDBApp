package fr.esgi.devtvdb.entities;

/**
 * Created by shgas on 24/11/2017.
 */

public class LoginData {
    public String apikey;
    public String username;
    public String userkey;


    public LoginData(String apikey, String username, String userkey) {
        this.apikey = apikey;
        this.username = username;
        this.userkey = userkey;
    }
}
