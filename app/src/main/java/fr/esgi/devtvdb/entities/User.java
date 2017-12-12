package fr.esgi.devtvdb.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by shgas on 11/10/2017.
 */

public class User implements Parcelable {
    private String username, identifier, token;

    public User(String userName, String identifier, String token){
        this.username = userName;
        this.identifier = identifier;
        this.token = token;
    };



    public void setToken(String token){
        this.token = token;
    };
    public String getToken(){
        return token;
    };
    public void setIdentifier(String identifier){
        this.identifier = identifier;
    };
    public String getIdentifier(){
        return identifier;
    };

    public void setUsername(String username){
        this.username = username;
    };
    public String getUsername(){
        return username;
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(identifier);
        dest.writeString(token);
    }

    public static final Parcelable.Creator<User> CREATOR
            = new Parcelable.Creator<User>() {
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };

    private User(Parcel in) {
        this.username = in.readString();
        this.identifier = in.readString();
        this.token = in.readString();
    }
}