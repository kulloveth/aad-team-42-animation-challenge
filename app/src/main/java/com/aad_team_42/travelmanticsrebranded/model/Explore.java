package com.aad_team_42.travelmanticsrebranded.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity(tableName = "favourite_travel_deals")
public class Explore implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;


    private String destination;
    private String about;
    private String price;
    private String imageUrl;

    @Ignore
    public Explore() {
    }

    public Explore(String destination, String about, String price, @NonNull int id, String imageUrl) {
        this.destination = destination;
        this.about = about;
        this.price = price;
        this.id = id;
        this.imageUrl = imageUrl;
    }

    @Ignore
    public Explore(String destination, String about, String price, String imageUrl) {
        this.destination = destination;
        this.about = about;
        this.price = price;
        // this.id = id;
        this.imageUrl = imageUrl;
    }

    @Ignore
    protected Explore(Parcel in) {
        destination = in.readString();
        about = in.readString();
        price = in.readString();
        id = in.readInt();
        imageUrl = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(destination);
        dest.writeString(about);
        dest.writeString(price);
        dest.writeInt(id);
        dest.writeString(imageUrl);
    }

    public static final Creator<Explore> CREATOR = new Creator<Explore>() {
        @Override
        public Explore createFromParcel(Parcel in) {
            return new Explore(in);
        }

        @Override
        public Explore[] newArray(int size) {
            return new Explore[size];
        }
    };

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


}
