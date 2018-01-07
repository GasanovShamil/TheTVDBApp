package fr.esgi.devtvdb.entities;

/**
 * Created by shgas on 11/12/2017.
 */

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class Series extends RealmObject implements Parcelable {

        @PrimaryKey
        public Long id = Long.valueOf(0);
        public String seriesName ;
        public RealmList<String> aliases = new RealmList<>();
        public String banner ;
        public String status  = "";
        public String firstAired  = "";
        public String network  = "";
        public String networkId  = "";
        public String runtime  = "";
        public RealmList<String> genre = new RealmList<>();
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

        public Series(){};

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
                this.aliases = new RealmList<>();
                this.aliases.addAll(in.createStringArrayList());
                this.banner = in.readString();
                this.status = in.readString();
                this.firstAired = in.readString();
                this.network = in.readString();
                this.networkId = in.readString();
                this.runtime = in.readString();
                this.genre = new RealmList<>();
                this.genre.addAll(in.createStringArrayList());
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


        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getSeriesName() {
                return seriesName;
        }

        public void setSeriesName(String seriesName) {
                this.seriesName = seriesName;
        }

        public RealmList<String> getAliases() {
                return aliases;
        }

        public void setAliases(RealmList<String> aliases) {
                this.aliases = aliases;
        }



        public String getBanner() {
                return banner;
        }

        public void setBanner(String banner) {
                this.banner = banner;
        }

        public String getStatus() {
                return status;
        }

        public void setStatus(String status) {
                this.status = status;
        }

        public String getFirstAired() {
                return firstAired;
        }

        public void setFirstAired(String firstAired) {
                this.firstAired = firstAired;
        }

        public String getNetwork() {
                return network;
        }

        public void setNetwork(String network) {
                this.network = network;
        }

        public String getNetworkId() {
                return networkId;
        }

        public void setNetworkId(String networkId) {
                this.networkId = networkId;
        }

        public String getRuntime() {
                return runtime;
        }

        public void setRuntime(String runtime) {
                this.runtime = runtime;
        }

        public RealmList<String> getGenre() {
                return genre;
        }

        public void setGenre(RealmList<String> genre) {
                this.genre = genre;
        }

        public String getOverview() {
                return overview;
        }

        public void setOverview(String overview) {
                this.overview = overview;
        }

        public Long getLastUpdated() {
                return lastUpdated;
        }

        public void setLastUpdated(Long lastUpdated) {
                this.lastUpdated = lastUpdated;
        }

        public String getAirsDayOfWeek() {
                return airsDayOfWeek;
        }

        public void setAirsDayOfWeek(String airsDayOfWeek) {
                this.airsDayOfWeek = airsDayOfWeek;
        }

        public String getAirsTime() {
                return airsTime;
        }

        public void setAirsTime(String airsTime) {
                this.airsTime = airsTime;
        }

        public String getRating() {
                return rating;
        }

        public void setRating(String rating) {
                this.rating = rating;
        }

        public String getImdbId() {
                return imdbId;
        }

        public void setImdbId(String imdbId) {
                this.imdbId = imdbId;
        }

        public String getZap2itId() {
                return zap2itId;
        }

        public void setZap2itId(String zap2itId) {
                this.zap2itId = zap2itId;
        }

        public String getAdded() {
                return added;
        }

        public void setAdded(String added) {
                this.added = added;
        }

        public Integer getAddedBy() {
                return addedBy;
        }

        public void setAddedBy(Integer addedBy) {
                this.addedBy = addedBy;
        }

        public Double getSiteRating() {
                return siteRating;
        }

        public void setSiteRating(Double siteRating) {
                this.siteRating = siteRating;
        }

        public Integer getSiteRatingCount() {
                return siteRatingCount;
        }

        public void setSiteRatingCount(Integer siteRatingCount) {
                this.siteRatingCount = siteRatingCount;
        }
}
