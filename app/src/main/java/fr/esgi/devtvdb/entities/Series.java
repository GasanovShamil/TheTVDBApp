package fr.esgi.devtvdb.entities;

/**
 * Created by shgas on 11/12/2017.
 */

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Series implements Parcelable {


        public Long id = Long.valueOf(0);
        public String seriesName  = "No name";
        public List<String> aliases = new ArrayList<>();
        public String banner = "";
        public String status  = "";
        public String firstAired  = "";
        public String network  = "";
        public String networkId  = "";
        public String runtime  = "";
        public List<String> genre = new ArrayList<>();
        public String overview  = "";
        public Long lastUpdated = Long.valueOf(0);
        public String airsDayOfWeek = "";
        public String airsTime = "";
        public String rating = "";
        public String imdbId = "";
        public String zap2itId = "";
        public String added = "";
        public Integer addedBy = 0;
        public Double siteRating = 0.0;
        public Integer siteRatingCount = 0;

        @Override
        public int describeContents() {
                return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
                dest.writeLong(id);
                dest.writeString(seriesName);
                dest.writeStringList(aliases);
                dest.writeString(banner);
                dest.writeString(status);
                dest.writeString(firstAired);
                dest.writeString(network);
                dest.writeString(networkId);
                dest.writeString(runtime);
                dest.writeStringList(genre);
                dest.writeString(overview);
                dest.writeLong(lastUpdated);
                dest.writeString(airsDayOfWeek);
                dest.writeString(airsTime);
                dest.writeString(rating);
                dest.writeString(imdbId);
                dest.writeString(zap2itId);
                dest.writeString(added);
                dest.writeInt(1);
                dest.writeDouble(siteRating);
                dest.writeInt(siteRatingCount);
        }

        public static final Parcelable.Creator<Series> CREATOR
                = new Parcelable.Creator<Series>() {
                public Series createFromParcel(Parcel in) {
                        return new Series(in);
                }

                public Series[] newArray(int size) {
                        return new Series[size];
                }
        };

        private Series(Parcel in) {
                this.id = in.readLong();
                this.seriesName = in.readString();
                this.aliases = in.createStringArrayList();
                this.banner = in.readString();
                this.status = in.readString();
                this.firstAired = in.readString();
                this.network = in.readString();
                this.networkId = in.readString();
                this.runtime = in.readString();
                this.genre = in.createStringArrayList();
                this.overview = in.readString();
                this.lastUpdated = in.readLong();
                this.airsDayOfWeek = in.readString();
                this.airsTime = in.readString();
                this.rating = in.readString();
                this.imdbId = in.readString();
                this.zap2itId = in.readString();
                this.added = in.readString();
                this.addedBy = in.readInt();
                this.siteRating = in.readDouble();
                this.siteRatingCount = in.readInt();
        }


}
