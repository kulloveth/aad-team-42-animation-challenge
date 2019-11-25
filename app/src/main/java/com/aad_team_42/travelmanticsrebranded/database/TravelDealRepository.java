package com.aad_team_42.travelmanticsrebranded.database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.aad_team_42.travelmanticsrebranded.model.Explore;

import java.util.List;


public class TravelDealRepository {


    private FavouriteTravelDealDao travelDealDao;

    public TravelDealRepository(Application application) {
        TravelDealDatabase travelDealDatabase = TravelDealDatabase.getDatabase(application);
        travelDealDao = travelDealDatabase.favouriteTravelDealDao();
    }

    public LiveData<List<Explore>> getAllFav() {
        return travelDealDao.fetchAllFavourites();
    }

    public void insert(Explore travelDeal) {
        new InsertAsyncTask(travelDealDao).execute(travelDeal);
    }

    private static class InsertAsyncTask extends AsyncTask<Explore, Void, Void> {

        private FavouriteTravelDealDao mAsyncTravelDao;

        private InsertAsyncTask(FavouriteTravelDealDao travelDealDao) {
            mAsyncTravelDao = travelDealDao;
        }

        @Override
        protected Void doInBackground(Explore... favouriteTravelDeals) {
            mAsyncTravelDao.insertFavouriteTravelDeals(favouriteTravelDeals[0]);
            return null;
        }

    }

}
