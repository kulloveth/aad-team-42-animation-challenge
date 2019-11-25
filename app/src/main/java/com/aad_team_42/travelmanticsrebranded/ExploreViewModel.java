package com.aad_team_42.travelmanticsrebranded;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.aad_team_42.travelmanticsrebranded.database.TravelDealRepository;
import com.aad_team_42.travelmanticsrebranded.model.Explore;

import java.util.List;

public class ExploreViewModel extends AndroidViewModel {
    private TravelDealRepository mTravelDealRepo;


    public ExploreViewModel(Application application) {
        super(application);
        mTravelDealRepo = new TravelDealRepository(application);

    }

    public LiveData<List<Explore>> getExplore() {
        return mTravelDealRepo.getAllFav();
    }

    public void addExplore(Explore travelDeal) {
        mTravelDealRepo.insert(travelDeal);
    }

}
