package com.aad_team_42.travelmanticsrebranded.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.aad_team_42.travelmanticsrebranded.model.Explore;


@Database(entities = {Explore.class}, version = 1, exportSchema = false)
public abstract class TravelDealDatabase extends RoomDatabase {

    public abstract FavouriteTravelDealDao favouriteTravelDealDao();

    private static TravelDealDatabase INSTANCE;

    static TravelDealDatabase getDatabase(final Context context){
        if (INSTANCE==null){
            synchronized (TravelDealDatabase.class){
                if (INSTANCE==null){
                    INSTANCE= Room.databaseBuilder(context.getApplicationContext(), TravelDealDatabase.class, "favorite")
                            .fallbackToDestructiveMigration().build();
                }
            }
        }
        return INSTANCE;
    }



}
