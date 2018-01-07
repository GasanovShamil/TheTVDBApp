package fr.esgi.devtvdb.Services;

import java.io.IOException;

import fr.esgi.devtvdb.entities.LoginData;
import fr.esgi.devtvdb.entities.Token;
import fr.esgi.devtvdb.interfaces.IUserService;

/**
 * Created by shgas on 14/12/2017.
 */

public class UserService {
    private static IUserService _userService;

    public static Token getToken(LoginData loginData){
        Token token;
        _userService = TVDBServices.getTvdbUserInstance();
        try {
            token = _userService.login(loginData).execute().body();
        } catch (IOException e) {
            e.printStackTrace();
            token = null;
        }
        return token;
    }
}
