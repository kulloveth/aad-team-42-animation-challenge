package com.aad_team_42.travelmanticsrebranded.views.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aad_team_42.travelmanticsrebranded.ExploreViewModel;
import com.aad_team_42.travelmanticsrebranded.R;
import com.aad_team_42.travelmanticsrebranded.adapters.ExploreAdapter;
import com.aad_team_42.travelmanticsrebranded.adapters.FavoriteAdapter;
import com.aad_team_42.travelmanticsrebranded.model.Explore;
import com.aad_team_42.travelmanticsrebranded.utils.FirebaseUtils;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private ExploreAdapter mAdapter;
    private ChildEventListener mChildEventLisener;
    private ProgressBar progressBar;
    TextView tvError;
   // List<Explore> mExplore = new ArrayList<>();
    private ExploreViewModel mExploreViewModel;

    public FavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_explore, container, false);
        progressBar = view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        tvError = view.findViewById(R.id.network_error);
        tvError.setVisibility(View.INVISIBLE);
        mAdapter = new ExploreAdapter();

        mRecyclerView = view.findViewById(R.id.recylerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mExploreViewModel = ViewModelProviders.of(this).get(ExploreViewModel.class);
        mExploreViewModel.getExplore().observe(this, new Observer<List<Explore>>() {
            @Override
            public void onChanged(List<Explore> explores) {
             mAdapter.setExplore(explores);
                mRecyclerView.setAdapter(mAdapter);
            }
        });
        return view;
    }


}
