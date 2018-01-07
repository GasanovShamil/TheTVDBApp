package fr.esgi.devtvdb.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by shgas on 11/12/2017.
 */

public class SeriesUpdate implements Parcelable {

    public long id;
    public long lastUpdated;



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeLong(lastUpdated);
    }

    public static final Parcelable.Creator<SeriesUpdate> CREATOR
            = new Parcelable.Creator<SeriesUpdate>() {
        public SeriesUpdate createFromParcel(Parcel in) {
            return new SeriesUpdate(in);
        }

        public SeriesUpdate[] newArray(int size) {
            return new SeriesUpdate[size];
        }
    };

    private SeriesUpdate(Parcel in) {
        this.id = in.readLong();
        this.lastUpdated =  in.readLong();
    }

}