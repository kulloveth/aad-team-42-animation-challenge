package com.aad_team_42.travelmanticsrebranded.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.aad_team_42.travelmanticsrebranded.model.Explore;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;


@Dao
interface FavouriteTravelDealDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFavouriteTravelDeals(Explore... favouriteTravelDeal);


    @Query("SELECT * FROM favourite_travel_deals ORDER BY id ASC")
   LiveData<List<Explore>> fetchAllFavourites();



}
